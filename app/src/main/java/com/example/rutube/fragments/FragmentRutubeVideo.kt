package com.example.rutube.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rutube.R
import com.example.rutube.RutubeViewModel
import com.example.rutube.data.Item
import com.example.rutube.ui.theme.RutubeTheme

class FragmentRutubeVideo : Fragment() {
    private val viewModel by viewModels<RutubeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val videoState = viewModel.state.collectAsState()

                RutubeTheme {
                    Recycler(rutubeList = videoState.value)
                }
//                if (!videoState.value.isEmpty())
//
//                    Test(a = videoState.value[1].text)

            }
        }
    }
}

@Composable
fun Recycler(rutubeList: List<Item>) {
    LazyColumn() {
        items(rutubeList) {
            RutubeItem(data = it)
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RutubeItem(data: Item) {
    Card (modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Column {
            GlideImage(model = data.image, contentDescription = null  )
            Text(text = data.text)
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


