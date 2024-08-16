package com.example.serierecomendator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serierecomendator.viewModel.SignUpViewModel

@Composable
fun SignUpView(){
    val displayName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        LazyColumn (modifier=Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "nombre aplicacion"
                )
                Spacer(modifier = Modifier.size(20.dp))

                OutlinedTextField(
                    value = displayName.value,
                    onValueChange = {displayName.value=it},
                    label = { Text(text = "Name")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = email.value,
                    onValueChange = {email.value=it},
                    label = { Text(text = "Email")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = {password.value=it},
                    label = { Text(text = "Password")},
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        SignUpViewModel().createUserWithEmailAndPassword(
                            displayName = displayName.value,
                            email = email.value,
                            password = password.value
                        )
                    }
                )
                {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}
