package br.com.fiap.hr_tech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.fiap.hr_tech.navigation.AppNavigation
import br.com.fiap.hr_tech.ui.theme.HR_TechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HR_TechTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(this.getColor(R.color.ice))
                ) {
                    createApp()
                }
            }
        }
    }
}

@Composable
fun createApp() {
    AppNavigation.starNavigation()
}
