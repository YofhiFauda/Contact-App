package com.yofhi.contactapp.data.repository

import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.data.local.room.ContactDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
){
    fun searchQuery(query: String): Flow<List<Contact>> = contactDao.searchQuery(query)
        .flowOn(Dispatchers.Main)
        .conflate()

    fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()
        .flowOn(Dispatchers.Main)
        .conflate()

    fun contactsWithId(id: Int): Flow<Contact>{
        return contactDao.findContactById(id)
    }
}