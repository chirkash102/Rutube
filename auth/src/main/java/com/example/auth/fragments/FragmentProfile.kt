package com.example.auth.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
        Column(modifier = modifier
            .padding(it)
            .fillMaxWidth(),) {
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