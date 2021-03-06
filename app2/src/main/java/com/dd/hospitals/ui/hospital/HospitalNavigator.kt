package com.dd.hospitals.ui.hospital

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.hospitals.base.BaseNavigator

class HospitalNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<HospitalNavigator.Navigation>() {
    sealed class Navigation : EmaNavigationState {

    }
}