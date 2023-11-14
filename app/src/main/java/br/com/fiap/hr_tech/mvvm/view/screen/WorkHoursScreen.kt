package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view.component.calendar.Calendar
import br.com.fiap.hr_tech.mvvm.view.component.work_hour.WorkHourItem
import br.com.fiap.hr_tech.mvvm.view.component.work_hour.WorkHoursPopup
import br.com.fiap.hr_tech.mvvm.view_model.CalendarViewModel
import br.com.fiap.hr_tech.mvvm.view_model.WorkHoursScreenViewModel
import br.com.fiap.hr_tech.util.getMonthName

@Composable
fun WorkHoursScreen(viewModel: WorkHoursScreenViewModel) {

    val context = LocalContext.current
    val openPopup by viewModel.openPopup.observeAsState(initial = false)
    val dateSelected by viewModel.dateSelected.observeAsState(initial = null)
    val retractCalendar by viewModel.retractCalendar.observeAsState(initial = false)

    if (openPopup) {
        WorkHoursPopup(
            context.getString(R.string.insert) + " " + context.getString(R.string.work_hour_name),
            viewModel
        )
    }

    Column {
        viewModel.dateSelectedChangeValue(Calendar(retractCalendar, CalendarViewModel()))
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Color(context.getColor(R.color.light_gray)),
                    RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = .75.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 25.dp, start = 25.dp, end = 25.dp)
                ) {
                    Box(
                        Modifier
                            .background(Color.Black, RoundedCornerShape(30.dp))
                            .fillMaxWidth(0.25f)
                            .height(5.dp)
                            .clickable {
                                if (dateSelected != null) {
                                    viewModel.retractCalendarChangeValue(!retractCalendar)
                                }
                            }
                    )
                    if (dateSelected == null) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = context.getString(R.string.select_day),
                                fontSize = 25.sp,
                                color = Color(context.getColor(R.color.gray))
                            )
                        }
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 25.dp)
                        ) {
                            Text(
                                text = dateSelected!!.dayOfMonth.toString() + " " +
                                        getMonthName(dateSelected!!.monthValue) + " " +
                                        dateSelected!!.year,
                                fontSize = 20.sp
                            )
                            Text(
                                text = context.getString(R.string.add),
                                fontSize = 20.sp,
                                color = Color(context.getColor(R.color.gray)),
                                modifier = Modifier.clickable {
                                    viewModel.openPopupChangeValue(true)
                                }
                            )
                        }
                        Column(Modifier.verticalScroll(rememberScrollState())) {
                            repeat(4) {
                                WorkHourItem(
                                    description = "TEste",
                                    hour = "08h00",
                                    textColor = Color(context.getColor(R.color.blue)),
                                    backgroundColor = Color(context.getColor(R.color.light_blue))
                                )
                                Spacer(
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(10f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}