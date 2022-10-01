package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.ChatEntity
import kotlinx.coroutines.flow.Flow

interface ChatListRepository {

    fun getChatList(): List<ChatEntity>

    suspend fun getChatById(id: Int): ChatEntity?

    suspend fun insertChat(chatEntity: ChatEntity)

}