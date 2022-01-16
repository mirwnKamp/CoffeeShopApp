package com.mironk.coffeeshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.mironk.coffeeshop.ui.theme.*

@Composable
fun Categories(
    items1: List<CategoriesList>,
    navController: NavHostController,
    activeHighlightColor: Color = DeepGreen,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Black,
    initialSelectedItemIndex: Int = 0
) {

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(initialSelectedItemIndex)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(text = "Categories", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            items1.forEachIndexed { index, item ->
                CategoriesItems(
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
    }

    if (selectedItemIndex == 0) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            items(HotCoffeeList) { model ->
                CategoriesListItems(listItem = model) {
                    navController.navigate("categories_hot_drinks/${model.id}")
                }
            }
        }
    }

    if (selectedItemIndex == 1) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            items(ColdCoffeeList) { model ->
                CategoriesListItems(listItem = model) {
                    navController.navigate("categories_cold_drinks/${model.id}")
                }

            }
        }
    }

    if (selectedItemIndex == 2) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            items(SnackTimeList) { model ->
                CategoriesListItems(listItem = model) {
                    navController.navigate("categories_snack_time/${model.id}")
                }
            }
        }
    }
}


@Composable
fun CategoriesItems(
    item: CategoriesList,
    isSelected: Boolean = false,
    activeHighlightColor: Color = DeepGreen,
    activeTextColor: Color = White,
    inactiveTextColor: Color = Black,
    onItemClick: () -> Unit
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.coffee,
                tint = if (isSelected)
                    activeTextColor
                else
                    inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = item.coffee,
                color = if (isSelected)
                    activeTextColor
                else
                    inactiveTextColor
            )
        }
}

@Composable
fun CategoriesListItems(
    listItem: CoffeeAndFoodList,
    fabClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(White)
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = listItem.contentPhoto,
                        builder = { transformations(RoundedCornersTransformation()) }),
                    contentDescription = listItem.contentName,
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Text(
                    text = listItem.contentName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 3.dp)
                )

                Text(
                    text = listItem.contentSize,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 3.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {

                    Box(modifier = Modifier.padding(top = 14.dp, start = 3.dp)) {
                        Text(
                            text = listItem.price,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }


                    FloatingActionButton(
                        onClick = { fabClick.invoke() },
                        backgroundColor = DeepGreen,
                        contentColor = White,
                        modifier = Modifier
                            .padding(start = 67.dp)
                            .size(38.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }

            }
        }
    }
}