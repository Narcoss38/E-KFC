package fr.neon.e_kfc_v2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import fr.neon.e_kfc_v2.MainViewModel
import fr.neon.e_kfc_v2.Model
import fr.neon.e_kfc_v2.R
import fr.neon.e_kfc_v2.databinding.FragmentHomeBinding
import fr.neon.e_kfc_v2.ui.feed.FeedFragment
import fr.neon.e_kfc_v2.ui.temp.TempFragment
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: MainViewModel by activityViewModels()

    var arrayList: ArrayList<Model> = ArrayList()
    private val client = OkHttpClient()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.selectedTemp.observe(viewLifecycleOwner, Observer {
            binding.thermostat.text = getString(R.string.thermostat, it)
        })

        homeViewModel.actualTemp.observe(viewLifecycleOwner, Observer {
            binding.TempNow.text = getString(R.string.temperature, it)
        })

        binding.imageView2.setOnClickListener {
            val fragmentManager = fragmentManager?.beginTransaction()
            fragmentManager?.replace(R.id.nav_host_fragment_activity_main, TempFragment())
            fragmentManager?.disallowAddToBackStack()
            fragmentManager?.commit()
        }

        binding.imageView3.setOnClickListener {
            val fragmentManager = fragmentManager?.beginTransaction()
            fragmentManager?.replace(R.id.nav_host_fragment_activity_main, FeedFragment())
            fragmentManager?.disallowAddToBackStack()
            fragmentManager?.commit()
        }

        binding.imageView4.setOnClickListener {
            val fragmentManager = fragmentManager?.beginTransaction()
            fragmentManager?.replace(R.id.nav_host_fragment_activity_main, FeedFragment())
            fragmentManager?.disallowAddToBackStack()
            fragmentManager?.commit()
        }

        binding.imageView.setOnClickListener {
            run("http://192.168.93.197")
        }


        return root
    }

    override fun onResume() {
        super.onResume()

    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()

                val json: JSONObject = JSONObject(str_response)

                var jsonArray: JSONArray = json.getJSONArray("temperature")
                var size: Int = jsonArray.length()
                arrayList = ArrayList()
                for (i in 0 until size) {
                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    var model: Model = Model()
                    model.temperature = jsonObject.getInt("temperature").toDouble()
                    arrayList.add(model)

                }

                homeViewModel.setActualTemp(arrayList.last().temperature)

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}