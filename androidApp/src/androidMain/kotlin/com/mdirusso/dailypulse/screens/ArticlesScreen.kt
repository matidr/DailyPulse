package com.mdirusso.dailypulse.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mdirusso.dailypulse.articles.presentation.ArticleUiModel
import com.mdirusso.dailypulse.articles.presentation.ArticlesEffect
import com.mdirusso.dailypulse.articles.presentation.ArticlesIntent
import com.mdirusso.dailypulse.articles.presentation.ArticlesState
import com.mdirusso.dailypulse.articles.presentation.ArticlesViewModel

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel,
    onAboutButtonScreen: () -> Unit,
    onSourcesButtonScreen: () -> Unit
) {

    val articlesState = articlesViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        articlesViewModel.effect.collect { effect ->
            when (effect) {
                ArticlesEffect.NavigateToAbout -> onAboutButtonScreen()
                ArticlesEffect.NavigateToSources -> onSourcesButtonScreen()
            }
        }
    }

    Column {
        AppBar(
            onAboutClick = { articlesViewModel.dispatchIntent(ArticlesIntent.ShowAbout) },
            onSourcesClick = {
                articlesViewModel.dispatchIntent(
                    ArticlesIntent.ShowSources
                )
            })
        when (val state = articlesState.value) {
            is ArticlesState.Error -> ErrorMessage(state.errorMessage)
            is ArticlesState.Success -> ArticlesListView(state) {
                articlesViewModel.dispatchIntent(ArticlesIntent.RefreshArticles)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onAboutClick: () -> Unit, onSourcesClick: () -> Unit) {
    TopAppBar(title = { Text(text = "Articles") }, actions = {
        IconButton(onClick = onSourcesClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.List,
                contentDescription = "Sources Button"
            )
        }
        IconButton(onClick = onAboutClick) {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "About Device Button")
        }
    })
}

@Composable
fun ArticlesListView(articlesState: ArticlesState.Success, onRefresh: () -> Unit) {
    PullToRefreshBox(isRefreshing = articlesState.isRefreshing, onRefresh = onRefresh) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articlesState.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }
}

@Composable
fun ArticleItemView(article: ArticleUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(model = article.imageUrl, contentDescription = null)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(article.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date, style = TextStyle(color = Color.Gray), modifier = Modifier.align(
                Alignment.End
            )
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center))
    }
}