package ru.arink_group.deliveryapp.presentation.view.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Company
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared
import ru.arink_group.deliveryapp.presentation.shared.WorkingDateTime

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

        val contactWeekTime = WorkingDateTime(company.workingDays, this)

        fillOrHide(R.id.about_company_phone_layout, contactPhoneText, company.contactInfo.phone)
        fillOrHide(R.id.about_company_clock_layout, contactClockText, contactWeekTime.toWorkWeekString())
        fillOrHide(R.id.about_company_email_layout, contactEmailText, company.contactInfo.email)
        fillOrHide(R.id.about_company_web_layout, contactWebText, company.contactInfo.web)
        fillOrHide(R.id.about_company_address_layout, contactAddressText, company.contactInfo.address)

        contactEmailText.setOnClickListener { mailUs(company.contactInfo.email) }
        contactPhoneText.setOnClickListener { callUs(company.contactInfo.phone) }
        if(company.contactInfo.web != null) {
            contactWebText.setOnClickListener { goWeb(company.contactInfo.web) }
        }

        aboutCompanyText.text = company.description
    }

    private fun fillOrHide(layoutRes: Int, textView: TextView, text: String?) {
        if (text != null && text.isNotEmpty()) {
            textView.text = text
        } else {
            findViewById<RelativeLayout>(layoutRes).visibility = View.GONE
        }
    }

    private fun callUs(phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
            startActivity(intent)
        } catch (e: SecurityException) {
            Toast.makeText(this, R.string.error_cant_call, Toast.LENGTH_SHORT).show()
        }
    }

    private fun mailUs(mail: String) {
        try {
            val mailIntent = Intent(Intent.ACTION_SEND)
            mailIntent.type = "message/rfc822"
            mailIntent.putExtra(Intent.EXTRA_EMAIL, mail)
            startActivity(Intent.createChooser(mailIntent,"Send Email"))
        } catch (e: SecurityException) {
            Toast.makeText(this, R.string.error_cant_mail, Toast.LENGTH_SHORT).show()
        }
    }

    private fun goWeb(address: String) {
        try {
            val web = Intent(Intent.ACTION_VIEW)
            web.data = Uri.parse(address)
            startActivity(web)
        } catch (e: Exception) {
            Toast.makeText(this, R.string.error_something_goes_wrong, Toast.LENGTH_SHORT).show()
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
            val marker = mMap.addMarker(MarkerOptions().position(position).title(getString(R.string.about_company_delivery_title)))
            marker.showInfoWindow()
            if (index == 0) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12.0F))
                marker.showInfoWindow()
            }
        })
        addressesGeotagsCafe().forEachIndexed({ index: Int, geotag: List<String> ->
            val position = LatLng(geotag[0].toDouble(), geotag[1].toDouble())
            mMap.addMarker(MarkerOptions().position(position).title(getString(R.string.about_company_cafe_title)))
        })
    }

    private fun addressesGeotags() : List<List<String>> {
        val listtags = mutableListOf<List<String>>()
        company.contactInfo.geotag.forEach {
            listtags.add(it.split(","))
        }
        return listtags
    }

    private fun addressesGeotagsCafe() : List<List<String>> {
        val listtags = mutableListOf<List<String>>()
        company.contactInfo.geotagCafe.forEach {
            listtags.add(it.split(","))
        }
        return listtags
    }
}
