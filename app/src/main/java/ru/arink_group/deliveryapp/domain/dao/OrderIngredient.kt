package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 20.11.17.
 */
data class OrderIngredient(
        val qty: Int,
        val totalCost: Double,
        val name: String
) : Serializable