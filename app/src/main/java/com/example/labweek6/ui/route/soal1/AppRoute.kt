package com.example.labweek6.ui.route.soal1

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.labweek6.ui.view.soal1.FoodDelivery
import com.example.labweek6.ui.view.soal1.HomeScreen
import com.example.labweek6.ui.view.soal1.PandaMart
import com.example.labweek6.ui.viewmodel.soal1.Soal1ViewModel

enum class AppViewSoal1(val title: String, val icon: ImageVector? = null) {
    Home("Home", Icons.Filled.Home),
    FoodDelivery("Food Delivery", Icons.Filled.Fastfood),
    Pandamart("Vegetables", Icons.Filled.ShoppingCart)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRouteSoal1() {
    val navController = rememberNavController()
    val viewModel: Soal1ViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val currentView = AppViewSoal1.entries.find { it.name == currentRoute }

    Scaffold(
        topBar = {
            if (currentView != AppViewSoal1.Home && currentView != AppViewSoal1.FoodDelivery) {
                MyTopAppBarSoal1(
                    currentView = currentView,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppViewSoal1.Home.name
        ) {
            composable(route = AppViewSoal1.Home.name) {
                HomeScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = AppViewSoal1.FoodDelivery.name) {
                FoodDelivery(viewModel = viewModel)
            }
            composable(route = AppViewSoal1.Pandamart.name) {
                PandaMart(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarSoal1(
    currentView: AppViewSoal1?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isPandamart = currentView == AppViewSoal1.Pandamart
    val containerColor = if (isPandamart) Color(0xFFB75C96) else Color(0xFFD9D9D9)

    TopAppBar(
        title = {
            if(isPandamart){
                Text(text = currentView.title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (isPandamart) {
                IconButton(onClick = { /* TODO: Navigasi ke keranjang */ }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = if (isPandamart) Color.White else MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = if (isPandamart) Color.White else MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = if (isPandamart) Color.White else MaterialTheme.colorScheme.onSurface
        )
    )
}