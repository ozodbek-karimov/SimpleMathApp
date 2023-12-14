package pl.ozodbek.mathapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SeekBar
import androidx.fragment.app.FragmentManager
import pl.ozodbek.mathapp.FragmentController.Companion.VALUE_KEY
import pl.ozodbek.mathapp.databinding.FragmentDetailControllerBinding

class DetailController : Fragment() {
    private var _binding: FragmentDetailControllerBinding? = null
    private val binding get() = _binding!!
    private var value: Int = 0
    private var i = 0
    private val handler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailControllerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        value = arguments?.getString(VALUE_KEY)?.toInt() ?: 0

        // Set up the ListView
        val listView = view.findViewById<ListView>(R.id.listView)
        val adapter = MultiplicationTableAdapter(requireContext(), value, 1)
        listView.adapter = adapter

        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        seekBar.max = value
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                adapter.setMultiplier(progress + 1)
                binding.sekkbarSize.text = "x${progress + 1}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        handler.post(object : Runnable {
            override fun run() {
                when (i % 4) {
                    0 -> binding.result.text = "Result"
                    1 -> binding.result.text = "Result."
                    2 -> binding.result.text = "Result.."
                    3 -> binding.result.text = "Result..."
                }
                i++
                handler.postDelayed(this, 2000)
            }
        })

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }

}