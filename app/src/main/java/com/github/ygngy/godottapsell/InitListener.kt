package com.github.ygngy.godottapsell

import ir.tapsell.plus.TapsellPlusInitListener
import ir.tapsell.plus.model.AdNetworkError
import ir.tapsell.plus.model.AdNetworks
import org.godotengine.godot.plugin.SignalInfo

class InitListener(private val main: MainAct): TapsellPlusInitListener {
    companion object {
        private const val EMIT_INIT_SUCCEED = "tapsell_init_succeed"
        private const val EMIT_INIT_FAILED = "tapsell_init_failed" // with error domain and error message

        val signals = listOf(
            SignalInfo(EMIT_INIT_SUCCEED),
            SignalInfo(EMIT_INIT_FAILED, String::class.java, String::class.java)
        )
    }

    override fun onInitializeSuccess(adNetworks: AdNetworks?) {
        main.notify(EMIT_INIT_SUCCEED)
        log("Initialize Succeed")
    }

    override fun onInitializeFailed(adNetworks: AdNetworks?,
                                    adNetworkError: AdNetworkError?) {
        val errorDomain = adNetworkError?.errorDomain ?: ""
        val errorMessage = adNetworkError?.errorMessage ?: ""

        main.notify(EMIT_INIT_FAILED, errorDomain, errorMessage)

        log("Initialize Failed! - error domain-> "
                + errorDomain
                + " - error message-> "
                + errorMessage
        )
    }


}