package com.beans.cmstest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.beans.cmstest.data.localdb.DatabaseModule
import com.beans.cmstest.data.network.NetworkModule
import com.beans.cmstest.data.repository.BeanRepository
import com.beans.cmstest.ui.AppNavigation
import com.beans.cmstest.ui.theme.CmstestTheme
import com.beans.cmstest.ui.viewmodel.BeanViewModelFactory
import com.beans.cmstest.ui.viewmodel.BeansViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CmstestTheme {
                Log.e("beans_data", "Start")
                val postDao = DatabaseModule.provideDatabase(applicationContext).beanDao()
                val apiService = NetworkModule.provideApiService()
                val repository = BeanRepository(postDao, apiService)
                val viewModel: BeansViewModel = viewModel(factory = BeanViewModelFactory(repository))
                AppNavigation(viewModel)
            }
        }
    }
}

