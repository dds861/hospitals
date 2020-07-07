package com.dd.hospitals.base

import com.carmabs.ema.android.ui.EmaToolbarFragmentActivity
import com.dd.hospitals.injection.activityInjection
import com.dd.hospitals.R
import org.kodein.di.Kodein


abstract class BaseActivity : EmaToolbarFragmentActivity() {

    override fun injectActivityModule(kodein: Kodein.MainBuilder): Kodein.Module? = activityInjection(this)

    //True if you want to set the Application theme to activity, otherwise it will take EmaTheme.
    //False by default -> EmaTheme
    override val overrideTheme: Boolean = true

    override val layoutId = R.layout.activity_base
}