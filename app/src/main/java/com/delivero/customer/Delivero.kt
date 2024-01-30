package com.delivero.customer

import android.app.Application
import com.delivero.customer.helpers.MapUtils
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.Token
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class Delivero:Application() {
    override fun onCreate() {
        super.onCreate()
        MapUtils.setUpGeoApiContext()
        setUpRemoteConfig()

    }

    private fun setUpRemoteConfig() {
        val config = Firebase.remoteConfig
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        config.setConfigSettingsAsync(settings)
        config.setDefaultsAsync(R.xml.remote_config_defaults)
        config.fetchAndActivate()
    }




}