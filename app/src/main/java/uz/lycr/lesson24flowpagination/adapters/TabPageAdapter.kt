package uz.lycr.lesson24flowpagination.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.lycr.lesson24flowpagination.PageFragment

class TabPageAdapter(f: FragmentActivity, var list: List<String>) : FragmentStateAdapter(f) {

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(list[position])
    }

}