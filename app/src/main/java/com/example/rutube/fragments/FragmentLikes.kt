package com.example.rutube.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rutube.data.Item
import com.example.rutube.transaction
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.uielements.RutubeBottomBar
import com.example.rutube.uielements.RutubeTopBar

class FragmentLikes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                RutubeTheme {
                    Test(onNavigateTop = { transaction(FragmentRutubeVideo()) })
                }

            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LikeItem(data: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            GlideImage(model = data.image, contentDescription = null)
            Text(text = data.text)
        }
    }
}

@Composable
fun NoLikes(modifier: Modifier = Modifier) {
    Box (contentAlignment = Alignment.Center, modifier = modifier){
        Text(fontSize = 32.sp, text = "Здесь пока Нет Видео.", modifier = Modifier)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(
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


            }){
        NoLikes(modifier = modifier
            .padding(it)
            .fillMaxSize())
    }
}
@Preview(showSystemUi = true)
@Composable
fun SimpleComposablePreview() {
Test(onNavigateTop = {Unit})

}