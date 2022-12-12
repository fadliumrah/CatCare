package com.fadli.catcare.ui.dashboard.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadli.catcare.R
import com.fadli.catcare.databinding.ActivityGalleryListBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GalleryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryListBinding

    private val storageReference = FirebaseStorage.getInstance().getReference("uploads")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "All Images"
        getAllImage()
    }

    /**
     * Fungsi mengambil semua data pada database
     */
    private fun getAllImage() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val images = storageReference.listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
            }

            withContext(Dispatchers.Main) {
                val animalAdapter = GalleryAdapter(imageUrls)
                if (animalAdapter.itemCount == 0) {
                    binding.textViewNoData.visibility = View.VISIBLE
                }
                binding.progressLoadList.visibility = View.GONE
                binding.recyclerViewImage.apply {
                    adapter = animalAdapter
                    layoutManager = LinearLayoutManager(this@GalleryListActivity)
                }
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                binding.progressLoadList.visibility = View.GONE
                Toast.makeText(this@GalleryListActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}