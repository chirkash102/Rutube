package com.example.auth.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.auth.R
import com.example.auth.roommodel.ViewEvents
import com.example.auth.viewmodel.RutubeViewModel
import com.example.uikit.RutubeTopBar
import com.example.uikit.theme.RutubeTheme
import com.example.uikit.utils.collectAsEvent

class FragmentRegistration : Fragment() {
    private var callBack: RutubeRegistrationNavigation? = null
    private var createViewModel: RoomViewModel? = null
    private lateinit var viewModel: RutubeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = (requireActivity() as? RutubeRegistrationNavigation)
        createViewModel = (requireActivity() as? RoomViewModel)
        viewModel = createViewModel!!.createRoomViewModel()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
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
                            is ViewEvents.SuccessAuth -> {
                                callBack?.navigateToTopVideosFromRegistration()
                            }

                            is ViewEvents.SuccessRegistration -> {
                                Toast.makeText(
                                    requireContext(),
                                    context.getString(R.string.account_were_signed_up),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is ViewEvents.SuccessDelete -> {
                                Toast.makeText(
                                    requireContext(),
                                    context.getString(R.string.account_were_deleted),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is ViewEvents.Error -> {
                                Toast.makeText(
                                    requireContext(),
                                    event.errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    var loginState by remember { mutableStateOf("") }
                    var passState by remember { mutableStateOf("") }

                    ColumnReg(
                        onNavigateLike = { callBack?.navigateToTopVideosFromRegistration() },
                        onNavigateTop = { },
                        isLoading = isLoading,
                        loginState = loginState,
                        onLoginStateChanges = { loginState = it },
                        onPassStateChanges = { passState = it },
                        passState = passState,
                        signIN = {
                            isLoading = true
                            viewModel.signIn(loginState, passState)
                        },
                        signUP = {
                            isLoading = true
                            viewModel.signUp(loginState, passState)
                        },
                        delete = {
                            isLoading = true
                            viewModel.delete(loginState, passState)
                        })
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }
}

interface RoomViewModel {
    fun createRoomViewModel(): RutubeViewModel
}

interface RutubeRegistrationNavigation {
    fun navigateToTopVideosFromRegistration()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ColumnReg(
    modifier: Modifier = Modifier,
    onNavigateTop: () -> Unit,
    onNavigateLike: () -> Unit,
    isLoading: Boolean,
    loginState: String,
    onLoginStateChanges: (String) -> Unit,
    onPassStateChanges: (String) -> Unit,
    passState: String,
    signIN: () -> Unit,
    signUP: () -> Unit,
    delete: () -> Unit
) {
    Scaffold(
        topBar = { RutubeTopBar() },
    ) { paddings ->
        Column(modifier = modifier.padding(paddings)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                com.example.uikit.SimpleCircularProgressIndicator(isLoading)
                Greeting(state = loginState, stringResource(R.string.Login), onLoginStateChanges)
                Greeting(state = passState, stringResource(R.string.Password), onPassStateChanges)
            }
            SqlButtons(text = stringResource(R.string.login), onClick = signIN)
            SqlButtons(text = stringResource(R.string.registration), onClick = signUP)
            SqlButtons(text = stringResource(R.string.delete), onClick = delete)
        }
    }
}

@Composable
fun SqlButtons(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    var onStatus by remember { mutableStateOf(false) }
    val transition =
        updateTransition(targetState = onStatus, label = stringResource(R.string.buttontransition))
    val xOffset by transition.animateDp(
        transitionSpec = {
            if (false isTransitioningTo true) {
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            } else {
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            }
        }, label = ""
    ) { isSelected ->
        if (isSelected) 250.dp else 0.dp // Adjust this value to control the horizontal offset
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = xOffset)
        ) {
            Button(
                modifier = modifier,
                onClick = {
                    onStatus = !onStatus
                    onClick.invoke()
                }
            ) {
                Text(text = text)
            }
        }
    }
}

@Composable
fun Greeting(state: String, hintText: String, onStateChanges: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state,
        onValueChange = onStateChanges,
        placeholder = { Text(text = hintText) }
    )
}
