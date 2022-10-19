package com.octopus.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

class InternetStateImpl @Inject constructor(
    context: Context,
    private val socket: Socket,
    private val socketAddress: InetSocketAddress,
) : ConnectionStatus {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun getCurrentNetworkStatus(): Boolean {
        return if (capabilityInternet()) {
            checkIntUsingSocket()
//            checkConnection()
        } else false
    }

    private suspend fun checkIntUsingSocket(): Boolean {
        return try {
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53) // 853
            withContext(Dispatchers.IO) {
                socket.connect(socketAddress, 3000)
                socket.close()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun capabilityInternet(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

//    private fun checkConnection(): Boolean {
//        var isConnection: Boolean
//
//        val startTime = System.currentTimeMillis()
//
//        val response: Response? = try {
//            client.newCall(request).execute()
//        } catch (e: Exception) {
//            null
//        }
//
//        val responseCode = response?.code() ?: 500
//        val timeTaken = System.currentTimeMillis() - startTime
//
//        isConnection = if (responseCode != 200) {
//            false
//        } else {
//            timeTaken <= 3000
//        }
//
////        withContext(Dispatchers.Main) {
////
////        }
////
////        withContext(Dispatchers.IO) {
////
////        }
//
//        return isConnection
//
//    }
}