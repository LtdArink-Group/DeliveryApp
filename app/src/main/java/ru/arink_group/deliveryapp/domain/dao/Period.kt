package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 16.11.17.
 */
data class Period (
        val start: String,
        val end: String
):Serializable