package com.yofhi.contactapp.data.di

import android.content.Context
import androidx.room.Room
import com.yofhi.contactapp.data.local.room.ContactDao
import com.yofhi.contactapp.data.local.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {

    @Singleton
    @Provides
    fun provideDao(contactDatabase: ContactDatabase): ContactDao {
       return contactDatabase.getContactDao()
    }
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contact-db"
        ).fallbackToDestructiveMigration().build()
    }

}