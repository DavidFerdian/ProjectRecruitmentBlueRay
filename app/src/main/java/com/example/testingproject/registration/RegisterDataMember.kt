package com.example.testingproject.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.R
import com.example.testingproject.dataClass.CustomerRegisterMandatoryRequestBody
import com.example.testingproject.dataClass.RegistrationCustomerFinishResponse
import com.example.testingproject.databinding.FragmentRegisterDataMemberBinding
import com.example.testingproject.util.BadClientResponseMessage
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterDataMember : Fragment() {

    lateinit var binding: FragmentRegisterDataMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register_data_member, container, false
        )

        binding.backToPreviousPageButton.setOnClickListener { activity?.onBackPressed() }
        binding.applyEditButton.setOnClickListener {
            RegisterManadatory()
        }
        return binding.root
    }


    private fun RegisterManadatory() {

        ProgressDialogUtility.showProgressDialog(activity as AppCompatActivity)

        val newFrontName = binding.newFrontNameEditText.text.toString().trim()
        val newBackName = binding.newBackNameEditText.text.toString().trim()
        val newPhoneNumber = binding.newPhoneNumberEditText.text.toString().trim()
        val newPassword = binding.newPasswordEditText.text.toString().trim()
        val newDOB = binding.newDOBEditText.text.toString().trim()

        if (newFrontName.isEmpty() || newBackName.isEmpty() || newPassword.isEmpty() || newPhoneNumber.isEmpty()) {
            ProgressDialogUtility.dismissProgressDialog()
            SnackBarUtility.renderSnackBar(
                binding.rootLayout, "Mohon isi informasi Data yang dibutuhkan"
            )
            return
        }
        val inputtedInformation = CustomerRegisterMandatoryRequestBody(
            newPassword,
            newPhoneNumber,
            newFrontName,
            newBackName,
        )

        APIRequestClient.registerService().customerRegisterMandatory(inputtedInformation)
            .enqueue(object : Callback<RegistrationCustomerFinishResponse> {
                override fun onFailure(
                    call: Call<RegistrationCustomerFinishResponse>, t: Throwable
                ) {
                    ProgressDialogUtility.dismissProgressDialog()
                    if (!call.isCanceled) {
                        SnackBarUtility.renderSnackBar(
                            binding.rootLayout, getString(R.string.api_request_failed_message)
                        )
                    }
                }

                override fun onResponse(
                    call: Call<RegistrationCustomerFinishResponse>,
                    response: Response<RegistrationCustomerFinishResponse>
                ) {

                    ProgressDialogUtility.dismissProgressDialog()

                    when {
                        response.code() == 200 -> {
                            SnackBarUtility.renderSnackBar(
                                binding.rootLayout, "Profil Berhasil Disimpan"
                            )
                            activity?.onBackPressed()
                        }

                        response.code() == 400 -> {
                            val message = BadClientResponseMessage.getMessage(response).toString()
                            SnackBarUtility.renderSnackBar(
                                binding.rootLayout, message
                            )
                        }

                        else -> {
                            binding.rootLayout.let {
                                SnackBarUtility.renderSnackBar(
                                    it,
                                    getString(R.string.api_request_failed_message) + "(${response.code()})"
                                )
                            }
                        }
                    }
                }
            })
    }

}