package com.guc.firstlinecode.ui.material

import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.guc.firstlinecode.R
import com.guc.firstlinecode.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fruit.*
import kotlinx.android.synthetic.main.activity_material_design.toolbar

/**
 * 水果详情
 */
class FruitActivity : BaseActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        collapsingToolbar.title = fruitName
        Glide.with(context).load(fruitImageId).into(fruitImage)
        fruitContentText.text = generateFruitContent(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return true
    }

    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)


}
