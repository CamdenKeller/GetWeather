package com.example.p5

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Networking() {
    val scope = rememberCoroutineScope()
    var temp by remember { mutableIntStateOf(0) }
    var humidity by remember { mutableIntStateOf(0) }
    var sunset by remember { mutableLongStateOf(0) }
    var minTemp by remember { mutableIntStateOf(0) }
    var feelsLike by remember { mutableIntStateOf(0) }
    var sunrise by remember { mutableLongStateOf(0) }
    var maxTemp by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
                    fontSize = 30.sp
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
                Text(
                    text = "$temp°C",
                    fontSize = 50.sp,
                )
            }
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Feels like: $feelsLike°C")
                    Text(text = "Humidity: $humidity%")
                    Text(
                        text = "Minimum Temperature: $minTemp°C",
                    )
                    Text(
                        text = "Maximum Temperature: $maxTemp°C",
                    )
                    Text(
                        text = "Sunrise: ${Time(sunrise * 1000)}",
                    )
                    Text(text = "Sunset: ${Time(sunset * 1000)}")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row()
            {
                Spacer(modifier = Modifier.width(30.dp))
                if (locationPermissionState.status.isGranted) {
                    Button(onClick = {
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener { location ->
                                location?.let {
                                    scope.launch {
                                        withContext(Dispatchers.IO) {
                                            val result = RetrofitHelper.apiService.getWeather(
                                                it.latitude,
                                                it.longitude
                                            )
                                            result.body()?.let {
                                                temp = it.temp
                                                humidity = it.humidity
                                                sunset = it.sunset
                                                minTemp = it.min_temp
                                                feelsLike = it.feels_like
                                                sunrise = it.sunrise
                                                maxTemp = it.max_temp
                                            }
                                        }
                                    }
                                }
                            }
                    })
                    {
                        Text("Update Weather")
                    }
                } else {
                    Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                        Text("Request Location Permission")
                    }
                }
            }
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                        .padding(start = 25.dp),
                    onClick = {
                        val message =
                            "It is currently $temp°C, and it feels like $feelsLike°C"
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


