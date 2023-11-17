package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view.component.DefaultInput
import br.com.fiap.hr_tech.mvvm.view_model.LoginScreenViewModel
import br.com.fiap.hr_tech.navigation.AppRoutes

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val login by viewModel.login.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.fillMaxHeight(.1f))
        Image(
            painter = painterResource(id = R.drawable.logo_hr_tech),
            contentDescription = "Logo_HrTech",
            modifier = Modifier.fillMaxWidth(.85f)
        )
        Spacer(Modifier.fillMaxHeight(.1f))
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .background(
                    Color(context.getColor(R.color.light_gray)),
                    RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(top = .75.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(Modifier.fillMaxHeight(.15f))
                    DefaultInput(
                        value = login,
                        label = context.getString(R.string.login),
                        onValueChange = { viewModel.loginChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.05f))
                    DefaultInput(
                        value = password,
                        label = context.getString(R.string.password),
                        onValueChange = { viewModel.passwordChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.35f))
                    TextButton(
                        onClick = { navController.navigate(AppRoutes.WORK_HOURS_ROUTE) },
                        modifier = Modifier
                            .fillMaxWidth(.75f)
                            .clip(RoundedCornerShape(40.dp))
                            .background(blue, RoundedCornerShape(40.dp))
                    ) {
                        Text(text = context.getString(R.string.enter), color = Color.White)
                    }
                    Spacer(Modifier.fillMaxHeight(.05f))
                    TextButton(
                        onClick = { navController.navigate(AppRoutes.REGISTER_ROUTE) },
                        modifier = Modifier
                            .fillMaxWidth(.75f)
                            .clip(RoundedCornerShape(40.dp))
                            .border(1.dp, blue, RoundedCornerShape(40.dp))
                    ) {
                        Text(text = context.getString(R.string.register), color = blue)
                    }
                }
            }
        }
    }

}