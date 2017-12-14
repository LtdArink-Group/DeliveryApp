package ru.arink_group.deliveryapp.domain.dao

import ru.arink_group.deliveryapp.presentation.model.DateTime
import java.io.Serializable
import java.util.*

/**
 * Created by kirillvs on 14.12.2017.
 */
class CompanyWorkingDay(
        dayOfWeek: String,
        val startTime: String,
        val endTime: String
) : Serializable {
    val dayOfWeek: Int = when(dayOfWeek) {
        "mon" -> Calendar.MONDAY
        "tue" -> Calendar.TUESDAY
        "wed" -> Calendar.WEDNESDAY
        "thu" -> Calendar.THURSDAY
        "fri" -> Calendar.FRIDAY
        "sat" -> Calendar.SATURDAY
        "sun" -> Calendar.SUNDAY
        else -> Calendar.MONDAY
    }

    fun startTimeClass(): DateTime? {
        return if (startTime.isEmpty()) {
            null
        } else {
            DateTime(startTime)
        }
    }
    fun endTimeClass(): DateTime? {
        return if (endTime.isEmpty()) {
            null
        } else {
            DateTime(endTime)
        }
    }
}