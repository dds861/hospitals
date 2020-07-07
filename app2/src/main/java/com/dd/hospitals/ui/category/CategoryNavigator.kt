package com.dd.hospitals.ui.category

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.core.navigator.EmaBaseNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.dd.hospitals.base.BaseNavigator
import com.dd.hospitals.R
import com.dd.hospitals.ui.makal.MakalState

class CategoryNavigator(
        override val navController: NavController,
        private val activity: Activity)
    : BaseNavigator<CategoryNavigator.Navigation>() {
    sealed class Navigation : EmaNavigationState {
        class Makal(private val makalState: MakalState) : Navigation() {
            override fun navigateWith(navigator: EmaBaseNavigator<out EmaNavigationState>) {
                val nav = navigator as CategoryNavigator
                nav.toMakal(makalState)
            }
        }
    }

    private fun toMakal(makalState: MakalState) {
        navigateWithAction(R.id.action_categoryViewFragment_to_makalViewFragment,
                addInputState(makalState))
    }
}