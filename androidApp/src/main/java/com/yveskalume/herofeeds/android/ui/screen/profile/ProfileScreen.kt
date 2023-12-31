package com.yveskalume.herofeeds.android.ui.screen.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.ui.components.HashnodePostItem
import com.yveskalume.herofeeds.data.model.hashnode.HashNodeRemotePost
import com.yveskalume.herofeeds.ui.profile.ProfileUiState
import com.yveskalume.herofeeds.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = getViewModel(),
    creatorId: Long?,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCreator(id = creatorId)
    }
    ProfileScreen(uiState = uiState, onNavigateBack = onNavigateBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { /*TODO*/ }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isCreatorLoading) {
                CircularProgressIndicator()
            } else if (uiState.errorMessage != null) {
                Text(text = uiState.errorMessage!!, textAlign = TextAlign.Center)
            } else {
                ProfileContent(uiState = uiState)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProfileContent(
    uiState: ProfileUiState
) {


    val pagerState = rememberPagerState { 2 }

    val coroutineScope = rememberCoroutineScope()

    val nestedScrollViewState = rememberNestedScrollViewState()

    VerticalNestedScrollView(
        modifier = Modifier.fillMaxWidth(),
        state = nestedScrollViewState,
        header = {
            if (uiState.creator != null) {
                HeaderSection(creator = uiState.creator!!, modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        if (!uiState.arePostsLoading && uiState.errorMessage == null) {
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(
                        selected = pagerState.currentPage == 0,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                        text = { Text(text = "Feed") }
                    )


                    Tab(
                        selected = pagerState.currentPage == 1,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        text = { Text(text = "Sources") }
                    )
                }
                HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) {

                    when (pagerState.currentPage) {
                        0 -> {
                            if (uiState.posts.isEmpty()) {
                                Text(
                                    text = "No posts yet",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            LazyColumn(
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {

                                items(items = uiState.posts, key = { it.id }) { post ->
                                    if (post is HashNodeRemotePost) {
                                        HashnodePostItem(
                                            post = post,
                                            modifier = Modifier
                                                .animateItemPlacement()
                                                .fillMaxWidth()
                                                .padding(
                                                    horizontal = 16.dp,
                                                    vertical = 8.dp
                                                )
                                        )
                                    }
                                }
                            }
                        }

                        1 -> {

                        }
                    }
                }
            }
        } else if (uiState.arePostsLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(uiState = ProfileUiState(), onNavigateBack = {})
    }
}