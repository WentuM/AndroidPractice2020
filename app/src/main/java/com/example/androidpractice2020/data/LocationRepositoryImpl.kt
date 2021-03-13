package com.example.androidpractice2020.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl(
    private var client: FusedLocationProviderClient
) {

        @SuppressLint("MissingPermission")
    suspend fun getUserLocation(): Location = suspendCancellableCoroutine { continuation ->
        client.lastLocation.addOnSuccessListener {
            continuation.resume(it)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }
}