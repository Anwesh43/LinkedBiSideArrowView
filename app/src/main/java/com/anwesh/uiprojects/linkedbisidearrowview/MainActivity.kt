package com.anwesh.uiprojects.linkedbisidearrowview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.bisidearrowview.BiSideArrowView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiSideArrowView.create(this)
    }
}
