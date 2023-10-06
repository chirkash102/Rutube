package com.example.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutubeTopBar(
    modifier: Modifier = Modifier,
    textLogin: String = stringResource(id = R.string.app_name)
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(0.dp),
                    painter = painterResource(R.drawable.baseline_delete_forever_24),
                    contentDescription = null
                )
                Text(
                    text = textLogin,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

@Composable
fun RutubeBottomBar(
    onNavigateTop: () -> Unit = {},
    onNavigateLIke: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.error
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { onNavigateTop.invoke() }, contentDescription = null,
                painter = painterResource(id = R.drawable.baseline_view_list_24)
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { onNavigateLIke.invoke() },
                painter = painterResource(id = R.drawable.heart),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { onNavigateProfile.invoke() },
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null
            )

        }
    }
}

@Composable
fun VideoButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun LikeButton(
    isLiked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = if (isLiked) Icons.Filled.CheckCircle else Icons.Filled.AddCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


