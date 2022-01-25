package com.example.videoplayer

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var fileList : List<File>
    val path = File(System.getenv("EXTERNAL_STORAGE")!!)
    val videoList = mutableListOf<File>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askPermissionForStorage()

    }

    private fun askPermissionForStorage() {
        val permissionListner = object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                Toast.makeText(this@MainActivity, "mil gai thanks", Toast.LENGTH_SHORT).show()
                displayFiles(path)
                Log.d("videoList", "$videoList ")
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

        for(i in allFiles){
            if(i.isDirectory || !i.isHidden){
                //Log.d("directory found", "= ${i.name}")
                displayFiles(i)

                if(i.name.endsWith(".mp4")) {
                    videoList.add(i)
                    Log.d("myPath", "=  ${i.path} ")
                    val intent = Intent(this , PlayerActivity::class.java)
                    intent.putExtra("myPath" , i.path.toString())
                    startActivity(intent)
                    //Log.d("videofound = ", " ${i.name}")
                }
            }

        }

    }
}