package com.project.twitter.ui.authenticate

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.project.twitter.R
import com.project.twitter.repository.session.UserSession
import com.project.twitter.ui.BaseActivity
import com.project.twitter.ui.map.TwitterMapActivity
import com.project.twitter.utils.ToastUtil
import com.project.twitter.utils.extensions.getViewModel
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.Callback
import kotlinx.android.synthetic.main.activity_authenticate.*


class AuthenticationActivity : BaseActivity() {

    lateinit var session: TwitterSession
    lateinit var authToken: TwitterAuthToken


    companion object {
        private const val ERROR_DIALOG_REQUEST = 9001
        private val TAG = AuthenticationActivity::class.java.simpleName
        private const val PERMISSION_CODE = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(this)
        setContentView(R.layout.activity_authenticate)
        checkUserPermissions()
        authenticateUser()
    }

    private fun isServicesOK(): Boolean {
        Log.d(TAG, "isServicesOK: checking google services version")
        val available =
            GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(this@AuthenticationActivity)
        when {
            available == ConnectionResult.SUCCESS -> {
                Log.d(TAG, "isServicesOK: Google Play Services is working")
                return true
            }
            GoogleApiAvailability.getInstance().isUserResolvableError(available) -> {
                Log.d(TAG, "isServicesOK: an error occurred but we can fix it")
                val dialog: Dialog = GoogleApiAvailability.getInstance()
                    .getErrorDialog(
                        this@AuthenticationActivity, available,
                        ERROR_DIALOG_REQUEST
                    )
                dialog.show()
            }
            else -> {
                ToastUtil.showCustomToast(this, "You can't make map requests")
            }
        }
        return false
    }


    private fun checkUserPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val lm = getSystemService(LOCATION_SERVICE) as LocationManager
            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            ) {
                askUserToEnableLocation()
            }
        } else {
            requestLocationPermission();
        }
    }

    fun loginUser() {
        if (isServicesOK()) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(this, TwitterMapActivity::class.java)
                startActivity(intent)
            } else {
                ToastUtil.showCustomToast(this, "Location Permission was denied")
            }
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                this@AuthenticationActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val lm = getSystemService(LOCATION_SERVICE) as LocationManager
                if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                ) {
                    askUserToEnableLocation()
                }
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun authenticateUser() {
        activityAuthenticate_loginBtn.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                session = TwitterCore.getInstance().sessionManager.activeSession
                authToken = session.authToken
                UserSession.session = session
                UserSession.userID = result.data.id
                loginUser()
            }

            override fun failure(exception: TwitterException?) {
                Log.e("Failed", exception.toString());
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityAuthenticate_loginBtn.onActivityResult(requestCode, resultCode, data)
    }

    private fun askUserToEnableLocation() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Location Services")
        builder.setMessage("Please enable Location Services and GPS")
        builder.setPositiveButton(
            "YES"
        ) { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        val alertDialog: Dialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}