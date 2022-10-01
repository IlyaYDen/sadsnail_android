package com.example.snailpasswordmanager.di

import android.app.Application
import com.example.snailpasswordmanager.data.repository.ChatListRepositoryImpl
import com.example.snailpasswordmanager.data.repository.SmileMessageRepositoryImpl
import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import com.example.snailpasswordmanager.domain.repository.SmileMessageRepository
import dagger.Module
import dagger.Provides

@Module

class DataModule {
    @Provides
    //@Singleton
    fun provideApplication(): Application {
        return Application()
    }

    @Provides
    //@Singleton
    fun provideChatListRepository(): ChatListRepository {
        return ChatListRepositoryImpl()
    }
    @Provides
    //@Singleton
    fun provideSmileMessageRepository(): SmileMessageRepository {
        return SmileMessageRepositoryImpl()
    }

}
