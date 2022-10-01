package com.example.snailpasswordmanager.domain.usecase.chats

import javax.inject.Inject

data class ChatUseCases @Inject constructor(
    val getChatList: GetChatList,
    val insertChat: InsertChat
)