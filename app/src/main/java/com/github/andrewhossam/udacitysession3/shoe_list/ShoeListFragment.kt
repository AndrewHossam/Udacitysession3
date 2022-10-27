package com.github.andrewhossam.udacitysession3.shoe_list

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.github.andrewhossam.udacitysession3.R
import com.github.andrewhossam.udacitysession3.database.ShoeDatabase
import com.github.andrewhossam.udacitysession3.databinding.FragmentShoeListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoeListFragment : Fragment() {
    private val viewModel: ShoeListViewModel by viewModels {
        ShoeListViewModel.Companion.ShoeListViewModelFactory(ShoeDatabase.getInstance(requireContext()).shoeDatabaseDao)
    }

    private var _binding: FragmentShoeListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        addMenu()
        return binding.root

    }

    private fun addMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_delete -> {
                        viewModel.delete()
                        true
                    }
                    else -> false
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allShoes.observe(viewLifecycleOwner) { list ->
//            binding.rv.adapter = ShoeAdapter().also {
//                it.data = list
//            }
            binding.rv.adapter = ShoeListAdapter().also {
                it.submitList(list)
            }
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_addShoeFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}