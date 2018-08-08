package ru.arink_group.deliveryapp.presentation.shared


import android.R.style.Theme_Holo_Light_Dialog_NoActionBar
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.TimePicker
import ru.arink_group.deliveryapp.R
import java.util.*

/**
 * Created by Swipe on 31.07.2018
 */
class TimePickerFragment : DialogFragment() {

    lateinit var listener: TimePicker.OnTimeChangedListener
    lateinit var timePicker: TimePicker

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val dialog = Dialog(ContextThemeWrapper(activity, Theme_Holo_Light_Dialog_NoActionBar))
        dialog.setContentView(R.layout.time_picker)
        timePicker = dialog.findViewById(R.id.time_picker)
        val button_soon = dialog.findViewById<Button>(R.id.button_soon)
        val button_ok = dialog.findViewById<Button>(R.id.button_ok)
        val button_cancel = dialog.findViewById<Button>(R.id.button_cancel)
        dialog.setTitle("Время заказа")

        timePicker.setIs24HourView(true)

        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            timePicker.currentHour = hourOfDay
            timePicker.currentMinute = minute

        }
        button_cancel.setOnClickListener {
            dialog.dismiss()
        }
        button_ok.setOnClickListener {
            listener.onTimeChanged(timePicker, timePicker.currentHour, timePicker.currentMinute)
            dialog.dismiss()
        }
        button_soon.setOnClickListener {
            listener.onTimeChanged(timePicker, null, null)
            dialog.dismiss()
        }
        return dialog

    }


}