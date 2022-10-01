package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatListRepositoryImpl @Inject constructor(
    //private val dao: ChatsDao
    ) : ChatListRepository {

    private val UserList = mutableListOf<ChatEntity>()

    override fun getChatList(): List<ChatEntity> {
        return UserList
    }

    override suspend fun getChatById(id: Int): ChatEntity? {
        return UserList.get(id)
    }

    override suspend fun insertChat(chatEntity: ChatEntity) {
        if(!UserList.contains(chatEntity))
            UserList.add(chatEntity)
    }


}