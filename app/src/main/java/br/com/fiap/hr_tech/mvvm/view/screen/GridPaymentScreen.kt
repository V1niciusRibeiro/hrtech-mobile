package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.mvvm.view.component.grid.Ano
import br.com.fiap.hr_tech.mvvm.view.component.grid.BotaoPadrao
import br.com.fiap.hr_tech.mvvm.view_model.GridPaymentViewModel


@Composable
fun GridPayment(gridPaymentViewModel: GridPaymentViewModel) {
    val months = listOf(
        "Jan", "Fev", "Mar", "Abr", "Mai", "Jun",
        "Jul", "Ago", "Set", "Out", "Nov", "Dez"
    )
    var selectedMonths by remember { mutableStateOf(emptySet<String>()) }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Ano()
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
            ) {
                items(months.size) { index ->
                    val month = months[index]
                    val isSelected = month in selectedMonths

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFF2173EF) else Color(0xFFDBDBDB)
                        ),
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedMonths = if (isSelected) {
                                    selectedMonths - month
                                } else {
                                    selectedMonths + month
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = month,
                                fontSize = 25.sp,
                                color = if (isSelected) Color.White else Color(0xFF000000),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Limpar Seleção",
                    color = Color(0xFF2173EF),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { selectedMonths = emptySet() }
                )
                Text(
                    text = "Selecionar Tudo",
                    color = Color(0xFF2173EF),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { selectedMonths = months.toSet() }
                )
            }
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(10.dp))
            BotaoPadrao(
                cor = ButtonDefaults.buttonColors(Color(0xFFBDD0FC)),
                text = "Visualizar Holerites",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                texto = Color(0xFF3F15EA)
            )
            Spacer(modifier = Modifier.height(10.dp))
            BotaoPadrao(
                cor = ButtonDefaults.buttonColors(Color(0xFFFCC8BD)),
                text = "Gerar PDF",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                texto = Color(0xFFEA1515)
            )


            Text(
                text = "Selecionados: ${selectedMonths.joinToString()}",
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF000000)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    run {
        GridPayment(GridPaymentViewModel())
    }
}
