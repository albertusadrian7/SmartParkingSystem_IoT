package id.ac.ukdw.smartparking.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.R
import id.ac.ukdw.smartparking.model.parkir.GetParkingSessionItem
import java.sql.Time
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class RiwayatAdapter(private val listRiwayat: ArrayList<GetParkingSessionItem>): RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {
    fun setData(data : List<GetParkingSessionItem>){
        listRiwayat.clear()
        listRiwayat.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_riwayat, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val data = listRiwayat[position]
        var splitDateTimeOut = data.waktu_keluar.toString().split(" ")
        var splitDateTimeIn = data.waktu_masuk.toString().split(" ")
        var waktuKeluar = splitDateTimeOut[1]
        var waktuMasuk = splitDateTimeIn[1]
        var splitWaktuMasuk = waktuMasuk.split(":")
        var splitWaktuKeluar = waktuKeluar.split(":")
        var tanggal = splitDateTimeOut[0]
        val start = Time(splitWaktuMasuk[0].toInt(), splitWaktuMasuk[1].toInt(), splitWaktuMasuk[2].toInt())
        val stop = Time(splitWaktuKeluar[0].toInt(), splitWaktuKeluar[1].toInt(), splitWaktuKeluar[2].toInt())
        var diff: String = difference(start, stop)

        holder.total.text = rupiah(data.total.toString().toDouble())
        holder.tanggal.text = tglIndonesia(tanggal)
        holder.durasi.text = decideTime(diff)
    }

    override fun getItemCount(): Int {
        return listRiwayat.size
    }

    class RiwayatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var total: TextView = itemView.findViewById(R.id.tvHargaRiwayat)
        var tanggal: TextView = itemView.findViewById(R.id.tvNamaRiwayat)
        var durasi: TextView = itemView.findViewById(R.id.tvDurasiRiwayat)
    }

    private fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

    private fun decideTime(diff: String): String{
        var durasi = ""
        var jam = diff.split(":")[0]
        var menit = diff.split(":")[1]
        var detik = diff.split(":")[2]
        if (jam != "00"){
            durasi = "$jam jam"
        }else if (menit != "00"){
            durasi = "$menit menit"
        } else if (detik != "00"){
            durasi = "$detik detik"
        }
        return durasi
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

    fun difference(start: Time, stop: Time): String {
        val diff = Time(0, 0, 0)

        if (stop.seconds > start.seconds) {
            --start.minutes
            start.seconds += 60
        }

        diff.seconds = start.seconds - stop.seconds
        if (stop.minutes > start.minutes) {
            --start.hours
            start.minutes += 60
        }

        diff.minutes = start.minutes - stop.minutes
        diff.hours = start.hours - stop.hours

        return diff.toString()
    }
}