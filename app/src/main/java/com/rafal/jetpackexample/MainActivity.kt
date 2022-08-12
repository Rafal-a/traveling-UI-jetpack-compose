package com.rafal.jetpackexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.rafal.jetpackexample.ui.Feature
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
                        Categories(listOf("🏔️ Mountain", "🏕️ Camp", "🌲 Forest"))
                        DataList(
                            listOf(
                                Feature(
                                    R.drawable.mountain_sunset,
                                    "USA,Los angeles",
                                    4.5,
                                ),
                                Feature(
                                    R.drawable.view,
                                    "USA,Arizona",
                                    4.3,
                                ),
                                Feature(
                                    R.drawable.mountain_sunset,
                                    "USA,Texas",
                                    4.0,
                                ),
                            )
                        )
                        BottomNavigationBar()

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

@Composable
fun Categories(categories: List<String>) {

    var selectedChip by remember {
        //the first chip is selected
        mutableStateOf(0)
    }
    Text(
        text = "Categories",
        style = TextStyle(
            fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black,
        ),
        modifier = Modifier.padding(16.dp)
    )
    LazyRow {
        items(categories.size) {
            Box(Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .clip(shape = RoundedCornerShape(50))
                .background(Color.White)
                .border(1.dp, color = Color(0xFFD5D3D3), CircleShape)

                .clickable {
                    selectedChip = it
                }
                .background(
                    if (selectedChip == it) Color(0xFFD5D3D3)
                    else
                        Color.Transparent
                )
                .padding(16.dp)
            ) {
                Text(text = categories[it], color = Color.Black)
            }
        }
    }

}

@Composable
fun DataList(features: List<Feature>) {
    LazyRow(
        modifier = Modifier
            .height(420.dp),
        //.padding(8.dp, 16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(features.size) {
            FeatureItem(features[it])
        }


    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(32.dp))

    ) {
        Image(
            painter = painterResource(id = feature.photoId), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(250.dp)
                .clip(RoundedCornerShape(25))
        )


        Text(
            text = "⭐ ${feature.rating}", style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                color = Color.White,
                shadow = Shadow(Color.Red)
            ),
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(50))
                .background(Color.Transparent)
                // .alpha(0.5f)
                .padding(8.dp, 6.dp)
        )

        Text(
            text = "📍 ${feature.location}", style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )
                Image(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "heart",
                    modifier = Modifier
                        .padding(18.dp)
                        .align(Alignment.TopEnd)
                )


        }


    }

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.White
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "home"
                )

            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "favorite"
                )

            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "profile"
                )

            }
        }
    }
}
