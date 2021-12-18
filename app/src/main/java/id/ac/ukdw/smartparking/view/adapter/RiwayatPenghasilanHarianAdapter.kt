package id.ac.ukdw.smartparking.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firestore.v1.Value
import id.ac.ukdw.smartparking.R
import java.security.Key
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class RiwayatPenghasilanHarianAdapter(private val listPenghasilan: ArrayList<Penghasilan>): RecyclerView.Adapter<RiwayatPenghasilanHarianAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_riwayat, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listPenghasilan[position]

        holder.durasiRiwayat.text = currentItem.durasi.toString() + " Jam"
        holder.namaRiwayat.text = currentItem.tanggal.toString()
        holder.hargaRiwayat.text = rupiah(currentItem.total.toString().toDouble())
    }

    override fun getItemCount(): Int {
        return listPenghasilan.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val namaRiwayat: TextView = itemView.findViewById(R.id.tvNamaRiwayat)
        val hargaRiwayat: TextView = itemView.findViewById(R.id.tvHargaRiwayat)
        val durasiRiwayat: TextView = itemView.findViewById(R.id.tvDurasiRiwayat)
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}