package com.example.testingproject.util

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.R
import com.example.testingproject.dataClass.defaultResponse.DefaultResponse
import com.example.testingproject.dataClass.wishlist.InsertWishlistRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WishlistUtility {
    fun insertNewWishlistItem(
        baseLayout: View,
        context: Context,
        inputtedInformation: InsertWishlistRequestBody,
        fragment: Fragment
    ) {

        ProgressDialogUtility.showProgressDialog(baseLayout.context)
        Log.i("apa isinya",inputtedInformation.toString())
        APIRequestClient.wishlistService(context).insertNewWishlist(inputtedInformation)
            .enqueue(object :
                Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    ProgressDialogUtility.dismissProgressDialog()

                    SnackBarUtility.renderSnackBar(
                        baseLayout,
                        "Gagal memasukkan produk kedalam wishlist, silahkan coba kembali"
                    )

                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.code() == 200) {
                        ProgressDialogUtility.dismissProgressDialog()
                        SnackBarUtility.renderSnackBar(
                            baseLayout,
                            "Produk Berhasil Masuk ke wishlist"
                        )
                    } else if (response.code() == 401) {
                        WarningDialogUtility.showTokenExpirationWarning(context, fragment)
                    } else {
                        SnackBarUtility.renderSnackBar(
                            baseLayout,
                            context.getString(
                                R.string.api_request_failed_message_error_code, response.code()
                            )
                        )
                    }

                }
            })
    }

}