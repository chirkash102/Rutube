package com.example.likescreen.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.auth.R
import com.example.likescreen.viewmodel.LikeViewModel
import com.example.uikit.LikeButton
import com.example.uikit.RutubeBottomBar
import com.example.uikit.RutubeTopBar
import com.example.uikit.VideoButton
import com.example.uikit.data.Item
import com.example.uikit.theme.RutubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLikes : Fragment() {
    private var callBack: LikeScreenNavigation? = null
    private val viewModel: LikeViewModel by viewModel()

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
            val videoState = viewModel.state.collectAsState()
            val loginTexst = viewModel.state1.collectAsState()
            RutubeTheme {
                LikeScreen(
                    viewModel = viewModel,
                    onNavigateTop = { callBack?.navigateToTopVideosFromLikeScreen() },
                    rutubeList = videoState.value,
                    topbar = loginTexst.value
                )
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
    viewModel: LikeViewModel,
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit = { },
    topbar: String,
    rutubeList: List<Item>
) {
    Scaffold(
        topBar = { RutubeTopBar(textLogin = topbar) },
        bottomBar = {
            RutubeBottomBar(
                onNavigateTop = { onNavigateTop.invoke() },
                onNavigateLIke = { onNavigateLIke.invoke() },
                isTopScreenPick = false
            )

        }) {
        LikeRecycler(
            logintext = topbar,
            viewModel = viewModel,
            onNavigateTop = { /*TODO*/ },
            onNavigateLIke = { /*TODO*/ },
            rutubeList = rutubeList
        )


//        NoLikes(
//            modifier = modifier
//                .padding(it)
//                .fillMaxSize()
//        )
    }
}


interface LikeScreenNavigation {
    fun navigateToTopVideosFromLikeScreen()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikeRecycler(
    logintext: String,
    viewModel: LikeViewModel,
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit,
    rutubeList: List<Item>
) {

    Scaffold(
        topBar = { RutubeTopBar(textLogin = logintext) },
        bottomBar = {
            RutubeBottomBar(
                onNavigateTop = { onNavigateTop.invoke() },
                { onNavigateLIke.invoke() }, isTopScreenPick = true
            )
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(rutubeList) {
                LikeItem(data = it, viewModel)

            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LikeItem(data: Item, viewModel: LikeViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = data.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                VideoButton(
                    modifier = Modifier.weight(1f),
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
                LikeButton(
                    modifier = Modifier.weight(1f),
                    isLiked = data.isLiked,
                    onClick = { viewModel.disLike(data.image, data.text) }
                )
            }
            if (expanded) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.text, fontFamily = FontFamily.Cursive, fontSize = 24.sp,
                )
            }

        }
    }
}