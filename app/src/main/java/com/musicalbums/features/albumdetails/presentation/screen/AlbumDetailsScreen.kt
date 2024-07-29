package com.musicalbums.features.albumdetails.presentation.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.musicalbums.R
import com.musicalbums.core.ui.component.MusicAlbumsAppBar
import com.musicalbums.features.topalbums.domain.entity.Album

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumDetailsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    item: Album,
    navigateToITunesStore: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) = with(sharedTransitionScope) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MusicAlbumsAppBar(
                title = item.name,
                arrangement = Arrangement.Start,
                navIcon = R.drawable.ic_arrow_back_24,
                onNavigationClick = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = item.artworkUrl100.replace("100x100", "300x300"),
                    contentDescription = "Album art work url",
                    error = painterResource(id = R.drawable.ic_album_placeholder),
                    placeholder = painterResource(id = R.drawable.ic_album_placeholder),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${item.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .aspectRatio(1f)
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(Color.Black)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.artistName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.genres.joinToString(", ") { it.name },
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.releaseDate,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.copyright,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(100.dp))
            }

            OutlinedButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { navigateToITunesStore(item.url) }
            ) {
                Text(
                    text = stringResource(R.string.lbl_go_to_itunes_store),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
