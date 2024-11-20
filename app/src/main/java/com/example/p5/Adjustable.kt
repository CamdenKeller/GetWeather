package com.example.p5

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlin.math.round

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Adjustable() {
    var temp by remember { mutableFloatStateOf(0f) }
    var lat by remember { mutableDoubleStateOf(0.0) }
    var num by remember { mutableIntStateOf(0) }
    val weather = remember { mutableStateOf("Sunny") }
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val context = LocalContext.current
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = num == 1,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 3000
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 3000
                )
            )
        )
        {
            Background(num)
        }
        AnimatedVisibility(
            visible = num == 2,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 3000
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 3000
                )
            )
        )
        {
            Background(num)
        }
        AnimatedVisibility(
            visible = num == 3,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 3000
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 3000
                )
            )
        )
        {
            Background(num)
        }
        AnimatedVisibility(
            visible = num == 4,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 3000
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 3000
                )
            )
        )
        {
            Background(num)
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Weather App",
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                val x = round(temp)
                val y = x.toInt()
                Text(
                    text = "$y°C",
                    fontSize = 40.sp,
                )
            }
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            )
            {
                Spacer(modifier = Modifier.width(20.dp))
                AnimatedVisibility(
                    visible = num == 1,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000
                        )
                    ),
                ) {
                    Icon1()
                }
                AnimatedVisibility(
                    visible = num == 2,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000
                        )
                    ),
                ) {
                    Icon2()
                }
                AnimatedVisibility(
                    visible = num == 3,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000
                        )
                    ),
                ) {
                    Icon3()
                }
                AnimatedVisibility(
                    visible = num == 4,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 3000
                        )
                    ),
                ) {
                    Icon4()
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Slider1(value = temp) { temp = it }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(140.dp),
                    onClick = {
                        num = 1
                        weather.value = "Sunny"
                    })
                {
                    Text("Sunny")

                }
                Spacer(modifier = Modifier.width(30.dp))
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(140.dp),
                    onClick = {
                        num = 2
                        weather.value = "Rainy"
                    })
                {
                    Text("Rainy")

                }
            }
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(140.dp),
                    onClick = {
                        num = 3
                        weather.value = "Cloudy"
                    })
                {
                    Text("Cloudy")

                }

                Spacer(modifier = Modifier.width(30.dp))
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(140.dp),
                    onClick = {
                        num = 4
                        weather.value = "Windy"
                    })
                {
                    Text("Windy")

                }
            }
            Row(
            )
            {
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 25.dp)
                )
            }
            Row()
            {
                Spacer(modifier = Modifier.width(30.dp))
                Spacer(modifier = Modifier.height(40.dp))
                if (locationPermissionState.status.isGranted) {
                    Button(onClick = {
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener { location ->
                                location?.let {
                                    lat = it.latitude
                                    if ((round(lat) % 4).toInt() == 0) {
                                        num = 1
                                        weather.value = "Sunny"
                                    }
                                    if ((round(lat) % 4).toInt() == 1) {
                                        num = 2
                                        weather.value = "Rainy"
                                    }
                                    if ((round(lat) % 4).toInt() == 2) {
                                        num = 3
                                        weather.value = "Cloudy"
                                    }
                                    if ((round(lat) % 4).toInt() == 3) {
                                        num = 4
                                        weather.value = "Windy"
                                    }
                                }
                            }
                    }) {
                        Text("Get Location Weather")
                    }
                } else {
                    Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                        Text("Request permission")
                    }
                }
            }
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            {
                Spacer(modifier = Modifier.height(25.dp))
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                        .padding(start = 25.dp),
                    onClick = {
                        val rounded: Int = round(temp).toInt()
                        val message =
                            "It is currently ${weather.value} with a temperature of $rounded degrees"
                        val share = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, message)
                        }
                        context.startActivity(
                            Intent.createChooser(
                                share,
                                "Share Weather"
                            )
                        )
                    }
                )
                {
                    Text("Share Weather")
                }
            }

        }
    }
}

@Composable
fun Slider1(value: Float, onValueChange: (Float) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width(375.dp)
    ) {
        Slider(
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(),
            steps = 69,
            valueRange = -20f..50f
        )
    }
}

@Composable
fun Icon1() {
    Image(
        painter = painterResource(id = R.drawable.baseline_wb_sunny_24),
        contentDescription = "Sun",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
fun Icon2() {
    Image(
        painter = painterResource(id = R.drawable.baseline_water_drop_24),
        contentDescription = "Rain",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
fun Icon3() {
    Image(
        painter = painterResource(id = R.drawable.baseline_wb_cloudy_24),
        contentDescription = "Cloud",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
fun Icon4() {
    Image(
        painter = painterResource(id = R.drawable.baseline_air_24),
        contentDescription = "Wind",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
fun Background(num: Int) {
    val color = when (num) {
        1 -> Color.Yellow
        2 -> Color.Blue
        3 -> Color.DarkGray
        4 -> Color.White
        else -> Color.LightGray
    }
    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color)
    )
    {
    }
}
