package br.com.fiap.hr_tech.mvvm.view.component.work_hour

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.WorkHoursScreenViewModel

@Composable
fun WorkHoursPopup(title: String, viewModel: WorkHoursScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val hour by viewModel.hour.observeAsState(initial = "")
    val description by viewModel.description.observeAsState(initial = "")

    AlertDialog(
        onDismissRequest = {
            viewModel.openPopupChangeValue(false)
        },
        title = {
            Text(title)
        },
        text = {
            Column {
                OutlinedTextField(
                    value = description,
                    label = { Text(context.getString(R.string.description)) },
                    onValueChange = { viewModel.descriptionChangeValue(it) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = blue,
                        focusedLabelColor = blue
                    )
                )
                OutlinedTextField(
                    value = hour,
                    label = { Text(context.getString(R.string.hour)) },
                    onValueChange = { viewModel.hourChangeValue(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = createHourMask(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = blue,
                        focusedLabelColor = blue
                    )
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                viewModel.openPopupChangeValue(false)
            }) {
                Text("OK", color = blue)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                viewModel.openPopupChangeValue(false)
            }) {
                Text("Cancelar", color = blue)
            }
        }
    )
}

fun createHourMask(): VisualTransformation {
    return VisualTransformation { originalText ->
        val digitsOnly = originalText.filter { it.isDigit() }

        if (digitsOnly.length <= 4) {
            TransformedText(buildAnnotatedString {
                if (digitsOnly.length >= 2) {
                    append(digitsOnly.substring(0, 2))
                    if (digitsOnly.length >= 4) {
                        append(":")
                        append(digitsOnly.substring(2, 4))
                    }
                } else {
                    append(digitsOnly)
                }
            }, OffsetMapping.Identity)
        } else {
            // Handle cases where the length of digitsOnly is greater than 4
            TransformedText(buildAnnotatedString {
                append(digitsOnly.substring(0, 2))
                append(":")
                append(digitsOnly.substring(2, 4))
            }, OffsetMapping.Identity)
        }
    }
}
