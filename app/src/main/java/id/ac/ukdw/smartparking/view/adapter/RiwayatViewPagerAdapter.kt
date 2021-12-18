package id.ac.ukdw.smartparking.view.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val TAG = "RIWAYAT VIEW PAGER ADAPTER"
class RiwayatViewPagerAdapter(private val listFragment: ArrayList<Fragment>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}