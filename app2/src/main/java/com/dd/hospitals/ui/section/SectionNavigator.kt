package com.dd.hospitals.ui.section

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.hospitals.R
import com.dd.hospitals.base.BaseNavigator
import com.dd.hospitals.ui.region.RegionState

class SectionNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<SectionNavigator.Navigation>() {
    sealed class Navigation : EmaNavigationState {
        class ToRegion(private val state: RegionState) : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as SectionNavigator
                nav.toRegion(state)
            }
        }
    }

    private fun toRegion(state: RegionState) {
        navigateWithAction(R.id.action_sectionViewFragment_to_categoryViewFragment,
                addInputState(state))
    }
}