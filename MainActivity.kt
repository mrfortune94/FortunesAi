package com.fortunateworld.grokxxx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fortunateworld.grokxxx.ui.ChatScreen
import com.fortunateworld.grokxxx.ui.HistoryScreen
import com.fortunateworld.grokxxx.ui.ImageGenScreen
import com.fortunateworld.grokxxx.ui.SettingsScreen
import com.fortunateworld.grokxxx.ui.VideoGenScreen
import com.fortunateworld.grokxxx.ui.theme.GrokXXXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrokXXXTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScaffold()
                }
            }
        }
    }
}

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                val tabs = listOf("Chat", "Images", "Video", "History", "Settings")
                tabs.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index; navController.navigate(label.lowercase()) },
                        icon = {
                            Icon(
                                when (label) {
                                    "Chat" -> Icons.Default.ChatBubble
                                    "Images" -> Icons.Default.Home
                                    "Video" -> Icons.Default.PlayArrow
                                    "History" -> Icons.Default.Favorite
                                    else -> Icons.Default.Settings
                                }, label
                            )
                        },
                        label = { Text(label) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* quick generate */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, "Generate")
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = "chat", modifier = Modifier.padding(padding)) {
            composable("chat") { ChatScreen() }
            composable("images") { ImageGenScreen() }
            composable("video") { VideoGenScreen() }
            composable("history") { HistoryScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}
