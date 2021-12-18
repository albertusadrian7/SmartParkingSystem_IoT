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
import id.ac.ukdw.smartparking.view.pengelola.PengelolaKartuFragment
import id.ac.ukdw.smartparking.view.pengunjung.PengunjungVoucherFragment


class RiwayatSaldoAdapter: RecyclerView.Adapter<RiwayatSaldoAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_riwayat, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val appCompatActivity = holder.itemView.context as AppCompatActivity
        holder.cvRiwayat.setOnClickListener {
            var bottomFragment = PengunjungVoucherFragment()
            bottomFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
            bottomFragment.show(appCompatActivity.supportFragmentManager, "TAG")
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvRiwayat: CardView = itemView.findViewById(R.id.cvRiwayat)
    }
}