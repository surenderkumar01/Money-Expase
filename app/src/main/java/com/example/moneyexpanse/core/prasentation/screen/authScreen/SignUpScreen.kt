package com.example.moneyexpanse.core.prasentation.screen.authScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moneyexpanse.R
import com.example.moneyexpanse.core.common.Route
import com.example.moneyexpanse.core.common.authState
import com.example.moneyexpanse.core.prasentation.viewModel.AuthViewModel
import com.example.moneyexpanse.ui.theme.bluecard
import com.example.moneyexpanse.ui.theme.darkBackground
import com.example.moneyexpanse.ui.theme.darkCard
import com.example.moneyexpanse.ui.theme.textDark

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.signUpState.value

    when (state) {
        is authState.Error ->
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()

        is authState.Success ->
            navController.navigate(Route.DashboardScreen) {
                popUpTo(Route.SignScreen) { inclusive = true }
            }

        is authState.Loading ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }

        is authState.Idle -> {}
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lo),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Manage Your Wealth",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Track expenses & income effortlessly",
                color = textDark,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 6.dp, bottom = 32.dp)
            )

            Text(
                "Sign Up",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            // NAME
            Text(
                "Full Name",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null, tint = textDark)
                },
                label = { Text("Your Name", color = textDark, fontSize = 16.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = darkCard,
                    unfocusedContainerColor = darkCard,
                    focusedIndicatorColor = bluecard,
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(Modifier.height(18.dp))

            // EMAIL
            Text(
                "Email Address",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = textDark)
                },
                label = { Text("example@gmail.com", color = textDark, fontSize = 16.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = darkCard,
                    unfocusedContainerColor = darkCard,
                    focusedIndicatorColor = bluecard,
                    focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(Modifier.height(18.dp))

            // PASSWORD
            Text(
                "Password",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = textDark)
                },
                label = { Text("Create password", color = textDark, fontSize = 16.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = darkCard,
                    unfocusedContainerColor = darkCard,
                    focusedIndicatorColor = bluecard,
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                }
            )

            Spacer(Modifier.height(32.dp))

            OutlinedButton(
                onClick = {
                    when {
                        name.isEmpty() -> Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show()
                        email.isEmpty() -> Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show()
                        password.isEmpty() -> Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show()
                        else -> viewModel.signUp(email, password, name)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = bluecard)
            ) {
                Text("Sign Up", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(Modifier.weight(1f), DividerDefaults.Thickness, color = textDark)
                Text("  Or continue with  ", color = textDark, fontSize = 16.sp)
                HorizontalDivider(Modifier.weight(1f), DividerDefaults.Thickness, color = textDark)
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f).height(55.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkCard)
                ) {
                    Text("Google", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(18.dp))

                OutlinedButton(
                    onClick = { navController.navigate(Route.LoginScreen) },
                    modifier = Modifier.weight(1f).height(55.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkCard)
                ) {
                    Text("Log In", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Logs() {
    SignUpScreen(rememberNavController(), hiltViewModel())
}
