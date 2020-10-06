package com.iwebsapp.living.ui.home

import com.iwebsapp.living.R
import com.iwebsapp.living.data.db.entities.Services
import com.iwebsapp.living.databinding.ItemServicesBinding
import com.xwray.groupie.databinding.BindableItem

class ServicesItem (
    private val service: Services
    ): BindableItem<ItemServicesBinding>() {

    override fun getLayout() = R.layout.item_services

    override fun bind(viewBinding: ItemServicesBinding, position: Int) {
        viewBinding.service = service
    }
    }
