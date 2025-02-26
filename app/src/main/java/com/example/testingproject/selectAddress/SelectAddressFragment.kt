package com.example.testingproject.selectAddress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.R
import com.example.testingproject.dataClass.AddressResponse
import com.example.testingproject.dataClass.DefaultAddressResponse
import com.example.testingproject.databinding.FragmentSelectAddressBinding
import com.example.testingproject.recyclerViewAdapter.SelectAddressAdapter
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectAddressFragment : Fragment() {

    lateinit var binding: FragmentSelectAddressBinding
    private var totalAddressListSize = 0
    private lateinit var addressListService: Call<AddressResponse>
    lateinit var selectAddressContext: Context
    private lateinit var defaultAddressService: Call<DefaultAddressResponse>
     var defaultSelectedAddress :Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectAddressContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_address, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = context

        binding.btnBackToPrevious.setOnClickListener { activity?.onBackPressed() }

//        binding.btnTambahAlamatBaru.setOnClickListener {
//            navigateToAddressEdit(memberId)
//        }

        val selectAddressAdapter =
            SelectAddressAdapter((selectAddressContext), this)

        with(binding.rvSelectAddress) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = selectAddressAdapter
            recycledViewPool.setMaxRecycledViews(0, 0)
        }

        getAddressList(selectAddressAdapter)
    }


    fun getAddressList(selectAddressAdapter: SelectAddressAdapter) {
        showProgressBar()
        hideAddressList()
        hideAddAddressButton()

        addressListService =
            APIRequestClient.addressService().getAddressList()

        addressListService.enqueue(object :
            Callback<AddressResponse> {
            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }
                hideProgressBar()
            }

            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {

                showAddressList()

                when (response.code()) {
                    200 -> {
                        val addressList = response.body()
                        SelectAddressUtil.setAddressList(
                            selectAddressAdapter,
                            addressList?.addressResponse
                        )
                        totalAddressListSize =
                            SelectAddressUtil.getAddressListSize(selectAddressAdapter)


                        hideProgressBar()
                        showAddAddressButton()
                    }
                    else -> {
                        binding.baseLayout.let {
                            SnackBarUtility.renderSnackBar(
                                it,
                                getString(R.string.api_request_failed_message) + " ${response.code()}"
                            )
                        }
                    }
                }
            }
        })
    }

    private fun hideAddAddressButton() {
        binding.btnTambahAlamatBaru.visibility = View.GONE
    }

    private fun showAddAddressButton() {
        binding.btnTambahAlamatBaru.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.pbSelectAddress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.pbSelectAddress.visibility = View.GONE
    }

    private fun hideAddressList() {
        binding.rvSelectAddress.visibility = View.GONE
    }

    private fun showAddressList() {
        binding.rvSelectAddress.visibility = View.VISIBLE
    }

    fun refreshAddressList(selectAddressAdapter: SelectAddressAdapter) {

        selectAddressContext.let { ProgressDialogUtility.showProgressDialog(it) }

        addressListService =
            APIRequestClient.addressService().getAddressList()

        addressListService.enqueue(object :
            Callback<AddressResponse> {
            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                ProgressDialogUtility.dismissProgressDialog()

                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }
            }

            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {

                ProgressDialogUtility.dismissProgressDialog()
                when (response.code()) {
                    200 -> {
                        val addressList = response.body()
                        SelectAddressUtil.setAddressList(selectAddressAdapter, addressList?.addressResponse)
                        totalAddressListSize =
                            SelectAddressUtil.getAddressListSize(selectAddressAdapter)
                        selectAddressAdapter.notifyDataSetChanged()
                    }
                    else -> {
                        binding.baseLayout.let {
                            SnackBarUtility.renderSnackBar(
                                it,
                                getString(R.string.api_request_failed_message) + " ${response.code()}"
                            )
                        }
                    }
                }
            }
        })
    }


    private fun getDefaultAddress() {
        defaultAddressService = APIRequestClient.addressService()
            .getDefaultAddress()

        defaultAddressService.enqueue(object :
            Callback<DefaultAddressResponse> {
            override fun onFailure(call: Call<DefaultAddressResponse>, t: Throwable) {
                ProgressDialogUtility.dismissProgressDialog()

                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }

            }

            override fun onResponse(
                call: Call<DefaultAddressResponse>,
                response: Response<DefaultAddressResponse>
            ) {

                ProgressDialogUtility.dismissProgressDialog()
                if (response.body()?.action == true) {
                    defaultSelectedAddress= response.body()?.result?.addressId!!
                }
            }
        })
    }

    override fun onDestroyView() {
        if (this::addressListService.isInitialized) {
            addressListService.cancel()
        }

        super.onDestroyView()
    }
}