package com.fadli.catcare.ui.dashboard.gallery

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fadli.catcare.R
import com.fadli.catcare.databinding.ActivityGalleryBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


private const val REQUEST_CODE_PICK_IMAGE = 72

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var title : String
    private var imageUri : Uri? = null
    private val storageReference = FirebaseStorage.getInstance().getReference("uploads")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Upload Image"
        initAction()
        setImageViewHome()
    }

    private fun initAction() {
        binding.buttonSelectImage.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
            }
        }

        binding.buttonUploadImage.setOnClickListener {
            title = binding.editTextTitle.text.toString().trim()
            if (imageUri != null) {
                if (title.isBlank() || title.isEmpty()){
                    binding.inputTextTitle.error = "Required*"
                    Toast.makeText(this, "Name is Required!", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBarLoadingIndicator.isIndeterminate = false
                    binding.progressBarLoadingIndicator.visibility = View.VISIBLE
                    binding.textViewIndicatorLoading.visibility = View.VISIBLE
                    binding.inputTextTitle.error = null
                    uploadImage(title)
                }
            } else {
                Toast.makeText(this, "Select Image!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonUploadImage.setOnClickListener {
            title = binding.editTextTitle.text.toString().trim()
            if (imageUri != null) {
                if (title.isBlank() || title.isEmpty()){
                    binding.inputTextTitle.error = "Required*"
                    Toast.makeText(this, "Name is Required!", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBarLoadingIndicator.isIndeterminate = false
                    binding.progressBarLoadingIndicator.visibility = View.VISIBLE
                    binding.textViewIndicatorLoading.visibility = View.VISIBLE
                    binding.inputTextTitle.error = null
                    uploadImage(title)
                }
            } else {
                Toast.makeText(this, "Select Image!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonDownloadImage.setOnClickListener {
            title = binding.editTextTitle.text.toString().trim()
            if (title.isBlank() || title.isEmpty()){
                binding.inputTextTitle.error = "Required*"
                Toast.makeText(this, "Name isRequired!", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBarLoadingIndicator.isIndeterminate = true
                binding.progressBarLoadingIndicator.visibility = View.VISIBLE
                binding.inputTextTitle.error = null
                downloadImage(title)
            }
        }

        binding.buttonDeleteImage.setOnClickListener {
            title = binding.editTextTitle.text.toString().trim()
            if (title.isBlank() || title.isEmpty()){
                binding.inputTextTitle.error = "Required*"
                Toast.makeText(this, "Name isRequired!", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBarLoadingIndicator.isIndeterminate = true
                binding.progressBarLoadingIndicator.visibility = View.VISIBLE
                binding.inputTextTitle.error = null
                deleteImage(title)
            }
        }

        binding.buttonShowAllImage.setOnClickListener {
            startActivity(Intent(this, GalleryListActivity::class.java))
        }
    }

    private fun setImageViewHome() {
        binding.imageViewHome.load(ContextCompat.getDrawable(this, R.drawable.shape)){
            crossfade(true)
            crossfade(500)
            transformations(RoundedCornersTransformation(10F))
        }
    }

    private fun resetLayout() {
        setImageViewHome()
        imageUri = null
        binding.inputTextTitle.error = null
        binding.editTextTitle.text?.clear()
        binding.progressBarLoadingIndicator.visibility = View.GONE
        binding.textViewIndicatorLoading.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            data?.data?.let {
                imageUri = it
                binding.imageViewHome.load(imageUri){
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }
    }

    private fun uploadImage(animalName : String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            imageUri?.let { uri ->
                storageReference.child(animalName).putFile(uri)
                    .addOnProgressListener {
                        val progress: Int = ((100 * it.bytesTransferred) / it.totalByteCount).toInt()
                        binding.progressBarLoadingIndicator.progress = progress
                        val indicator = "Loading... $progress%"
                        binding.textViewIndicatorLoading.text = indicator
                    }.await()

                withContext(Dispatchers.Main){
                    Toast.makeText(this@GalleryActivity , "Success Uploaded", Toast.LENGTH_LONG).show()
                    delay(3000L)
                    resetLayout()
                }
            }
        } catch (e : Exception) {
            withContext(Dispatchers.Main){
                Toast.makeText(this@GalleryActivity , e.message, Toast.LENGTH_LONG).show()
                resetLayout()
            }
        }
    }

    private fun downloadImage(animalName: String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val maxDownloadSize = 5L * 1024 * 1024
            val bytes = storageReference.child(animalName).getBytes(maxDownloadSize).await()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            withContext(Dispatchers.Main) {
                binding.imageViewHome.load(bitmap){
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(10F))
                }
                binding.progressBarLoadingIndicator.visibility = View.GONE
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                binding.inputTextTitle.error = e.message
                binding.progressBarLoadingIndicator.visibility = View.GONE
                setImageViewHome()
            }
        }
    }

    private fun deleteImage(animalName: String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            storageReference.child(animalName).delete().await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@GalleryActivity, "Successfully deleted image.",
                    Toast.LENGTH_LONG).show()
                resetLayout()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                binding.progressBarLoadingIndicator.visibility = View.GONE
                binding.inputTextTitle.error = e.message
            }
        }
    }
}