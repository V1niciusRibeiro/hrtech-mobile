package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.hr_tech.mvvm.view_model.AppHeaderViewModel
import br.com.fiap.hr_tech.navigation.AppRoutes

@Composable
fun SideBarMenu(navController: NavController, viewModel: AppHeaderViewModel) {

    val sideBarOpen by viewModel.sideBarOpen.observeAsState(initial = false)
    val animationEnter by remember {
        mutableStateOf(slideInHorizontally())
    }
    val animationExit by remember {
        mutableStateOf(slideOutHorizontally() + fadeOut())
    }

    AnimatedVisibility(visible = sideBarOpen, enter = animationEnter, exit = animationExit) {
        Box(
            Modifier
                .fillMaxSize()
                .clickable { viewModel.sideBarOpenChengeValue(false) }) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.65f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Column(Modifier.fillMaxSize()) {
                    Menus(navController, viewModel)
                }
            }
        }
    }

}

@Composable
private fun Menus(navController: NavController, viewModel: AppHeaderViewModel) {

    val context = LocalContext.current

    data class Menu(
        val menuRoute: String,
        val menuName: String,
    )

    val menus = arrayOf(
        Menu(
            AppRoutes.WORK_HOURS_ROUTE,
            AppRoutes.getScreenName(AppRoutes.WORK_HOURS_ROUTE, context)
        ),
        Menu(
            AppRoutes.GRID_PAYMENT_ROUTE,
            AppRoutes.getScreenName(AppRoutes.GRID_PAYMENT_ROUTE, context)
        ),
        Menu(
            AppRoutes.FEED_ROUTE,
            AppRoutes.getScreenName(AppRoutes.FEED_ROUTE, context)
        ),
        Menu(
            AppRoutes.LOGIN_ROUTE,
            AppRoutes.getScreenName(AppRoutes.LOGIN_ROUTE, context)
        )
    )

    menus.forEach {
        Spacer(
            Modifier
                .fillMaxWidth()
                .aspectRatio(8f)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7f)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    navController.navigate(it.menuRoute)
                    viewModel.sideBarOpenChengeValue(false)
                }
        ) {
            Text(text = it.menuName, fontSize = 24.sp, color = Color.White)
            Text(text = it.menuName, fontSize = 22.sp)
        }
    }

}
