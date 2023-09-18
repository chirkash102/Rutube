package com.example.rutube.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.rutube.transaction

class FragmentHome : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                MainScreen (){
                    transaction(FragmentRutubeVideo())
                }

            }
        }
    }
}

@Composable
fun MainScreen(
    onClick : () -> Unit
) {
    Box(){
        Button(onClick = onClick) {

        }
    }

}