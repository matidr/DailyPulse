package com.mdirusso.dailypulse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdirusso.dailypulse.articles.presentation.ArticlesViewModel
import com.mdirusso.dailypulse.screens.AboutScreen
import com.mdirusso.dailypulse.screens.ArticlesScreen
import com.mdirusso.dailypulse.screens.Screens
import com.mdirusso.dailypulse.screens.SourcesScreen
import com.mdirusso.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Articles,
        modifier = modifier
    ) {
        composable<Screens.Articles> {
            val viewmodel: ArticlesViewModel = koinViewModel()
            ArticlesScreen(
                onAboutButtonScreen = { navController.navigate(Screens.About) },
                onSourcesButtonScreen = { navController.navigate(Screens.Sources) },
                articlesViewModel = viewmodel
            )
        }
        composable<Screens.About> {
            AboutScreen(onUpButtonClick = { navController.popBackStack() })
        }
        composable<Screens.Sources> {
            val viewModel: SourcesViewModel = koinViewModel()
            SourcesScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}
