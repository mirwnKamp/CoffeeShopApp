package com.mironk.coffeeshop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mironk.coffeeshop.BestPicks
import com.mironk.coffeeshop.Categories
import com.mironk.coffeeshop.R
import com.mironk.coffeeshop.ui.theme.*
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun HomeScreen(navController: NavHostController) {

    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray),
        toolbarModifier = Modifier
            .background(BackgroundGray),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        state = state,
        toolbar = {
            Column {
                TopBarProfile()
            }
        },
        body = {
            LazyColumn(contentPadding = PaddingValues(bottom = 125.dp)) {
                item {
                    SearchBar()
                    Categories(items1 = categoriesList, navController = navController)
                    BestPicks(items = bestPicks)
                }
            }
        }
    )
}


@Composable
fun TopBarProfile() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding()
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = LightBrown,
                        shape = CircleShape
                    )
                    .align(Alignment.CenterVertically)
                    .size(40.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.my_photo),
                contentDescription = "Profile_photo",
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
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
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Athens, ")
                        }
                        append("Greece")
                    }
                )
            }

            Icon(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(23.dp),
                painter = painterResource(id = R.drawable.notification_bell),
                contentDescription = "notification",
                tint = LightBrown
            )
        }
    }
}

@Composable
fun SearchBar(hint: String = "") {

    val focusRequester = remember { FocusRequester() }
    var text by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(Black) }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp, top = 10.dp)
    ) {
        Text(
            text = "Good morning, Myron",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, CircleShape)
                .background(LightGray, CircleShape)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)

            ) {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 10.dp)
                )
                Box(contentAlignment = Alignment.CenterStart) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (it.isFocused) {
                                    text = hint
                                    color = Black
                                } else {
                                    text = "Search Coffee..."
                                    color = Color.Gray
                                }
                            },
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        maxLines = 1,
                        singleLine = true,
                        textStyle =
                        if (color == Black) {
                            TextStyle(color = Color.Black)
                        } else {
                            TextStyle(color = Color.Gray)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                // TODO
                            }
                        )
                    )
                }
            }
        }
    }
}


