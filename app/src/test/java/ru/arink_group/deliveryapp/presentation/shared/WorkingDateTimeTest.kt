package ru.arink_group.deliveryapp.presentation.shared

import android.content.Context
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.CompanyWorkingDay

/**
 * Created by kirillvs on 14.12.2017.
 */

@RunWith(MockitoJUnitRunner::class)
class WorkingDateTimeTest {

    lateinit var subject: WorkingDateTime

    @Mock
    lateinit var mContext: Context

    @Before
    fun initAll() {
        `when`(mContext.getString(R.string.mon)).thenReturn("Пн")
        `when`(mContext.getString(R.string.tue)).thenReturn("Вт")
        `when`(mContext.getString(R.string.wed)).thenReturn("Ср")
        `when`(mContext.getString(R.string.thu)).thenReturn("Чт")
        `when`(mContext.getString(R.string.fri)).thenReturn("Пт")
        `when`(mContext.getString(R.string.sat)).thenReturn("Сб")
        `when`(mContext.getString(R.string.sun)).thenReturn("Вс")
        `when`(mContext.getString(R.string.rest)).thenReturn("Выходной")


        val mon = CompanyWorkingDay("mon", null, null)
        val tue = CompanyWorkingDay("tue", "12:00:00+10", "21:00:00+10")
        val wed = CompanyWorkingDay("wed", "12:00:00+10", "21:00:00+10")
        val thu = CompanyWorkingDay("thu", "12:00:00+10", "21:00:00+10")
        val fri = CompanyWorkingDay("fri", "12:00:00+10", "21:00:00+10")
        val sat = CompanyWorkingDay("sat", "12:00:00+10", "21:00:00+10")
        val sun = CompanyWorkingDay("sun", "14:00:00+10", "23:00:00+10")
        val workDaysArray = listOf<CompanyWorkingDay>(mon, tue, wed, thu, fri, sat, sun)
        subject = WorkingDateTime(workDaysArray, mContext)
    }

    @Test
    fun toWorkWeekString() {
        assertThat(subject.toWorkWeekString(),
                `is`("Вт, Ср, Чт, Пт, Сб - 12:00-21:00\n" +
                "Вс - 14:00-23:00\n" +
                "Пн - Выходной\n"))
    }

}