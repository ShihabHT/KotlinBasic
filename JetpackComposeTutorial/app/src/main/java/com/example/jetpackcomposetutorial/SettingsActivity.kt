package com.example.jetpackcomposetutorial

import android.content.Context
import android.os.Bundle
import android.widget.Switch
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme
import kotlinx.coroutines.flow.Flow
import kotlin.math.round

// DataStore extension property
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTutorialTheme {
                val context = LocalContext.current
                val viewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(context.dataStore))
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Greeting( viewModel = viewModel)

                        Spacer(modifier = Modifier.height(5.dp))
                        NotificationEnabledSection(viewModel)

                        Spacer(modifier = Modifier.height(5.dp))
                        AcceptTermsSection(viewModel)

                        Spacer(modifier = Modifier.height(5.dp))
                        GenderSelectionListField(viewModel)

                        Spacer(modifier = Modifier.height(5.dp))
                        SliderAdvancedExample(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: SettingsViewModel) {
    val focusManager = LocalFocusManager.current // to remove focus from TextField
    val userName by viewModel.userNameFlow.collectAsState(initial = "...")
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = userName){
        text = userName
    }

    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text("-> $userName", modifier = Modifier.background(color = MaterialTheme.colorScheme.primary))
        TextField(
            value = text,
            onValueChange = { text = it },
//            label = { Text("Enter your name") },
            singleLine = true,
            modifier = Modifier.weight(1f) // Gives this element the most available space
        )
        Button(onClick = {
            viewModel.saveUserName(text)
//            text = ""
            focusManager.clearFocus()
        }) { Text(text = "Save") }
    }
}

@Composable
fun NotificationEnabledSection(viewModel: SettingsViewModel){
    val notificationEnabled by viewModel.notificationEnabledFlow.collectAsState(initial = true)

    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
        Text(text = "$notificationEnabled")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = notificationEnabled,
            onCheckedChange = {
                viewModel.saveNotificationEnabled(it)
            }
        )
    }
}

@Composable
fun AcceptTermsSection(viewModel: SettingsViewModel){
    val acceptTerms by viewModel.acceptTermsFlow.collectAsState(initial = true)
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
        Text(text = "$acceptTerms")
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = acceptTerms,
            onCheckedChange = {
                viewModel.saveAcceptTerms(it)
            }
        )
    }
}

@Composable
fun GenderSelectionListField(viewModel: SettingsViewModel) {
    val genderOptions = listOf("Undisclosed", "Male", "Female")
    var expanded by remember { mutableStateOf(false) }
    val selectedGender by viewModel.genderFlow.collectAsState(initial = "Undisclosed")

    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
        Text(text = "Select Gender")
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = selectedGender,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }
            ) {
                genderOptions.forEach { gender ->
                    DropdownMenuItem(
                        text = { Text(gender) },
                        onClick = {
                            viewModel.saveGender(gender)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SliderAdvancedExample(viewModel: SettingsViewModel) {
    val sliderPosition by viewModel.sliderValueFlow.collectAsState(initial = 0f)
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
        Text(text = sliderPosition.toString(), modifier = Modifier.padding(horizontal = 20.dp).width(50.dp))
        Slider(
            value = sliderPosition,
            onValueChange = { viewModel.saveSliderValue(round(it*10)/10f)},
            valueRange = 0f..50f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTutorialTheme {
        val viewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(LocalContext.current.dataStore))
        Column {
            Greeting(viewModel = viewModel)
            NotificationEnabledSection(viewModel)
            AcceptTermsSection(viewModel)
            GenderSelectionListField(viewModel)
            SliderAdvancedExample(viewModel)
        }
    }
}