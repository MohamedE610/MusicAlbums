package com.musicalbums.features.topalbums.presentation.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.musicalbums.R
import com.musicalbums.core.exception.getMessageShouldDisplay
import com.musicalbums.core.ui.component.ListScreenPadding
import com.musicalbums.core.ui.component.MusicAlbumsAppBar
import com.musicalbums.core.ui.component.SimpleErrorComponent
import com.musicalbums.core.ui.component.SimpleGridSkeleton
import com.musicalbums.features.topalbums.domain.entity.Album
import com.musicalbums.features.topalbums.presentation.viewmodel.AlbumsState


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopAlbumsScreen(
    modifier: Modifier = Modifier,
    state: AlbumsState,
    onItemClicked: (Album) -> Unit,
    onRetry: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MusicAlbumsAppBar(title = stringResource(id = R.string.app_name)) }
    ) { innerPadding ->
        when (state) {
            is AlbumsState.Loading -> SimpleGridSkeleton(modifier = modifier.padding(innerPadding))
            is AlbumsState.Success -> TopAlbumsContent(
                modifier = modifier.padding(innerPadding),
                albums = state.data,
                onItemClicked = onItemClicked,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )

            is AlbumsState.Error -> SimpleErrorComponent(
                modifier = modifier.padding(innerPadding),
                errorMsg = state.e.getMessageShouldDisplay(context),
                actionTitle = context.getString(R.string.lbl_retry),
                onActionClicked = onRetry
            )

            is AlbumsState.Initial -> Unit
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopAlbumsContent(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    onItemClicked: (Album) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    LazyVerticalGrid(
        contentPadding = ListScreenPadding,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(items = albums) { album ->
            AlbumCard(
                item = album,
                onItemClicked = onItemClicked,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumCard(
    item: Album,
    onItemClicked: (Album) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) = with(sharedTransitionScope) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
        modifier = Modifier
            .width(200.dp)
            .heightIn(250.dp)
            .clickable { onItemClicked(item) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = item.artworkUrl100.replace("100x100", "150x150"),
                contentDescription = "Album art work url",
                error = painterResource(id = R.drawable.ic_album_placeholder),
                placeholder = painterResource(id = R.drawable.ic_album_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image-${item.id}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = item.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = item.artistName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}
