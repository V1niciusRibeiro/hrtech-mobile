package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.fiap.hr_tech.R
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeekdayList() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (dayOfWeek in DayOfWeek.values()) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            ) {
                ItemBoxCalendar(
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase(),
                    textColor = Color(R.color.gray),
                    borderColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            }
        }
    }

}