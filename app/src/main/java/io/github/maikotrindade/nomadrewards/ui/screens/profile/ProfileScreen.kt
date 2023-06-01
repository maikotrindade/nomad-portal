package io.github.maikotrindade.nomadrewards.ui.screens.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, showMessage: (String) -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    val message by viewModel.showMessage.collectAsState()
    LaunchedEffect(message) {
        message?.let { showMessage(it) }
    }

    val users by viewModel.users.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        users?.forEach { user ->
            Column {
                Text(text = "Name: " + user.name, color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Email: " + user.email, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
