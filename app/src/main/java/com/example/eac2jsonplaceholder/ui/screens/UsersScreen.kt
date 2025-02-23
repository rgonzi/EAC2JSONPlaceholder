package com.example.eac2jsonplaceholder.ui.screens

import DataState
import DataState.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.eac2jsonplaceholder.network.User
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.eac2jsonplaceholder.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UsersScreen(
    usersState: DataState<List<User>>,
    usersList: List<User>,
    retryGetData: () -> Unit,
    onUserClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (usersState) {
        is Success -> UsersSuccessScreen(usersList, onUserClick, modifier)
        Error -> ErrorScreen(retryGetData, modifier = modifier.fillMaxSize())
        Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        else -> {}
    }
}

@Composable
fun ErrorScreen(retryGetUsers: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = { retryGetUsers() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = ""
    )
}

@Composable
fun UsersSuccessScreen(
    usersList: List<User>, onUserClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Usuaris",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(usersList) { user ->
                UserItem(user = user, onUserClick = onUserClick)
                HorizontalDivider()
            }

        }
    }
}

@Composable
fun UserItem(user: User, onUserClick: (Int) -> Unit) {
    Text(
        text = user.name,
        modifier = Modifier
            .clickable { onUserClick(user.id) }
            .fillMaxWidth()
            .padding(16.dp)
    )
}


