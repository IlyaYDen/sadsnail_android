package com.example.snailpasswordmanager.domain.usecase.chats

import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetChatList @Inject constructor(
    private val repository: ChatListRepository
    ) {

    operator fun invoke () : List<ChatEntity> {
        return repository.getChatList()
    }
}