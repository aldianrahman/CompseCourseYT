package com.example.compsecourseyt.transport

import android.content.Context
import android.util.Log
import com.example.compsecourseyt.R
import com.example.compsecourseyt.util.Util
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.koushikdutta.async.future.FutureCallback
import java.util.concurrent.CancellationException

open class IonMaster {

    fun getJsonFutureCallback(
        context: Context,
        callback: IonCallback
    ): FutureCallback<JsonObject> {
        return FutureCallback { e, jsonObject ->
            if (e != null) {
                if (e is CancellationException) {
                    callback.onReadyCallback(
                        context.getString(R.string.error_when_processing_your_data),
                        e
                    )
                } else if (Util.isNetworkAvailable(context)) {
                    callback.onReadyCallback(
                        context.getString(R.string.error_when_processing_your_data),
                        null
                    )
                } else {
                    callback.onReadyCallback(
                        context.getString(R.string.check_your_internet_connection),
                        null
                    )
                }
                Log.d("ION_MASTER", "onError: $e")
            } else {
                Log.d("ION_MASTER", "onCompleted: $jsonObject") //sampai sini bener
                if(jsonObject["document"] != null) {
                    if (jsonObject["document"] is JsonObject ||
                        jsonObject["document"] is JsonArray ||
                        jsonObject["document"] is JsonPrimitive)
                    {
                        callback.onReadyCallback(null,jsonObject)
                    }else {
                        callback.onReadyCallback("Error ","Error")
                    }
                }else {
                    callback.onReadyCallback(
                        context.getString(R.string.error_when_processing_your_data),
                        null
                    )
                }

            }
        }
    }

    interface IonCallback {
        fun onReadyCallback(errorMessage: String?, `object`: Any?)
    }
}