package com.github.ygngy.godottapsell

import ir.tapsell.plus.AdShowListener
import ir.tapsell.plus.model.TapsellPlusAdModel
import ir.tapsell.plus.model.TapsellPlusErrorModel
import org.godotengine.godot.plugin.SignalInfo

class ShowListener(private val main: MainAct): AdShowListener() {
    companion object {
        private const val EMIT_SHOW_OPENED = "tapsell_show_opened"// with id
        private const val EMIT_SHOW_CLOSED = "tapsell_show_closed" // with id
        private const val EMIT_SHOW_REWARDED = "tapsell_show_rewarded"// with id
        private const val EMIT_SHOW_ERROR = "tapsell_show_error" // with id & error message

        val signals = listOf(
            SignalInfo(EMIT_SHOW_OPENED, String::class.java),
            SignalInfo(EMIT_SHOW_CLOSED, String::class.java),
            SignalInfo(EMIT_SHOW_REWARDED, String::class.java),
            SignalInfo(EMIT_SHOW_ERROR, String::class.java, String::class.java),
        )
    }

    override fun onOpened(adModel: TapsellPlusAdModel) {
        super.onOpened(adModel)
        main.notify(EMIT_SHOW_OPENED, adModel.responseId)
        log("Ad Opened")
    }

    override fun onClosed(adModel: TapsellPlusAdModel) {
        super.onClosed(adModel)
        main.notify(EMIT_SHOW_CLOSED, adModel.responseId)
        log("Ad Closed")
    }

    override fun onRewarded(adModel: TapsellPlusAdModel) {
        super.onRewarded(adModel)
        main.notify(EMIT_SHOW_REWARDED, adModel.responseId)
        log("Ad Rewarded")
    }

    override fun onError(adErrorModel: TapsellPlusErrorModel) {
        super.onError(adErrorModel)
        main.notify(EMIT_SHOW_ERROR, adErrorModel.responseId, adErrorModel.errorMessage)
        log("Ad showing Error: ${adErrorModel.errorMessage}")
    }
}