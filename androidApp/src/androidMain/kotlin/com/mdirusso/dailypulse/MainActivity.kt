package com.mdirusso.dailypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.mdirusso.dailypulse.articles.ArticlesViewModel

class MainActivity : ComponentActivity() {
    val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Platform().logSystemInfo()
        setContent {
            AppScaffold(articlesViewModel = articlesViewModel)
        }
    }
}