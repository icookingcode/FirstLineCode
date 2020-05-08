package com.guc.firstlinecode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.core.content.FileProvider
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_picture.*
import java.io.File

class SelectPictureActivity : BaseActivity(), View.OnClickListener {
    companion object {
        const val REQUEST_CODE = 1000
        const val DATA_URI = "data_uri"
        const val DATA_PATH = "data_path"
        private const val REQUEST_CODE_TAKE_PHOTO = 1001
        private const val REQUEST_CODE_ALBUM = 1002
        fun start(context: Activity) {
            context.startActivityForResult(
                Intent(context, SelectPictureActivity::class.java),
                REQUEST_CODE
            )
        }
    }

    private lateinit var imageUri: Uri
    private lateinit var outputImage: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setGravity(Gravity.BOTTOM)//底部显示
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//设置宽度满屏
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.attributes = lp
        setContentView(R.layout.activity_select_picture)
        initView()
    }

    private fun initView() {
        takePhoto.setOnClickListener(this)
        selectAlbum.setOnClickListener(this)
        cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.takePhoto -> takePhoto()
            R.id.selectAlbum -> selectAlbum()
            R.id.cancel -> finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_TAKE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    val intent = Intent()
                    intent.putExtra(DATA_URI, imageUri)
                    intent.putExtra(DATA_PATH, outputImage.absolutePath)
                    setResult(Activity.RESULT_OK, intent)

                } else {
                    outputImage.delete()
                }
                this.finish()
            }
            REQUEST_CODE_ALBUM -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val uri = data.data
                    intent.putExtra(DATA_URI, uri)
                    setResult(Activity.RESULT_OK, intent)
                }
                finish()
            }
        }

    }

    //拍照
    private fun takePhoto() {
        outputImage = File(externalCacheDir, generateFileName())
        if (outputImage.exists()) outputImage.delete()
        outputImage.createNewFile()
        imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "com.guc.firstlinecode.fileprovider", outputImage)
        } else {
            Uri.fromFile(outputImage)
        }
        //启动相机
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO)
    }

    //选相册
    private fun selectAlbum() {
        //打开文件选择器
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        //指定只显示图片
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_ALBUM)
    }

    //获取文件名
    private fun generateFileName(): String = "IMG-${System.currentTimeMillis()}.jpg"
}
