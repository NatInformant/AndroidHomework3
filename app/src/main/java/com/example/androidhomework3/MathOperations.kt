package com.example.androidhomework3

import java.math.BigDecimal

object MathOperations {
    fun plus(a:Double, b:Double) = a+b
    fun minus(a:Double, b:Double) = a-b
    fun divide(a:Double, b:Double) = if (b!=0.0) a/b else 0.0
    fun multiply(a:Double, b:Double) = a*b
}