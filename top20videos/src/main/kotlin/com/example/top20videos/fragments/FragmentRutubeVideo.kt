package com.example.top20videos.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.top20videos.datamodel.Item
import com.example.top20videos.viewModel.RutubeRetrofitViewModel
import com.example.uikit.RutubeBottomBar
import com.example.uikit.RutubeTopBar
import com.example.uikit.VideoButton
import com.example.uikit.theme.RutubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentRutubeVideo : Fragment() {
    private val viewModel: RutubeRetrofitViewModel by viewModel()
    private var callBack: RutubeVideoScreen? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = (requireActivity() as? RutubeVideoScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val loginTexst = viewModel.state1.collectAsState()
            val videoState = viewModel.state.collectAsState()
            RutubeTheme {
                Recycler(
                    logintext = loginTexst.value,
                    onNavigateLIke = { callBack?.onLikeClick() },
                    onNavigateTop = { },
                    rutubeList = videoState.value
                )
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}

interface RutubeVideoScreen {
    fun onLikeClick()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Recycler(
    logintext:String,
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
            VideoButton(expanded = expanded, onClick = { expanded = !expanded })
            if (expanded)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = data.text, fontFamily = FontFamily.Cursive, fontSize = 24.sp,
                )
        }
    }
}
