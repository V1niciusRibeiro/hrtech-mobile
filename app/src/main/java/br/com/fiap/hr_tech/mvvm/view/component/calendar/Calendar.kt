package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.CalendarGridViewModel
import br.com.fiap.hr_tech.mvvm.view_model.CalendarViewModel
import br.com.fiap.hr_tech.mvvm.view_model.MonthYearSelectorViewModel
import java.time.LocalDate

@Composable
fun Calendar(viewModel: CalendarViewModel) {

    val today = LocalDate.now()
    val context = LocalContext.current
    val year by viewModel.year.observeAsState(initial = today.year)
    val month by viewModel.month.observeAsState(initial = today.monthValue)
    val selectorMonthOpen by viewModel.selectorMonthOpen.observeAsState(initial = false)

    Column {
        if (selectorMonthOpen) {
            val selectedYearMonth =
                MonthYearSelector(year, month, MonthYearSelectorViewModel(context))
            if (selectedYearMonth.dayOfMonth > 1) {
                viewModel.selectorOpenChangeValue(false)
            }
            viewModel.yearChangeValue(selectedYearMonth.year)
            viewModel.monthChangeValue(selectedYearMonth.monthValue)
        } else {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        viewModel.selectorOpenChangeValue(true)
                    }
            ) {
                Text(
                    text = viewModel.months[month - 1] + ", " + year,
                    fontSize = 25.sp,
                )
                Image(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = "Arrow_Down",
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            CalendarGrid(CalendarGridViewModel(year, month))
        }
    }

}
