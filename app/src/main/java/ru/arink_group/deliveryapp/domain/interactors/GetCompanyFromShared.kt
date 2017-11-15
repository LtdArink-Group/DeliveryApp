package ru.arink_group.deliveryapp.domain.interactors

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.Company

/**
 * Created by kirillvs on 15.11.17.
 */
object GetCompanyFromShared {

    var company: Company? = null

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