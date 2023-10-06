package com.example.rutube

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.auth.fragments.FragmentProfile
import com.example.auth.fragments.FragmentRegistration
import com.example.auth.fragments.RutubeRegistrationNavigation
import com.example.likescreen.fragments.FragmentLikes
import com.example.likescreen.fragments.LikeScreenNavigation
import com.example.rutube.fragments.FragmentHome
import com.example.top20videos.fragments.FragmentRutubeVideo
import com.example.top20videos.fragments.RutubeVideoScreen

class MainActivity : FragmentActivity(), RutubeVideoScreen, RutubeRegistrationNavigation,
    LikeScreenNavigation, FragmentProfile.RutubeProfileNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction(FragmentHome())
    }

    override fun navigateFromTop20ToLikeScreen() {
        transaction(FragmentLikes())

    }

    override fun navigateFromTop20ToProfile() {
        transaction(FragmentProfile())
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
        transaction(FragmentProfile())
    }

    override fun navigateToTopVideosFromLikeScreen() {
        transaction(FragmentRutubeVideo())
    }

    override fun navigateToProfileFromLikeScreen() {
        transaction(FragmentProfile())
    }

    override fun navigateToRegistrationFromLikeScreen() {
        transaction(FragmentRegistration())
    }

    override fun navigateToTopVideosFromProfile() {
        transaction(FragmentRutubeVideo())
    }

    override fun navigateToLikeVideosFromProfile() {
        transaction(FragmentLikes())
    }

    override fun navigateToRegistrationFromProfile() {
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
        .replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}
