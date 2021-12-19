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
        val dateTime = data.tanggal.toString()
        val tanggal = dateTime.split(" ")[0]
        holder.nominal.text = rupiah(nominal.toDouble())
        if (status == "true"){
            holder.status.text = "Sudah digunakan"
        } else {
            holder.status.text = "Belum digunakan"
        }
        holder.tglVoucher.text = tglIndonesia(tanggal)
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
        var tglVoucher: TextView = itemView.findViewById(R.id.tvNamaRiwayat)
        var status: TextView = itemView.findViewById(R.id.tvDurasiRiwayat)
        val cvRiwayat: CardView = itemView.findViewById(R.id.cvRiwayat)
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

    fun tglIndonesia (tgl: String): String{
        val tgl = tgl.split("-")
        val bulan = when(tgl[1]){
            "01" -> "Januari"
            "02" -> "Februari"
            "03" -> "Maret"
            "04" -> "April"
            "05" -> "Mei"
            "06" -> "Juni"
            "07" -> "Juli"
            "08" -> "Agustus"
            "09" -> "September"
            "10" -> "Oktober"
            "11" -> "November"
            "12" -> "Desember"
            else -> "Terjadi kesalahan"
        }
        var tglIndonesia = "${tgl[2]} $bulan ${tgl[0]}"
        return tglIndonesia
    }
}