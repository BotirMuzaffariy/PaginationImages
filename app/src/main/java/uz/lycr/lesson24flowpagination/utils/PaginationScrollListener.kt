package uz.lycr.lesson24flowpagination.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var manager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val childCount = manager.childCount
        val itemCount = manager.itemCount
        val findFirstVisibleItemPosition = manager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((childCount + findFirstVisibleItemPosition) >= itemCount && findFirstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }

    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean

}