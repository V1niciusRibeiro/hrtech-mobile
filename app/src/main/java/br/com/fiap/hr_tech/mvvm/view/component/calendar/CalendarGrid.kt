package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.hr_tech.mvvm.view_model.CalendarGridViewModel
import java.time.LocalDate

@Composable
fun CalendarGrid(viewModel: CalendarGridViewModel): LocalDate? {

    val context = LocalContext.current
    val calendar = viewModel.calendar
    val dateSelected by viewModel.dateSelected.observeAsState(initial = null)

    Column {
        WeekdayList()
        repeat(5) { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(7) { day ->
                    val calendarDate = calendar[(week * 7) + day]
                    Box(modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .clickable {
                            var localDate: LocalDate? = calendarDate
                            if (calendarDate == dateSelected) {
                                localDate = null
                            }
                            viewModel.dateSelectedChangeValue(localDate)
                        }) {
                        ItemBoxCalendar(
                            text = calendarDate.dayOfMonth.toString(),
                            textColor = viewModel.textColor(
                                calendarDate,
                                dateSelected,
                                context
                            ),
                            borderColor = viewModel.borderColor(
                                calendarDate,
                                context
                            ),
                            backgroundColor = viewModel.backgroundColor(
                                calendarDate,
                                dateSelected,
                                context
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(5.dp))
        }
    }

    return dateSelected

}
