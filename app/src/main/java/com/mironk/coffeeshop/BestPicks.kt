package com.mironk.coffeeshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.mironk.coffeeshop.ui.theme.BestPicksList
import com.mironk.coffeeshop.ui.theme.White
import com.mironk.coffeeshop.ui.theme.bestPicks

@Composable
fun BestPicks(
    items : List<BestPicksList>
){

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Best Picks", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Column{
            items.forEach { item ->
                BestPicksItems(picksItem = item)
            }
        }
    }

}

@Composable
fun BestPicksItems(
    picksItem: BestPicksList
) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(White)) {
            Row(modifier = Modifier
                .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image( painter = rememberImagePainter(
                    data = picksItem.image,
                    builder = { transformations(RoundedCornersTransformation()) }),
                    contentDescription = picksItem.name,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(20.dp)))
                
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(
                        text = picksItem.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    Text(
                        text = picksItem.size,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 5.dp)
                    )

                    Text(
                        text = picksItem.price,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }

        }
    }
}