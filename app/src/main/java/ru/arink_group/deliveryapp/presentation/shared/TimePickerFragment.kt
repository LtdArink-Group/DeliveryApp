package ru.arink_group.deliveryapp.presentation.shared

import android.R.*
import android.R.style.Theme
import android.R.style.Theme_Holo_Light_Dialog_NoActionBar
import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.android.synthetic.main.time_picker.*
import kotlinx.android.synthetic.main.time_picker.view.*
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.R.id.time
import ru.arink_group.deliveryapp.R.id.time_picker
import java.sql.Time
import java.util.*

/**
 * Created by Swipe on 31.07.2018
 */
class TimePickerFragment : DialogFragment(), OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var listener: TimePicker.OnTimeChangedListener
    lateinit var timePicker: TimePicker

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val dialog = Dialog(ContextThemeWrapper(activity, Theme_Holo_Light_Dialog_NoActionBar))
        dialog.setContentView(R.layout.time_picker)
        timePicker = dialog.findViewById<TimePicker>(R.id.time_picker)
        //timePicker.currentHour=-1
        //timePicker.currentMinute=-1
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
            listener.onTimeChanged(timePicker, -1, -1)
            dialog.dismiss()
        }
        return dialog

    }


}