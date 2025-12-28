package com.example.moneyexpanse.core.prasentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.core.prasentation.screen.authScreen.LoginScreen
import com.example.moneyexpanse.core.prasentation.screen.authScreen.SignUpScreen
import com.example.moneyexpanse.core.prasentation.screen.daskboardScreen.DashboardScreen

@Composable
fun NavigationBar() {
 val navController=rememberNavController()
 NavHost(navController=navController, startDestination = Route.LoginScreen){
  composable(Route.LoginScreen) { LoginScreen(navController) }
  composable(Route.SignScreen) { SignUpScreen(navController) }
  composable(Route.DashboardScreen) { DashboardScreen  (navController) }
 }
}