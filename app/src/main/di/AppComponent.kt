package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainListActivity: MainListActivity)
}