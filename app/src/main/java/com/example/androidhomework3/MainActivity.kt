package com.example.androidhomework3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var signIndex: Int = -1
    private var currentSign: MathSigns? = null
    private var signButtonsEnabledState = true
    private var isPreviousTaskMistake = false

    private lateinit var resultTextView: TextView
    private lateinit var plusButton: TextView
    private lateinit var minusButton: TextView
    private lateinit var divideButton: TextView
    private lateinit var multiplyButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpEdgeToEdge()


        resultTextView = findViewById(R.id.task_field)
        setUpNumberButtonsListeners()
        setUpSignButtonsListeners()
        setUpBackspaceButtonListener()
        setUpGetResultButtonListener()
    }

    private fun setUpNumberButtonsListeners() {
        listOf(
            R.id.one_button,
            R.id.two_button,
            R.id.three_button,
            R.id.four_button,
            R.id.five_button,
            R.id.six_button,
            R.id.seven_button,
            R.id.eight_button,
            R.id.nine_button,
            R.id.zero_button,
            R.id.point_button
        ).forEach {
            with(findViewById<TextView>(it)) {
                setOnClickListener {
                    numberButtonClickListener(this.text.toString())
                }
            }
        }
    }

    private fun setUpSignButtonsListeners() {
        plusButton = findViewById(R.id.plus_button)
        plusButton.setOnClickListener {
            signButtonClickListener(plusButton.text.toString())
        }

        minusButton = findViewById(R.id.minus_button)
        minusButton.setOnClickListener {
            signButtonClickListener(minusButton.text.toString())
        }

        divideButton = findViewById(R.id.divide_button)
        divideButton.setOnClickListener {
            signButtonClickListener(divideButton.text.toString())
        }

        multiplyButton = findViewById(R.id.multiply_button)
        multiplyButton.setOnClickListener {
            signButtonClickListener(multiplyButton.text.toString())
        }
    }

    private fun setUpBackspaceButtonListener() {
        findViewById<TextView>(R.id.backspace_button).setOnClickListener {
            checkIsNeedToClearMistakeMessage()

            if (resultTextView.text.isEmpty()) return@setOnClickListener

            if (resultTextView.text[resultTextView.text.length - 1] in "+-/*") {
                currentSign = null
                signButtonsEnabledState = true
                signIndex = -1
                setUpSignButtonsEnabledState()
            }
            resultTextView.text =
                resultTextView.text.subSequence(0, resultTextView.text.length - 1)
        }
    }

    private fun setUpGetResultButtonListener() {
        findViewById<TextView>(R.id.get_result_button).setOnClickListener {
            checkIsNeedToClearMistakeMessage()

            if (signButtonsEnabledState) return@setOnClickListener

            val a = if (signIndex == 0) 0.0 else resultTextView.text.subSequence(0, signIndex)
                .toString().toDoubleOrNull()
            val b = resultTextView.text.subSequence(signIndex + 1, resultTextView.text.length)
                .toString().toDoubleOrNull()

            signButtonsEnabledState = true
            setUpSignButtonsEnabledState()

            if (a == null || b == null || (b == 0.0 && currentSign == MathSigns.Divide)) {
                resultTextView.text = "Ошибка в выражении"
                isPreviousTaskMistake = true
                return@setOnClickListener
            }

            resultTextView.text = currentSign?.value?.invoke(a, b)?.toString() ?: "Ошибка в выражении"
        }
    }

    private fun numberButtonClickListener(numberString: String) {
        checkIsNeedToClearMistakeMessage()

        addStringToResultTextView(numberString)
    }

    private fun signButtonClickListener(signString: String) {
        checkIsNeedToClearMistakeMessage()

        signIndex = resultTextView.text.length
        currentSign = when (signString) {
            "+" -> MathSigns.Plus
            "-" -> MathSigns.Minus
            "/" -> MathSigns.Divide
            else -> MathSigns.Multiply
        }
        signButtonsEnabledState = false
        setUpSignButtonsEnabledState()
        addStringToResultTextView(signString)
    }

    private fun addStringToResultTextView(numberString: String) {
        resultTextView.text = resultTextView.text.toString() + numberString
    }

    private fun checkIsNeedToClearMistakeMessage() {
        if (isPreviousTaskMistake) resultTextView.text = ""
        isPreviousTaskMistake = false
    }

    private fun setUpSignButtonsEnabledState() {
        plusButton.isEnabled = signButtonsEnabledState
        minusButton.isEnabled = signButtonsEnabledState
        divideButton.isEnabled = signButtonsEnabledState
        multiplyButton.isEnabled = signButtonsEnabledState
    }

    private fun setUpEdgeToEdge() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}