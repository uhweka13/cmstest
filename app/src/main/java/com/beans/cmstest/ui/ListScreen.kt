package com.beans.cmstest.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.beans.cmstest.R
import com.beans.cmstest.ui.viewmodel.BeansViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: BeansViewModel) {
    val beans by viewModel.beans.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val screenWidth = getScreenWidth()
    val listState = rememberLazyListState()

    LaunchedEffect(isLoading) {
        Log.d("ListScreen", "Loading state: $isLoading")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Cms Test") },
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.background
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(beans, key = { bean -> bean.beanId }) { bean ->
                    Card(
                        modifier = Modifier
                            .size(width = screenWidth.dp, height = 100.dp)
                            .padding(bottom = 7.dp)
                            .clickable {
//                                Log.d("ListScreen", "Bean clicked: ID=${bean.beanId}")
                                viewModel.onBeanClicked(bean.beanId)
                            }
                    ) {
                        Row {
                            //                    Image(
                            //                        painter = rememberAsyncImagePainter(
                            //                            model = "${bean.imageUrl}",
                            ////                            placeholder = painterResource(R.drawable.ic_launcher_foreground),
                            //                            error = painterResource(R.drawable.ic_launcher_background)
                            //                        ),
                            //                        contentDescription = "Example Image",
                            //                        modifier = Modifier
                            //                            .width(100.dp) // Set desired width
                            //                            .height(100.dp) // Set desired height
                            //                    )
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(bean.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = bean.flavorName,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)

                            )
                            Column(
                                modifier = Modifier.height(100.dp),
                                verticalArrangement = Arrangement.Center,

                                ) {
                                Text("${bean.description}", maxLines = 2)
                                //                        Text("text 3")
                                //                        Text("text 4")
                            }
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen(innerPadding: PaddingValues) {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 12", "Item 13")
    val screenWidth = getScreenWidth()
    val listState = rememberLazyListState()


    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(itemsList, key = {item->item}) { item ->
            Card(
                modifier = Modifier
                    .size(width = screenWidth.dp, height = 100.dp)
                    .padding(bottom = 7.dp)
            ) {
                Row {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = "https://cdn-tp1.mozu.com/9046-m1/cms/files/ab692677-5471-4863-91a8-659363ae4cc4",
                            placeholder = painterResource(R.drawable.ic_launcher_foreground),
                            error = painterResource(R.drawable.ic_launcher_background)
                        ),
                        contentDescription = "Example Image",
                        modifier = Modifier
                            .width(100.dp) // Set desired width
                            .height(100.dp) // Set desired height
                    )
                    Column(
                        modifier = Modifier.height(100.dp),
                        verticalArrangement = Arrangement.Center,

                        ){
                        Text("The Refreshing And Crisp Flavor Of Lemon Lime Soda.${item}", maxLines = 2)
//                        Text("text 3")
//                        Text("text 4")
                    }
                }
            }
        }
    }

}

@Composable
fun getScreenWidth(): Float {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.toFloat()
}