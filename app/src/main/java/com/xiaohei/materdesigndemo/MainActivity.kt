package com.xiaohei.materdesigndemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
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

    }


}

class MyTimerTask : TimerTask() {
    override fun run() {
       Log.d("TAG",""+System.currentTimeMillis())
    }


}
