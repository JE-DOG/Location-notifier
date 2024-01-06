package ru.je_dog.core.feature.location

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
import ru.je_dog.core.feature.ext.lineMeters

class LocationManagerImpl(
    private val context: Context,
): LocationManager {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(): GeoPoint? {

        var geoPoint: GeoPoint? = null

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->

            geoPoint = GeoPoint(
                location.latitude,
                location.longitude
            )

        }

        return geoPoint
    }

    @SuppressLint("MissingPermission")
    override fun broadcastLocation(
        secondsInterval: Long,
    ): Flow<GeoPoint> = callbackFlow {



        val locationCallback = object : LocationCallback(){

            override fun onLocationResult(locationResult: LocationResult) {

                locationResult.lastLocation?.let { location ->

                    val geoPoint = GeoPoint(
                        location.latitude,
                        location.longitude
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

    override fun isLocationNearby(geoPoint: GeoPoint, meters: Int): Boolean {

        val currentGeoPoint = getLocation() ?: return false
        val lineMeters = currentGeoPoint lineMeters geoPoint

        return lineMeters <= meters
    }

}

