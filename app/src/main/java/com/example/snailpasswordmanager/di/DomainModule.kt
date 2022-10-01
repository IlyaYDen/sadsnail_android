package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import com.example.snailpasswordmanager.domain.repository.SmileMessageRepository
import com.example.snailpasswordmanager.domain.usecase.chats.ChatUseCases
import com.example.snailpasswordmanager.domain.usecase.chats.GetChatList
import com.example.snailpasswordmanager.domain.usecase.chats.InsertChat
import com.example.snailpasswordmanager.domain.usecase.messages.MessagesUseCases
import dagger.Module
import dagger.Provides

@Module

class DomainModule {
    @Provides
    //@Singleton
    fun providerPasswordUseCases(repository: ChatListRepository) : ChatUseCases {
        return ChatUseCases(

            GetChatList(repository),
            InsertChat(repository),

            //getPasswordList = GetPasswordList(repository),
            //deletePassword = DeletePassword(repository),
            //insertPassword = InsertPassword(repository)
        )
    }

    @Provides
    //@Singleton
    fun providerMessageUseCases(repository: SmileMessageRepository) : MessagesUseCases {
        return MessagesUseCases(

            //getPasswordList = GetPasswordList(repository),
            //deletePassword = DeletePassword(repository),
            //insertPassword = InsertPassword(repository)
        )
    }
}
