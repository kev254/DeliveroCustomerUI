package com.delivero.customer.home

import android.Manifest
import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.delivero.customer.R
import com.delivero.customer.databinding.FragmentProfileBinding
import com.delivero.customer.helpers.Preference
import com.delivero.customer.helpers.toast
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.jianastrero.capiche.iNeed
import com.nguyenhoanglam.imagepicker.model.GridCount
import com.nguyenhoanglam.imagepicker.model.ImagePickerConfig
import com.nguyenhoanglam.imagepicker.ui.imagepicker.registerImagePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File

class ProfileFragment:Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var currentUser:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentProfileBinding.inflate(inflater, container, false)

        currentUser=Preference.getUser(requireContext())!!
        if (currentUser.profilePicture != "") {
            binding.avatar.load(currentUser.profilePicture){
                crossfade(true)
                placeholder(R.drawable.loading)
            }
        }
        binding.userName.text=currentUser.userName
        binding.phoneNumber.text=currentUser.phoneNumber

        binding.changeProfile.setOnClickListener {
            checkPermissions()
        }
        return binding.root
    }

    private suspend fun upload(data: Uri?) {
         currentUser.profilePicture= uploadUserPhoto(data)
        Firebase.firestore.collection(Collections.USERS)
            .document(Firebase.auth.currentUser!!.uid)
            .update("profilePicture", currentUser.profilePicture)
            .await()
        Preference.saveUser(requireContext(),currentUser)

    }

    private suspend fun uploadUserPhoto(data: Uri?): String {
        val riderPhotoReference = Firebase.storage.getReference(Collections.USERS)
            .child("${System.nanoTime()}.${getFileMimeType(data!!)}")
        riderPhotoReference.putFile(data).await()
        return riderPhotoReference.downloadUrl.await().toString()

    }

    private fun getFileMimeType(uri: Uri): String? {
        return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            val mime = MimeTypeMap.getSingleton()
            mime.getExtensionFromMimeType(requireActivity().contentResolver.getType(uri))
        } else {
            MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path!!)).toString())
        }
    }

    private val launcher = registerImagePicker { images ->
        // selected images
        if(images.isNotEmpty()){
            val image = images[0]
            binding.avatar.load(image.uri){
                crossfade(true)
                placeholder(R.drawable.loading)
            }

            uploadImage(image.uri)

        }
    }

    private fun uploadImage(uri: Uri) {
        requireActivity().toast("Uploading photo...")
        lifecycleScope.launch(Dispatchers.IO) {
            upload(uri)
        }.invokeOnCompletion {
            if (it != null) {
                requireActivity().runOnUiThread {
                    binding.progress.hide()
                    requireActivity().toast("Unable to change picture now. Try again later")

                }
            } else {
                requireActivity().runOnUiThread {
                    binding.progress.hide()
                    requireActivity().toast("Profile picture changed successfully")

                }

            }
        }
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().iNeed(Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.CAMERA,
                onGranted = {
                    val config = ImagePickerConfig(
                        limitSize = 1,
                        isShowCamera = true,
                        isFolderMode = false,
                        imageGridCount = GridCount(3, 5),
                        // See more at configuration attributes table below
                    )

                    launcher.launch(config)
                },
                onDenied = {

                })
        } else {
            requireActivity().iNeed(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                onGranted = {
                    val config = ImagePickerConfig(
                        limitSize = 1,
                        isShowCamera = true,
                        isFolderMode = false,
                        imageGridCount = GridCount(3, 5),
                        // See more at configuration attributes table below
                    )

                    launcher.launch(config)
                },
                onDenied = {

                })
        }
    }
}