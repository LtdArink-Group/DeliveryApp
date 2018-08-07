package ru.arink_group.deliveryapp.domain.dao

import ru.arink_group.deliveryapp.presentation.shared.DateTime
import ru.arink_group.deliveryapp.presentation.shared.Statuses
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kirillvs on 20.11.17.
 */
class Order(
        val id: String,
        val status: String,
        val totalCost: Double,
        val deliveryCost: Double,
        val companyId: Int,
        val accountId: String,
        val deliveryTime: String,
        val note: String?,
        val pickup: Boolean,
        val products: MutableList<OrderProduct>,
        val num: Int,
        val addressInfo: OrderAddressInfo?
) : Serializable {
    fun isActive(): Boolean {
        if (status == Statuses.CANCELLED) return false
        val pattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date = pattern.parse(deliveryTime)
        val dateTime = DateTime(date)
        if (dateTime.minute == 59 && dateTime.hour == 22)
            return true
        val currentCal = Calendar.getInstance()
        val diff = dateTime.getTimeInMillis() - currentCal.timeInMillis
        return diff > 0
    }
}