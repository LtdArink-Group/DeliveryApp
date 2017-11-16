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
        val delivery: Delivery,
        val url: String
) : Serializable