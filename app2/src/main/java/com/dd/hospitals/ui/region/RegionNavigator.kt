package com.dd.hospitals.ui.region

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.hospitals.base.BaseNavigator
import com.dd.hospitals.R
import com.dd.hospitals.ui.hospital.HospitalState

class RegionNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<RegionNavigator.Navigation>() {
    sealed class Navigation : EmaNavigationState {
        class Makal(private val hospitalState: HospitalState) : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as RegionNavigator
                nav.toMakal(hospitalState)
            }
        }
    }

    private fun toMakal(hospitalState: HospitalState) {
        navigateWithAction(R.id.action_categoryViewFragment_to_makalViewFragment,
                addInputState(hospitalState))
    }
}