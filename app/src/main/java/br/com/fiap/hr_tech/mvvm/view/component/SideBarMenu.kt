package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.hr_tech.mvvm.view_model.AppHeaderViewModel

@Composable
fun SideBarMenu(viewModel: AppHeaderViewModel) {

    val sideBarOpen by viewModel.sideBarOpen.observeAsState(initial = false)

    if (sideBarOpen){
        Box(
            Modifier
                .fillMaxSize()
                .clickable { viewModel.sideBarOpenChengeValue(false) }){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.7f)
                    .border(1.dp, color = Color.White.copy(0.3f))
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White.copy(alpha = 0.9f))
                    .padding(16.dp)
            ) {
                // Conteúdo da coluna
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text("Seu conteúdo aqui", color = MaterialTheme.colorScheme.onSurface)
                    // Adicione outros elementos conforme necessário
                }
            }
        }
    }

}