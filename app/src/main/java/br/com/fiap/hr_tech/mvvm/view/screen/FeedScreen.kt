package br.com.fiap.hr_tech.mvvm.view.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view.component.feed.News
import br.com.fiap.hr_tech.mvvm.view_model.FeedViewModel

@Composable
fun Feed(feedViewModel: FeedViewModel) {
    val noticias = listOf(
        "Sodexo Carregado Com R$600", "Feriado Natal 25/12/2022",
        "Sodexo Carregado Com R$600", "Feriado Finados 01/12/2022",
        "Sodexo Carregado Com R$600", "Feliz Aniversário!"
    )
    val datas = listOf(
        "25/12/2022", "24/12/2022", "01/11/2022", "25/10/2022", "25/10/2022", "18/10/2022"
    )
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(8.dp))
            noticias.forEachIndexed { index, noticia ->
                val data = datas.getOrElse(index) { "Data Indisponível" }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier =  Modifier.width(370.dp)
                ) {
                    Text(
                        text = data,
                        modifier = Modifier.padding(8.dp)
                    )
                    val cardColor = if (noticia.contains("Sodexo Carregado")) {
                        Color(0xFFBDFCCB)
                    } else {
                        Color(0xFFBFE1FF)
                    }

                    val imagemcard = when {
                        noticia.contains("Sodexo Carregado") -> painterResource(id = R.drawable.valerefeicao)
                        noticia.contains("Feriado Natal") -> painterResource(id = R.drawable.natal)
                        noticia.contains("Feriado Finados") -> painterResource(id = R.drawable.finados)
                        noticia.contains("Aniversário") -> painterResource(id = R.drawable.aniversario)
                        else -> null
                    }
                    News(
                        text = noticia,
                        fontSize = 20,
                        textColor = Color.Black,
                        backgroundColor = CardDefaults.cardColors(cardColor),
                        textAlign = TextAlign.Center,
                        image = imagemcard
                    )
                }
            }
            Text(
                text = "Mais...",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { }
            )
        }
    }
}

@Preview
@Composable
fun FeedPreview() {
    Feed(FeedViewModel())
}