package com.fortunateworld.grokxxx.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fortunateworld.grokxxx.data.AppPreferences
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val prefs = AppPreferences(context)
    val scope = rememberCoroutineScope()

    var apiKey by remember { mutableStateOf("") }
    var unfiltered by remember { mutableStateOf(true) }
    var chatModel by remember { mutableStateOf("grok-4") }
    var imageModel by remember { mutableStateOf("grok-2-image-1212") }

    LaunchedEffect(Unit) {
        apiKey = prefs.apiKeyFlow.first()
        unfiltered = prefs.unfilteredFlow.first()
        chatModel = prefs.chatModelFlow.first()
        imageModel = prefs.imageModelFlow.first()
    }

    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Grok XXX Studio Settings", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = apiKey,
            onValueChange = { apiKey = it },
            label = { Text("xAI API Key") },
            placeholder = { Text("sk-... from console.x.ai") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { scope.launch { prefs.saveApiKey(apiKey) } }) { Text("Save Key") }

        Spacer(Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Unfiltered XXX Mode (21+ explicit only)")
            Spacer(Modifier.weight(1f))
            Switch(checked = unfiltered, onCheckedChange = { new ->
                scope.launch { prefs.saveUnfiltered(new) }
                unfiltered = new
            })
        }

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = chatModel,
            onValueChange = { chatModel = it },
            label = { Text("Chat Model") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { scope.launch { prefs.saveChatModel(chatModel) } }) { Text("Save") }

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = imageModel,
            onValueChange = { imageModel = it },
            label = { Text("Image Model") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { scope.launch { prefs.saveImageModel(imageModel) } }) { Text("Save") }
    }
}
