package com.example.androidhomework3

import com.example.androidhomework3.MathOperations.plus
import com.example.androidhomework3.MathOperations.minus
import com.example.androidhomework3.MathOperations.multiply
import com.example.androidhomework3.MathOperations.divide

enum class MathSigns(val value: (Long, Long) -> Long) {
    Plus(value = ::plus),
    Minus(value = ::minus),
    Divide(value = ::divide),
    Multiply(value = ::multiply)
}