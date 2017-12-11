package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 15.11.17.
 */
data class ContactInfo(
        val email: String,
        val phone: String,
        val web: String?,
        val address: String?,
        val geotag: List<String>,
        val geotagCafe: List<String>
) : Serializable