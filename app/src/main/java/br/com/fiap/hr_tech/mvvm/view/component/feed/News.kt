package br.com.fiap.hr_tech.mvvm.view.component.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun News(
    text: String,
    fontSize: Int,
    textColor: Color,
    backgroundColor: CardColors,
    textAlign: TextAlign,
    image: Painter? = null
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(80.dp)
            .clickable {}
        ,
        colors = backgroundColor
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = fontSize.sp,
                color = textColor,
                textAlign = textAlign,
                modifier = Modifier.weight(1f)
            )

            image?.let {
                Image(
                    painter = it,
                    contentDescription = "",
                    modifier = Modifier.height(35.dp)
                )
            }
        }
    }
}
