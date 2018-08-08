package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnOrdersHistoryClickListener
import ru.arink_group.deliveryapp.presentation.shared.DateTime
import ru.arink_group.deliveryapp.presentation.shared.Statuses
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kirillvs on 21.11.17.
 */
class OrdersHistoryAdapter(val isActive: Boolean = true) : RecyclerView.Adapter<OrdersHistoryAdapter.ViewHolder>() {

    lateinit var onClickListener: OnOrdersHistoryClickListener

    var orders: List<Order> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = orders.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_order_history, parent, false)
        return ViewHolder(itemView, parent!!.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        val view = holder.view
        val context = holder.context

        val orderNumView = view.findViewById<TextView>(R.id.order_num)
        val statusView = view.findViewById<TextView>(R.id.order_history_status)
        val addressTypeView = view.findViewById<TextView>(R.id.order_history_type)
        val addressView = view.findViewById<TextView>(R.id.order_history_address)
        val contentView = view.findViewById<TextView>(R.id.order_history_data_and_cost)
        val counterView = view.findViewById<TextView>(R.id.order_history_counter)

        val pattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date = pattern.parse(order.deliveryTime)
        val dateTime = DateTime(date)

        orderNumView.text = "№ ${order.num}"

        statusView.text = order.status
        if (order.status == Statuses.CANCELLED) {
            val cancelledColor = ContextCompat.getColor(context, R.color.colorstatusCancelled)
            statusView.setTextColor(cancelledColor)
        }

        if (isActive) {
            val currentCal = Calendar.getInstance()
            val diff = dateTime.getTimeInMillis() - currentCal.timeInMillis

            if (dateTime.minute == null && dateTime.hour == null) {
                counterView.setText(R.string.soon)
                counterView.visibility = View.VISIBLE
            } else {
                if (diff > 0) {
                    object : CountDownTimer(diff, 1000) {

                        override fun onFinish() {
                            counterView.text = "Готово"
                        }

                        override fun onTick(millisUntilFinished: Long) {
                            val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24
                            val minutes = (millisUntilFinished / (1000 * 60)) % 60
                            val seconds = (millisUntilFinished / 1000) % 60
                            counterView.text = "$hours:$minutes:$seconds"
                        }
                    }.start()
                    counterView.visibility = View.VISIBLE
                }
            }
        }

        val addressInfo = order.addressInfo
        if (addressInfo != null) {
            val addressString = "${addressInfo.street}, ${addressInfo.house} (${addressInfo.title})"
            addressView.text = addressString
        }

        if (order.pickup) {
            addressTypeView.setText(R.string.self_export)
        } else {
            addressTypeView.setText(R.string.company_export)
        }
        val content: String
        if ((counterView.text == "На ближайшее время"))
            content = "На ближайшее время - ${order.totalCost + order.deliveryCost} Р"
        else
            content = "${dateTime.toTimeWithDate()} - ${order.totalCost + order.deliveryCost} Р"
        contentView.text = content

        view.setOnClickListener { onClickListener.onOrderHistoryItemClick(order) }
    }

    class ViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view)
}