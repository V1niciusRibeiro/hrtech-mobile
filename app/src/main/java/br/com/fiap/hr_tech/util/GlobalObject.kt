package br.com.fiap.hr_tech.util

import br.com.fiap.hr_tech.mvvm.model.User
import br.com.fiap.hr_tech.mvvm.view_model.AppMessagesViewModel

object GlobalObject {

    val message = AppMessagesViewModel()
    var user: User? = null

}
