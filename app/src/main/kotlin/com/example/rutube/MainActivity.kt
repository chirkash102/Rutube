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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentHome())
            .addToBackStack(FragmentHome::class.java.name).commit()
    }

    override fun onLikeClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentLikes())
            .addToBackStack(FragmentLikes::class.java.name)
            .commit()
    }

    override fun navigateToTopVideosFromRegistration() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentRutubeVideo())
            .addToBackStack(FragmentRutubeVideo::class.java.name)
            .commit()
    }

    override fun navigateToTopVideosFromLikeScreen() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentRutubeVideo())
            .addToBackStack(FragmentRutubeVideo::class.java.name)
            .commit()
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
