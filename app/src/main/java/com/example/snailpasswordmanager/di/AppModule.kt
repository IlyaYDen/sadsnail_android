package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import com.example.snailpasswordmanager.domain.usecase.chats.ChatUseCases
import com.example.snailpasswordmanager.domain.usecase.messages.MessagesUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import com.example.snailpasswordmanager.presentation.smilechat.SmileChatViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    lateinit var mApplication: Application

    @Provides
    fun provideContext() : Context {
        return context;
    }

    @Provides
    @Singleton
    fun provideMainListViewModel(useCases: ChatUseCases): MainListViewModel {
        return MainListViewModel(useCases)
    }
    @Provides
    @Singleton
    fun provideSmileChatViewModel(useCases: MessagesUseCases): SmileChatViewModel {
        return SmileChatViewModel(useCases)
    }

}

