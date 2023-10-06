package com.example.auth.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.auth.R
import com.example.auth.viewmodel.RutubeViewModel
import com.example.uikit.RutubeBottomBar
import com.example.uikit.RutubeTopBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile : Fragment() {
    private var callBack: RutubeProfileNavigation? = null
    private val viewModel: RutubeViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = (requireActivity() as? RutubeProfileNavigation)
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            var userProfile = viewModel.stateLogin.value

            Profile(onNavigateTop = { callBack?.navigateToTopVideosFromProfile() },
                onNavigateLike = { callBack?.navigateToLikeVideosFromProfile() }) {

            }
        }

    }

    interface RutubeProfileNavigation {
        fun navigateToTopVideosFromProfile()
        fun navigateToLikeVideosFromProfile()
        fun navigateToRegistrationFromProfile()
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    onNavigateTop: () -> Unit,
    onNavigateLike: () -> Unit,
    onNavigateProfile: () -> Unit,


    ) {
    Scaffold(
        topBar = { RutubeTopBar() },
        bottomBar = {
            RutubeBottomBar(
                onNavigateTop = { onNavigateTop.invoke() },
                onNavigateLIke = { onNavigateLike.invoke() },
                onNavigateProfile = {},
            )
        }) {
        Box(contentAlignment = Alignment.Center, modifier = modifier) {
            Text(
                modifier = Modifier,
                fontSize = 32.sp,
                text = stringResource(R.string.zero_like_video)
            )
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }

}