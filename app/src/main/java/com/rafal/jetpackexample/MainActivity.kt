package com.rafal.jetpackexample

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafal.jetpackexample.ui.theme.JetpackExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Column {
                        Greeting()
                        SearchBar()

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Where do you\nwant to discover?",
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )

        Image(
            painter = painterResource(R.drawable.android_image),
            contentDescription = "profile pic",
            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)


        )

    }


}

@Composable
fun SearchBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(8.dp, 0.dp),
        // shadowElevation = AppBarDefaults.TopAppBarElevation,
        color = Color.White
    ) {

        OutlinedTextField(modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color(0xFFEBE8E8), shape = CircleShape),
            value = "",
            shape = CircleShape,
            onValueChange = {},
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search...",
                    color = Color.Black,

                    )

            },

            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize),
            singleLine = true,
            leadingIcon = {
                Modifier.alpha(ContentAlpha.medium)
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search"
                    )

                }
            },
            trailingIcon = {
                Modifier.alpha(ContentAlpha.medium)
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "settings"
                    )

                }
            }
        )

    }
}