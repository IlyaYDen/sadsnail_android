package com.example.snailpasswordmanager.di

import android.app.Application
import com.example.snailpasswordmanager.data.repository.ChatListRepositoryImpl
import com.example.snailpasswordmanager.domain.repository.ChatListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

}