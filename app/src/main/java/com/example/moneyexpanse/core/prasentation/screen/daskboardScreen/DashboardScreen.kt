package com.example.moneyexpanse.core.prasentation.screen.daskboardScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moneyexpanse.core.common.BottomNavItem
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.ui.theme.darkBackground
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DashboardScreen(navController1: NavHostController) {
    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { innerPadinng ->


        AnimatedNavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
//            modifier = Modifier.padding(innerPadinng)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(BottomNavItem.Expanse.route) {AddTransactionScreen(navController)  }
            composable(BottomNavItem.Profile.route) { ProfileScreen(navController1) }
            composable(BottomNavItem.Transaction.route) { TransactionsScreen(navController) }
            composable(Route.AddIncomeScreen) { AddIncomeScreen  (navController1) }
//
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val items = listOf(BottomNavItem.Home, BottomNavItem.Transaction, BottomNavItem.Expanse, BottomNavItem.Profile)

    NavigationBar(containerColor = darkBackground){
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach {item ->

            NavigationBarItem(
                selected = currentRoute==item.route,

                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState =true
//
                        }
                        restoreState=true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,        // Selected icon ka color
                    selectedTextColor = Color.White,        // Selected text color
                    unselectedIconColor = Color.Gray,       // Normal icon color
                    unselectedTextColor = Color.Gray,       // Normal text color

                )

            )

        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun d() {
//    DashboardScreen(rememberNavController())
//}

