package com.kovsheful.signsync.feature_signsync.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kovsheful.signsync.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Composable
internal fun RegistrationView(
    navigator: DestinationsNavigator
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    RegistrationView(
        onRegistration = {

        }
    )
}

@Preview
@Composable
fun PrevRegistrationView() {
    RegistrationView(
        onRegistration = {}
    )
}

@Composable
fun RegistrationView(
    onRegistration: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        backgroundColor = Background,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer()
                //Image()
                //Spacer()
                //Row() {
                //    Text()
                //}
                //Spacer()
                //ContactsTextField()
                //Spacer()
                //ContactsTextField()
                //Spacer()
                //ContactsTextField()
                //Spacer()
                //SubmitButton()
                //Spacer()
            }
        }
    }
}