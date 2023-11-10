package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view.component.calendar.Calendar
import br.com.fiap.hr_tech.mvvm.view_model.CalendarViewModel
import br.com.fiap.hr_tech.mvvm.view_model.PontosScreenViewModel

@Composable
fun PontosScreen(viewModel: PontosScreenViewModel) {

    val context = LocalContext.current
    val dateSelected by viewModel.dateSelected.observeAsState(initial = null)
    val retractCalendar by viewModel.retractCalendar.observeAsState(initial = false)

    Column {
        viewModel.dateSelectedChangeValue(Calendar(CalendarViewModel(context), retractCalendar))
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Color(context.getColor(R.color.light_gray)),
                    RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = .75.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(Modifier.height(15.dp))
                    Row(
                        Modifier
                            .background(Color.Black, RoundedCornerShape(30.dp))
                            .fillMaxWidth(0.25f)
                            .height(5.dp)
                            .clickable {
                                if (dateSelected != null){
                                    viewModel.retractCalendarChangeValue(!retractCalendar)
                                }
                            }
                    ) {}
                }
            }
        }
    }
}