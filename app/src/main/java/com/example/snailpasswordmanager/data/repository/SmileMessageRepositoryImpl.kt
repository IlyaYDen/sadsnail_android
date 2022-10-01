package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.domain.model.SmileMessageEntity
import com.example.snailpasswordmanager.domain.repository.SmileMessageRepository
import javax.inject.Inject

class SmileMessageRepositoryImpl @Inject constructor(
//private val dao: ChatsDao
) : SmileMessageRepository {

    private val UserList = mutableListOf<SmileMessageEntity>()

    override fun addSmile(smileMessageEntity: SmileMessageEntity) {
        if(!UserList.contains(smileMessageEntity))
            UserList.add(smileMessageEntity)
    }

    override fun getSmileList(): List<SmileMessageEntity> {
        return UserList
    }


}