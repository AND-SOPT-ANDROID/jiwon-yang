package org.sopt.and.ui.components.HomeScreen

import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeLazyRow(
    title: String,
    images: List<Int>,
    height: Int,
    width: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        LazyRow(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(start = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(images) { imageRes ->
                Image(
                    modifier = Modifier
                        .size(width.dp, height.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(id = imageRes),
                    contentDescription = "LazyRow Image",
                    contentScale = ContentScale.Crop
                )
            }
        }

    }

}
