package com.example.rutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.rutube.fragments.FragmentHome
import com.example.rutube.ui.theme.RutubeTheme

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentHome())
            .addToBackStack(FragmentHome::class.java.name).commit()


    }
}

fun Fragment.transaction(fragment: Fragment) {
    parentFragmentManager.beginTransaction().replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}