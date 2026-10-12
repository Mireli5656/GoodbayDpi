
package com.goodbaydpi.app.vpn

import android.content.Intent
import android.net.VpnService
import android.util.Log

/**
 * VPN service registration for future tunnel integration.
 *
 * This initial version intentionally does not establish a VPN interface.
 * No traffic is routed or intercepted by this service.
 */
class GoodbayVpnService : VpnService() {

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        Log.w(
            "GoodbayDPI",
            "Tunnel engine is not integrated. No VPN connection established."
        )

        stopSelf(startId)
        return START_NOT_STICKY
    }
}
