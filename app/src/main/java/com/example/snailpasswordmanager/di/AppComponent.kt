package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.login.LoginActivity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.smilechat.SmileChatActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(mainListActivity: MainListActivity)
    fun inject(smileChatActivity: SmileChatActivity)
}