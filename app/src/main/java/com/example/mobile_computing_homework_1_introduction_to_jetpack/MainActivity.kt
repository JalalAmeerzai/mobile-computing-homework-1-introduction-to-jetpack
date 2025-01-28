package com.example.mobile_computing_homework_1_introduction_to_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AngrySnowManApp()
        }
    }
}

@Composable
fun AngrySnowManApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "chat_screen") {
        composable("chat_screen") { AngrySnowMan(navController) }
        composable("profile_screen") { ProfileScreen(navController) }
    }
}

@Composable
fun AngrySnowMan(navController: NavHostController) {
    var messages by remember {
        mutableStateOf(
            listOf(
                "Hi there!",
                "Hello! How are you?",
                "I'm fine, thank you!",
                "What about you?",
                "I'm good, thanks for asking."
            )
        )
    }
    var clickCount by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages.size) { index ->
                ChatBubble(
                    message = messages[index],
                    isUser = index % 2 == 0,
                    navController = navController
                )
            }
        }

        Button(
            onClick = {
                clickCount++
                messages = when {
                    clickCount % 2 == 0 -> when (clickCount) {
                        2 -> messages + "K. Bye!!"
                        4 -> messages + "What??"
                        else -> messages + ":)"
                    }
                    else -> when (clickCount) {
                        3 -> messages + "NO!!!!"
                        6 -> messages + "DONT GO!!!!"
                        else -> messages + "NOOOOOOOOOOO!!!!!!"
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Make It Angrier")
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean, navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isUser) {
            Image(
                painter = painterResource(id = R.drawable.user1),
                contentDescription = "Confused Me",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate("profile_screen")},
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = message,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            fontSize = if (isUser) 16.sp else 14.sp,
            fontWeight = if (isUser) FontWeight.Bold else FontWeight.Normal,
            color = if (isUser) Color.Blue else Color.DarkGray
        )
        if (isUser) {
            Image(
                painter = painterResource(id = R.drawable.newuser2),
                contentDescription = "Angry Snowman",
                modifier = Modifier.size(40.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Profile Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Jalal Ghaffar",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.user1),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Full Name: Jalal Ghaffar", fontSize = 20.sp)
                    Text(text = "Email: jalal.ghaffar@student.oulu.fi", fontSize = 20.sp)
                    Text(text = "Student ID: 2315257", fontSize = 20.sp)
                }
            }
        }
    }
}


