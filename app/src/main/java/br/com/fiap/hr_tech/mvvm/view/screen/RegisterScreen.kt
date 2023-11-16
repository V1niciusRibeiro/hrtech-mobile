package br.com.fiap.hr_tech.mvvm.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.hr_tech.R
import br.com.fiap.hr_tech.mvvm.view_model.RegisterScreenViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterScreenViewModel) {

    val context = LocalContext.current
    val blue = Color(context.getColor(R.color.blue))
    val name by viewModel.name.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val confirmPassword by viewModel.confirmPassword.observeAsState(initial = "")

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = name,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(.75f),
            label = { Text(context.getString(R.string.login)) },
            onValueChange = { viewModel.nameChangeValue(it) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                focusedLabelColor = blue
            )
        )
        Spacer(Modifier.fillMaxHeight(.05f))
        OutlinedTextField(
            value = email,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(.75f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(context.getString(R.string.password)) },
            onValueChange = { viewModel.passwordChangeValue(it) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                focusedLabelColor = blue
            )
        )
        OutlinedTextField(
            value = password,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(.75f),
            label = { Text(context.getString(R.string.login)) },
            onValueChange = { viewModel.passwordChangeValue(it) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                focusedLabelColor = blue
            )
        )
        Spacer(Modifier.fillMaxHeight(.05f))
        OutlinedTextField(
            value = confirmPassword,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(.75f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(context.getString(R.string.password)) },
            onValueChange = { viewModel.confirmPasswordChangeValue(it) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = blue,
                focusedLabelColor = blue
            )
        )
    }

}