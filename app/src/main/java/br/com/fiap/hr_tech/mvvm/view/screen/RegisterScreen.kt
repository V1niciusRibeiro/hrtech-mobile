package br.com.fiap.hr_tech.mvvm.view.screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view.component.DefaultInput
import br.com.fiap.hr_tech.mvvm.view_model.RegisterScreenViewModel
import br.com.fiap.hr_tech.navigation.AppRoutes

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val name by viewModel.name.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val confirmPassword by viewModel.confirmPassword.observeAsState(initial = "")

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                text = context.getString(R.string.register),
                fontSize = 40.sp,
                color = Color(context.getColor(R.color.gray)),
            )
        }
        Box(
            Modifier
                .fillMaxSize()
                .weight(3f)
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
                    Spacer(Modifier.fillMaxHeight(.05f))
                    DefaultInput(
                        value = name,
                        label = context.getString(R.string.name),
                        onValueChange = { viewModel.nameChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.05f))
                    DefaultInput(
                        value = email,
                        label = context.getString(R.string.login),
                        onValueChange = { viewModel.emailChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.05f))
                    DefaultInput(
                        value = password,
                        label = context.getString(R.string.password),
                        onValueChange = { viewModel.passwordChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.05f))
                    DefaultInput(
                        value = confirmPassword,
                        label = context.getString(R.string.confirm_password),
                        onValueChange = { viewModel.confirmPasswordChangeValue(it) }
                    )
                    Spacer(Modifier.fillMaxHeight(.15f))
                    TextButton(
                        onClick = {
                            viewModel.doRegister(
                                name,
                                email,
                                password,
                                confirmPassword,
                                navController,
                                context
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(.75f)
                            .clip(RoundedCornerShape(40.dp))
                            .background(blue, RoundedCornerShape(40.dp))
                    ) {
                        Text(text = context.getString(R.string.register), color = Color.White)
                    }
                    Spacer(Modifier.fillMaxHeight(.05f))
                    TextButton(
                        onClick = { navController.navigate(AppRoutes.LOGIN_ROUTE) },
                        modifier = Modifier
                            .fillMaxWidth(.75f)
                            .clip(RoundedCornerShape(40.dp))
                            .border(1.dp, blue, RoundedCornerShape(40.dp))
                    ) {
                        Text(text = context.getString(R.string._return), color = blue)
                    }
                }
            }
        }
    }

}