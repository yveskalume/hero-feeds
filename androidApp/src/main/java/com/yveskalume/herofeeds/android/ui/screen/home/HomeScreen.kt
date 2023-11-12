package com.yveskalume.herofeeds.android.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.ui.components.CreatorItem

@Composable
fun HomeRoute(
    onCreatorClick: () -> Unit,
    onAddClick: () -> Unit
) {
    HomeScreen(onCreatorClick = onCreatorClick, onAddClick = onAddClick)
}

@Composable
private fun HomeScreen(
    onCreatorClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { contentPadding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(16) {
                CreatorItem(onClick = onCreatorClick, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(onCreatorClick = {}, onAddClick = {})
    }
}