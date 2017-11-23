package ru.arink_group.deliveryapp.presentation.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Company
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared
import ru.arink_group.deliveryapp.presentation.model.DateTime

class AboutCompanyActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val company: Company = GetCompanyFromShared.getCompanyOrDefault()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_company)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initView()
    }

    private fun initView() {
        val aboutCompanyText = findViewById<TextView>(R.id.about_company_text)
        val contactPhoneText = findViewById<TextView>(R.id.phone_text)
        val contactClockText = findViewById<TextView>(R.id.clock_text)
        val contactEmailText = findViewById<TextView>(R.id.email_text)
        val contactWebText = findViewById<TextView>(R.id.web_text)
        val contactAddressText = findViewById<TextView>(R.id.address_text)

        val contactTime = "${DateTime(company.delivery.period.start)} - ${DateTime(company.delivery.period.end)}"

        fillOrHide(R.id.about_company_phone_layout, contactPhoneText, company.contactInfo.phone)
        fillOrHide(R.id.about_company_clock_layout, contactClockText, contactTime)
        fillOrHide(R.id.about_company_email_layout, contactEmailText, company.contactInfo.email)
        fillOrHide(R.id.about_company_web_layout, contactWebText, company.contactInfo.web)
        fillOrHide(R.id.about_company_address_layout, contactAddressText, company.contactInfo.address)

        aboutCompanyText.text = company.description
    }

    private fun fillOrHide(layoutRes: Int, textView: TextView, text: String?) {
        if (text != null) {
            textView.text = text
        } else {
            findViewById<RelativeLayout>(layoutRes).visibility = View.GONE
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        addressesGeotags().forEachIndexed({ index: Int, geotag: List<String> ->
            val position = LatLng(geotag[0].toDouble(), geotag[1].toDouble())
            mMap.addMarker(MarkerOptions().position(position).title(company.name))
            if (index == 0)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0F))
        })
    }

    private fun addressesGeotags() : List<List<String>> {
        val listtags = mutableListOf<List<String>>()
        company.contactInfo.geotag.forEach {
            listtags.add(it.split(","))
        }
        return listtags
    }
}