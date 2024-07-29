package com.musicalbums.core.exception

import android.content.Context
import coil.network.HttpException
import com.musicalbums.R
import com.musicalbums.core.retrofit.interceptor.NoConnectivityException
import java.io.IOException

sealed class AppException : Throwable() {
    data object Business : AppException()
    data object ServerDown : AppException()
    data object ConnectionFailed : AppException()
}

fun Throwable.toAppException(): AppException {
    return try {
        when (this) {
            is HttpException -> AppException.Business
            is IOException -> AppException.ConnectionFailed
            else -> AppException.ServerDown
        }
    } catch (e: Exception) {
        AppException.ServerDown
    }
}

fun AppException.getMessageShouldDisplay(
    ctx: Context,
    generalErrorMsgResId: Int = R.string.lbl_general_error_msg
): String {
    return when (this) {
        is AppException.ConnectionFailed -> ctx.getString(R.string.lbl_no_connection_error_msg)
        else -> ctx.getString(generalErrorMsgResId)
    }
}