package com.musicalbums.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleErrorComponent(
    modifier: Modifier = Modifier,
    errorMsg: String,
    actionTitle: String? = null,
    onActionClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMsg,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        actionTitle?.let {
            OutlinedButton(onClick = onActionClicked) {
                Text(text = it, fontSize = 14.sp)
            }
        }
    }
}
