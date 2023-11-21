package br.com.fiap.hr_tech.mvvm.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.fiap.hr_tech.util.GlobalObject
import kotlinx.coroutines.delay

@Composable
fun AppMessages() {

    val messages by GlobalObject.message.messages.observeAsState(initial = null)

    if (messages != null) {
        Column(Modifier.zIndex(1500f)) {
            messages!!.forEach {
                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .background(
                            it.messageType.getBackgroundColor(),
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Text(
                            text = it.messageType.getDescription(),
                            color = it.messageType.getTextColor()
                        )
                        Text(
                            text = it.messageDescription,
                            color = it.messageType.getTextColor()
                        )
                    }
                    Text(
                        text = "X",
                        color = it.messageType.getTextColor(),
                        modifier = Modifier
                            .padding(20.dp, 15.dp)
                            .clickable { GlobalObject.message.removeMessage(it) }
                    )
                }
                LaunchedEffect(Unit) {
                    delay(2750)
                    GlobalObject.message.removeMessage(it)
                }
            }
        }
    }
}