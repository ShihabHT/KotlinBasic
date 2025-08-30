package com.example.jetpackcomposetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTutorialTheme {
                MainUiComponentHolder()
            }
        }
    }
}

data class Message(val author:String, val body: String)
@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Profile picture",
            modifier = Modifier.size(25.dp).clip(CircleShape).border(1.5.dp, MaterialTheme.colorScheme.primary)

        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
        Column(modifier = Modifier.clickable{ isExpanded = !isExpanded}) {
            Text(msg.author, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages){ message ->
            MessageCard(message)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUiComponentHolder() {
    // State to track if the dropdown menu is expanded
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(4.dp).height(55.dp),
                title = { Text("Home", style = MaterialTheme.typography.titleMedium)},
                actions = {
                    // Box to anchor the DropdownMenu
                    Box {
                        // IconButton for the 3-dot overflow menu
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "More"
                            )
                        }

                        // The DropdownMenu itself
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false } // Close when dismissed
                        ) {
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = {
                                    /* Handle settings click */
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Help") },
                                onClick = {
                                    /* Handle help click */
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Conversation(SampleData.conversationSample)
        }
    }
}

@Preview(name = "Light Mode", showBackground = false)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewScaffoldWithMenu() {
    JetpackComposeTutorialTheme {
        MainUiComponentHolder()
    }
}