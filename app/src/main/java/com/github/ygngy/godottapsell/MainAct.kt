package com.github.ygngy.godottapsell

import android.util.Log
import ir.tapsell.plus.TapsellPlus
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo


class MainAct(godot: Godot): GodotPlugin(godot) {

    //todo same as manifest
    override fun getPluginName(): String = "Tapsell"

    override fun getPluginSignals() = mutableSetOf<SignalInfo>().apply{
        addAll(InitListener.signals)
        addAll(RequestListener.signals)
        addAll(ShowListener.signals)
    }

    //todo update
    override fun getPluginMethods() = mutableListOf(
        ::initialize.name,
        ::requestRewardedVideo.name,
        ::requestInterstitial.name,
        ::showRewardedVideo.name,
        ::showInterstitial.name,
            )

    fun notify(signalName: String, vararg signalArgs: Any?) {
        try {
            emitSignal(signalName, *signalArgs)
        }
        catch (e: Exception){
            log("Error on Emit: $signalName", e)
        }
    }

    fun initialize(appId: String, debug: Boolean) {
        debugMode = debug
        if (debug)
            TapsellPlus.setDebugMode(Log.DEBUG)

        TapsellPlus.initialize(godot.requireContext(), appId, InitListener(this))
    }

    fun requestRewardedVideo(zone: String){
        TapsellPlus.requestRewardedVideoAd(godot.requireActivity(), zone,
            RequestListener(this, zone))
    }

    fun requestInterstitial(zone: String){
        TapsellPlus.requestInterstitialAd(godot.requireActivity(), zone,
            RequestListener(this, zone))
    }

    fun showRewardedVideo(id: String){
        TapsellPlus.showRewardedVideoAd(godot.requireActivity(), id, ShowListener(this))
    }

    fun showInterstitial(id: String){
        TapsellPlus.showInterstitialAd(godot.requireActivity(), id, ShowListener(this))
    }

}