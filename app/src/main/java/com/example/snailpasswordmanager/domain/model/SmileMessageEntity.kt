package com.example.snailpasswordmanager.domain.model


//TODO сделать DbModel для объекта

data class SmileMessageEntity(
    val id:Int? = null,
    val emo:String,
    val receiver_id:Int? = null,
    val sender_id:Int? = null,
    val time:Long? = null
)