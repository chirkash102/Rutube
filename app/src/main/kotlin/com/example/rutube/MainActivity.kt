package com.example.rutube

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.auth.fragments.RutubeRegistrationNavigation
import com.example.likescreen.fragments.FragmentLikes
import com.example.likescreen.fragments.LikeScreenNavigation
import com.example.rutube.fragments.FragmentHome
import com.example.top20videos.fragments.FragmentRutubeVideo
import com.example.top20videos.fragments.RutubeVideoScreen

class MainActivity : FragmentActivity(), RutubeVideoScreen, RutubeRegistrationNavigation,
    LikeScreenNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction(FragmentHome())
    }

    override fun onLikeClick() {
        transaction(FragmentLikes())

    }

    override fun navigateToTopVideosFromRegistration() {
        transaction(FragmentRutubeVideo())

    }

    override fun navigateToTopVideosFromLikeScreen() {
        transaction(FragmentRutubeVideo())
    }
}

fun Fragment.transaction(fragment: Fragment) {
    parentFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}

fun FragmentActivity.transaction(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}
