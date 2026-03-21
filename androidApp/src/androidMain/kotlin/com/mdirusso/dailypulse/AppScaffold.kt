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
import com.mdirusso.dailypulse.articles.ArticlesViewModel
import com.mdirusso.dailypulse.screens.AboutScreen
import com.mdirusso.dailypulse.screens.ArticlesScreen
import com.mdirusso.dailypulse.screens.Screens

@Composable
fun AppScaffold(articlesViewModel: ArticlesViewModel) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            articlesViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    articlesViewModel: ArticlesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Articles,
        modifier = modifier
    ) {
        composable<Screens.Articles> {
            ArticlesScreen(
                onAboutButtonScreen = { navController.navigate(Screens.About) },
                articlesViewModel = articlesViewModel
            )
        }
        composable<Screens.About> {
            AboutScreen(onUpButtonClick = { navController.popBackStack() })
        }
    }
}
