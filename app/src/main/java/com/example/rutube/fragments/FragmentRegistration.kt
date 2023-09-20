package com.example.rutube.fragments

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rutube.application.App
import com.example.rutube.transaction
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.viewmodels.RutubeViewModel

class FragmentRegistration : Fragment() {


    @SuppressLint("StateFlowValueCalledInComposition")
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
                        val state by viewModel.state.collectAsState()
                        var loginState by remember { mutableStateOf("") }
                        var passState by remember { mutableStateOf("") }
                        Column {
                            Greeting(state = loginState, ){loginState = it}
                            Greeting(state = passState, ){passState = it}
                            Button(onClick = {viewModel.login(loginState,passState )
                                transaction(FragmentRutubeVideo()) }) {
                                Text(text = "Login")


                            }
                            Button(onClick = {viewModel.regNewUser(loginState,passState)
                            }) {
                                Text(text = "Registration")

                            }
                            Button(onClick = {viewModel.delete(loginState,passState)}) {
                                Text(text = "Delete")

                            }

                            if (state != null) Toast.makeText(
                                LocalContext.current,
                                viewModel.state.value.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
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


