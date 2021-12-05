package com.shashi.onebanc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shashi.onebanc.fragment.CartFragment
import com.shashi.onebanc.fragment.DialogLanguageFragment
import com.shashi.onebanc.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView : BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val homeFragment = HomeFragment()
        val cartFragment = CartFragment()

        setCurrentFragment(homeFragment)

        bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_item_home -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.nav_item_cart -> {
                    setCurrentFragment(cartFragment)
                    true
                }
                R.id.nav_item_language -> {
                    val dialogLanguageFragment = DialogLanguageFragment()
                    dialogLanguageFragment.show(supportFragmentManager, "DialogLanguageFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }

}