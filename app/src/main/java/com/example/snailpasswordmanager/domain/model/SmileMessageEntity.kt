package com.example.snailpasswordmanager.domain.model


//TODO сделать DbModel для объекта

data class SmileMessageEntity(
    val emo:String,
    val receiver_id:String? = null,
    val sender_id:String? = null
)