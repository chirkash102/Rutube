package com.example.rutube.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rutube.data.Item
import com.example.rutube.transaction
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.uielements.RutubeBottomBar
import com.example.rutube.uielements.RutubeTopBar
import com.example.rutube.uielements.VideoButton
import com.example.rutube.viewmodels.RutubeRetrofitViewModel

class FragmentRutubeVideo : Fragment() {
    private val viewModel by viewModels<RutubeRetrofitViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val videoState = viewModel.state.collectAsState()

                RutubeTheme {
                    Recycler(
                        onNavigateLIke = { transaction(FragmentLikes()) },
                        onNavigateTop = { Unit },
                        rutubeList = videoState.value
                    )

                }

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recycler(
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit,
    rutubeList: List<Item>
) {
    Scaffold(
        topBar = { RutubeTopBar() },
        bottomBar = {
            RutubeBottomBar(onNavigateTop = { onNavigateTop.invoke() },
                { onNavigateLIke.invoke() }, isTopScreenPick = true
            )
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(rutubeList) {
                RutubeItem(data = it)
            }
        }
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RutubeItem(data: Item) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        )) {
            GlideImage(
                model = data.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            VideoButton(expanded = expanded, onClick = {expanded = !expanded} )
            if (expanded)
            Text(text = data.text, fontFamily = FontFamily.Cursive, fontSize = 24.sp, modifier = Modifier
                .fillMaxWidth())
        }
    }

}

@Composable
fun Test(a: String) {
    Text(text = a)
}
//@Preview(showSystemUi = true)
//@Composable
//fun SimpleComposablePreview() {
//    val rutube11List = listOf<Item>(
//        Item("https://pic.rutubelist.ru/video/e0/c8/e0c8363128c6da775c5e1bfc7539d764.jpg","fefe",true),
//        Item("https://pic.rutubelist.ru/video/e0/c8/e0c8363128c6da775c5e1bfc7539d764.jpg","fefe",true),
//        Item("https://pic.rutubelist.ru/video/e0/c8/e0c8363128c6da775c5e1bfc7539d764.jpg","fefe",true),
//    )
//    RutubeTheme {
//        Recycler(rutubeList = rutube11List )
//    }
//}


