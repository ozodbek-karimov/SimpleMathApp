package pl.ozodbek.mathapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pl.ozodbek.mathapp.databinding.FragmentContrrollerBinding

class FragmentController : Fragment() {
    private var _binding: FragmentContrrollerBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val VALUE_KEY = "value"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContrrollerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendValue.setOnClickListener {
            val value = binding.actionText.text.toString()
            if (value.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString(VALUE_KEY, value)
                }
                val detailControllerFragment = DetailController()
                detailControllerFragment.arguments = bundle


                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, detailControllerFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Please enter a value", Toast.LENGTH_SHORT).show()
            }
        }
    }
}