package com.beans.cmstest.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beans.cmstest.ui.viewmodel.BeansViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AppNavigation(viewModel: BeansViewModel) {
    val navController = rememberNavController()
    val navigateToDetail by viewModel.navigateToDetail.collectAsState()

    LaunchedEffect(navigateToDetail) {
        navigateToDetail?.let { postId ->
            navController.navigate("detail/$postId")
            viewModel.onNavigatedToDetail()
        }
    }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ListScreen(viewModel) }
        composable("detail/{beanId}") { backStackEntry ->
            val beanId = backStackEntry.arguments?.getString("beanId")?.toIntOrNull() ?: 0
            DetailScreen(beanId = beanId, viewModel = viewModel)
        }
    }
}