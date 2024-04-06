package ru.je_dog.core.feature.base.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.utills.ext.lineMeters
import ru.je_dog.core.feature.model.GeoPointPresentation

class LocationManagerImpl(
    private val context: Context,
): LocationManager {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(): GeoPointPresentation? {

        var geoPoint: GeoPointPresentation? = null

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            geoPoint = GeoPointPresentation(
                latitude = location.latitude,
                longitude = location.longitude
            )

        }

        return geoPoint
    }

    @SuppressLint("MissingPermission")
    override fun broadcastLocation(
        secondsInterval: Long,
    ): Flow<GeoPointPresentation> = callbackFlow {



        val locationCallback = object : LocationCallback(){

            override fun onLocationResult(locationResult: LocationResult) {

                locationResult.lastLocation?.let { location ->

                    val geoPoint = GeoPointPresentation(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )

                    trySend(geoPoint)

                }

            }

        }

        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.Builder(secondsInterval * 1000).apply {
                setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            }.build(),
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

}

