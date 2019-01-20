package com.anwesh.uiprojects.bisidearrowview

/**
 * Created by anweshmishra on 20/01/19.
 */

import android.view.View
import android.content.Context
import android.view.MotionEvent
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.8f
val strokeFactor : Int = 90
val foreColor : Int = Color.parseColor("#1565C0")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i , n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + (scaleFactor()) * b.inverse()
fun Float.updateValue(a : Int, dir : Float, b : Int) : Float = mirrorValue(a, b) * dir * scGap