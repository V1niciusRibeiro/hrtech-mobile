package br.com.fiap.hr_tech.mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.hr_tech.util.Message
import br.com.fiap.hr_tech.util.TypeMessage

class AppMessagesViewModel {

    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>> = _messages

    fun addMessage(description: String, typeMessage: TypeMessage) {
        val list = mutableListOf(Message(description, typeMessage))
        _messages.value?.forEach {
            list.add(it)
        }
        _messages.value = list
    }

    fun removeMessage(message: Message) {
        val list = mutableListOf<Message>()
        _messages.value?.forEach {
            list.add(it)
        }
        list.remove(message)
        _messages.value = list
    }

}