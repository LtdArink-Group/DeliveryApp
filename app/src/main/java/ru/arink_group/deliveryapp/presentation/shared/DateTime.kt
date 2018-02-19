package ru.arink_group.deliveryapp.presentation.shared

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
        Calendar.MONDAY
    }

    constructor(time: String) {
        val tms = time.split(" ")[0].split(":")
        hour = tms[0].toInt()
        minute = tms[1].toInt()
        cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.timeZone =TimeZone.getTimeZone("GMT${time.split(" ")[1]}")
    }

    constructor(time: String, zone: String) {
        val tms = time.split(":")
        hour = tms[0].toInt()
        minute = tms[1].toInt()
        cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.timeZone =TimeZone.getTimeZone("GMT+$zone")
    }

    constructor(timeDate: Date) {
        cal = Calendar.getInstance()
        cal.time = timeDate
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    fun isGreaterThen(secondTime: DateTime): Boolean = when {
        cal.compareTo(secondTime.cal) > 0 -> true
        else -> false
    }

    fun isLowerThen(secondTime: DateTime): Boolean = when {
        cal.compareTo(secondTime.cal) < 0 -> true
        else -> false
    }

    fun isLowerThenNextHourOf(secondTime: DateTime): Boolean {
        val secondCal = secondTime.cal.clone() as Calendar
        secondCal.set(Calendar.HOUR_OF_DAY, secondTime.nextHour())
        return cal.compareTo(secondCal) < 0
    }

    private fun nextHour(): Int = hour + 1

    override fun toString(): String = "%02d:%02d".format(hour, minute)

    fun toCurrentDateString(): String = cal.time.toString()

    fun toTimeWithDate(): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

        return sdf.format(cal.time)
    }

    fun getTimeInMillis(): Long = cal.timeInMillis
}