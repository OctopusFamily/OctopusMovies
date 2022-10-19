package com.octopus.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

class ConnectionTrackerImpl @Inject constructor(
    context: Context,
) : ConnectionTracker {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override suspend fun isInternetConnectionAvailable(): Boolean {
        return if (isConnectedToWifiOrCellular()) {
            checkInternetAvailability()
        } else false
    }

    private fun isConnectedToWifiOrCellular(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private suspend fun checkInternetAvailability(): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val socket = Socket()
                val socketAddress = InetSocketAddress(HOST_NAME, PORT)
                socket.connect(socketAddress, CONNECTION_TIMEOUT)
                socket.close()
            }
            true
        } catch (e: Exception) {
            Log.d("MALT", "ERROR: $e")
            false
        }
    }


//    private val socket: Socket,
//    private val socketAddress: InetSocketAddress,

    companion object {
        private const val HOST_NAME = "8.8.8.8"
        private const val PORT = 53
        private const val CONNECTION_TIMEOUT = 3000
    }
}