package com.example.rutube.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.rutube.transaction
import com.example.rutube.uielements.RutubeBottomBar

class FragmentLikes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                RutubeBottomBar(
                    onNavigateTop = { transaction(FragmentRutubeVideo()) },
                    { Unit },
                    false
                )

            }
        }
    }
}