package id.ac.ukdw.smartparking.view.viewInterface

import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem

interface RiwayatInterface {
    fun showDataRiwayat(riwayat: List<GetParkingSessionItem>)
    fun updateDataRiwayat(riwayat: List<GetParkingSessionItem>)
    fun resultSuccess(result: List<GetParkingSessionItem>)
    fun resultFailed(t: Throwable)
}