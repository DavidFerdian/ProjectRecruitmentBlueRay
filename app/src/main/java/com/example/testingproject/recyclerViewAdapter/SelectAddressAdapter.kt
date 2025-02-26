package com.example.testingproject.recyclerViewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.R
import com.example.testingproject.dataClass.AddressResponseItem
import com.example.testingproject.dataClass.DefaultResponse
import com.example.testingproject.dataClass.DeleteAddressRequestBody
import com.example.testingproject.selectAddress.SelectAddressFragment
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.items_select_address.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SelectAddressAdapter(
    val context: Context,
    private var selectAddressFragment: SelectAddressFragment
) :
    RecyclerView.Adapter<SelectAddressAdapter.SelectAddressViewHolder>() {

    var addressList = ArrayList<AddressResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAddressViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_select_address, parent, false)
        return SelectAddressViewHolder(view)
    }

    override fun getItemCount(): Int = addressList.size

    override fun onBindViewHolder(holder: SelectAddressViewHolder, position: Int) {
        val addressData = addressList[position]
        holder.bind(addressData)
    }


    inner class SelectAddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            addressData: AddressResponseItem
        ) {
            with(itemView) {
                if (addressList.isNotEmpty()) {
                    if (selectAddressFragment.defaultSelectedAddress == addressData.addressId) {
                        deleteAddressButton.visibility = View.GONE
                    } else {
                        deleteAddressButton.setOnClickListener {
                            showDeletionConfirmation(addressData)
                        }
                    }

                    setupAddressValue(itemView, addressData)
                }
            }
        }
    }

    private fun setupAddressValue(itemView: View, addressData: AddressResponseItem) {
        with(itemView){
            tv_item_address_name.text =
                addressData.name?.toUpperCase(Locale.ROOT)


            tv_item_address_detail.text = addressData.address

            tv_item_address_phone.text = addressData.phoneNumber

            rb_select_address.isChecked = selectAddressFragment.defaultSelectedAddress == addressData.addressId

            rb_select_address.setOnClickListener {
                selectAddress(addressData.addressId!!)
            }

//            btn_ganti_alamat.setOnClickListener {
//                navigateToEditAddress(addressData)
//            }
        }
    }
//
//    private fun navigateToEditAddress(addressData: AddressData) {
//        selectAddressFragment.findNavController().navigate(
//            SelectAddressFragmentDirections.toEditAddressFragment(
//                AddressData(
//                    addressData.ememberId,
//                    addressData.addressId,
//                    addressData.namaTujuan,
//                    addressData.noHpTujuan,
//                    addressData.alamatTujuan,
//                    addressData.provinsiTujuan,
//                    addressData.kotaTujuan,
//                    addressData.kecamatanTujuan,
//                    addressData.kodePosTujuan,
//                    addressData.kecamatanTujuanJnt,
//                    addressData.defaultYN,
//                    addressData.receiverArea,
//                    addressData.expeditionOID,
//                    addressData.kelurahanTujuan,
//                    addressData.longitude,
//                    addressData.latitude
//                )
//            )
//        )
//    }

    private fun showDeletionConfirmation(addressData: AddressResponseItem) {
        MaterialAlertDialogBuilder(context)
            .setTitle("")
            .setMessage("Anda yakin akan hapus alamat ini ?")
            .setNegativeButton("Tidak") { _, _ ->
                // Respond to negative button press
            }
            .setPositiveButton("Ya") { _, _ ->
                    deleteAddress(addressData.addressId!!)
            }
            .show()
    }


    fun selectAddress(
        PrimaryAddress: Int
    ) {

        ProgressDialogUtility.showProgressDialog(context)
        APIRequestClient.addressService().selectAddress(DeleteAddressRequestBody(PrimaryAddress))
            .enqueue(object :
                Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    SnackBarUtility.renderSnackBar(
                        selectAddressFragment.binding.baseLayout,
                        context.getString(R.string.api_request_failed_message)
                    )
                    ProgressDialogUtility.dismissProgressDialog()
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {

                    ProgressDialogUtility.dismissProgressDialog()

                    if (response.code() == 200) {

                            selectAddressFragment.refreshAddressList(
                                this@SelectAddressAdapter,

                            )

                    } else {
                        selectAddressFragment.binding.baseLayout.let {
                            SnackBarUtility.renderSnackBar(
                                selectAddressFragment.binding.baseLayout,
                                selectAddressFragment.getString(R.string.api_request_failed_message) + " ${response.code()}"
                            )
                        }
                    }


                }
            })
    }


    private fun deleteAddress(addressId: Int) {

        ProgressDialogUtility.showProgressDialog(context)

        APIRequestClient.addressService().deleteAddress(DeleteAddressRequestBody( addressId))
            .enqueue(object : Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    ProgressDialogUtility.dismissProgressDialog()

                    if (!call.isCanceled) {
                        SnackBarUtility.renderSnackBar(
                            selectAddressFragment.binding.baseLayout,
                            context.getString(R.string.api_request_failed_message)
                        )
                    }
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    ProgressDialogUtility.dismissProgressDialog()
                    when {
                        response.code() == 200 -> {
                            selectAddressFragment.getAddressList(
                                this@SelectAddressAdapter,
                            )
                        }

                        else -> {
                            selectAddressFragment.binding.baseLayout.let {
                                SnackBarUtility.renderSnackBar(
                                    selectAddressFragment.binding.baseLayout,
                                    selectAddressFragment.getString(R.string.api_request_failed_message) + " ${response.code()}"
                                )
                            }
                        }
                    }
                }
            })
    }

}