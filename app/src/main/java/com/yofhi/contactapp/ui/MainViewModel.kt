package com.yofhi.contactapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.data.local.room.ContactDao
import com.yofhi.contactapp.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val contactDao: ContactDao,
    private val repository: ContactRepository
): ViewModel() {

    private val _fullName: MutableLiveData<String> = MutableLiveData("")
    val fullName: LiveData<String> = _fullName

    fun onFullNameChanged(newName: String) {
        _fullName.value = newName
    }

    private val _photo: MutableLiveData<String> = MutableLiveData("")
    val photo: LiveData<String> = _photo

    fun onPhotoChanged(newName: String) {
        _photo.value = newName
    }

    private var _email: MutableLiveData<String> = MutableLiveData("")
    val email: LiveData<String> = _email

    fun onEmailChanged(newName: String) {
        _email.value = newName
    }

    private var _telepon: MutableLiveData<Int> = MutableLiveData(0)
    val telepon = _telepon

    fun onTeleponChanged(newName: Int) {
        _telepon.value = newName
    }

    var _searchViewVisible = MutableLiveData(false)
    val searchViewVisible: LiveData<Boolean> = _searchViewVisible

    fun searchViewVisibility(isVisible: Boolean) {
        _searchViewVisible.value = isVisible
    }

    var _searchParam = MutableLiveData("")
    val searchParam: LiveData<String> = _searchParam


    fun updateSearchParam(newValue: String) {
        _searchParam.value = newValue
    }

    val contacts: LiveData<List<Contact>> = repository.getAllContacts().asLiveData()

    //move local db operation to repository layer
    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.insertContacts(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.updateContacts(contact)
        }
    }

    fun deleteAllContact() {
        viewModelScope.launch {
            contactDao.deleteAllContacts()
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactDao.deleteContacts(contact)
        }
    }

}