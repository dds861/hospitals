package com.dd.hospitals.dialog.simple

import com.carmabs.ema.core.dialog.EmaDialogListener

interface SimpleDialogListener : EmaDialogListener {
    fun onCancelClicked()
    fun onConfirmClicked()

}