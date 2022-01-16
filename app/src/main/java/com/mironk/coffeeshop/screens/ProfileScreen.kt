package com.mironk.coffeeshop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mironk.coffeeshop.R
import com.mironk.coffeeshop.ui.theme.BackgroundGray
import com.mironk.coffeeshop.ui.theme.Brown
import com.mironk.coffeeshop.ui.theme.DeepGreen

@Composable
fun ProfileScreen() {
    Scaffold(backgroundColor = BackgroundGray) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(bottom = 30.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.my_photo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(CircleShape)
                        .size(200.dp)
                        .border(width = 4.dp, shape = CircleShape, color = Brown)
                )

            Box(
                modifier = Modifier.clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 30.dp,
                                    bottom = 20.dp,
                                    start = 20.dp,
                                    end = 20.dp
                                ),
                            text = "Myron \nKampourakis",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.location),
                                contentDescription = "location",
                                tint = DeepGreen,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(20.dp)
                            )

                            Text(
                                text = "Athens, Greece 15344",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clickable { }
                                    .fillMaxWidth(),
                                fontSize = 18.sp
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 20.dp
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "phone",
                                tint = DeepGreen,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(20.dp)
                            )

                            Text(
                                text = "+306949744700",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                fontSize = 18.sp
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 20.dp
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = "email",
                                tint = DeepGreen,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(20.dp)
                            )

                            Text(
                                text = "Kampourakis.murwn@gmail.com",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                fontSize = 18.sp
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 20.dp
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = "password change",
                                tint = DeepGreen,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(20.dp)
                            )

                            Text(
                                text = "Change Password",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                fontSize = 18.sp
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 20.dp
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "log out",
                                tint = DeepGreen,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .size(20.dp)
                            )

                            Text(
                                text = "Log Out",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                fontSize = 18.sp,
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}