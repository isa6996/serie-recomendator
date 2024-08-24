package com.example.serierecomendator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serierecomendator.R
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.LoginViewModel

@Composable
fun LoginView(navController: NavHostController) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column ( modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LazyColumn (modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            item {
                Text(text = "Login")
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
              //      label = { Text(stringResource(/*id = R.string.email*/)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                // Password field
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
            //        label = { Text(stringResource(id = R.string.password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )


                Button(onClick = {
                    LoginViewModel().loginUser(email.value, password.value)
                    navController.navigate(Screen.Home.route)

                }
                ) {
                    Text(text = "Login")
                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "sign in with google")
               //     Text(text = stringResource(id = R.string.signInWithGoogle))
                }


                // Sign up button
                TextButton(
                    onClick = {
                        navController.navigate(Screen.SignUp.route)
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "create account")
                //Text(stringResource(id = R.string.noAccount))
                }

                // Forgot password button
                TextButton(
                    onClick = {
                        navController.navigate(Screen.ForgottenPass.route)
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "forgot pass")
              //      Text(stringResource(id = R.string.forgetPassword))
                }


            }
        }
    }
}