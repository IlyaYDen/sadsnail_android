package com.example.snailpasswordmanager.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.domain.usecase.chats.ChatUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//@HiltViewModel
class MainListViewModel constructor(
    private val useCases: ChatUseCases
    ) : ViewModel() {
    private var recentlyDeletedPassword: ChatEntity? = null

    fun getChats(): List<ChatEntity> {
        Log.d("test","getpass1")
        return useCases.getChatList()

    }
    fun onEvent(event: ChatsEvent) {

        when (event) {

            is ChatsEvent.AddChat -> {
                viewModelScope.launch {
                    Log.d("testb","1")

                    //val passwordEntity = event.passwordEntity

                    useCases.insertChat(event.chatEntity)
                }
            }
            is ChatsEvent.DeletePassword -> {

            }
        }
    }
}





//private fun getPassowrds(passwordOrder: PasswordOrder) {
    //    passwordUseCases.getPasswordList(passwordOrder)
    //        .onEach { passwords ->
    //
    //        }
    //}
//}