package com.example.testingproject.util

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testingproject.APIRequestHandling.APIRequestClient
import com.example.testingproject.R
import com.example.testingproject.dataClass.defaultResponse.DefaultResponse
import com.example.testingproject.dataClass.basket.InsertToBasketRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CartUtility {
    fun insertItemToCart(
        baseLayout: View,
        insertedInformation: InsertToBasketRequestBody,
        context: Context,
        fragment: Fragment
    ) {

        ProgressDialogUtility.showProgressDialog(context)

        APIRequestClient.basketService(context).insertItemToCart(insertedInformation)
            .enqueue(object :
                Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    ProgressDialogUtility.dismissProgressDialog()

                    if (!call.isCanceled) {
                        SnackBarUtility.renderSnackBar(
                            baseLayout,
                            context.getString(R.string.api_request_failed_message)
                        )
                    }
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    ProgressDialogUtility.dismissProgressDialog()

                    when (response.code()) {
                        200 -> {
                            SnackBarUtility.renderSnackBar(
                                baseLayout,
                                "Produk Berhasil Masuk Keranjang"
                            )
                        }

                        400 ->{
                            try {
                                //Retrofit does not convert response if response code is 400
                                val responseMessage = BadClientResponseMessage.getMessage(response)

                                if(responseMessage.equals("Exceeds Stock")){
                                    SnackBarUtility.renderSnackBar(
                                        baseLayout,
                                        context.getString(R.string.product_stock_validation)
                                    )
                                }else if(responseMessage.equals("Exceeds Limit Qty Order") || responseMessage.equals("Exceeds Limit Purchase")){
                                    SnackBarUtility.renderSnackBar(
                                        baseLayout,
                                        context.getString(R.string.product_qty_limit_reached)
                                    )
                                }else{
                                    SnackBarUtility.renderSnackBar(
                                        baseLayout,
                                        "$responseMessage"
                                    )
                                }
                            } catch (error: Exception) {
                                SnackBarUtility.renderSnackBar(
                                    baseLayout,
                                    context.getString(R.string.api_request_failed_message) + " (${response.code()})"
                                )
                            }
                        }

                        401 -> {
                            WarningDialogUtility.showTokenExpirationWarning(context, fragment)
                        }

                        else -> {
                            SnackBarUtility.renderSnackBar(
                                baseLayout,
                                context.getString(R.string.api_request_failed_message) + " (${response.code()})"
                            )
                        }
                    }
                }
            })
    }


}