package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable
import java.util.*

/**
 * Created by kirillvs on 15.11.17.
 */
class Company(
        val id: Int,
        val name: String,
        val categories: List<Int>,
        val addresses: List<Address>,
        val description: String?,
        val contactInfo: ContactInfo,
        val delivery: Delivery,
        val url: String?,
        val workingDays: List<CompanyWorkingDay>
) : Serializable {
    fun getCurrentDayOrFirst(): CompanyWorkingDay {
        val cal = Calendar.getInstance()
        val currentWorkDay = workingDays.filter { cal.get(Calendar.DAY_OF_WEEK) == it.dayOfWeek }
        if (currentWorkDay.isNotEmpty()) return currentWorkDay[0]
        else if (workingDays.isNotEmpty()) return workingDays[0]
        else return CompanyWorkingDay("mon", delivery.period.start, delivery.period.end)
    }
}