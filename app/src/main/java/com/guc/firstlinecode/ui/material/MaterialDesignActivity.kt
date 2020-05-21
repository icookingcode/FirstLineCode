package com.guc.firstlinecode.ui.material

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.guc.firstlinecode.R
import com.guc.firstlinecode.adapter.common.CommonAdapter4Rcv
import com.guc.firstlinecode.adapter.common.ViewHolder4RecyclerView
import com.guc.firstlinecode.base.BaseActivity
import com.guc.firstlinecode.bean.Fruit
import com.guc.firstlinecode.utils.ToastUtil
import com.guc.firstlinecode.utils.quickStartActivity
import kotlinx.android.synthetic.main.activity_material_design.*

class MaterialDesignActivity : BaseActivity() {
    private val datas = ArrayList<Fruit>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.apple)
        }

        fab.setOnClickListener {
//            Snackbar.make(it,"删除数据？",Snackbar.LENGTH_SHORT).setAction("取消"){
//                ToastUtil.toast(this,"取消了该操作")
//            }.show()

            ToastUtil.snack(it, "删除数据？") { ToastUtil.toast(this, "取消了该操作") }
        }
        initNavView()
        initRcv()

    }

    private fun initRcv() {
        rcvContent.layoutManager = GridLayoutManager(this, 2)
        loadFruits()
        val adapter = object : CommonAdapter4Rcv<Fruit>(datas) {
            override fun getRootView(parent: ViewGroup, viewType: Int): View {
                return LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)
            }

            override fun bindData(
                viewHolder: ViewHolder4RecyclerView,
                position: Int,
                data: Fruit,
                itemType: Int
            ) {
                viewHolder.apply {
                    setText(R.id.fruitName, data.name)
                    Glide.with(context).load(data.imageId)
                        .into(getView(R.id.fruitImage) as ImageView)
                    onItemClickListener = { _, _ ->
                        quickStartActivity<FruitActivity>(context) {
                            putExtra(FruitActivity.FRUIT_NAME, data.name)
                            putExtra(FruitActivity.FRUIT_IMAGE_ID, data.imageId)
                        }
                    }
                }

            }

        }
        rcvContent.adapter = adapter
    }

    private fun initNavView() {
        navView.setCheckedItem(R.id.navApple)
        navView.menu.getItem(0).subMenu.add("苹果").setIcon(R.drawable.apple)
        navView.setNavigationItemSelectedListener {
            val subMenu: SubMenu = navView.menu.getItem(0).subMenu.apply { clear() }
            when (it.itemId) {
                R.id.navApple -> {
                    subMenu.add("苹果").setIcon(R.drawable.apple)
                }
                R.id.navOrange -> {
                    subMenu.add("橘子").setIcon(R.drawable.orange)
                }
                R.id.navBanana -> {
                    subMenu.add("香蕉").setIcon(R.drawable.banana)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun loadFruits() {
        repeat(5) {
            datas.add(Fruit("苹果", R.drawable.bigapple))
            datas.add(Fruit("香蕉", R.drawable.bigbanana))
            datas.add(Fruit("橘子", R.drawable.bigorange))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> ToastUtil.toast(this, "添加")
            R.id.remove_item -> ToastUtil.toast(this, "移除")
            R.id.backup_item -> ToastUtil.toast(this, "备份")
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}
