package com.yofhi.contactapp.ui.elements

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yofhi.contactapp.R
import com.yofhi.contactapp.ui.ContactViewModel
import com.yofhi.contactapp.ui.theme.StateTestComposeTheme
import com.yofhi.contactapp.ui.theme.purpleD0
import com.yofhi.contactapp.ui.theme.purpleD1
import com.yofhi.contactapp.ui.theme.purpleD3
import com.yofhi.contactapp.ui.theme.typography


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditContact(vm: ContactViewModel, backPress: () -> Unit){
    StateTestComposeTheme {

        val fullName by vm.fullName.observeAsState()
        val photo by vm.photo.observeAsState()
        val email by vm.email.observeAsState()
        val telepon by vm.telepon.observeAsState()
        val ctx = LocalContext.current

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
                        Text(text = "Edit Contact", color = Color.White)
                    },
                    actions = {
                        TextButton(
                            onClick = {
                                if (!fullName.isNullOrBlank()) {
                                    vm.updateContact()
                                    backPress.invoke()
                                } else ctx.makeShortToast("FullName cant be empty!")
                            }
                        ) {
                            Text(
                                text = "Save",
                                color = Color.White,
                                style = typography.headlineMedium,
                                fontSize = 20.sp,
                            )
                        }
                    }
                )
            },
            containerColor = purpleD3,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultMinSize(minHeight = 5000.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(purpleD0)
                    ) {
                        // Your photo here
                        Image(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Photo",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_camera_24),
                            contentDescription ="camera",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.BottomEnd)
                                .clickable {
                                    vm.updatePhoto(photo?:"")
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = fullName?: "",
                        onValueChange = { vm.updateFullName(it) },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        label = { Text(text = " Full Name ") },
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
                        ),
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = telepon ?: "",
                        onValueChange = { vm.updateTelepon(it) },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        label = { Text(text = "Telepon ") },
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
                        ),
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    OutlinedTextField(
                        value = email ?: "",
                        onValueChange = { vm.updateEmail(it) },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        label = { Text(text = "Email ") },
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
                        ),
                    )
                }
            }
        )
    }
}

fun Context.makeShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
