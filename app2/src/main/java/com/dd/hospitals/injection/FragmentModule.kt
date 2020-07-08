package com.dd.hospitals.injection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.carmabs.ema.android.ui.dialog.EmaBaseDialogProvider
import com.dd.hospitals.DIALOG_TAG_LOADING
import com.dd.hospitals.DIALOG_TAG_SIMPLE
import com.dd.hospitals.dialog.loading.LoadingDialogProvider
import com.dd.hospitals.dialog.simple.SimpleDialogProvider
import com.dd.hospitals.ui.makal.HospitalViewModel
import com.dd.hospitals.ui.region.RegionViewModel
import com.dd.hospitals.ui.search.SearchViewModel
import com.dd.hospitals.ui.section.SectionViewModel
import com.dd.hospitals.ui.widget.WidgetViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun fragmentInjection(fragment: Fragment) = Kodein.Module(name = "FragmentModule") {
    bind<Fragment>() with singleton { fragment }

    bind<FragmentManager>() with singleton { fragment.requireActivity().supportFragmentManager }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_SIMPLE) with provider { SimpleDialogProvider(instance()) }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_LOADING) with provider { LoadingDialogProvider(instance()) }

    bind<RegionViewModel>() with provider { RegionViewModel(instance(), instance()) }

    bind<SectionViewModel>() with provider { SectionViewModel(instance(), instance()) }

    bind<HospitalViewModel>() with provider { HospitalViewModel(instance(), instance()) }

    bind<SearchViewModel>() with provider { SearchViewModel(instance(), instance(), instance()) }

    bind<WidgetViewModel>() with provider { WidgetViewModel(instance()) }
}