package br.com.fiap.hr_tech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.fiap.hr_tech.mvvm.view.component.calendar.CalendarGrid
import br.com.fiap.hr_tech.mvvm.view.component.calendar.WeekdayList
import br.com.fiap.hr_tech.mvvm.view_model.CalendarGridViewModel
import br.com.fiap.hr_tech.ui.theme.HR_TechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HR_TechTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column{
                        WeekdayList()
                        CalendarGrid(CalendarGridViewModel(11,2023))
                    }
                }
            }
        }
    }
}



