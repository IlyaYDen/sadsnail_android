package com.example.snailpasswordmanager.domain.model

//TODO сделать DbModel для объекта
//TODO сделать мапер для объекта
data class ChatEntity(
    val id:Int? = null,
    val service:String,
    val login: String,
    val password: String,
    val timestamp: Long
)
