package br.com.fiap.hr_tech.mvvm.view.component.work_hour

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
fun WorkHoursPopup(viewModel: WorkHoursScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val id by viewModel.idPopup.observeAsState(initial = 0)
    val title by viewModel.titlePopup.observeAsState(initial = "")
    val hour by viewModel.hourPopup.observeAsState(initial = "")
    val description by viewModel.descriptionPopup.observeAsState(initial = "")

    AlertDialog(
        onDismissRequest = {
            viewModel.openPopupChangeValue(false)
        },
        title = {
            Text(title + " " + context.getString(R.string.work_hour_name))
        },
        text = {
            Column {
                OutlinedTextField(
                    value = description,
                    label = { Text(context.getString(R.string.description)) },
                    onValueChange = { viewModel.descriptionPopupChangeValue(it) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = blue,
                        focusedLabelColor = blue
                    )
                )
                OutlinedTextField(
                    value = hour,
                    label = { Text(context.getString(R.string.hour)) },
                    onValueChange = { viewModel.hourPopupChangeValue(it) },
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
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { viewModel.openPopupChangeValue(false) }) {
                    Text(text = context.getString(R.string.save), color = blue)
                }
                if (id > 0) {
                    TextButton(onClick = { viewModel.openPopupChangeValue(false) }) {
                        Text(text = context.getString(R.string.delete), color = blue)
                    }
                }
                TextButton(onClick = { viewModel.openPopupChangeValue(false) }) {
                    Text(text = context.getString(R.string.cancel), color = blue)
                }
            }
        },
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
