package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.SmileMessageEntity


interface SmileMessageRepository {
    fun addSmile(smileMessageEntity: SmileMessageEntity)
    fun getSmileList():List<SmileMessageEntity>
}