package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.fiap.hr_tech.mvvm.view_model.CalendarGridViewModel
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun CalendarGrid(calendarGridViewModel: CalendarGridViewModel, month: Int, year: Int) {

    val daySelected by calendarGridViewModel.daySelected.observeAsState(initial = 0)

    var calendar = prepareCalendar(month, year)

    Column {
        repeat(5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(7) {
                    Box {
                        ItemBoxCalendar(
                            text = calendar.dayOfMonth.toString(),
                            textColor = Color.Black,
                            borderColor = Color.Transparent,
                            backgroundColor = Color.Transparent
                        )
                    }
                    calendar = calendar.plusDays(1)
                }
            }
        }
    }

}

fun prepareCalendar(month: Int, year: Int): LocalDate {
    var localDate = LocalDate.of(year, month, 1)
    while (localDate.dayOfWeek != DayOfWeek.SUNDAY) {
        localDate = localDate.minusDays(1)
    }
    return localDate
}
