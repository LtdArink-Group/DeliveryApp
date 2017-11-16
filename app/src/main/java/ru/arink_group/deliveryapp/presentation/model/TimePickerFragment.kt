package ru.arink_group.deliveryapp.presentation.model

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import java.util.*

/**
 * Created by kirillvs on 16.11.17.
 */
class TimePickerFragment: DialogFragment() {

    lateinit var listener: TimePickerDialog.OnTimeSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity, listener, hour, minute, true)
    }

}