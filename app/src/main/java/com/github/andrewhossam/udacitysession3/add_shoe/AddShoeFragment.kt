package com.github.andrewhossam.udacitysession3.add_shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.andrewhossam.udacitysession3.R
import com.github.andrewhossam.udacitysession3.database.ShoeDatabase
import com.github.andrewhossam.udacitysession3.databinding.FragmentAddShoeBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddShoeFragment : Fragment() {
    private val viewModel: AddShoeViewModel by viewModels {
        AddShoeViewModel.Companion.AddShoeViewModelFactory(ShoeDatabase.getInstance(requireContext()).shoeDatabaseDao)
    }

    private var _binding: FragmentAddShoeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentAddShoeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shoeAdded.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}