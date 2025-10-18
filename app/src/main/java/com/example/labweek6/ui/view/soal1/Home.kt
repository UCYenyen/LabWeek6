package com.example.labweek6.ui.view.soal1

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.labweek6.R
import com.example.labweek6.ui.route.soal1.AppViewSoal1
import com.example.labweek6.ui.view.soal1.components.SearchBar
import com.example.labweek6.ui.view.soal1.components.StyledTab
import com.example.labweek6.ui.viewmodel.soal1.Soal1ViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: Soal1ViewModel
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Restaurants", "Deals", "Track Order")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9D9D9))
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Taste the world\nat your Door \nStep!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Light,
                )
                Image(
                    painter = painterResource(R.drawable.pandamartlogo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .width(60.dp)
                        .aspectRatio(1f)
                )
            }
        }

        item {
            SearchBar(
                hint = "What are you craving?",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEachIndexed { index, title ->
                    StyledTab(
                        text = title,
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                CategoryCard(
                    title = "Food delivery",
                    subtitle = "Delivery from 00$",
                    icon = painterResource(R.drawable.pandaburger),
                    onClick = {
                        navController.navigate(AppViewSoal1.FoodDelivery.name)
                    },
                    modifier = Modifier.weight(1f)
                )
                CategoryCard(
                    title = "Pandamart",
                    subtitle = "New users $10 off",
                    icon = painterResource(R.drawable.pandacart),
                    onClick = {
                        navController.navigate(AppViewSoal1.Pandamart.name)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            // Wrap in a Column to explicitly control vertical spacing
            Column {
                Text(
                    text = "Restaurant Available Now!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Image(
                    painter = painterResource(R.drawable.pizza),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxWidth() // <-- This is still essential for correct layout
                        .aspectRatio(1f)
                     .offset(y = (-48).dp) // <-- Add this to pull the image up
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    subtitle: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Image(
                painter = icon,
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(160.dp)
                    .aspectRatio(1f)
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = Soal1ViewModel()
    )
}