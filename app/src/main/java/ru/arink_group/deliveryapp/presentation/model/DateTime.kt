package ru.arink_group.deliveryapp.presentation.model

import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by kirillvs on 16.11.17.
 */
class DateTime {
    val hour: Int
    val minute: Int
    val cal : Calendar

    constructor(h: Int, m: Int) {
        hour = h
        minute = m
        cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)

    }

    constructor(time: String) {
        val tms = time.split(" ")[0].split(":")
        hour = tms[0].toInt()
        minute = tms[1].toInt()
        cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
    }

    constructor(timeDate: Date) {
        cal = Calendar.getInstance()
        cal.time = timeDate
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    fun isGreaterThen(secondTime: DateTime): Boolean = when {
        hour > secondTime.hour -> true
        hour == secondTime.hour && minute > secondTime.minute -> true
        else -> false
    }

    fun isLowerThen(secondTime: DateTime): Boolean = when {
        hour < secondTime.hour -> true
        hour == secondTime.hour && minute < secondTime.minute -> true
        else -> false
    }

    fun isLowerThenNextHourOf(secondTime: DateTime): Boolean {
        return hour == secondTime.hour ||
                ((hour + 1) == secondTime.nextHour() && minute <= secondTime.minute)
    }

    private fun nextHour(): Int = hour + 1

    override fun toString(): String = "%02d:%02d".format(hour, minute)

    fun toCurrentDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

        return sdf.format(cal.time)
    }

    fun toTimeWithDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

        return sdf.format(cal.time)
    }

    fun getTimeInMillis(): Long = cal.timeInMillis
}