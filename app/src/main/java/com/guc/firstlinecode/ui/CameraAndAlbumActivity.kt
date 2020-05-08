package com.guc.firstlinecode.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import com.guc.firstlinecode.R
import com.guc.firstlinecode.SelectPictureActivity
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_camera_and_album.*

class CameraAndAlbumActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CameraAndAlbumActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_and_album)
        titleLayout.title = "摄像头和相机"
        btnSelect.setOnClickListener {
            SelectPictureActivity.start(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SelectPictureActivity.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val imageUri = data?.extras?.get(SelectPictureActivity.DATA_URI)
                    val filePath = data?.extras?.get(SelectPictureActivity.DATA_PATH)
                    if (filePath != null) {
                        val bitmap = BitmapFactory.decodeFile(filePath as String)
                        ivShow.setImageBitmap(ImageUtils.rotateBitmapIfRequired(filePath, bitmap))
                    } else {
                        if (imageUri != null) {
                            imageUri as Uri
                            ivShow.setImageBitmap(
                                ImageUtils.getBitmapFromUri(
                                    imageUri,
                                    contentResolver
                                )
                            )
                        }

                    }
                }
            }
        }
    }
}
