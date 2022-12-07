package com.hooooooo.android.veni.badgedrawabledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val tv: TextView by lazy { findViewById(R.id.text) }
    private val tab: TabLayout by lazy { findViewById(R.id.tb_layout) }
    private val bottomNv: BottomNavigationView by lazy { findViewById(R.id.bottom_nv) }

    @ExperimentalBadgeUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //textView,Button,ImageView的用法
        tv.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                BadgeDrawable.create(this@MainActivity).apply {
                    badgeGravity = BadgeDrawable.TOP_END
                    //字节长度，"+"占一个长度
                    maxCharacterCount = 3
                    //不设置直接显示圆点
                    number = 55
                    backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.red)
                    isVisible = true
                    BadgeUtils.attachBadgeDrawable(this@apply, tv)
                }
                tv.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        //Tab中的用法
        tab.getTabAt(0)?.let {
            it.orCreateBadge.apply {
                backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.red)
                badgeTextColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                number = 6
                maxCharacterCount = 3
            }
        }
        tab.getTabAt(1)?.let {
            it.orCreateBadge.apply {
                backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.red)
                badgeTextColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                maxCharacterCount = 3
            }
        }
        //BottomNavigationView中的用法
        bottomNv.getOrCreateBadge(R.id.navigation_home).apply {
            backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.red)
            badgeTextColor = ContextCompat.getColor(this@MainActivity, R.color.white)
            number = 22
        }
    }
}