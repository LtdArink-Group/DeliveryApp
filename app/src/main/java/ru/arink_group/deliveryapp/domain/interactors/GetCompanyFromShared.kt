package ru.arink_group.deliveryapp.domain.interactors

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.domain.dao.*

/**
 * Created by kirillvs on 15.11.17.
 */
object GetCompanyFromShared {

    val companyDefault: Company = Company(
            App.getCompanyId().toInt(),
            "ИП Свиридов Ф.Т.",
            listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            emptyList(),
            "ChiXX – это команда единомышленников, которые любят и умеют готовить.\\nНаша цель – предложить Вам лучшие рецепты из самых деликатесных частей курицы. Все наши блюда готовятся вручную, вкусно, быстро и с душой. Используем только свежие продукты и очень внимательно относимся к процессу приготовления.\\nМы уверены, что наш проект поможет Вам разнообразить привычную палитру готовых блюд, которая предлагается в нашем городе.\\nЗакажите прямо сейчас и в кратчайшие сроки восхитительные блюда окажутся на Вашем столе!",
            ContactInfo("info@chixx.ru", "+7 (4212) 77-60-25", null, null, listOf("48.483257,135.094393"), listOf("48.469463,135.071622")),
            Delivery(150.toDouble(), 800.toDouble(), 10.toDouble(), Period("12:00 +10", "19:30 +10")),
            "",
            listOf(
                    CompanyWorkingDay("sun","13:00:00+10", "21:00:00+10"),
                    CompanyWorkingDay("mon",null, null),
                    CompanyWorkingDay("tue","12:00:00+10", "21:00:00+10"),
                    CompanyWorkingDay("wed","12:00:00+10", "21:00:00+10"),
                    CompanyWorkingDay("thu","12:00:00+10", "21:00:00+10"),
                    CompanyWorkingDay("fri","12:00:00+10", "21:00:00+10"),
                    CompanyWorkingDay("sat","12:00:00+10", "21:00:00+10")
            )
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