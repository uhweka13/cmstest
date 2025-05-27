package com.beans.cmstest.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beans.cmstest.ui.viewmodel.BeansViewModel
import androidx.compose.runtime.collectAsState
import com.beans.cmstest.data.localdb.BeansEntity
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun DetailScreen(beanId: Int, viewModel: BeansViewModel) {
    LaunchedEffect(beanId) {
        Log.d("DetailScreen", "Fetching bean for ID: $beanId")
        viewModel.getBeanById(beanId)
    }

    val bean by viewModel.beanById.collectAsState()

    Scaffold { innerPadding ->
        bean?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = it.flavorName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.flavorName, style = MaterialTheme.typography.headlineSmall)
                Text(text = it.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Groups: ${it.groupName.joinToString()}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Ingredients: ${it.ingredients.joinToString()}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Color: ${it.colorGroup}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Gluten Free: ${it.glutenFree}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Sugar Free: ${it.sugarFree}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Seasonal: ${it.seasonal}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Kosher: ${it.kosher}", style = MaterialTheme.typography.bodyMedium)
            }
        } ?: Text(
            text = "Bean not found",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}