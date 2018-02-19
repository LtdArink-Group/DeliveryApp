package ru.arink_group.deliveryapp.presentation.presenter

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.ResponseBody
import org.json.JSONObject
import ru.arink_group.deliveryapp.presentation.shared.ErrorsTranslator

/**
 * Created by kirillvs on 22.11.17.
 */
abstract class BasePresenter {

    fun handleInternalError(e: Throwable) : String {
        return ErrorsTranslator.translate("internal")
    }

    fun handleGetNetError(e: Throwable) : String {
        return if (e is HttpException && e.code() == 404) {
            "404"
        } else {
            ErrorsTranslator.translate("connection")
        }
    }

    fun handlePostNetError(e: Throwable) : String {
        return if (e is com.jakewharton.retrofit2.adapter.rxjava2.HttpException) {
            val body = e.response().errorBody()
            getErrorMessage(body!!)
        } else {
            ErrorsTranslator.translate("connection")
        }
    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            val resp = jsonObject.getJSONObject("errors").keys()
            val result = StringBuilder("Неверно заполнены поля: ")
            resp.forEach { result.append("\n${ErrorsTranslator.translate(it)}") }
            result.toString()
        } catch (e: Exception) {
            ErrorsTranslator.translate("")
        }
    }

}