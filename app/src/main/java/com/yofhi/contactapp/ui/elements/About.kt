package com.yofhi.contactapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yofhi.contactapp.R
import com.yofhi.contactapp.ui.theme.purpleD0
import com.yofhi.contactapp.ui.theme.purpleD3

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun About(backPress: () -> Unit){
    val nama = "Yofhi Fauda Pradana"
    val alamatEmail = "yofhi132@gmail.com"
    val foto = R.drawable.avatar
    val univ = "Informatic Engineering Student at Muhammadiyah of University Ponorogo"

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = purpleD3),
                navigationIcon = {
                    IconButton(onClick = { backPress.invoke() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                        )
                    }
                },
                title = {
                    Text(text = "About", color = Color.White)
                },
            )
        },
        containerColor = purpleD3,
        content = {
            // Content of the page
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Image(
                        painter = painterResource(id = foto),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(purpleD0),
                        contentScale = ContentScale.FillWidth
                    )

                Spacer(modifier = Modifier.height(10.dp))

                // Nama
                Text(
                    text = nama,
                    style = MaterialTheme.typography.headlineMedium,
                    color = purpleD0
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Nama
                Text(
                    text = alamatEmail,
                    style = MaterialTheme.typography.bodyLarge,
                    color = purpleD0
                )

                //Univ
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = univ,
                    style = MaterialTheme.typography.bodyLarge,
                    color = purpleD0,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}