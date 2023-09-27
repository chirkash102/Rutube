package com.example.rutube

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.auth.fragments.FragmentRegistration
import com.example.auth.fragments.RoomViewModel
import com.example.auth.fragments.RutubeLIkeScreen
import com.example.auth.viewmodel.RutubeViewModel
import com.example.rutube.application.App
import com.example.rutube.fragments.FragmentHome
import com.example.rutube.fragments.FragmentLikes
import com.example.top20videos.fragments.FragmentRutubeVideo
import com.example.top20videos.fragments.RetrofitViewModel
import com.example.top20videos.fragments.RutubeVideoScreen
import com.example.top20videos.viewModel.RutubeRetrofitViewModel

class MainActivity : FragmentActivity(), RutubeVideoScreen, RutubeLIkeScreen, RetrofitViewModel,
    RoomViewModel {

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
            .addToBackStack(FragmentRutubeVideo::class.java.name)
            .commit()
    }

    override fun createViewModel(): RutubeRetrofitViewModel {
        val app = application as App
        val viewModelFactory = app.viewModelFactory
        return ViewModelProvider(this, viewModelFactory)
            .get(RutubeRetrofitViewModel::class.java)
    }

    override fun navigateToTopVideos() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentRutubeVideo())
            .addToBackStack(FragmentRegistration::class.java.name)
            .commit()
    }

    override fun createRoomViewModel(): RutubeViewModel {
        val app = application as App
        val viewModelFactory = app.viewModelFactory
        return ViewModelProvider(this, viewModelFactory)
            .get(RutubeViewModel::class.java)
    }

}

fun Fragment.transaction(fragment: Fragment) {
    parentFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}
