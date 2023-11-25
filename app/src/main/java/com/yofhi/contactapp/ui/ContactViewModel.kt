package com.yofhi.contactapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.data.local.room.ContactDao
import com.yofhi.contactapp.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactDao: ContactDao,
    private val repository: ContactRepository,
): ViewModel() {

    private val _fullName: MutableLiveData<String> = MutableLiveData("")
    val fullName: LiveData<String> = _fullName

    private val _photo: MutableLiveData<String> = MutableLiveData("")
    val photo: LiveData<String> = _photo

    private var _email: MutableLiveData<String> = MutableLiveData("")
    val email: LiveData<String> = _email

    private var _telepon: MutableLiveData<String> = MutableLiveData("")
    val telepon = _telepon

    private var _contactId: MutableLiveData<Int> = MutableLiveData(-1)
    val contactId = _contactId

    private val _contact = MutableStateFlow<Contact?>(null)
    val contact: StateFlow<Contact?> = _contact


    fun getContact(id: Int) {
        if (id != -1) {
            _contactId.value = id
            viewModelScope.launch {
                try {
                    repository.contactsWithId(id).collect { contact ->
                        contact.let {
                            _fullName.value = it.fullName ?: ""
                            _photo.value = it.photo ?: ""
                            _email.value = it.email ?: ""
                            _telepon.value = it.telepon ?: ""

                            _contact.value = it

                        }
                    }
                }catch (e: Exception) {
                    // Log the exception or handle it appropriately
                    e.printStackTrace()
                }
            }

        }
    }

    fun insertContact() {
        viewModelScope.launch {
            try {
                contactDao.insertContacts(
                    Contact(
                        fullName = _fullName.value,
                        photo = _photo.value,
                        email = _email.value,
                        telepon = _telepon.value
                    )
                )
                getContact(_contactId.value ?: -1)
            } catch (e: Exception) {
                // Log the exception or handle it appropriately
                e.printStackTrace()
            }
        }
    }

    fun updateContact() {
        viewModelScope.launch {
            try {
                contactDao.updateContacts(
                    Contact(
                        id = _contactId.value ?: -1,
                        fullName = _fullName.value,
                        photo = _photo.value,
                        email = _email.value,
                        telepon = _telepon.value
                    )
                )
                getContact(_contactId.value ?: -1)
            } catch (e: Exception) {
                // Log the exception or handle it appropriately
                e.printStackTrace()
            }
        }
    }
    

    fun deleteContact() {
        viewModelScope.launch {
            _contactId.value?.let { contactId ->
                contactDao.deleteContactsById(contactId)
            }
        }
    }



    fun updateFullName(fullName: String) {
        _fullName.value = fullName
    }

    fun updatePhoto(photo: String) {
        _photo.value = photo

    }fun updateEmail(email: String) {
        _email.value = email

    }fun updateTelepon(telepon: String) {
        _telepon.value = telepon
    }

}