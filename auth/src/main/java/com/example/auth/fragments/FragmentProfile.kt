package com.example.auth.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
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

        }

    }

    interface RutubeProfileNavigation {
        fun navigateToTopVideosFromRegistration()
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Text(modifier = Modifier, fontSize = 32.sp, text = stringResource(R.string.zero_like_video))
        Button(onClick = { /*TODO*/ }) {

        }
        Button(onClick = { /*TODO*/ }) {

        }
        Button(onClick = { /*TODO*/ }) {

        }
    }
}