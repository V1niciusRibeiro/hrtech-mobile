package br.com.fiap.hr_tech.mvvm.view.component.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R

@Composable
fun BotaoPadrao(text:String, cor: ButtonColors, modifier:Modifier, texto: Color) {
    Button(
        onClick = {},
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = cor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            , modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                color = texto,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(R.font.abel_regular))

            )
        }
    }
}