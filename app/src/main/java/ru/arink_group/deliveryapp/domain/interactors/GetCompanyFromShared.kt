package ru.arink_group.deliveryapp.domain.interactors

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Company
import ru.arink_group.deliveryapp.domain.dao.ContactInfo
import ru.arink_group.deliveryapp.domain.dao.Delivery
import ru.arink_group.deliveryapp.domain.dao.Period

/**
 * Created by kirillvs on 15.11.17.
 */
object GetCompanyFromShared {

    val companyDefault: Company = Company(
            App.getCompanyId().toInt(),
            "Chixx",
            listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            "Lorem ipsum dolor sit amet, consectetur adipiscing...",
            ContactInfo("info@chixx.ru", "+7 (962) 287-90-11", "chixx.ru", "Ленинградская, 46", listOf("48.483257,135.094393", "48.469463,135.071622")),
            Delivery(300.toDouble(), 1500.toDouble(), 10.toDouble(), Period("09:00 Vladivostok", "23:55 Vladivostok")),
            ""
    )

    var company: Company? = null

    fun getCompanyOrDefault() : Company {
        if (company != null) return company as Company
        return companyDefault
    }

    fun loadCompany(context: Context) {
        if (company != null) return
        val sp = context.getSharedPreferences(App.APP_SHARED_PREF, Activity.MODE_PRIVATE)
        val companyJson = sp.getString(App.COMPANY_INFO, "")
        if (companyJson.isNotEmpty()) {
            val gson = Gson()
            company = gson.fromJson(companyJson, Company::class.java)
        } else {
            GetCompany().execute(CompanyDisposableObserver(), null)
        }
    }

    class CompanyDisposableObserver : DisposableObserver<Company>() {

        override fun onNext(company: Company) {
            GetCompanyFromShared.company = company
        }

        override fun onError(e: Throwable) {
            // no-op
        }

        override fun onComplete() {
            // no-op
        }
    }


}