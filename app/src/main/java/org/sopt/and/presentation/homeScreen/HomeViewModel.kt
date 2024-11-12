package org.sopt.and.presentation.homeScreen

import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import org.sopt.and.R

class HomeViewModel : ViewModel() {

    val mainPagerImages = listOf(
        R.drawable.food_pic1,
        R.drawable.food_pic2,
        R.drawable.food_pic3,
        R.drawable.food_pic4,
        R.drawable.food_pic5
    )

}