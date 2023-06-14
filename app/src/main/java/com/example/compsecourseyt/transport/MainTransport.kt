package com.example.compsecourseyt.transport

import android.content.Context
import android.util.Log
import com.example.compsecourseyt.util.Util
import com.google.gson.JsonObject
import com.koushikdutta.async.future.Future
import com.koushikdutta.ion.Ion

class MainTransport : IonMaster() {

        fun getData(context: Context?, callback: IonMaster.IonCallback?): Future<JsonObject>? {
            val sendObject = JsonObject()

            sendObject.addProperty("dataSource", "Cluster0")
            sendObject.addProperty("database", "todo")
            sendObject.addProperty("collection", "traning")

            val returnObj: Future<JsonObject> = Ion.with(context)
                .load(Util.getBaseUrl("findOne"))
                .setLogging("IONLOG", Log.DEBUG)
                .setHeader("Content-Type", "application/json")
                .setHeader("Access-Control-Request-Headers", "*")
                .setHeader("Accept", "application/json")
                .setJsonObjectBody(sendObject)
                .asJsonObject()
            returnObj.setCallback(getJsonFutureCallback(context!!, callback!!))
            return returnObj
        }


}