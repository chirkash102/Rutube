package com.example.likescreen.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.auth.R
import com.example.uikit.RutubeBottomBar
import com.example.uikit.RutubeTopBar
import com.example.uikit.theme.RutubeTheme

class FragmentLikes : Fragment() {
    private var callBack: LikeScreenNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = (requireActivity() as? LikeScreenNavigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            RutubeTheme {
                LikeScreen(onNavigateTop = { callBack?.navigateToTopVideosFromLikeScreen() })
            }
        }
    }
    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}

@Composable
fun NoLikes(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Text(modifier = Modifier, fontSize = 32.sp, text = stringResource(R.string.zero_like_video))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikeScreen(
    modifier: Modifier = Modifier,
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit = { },
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
    LikeScreen(onNavigateTop = { })
}

interface LikeScreenNavigation {
    fun navigateToTopVideosFromLikeScreen()
}
