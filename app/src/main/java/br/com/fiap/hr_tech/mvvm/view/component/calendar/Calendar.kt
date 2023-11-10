package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.CalendarGridViewModel
import br.com.fiap.hr_tech.mvvm.view_model.CalendarViewModel
import br.com.fiap.hr_tech.mvvm.view_model.MonthYearSelectorViewModel
import br.com.fiap.hr_tech.util.getMonthName
import java.time.LocalDate

@Composable
fun Calendar(retractCalendar: Boolean, viewModel: CalendarViewModel): LocalDate? {

    val year by viewModel.year.observeAsState(initial = LocalDate.now().year)
    val month by viewModel.month.observeAsState(initial = LocalDate.now().monthValue)
    val dateSelected by viewModel.dateSelected.observeAsState(initial = null)
    val selectorMonthOpen by viewModel.selectorMonthOpen.observeAsState(initial = false)

    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 11.dp)
                .clickable {
                    viewModel.dateSelectedChangeValue(null)
                    viewModel.selectorOpenChangeValue(!selectorMonthOpen)
                }
        ) {
            Spacer(Modifier.fillMaxWidth(0.035f))
            Text(
                text = getMonthName(month) + ", " + year,
                fontSize = 25.sp,
            )
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = "Arrow_Down",
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        if (selectorMonthOpen) {
            viewModel.processMonthYearSelectorReturn(
                MonthYearSelector(year, month, MonthYearSelectorViewModel())
            )
        } else {
            viewModel.dateSelectedChangeValue(
                CalendarGrid(retractCalendar, CalendarGridViewModel(year, month))
            )
        }
    }
    return dateSelected
}

