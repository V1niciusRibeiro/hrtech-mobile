package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.hr_tech.R

@Composable
fun DefaultInput(value: String, label: String, onValueChange: (String) -> Unit) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))

    OutlinedTextField(
        value = value,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.fillMaxWidth(.75f),
        label = { Text(label) },
        onValueChange = { onValueChange(it) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = blue,
            focusedLabelColor = blue
        )
    )

}