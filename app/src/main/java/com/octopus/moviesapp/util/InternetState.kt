package com.octopus.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class InternetState(
    context: Context,
    private val client: OkHttpClient,
    private val request: Request
) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun getCurrentNetworkStatus(): Boolean {
        return if (capabilityInternet()) {
            checkConnection()
        } else false
    }

    private fun capabilityInternet(): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private suspend fun checkConnection(): Boolean {
        var isConnection: Boolean

        withContext(Dispatchers.IO) {
            val startTime = System.currentTimeMillis()

            val response: Response? = try {
                client.newCall(request).execute()
            } catch (e: Exception) {
                null
            }

            val responseCode = response?.code() ?: 500
            val timeTaken = System.currentTimeMillis() - startTime

            withContext(Dispatchers.Main) {
                isConnection = if (responseCode != 200) {
                    false
                } else {
                    timeTaken <= 3000
                }
            }
        }

        return isConnection

    }


}