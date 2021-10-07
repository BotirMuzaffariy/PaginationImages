package uz.lycr.lesson24flowpagination.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.lycr.lesson24flowpagination.R
import uz.lycr.lesson24flowpagination.databinding.ItemImageBinding
import uz.lycr.lesson24flowpagination.databinding.ItemProgressBinding

class ImageAdapter(var listener: MyOnClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_DATA = 1
    val ITEM_LOAD = 0

    var isLoadingAdded = false
    private var list = ArrayList<String>()

    inner class MyVH(var itemBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(url: String) {
            Picasso.get().load(url).placeholder(R.drawable.waiting).error(R.drawable.no_image)
                .into(itemBinding.ivItem)
            itemBinding.ivItem.setOnClickListener { listener.onItemClick(url) }
        }
    }

    inner class ProgressVh(itemBinding: ItemProgressBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface MyOnClick {
        fun onItemClick(url: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_LOAD) {
            return ProgressVh(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return MyVH(
                ItemImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProgressVh) {

        } else if (holder is MyVH) {
            holder.onBind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size - 1 && isLoadingAdded) {
            return ITEM_LOAD
        }
        return ITEM_DATA
    }

    fun addListData(list1: List<String>) {
        list.addAll(list1)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoadingAdded = true
    }

    fun removeLoading() {
        isLoadingAdded = false
    }

}