package com.example.rutube.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rutube.application.App
import com.example.rutube.roommodel.ViewEvents
import com.example.rutube.transaction
import com.example.rutube.ui.SimpleCircularProgressIndicator
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.utils.collectAsEvent
import com.example.rutube.viewmodels.RutubeViewModel

class FragmentRegistration : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val app = requireActivity().application as App
        val viewModelFactory = app.viewModelFactory
        val viewModel by viewModels<RutubeViewModel> { viewModelFactory }

        return ComposeView(requireContext()).apply {

            setContent {

                RutubeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        var isLoading by remember { mutableStateOf(false) }
                        viewModel.getEventsFlow().collectAsEvent { event ->
                            isLoading = false
                            when (event) {
                                is ViewEvents.SuccessAuth -> transaction(FragmentRutubeVideo())
                                is ViewEvents.Error -> {
                                    Toast.makeText(
                                        requireContext(),
                                        event.errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {}
                            }
                        }
                        var loginState by remember { mutableStateOf("") }
                        var passState by remember { mutableStateOf("") }

                        Column {
                            SimpleCircularProgressIndicator(isLoading)
                            Greeting(state = loginState) { loginState = it }
                            Greeting(state = passState) { passState = it }
                            Button(
                                onClick = {
                                    isLoading = true
                                    viewModel.signIn(loginState, passState)
                                }
                            ) {
                                Text(text = "Login")
                            }
                            Button(
                                onClick = {
                                    isLoading = true
                                    viewModel.signUP(loginState, passState)
                                }
                            ) {
                                Text(text = "Registration")
                            }
                            Button(
                                onClick = {
                                    isLoading = true
                                    viewModel.delete(loginState, passState)
                                }
                            ) {
                                Text(text = "Delete")
                            }
                        }

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(state: String, onStateChanges: (String) -> Unit) {
    TextField(value = state, onValueChange = onStateChanges)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(state: String, onClick: () -> Unit, onStateChanges: (String) -> Unit) {
    TextField(value = state, onValueChange = onStateChanges)
}


