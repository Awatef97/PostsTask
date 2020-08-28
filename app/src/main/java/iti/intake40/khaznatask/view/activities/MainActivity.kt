package iti.intake40.khaznatask.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.view.fragments.PostsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextFrag = PostsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, nextFrag)
            .commit()


    }
}
