package uz.lycr.lesson24flowpagination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.lycr.lesson24flowpagination.adapters.ImageAdapter
import uz.lycr.lesson24flowpagination.databinding.FragmentPageBinding
import uz.lycr.lesson24flowpagination.models.UnsplashImage
import uz.lycr.lesson24flowpagination.retrofit.ApiClient
import uz.lycr.lesson24flowpagination.utils.PaginationScrollListener
import kotlin.collections.ArrayList

class PageFragment : Fragment() {

    private var mParam1 = ""
    private val totalPage = 10
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    private lateinit var adapter: ImageAdapter
    private lateinit var binding: FragmentPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)

        adapter = ImageAdapter(object : ImageAdapter.MyOnClick {
            override fun onItemClick(url: String) {
                val bundle = Bundle()
                bundle.putString("img", url)
                findNavController().navigate(R.id.imageFragment, bundle)
            }
        })

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        binding.rvItem.addOnScrollListener(object : PaginationScrollListener(gridLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                loadPageData()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }
        })

        binding.rvItem.layoutManager = gridLayoutManager
        binding.rvItem.adapter = SlideInLeftAnimationAdapter(adapter).apply {
            setDuration(800)
            setInterpolator(OvershootInterpolator())
            setFirstOnly(false)
        }

        loadPageData()

        return binding.root
    }

    private fun loadPageData() {
        ApiClient.apiService.searchUnsplashImage(mParam1, currentPage)
            .enqueue(object : Callback<UnsplashImage> {
                override fun onResponse(
                    call: Call<UnsplashImage>,
                    response: Response<UnsplashImage>
                ) {
                    if (response.isSuccessful) {
                        binding.pbLoad.visibility = View.INVISIBLE
                        val list2 = ArrayList<String>()
                        response.body()?.results?.forEach { i ->
                            list2.add(i.urls.small)
                        }
                        adapter.addListData(list2)
                    }

                    if (currentPage != 1) {
                        adapter.removeLoading()
                    }

                    if (currentPage < totalPage) {
                        adapter.addLoading()
                    } else {
                        isLastPage = true
                    }
                    isLoading = false

                }

                override fun onFailure(call: Call<UnsplashImage>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    companion object {
        private const val ARG_PARAM1 = "type"
        fun newInstance(param1: String): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}