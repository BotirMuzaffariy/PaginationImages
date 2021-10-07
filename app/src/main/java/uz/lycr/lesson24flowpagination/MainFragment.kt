package uz.lycr.lesson24flowpagination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import uz.lycr.lesson24flowpagination.adapters.TabPageAdapter
import uz.lycr.lesson24flowpagination.databinding.FragmentMainBinding
import uz.lycr.lesson24flowpagination.databinding.ItemTabBinding
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var list: ArrayList<String>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        loadData()

        binding.vpMainF.adapter = TabPageAdapter(requireActivity(), list)
        TabLayoutMediator(binding.tlMainF, binding.vpMainF) { _, _ -> }.attach()

        setTabs()

        return binding.root
    }

    private fun loadData() {
        list = ArrayList()
        list.add("Nature")
        list.add("Cars")
        list.add("Animals")
        list.add("Technology")
        list.add("Horses")
        list.add("Foods")
        list.add("Fruits")
        list.add("Vegetables")
        list.add("Architecture")
        list.add("Flowers")
    }

    private fun setTabs() {
        val tabCount = binding.tlMainF.tabCount

        for (i in 0 until tabCount) {
            val itemBinding = ItemTabBinding.inflate(layoutInflater)

            itemBinding.tv.text = list[i]
            binding.tlMainF.getTabAt(i)?.customView = itemBinding.root
        }
    }

}