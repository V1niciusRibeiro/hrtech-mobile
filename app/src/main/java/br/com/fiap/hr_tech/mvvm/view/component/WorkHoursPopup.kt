package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import br.com.fiap.hr_tech.mvvm.view_model.WorkHoursScreenViewModel

@Composable
fun WorkHoursPopup(title: String, viewModel: WorkHoursScreenViewModel) {
    AlertDialog(
        onDismissRequest = {
            viewModel.openPopupChangeValue(false)
        },
        title = {
            Text(title)
        },
        text = {
            Column {
                OutlinedTextField(value = "", label = { Text("Teste")}, onValueChange = {})
            }
        },
        confirmButton = {
            TextButton(onClick = {
                viewModel.openPopupChangeValue(false)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                viewModel.openPopupChangeValue(false)
            }) {
                Text("Cancelar")
            }
        }
    )
}