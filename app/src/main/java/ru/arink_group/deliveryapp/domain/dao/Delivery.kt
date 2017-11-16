package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 16.11.17.
 */
data class Delivery(
        val cost: Double,
        val freeShipping: Double,
        val pickupDiscount: Double,
        val period: Period
):Serializable