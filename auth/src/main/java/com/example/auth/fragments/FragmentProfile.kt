package com.example.auth.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.auth.viewmodel.RutubeViewModel
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
        setContent {}

    }
    interface RutubeProfileNavigation {
        fun navigateToTopVideosFromRegistration()
    }
    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}