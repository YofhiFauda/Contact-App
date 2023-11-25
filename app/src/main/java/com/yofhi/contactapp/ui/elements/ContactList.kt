package com.yofhi.contactapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.ui.MainViewModel
import com.yofhi.contactapp.ui.theme.StateTestComposeTheme
import com.yofhi.contactapp.ui.theme.purpleD0
import com.yofhi.contactapp.ui.theme.purpleD1
import com.yofhi.contactapp.ui.theme.purpleD3
import com.yofhi.contactapp.ui.theme.shapes
import com.yofhi.contactapp.ui.theme.typography


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ContactList(
    vm: MainViewModel,
    addContact: (Contact) -> Unit,
    detailContact: (Contact) -> Unit,
    about:() -> Unit
) {
    val contact by vm.contacts.observeAsState()
    val searchParam by vm.searchParam.observeAsState("")
    val isSearchBarVisible by vm.searchViewVisible.observeAsState(false)

    StateTestComposeTheme {
        Scaffold(
            topBar = {
                Crossfade(targetState = isSearchBarVisible, label = "") { isVisible ->
                    when (isVisible) {
                        true -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                                    .height(50.dp)
                            ) {
                                OutlinedTextField(
                                    placeholder = { Text(text = "Enter Search Query") },
                                    singleLine = true,
                                    textStyle = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier
                                        .fillMaxWidth(.9f)
                                        .padding(start = 8.dp),
                                    value = searchParam, onValueChange = {
                                        vm.updateSearchParam(it)
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedLabelColor = Color.White,
                                        focusedLabelColor = purpleD0,
                                        unfocusedBorderColor = purpleD1,
                                        focusedBorderColor = purpleD0,
                                        cursorColor = purpleD0,
                                        focusedPlaceholderColor = purpleD1,
                                        disabledPlaceholderColor = purpleD1,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White
                                    )
                                )
                                Icon(
                                    imageVector = Icons.Sharp.Clear,
                                    tint = Color.White,
                                    contentDescription = "clear-icon",
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentWidth(Alignment.End)
                                        .padding(end = 6.dp, top = 16.dp)
                                        .size(30.dp)
                                        .clickable {
                                            vm.searchViewVisibility(false)
                                            vm.updateSearchParam("")
                                        }
                                )
                            }

                        }
                        false -> {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(containerColor = purpleD3),
                                modifier = Modifier
                                    .padding(bottom = 20.dp),
                                title = {
                                    Text(text = "Contact",
                                        color = Color.White,
                                        )
                                },
                                actions = {
                                    IconButton(onClick = { about.invoke()  }) {
                                        Icon(
                                            imageVector =  Icons.Sharp.Person,
                                            contentDescription = "about-page",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(30.dp)
                                        )
                                    }
                                    IconButton(onClick = { vm.searchViewVisibility(true)  }) {
                                        Icon(
                                            imageVector =  Icons.Sharp.Search,
                                            contentDescription = "Add Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(30.dp)
                                        )
                                    }
                                }
                            )
                        }
                    }

                }
            },
            containerColor = purpleD3,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    //rectangel shape FAB
                    text = {
                        Text(
                            text = "New Contact",
                            style = typography.headlineMedium,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(bottom = 2.dp)
                        )
                    },
                    shape = shapes.medium,
                    onClick = { addContact(Contact(id = -1, fullName = "", photo = "", email = "", telepon = "")) },
                    icon = { Icon(
                        imageVector = Icons.Sharp.Add,
                        contentDescription = "add-contact",
                        tint = Color.White
                    ) },
                    containerColor = purpleD0
                )
            }
        ){
                LazyColumn(
                    contentPadding = PaddingValues(top = 80.dp)
                ){
                    itemsIndexed(
                        items = contact.orEmpty()
                            .filter { it.fullName?.contains(searchParam)!! }
                            .sortedBy { it.fullName?.lowercase() } //Ascending
                    ) { _, contact ->
                            ContactListItem(contact, detailContact)
                    }
                    if (contact.orEmpty().isEmpty()) {
                        item {
                            Text(text = "No contacts found",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                            )
                        }
                    }
                }
        }

    }
}
