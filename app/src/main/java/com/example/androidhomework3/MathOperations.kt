package com.example.androidhomework3

import java.math.BigDecimal

object MathOperations {
    fun plus(a:Long, b:Long) = a+b
    fun minus(a:Long, b:Long) = a-b
    fun divide(a:Long, b:Long) = if (b!=0L) a/b else 0
    fun multiply(a:Long, b:Long) = a*b
}