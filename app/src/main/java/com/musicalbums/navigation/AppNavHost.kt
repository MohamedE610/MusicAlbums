package com.musicalbums.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.musicalbums.features.albumdetails.presentation.screen.AlbumDetailsScreen
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.presentation.screen.TopAlbumsScreen
import com.musicalbums.features.topalbums.presentation.viewmodel.AlbumsState
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreen.TopAlbums.path,
    state: AlbumsState,
    onRetry: () -> Unit,
    getAlbum: (id: String) -> Flow<Album?>,
    navigateToITunesStore: (url: String) -> Unit
) {
    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable(AppScreen.TopAlbums.path) {
                TopAlbumsScreen(
                    state = state,
                    onItemClicked = { album: Album -> navController.navigate("album/${album.id}") },
                    onRetry = onRetry,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(AppScreen.AlbumDetails.path) { navBackStackEntry ->
                val albumId = navBackStackEntry.arguments?.getString("id")
                val album = getAlbum(albumId ?: "").collectAsStateWithLifecycle(initialValue = null)
                album.value?.let {
                    AlbumDetailsScreen(
                        navController = navController,
                        item = it,
                        navigateToITunesStore = navigateToITunesStore,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    )
                }
            }
        }
    }
}