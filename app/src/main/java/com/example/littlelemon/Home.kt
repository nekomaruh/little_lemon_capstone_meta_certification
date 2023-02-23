package com.example.littlelemon

import android.annotation.SuppressLint
import android.app.Activity
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Database
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.littlelemon.ui.component.LemonHeader
import com.example.littlelemon.ui.component.LemonOutlinedButton
import com.example.littlelemon.ui.component.LemonTextField
import com.example.littlelemon.ui.component.SearchLemonTextField
import com.example.littlelemon.ui.component.TextH1
import com.example.littlelemon.ui.component.TextH2
import com.example.littlelemon.ui.theme.LemonBlack
import com.example.littlelemon.ui.theme.LemonDarkGreen
import com.example.littlelemon.ui.theme.LemonGray
import com.example.littlelemon.ui.theme.LemonWhite
import com.example.littlelemon.ui.theme.LemonYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {


    val database by lazy {
        Room.databaseBuilder(
            navController.context,
            AppDatabase::class.java, "database"
        ).build()
    }



    Scaffold(
        topBar = { ProfileLemonHeader(navController) }
    ) {
        Content(database)
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
private fun Content(database: AppDatabase) {
    val databaseMenuItems = database.menuItemDao()
        .getAll()
        .observeAsState(emptyList())
        .value

    val categories = databaseMenuItems.map { it.category }.toSet().toList()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        RestaurantHeader()
        OrderForDelivery(categories)
        MenuItems(databaseMenuItems)
    }

}

@Composable
fun OrderForDelivery(categories: List<String>) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = LemonBlack
        )
        Box(modifier = Modifier.height(10.dp))
        LazyRow(
            state = rememberLazyListState()
        ) {
            items(categories.size, { it }) {
                LemonOutlinedButton(categories[it]) {

                }
                Box(modifier = Modifier.width(20.dp))
            }
        }
        Divider(
            color = LemonGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )
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


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(list: List<MenuItemRoom>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    ) {
        list.forEach { item ->
            Row {
                Column(
                    modifier = Modifier.weight(1F)
                ) {
                    Text(
                        text = item.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Box(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Box(modifier = Modifier.height(8.dp))
                    Text(text = "\$${item.price}")
                }
                Box(modifier = Modifier.width(16.dp))
                GlideImage(
                    model = item.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                ){
                    it.dontAnimate()
                }
            }
            if (item != list[list.count() - 1])
                Divider(
                    color = LemonGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
        }
    }
}