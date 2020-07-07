package com.dd.hospitals.injection

import android.app.Activity
import androidx.navigation.NavController
import com.carmabs.ema.android.ui.EmaFragmentActivity
import com.dd.hospitals.ui.category.CategoryNavigator
import com.dd.hospitals.ui.main.HomeNavigator
import com.dd.hospitals.ui.main.MainToolbarsViewModel
import com.dd.hospitals.ui.makal.MakalNavigator
import com.dd.hospitals.ui.search.SearchNavigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun activityInjection(activity: Activity) = Kodein.Module(name = "ActivityModule") {
    bind<Activity>() with singleton { activity }

    bind<NavController>() with singleton { (activity as EmaFragmentActivity).let { it.navController } }

    bind<MainToolbarsViewModel>() with provider { MainToolbarsViewModel() }

    bind<HomeNavigator>() with singleton { HomeNavigator(instance(), instance()) }

    bind<CategoryNavigator>() with singleton { CategoryNavigator(instance(), instance()) }

    bind<MakalNavigator>() with singleton { MakalNavigator(instance(), instance()) }

    bind<SearchNavigator>() with singleton { SearchNavigator(instance(), instance()) }
}