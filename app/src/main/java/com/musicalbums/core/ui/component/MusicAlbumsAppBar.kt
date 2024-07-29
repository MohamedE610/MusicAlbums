package com.musicalbums.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicAlbumsAppBar(
    title: String,
    navIcon: Int? = null,
    onNavigationClick: () -> Unit = {},
    arrangement: Arrangement.Horizontal = Arrangement.Center
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = arrangement,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it),
                        contentDescription = "Navigation Icon"
                    )
                }
            }
        }
    )
}