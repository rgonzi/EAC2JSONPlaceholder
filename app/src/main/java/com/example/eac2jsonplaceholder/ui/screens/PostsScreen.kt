package com.example.eac2jsonplaceholder.ui.screens

import DataState
import DataState.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eac2jsonplaceholder.R
import com.example.eac2jsonplaceholder.network.Post

@Composable
fun PostsScreen(
    postState: DataState<List<Post>>,
    postList: List<Post>,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (postState) {
        is Success -> PostsSuccessScreen(postList, navigateUp, modifier)
        Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        else -> {}
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun PostsSuccessScreen(postsList: List<Post>, navigateUp: () -> Unit, modifier: Modifier = Modifier) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Posts",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(postsList) { post ->
                    PostItem(post = post)
                }
            }
        }
        Button(
            onClick = { navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {Text(text = "Back")}
    }
}

@Composable
fun PostItem(post: Post) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true
            )
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = post.title,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body
            )
        }
    }
}