package com.xiaohei.materdesigndemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var fruit: Array<Fruit> = arrayOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Mango", R.drawable.mango),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Grape", R.drawable.grape)
    )
    private var fruitlist = ArrayList<Fruit>()
    private lateinit var adapter: FruitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar = supportActionBar as ActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        initFruit()
        recycler_view.layoutManager = GridLayoutManager(MainActivity@ this, 2)
        adapter = FruitAdapter(fruitlist, this)
        recycler_view.adapter = adapter
        nav_view.setCheckedItem(R.id.nav_call)
        /***
         * 传入的lambda是 {MenuItem -> Boolean}
         */
        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked
            drawer_layout.closeDrawers()
            true
        }
        btn_alarm.setOnClickListener {
            Toast.makeText(MainActivity@ this, "点击定时器按钮", Toast.LENGTH_LONG).show()
            //获取定时器对象   kotlin 中使用as  进行强制类型转化
            val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            //获取的是系统开机到现在为止的时间
            val triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000
            manager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerAtTime,
                PendingIntent.getActivity(
                    MainActivity@ this,
                    12,
                    Intent(MainActivity@ this, DemoActivity::class.java),
                    0
                )
            )

        }
        btn_timer.setOnClickListener {
            val timer = Timer()
            timer.schedule(MyTimerTask(), 3000, 3000)
        }
        fab.setOnClickListener {
            Snackbar.make(fab, "snacker", Snackbar.LENGTH_SHORT)
                .setAction("Undo", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        Toast.makeText(applicationContext, "Data restored", Toast.LENGTH_LONG).show()
                    }

                })
                .show()
        }
    }

    fun initFruit() {
        fruitlist.clear()
        for (i in 0..9) {
          fruitlist.add(fruit.get(i))

        }
    }

    /***
     * 加载菜单文件
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    /***
     * 菜单项点击的处理处理事件
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.backup ->
                Toast.makeText(MainActivity@ this, "You Click BackUp", Toast.LENGTH_LONG).show()
            R.id.delect ->
                Toast.makeText(MainActivity@ this, "You Click Delect", Toast.LENGTH_LONG).show()
            R.id.setting ->
                Toast.makeText(MainActivity@ this, "You CLick Setting", Toast.LENGTH_LONG).show()
            android.R.id.home ->
                drawer_layout.openDrawer(GravityCompat.START)
        }
        return true
    }
}

class MyTimerTask : TimerTask() {
    override fun run() {
        Log.d("TAG", "" + System.currentTimeMillis())
    }


}
