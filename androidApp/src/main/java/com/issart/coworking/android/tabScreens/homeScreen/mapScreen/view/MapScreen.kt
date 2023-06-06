package com.issart.coworking.android.tabScreens.homeScreen.mapScreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.mapScreen.viewModel.MapViewModel
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.buttonTextStyle
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreen(viewModel: MapViewModel = koinViewModel(), navController: NavHostController) {


    val lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle.value = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        AndroidView(factory = { context ->
            MapKitFactory.initialize(context)
            MapView(context)
        },
            update = { mapView ->
                val cameraListener = CameraListener { map, cameraPosition, cameraUpdateReason, b ->
                    viewModel.setLatLng(
                        cameraPosition.target.latitude,
                        cameraPosition.target.longitude
                    )
                }
                when (lifecycle.value) {
                    Lifecycle.Event.ON_START -> {
                        MapKitFactory.getInstance().onStart()
                        mapView.onStart()
                        mapView.map.move(CameraPosition(Point(55.754405, 37.619992), 10f, 0f, 0f))
                        mapView.map.addCameraListener(cameraListener)
                    }
                    Lifecycle.Event.ON_STOP -> {
                        mapView.map.removeCameraListener(cameraListener)
                        mapView.onStop()
                        MapKitFactory.getInstance().onStop()
                    }
                    else -> Unit
                }
            })
        Image(
            modifier = Modifier
                .size(width = 39.dp, height = 57.dp)
                .padding(bottom = 24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.subtract),
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .indication(remember { MutableInteractionSource() }, null),
                onClick = {
                    viewModel.setGeo()
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = activeContentColor,
                    contentColor = backgroundColor
                )
            ) {
                Text(text = "Выбрать местоположение", style = buttonTextStyle)
            }
            Spacer(modifier = Modifier.height(100.dp))
        }

    }
}