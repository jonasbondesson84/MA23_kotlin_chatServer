package com.example.composechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composechat.ui.theme.ComposeChatTheme


const val MESSAGES = "messages"
const val SERVERS = "servers"
const val ACCOUNT = "account"

class MainActivity : ComponentActivity() {

    private var messages = mutableListOf<Message>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createMessages()

        setContent {
            ComposeChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    innerView()
                }
            }
        }
    }

    private fun createMessages() {
        messages.add(Message("Jonas", "hello there"))
        messages.add(Message("Elisabet", "well, hello!"))
        messages.add(Message("Jonas", "see you soon!"))
        messages.add(Message("Jonas", "or will I?!"))
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun innerView() {
        var innerView by remember { mutableStateOf(MESSAGES) }
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("test")
                }
                )
            },
            bottomBar = {
                BottomAppBar(
                ) {
                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        
                        horizontalArrangement = Arrangement.SpaceEvenly


                    ) {

                        Column(
                            Modifier.clickable {
                                innerView = MESSAGES

                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(Icons.Filled.Email, contentDescription = "Messages")
                            Text(
                                text = "Messages"
                            )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                        Column(
                            Modifier.clickable {
                                innerView = SERVERS
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = "Servers"
                            )
                            Text(
                                text = "Servers"
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column(
                            Modifier.clickable {
                                innerView = ACCOUNT
                            },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                Icons.Filled.AccountCircle,
                                contentDescription = "Account"
                            )
                            Text(
                                text = "Account"
                            )
                        }

                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {


                when (innerView) {
                    MESSAGES -> {
                        viewMessages()
                    }
                    SERVERS -> {
                        viewServers()
                    }
                    else -> {
                        viewAccount()
                    }
                }
            }
        }
    }

    @Composable
    fun viewMessages() {
        messageList()
    }

    @Composable
    fun viewServers() {
        Text("Servers")
    }

    @Composable
    fun viewAccount() {
        Text(text = "Account")
    }

    @Composable
    fun messageList() {
        LazyColumn() {
            items(messages) { message ->

                messageCard(message = message)
            }
        }
    }


    @Composable
    fun messageCard(message: Message, modifier: Modifier = Modifier) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
                .defaultMinSize(0.dp, 75.dp)


        ) {

            Text(
                text = message.author,
                modifier = modifier.padding(5.dp, 0.dp, 0.dp, 0.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = modifier.padding(5.dp))
            Text(
                text = message.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(15.dp, 0.dp, 0.dp, 10.dp)
            )

        }

    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeChatTheme {
//        messageCard(Message("Jonas", "was here"))
//    }
//}