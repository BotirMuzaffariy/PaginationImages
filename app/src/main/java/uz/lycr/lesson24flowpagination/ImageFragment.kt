package uz.lycr.lesson24flowpagination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import uz.lycr.lesson24flowpagination.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var imgUrl = ""
    private lateinit var binding: FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) imgUrl = requireArguments().getString("img")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)

        Picasso.get().load(imgUrl).placeholder(R.drawable.waiting).error(R.drawable.no_image).into(binding.ivImageF)

        return binding.root
    }

}