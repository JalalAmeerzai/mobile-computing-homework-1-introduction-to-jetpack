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



    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                AngrySnowMan()
            }
        }
    }

    @Composable
    fun AngrySnowMan() {
        var messages by remember { mutableStateOf(listOf(
            "Hi there!",
            "Hello! How are you?",
            "I'm fine, thank you!",
            "What about you?",
            "I'm good, thanks for asking."
        )) }
        var clickCount by remember { mutableIntStateOf(1)}
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages.size) { index ->
                    ChatBubble(
                        message = messages[index],
                        isUser = index % 2 == 0
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

    // help taken from a friend to design the structure of the below composable, help needed to figure out left right aligments for chat bubbles
    @Composable
    fun ChatBubble(message: String, isUser: Boolean) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isUser) {
                Image(
                    painter = painterResource(id = R.drawable.user1),
                    contentDescription = "Confused Me",
                    modifier = Modifier.size(40.dp).clip(CircleShape)
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
                    painter = painterResource(id = R.drawable.user2),
                    contentDescription = "Angry Snowman",
                    modifier = Modifier.size(40.dp).clip(CircleShape)
                )
            }
        }
    }








