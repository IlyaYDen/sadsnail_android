package com.example.snailpasswordmanager.presentation.smilechat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.chats.ChatUseCases
import com.example.snailpasswordmanager.domain.usecase.messages.MessagesUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import javax.inject.Inject

class SmileChatViewModel (
    private val useCases: MessagesUseCases
    ) : ViewModel() {

}




@Suppress("UNCHECKED_CAST")
class  SmileChatViewModelFactory @Inject constructor(

    var useCases: MessagesUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SmileChatViewModel(
            useCases
        ) as T

    }
}