package com.example.testingproject.selectAddress

import com.example.testingproject.dataClass.AddressResponseItem
import com.example.testingproject.recyclerViewAdapter.SelectAddressAdapter


object SelectAddressUtil {

    fun setAddressList(adapter: SelectAddressAdapter, newAddressList: List<AddressResponseItem?>?) {
        if (newAddressList == null) return

        adapter.addressList.clear()
        adapter.addressList.addAll(newAddressList.filterNotNull())
        adapter.notifyDataSetChanged()
    }

    fun getAddressListSize(adapter: SelectAddressAdapter): Int {
        return adapter.addressList.size
    }


}