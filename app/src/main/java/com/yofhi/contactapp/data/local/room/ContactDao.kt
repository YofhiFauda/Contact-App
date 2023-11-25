package com.yofhi.contactapp.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yofhi.contactapp.data.local.entity.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE fullName LIKE '%' || :fullName || '%' ")
    fun searchQuery(fullName: String): Flow<List<Contact>>

    @Query("DELETE FROM contact")
    suspend fun deleteAllContacts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contact: Contact)

    @Delete
    suspend fun deleteContacts(contact: Contact)

    @Query("DELETE FROM contact WHERE contact.id = :contactId")
    suspend fun deleteContactsById(contactId: Int)

    @Update
    suspend fun updateContacts(contact: Contact)

    @Query("SELECT * FROM contact WHERE id = :contactId")
    fun findContactById(contactId: Int): Flow<Contact>
}