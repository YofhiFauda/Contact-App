package com.yofhi.contactapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yofhi.contactapp.data.local.entity.Contact
import com.yofhi.contactapp.ui.theme.purpleD0
import com.yofhi.contactapp.ui.theme.typography

@SuppressLint("DiscouragedApi")
@Composable
fun ContactListItem(contact: Contact, detailContact: (Contact) -> Unit){


    Card(
        modifier = Modifier
            .padding(bottom = 15.dp, start = 15.dp)
            .fillMaxWidth()
//            .clip(RoundedCornerShape(12.dp))
            .clickable(true) {
                detailContact(contact)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = purpleD0, shape = CircleShape)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                // Displaying the first letter of fullName
                Text(
                    text = contact.fullName?.take(1)?.replaceFirstChar { it.uppercase() } ?: "",
                    style = typography.bodyLarge,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(.95f),
                    text = contact.fullName?.replaceFirstChar { it.uppercase() } ?: "",
                    style = typography.bodyLarge,
                    fontSize = 18.sp,
                    color = Color.White
                )

        }
    }
}