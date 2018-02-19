package ru.arink_group.deliveryapp.presentation.view.fragment


import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Address
import ru.arink_group.deliveryapp.presentation.presenter.AddressPresenterImpl
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AddressPresenter
import ru.arink_group.deliveryapp.presentation.view.AddressView
import ru.arink_group.deliveryapp.presentation.view.ProgressView
import ru.arink_group.deliveryapp.presentation.view.activity.AddressActivity
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity


/**
 * A simple [Fragment] subclass.
 */
class AddressFragment : Fragment(),  AddressView, View.OnClickListener{

    lateinit var address: Address

    lateinit var presenter: AddressPresenter

    lateinit var addressTitleView: TextInputEditText

    lateinit var addressCityView: TextInputEditText

    lateinit var addressStreetView: TextInputEditText

    lateinit var addressHouseView: TextInputEditText

    lateinit var addressOfficeView: TextInputEditText

    lateinit var addressFloorView: TextInputEditText

    lateinit var addressEntranceView: TextInputEditText

    lateinit var addressCodeView: TextInputEditText

    lateinit var addAddressButton: Button

    lateinit var errorCantBeBlankString: String

    lateinit var progressView : ProgressView

    private var startOrder: Boolean = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_address, container, false)

        initViews(root)

        if (arguments!!.getSerializable(AddressActivity.ADDRESS_SER) != null) {
            address = arguments!!.getSerializable(AddressActivity.ADDRESS_SER) as Address
        } else {
            address = Address(null)
        }

        startOrder = arguments.getBoolean(OrderFragment.REDIRECT_TO_ORDER, false)

        presenter = AddressPresenterImpl(this)

        setViewFileds()

        addAddressButton.setOnClickListener(this)

        progressView =  activity as ProgressView

        return root
    }

    private fun initViews(root: View) {
        addressTitleView = root.findViewById(R.id.address_title)
        addressCityView = root.findViewById(R.id.address_city)
        addressStreetView = root.findViewById(R.id.address_street)
        addressHouseView = root.findViewById(R.id.address_house)
        addressOfficeView = root.findViewById(R.id.address_office)
        addressFloorView = root.findViewById(R.id.address_floor)
        addressEntranceView = root.findViewById(R.id.address_entrance)
        addressCodeView = root.findViewById(R.id.address_code)

        addAddressButton = root.findViewById(R.id.add_address_button)

        errorCantBeBlankString = activity.getString(R.string.error_cant_be_blank)
    }

    private fun setViewFileds() {
        addressTitleView.setText(address.title)
        addressCityView.setText(address.city)
        addressStreetView.setText(address.street)
        addressHouseView.setText(address.house)
        addressOfficeView.setText(address.office)
        addressFloorView.setText(address.floor)
        addressEntranceView.setText(address.entrance)
        addressCodeView.setText(address.code)
    }

    private fun verifyAddress() : Boolean {
        var flag = true

        if (!verifyViewItem(addressTitleView)) flag = false
        if (!verifyViewItem(addressCityView)) flag = false
        if (!verifyViewItem(addressStreetView)) flag = false
        if (!verifyViewItem(addressHouseView)) flag = false

//                -- Not required! --
//        if (!verifyViewItem(addressOfficeView)) flag = false
//        if (!verifyViewItem(addressFloorView)) flag = false
//        if (!verifyViewItem(addressEntranceView)) flag = false
//        if (!verifyViewItem(addressCodeView)) flag = false

        return flag
    }

    private fun verifyViewItem(v: TextInputEditText): Boolean {
        if (v.text.toString().isEmpty()) {
            v.error = errorCantBeBlankString
            return false
        }

        return true
    }

    private fun updateAddressModel() {
        address.title = addressTitleView.text.toString()
        address.city = addressCityView.text.toString()
        address.street = addressStreetView.text.toString()
        address.house = addressHouseView.text.toString()
        address.office = addressOfficeView.text.toString()
        address.floor = addressFloorView.text.toString()
        address.entrance = addressEntranceView.text.toString()
        address.code = addressCodeView.text.toString()
    }

    override fun onClick(v: View?) {
        if (verifyAddress()) {
            updateAddressModel()
            presenter.updateAddress(address)
        }
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAddress(address: Address) {
        // no need as i see now
    }

    override fun goToAccount() {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.putExtra(MenuActivity.IS_ACCOUNT_START, true)
        activity.startActivity(intent)
    }

    override fun goToOrder() {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.putExtra(MenuActivity.IS_ORDER_START, true)
        activity.startActivity(intent)
    }

    override fun loadingStart() {
        progressView.loadingStart()
    }

    override fun loadingFinish() {
        progressView.loadingFinish()
    }
    override fun isStartOrder(): Boolean = startOrder
}// Required empty public constructor
