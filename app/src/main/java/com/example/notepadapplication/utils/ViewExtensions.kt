package com.example.notepadapplication.utils

import android.content.Context
import android.view.View

fun Context.dip(value: Int): Int = dipF(value).toInt()
fun Context.dipF(value: Int): Float = value * resources.displayMetrics.density
fun View.dip(value: Int): Int = context.dip(value)