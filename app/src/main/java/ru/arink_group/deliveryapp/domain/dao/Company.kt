package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 15.11.17.
 */
data class Company(
        val id: Int,
        val name: String,
        val categories: List<Int>,
        val description: String?,
        val contactInfo: ContactInfo,
        val url: String,
        val discountForSelfExport: Double = 0.0,
        val discountForBuscketSum: Double = 0.0,
        val sumForBuscketDiscount: Double = 0.0
) : Serializable