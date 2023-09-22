package com.example.rutube.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import com.example.rutube.R
import com.example.rutube.transaction
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.ui.theme.Shapes
import com.example.rutube.uielements.RutubeTopBar

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                RutubeTheme {

                    MainScreen() {
                        // transaction(FragmentRutubeVideo())
                        transaction(FragmentRegistration())
                    }
                }

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClick: () -> Unit
) {
    Scaffold(
        topBar = { RutubeTopBar() }
    ){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.putin),
                    contentScale = ContentScale.Crop

                ),


            ) {
            Button(shape = Shapes.small, onClick = onClick) {
                Text(text = "START")
            }
        }
    }

}
