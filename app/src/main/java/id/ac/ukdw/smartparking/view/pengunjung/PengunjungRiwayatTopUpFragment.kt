package id.ac.ukdw.smartparking.view.pengunjung

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.smartparking.databinding.FragmentPengunjungRiwayatSaldoBinding
import id.ac.ukdw.smartparking.extentions.UserSession
import id.ac.ukdw.smartparking.model.voucher.VoucherItem
import id.ac.ukdw.smartparking.presenter.ListRiwayatTopUpPresenter
import id.ac.ukdw.smartparking.view.adapter.RiwayatTopUpAdapter
import id.ac.ukdw.smartparking.view.viewInterface.RiwayatTopUpInterface


class PengunjungRiwayatTopUpFragment : Fragment(), RiwayatTopUpInterface {
    private lateinit var binding: FragmentPengunjungRiwayatSaldoBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var rvRiwayatTopUp: RecyclerView
    private lateinit var riwayatTopUpAdapter: RiwayatTopUpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindingView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvRiwayatTopUp()
        ListRiwayatTopUpPresenter(requireActivity(), this).getHistoryTopUpPresenter(getIdUser())
    }

    private fun setRvRiwayatTopUp() {
        riwayatTopUpAdapter = RiwayatTopUpAdapter(arrayListOf())
        rvRiwayatTopUp = binding.rvRiwayatSaldo
        rvRiwayatTopUp.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        rvRiwayatTopUp.adapter = riwayatTopUpAdapter
    }

    private fun bindingView(): View {
        binding = FragmentPengunjungRiwayatSaldoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun showDataRiwayat(riwayat: List<VoucherItem>) {
        updateDataRiwayat(riwayat)
    }

    override fun updateDataRiwayat(riwayat: List<VoucherItem>) {
        riwayatTopUpAdapter.setData(riwayat as ArrayList<VoucherItem>)
    }

    override fun resultSuccess(result: List<VoucherItem>) {
        showDataRiwayat(result)
    }

    override fun resultFailed(t: Throwable) {
        Toast.makeText(requireContext(),"Pesan: $t", Toast.LENGTH_LONG).show()
    }

    private fun getIdUser(): String {
        val preferences = UserSession(requireActivity())
        val idUser = preferences.getValueString(UserSession.SHARED_PREFERENCE_ID_KEY)
        Log.d(ContentValues.TAG,idUser)
        return idUser
    }

}