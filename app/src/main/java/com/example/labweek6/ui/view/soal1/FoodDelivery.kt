package com.example.labweek6.ui.view.soal1

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.NotificationAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labweek6.R
import com.example.labweek6.ui.view.soal1.components.FoodItemCard
import com.example.labweek6.ui.view.soal1.components.PromoCard
import com.example.labweek6.ui.view.soal1.components.SearchBar
import com.example.labweek6.ui.viewmodel.soal1.Soal1ViewModel

@Composable
fun FoodDelivery(
    viewModel: Soal1ViewModel
) {
    val foodItems by viewModel.foodItems.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD9D9D9))
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Find Your\nFavourite Food",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Light,
            )
            Image(
                painter = painterResource(R.drawable.pandaride),
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(1f)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            SearchBar(
                hint = "Search for Food",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.NotificationAdd,
                modifier = Modifier.size(32.dp),
                tint = Color(0xFFC461C9),
                contentDescription = "Search Icon"
            )
        }

        PromoCard(
            title = "Special Deal For December",
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Menu",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "View More",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(foodItems) { item ->
                FoodItemCard(item = item)
            }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FoodDeliveryPreview() {
    val dummyViewModel = Soal1ViewModel()
    FoodDelivery(
        viewModel = dummyViewModel
    )
}