package br.com.fiap.hr_tech.mvvm.view.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.MonthYearSelectorViewModel
import java.time.LocalDate

@Composable
fun MonthYearSelector(
    year: Int,
    month: Int,
    viewModel: MonthYearSelectorViewModel
): LocalDate {

    val context = LocalContext.current
    val year by viewModel.year.observeAsState(initial = year)
    val daySelected by viewModel.daySelected.observeAsState(initial = 1)
    val yearSelected by viewModel.yearSelected.observeAsState(initial = year)
    val monthSelected by viewModel.monthSelected.observeAsState(initial = month)

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        viewModel.yearChangeValue(year.minus(1))
                    }
            ) {
                Text(
                    text = "<",
                    fontSize = 30.sp
                )
            }
            Text(
                text = year.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(10.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        viewModel.yearChangeValue(year.plus(1))
                    }
            ) {
                Text(
                    text = ">",
                    fontSize = 30.sp
                )
            }
        }

        repeat(3) { x ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { y ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(75.dp)
                            .width(90.dp)
                            .padding(5.dp)
                            .background(
                                color = Color(context.getColor(R.color.light_gray)),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        val indexMonth = (x * 4) + y
                        Box(
                            modifier = Modifier
                                .width(88.dp)
                                .height(74.5.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .absoluteOffset((-0.5).dp, (-2).dp)
                                .background(
                                    color =
                                    if (((indexMonth + 1) == monthSelected) and (yearSelected == year))
                                        Color(context.getColor(R.color.dark_ice))
                                    else
                                        Color(context.getColor(R.color.ice)),
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .clickable {
                                    viewModel.monthSelectedChangeValue(indexMonth + 1)
                                    viewModel.yearSelectedChangeValue(year)
                                    viewModel.daySelectedChangeValue(2)
                                }
                        )
                        Text(text = viewModel.months[indexMonth], fontSize = 25.sp)
                    }
                }
            }
        }
    }
    return LocalDate.of(yearSelected, monthSelected, daySelected)
}
