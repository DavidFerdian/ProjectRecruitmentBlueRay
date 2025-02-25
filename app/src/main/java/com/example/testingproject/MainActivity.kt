package com.example.testingproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.testingproject.R
import com.example.testingproject.databinding.ActivityMainBinding
import com.example.testingproject.dataClass.voucher.VoucherTransaksiHeader
import com.example.testingproject.util.SharedPreferenceUtility


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    var x = ""
    var Voucherid=""
    var base64photo =""
    var OutletIdChange =""
    var expeditionid= 0
    var lat = 0.0
    var long = 0.0
    var outletId =""
    var keranjangchecker = false

    var voucherTransaksiSelected : VoucherTransaksiHeader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Reset Dashboard Bottom Navigation Shared Preference
        this.resetBottomNavPosition()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = this.findNavController(R.id.myNavHostFragment)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.clear()
    }

    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            return
        }
    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        if (currentFocus != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
//        }
//        return super.dispatchTouchEvent(ev)
//    }

    override fun onDestroy() {
        super.onDestroy()

        //Reset Dashboard Bottom Navigation Shared Preference
        this.resetBottomNavPosition()
    }

    private fun resetBottomNavPosition() {
        SharedPreferenceUtility.resetDashboardBottomNavPosition(this)
    }
}