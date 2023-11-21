package br.com.fiap.hr_tech.mvvm.view.component.work_hour

import android.content.Context
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
import androidx.compose.ui.text.input.KeyboardType
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.model.WorkHour
import br.com.fiap.hr_tech.mvvm.view_model.WorkHoursScreenViewModel

@Composable
fun WorkHoursPopup(viewModel: WorkHoursScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val hour by viewModel.hourPopup.observeAsState(initial = "")
    val workHour by viewModel.workHour.observeAsState(initial = null)
    val description by viewModel.descriptionPopup.observeAsState(initial = "")

    AlertDialog(
        onDismissRequest = {
            viewModel.openPopupChangeValue(false)
        },
        title = {
            Text(popupTitle(workHour, context))
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
                TextButton(onClick = {
                    viewModel.saveWorkHour(
                        description,
                        hour,
                        workHour!!,
                        context
                    )
                }
                ) {
                    Text(text = context.getString(R.string.save), color = blue)
                }
                if (workHour!!.id > 0) {
                    TextButton(onClick = {
                        viewModel.deleteWorkHour(workHour!!, context)
                    }
                    ) {
                        Text(text = context.getString(R.string.delete), color = blue)
                    }
                }
                TextButton(onClick = { viewModel.openPopupChangeValue(false) }) {
                    Text(text = context.getString(R.string.cancel), color = blue)
                }
            }
        }
    )
}

fun popupTitle(workHour: WorkHour?, context: Context): String {
    val mode =
        if (workHour!!.id > 0) context.getString(R.string.update) else context.getString(R.string.insert)
    return mode + " " + context.getString(R.string.work_hour_name)
}
