package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.hr_tech.R

@Composable
fun WeekdayList() {

    val context = LocalContext.current
    val weekdays = listOf(
        context.getString(R.string.sunday),
        context.getString(R.string.monday),
        context.getString(R.string.tuesday),
        context.getString(R.string.wednesday),
        context.getString(R.string.thursday),
        context.getString(R.string.friday),
        context.getString(R.string.saturday),
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        weekdays.forEach {
            Box(Modifier.fillMaxWidth().weight(1f).aspectRatio(1f)){
                ItemBoxCalendar(
                    text = it,
                    textColor = Color(R.color.gray),
                    borderColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            }
        }
    }

}