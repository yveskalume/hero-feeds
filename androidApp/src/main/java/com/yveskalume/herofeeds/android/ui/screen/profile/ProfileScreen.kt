package com.yveskalume.herofeeds.android.ui.screen.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.ui.components.HashnodePostItem
import com.yveskalume.herofeeds.android.ui.components.MediumPostItem
import com.yveskalume.herofeeds.android.ui.components.XPostItem
import kotlinx.coroutines.launch
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen() {

    val pagerState = rememberPagerState { 3 }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { /*TODO*/ }
            )
        }
    ) { contentPadding ->
        val nestedScrollViewState = rememberNestedScrollViewState()

        VerticalNestedScrollView(
            modifier = Modifier
                .padding(contentPadding),
            state = nestedScrollViewState,
            header = {
                HeaderSection(modifier = Modifier.padding(16.dp))
            }
        ) {
            Column {
                ScrollableTabRow(
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
                        text = { Text(text = "Twitter/ X") }
                    )


                    Tab(
                        selected = pagerState.currentPage == 1,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        text = { Text(text = "Hashnode") }
                    )


                    Tab(
                        selected = pagerState.currentPage == 2,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        },
                        text = { Text(text = "Medium") }
                    )
                }
                HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) {

                    when(pagerState.currentPage) {
                        0 -> {
                            LazyColumn(
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {

                                items(16) {
                                    XPostItem(modifier = Modifier
                                        .animateItemPlacement()
                                        .padding(horizontal = 16.dp))
                                }
                            }
                        }

                        1 -> {
                            LazyColumn(
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {
                                items(16) {
                                    HashnodePostItem(
                                        modifier = Modifier
                                            .animateItemPlacement()
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }

                        2 -> {
                            LazyColumn(
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {
                                items(16) {
                                    MediumPostItem(
                                        modifier = Modifier
                                            .animateItemPlacement()
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}