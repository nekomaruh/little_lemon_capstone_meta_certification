package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.component.LemonHeader
import com.example.littlelemon.ui.component.LemonTextField
import com.example.littlelemon.ui.component.SearchLemonTextField
import com.example.littlelemon.ui.component.TextH1
import com.example.littlelemon.ui.component.TextH2
import com.example.littlelemon.ui.theme.LemonDarkGreen
import com.example.littlelemon.ui.theme.LemonWhite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = { ProfileLemonHeader(navController) }
    ) {
        Content()
    }
}

@Composable
private fun ProfileLemonHeader(navController: NavController) {
    LemonHeader()
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)
    ) {
        Box(modifier = Modifier.weight(1f))
        ProfileImageButton(navController = navController)
    }
}

@Composable
private fun ProfileImageButton(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "",
        modifier = Modifier
            .height(60.dp)
            .clickable {
                navController.navigate(Profile.route)
            }
    )
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RestaurantHeader()
    }

}

@Composable
fun RestaurantHeader() {

    val restaurantText = "We are a family owned\n" +
            "Mediterranean restaurant,\n" +
            "focused on traditional\n" +
            "recipes served with a\n" +
            "modern twist."

    val search = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(color = LemonDarkGreen)
            .fillMaxWidth()
            .padding(all = 20.dp)
    ) {
        TextH1(text = "Little Lemon")
        TextH2(
            text = "Chicago",
            modifier = Modifier.offset(y = (-30).dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-18).dp)
        ) {
            Text(
                text = restaurantText,
                color = LemonWhite,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(120.dp)
                    .height(120.dp)
            )
        }
        SearchLemonTextField(
            hint = "Enter search phrase",
            value = search.value
        ) { search.value = it }
    }
}
