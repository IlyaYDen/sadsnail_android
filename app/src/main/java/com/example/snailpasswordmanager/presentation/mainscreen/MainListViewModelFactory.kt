package com.example.snailpasswordmanager.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.chats.ChatUseCases
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class  MainListViewModelFactory @Inject constructor(

    var useCases: ChatUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainListViewModel(
            useCases = useCases
        ) as T

    }
}