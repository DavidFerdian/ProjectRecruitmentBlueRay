package com.example.testingproject.registration

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
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
import com.example.testingproject.dataClass.RegisterMemberResponse
import com.example.testingproject.databinding.FragmentRegisterPart1Binding
import com.example.testingproject.util.NavigationDrawerLockUtility
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register_part1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragmentPart1 : Fragment() {

    lateinit var binding: FragmentRegisterPart1Binding
    lateinit var registerContext: Context
    private lateinit var registerService: Call<RegisterMemberResponse>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        NavigationDrawerLockUtility.lockNavigationDrawer((activity as AppCompatActivity).drawerLayout)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_part1, container, false
        )
        binding.RegisterButton.setOnClickListener {
            executeRegisterMember()
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        RegisterUsernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()

                if (isValidEmail(email)) {
                    binding.RegisterUsernameInputLayout.error = null
                } else {
                    binding.RegisterUsernameInputLayout.error = "Format email tidak valid"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun executeRegisterMember() {
        registerService = APIRequestClient.registerService()
            .insertNewMember(
                InsertNewMemberRequestBody(
                    RegisterUsernameEditText.text.toString().trim()
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
                if (response.body()?.action == true) {
                    navigateToVerificationCodeFragment()
                } else {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        "Email atau Password Anda Salah Mohon Coba Kembali"
                    )
                }
            }
        })
    }

    private fun navigateToVerificationCodeFragment() {
        findNavController().navigate(
            NavigationDirections.toRegisterVerificationCodeFragment(RegisterUsernameEditText.text.toString())
        )
    }


}