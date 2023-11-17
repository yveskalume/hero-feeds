package com.yveskalume.herofeeds.android.ui.screen.addcreator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.ui.addcreator.AddCreatorUiState
import com.yveskalume.herofeeds.ui.addcreator.AddCreatorViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AddCreatorRoute(
    viewModel: AddCreatorViewModel = getViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    AddCreatorScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onSubmit = viewModel::addCreator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCreatorScreen(
    uiState: AddCreatorUiState,
    onNavigateBack: () -> Unit,
    onSubmit: (name: String, bio: String, hashnode: String, medium: String) -> Unit
) {

    LaunchedEffect(uiState) {
        if (uiState is AddCreatorUiState.Success) {
            onNavigateBack()
        }
    }


    var name by remember {
        mutableStateOf("")
    }

    var bio by remember {
        mutableStateOf("")
    }

    var hashnode by remember {
        mutableStateOf("")
    }

    var medium by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text(text = "Add Creator") }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Name")
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Yves Kalume")
                }
            )

            Text(text = "Bio")
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Android Developer")
                }
            )

            Text(text = "Hashnode")
            OutlinedTextField(
                value = hashnode,
                onValueChange = { hashnode = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "kalume.hashnode.dev")
                }
            )

            Text(text = "Medium")
            OutlinedTextField(
                value = medium,
                onValueChange = { medium = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "medium.com/@yveskalume")
                }
            )

            Button(
                enabled = name.isNotBlank()
                        && bio.isNotBlank()
                        && (hashnode.isNotBlank() || medium.isNotBlank())
                        && uiState !is AddCreatorUiState.Loading,
                onClick = {
                    onSubmit(name, bio, hashnode, medium)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save")
                AnimatedVisibility(visible = uiState is AddCreatorUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddCreatorScreenPreview() {
    MaterialTheme {
        AddCreatorScreen(
            uiState = AddCreatorUiState.Idle,
            onNavigateBack = {},
            onSubmit = { _, _, _, _ -> })
    }
}