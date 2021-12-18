package id.ac.ukdw.smartparking.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import id.ac.ukdw.smartparking.model.voucher.VoucherItem
import id.ac.ukdw.smartparking.view.pengunjung.PengunjungVoucherFragment
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import android.os.Bundle





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
        val appCompatActivity = holder.itemView.context as AppCompatActivity
        val data = listRiwayatTopUp[position]
        val kodeVoucher = data.kodeVoucher.toString()
        val status = data.status.toString()
        val nominal = data.nominal.toString()
        holder.nominal.text = rupiah(nominal.toDouble())
        holder.status.text = status
        holder.kodeVoucher.text = "Tanggal, kode? klik"
        holder.cvRiwayat.setOnClickListener {
            val arguments = Bundle()
            arguments.putString("kode", kodeVoucher)
            var bottomFragment = PengunjungVoucherFragment()
            bottomFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            bottomFragment.arguments = arguments
            bottomFragment.show(appCompatActivity.supportFragmentManager, "TAG")
        }
    }

    override fun getItemCount(): Int {
        return listRiwayatTopUp.size
    }

    class RiwayatTopUpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nominal: TextView = itemView.findViewById(R.id.tvHargaRiwayat)
        var kodeVoucher: TextView = itemView.findViewById(R.id.tvNamaRiwayat)
        var status: TextView = itemView.findViewById(R.id.tvDurasiRiwayat)
        val cvRiwayat: CardView = itemView.findViewById(R.id.cvRiwayat)
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}