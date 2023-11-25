package com.yofhi.contactapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yofhi.contactapp.data.local.entity.Contact

@Database(
    entities = [Contact::class],
    version = 2,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao
}