package com.mashup.latte.ui.record

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.record.adapter.RecordImageRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_record_image.*
import java.io.File
import java.io.FileOutputStream


/**
 * Created by Namget on 2019.11.23.
 */
class RecordImageFragment : Fragment() {

    private lateinit var recordImageRecyclerViewAdapter: RecordImageRecyclerViewAdapter


    private val imageSelected: (Uri) -> Unit = {
        txtUploadImageSelect.load(it)
    }

    private val takePicture: () -> Unit = {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        initMultipleSelectable()
    }

    private fun initRecyclerView() {
        recordImageRecyclerViewAdapter =
            RecordImageRecyclerViewAdapter(requireContext(), imageSelected, takePicture)

        recyclerViewImage.apply {
            setHasFixedSize(true)
            adapter = recordImageRecyclerViewAdapter
        }
        recordImageRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageUri = data?.extras?.get("data") as Bitmap // bitmap에 대한 처리 필요
//            imageSelected(imageUri)
            txtUploadImageSelect.load(imageUri)
        }

    }

    private fun initMultipleSelectable() {
//        imgMultiSelcectEnabled.setOnClickListener {
//            it.isSelected = !it.isSelected
//            recordImageRecyclerViewAdapter.clearSelectedUri()
//        }
    }

    fun giveImageData(){

        val uriList = recordImageRecyclerViewAdapter.getUriList()
        for(i in uriList.indices) {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        requireContext().contentResolver,
                        uriList[0]
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uriList[0])
            }
            SaveImage(bitmap,i)
        }
    }

    private fun SaveImage(finalBitmap: Bitmap, index : Int) {
        val file = File(context?.filesDir, "image_${System.currentTimeMillis()}_${index}.jpg")

        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    companion object {

        const val REQUEST_IMAGE_CAPTURE = 100
        private lateinit var recordImageFragment: RecordImageFragment
        fun newInstance(): RecordImageFragment {
            synchronized(RecordImageFragment::class) {
                recordImageFragment = RecordImageFragment()
                val args = Bundle()
                recordImageFragment.arguments = args
                return recordImageFragment
            }
        }

        var MAX_IMAGE_SELECTABLE = 1
    }


}