package ru.arink_group.deliveryapp.presentation.view.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.app.Fragment
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton

import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Address
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared
import ru.arink_group.deliveryapp.presentation.adapters.OrderAddressesListAdapter
import ru.arink_group.deliveryapp.presentation.adapters.RetryOrdersListAdapter
import ru.arink_group.deliveryapp.presentation.model.DateTime
import ru.arink_group.deliveryapp.presentation.model.Statuses
import ru.arink_group.deliveryapp.presentation.model.TimePickerFragment
import ru.arink_group.deliveryapp.presentation.presenter.RetryOrderPresenterImpl
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.RetryOrderPresenter
import ru.arink_group.deliveryapp.presentation.view.RetryOrderView
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity
import ru.arink_group.deliveryapp.presentation.view.fragment.OrdersHistoryFragment.RETRY_ORDER
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class RetryOrderFragment : Fragment(), RetryOrderView, TimePickerDialog.OnTimeSetListener {

    lateinit var order: Order
    lateinit var actionButton: CircularProgressButton
    lateinit var timePicker: Button
    lateinit var addresses: Spinner
    lateinit var address: TextView
    lateinit var progress: ProgressBar
    lateinit var addressesAdapter: ArrayAdapter<String>
    var addressesList: List<Address> = emptyList()
    lateinit var selectedTime: DateTime

    val presenter: RetryOrderPresenter = RetryOrderPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_retry_order, container, false)

        order = arguments.getSerializable(RETRY_ORDER) as Order

        initActionFields(root)
        initRecycler(root)
        initOrderFields(root)

        return root
    }

    private fun initRecycler(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_retry_order)
        val llm = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        val adapter = RetryOrdersListAdapter(order.products)
        recycler.adapter = adapter
        recycler.layoutManager = llm
    }

    private fun initOrderFields(view: View) {
        val selfExport = view.findViewById<SwitchCompat>(R.id.summary_self_export_switch)
        val summaryCostWithDiscount = view.findViewById<TextView>(R.id.summary_cost_with_discount)
        val deliveryCost = view.findViewById<TextView>(R.id.summary_delivery)
        val summaryCost = view.findViewById<TextView>(R.id.summary)

        selfExport.isChecked = order.pickup
        selfExport.isEnabled = false

        summaryCostWithDiscount.text = "${order.totalCost} р"
        if (order.pickup) {
            deliveryCost.visibility = View.GONE
            view.findViewById<TextView>(R.id.summary_delivery_title).visibility = View.GONE
        } else {
            deliveryCost.text = "${order.deliveryCost} р"
        }
        summaryCost.text = "${order.totalCost + order.deliveryCost} р"

        actionButton = view.findViewById(R.id.send_order_button)
        if (order.isActive() && order.status == Statuses.NEW) {
            actionButton.setText(R.string.order_cancell)
            actionButton.setOnClickListener {
                cancelOrder()
            }
            val color = ContextCompat.getColor(activity, R.color.colorButtonCancell)
            actionButton.setBackgroundColor(color)
        } else if (order.isActive()) {
            actionButton.visibility = View.GONE
        } else {
            actionButton.setText(R.string.order_retry)
            val color = ContextCompat.getColor(activity, R.color.colorButtonRetry)
            actionButton.setBackgroundColor(color)
            actionButton.setOnClickListener {
                sendOrder()
            }
        }
    }

    private fun sendOrder() {
        if (verifyViews()) {
            presenter.sendOrderToServer()
        }
    }

    private fun verifyViews() : Boolean {
        var flag: Boolean

        flag = verifyDeliveryTime()

        return flag
    }

    private fun verifyDeliveryTime(): Boolean {
        val time = getString(R.string.summary_delivery_time)
        val valid = !time.equals(timePicker.text.toString(), ignoreCase = true)
        if (!valid) Toast.makeText(activity, R.string.error_delivery_time_empty, Toast.LENGTH_SHORT).show()
        return valid
    }

    private fun cancelOrder() {
        AlertDialog.Builder(activity)
                .setMessage(R.string.confirm_cancel)
                .setPositiveButton(R.string.yes) { _, _ -> presenter.cancelOrder(order.id) }
                .setNegativeButton(R.string.no, null)
                .show()
    }

    private fun initActionFields(view: View) {
        timePicker = view.findViewById(R.id.start_date_picker)
        address = view.findViewById(R.id.summary_address_text)
        addresses = view.findViewById(R.id.summary_address_list_spinner)
        progress = view.findViewById(R.id.summary_address_progress)

        if (order.isActive()) {
            initTime()
            initAddress(view)
        } else {
            initTimePicker()
            if (order.pickup) {
                hideAddressSpiner(view)
            } else {
                initAddressSpinner()
            }
        }

    }

    private fun initAddress(view: View) {
        progress.visibility = View.GONE
        view.findViewById<TextView>(R.id.summary_address_list).visibility = View.GONE
        val addressInfo = order.addressInfo
        if (!order.pickup) {
            address.visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.summary_address_list).visibility = View.VISIBLE
        }
        address.text = "${addressInfo?.street}, ${addressInfo?.house} (${addressInfo?.title})"
    }

    private fun initTime() {
        val pattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date = pattern.parse(order.deliveryTime)
        val dateTime = DateTime(date)

        timePicker.text = dateTime.toTimeWithDate()
    }

    private fun hideAddressSpiner(view: View) {
        progress.visibility = View.GONE
        addresses.visibility = View.GONE
        view.findViewById<TextView>(R.id.summary_address_list).visibility = View.GONE
    }

    private fun initAddressSpinner() {
        addressesAdapter = OrderAddressesListAdapter(activity, R.layout.item_spinner_address, ArrayList())
        addresses.adapter = addressesAdapter
        presenter.getAddresses()
    }

    private fun initTimePicker() {
        timePicker.setOnClickListener({
            val dp = TimePickerFragment()
            dp.listener = this@RetryOrderFragment
            dp.show(fragmentManager, "DeliveryTime")
        })
    }

    override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {
        val start = DateTime(GetCompanyFromShared.company!!.delivery.period.start)
        val end = DateTime(GetCompanyFromShared.company!!.delivery.period.end)

        val c = Calendar.getInstance()
        val current = DateTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))

        selectedTime = DateTime(hour, minute)

        if (selectedTime.isGreaterThen(end) || selectedTime.isLowerThen(start)) {
            val deliveryError = getString(R.string.time_should_be_between, start, end)
            Toast.makeText(activity, deliveryError, Toast.LENGTH_SHORT).show()
        } else if (selectedTime.isLowerThenNextHourOf(current)) {
            Toast.makeText(activity, R.string.error_cant_be_greater_then_hour, Toast.LENGTH_SHORT).show()
        } else if (selectedTime.isLowerThen(current)) {
            Toast.makeText(activity, R.string.error_cant_be_less_then_current, Toast.LENGTH_SHORT).show()
        } else {
            timePicker.setText(selectedTime.toString())
        }
    }

    override fun showErrorMessage(e: String) {
        Toast.makeText(activity, e, Toast.LENGTH_SHORT).show()
    }

    override fun startButtonAnimation() {
        actionButton.startAnimation()
    }

    override fun stopButtonAnimationWithError(err: String) {
        actionButton.revertAnimation({
            val color = ContextCompat.getColor(activity, R.color.colorButtonFail)
            actionButton.setText(R.string.send_fail)
            actionButton.setBackgroundColor(color)
        })

        showErrorMessage(err)
    }

    override fun showSendingOrderOk() {
        actionButton.revertAnimation({
            val color = ContextCompat.getColor(activity, R.color.colorButtonSuccess)
            actionButton.setText(R.string.send_ok)
            actionButton.setBackgroundColor(color)
        })
    }

    override fun showSendingOrderFail(err: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateAddresses(addresses: MutableList<Address>) {
        addressesList = addresses
        val addressesString = ArrayList<String>()
        for (i in addresses.indices) {
            addressesString.add(i, this.concatAddressName(addresses[i]))
        }
        addressesAdapter.addAll(addressesString)
    }

    private fun concatAddressName(a: Address): String {
        val addressName = StringBuilder()
        addressName.append(a.title)
        addressName.append(" (")
        addressName.append(a.street)
        addressName.append(", ")
        addressName.append(a.house)
        addressName.append(")")
        return addressName.toString()
    }

    override fun loadingAddressStart() {
        addresses.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    override fun loadingAddressFinish() {
        addresses.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    override fun getVerifyedOrder(): Order = order

    override fun redirectToHistory() {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.putExtra(MenuActivity.IS_HISTORY_START, true)
        activity.startActivity(intent)

    }

    override fun getAddressId(): Int? {
        return if (order.pickup) {
            null
        } else {
            val pos = addresses.getSelectedItemPosition()
            addressesList[pos].id
        }
    }

    override fun getDeliveryTime(): DateTime = selectedTime

}// Required empty public constructor
