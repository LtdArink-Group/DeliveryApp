package ru.arink_group.deliveryapp.domain.dao

import ru.arink_group.deliveryapp.presentation.shared.DateTime
import java.io.Serializable
import java.util.*

/**
 * Created by kirillvs on 14.12.2017.
 */
class CompanyWorkingDay(
        dayOfWeek: String,
        private val startTime: String?,
        private val endTime: String?
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

    fun isRest(): Boolean = startTime == null || endTime == null

    fun startTimeClass(): DateTime? {
        return if (startTime == null) {
            null
        } else {
            val tmp = startTime.split("+")
            DateTime(tmp[0].trim(), tmp[1])
        }
    }
    fun endTimeClass(): DateTime? {
        return if (endTime == null) {
            null
        } else {
            val tmp = endTime.split("+")
            DateTime(tmp[0].trim(), tmp[1])
        }
    }
}