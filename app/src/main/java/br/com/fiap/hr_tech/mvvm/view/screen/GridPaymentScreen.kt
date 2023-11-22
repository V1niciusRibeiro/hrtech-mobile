package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.GridPaymentViewModel
import br.com.fiap.hr_tech.util.GlobalObject
import br.com.fiap.hr_tech.util.TypeMessage
import br.com.fiap.hr_tech.util.getMonthName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun GridPayment(viewModel: GridPaymentViewModel) {

    val context = LocalContext.current
    val openPopup by viewModel.openPopup.observeAsState(initial = false)
    val selectedYear by viewModel.selectedYear.observeAsState(initial = LocalDate.now().year)
    val selectedMonths by viewModel.selectedMonths.observeAsState(initial = listOf())
    val openDropDownMenu by viewModel.openDropDownMenu.observeAsState(initial = false)

    if (openPopup) {
        Popup(viewModel)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .padding(top = 20.dp, bottom = 5.dp)
                .clickable { viewModel.openDropDownMenuChangeValue(!openDropDownMenu) }
        ) {
            Column {
                Text(text = selectedYear.toString(), fontSize = 23.sp)
                DropdownMenu(
                    modifier = Modifier.fillMaxHeight(.15f),
                    expanded = openDropDownMenu,
                    onDismissRequest = { viewModel.openDropDownMenuChangeValue(false) }
                ) {
                    repeat(LocalDate.now().year - 1990) {
                        val year = LocalDate.now().year - it
                        DropdownMenuItem(
                            text = { Text(text = year.toString()) },
                            onClick = {
                                viewModel.selectedYearChangeValue(year)
                                viewModel.openDropDownMenuChangeValue(false)
                            }
                        )
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = "Drop_Down"
            )
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
                        val month = (x * 4) + y + 1
                        Box(
                            modifier = Modifier
                                .width(88.dp)
                                .height(74.5.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .absoluteOffset((-0.5).dp, (-2).dp)
                                .background(
                                    color =
                                    if (selectedMonths.contains(month))
                                        Color(context.getColor(R.color.dark_ice))
                                    else
                                        Color(context.getColor(R.color.ice)),
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .clickable {
                                    if (selectedMonths.contains(month)) {
                                        viewModel.selectedMonthsMinus(month, selectedMonths)
                                    } else {
                                        viewModel.selectedMonthsPlus(month, selectedMonths)
                                    }
                                }
                        )
                        Text(text = getMonthName(month).substring(0, 3), fontSize = 25.sp)
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = context.getString(R.string.clear_selection),
                color = Color(context.getColor(R.color.blue)),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { viewModel.clearSelectedMonths() }
            )
            Text(
                text = context.getString(R.string.select_all),
                color = Color(context.getColor(R.color.blue)),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { viewModel.selectAllMonths() }
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(.75.dp)
                .background(Color.Black)
        )
        Button(
            text = context.getString(R.string.view_payments),
            textColor = Color(context.getColor(R.color.blue)),
            backgroundColor = Color(context.getColor(R.color.light_blue)),
            onClick = { viewModel.getPayments(selectedMonths, selectedYear, context) }
        )
        Button(
            text = context.getString(R.string.generate_pdf),
            textColor = Color(context.getColor(R.color.red)),
            backgroundColor = Color(context.getColor(R.color.light_red)),
            onClick = {
                GlobalObject.message.addMessage(
                    context.getString(R.string.unavailable),
                    TypeMessage.ERROR
                )
            }
        )
    }

}

@Composable
fun Popup(viewModel: GridPaymentViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val payments by viewModel.payments.observeAsState(initial = listOf())

    AlertDialog(
        onDismissRequest = {
            viewModel.openPopupChangeValue(false)
        },
        title = {
            Text(context.getString(R.string.grid_payment_name))
        },
        text = {
            if (payments.isNotEmpty()) {
                LazyColumn(Modifier.fillMaxHeight()) {
                    items(payments) {
                        Spacer(Modifier.height(25.dp))
                        Spacer(
                            Modifier
                                .height(.75.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                        )
                        Spacer(Modifier.height(25.dp))
                        Column {
                            val date = LocalDateTime.parse(
                                it.date,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                            )
                            PopupInput(
                                value = it.id.toString(),
                                text = context.getString(R.string.id)
                            )
                            PopupInput(
                                value = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                                text = context.getString(R.string.date)
                            )
                            if (it.adjustments.isNotEmpty()) {
                                Text(
                                    text = context.getString(R.string.adjustment),
                                    fontSize = 18.sp
                                )
                                it.adjustments.forEach {
                                    Text(
                                        text = it.name + " - " + it.value,
                                        modifier = Modifier.padding(start = 20.dp)
                                    )
                                }
                            }
                            PopupInput(
                                value = it.value.toString(),
                                text = context.getString(R.string.value)
                            )
                        }
                    }
                }
            } else {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = context.getString(R.string.no_payments),
                        fontSize = 25.sp,
                        color = Color(context.getColor(R.color.gray))
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { viewModel.openPopupChangeValue(false) }) {
                Text(text = context.getString(R.string.ok), color = blue)
            }
        },
        modifier = Modifier.fillMaxHeight(.5f)
    )

}

@Composable
fun PopupInput(value: String, text: String) {
    val blue = Color(LocalContext.current.getColor(R.color.blue))
    OutlinedTextField(
        value = value,
        label = { Text(text = text, color = blue) },
        onValueChange = {},
        enabled = false,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = blue,
            focusedLabelColor = blue
        ),
        shape = RoundedCornerShape(25.dp),
    )
}

@Composable
fun Button(text: String, textColor: Color, backgroundColor: Color, onClick: () -> Unit) {
    Box(
        Modifier
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .fillMaxWidth(.9f)
            .background(backgroundColor, RoundedCornerShape(20.dp))
    ) {
        Text(text = text, color = textColor, modifier = Modifier.padding(15.dp, 20.dp))
    }
}
