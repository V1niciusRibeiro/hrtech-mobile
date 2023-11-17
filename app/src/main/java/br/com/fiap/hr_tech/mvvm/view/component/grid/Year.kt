package br.com.fiap.hr_tech.mvvm.view.component.grid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun Ano() {

    val list = listOf("2020", "2021", "2022", "2023", "2024")
    val expanded = remember { mutableStateOf(false) }
    val currentValue = remember { mutableStateOf(list[0]) }

    Spacer(modifier = Modifier.height(30.dp))
    Box(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .clickable {
                expanded.value = !expanded.value

            }
            .align(Alignment.Center)) {

            Text(text = currentValue.value, fontSize = 32.sp)
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)


            DropdownMenu(expanded = expanded.value, onDismissRequest = {
                expanded.value = false
            }) {

                list.forEach {

                    DropdownMenuItem(text = { Text(text = it) }, onClick = {
                        expanded.value = false
                        currentValue.value = it
                    })

                }
            }
        }
    }
}

