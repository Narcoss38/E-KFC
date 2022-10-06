package fr.neon.e_kfc_v2.ui.time

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.neon.e_kfc_v2.R
import fr.neon.e_kfc_v2.databinding.FragmentTimeBinding

class TimeFragment : Fragment() {

    private var _binding: FragmentTimeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[TimeViewModel::class.java]

        _binding = FragmentTimeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.hours_morning,
            android.R.layout.simple_spinner_item
        ) .also { morningAdapter ->
            morningAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerLundiOpen.adapter = morningAdapter
            binding.spinnerMardiOpen.adapter = morningAdapter
            binding.spinnerMercrediOpen.adapter = morningAdapter
            binding.spinnerJeudiOpen.adapter = morningAdapter
            binding.spinnerVendrediOpen.adapter = morningAdapter
            binding.spinnerSamediOpen.adapter = morningAdapter
            binding.spinnerDimancheOpen.adapter = morningAdapter
        }

        ArrayAdapter.createFromResource(
            requireActivity().applicationContext,
            R.array.hours_afternoon,
            android.R.layout.simple_spinner_item
        ) .also { afternoonAdapter ->
            afternoonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerLundiClose.adapter = afternoonAdapter
            binding.spinnerMardiClose.adapter = afternoonAdapter
            binding.spinnerMercrediClose.adapter = afternoonAdapter
            binding.spinnerJeudiClose.adapter = afternoonAdapter
            binding.spinnerVendrediClose.adapter = afternoonAdapter
            binding.spinnerSamediClose.adapter = afternoonAdapter
            binding.spinnerDimancheClose.adapter = afternoonAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}