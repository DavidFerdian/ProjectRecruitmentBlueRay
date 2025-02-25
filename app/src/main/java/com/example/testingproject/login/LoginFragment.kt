package com.example.testingproject.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.NavigationDirections
import com.example.testingproject.R
import com.example.testingproject.dataClass.LoggedUser
import com.example.testingproject.dataClass.LoginSuccesResponse
import com.example.testingproject.dataClass.LoginValidationRequestBody
import com.example.testingproject.databinding.FragmentLoginBinding
import com.example.testingproject.sharedPreference.LoginStatusPreference
import com.example.testingproject.util.NavigationDrawerLockUtility
import com.example.testingproject.util.ProgressDialogUtility
import com.example.testingproject.util.SnackBarUtility
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    lateinit var loggedStatusPreference: LoginStatusPreference

    private lateinit var loginService: Call<LoginSuccesResponse>

    private lateinit var loginContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        navController = this.findNavController()
        NavigationDrawerLockUtility.lockNavigationDrawer((activity as AppCompatActivity).drawerLayout)
        loggedStatusPreference = LoginStatusPreference(loginContext)


        binding.loginButtonOnClickHandler = this

        binding.tvRegister.setOnClickListener {
        }


        return binding.root
    }


    /*
    * Layout-related methods
    * */

    private fun enableLoginButton() {
        binding.nextButton.isEnabled = true
    }


    /*
    * Validation-related methods
    * */
    fun validateUsernameInput() {
        ProgressDialogUtility.showProgressDialog(activity as AppCompatActivity)

        val inputtedUsername = binding.loginUsernameEditText.text.toString().trim()
        val inputtedPassword = binding.loginPasswordEditText.text.toString().trim()
        if (inputtedUsername.isEmpty()) {
            ProgressDialogUtility.dismissProgressDialog()
            SnackBarUtility.renderSnackBar(binding.baseLayout, "Mohon mengisi username")
            return
        }

        binding.nextButton.isEnabled = false

        executeLoginValidation(LoginValidationRequestBody(inputtedPassword,inputtedUsername))
    }

    private fun executeLoginValidation(postedInformation: LoginValidationRequestBody) {
        loginService = APIRequestClient.memberService()
            .loginValidation(postedInformation)

        loginService.enqueue(object :
            Callback<LoginSuccesResponse> {
            override fun onFailure(call: Call<LoginSuccesResponse>, t: Throwable) {
                ProgressDialogUtility.dismissProgressDialog()

                if (!call.isCanceled) {
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        getString(R.string.api_request_failed_message)
                    )
                }
                enableLoginButton()
            }

            override fun onResponse(
                call: Call<LoginSuccesResponse>,
                response: Response<LoginSuccesResponse>
            ) {

                ProgressDialogUtility.dismissProgressDialog()
                if (response.body()?.login == true) {
                    this@LoginFragment.setupLoggedUser(response.body()?.token!!)
                    navigateToAddressList()
                }else{
                    SnackBarUtility.renderSnackBar(
                        binding.baseLayout,
                        "Email atau Password Anda Salah Mohon Coba Kembali"
                    )
                }
            }
        })
    }

    private fun setupLoggedUser(
        loginToken: String,
    ) {
        loggedStatusPreference.setAsLoggedIn(
            LoggedUser(
                true,
                loginToken,

            )
        )
    }
    /*
    * Methods for navigation to other pages
    * */
    private fun navigateToAddressList() {
        findNavController().navigate(
            NavigationDirections.toAddressList()
        )
    }

    private fun navigateToRegister() {
        findNavController().navigate(
            NavigationDirections.toRegisterFragmentPart1()
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()

        if (this::loginService.isInitialized) {
            loginService.cancel()
        }
    }
}