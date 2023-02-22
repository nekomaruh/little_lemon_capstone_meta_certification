package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.preference.PreferenceManager
import com.example.littlelemon.ui.component.LemonBorderedText
import com.example.littlelemon.ui.component.LemonButton
import com.example.littlelemon.ui.component.LemonHeader

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController) {
    Scaffold(
        topBar = { LemonHeader() }
    ) {
        Content(navController)
    }
}

@Composable
private fun Content(navController: NavController) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
    val firstName = prefs.getString("firstName", "").toString()
    val lastName = prefs.getString("lastName", "").toString()
    val email = prefs.getString("email", "").toString()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Personal Information",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(all = 20.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "First name")
                LemonBorderedText(text = firstName)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Last name")
                LemonBorderedText(text = lastName)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Email")
                LemonBorderedText(text = email)
            }
        }
        LemonButton(text = "Log out") {
            navController.navigate(Onboarding.route)
        }
    }
}

