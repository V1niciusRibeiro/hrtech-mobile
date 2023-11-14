package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun CalendarGrid(reduced: Boolean, viewModel: CalendarGridViewModel): LocalDate? {

    val context = LocalContext.current
    val dateSelected by viewModel.dateSelected.observeAsState(initial = null)
    val initialDay = if ((dateSelected != null) and reduced) dateSelected!!.dayOfMonth else 1
    val initialMonth = if ((dateSelected != null) and reduced) dateSelected!!.monthValue else 0
    var actualDate = viewModel.getInitialDate(initialMonth, initialDay)

    Column {
        WeekdayList()
        repeat(if (reduced) 1 else 5) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(7) {
                    val itemDate = actualDate
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(2.5.dp)
                            .aspectRatio(1f)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .clickable {
                                viewModel.dateSelectedChangeValue(
                                    if ((itemDate == dateSelected) and !reduced)
                                        null
                                    else
                                        itemDate
                                )
                            }
                    ) {
                        ItemBoxCalendar(
                            text = itemDate.dayOfMonth.toString(),
                            textColor = viewModel.textColor(itemDate, dateSelected, context),
                            borderColor = viewModel.borderColor(itemDate, context),
                            backgroundColor = viewModel.backgroundColor(
                                itemDate,
                                dateSelected,
                                context
                            )
                        )
                    }
                    actualDate = actualDate.plusDays(1)
                }
            }
        }
    }
    return dateSelected
}