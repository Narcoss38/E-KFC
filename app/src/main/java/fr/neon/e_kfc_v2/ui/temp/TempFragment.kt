package fr.neon.e_kfc_v2.ui.temp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import fr.neon.e_kfc_v2.MainViewModel
import fr.neon.e_kfc_v2.R
import fr.neon.e_kfc_v2.databinding.FragmentTempBinding

class TempFragment : Fragment() {

    private var _bindind: FragmentTempBinding? = null

    private val binding get() = _bindind!!

    private val tempViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _bindind = FragmentTempBinding.inflate(inflater, container, false)
        val root: View = binding.root



        tempViewModel.selectedTemp.observe(viewLifecycleOwner, Observer {
            binding.NeededTemp.text = getString(R.string.temperature, it)
        })

        tempViewModel.actualTemp.observe(viewLifecycleOwner, Observer {
            binding.ActualTemp.text = getString(R.string.temperature, it)
        })

        binding.floatingActionButtonPlus.setOnClickListener {
            tempViewModel.sentPlusSelectedTemp(1)
        }

        binding.floatingActionButtonMinus.setOnClickListener {
            tempViewModel.sentMinusSelectedTemp(1)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindind = null
    }

}