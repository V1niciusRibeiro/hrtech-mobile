package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.fiap.hr_tech.mvvm.view_model.AppHeaderViewModel

@Composable
fun AppHeader(viewModel: AppHeaderViewModel) {

    val context = LocalContext.current
    val sideBarOpen by viewModel.sideBarOpen.observeAsState(initial = false)

    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(5f)
            .background(
                Color(context.getColor(R.color.light_gray)),
                RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
            )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = .75.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
                )
        ) {
            Row {
                Spacer(Modifier.size(15.dp))
                Box(
                    Modifier
                        .fillMaxHeight(.4f)
                        .aspectRatio(1f)
                        .clickable { viewModel.sideBarOpenChengeValue(!sideBarOpen) }) {
                    BurgerMenu(sideBarOpen)
                }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Pontos", fontSize = 22.sp)
            }
        }
    }
}

@Composable
fun BurgerMenu(sideBarOpen: Boolean) {

    Column {
        repeat(3) {
            Spacer(
                Modifier
                    .fillMaxSize()
                    .weight(.5f)
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.Black)
            )
            Spacer(
                Modifier
                    .fillMaxSize()
                    .weight(.5f)
            )
        }
    }

}


