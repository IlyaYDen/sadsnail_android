package com.example.snailpasswordmanager.domain.usecase.chats

import android.util.Log
import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import javax.inject.Inject

class InsertChat @Inject constructor(
    private val repository: ChatListRepository
    ) {

    suspend operator fun invoke(chatEntity: ChatEntity){
        Log.d("testb","2")
        repository.insertChat(chatEntity)
    }
}
