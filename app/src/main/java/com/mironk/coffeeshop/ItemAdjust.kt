package com.mironk.coffeeshop

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mironk.coffeeshop.ui.theme.*
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HotDrinksToolBar(
    itemId: Int,
    activeHighlightColor: Color = DeepGreen,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Black,
    initialSelectedItemIndex: Int = 0,
    navController: NavHostController
) {
    val state = rememberCollapsingToolbarScaffoldState()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(initialSelectedItemIndex)
    }
    val tap = remember { mutableStateOf(false) }

    val hotCoffeeListItems =
        HotCoffeeList.first { CoffeeAndFoodList -> itemId == CoffeeAndFoodList.id }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        toolbarModifier = Modifier
            .background(MaterialTheme.colors.primary),
        state = state,
        toolbar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Box {
                Image(
                    painter = painterResource(id = hotCoffeeListItems.contentPhoto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .graphicsLayer {
                            alpha = state.toolbarState.progress
                        }
                )

                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { navController.navigate("home") }) {
                        Image(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }

                    var fav by remember { mutableStateOf(false) }

                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { fav = !fav }) {
                        Image(
                            painter = painterResource(
                                id = if (!fav)
                                    R.drawable.empty_heart
                                else
                                    R.drawable.filled_heart
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }
                }
            }

            Text(
                text = hotCoffeeListItems.contentName,
                modifier = Modifier
                    .road(
                        whenCollapsed = Center,
                        whenExpanded = Alignment.BottomStart
                    )
                    .padding(20.dp),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500
            )
        },
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        body = {
            Surface(modifier = Modifier.fillMaxSize(), color = BackgroundGray) {
                LazyColumn {
                    item {
                        if (itemId == 0) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    hotCoffees.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = hotCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        if (selectedItemIndex == 0) {
                                            Text(
                                                text = "1.50 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 1) {
                                            Text(
                                                text = "2.00 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 2) {
                                            Text(
                                                text = "3.20 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }

                                    }
                                }

                            }
                        }

                        if (itemId == 1) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    hotCoffees.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = hotCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        if (selectedItemIndex == 0) {
                                            Text(
                                                text = "2.00 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 1) {
                                            Text(
                                                text = "2.20 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 2) {
                                            Text(
                                                text = "3.40 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }

                                    }
                                }

                            }
                        }

                        if (itemId == 2) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    smoothies.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = hotCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "2.20 €",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    )

}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ColdDrinksToolBar(
    itemId: Int,
    activeHighlightColor: Color = DeepGreen,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Black,
    initialSelectedItemIndex: Int = 0,
    navController: NavHostController
) {
    val state = rememberCollapsingToolbarScaffoldState()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(initialSelectedItemIndex)
    }
    val tap = remember { mutableStateOf(false) }

    val coldCoffeeListItems =
        ColdCoffeeList.first { CoffeeAndFoodList -> itemId == CoffeeAndFoodList.id }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        toolbarModifier = Modifier.background(MaterialTheme.colors.primary),
        state = state,
        toolbar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Box {
                Image(
                    painter = painterResource(id = coldCoffeeListItems.contentPhoto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .graphicsLayer {
                            alpha = state.toolbarState.progress
                        }
                )

                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { navController.navigate("home") }) {
                        Image(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }

                    var fav by remember { mutableStateOf(false) }

                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { fav = !fav }) {
                        Image(
                            painter = painterResource(
                                id = if (!fav)
                                    R.drawable.empty_heart
                                else
                                    R.drawable.filled_heart
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }
                }

            }

            Text(
                text = coldCoffeeListItems.contentName,
                modifier = Modifier
                    .road(
                        whenCollapsed = Alignment.Center,
                        whenExpanded = Alignment.BottomStart
                    )
                    .padding(20.dp),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500
            )
        },
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        body = {
            Surface(modifier = Modifier.fillMaxSize(), color = BackgroundGray) {
                LazyColumn {
                    item {
                        if (itemId == 0) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    coldCoffees.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = coldCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        if (selectedItemIndex == 0) {
                                            Text(
                                                text = "2.00 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 1) {
                                            Text(
                                                text = "3.20 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                    }
                                }

                            }
                        }

                        if (itemId == 1) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    coldCoffees.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = coldCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        if (selectedItemIndex == 0) {
                                            Text(
                                                text = "2.10 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                        if (selectedItemIndex == 1) {
                                            Text(
                                                text = "3.30 €",
                                                color = White,
                                                fontSize = 23.sp,
                                            )
                                        }
                                    }
                                }

                            }
                        }

                        if (itemId == 2) {
                            Column {
                                Text(
                                    "Coffee Size",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                )
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp)
                                ) {
                                    smoothies.forEachIndexed { index, item ->
                                        ItemsSizeView(
                                            item = item,
                                            isSelected = index == selectedItemIndex,
                                            activeHighlightColor = activeHighlightColor,
                                            activeTextColor = activeTextColor,
                                            inactiveTextColor = inactiveTextColor
                                        ) {
                                            selectedItemIndex = index
                                        }
                                    }
                                }

                                Text(
                                    "About",
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 20.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 10.dp
                                        )
                                )

                                Text(
                                    text = coldCoffeeListItems.about,
                                    maxLines =
                                    if (tap.value) {
                                        Int.MAX_VALUE
                                    } else {
                                        3
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            tap.value = !tap.value
                                        }
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 20.dp,
                                            end = 20.dp,
                                            bottom = 20.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(CircleShape)
                                            .clickable { }
                                            .background(DeepGreen)
                                            .padding(
                                                start = 50.dp,
                                                end = 50.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Add to cart  ",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "|",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                        Text(
                                            text = "3.00 €",
                                            color = White,
                                            fontSize = 23.sp,
                                        )
                                    }
                                }

                            }
                        }
                    }
                }

            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SnackTimeToolBar(
    itemId: Int,
    navController: NavHostController
) {
    val state = rememberCollapsingToolbarScaffoldState()

    val snackTimeListItems =
        SnackTimeList.first { CoffeeAndFoodList -> itemId == CoffeeAndFoodList.id }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        toolbarModifier = Modifier.background(MaterialTheme.colors.primary),
        state = state,
        toolbar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Box {
                Image(
                    painter = painterResource(id = snackTimeListItems.contentPhoto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .graphicsLayer {
                            alpha = state.toolbarState.progress
                        }
                )

                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { navController.navigate("home") }) {
                        Image(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = "",
                            modifier = Modifier
                                .size(15.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }

                    var fav by remember { mutableStateOf(false) }

                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(White)
                        .size(30.dp)
                        .clickable { fav = !fav }) {
                        Image(
                            painter = painterResource(
                                id = if (!fav)
                                    R.drawable.empty_heart
                                else
                                    R.drawable.filled_heart
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .align(Center)
                        )
                    }
                }

            }

            Text(
                text = snackTimeListItems.contentName,
                modifier = Modifier
                    .road(
                        whenCollapsed = Alignment.Center,
                        whenExpanded = Alignment.BottomStart
                    )
                    .padding(20.dp),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500
            )
        },
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        body = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                LazyColumn() {
                    items(2) { index ->
                        Surface(color = Color.White) {
                            Text(
                                "Item ${index + 1}",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }

                    }
                }
            }
        }
    )
}

@Composable
fun ItemsSizeView(
    item: ContentSize,
    isSelected: Boolean = false,
    activeHighlightColor: Color = DeepGreen,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Black,
    onItemClick: () -> Unit
) {

    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onItemClick()
            }
            .background(
                if (isSelected)
                    activeHighlightColor
                else
                    White
            )
            .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Text(
            text = item.size,
            color = if (isSelected)
                activeTextColor
            else
                inactiveTextColor
        )
    }
}
