package com.example.testingproject.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.NavigationDirections
import com.example.testingproject.R
import com.example.testingproject.dataClass.InsertNewMemberRequestBody
import com.example.testingproject.dataClass.InsertNewMemberVerificationCodeRequestBody
import com.example.testingproject.dataClass.RegisterMemberResponse
import com.example.testingproject.databinding.FragmentRegisterVerificationCodeBinding
import com.example.testingproject.util.NavigationDrawerLockUtility
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register_part1.*
import kotlinx.android.synthetic.main.fragment_register_verification_code.VerificationEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVerificationCodeFragment : Fragment() {

    lateinit var binding: FragmentRegisterVerificationCodeBinding
    lateinit var registerContext: Context
    lateinit var Email: String
    private lateinit var registerService: Call<RegisterMemberResponse>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerContext = context
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments?.let {
            RegisterVerificationCodeFragmentArgs.fromBundle(
                it
            )
        }

        if (args != null) {
            Email = args.email
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        NavigationDrawerLockUtility.lockNavigationDrawer((activity as AppCompatActivity).drawerLayout)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_verification_code, container, false
        )
        binding.verificationCodeButton.setOnClickListener {
            executeRegisterValidationCode()
        }
        binding.resendCode.setOnClickListener {
            executeResendCode()
        }
        binding.textEmail.text = "Masukan Kode Verifikasi Yang telah kami kirim ke " +Email
        return binding.root
    }


    private fun executeRegisterValidationCode() {
        registerService = APIRequestClient.registerService()
            .customerRegisterVerifyCode(
                InsertNewMemberVerificationCodeRequestBody(
                    binding.VerificationEditText.text.toString().trim(), Email
                )
            )

        registerService.enqueue(object :
            Callback<RegisterMemberResponse> {
            override fun onFailure(call: Call<RegisterMemberResponse>, t: Throwable) {
                ProgressDialogUtility.dismissProgressDialog()

                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }
            }

            override fun onResponse(
                call: Call<RegisterMemberResponse>,
                response: Response<RegisterMemberResponse>
            ) {

                ProgressDialogUtility.dismissProgressDialog()
                when {
                    response.code() == 200 -> {
                        Log.i("response",response.toString())
                        navigateTotoregisterDataMember()
                    }
                }
            }
        })
    }

    private fun executeResendCode() {
        registerService = APIRequestClient.registerService()
            .resendCode(InsertNewMemberRequestBody(Email))

        registerService.enqueue(object :
            Callback<RegisterMemberResponse> {
            override fun onFailure(call: Call<RegisterMemberResponse>, t: Throwable) {
                ProgressDialogUtility.dismissProgressDialog()

                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }
            }

            override fun onResponse(
                call: Call<RegisterMemberResponse>,
                response: Response<RegisterMemberResponse>
            ) {

                SnackBarUtility.renderSnackBar(
                    binding.baseLayout,
                    "Kode Berhasil Dikirim Silahkan Di check Kembali"
                )
            }
        })
    }

    private fun navigateTotoregisterDataMember() {
        findNavController().navigate(
            NavigationDirections.toregisterDataMember(
                Email
            )
        )
    }

}