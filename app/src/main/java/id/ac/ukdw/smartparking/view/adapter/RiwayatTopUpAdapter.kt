package id.ac.ukdw.smartparking.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.model.voucher.VoucherItem
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class RiwayatTopUpAdapter(private val listRiwayatTopUp: ArrayList<VoucherItem>): RecyclerView.Adapter<RiwayatTopUpAdapter.RiwayatTopUpViewHolder>() {
    fun setData(data : List<VoucherItem>){
        listRiwayatTopUp.clear()
        listRiwayatTopUp.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTopUpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_riwayat, parent, false)
        return RiwayatTopUpViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatTopUpViewHolder, position: Int) {
        val data = listRiwayatTopUp[position]
        val kodeVoucher = data.kodeVoucher.toString()
        val status = data.status.toString()
        val nominal = data.nominal.toString()
        holder.nominal.text = rupiah(nominal.toDouble())
        holder.status.text = status
        holder.kodeVoucher.text = kodeVoucher
    }

    override fun getItemCount(): Int {
        return listRiwayatTopUp.size
    }

    class RiwayatTopUpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nominal: TextView = itemView.findViewById(R.id.tvHargaRiwayat)
        var kodeVoucher: TextView = itemView.findViewById(R.id.tvNamaRiwayat)
        var status: TextView = itemView.findViewById(R.id.tvDurasiRiwayat)
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}