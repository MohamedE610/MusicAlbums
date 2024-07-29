package com.musicalbums.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.musicalbums.R
import com.musicalbums.core.ui.theme.MusicAlbumsTheme
import com.musicalbums.features.topalbums.presentation.viewmodel.AlbumsViewModel
import com.musicalbums.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            MusicAlbumsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onRetry = viewModel::loadTopAlbums,
                        getAlbum = viewModel::getAlbum,
                        navigateToITunesStore = ::navigateToITunesStore
                    )
                }
            }
        }
    }

    private fun navigateToITunesStore(albumUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(albumUrl)
        }

        if (intent.resolveActivity(packageManager) != null)
            startActivity(intent)
        else
            Toast.makeText(
                this,
                getString(R.string.lbl_can_not_perform_this_action), Toast.LENGTH_SHORT
            ).show()
    }
}