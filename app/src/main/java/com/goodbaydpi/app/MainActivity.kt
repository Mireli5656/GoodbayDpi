package com.goodbaydpi.app

import android.app.Activity
import android.net.VpnService
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var statusText: TextView
    private lateinit var connectButton: Button

    companion object {
        private const val VPN_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        connectButton = findViewById(R.id.connectButton)

        connectButton.setOnClickListener {
            requestVpnPermission()
        }

        statusText.text =
            "Hazırdır. Tunel mühərriki hələ inteqrasiya edilməyib."
    }

    private fun requestVpnPermission() {
        val intent = VpnService.prepare(this)

        if (intent != null) {
            startActivityForResult(intent, VPN_REQUEST_CODE)
        } else {
            showPermissionGranted()
        }
    }

    @Deprecated("Kept for compatibility with the initial implementation")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: android.content.Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VPN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                showPermissionGranted()
            } else {
                statusText.text = "VPN icazəsi verilmədi."
            }
        }
    }

    private fun showPermissionGranted() {
        statusText.text =
            "VPN icazəsi verilib. Tunel hələ qoşulmayıb."
        connectButton.text = "VPN icazəsi verildi"
        connectButton.isEnabled = false
    }
}

