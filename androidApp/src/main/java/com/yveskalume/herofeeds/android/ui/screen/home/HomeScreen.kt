package com.yveskalume.herofeeds.android.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    onCreatorClick: () -> Unit,
    onAddClick: () -> Unit
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    var isFabVisible by rememberSaveable { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isFabVisible = false
                }

                if (available.y > 1) {
                    isFabVisible = true
                }

                return Offset.Zero
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Creators") }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                FloatingActionButton(onClick = onAddClick, shape = MaterialTheme.shapes.medium) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) { contentPadding ->
        LazyVerticalStaggeredGrid(
            state = lazyStaggeredGridState,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .nestedScroll(nestedScrollConnection),
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