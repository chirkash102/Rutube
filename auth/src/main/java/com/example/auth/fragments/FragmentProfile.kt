package com.example.auth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.auth.viewmodel.RutubeViewModel
import com.example.uikit.theme.RutubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile : Fragment() {
    private var callBack: RutubeProfileNavigation? = null
    private val viewModel: RutubeViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = (requireActivity() as? RutubeProfileNavigation)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            RutubeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var userProfile = viewModel.give

                }
            }


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