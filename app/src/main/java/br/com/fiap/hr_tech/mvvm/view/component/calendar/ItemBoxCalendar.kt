package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemBoxCalendar(text: String, textColor: Color, borderColor: Color, backgroundColor: Color) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(15.dp)
            )
    )
    {
        Text(text = text, color = textColor)
    }

}
