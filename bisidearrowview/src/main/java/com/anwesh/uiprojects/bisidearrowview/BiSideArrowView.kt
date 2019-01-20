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
val arrowDeg : Float = 45f
val arSizeFactor : Float = 5f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i , n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + (scaleFactor()) * b.inverse()
fun Float.updateValue(a : Int, dir : Float, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Int.iMod2() : Int = this % 2
fun Int.iDiv2() : Int = this / 2
fun Int.iMod2F() : Float = 1f - 2 * iMod2()

fun Canvas.drawRotatingArrow(gap : Float, arSize : Float, i : Int, scale : Float, paint : Paint) {
    save()
    translate(0f, gap * i.iDiv2())
    rotate(arrowDeg * i.iMod2F() * scale)
    drawLine(0f, 0f, arSize * i.iMod2F(), 0f, paint)
    restore()
}

fun Paint.setStyle(sw : Float) {
    color = foreColor
    strokeWidth = sw
    strokeCap = Paint.Cap.ROUND
}

fun Canvas.drawBSANode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val sf : Float = i.iMod2F()
    paint.setStyle(Math.min(w, h) / strokeFactor)
    save()
    translate(w/2 + (w/2 + size) * sc2.divideScale(1, 2), gap * (i + 1))
    rotate(90f * sf * sc2.divideScale(0, 2))
    translate(0f, -size)
    for (j in 0..(lines - 1)) {
        val sc : Float = sc1.divideScale(j, lines)
        save()
        drawRotatingArrow(2 * size, size / arSizeFactor, j, sc, paint)
        restore()
    }
    restore()
}