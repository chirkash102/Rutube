package com.example.rutube.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.rutube.R
import com.example.rutube.transaction
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.uielements.RutubeBottomBar
import com.example.rutube.uielements.RutubeTopBar

class FragmentLikes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            RutubeTheme {
                LikeScreen(onNavigateTop = { transaction(FragmentRutubeVideo()) })
            }

        }
    }
}

@Composable
fun NoLikes(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Text(modifier = Modifier, fontSize = 32.sp, text = stringResource(R.string.zero_like_video))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeScreen(
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit = { Unit },
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { RutubeTopBar() },
        bottomBar = {
            RutubeBottomBar(
                onNavigateTop = { onNavigateTop.invoke() },
                onNavigateLIke = { onNavigateLIke.invoke() },
                isTopScreenPick = false
            )

        }) {
        NoLikes(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SimpleComposablePreview() {
    LikeScreen(onNavigateTop = { Unit })

}