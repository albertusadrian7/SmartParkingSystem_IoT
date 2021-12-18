package id.ac.ukdw.smartparking.view.viewInterface

import android.view.View
import id.ac.ukdw.smartparking.model.kartu.GetKartuItem

interface KartuInterface {
    fun resultCardSuccess(kartu: List<GetKartuItem>, view: View)
    fun resultCardFailed(t: Throwable)
}