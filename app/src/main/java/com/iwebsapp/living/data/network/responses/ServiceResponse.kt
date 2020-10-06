package com.iwebsapp.living.data.network.responses

import com.iwebsapp.living.data.db.entities.Services

data class ServiceResponse (
    val statusCode: Int,
    val data: List<Services>
    )

