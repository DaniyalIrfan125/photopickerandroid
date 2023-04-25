package com.daniyal.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.daniyal.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pickMultipleImageMediaRequest: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickSingleImageMediaRequest: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialising()
        clickListeners()


    }

    //method for clicklisteners
    private fun clickListeners() {
        binding.buttonSingleImage.setOnClickListener {
            pickSingleImageMediaRequest.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.buttonImageVideo.setOnClickListener {
            pickMultipleImageMediaRequest.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        }

        binding.buttonMultipleImages.setOnClickListener {
            pickMultipleImageMediaRequest.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    //method for initialising
    private fun initialising() {
        pickMultipleImageMediaRequest = registerForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(
                5
            )
        ) { uris ->
            // Process URIs
            Toast.makeText(this, "Uris Count " + uris.size.toString(), Toast.LENGTH_SHORT).show()
            Log.d("Photo Picker URIs count", uris.size.toString())
        }

        pickSingleImageMediaRequest =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                Toast.makeText(this, "Uri " + uri.toString(), Toast.LENGTH_SHORT).show()

                Log.d("Photo Picker URI ", uri.toString())
            }

    }
}