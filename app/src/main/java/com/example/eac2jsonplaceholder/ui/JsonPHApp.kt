
package com.example.eac2jsonplaceholder.ui

import JsonPHViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.eac2jsonplaceholder.ui.screens.PostsScreen
import com.example.eac2jsonplaceholder.ui.screens.UsersScreen

enum class JsonPHScreen() {
    Users,
    Posts
}

@Composable
fun EAC2JsonPlaceHolderApp(
    navController: NavHostController = rememberNavController()
) {
    val jsonPHViewModelViewModel: JsonPHViewModel = viewModel(factory = JsonPHViewModel.Factory)
    val usersList by jsonPHViewModelViewModel.usersList.collectAsState()
    val postList by jsonPHViewModelViewModel.postList.collectAsState()
    val usersState by jsonPHViewModelViewModel.usersState.collectAsState()
    val postsState by jsonPHViewModelViewModel.postsState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = JsonPHScreen.Users.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = JsonPHScreen.Users.name) {
                UsersScreen(
                    usersState = usersState,
                    retryGetData = { jsonPHViewModelViewModel.getUsers() },
                    usersList = usersList,
                    onUserClick = {userId ->
                        jsonPHViewModelViewModel.getPostsByUserId(userId)
                        navController.navigate(JsonPHScreen.Posts.name)
                    }
                )
            }
            composable(route = JsonPHScreen.Posts.name) {
                PostsScreen(postState = postsState, postList = postList, navigateUp = {navController.popBackStack()})
            }
        }

    }
}