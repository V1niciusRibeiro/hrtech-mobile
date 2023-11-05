package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun CalendarGrid(calendarGridViewModel: CalendarGridViewModel) {

    val context = LocalContext.current
    val calendar = calendarGridViewModel.calendar
    val daySelected by calendarGridViewModel.dateSelected.observeAsState(initial = null)

    Column {
        repeat(5) { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(7) { day ->
                    val calendarDate = calendar[(week * 7) + day]
                    if (calendarDate != null) {
                        Box(modifier = Modifier
                            .clip(shape = RoundedCornerShape(15.dp))
                            .clickable {
                                calendarGridViewModel.dateSelectedChangeValue(
                                    calendarDate.monthValue,
                                    calendarDate.dayOfMonth
                                )
                            }) {
                            ItemBoxCalendar(
                                text = calendarDate.dayOfMonth.toString(),
                                textColor = calendarGridViewModel.textColor(
                                    calendarDate,
                                    daySelected,
                                    context
                                ),
                                borderColor = calendarGridViewModel.borderColor(
                                    calendarDate,
                                    context
                                ),
                                backgroundColor = calendarGridViewModel.backgroundColor(
                                    calendarDate,
                                    daySelected,
                                    context
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(5.dp))
        }
    }

}
