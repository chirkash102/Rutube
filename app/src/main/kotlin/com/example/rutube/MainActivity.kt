package com.example.rutube

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rutube.application.App
import com.example.rutube.fragments.FragmentHome
import com.example.rutube.fragments.FragmentLikes
import com.example.top20videos.fragments.RetrofitViewModel
import com.example.top20videos.fragments.RutubeVideoScreen
import com.example.top20videos.viewModel.RutubeRetrofitViewModel

class MainActivity : FragmentActivity(), RutubeVideoScreen,
    RetrofitViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentHome())
            .addToBackStack(FragmentHome::class.java.name).commit()
        createViewModel()
    }

    override fun onLikeClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentLikes())
            .addToBackStack(FragmentHome::class.java.name)
            .commit()
    }

    override fun createViewModel(): RutubeRetrofitViewModel {
            val app = application as App
            val viewModelFactory = app.viewModelFactory
           val viewModel by viewModels<RutubeRetrofitViewModel>{ viewModelFactory }
        return ViewModelProvider(this, viewModelFactory).get(RutubeRetrofitViewModel::class.java)
        }

}

fun Fragment.transaction(fragment: Fragment) {
    parentFragmentManager.beginTransaction().replace(R.id.container, fragment)
        .addToBackStack(fragment::javaClass.name).commit()
}
