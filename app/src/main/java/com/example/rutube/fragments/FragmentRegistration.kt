package com.example.rutube.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rutube.application.App
import com.example.rutube.roommodel.ViewEvents
import com.example.rutube.transaction
import com.example.rutube.ui.SimpleCircularProgressIndicator
import com.example.rutube.ui.theme.RutubeTheme
import com.example.rutube.ui.theme.Shapes
import com.example.rutube.uielements.RutubeTopBar
import com.example.rutube.utils.collectAsEvent
import com.example.rutube.viewmodels.RutubeViewModel

class FragmentRegistration : Fragment() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                                is ViewEvents.SuccessRegistration -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Ты зареган , быдло",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                is ViewEvents.SuccessDelete -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Мы удалили твой аккаунт, быдло",
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

                        RegistrationScreen(
                            onNavigateLIke = { transaction(FragmentLikes()) },
                            onNavigateTop = { Unit },
                            isLoading = isLoading,
                            loginState = loginState,
                            onLoginStateChanges = { loginState = it },
                            onPassStateChanges = { passState = it },
                            passState = passState,
                            onClickLogin = {
                                isLoading = true
                                viewModel.signIn(loginState, passState)
                            },
                            onClickReg = {
                                isLoading = true
                                viewModel.signUP(loginState, passState)
                            },
                            onClickDel = {
                                isLoading = true
                                viewModel.delete(loginState, passState)
                            })

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onNavigateTop: () -> Unit,
    onNavigateLIke: () -> Unit,
    isLoading: Boolean,
    loginState: String,
    onLoginStateChanges: (String) -> Unit,
    onPassStateChanges: (String) -> Unit,
    passState: String,
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickReg: () -> Unit,
    onClickDel: () -> Unit
) {

    Scaffold(
        topBar = { RutubeTopBar() },
    ) { paddings ->
        ColumnReg(
            modifier = modifier.padding(paddings),
            isLoading = isLoading,
            loginState = loginState,
            onLoginStateChanges = onLoginStateChanges,
            onPassStateChanges = onPassStateChanges,
            passState = passState,
            onClickLogin = onClickLogin,
            onClickReg = onClickReg,
            onClickDel = onClickDel
        )
    }
}


@Composable
fun ColumnReg(
    isLoading: Boolean,
    loginState: String,
    onLoginStateChanges: (String) -> Unit,
    onPassStateChanges: (String) -> Unit,
    passState: String,
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickReg: () -> Unit,
    onClickDel: () -> Unit
) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SimpleCircularProgressIndicator(isLoading)
            Greeting(state = loginState, "Логин", onLoginStateChanges)
            Greeting(state = passState, "Пароль", onPassStateChanges)
        }
        RegistrationButton(text = "Login", onClick = onClickLogin)
        RegistrationButton(text = "Registration", onClick = onClickReg)
        RegistrationButton(text = "Delete", onClick = onClickDel)


    }

}

@Composable
fun HorizontalButtonSwitching() {
    var selectedIndex by remember { mutableStateOf(0) }
    val buttonLabels = listOf("Button 1", "Button 2", "Button 3")

    val transition = updateTransition(selectedIndex)
    val xOffset by transition.animateDp { selectedIndex ->
        when (selectedIndex) {
            0 -> 0.dp
            1 -> 100.dp // Adjust this value to control the horizontal offset
            2 -> 200.dp // Adjust this value to control the horizontal offset
            else -> 0.dp
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        buttonLabels.forEachIndexed { index, label ->
            Button(
                onClick = { selectedIndex = index },
                modifier = Modifier.offset(x = xOffset)
            ) {
                Text(text = label)
            }
        }
    }
}



@Composable
fun RegistrationButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    var onStatus by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = onStatus, label = "ButtonTransition")

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(state: String, hintText: String, onStateChanges: (String) -> Unit) {
    TextField(
        value = state,
        shape = Shapes.extraLarge,
        onValueChange = onStateChanges,
        placeholder = { Text(text = hintText) },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(state: String, onClick: () -> Unit, onStateChanges: (String) -> Unit) {
    TextField(value = state, onValueChange = onStateChanges)
}


