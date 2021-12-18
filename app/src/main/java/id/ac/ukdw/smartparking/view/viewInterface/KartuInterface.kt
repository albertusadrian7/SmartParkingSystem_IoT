package id.ac.ukdw.smartparking.view.viewInterface

import id.ac.ukdw.smartparking.model.kartu.GetKartuItem

interface KartuInterface {
    fun resultCardSuccess(kartu: List<GetKartuItem>)
    fun resultCardFailed(t: Throwable)
}