package com.example.androidhomework3

import kotlin.math.abs

object MathOperations {
    fun plus(a: Double, b: Double) = a + b
    fun minus(a: Double, b: Double) = if (a - b < 0) 0.0 else a - b
    fun divide(a: Double, b: Double) =  a / b
    fun multiply(a: Double, b: Double) = a * b
}