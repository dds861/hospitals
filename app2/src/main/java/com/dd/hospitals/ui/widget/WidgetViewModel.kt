package com.dd.hospitals.ui.widget

import android.content.Context
import com.dd.domain.usecase.GetLocalRandomHospitalUseCase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class WidgetViewModel(private val context: Context) : KodeinAware {
    /**
     * Default variables
     */
    override val kodein: Kodein by closestKodein(context)

    /**
     * Custom variables
     */
    private val getLocalRandomHospitalUseCase: GetLocalRandomHospitalUseCase by instance()

    /**
     * Custom functions
     */
    suspend fun getRandomMakal(): String {

        val randomMakal = getLocalRandomHospitalUseCase.execute(Unit)
        return randomMakal.randomHospital
    }
}