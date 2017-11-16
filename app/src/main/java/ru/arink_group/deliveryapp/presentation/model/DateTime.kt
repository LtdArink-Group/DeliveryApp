package ru.arink_group.deliveryapp.presentation.model

/**
 * Created by kirillvs on 16.11.17.
 */
class DateTime {
    val hour: Int
    val minute: Int

    constructor(h: Int, m: Int) {
        hour = h
        minute = m
    }

    constructor(time: String) {
        val tms = time.split(":").map { it.toInt() }
        hour = tms[0]
        minute = tms[1]
    }

    fun isGreaterThen(secondTime: DateTime): Boolean {
        return when {
            hour > secondTime.hour -> true
            hour == secondTime.hour && minute > secondTime.minute -> true
            else -> false
        }
    }

    fun isLowerThen(secondTime: DateTime): Boolean {
        return when {
            hour < secondTime.hour -> true
            hour == secondTime.hour && minute < secondTime.minute -> true
            else -> false
        }
    }

    fun isLowerThenNextHourOf(secondTime: DateTime): Boolean {
        return hour == secondTime.hour ||
                ((hour + 1) == secondTime.nextHour() && minute <= secondTime.minute)
    }

    private fun nextHour(): Int {
        return hour + 1
    }

    override fun toString(): String {
        return "%02d:%02d".format(hour, minute)
    }
}