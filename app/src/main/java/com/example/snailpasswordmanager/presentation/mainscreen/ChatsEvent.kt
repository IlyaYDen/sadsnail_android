package com.example.snailpasswordmanager.presentation.mainscreen

import com.example.snailpasswordmanager.domain.model.ChatEntity


sealed class ChatsEvent {
    data class DeletePassword(val chatEntity: ChatEntity) : ChatsEvent()
    data class AddChat(val chatEntity: ChatEntity) : ChatsEvent()
}