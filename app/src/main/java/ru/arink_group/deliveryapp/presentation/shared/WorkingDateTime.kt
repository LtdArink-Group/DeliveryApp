package ru.arink_group.deliveryapp.presentation.shared

import android.content.Context
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.CompanyWorkingDay
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by kirillvs on 14.12.2017.
 */
class WorkingDateTime(val workingDays: List<CompanyWorkingDay>, val context: Context) {

    private val mon = context.getString(R.string.mon)
    private val tue = context.getString(R.string.tue)
    private val wed = context.getString(R.string.wed)
    private val thu = context.getString(R.string.thu)
    private val fri = context.getString(R.string.fri)
    private val sat = context.getString(R.string.sat)
    private val sun = context.getString(R.string.sun)
    private val rest = context.getString(R.string.rest)


//    fun toWorkWeekString(): String = "Вт-Пт 12:00-21:00\nВс 14:00-23:00\nПн - выходной"
    fun toWorkWeekString(): String {
        val groupdDays = combineDays(parseList(workingDays))

        var result = ""
        groupdDays.forEach({(k, v) -> result += "${v.joinToString()} - $k\n" })
        return result
    }

    private fun parse(day: CompanyWorkingDay): String {
        val dw = when(day.dayOfWeek) {
            Calendar.MONDAY -> mon
            Calendar.TUESDAY -> tue
            Calendar.WEDNESDAY -> wed
            Calendar.THURSDAY -> thu
            Calendar.FRIDAY -> fri
            Calendar.SATURDAY -> sat
            else -> sun
        }
        return if (day.startTimeClass() == null || day.endTimeClass() == null) return "$dw $rest"
        else "$dw ${day.startTimeClass()}-${day.endTimeClass()}"
    }

    private fun parseList(days:List<CompanyWorkingDay>) : List<String> {
        val parsedList = ArrayList<String>()
        days.forEach {
            parsedList.add(parse(it))
        }
        return parsedList
    }

    private fun valueOrDefault(map: HashMap<String, String>, key: String) : String = if (map[key] == null) "" else map[key]!!

    private fun combineDays(days: List<String>): java.util.HashMap<String, ArrayList<String>> {
        val groups = java.util.HashMap<String, ArrayList<String>>()
        days.forEach {
            val dt = it.split(" ")
            val day = dt[0]
            val time = dt[1]
            if (groups[time] != null)
                groups[time]!!.add(day)
            else {
                groups[time] = ArrayList()
                groups[time]!!.add(day)
            }
        }
        return groups
    }

}