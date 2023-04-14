package com.github.ygngy.godottapsell

import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.model.TapsellPlusAdModel
import org.godotengine.godot.plugin.SignalInfo

class RequestListener(private val main: MainAct, private val zone: String): AdRequestCallback() {
    companion object {
        private const val EMIT_REQUEST_SUCCEED = "tapsell_request_succeed"// with zone & responseId
        private const val EMIT_REQUEST_FAILED = "tapsell_request_failed" // with zone & error message

        val signals = listOf(
            SignalInfo(EMIT_REQUEST_SUCCEED, String::class.java, String::class.java),
            SignalInfo(EMIT_REQUEST_FAILED, String::class.java, String::class.java)
        )
    }


    override fun response(adModel: TapsellPlusAdModel) {
        super.response(adModel)
        main.notify(EMIT_REQUEST_SUCCEED, zone, adModel.responseId)
        log("Ad Request done")
    }

    override fun error(message: String?) {
        super.error(message)
        val msg = message ?: "Ad Request Error"
        main.notify(EMIT_REQUEST_FAILED, zone, msg)
        log("Ad Request Error: $msg")
    }
}