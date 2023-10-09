package com.example.rutube

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.auth.fragments.FragmentRegistration
import com.example.auth.fragments.RutubeRegistrationNavigation
import com.example.likescreen.fragments.FragmentLikes
import com.example.likescreen.fragments.LikeScreenNavigation
import com.example.top20videos.fragments.FragmentRutubeVideo
import com.example.top20videos.fragments.RutubeVideoScreen

class MainActivity : FragmentActivity(), RutubeVideoScreen, RutubeRegistrationNavigation,
    LikeScreenNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction(FragmentRutubeVideo())
    }

    override fun navigateFromTop20ToLikeScreen() {
        transaction(FragmentLikes())

    }

    override fun navigateFromTop20ToProfile() {
        transaction(FragmentRegistration())
    }

    override fun navigateFromTop20ToRegistration() {
        transaction(FragmentRegistration())
    }

    override fun navigateToTopVideosFromRegistration() {
        transaction(FragmentRutubeVideo())

    }

    override fun navigateToLikeVideosFromRegistration() {
        transaction(FragmentLikes())
    }

    override fun navigateToProfileFromRegistration() {
        transaction(FragmentRegistration())
    }

    override fun navigateToTopVideosFromLikeScreen() {
        transaction(FragmentRutubeVideo())
    }

    override fun navigateToProfileFromLikeScreen() {
        transaction(FragmentRegistration())
    }

    override fun navigateToRegistrationFromLikeScreen() {
        transaction(FragmentRegistration())
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
        .add(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}
