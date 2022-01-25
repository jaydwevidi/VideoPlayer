package com.example.videoplayer

import android.Manifest
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var fileList : List<File>
    private lateinit var path : File
    val videoList = mutableListOf<VideoObject>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        path = File(System.getenv("EXTERNAL_STORAGE")!!)
        askPermissionForStorage()

    }

    private fun setupRV(){
        findViewById<RecyclerView>(R.id.myRecyclerView).apply {
            adapter = MyRVadapter(videoList , this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.VERTICAL , false)

        }
    }

    private fun askPermissionForStorage() {

        val permissionListner = object : PermissionListener {

            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                Toast.makeText(this@MainActivity, "Permission Given", Toast.LENGTH_SHORT).show()
                displayFiles(path)
                Log.d("videoList", "$videoList ")
                findViewById<ProgressBar>(R.id.progress_circular).visibility = View.GONE
                setupRV()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(this@MainActivity, "permission dedo please", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                permissionToken: PermissionToken
            ) {
                permissionToken.continuePermissionRequest()

            }
        }


        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(permissionListner)
            .check()
    }

    private fun displayFiles(file : File){
        val allFiles  = file.listFiles()

        if (allFiles == null)
            return

        Log.d("allFiles", "All files : $allFiles")
        val newLideoList = mutableListOf<VideoObject>()
        for(i in allFiles){
            if(i.isDirectory || !i.isHidden){
                //Log.d("directory found", "= ${i.name}")
                displayFiles(i)

                if(i.name.endsWith(".mp4")) {

                    val videoObject = VideoObject(i.name , i.path.toString() ,  i )
                    videoList.add(videoObject)
                    Log.d("myPath", "=  ${i.path} ")
                    //Log.d("videofound = ", " ${i.name}")
                }
            }

        }

    }
}