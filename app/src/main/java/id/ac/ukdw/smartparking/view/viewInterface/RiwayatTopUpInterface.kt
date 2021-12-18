package id.ac.ukdw.smartparking.view.viewInterface

import id.ac.ukdw.smartparking.model.voucher.VoucherItem

interface RiwayatTopUpInterface {
    fun showDataRiwayat(riwayat: List<VoucherItem>)
    fun updateDataRiwayat(riwayat: List<VoucherItem>)
    fun resultSuccess(result: List<VoucherItem>)
    fun resultFailed(t: Throwable)
}