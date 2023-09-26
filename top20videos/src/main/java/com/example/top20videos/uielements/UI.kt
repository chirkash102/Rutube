package com.example.top20videos.uielements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import com.example.rutube.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutubeTopBar(modifier: Modifier = Modifier) {
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
                    text = (stringResource(R.string.app_name)),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

@Composable
fun RutubeBottomBar(
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit,
    isTopScreenPick: Boolean
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


