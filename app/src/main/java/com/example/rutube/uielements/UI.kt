package com.example.rutube.uielements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rutube.R

class UI {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutubeTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
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
                    text = ("Rutube"),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }, modifier = modifier
    )
}

@Composable
fun RutubeBottomBar(
    onNavigateTop : () -> Unit,
    onNavigateLIke : () -> Unit,
    isTopScreenPick:Boolean
) {
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.error
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Image(painter = painterResource(id = R.drawable.baseline_view_list_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable{onNavigateTop.invoke()}
                ,contentDescription = null)
            Image(
                painter = painterResource(id = R.drawable.heart),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                .clickable{onNavigateLIke.invoke()},
                contentDescription = null
            )

        }
    }

}

