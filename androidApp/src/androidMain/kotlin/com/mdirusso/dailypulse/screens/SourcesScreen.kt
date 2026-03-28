package com.mdirusso.dailypulse.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mdirusso.dailypulse.sources.presentation.SourceUiModel
import com.mdirusso.dailypulse.sources.presentation.SourcesIntent
import com.mdirusso.dailypulse.sources.presentation.SourcesState
import com.mdirusso.dailypulse.sources.presentation.SourcesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourcesScreen(viewModel: SourcesViewModel, onBackClicked: () -> Unit) {
    val sourcesState = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Sources") }, navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        })
        when (val state = sourcesState.value) {
            is SourcesState.Error -> ErrorMessage(state.errorMessage)
            is SourcesState.Success -> SourcesListView(state.sources, state.isRefreshing) {
                viewModel.dispatchIntent(SourcesIntent.RefreshSources)
            }
        }
    }
}

@Composable
fun SourcesListView(sources: List<SourceUiModel>, refreshing: Boolean, onRefresh: () -> Unit) {
    PullToRefreshBox(isRefreshing = refreshing, onRefresh = onRefresh) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sources) { source ->
                SourceItemView(source)
            }
        }
    }
}

@Composable
fun SourceItemView(source: SourceUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(source.description.orEmpty())
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.locale,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(
                Alignment.End
            )
        )
    }
}