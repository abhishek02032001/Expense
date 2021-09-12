package com.example.manage.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.manage.R
import com.example.manage.view.fragment.Home
import com.example.manage.view.fragment.More
import com.example.manage.view.fragment.Statistic
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        showFragment(Home())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> showFragment(Home())
                R.id.statistics -> showFragment(Statistic())
                R.id.more -> showFragment(More())
                else -> showFragment(Home())
            }
            true
        }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navigationContainer,fragment)
            commit()
        }
    }

}