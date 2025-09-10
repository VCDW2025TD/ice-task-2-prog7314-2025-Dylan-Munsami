package com.example.memestream

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.memestream.ui.theme.MemeStreamTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MemeStreamTheme {
                LoginScreenWithBiometrics(
                    activity = this,
                    onGoogleSignInClicked = {
                        // TODO: Launch your Google Sign-In flow here
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreenWithBiometrics(activity: ComponentActivity, onGoogleSignInClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome! Please log in")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onGoogleSignInClicked) {
                Text(text = "Login with Google")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (isBiometricAvailable(activity)) {
                Button(onClick = {
                    showBiometricPrompt(
                        activity as FragmentActivity,
                        onSuccess = {
                            activity.startActivity(Intent(activity, MainActivity::class.java))
                            activity.finish()
                        },
                        onFailure = { /* optional toast */ })
                }) {
                    Text(text = "Login with Biometrics")
                }
            }
        }
    }
}
