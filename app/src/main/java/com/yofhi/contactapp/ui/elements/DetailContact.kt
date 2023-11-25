package com.yofhi.contactapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yofhi.contactapp.R
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.ui.ContactViewModel
import com.yofhi.contactapp.ui.theme.purpleD0
import com.yofhi.contactapp.ui.theme.purpleD3

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContact(vm:ContactViewModel, editContact: (Contact) -> Unit,backPress: () -> Unit){

    val contact by vm.contact.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { backPress.invoke() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            )
                    }
                },
                title = {
                        Text(text = "Detail Contact",
                            color = Color.White)
                },
                actions = {
                    IconButton(onClick = { contact?.let { editContact(it) } }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "edit-contact",
                            tint = Color.White,
                        )
                    }
                    IconButton(onClick = {
                        if (contact != null)  {
                            vm.deleteContact()
                            backPress.invoke()
                        }else{
                            print("Gagal Hapus Pesan")
                        }
                       }
                    )
                    {
                        Icon(Icons.Default.Delete, contentDescription = "delete-contact", tint = Color.White)
                    }

                }
            )
        },
        containerColor = purpleD3,
        content = {
            // Content of the page
            if(contact != null){

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
//                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Photo with name
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(purpleD0)
                    ) {
                        // Your photo here
                        Image(
                            painter = painterResource(R.drawable.person),
                            contentDescription = "Photo",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Name in the bottom-left corner
                        Text(
                            text = contact?.fullName?.replaceFirstChar { it.uppercase() } ?: "",
                            color = Color.White,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .padding(24.dp)
                                .align(Alignment.BottomStart)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // Card with phone
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Your phone details here
                        Text(
                            text = "Phone: ${contact?.telepon ?: ""}",
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }

                    // Card with chat
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Your chat details here
                        Text(
                            text = "Email: ${contact?.email ?: ""}",
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            } else{
                Text("Contact not available")
            }
        }
    )
}