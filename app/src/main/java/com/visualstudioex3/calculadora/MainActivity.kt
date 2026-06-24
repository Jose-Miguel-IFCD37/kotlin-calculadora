package com.visualstudioex3.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualstudioex3.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val RESET_BUTTON_DEFAULT_LABEL: String = "AC"
        const val RESET_BUTTON_STATE_LABEL: String = "C"

        enum class ButtonColor {
            Default,
            Grey,
            Orange
        }

        private val baseModifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Scaffold(
                    modifier = baseModifier
                ) { innerPadding ->
                    Calculadora(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Calculadora(modifier: Modifier = Modifier) {
        val calculator by remember { mutableStateOf(CalculatorService()) }
        val displayTextState: TextFieldState =
            rememberTextFieldState(calculator.displayText)
        var clearButtonLabel by remember { mutableStateOf(RESET_BUTTON_DEFAULT_LABEL) }

        calculator.onDisplayClear = {
            clearButtonLabel = RESET_BUTTON_DEFAULT_LABEL
            displayTextState.setTextAndPlaceCursorAtEnd(calculator.displayText)
        }

        calculator.onDisplayUpdated = { text ->
            clearButtonLabel = RESET_BUTTON_STATE_LABEL
            displayTextState.setTextAndPlaceCursorAtEnd(text)
        }

        Column(modifier) {
            Row {
                TextField(
                    state = displayTextState,
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    textStyle = MaterialTheme.typography.displayLarge
                        .copy(textAlign = TextAlign.End)
                )
            }

            Spacer(Modifier.padding(bottom = 16.dp))

            Row {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        DrawButton(
                            onClick = { calculator.remove() },
                            label = "\u232b",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                        DrawButton(
                            onClick = { calculator.clear() },
                            label = clearButtonLabel,
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                        DrawButton(
                            onClick = { calculator.percent() },
                            label = "%",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                        DrawButton(
                            onClick = { calculator.divide() },
                            label = "÷",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        DrawButton(
                            onClick = { calculator.append('7') },
                            label = "7",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('8') },
                            label = "8",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('9') },
                            label = "9",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.multiply() },
                            label = "x",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        DrawButton(
                            onClick = { calculator.append('4') },
                            label = "4",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('5') },
                            label = "5",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('6') },
                            label = "6",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.substract() },
                            label = "-",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        DrawButton(
                            onClick = { calculator.append('1') },
                            label = "1",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('2') },
                            label = "2",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append('3') },
                            label = "3",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.sum() },
                            label = "+",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        DrawButton(
                            onClick = { calculator.sign() },
                            label = "+/-",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                        DrawButton(
                            onClick = { calculator.append('0') },
                            label = "0",
                            modifier = Modifier.weight(1f)
                        )
                        DrawButton(
                            onClick = { calculator.append(',') },
                            label = ",",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Grey
                        )
                        DrawButton(
                            onClick = { calculator.equal() },
                            label = "=",
                            modifier = Modifier.weight(1f),
                            color = ButtonColor.Orange
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CalculadoraPreview() {
        CalculadoraTheme {
            Calculadora(baseModifier)
        }
    }

    @Composable
    fun DrawButton(
        @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
        onClick: () -> Unit,
        label: String,
        color: ButtonColor = ButtonColor.Default
    ) {
        val buttonColors: ButtonColors = when (color) {
            ButtonColor.Orange -> {
                ButtonDefaults.buttonColors()
                    .copy(
                        containerColor = Color(
                            1.0f,
                            0.596f,
                            0.0f,
                            1.0f
                        )
                    )
            }

            ButtonColor.Grey -> {
                ButtonDefaults.buttonColors()
                    .copy(
                        containerColor = Color(
                            0.5f,
                            0.5f,
                            0.5f
                        )
                    )
            }

            else -> {
                ButtonDefaults.buttonColors()
            }
        }

        return Button(onClick, modifier, colors = buttonColors) {
            Text(
                text = label,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

}

// Servicio de control de la entrada de datos de usuario de la calculadora:
// Gestiona todas las operaciones de transformacion de la entrada de datos del usuario de texto a
// valor decimal (Float) asi de como mostrar correctamente los valores en el campo de texto.
class CalculatorInputDisplay {
    companion object {
        private const val MAX_INTEGERS: Int = 6
        private const val MAX_DECIMALS: Int = 4
        private const val DEFAULT_VALUE: String = "0"
        private const val DECIMAL: Char = ','
        private const val ERROR_VALUE: String = "ERROR"

        private val _zeroDecimalsTemplate: String = ".${"0".repeat(MAX_DECIMALS)}"

        private var _text: String = DEFAULT_VALUE
    }

    val text: String
        get() = _text

    private fun isZero(): Boolean = text == DEFAULT_VALUE
    private fun hasDecimal(): Boolean = text.any { it == DECIMAL }

    private fun count(): Int {
        val value: Int = if (isZero())
            0
        else if (hasDecimal()) {
            text
                .split(DECIMAL)
                .last().length
        } else {
            text.length
        }

        Log.d("CALC_DISPLAY", "CalculatorInputDisplay::count() = $value")

        return value
    }

    private fun canAppendInteger(): Boolean = count() < MAX_INTEGERS
    private fun canAppendDecimal(): Boolean = count() < MAX_DECIMALS
    private fun canAppendComma(): Boolean = !hasDecimal()

    fun set(value: Float) {
        var textValue: String = if (value.isNaN())
            ERROR_VALUE
        else
            "%.${MAX_DECIMALS}f".format(value)

        if (textValue.endsWith(_zeroDecimalsTemplate))
            textValue = textValue.substring(0, textValue.length - _zeroDecimalsTemplate.length)

        Log.d("CALC_DISPLAY", "CalculatorInputDisplay::set($value) = $textValue")

        _text = textValue
    }

    fun append(value: Char) {
        var newValue: String = text
        val canAppend: Boolean = when (value) {
            DECIMAL -> {
                canAppendComma()
            }

            else -> {
                if (hasDecimal()) canAppendDecimal() else canAppendInteger()
            }
        }

        if (canAppend) {
            val newCurrent: String = if (isZero() && value != DECIMAL)
                ""
            else
                text

            newValue = "$newCurrent$value"
        }

        Log.d("CALC_DISPLAY", "CalculatorInputDisplay::append($value) = $newValue")

        _text = newValue
    }

    fun remove() {
        if (!isZero()) {
            val value: String = text
            val length: Int = value.length

            if (length > 0) {
                val newLength = length - 1
                val newValue = if (newLength == 0)
                    DEFAULT_VALUE
                else
                    value.substring(0, length - 1)

                Log.d("CALC_DISPLAY", "CalculatorInputDisplay::remove() = $newValue")

                _text = newValue
            }
        }
    }

    fun clear() {
        _text = DEFAULT_VALUE
        Log.d("CALC_DISPLAY", "CalculatorInputDisplay::clear()")
    }

    fun sign() {
        if (!isZero()) {
            val newValue: String = if (text.first() == '-')
                text.substring(1, text.length)
            else
                "-$text"

            Log.d("CALC_DISPLAY", "CalculatorInputDisplay::sign() = $newValue")

            _text = newValue
        }
    }

    fun toFloat(): Float {
        var newValue: String = text

        if (hasDecimal()) {
            newValue = if (text.last() == DECIMAL)
                text.substring(0, text.length - 1)
            else
                text.replace(',', '.')
        }

        Log.d("CALC_DISPLAY", "CalculatorInputDisplay::toFloat() = $newValue")

        return newValue.toFloat()
    }
}

// Servicio de matematica de la calculadora:
// Gestiona las operaciones matematicas disponibles de la calculadora asi como almacenar el total
// de las operaciones calculadas.
class CalculatorMath {
    private var _total: Float = 0f

    val total: Float
        get() = _total

    fun clear() {
        _total = 0f
    }

    fun initTotal(value: Float) {
        _total = value

        Log.d("CALC_MATH", "CalculatorMath::iniTotal()")
    }

    fun sum(value: Float) {
        _total += value
        Log.d("CALC_MATH", "CalculatorMath::sum($value) = $_total")
    }

    fun substract(value: Float) {
        _total -= value
        Log.d("CALC_MATH", "CalculatorMath::substract($value) = $_total")
    }

    fun multiply(value: Float) {
        _total *= value
        Log.d("CALC_MATH", "CalculatorMath::multiply($value) = $_total")
    }

    fun divide(value: Float) {
        _total = if (value == 0f) Float.NaN else _total / value
        Log.d("CALC_MATH", "CalculatorMath::divide($value) = $_total")
    }

    fun percent(value: Float) {
        _total = value / 100f
        Log.d("CALC_MATH", "CalculatorMath::percent($value) = $_total")
    }
}

// Servicio principal para operar la calculadora desde Compose:
// Abstrae todo el funcionamiento visual y de entrada de usuario asi como toda la logica matematica
// de la calculadora para ser utilizado desde Compose.
class CalculatorService {
    private enum class Operator {
        None,
        Sum,
        Substract,
        Multiply,
        Divide
    }

    private val display = CalculatorInputDisplay()
    private val calcMath = CalculatorMath()
    private var selectedOperator: Operator = Operator.None
    private var clearInput: Boolean = true
    private var initializeTotal: Boolean = true

    val displayText: String
        get() = display.text

    var onDisplayClear: () -> Unit = {}
    var onDisplayUpdated: (String) -> Unit = {}

    fun append(value: Char) {
        Log.d("CALC_SERVICE", "CalculatorService::append($value)")

        if (clearInput) {
            display.clear()
            clearInput = false
        }

        display.append(value)

        onDisplayUpdated(display.text)
    }

    fun remove() {
        Log.d("CALC_SERVICE", "CalculatorService::remove()")

        if (clearInput)
            display.clear()
        else
            display.remove()

        onDisplayUpdated(display.text)
    }

    fun clear() {
        Log.d("CALC_SERVICE", "CalculatorService::clear()")

        display.clear()
        calcMath.clear()
        selectedOperator = Operator.None
        initializeTotal = true

        onDisplayClear()
    }

    fun sign() {
        Log.d("CALC_SERVICE", "CalculatorService::sign()")
        display.sign()
        onDisplayUpdated(display.text)
    }

    fun sum() {
        Log.d("CALC_SERVICE", "CalculatorService::sum()")

        applyCurrentSelectedOperatorIfRequired()
        selectedOperator = Operator.Sum
        initializeTotalIfRequired()

        onDisplayUpdated(display.text)
    }

    fun substract() {
        Log.d("CALC_SERVICE", "CalculatorService::substract()")

        applyCurrentSelectedOperatorIfRequired()
        selectedOperator = Operator.Substract
        initializeTotalIfRequired()

        onDisplayUpdated(display.text)
    }

    fun multiply() {
        Log.d("CALC_SERVICE", "CalculatorService::multiply()")

        applyCurrentSelectedOperatorIfRequired()
        selectedOperator = Operator.Multiply
        initializeTotalIfRequired()

        onDisplayUpdated(display.text)
    }

    fun divide() {
        Log.d("CALC_SERVICE", "CalculatorService::divide()")

        applyCurrentSelectedOperatorIfRequired()
        selectedOperator = Operator.Divide
        initializeTotalIfRequired()

        onDisplayUpdated(display.text)
    }

    fun percent() {
        Log.d("CALC_SERVICE", "CalculatorService::percent()")

        calcMath.percent(display.toFloat())
        display.set(calcMath.total)

        onDisplayUpdated(display.text)
    }

    fun equal() {
        Log.d("CALC_SERVICE", "CalculatorService::equal()")

        when (selectedOperator) {
            Operator.Sum -> {
                calcMath.sum(display.toFloat())
            }

            Operator.Substract -> {
                calcMath.substract(display.toFloat())
            }

            Operator.Multiply -> {
                calcMath.multiply(display.toFloat())
            }

            Operator.Divide -> {
                calcMath.divide(display.toFloat())
            }

            else -> {
                calcMath.initTotal(display.toFloat())
            }
        }

        selectedOperator = Operator.None
        display.set(calcMath.total)
        clearInput = true

        onDisplayUpdated(display.text)
    }

    private fun applyCurrentSelectedOperatorIfRequired() {
        Log.d(
            "CALC_SERVICE",
            "CalculatorService::applyCurrentSelectedOperatorIfRequired()"
        )
        if (selectedOperator != Operator.None)
            equal()
        clearInput = true
    }

    private fun initializeTotalIfRequired() {
        Log.d(
            "CALC_SERVICE",
            "CalculatorService::initializeTotalIfRequired()"
        )
        if (initializeTotal || calcMath.total.isNaN()) {
            calcMath.initTotal(display.toFloat())
            initializeTotal = false
        }
    }
}
