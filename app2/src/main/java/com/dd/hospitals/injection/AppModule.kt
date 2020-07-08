package com.dd.hospitals.injection

import android.app.Application
import com.dd.domain.usecase.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun appInjection(application: Application) = Kodein.Module(name = "AppModule") {
    bind<Application>() with singleton { application }

    bind<GetRegionUseCase>() with provider { GetRegionUseCase(instance()) }

    bind<GetHospitalUseCase>() with provider { GetHospitalUseCase(instance()) }

    bind<GetLocalRegionUseCase>() with provider { GetLocalRegionUseCase(instance()) }

    bind<GetLocalSectionUseCase>() with provider { GetLocalSectionUseCase(instance()) }

    bind<GetLocalHospitalUseCase>() with provider { GetLocalHospitalUseCase(instance()) }

    bind<GetLocalHospitalBySectionIdAndRegionIdUseCase>() with provider { GetLocalHospitalBySectionIdAndRegionIdUseCase(instance()) }

    bind<GetLocalHospitalByQueryTextUseCase>() with provider { GetLocalHospitalByQueryTextUseCase(instance()) }

    bind<GetLocalRandomHospitalUseCase>() with provider { GetLocalRandomHospitalUseCase(instance()) }
}