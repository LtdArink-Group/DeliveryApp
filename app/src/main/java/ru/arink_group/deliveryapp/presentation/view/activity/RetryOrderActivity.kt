package ru.arink_group.deliveryapp.presentation.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Order
import ru.arink_group.deliveryapp.presentation.view.fragment.OrdersHistoryFragment
import ru.arink_group.deliveryapp.presentation.view.fragment.RetryOrderFragment

class RetryOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retry_order)

        supportActionBar?.setTitle(R.string.title_retry_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val order = getOrderFromParams(intent)

        val ft = fragmentManager.beginTransaction()
        val fragment = RetryOrderFragment()
        fragment.arguments = setBundleForFragment(order)
        ft.add(R.id.retry_order_frame, fragment)
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun getOrderFromParams(intent: Intent) : Order =
            intent.getSerializableExtra(OrdersHistoryFragment.RETRY_ORDER) as Order

    private fun setBundleForFragment(order: Order) : Bundle {
        val bundle = Bundle()
        bundle.putSerializable(OrdersHistoryFragment.RETRY_ORDER, order)
        return bundle
    }

}
