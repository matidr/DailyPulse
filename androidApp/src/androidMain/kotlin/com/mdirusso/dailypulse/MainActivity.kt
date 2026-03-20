package com.mdirusso.dailypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mdirusso.dailypulse.articles.ArticlesViewModel
import com.mdirusso.dailypulse.screens.AboutScreen
import com.mdirusso.dailypulse.screens.ArticlesScreen

class MainActivity : ComponentActivity() {
    val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Platform().logSystemInfo()
        setContent {
            ArticlesScreen(articlesViewModel = articlesViewModel)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AboutScreen()
}