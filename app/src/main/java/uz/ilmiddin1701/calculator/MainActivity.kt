package uz.ilmiddin1701.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import uz.ilmiddin1701.calculator.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.MathContext

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var checkedText = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnAnswer.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        binding.btnPositiveOrNegative.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnPercent.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnSlash.setOnClickListener(this)
        binding.btnX.setOnClickListener(this)
        binding.btnDot.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_0 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("0")
            }
            R.id.btn_1 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("1")
            }
            R.id.btn_2 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("2")
            }
            R.id.btn_3 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("3")
            }
            R.id.btn_4 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("4")
            }
            R.id.btn_5 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("5")
            }
            R.id.btn_6 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("6")
            }
            R.id.btn_7 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("7")
            }
            R.id.btn_8 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("8")
            }
            R.id.btn_9 ->{
                vibrate(this)
                emptyAdd()
                binding.tvAnswer.append("9")
            }
            R.id.btnPositiveOrNegative ->{
                vibrate(this)
                val text = binding.tvAnswer.text.toString()
                if (binding.tvAnswer.text.isNotBlank() && !text.contains("+") && !text.contains("-") && !text.contains("*") && !text.contains("/") && !text.contains("%") && text.toFloat() > 0) {
                    binding.tvAnswer.text = "-" + binding.tvAnswer.text.toString()
                } else if (binding.tvAnswer.text.isNotBlank() && text.substring(0, 1) == "-") {
                    binding.tvAnswer.text = text.replace("-", "")
                }
            }
            R.id.btn_percent ->{
                vibrate(this)
                emptyAdd()
                val text = binding.tvAnswer.text.toString()
                if (!text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith("/") && !text.endsWith(".") && !text.endsWith("%")){
                    binding.tvAnswer.append("%")
                    checkedText = true
                }
            }
            R.id.btn_dot ->{
                vibrate(this)
                val text = binding.tvAnswer.text.toString()
                // Oxirgi amaldan keyingi qismini olamiz
                val lastOperand = text.split("[+\\-*/]".toRegex()).last()
                // Oxirgi sonning ichida nuqta mavjud emasligiga ishonch hosil qilamiz
                if (!lastOperand.contains(".") && !text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith("/")) {
                    binding.tvAnswer.append(".")
                }
            }
            R.id.btn_minus ->{
                if (!checkedText) {
                    vibrate(this)
                    val text = binding.tvAnswer.text.toString()
                    if (!text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith("/") && !text.endsWith(".")){
                        binding.tvAnswer.append("-")
                    }
                }
            }
            R.id.btn_plus ->{
                if (!checkedText) {
                    vibrate(this)
                    val text = binding.tvAnswer.text.toString()
                    if (!text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith(
                            "/"
                        ) && !text.endsWith(".")
                    ) {
                        binding.tvAnswer.append("+")
                    }
                }
            }
            R.id.btn_slash ->{
                if (!checkedText) {
                    vibrate(this)
                    val text = binding.tvAnswer.text.toString()
                    if (!text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith(
                            "/"
                        ) && !text.endsWith(".")
                    ) {
                        binding.tvAnswer.append("/")
                    }
                }
            }
            R.id.btn_x ->{
                if (!checkedText) {
                    vibrate(this)
                    val text = binding.tvAnswer.text.toString()
                    if (!text.endsWith("+") && !text.endsWith("-") && !text.endsWith("*") && !text.endsWith(
                            "/"
                        ) && !text.endsWith(".")
                    ) {
                        binding.tvAnswer.append("*")
                    }
                }
            }
            R.id.btn_answer -> {
                vibrate(this)
                if (binding.tvAnswer.text == "0") {
                    Toast.makeText(this, "Son kiritmasdan hisoblash imkonsiz!", Toast.LENGTH_SHORT).show()
                } else {
                    if (checkedText) {
                        // Agar foiz hisoblanayotgan bo'lsa
                        val text = binding.tvAnswer.text.toString()
                        val parts = text.split("%")
                        val result = parts[0].toBigDecimal() * parts[1].toBigDecimal() / BigDecimal(100)
                        binding.tvAnswer.text = result.stripTrailingZeros().toPlainString()
                        checkedText = false
                    } else {
                        try {
                            val text = binding.tvAnswer.text.toString()

                            // Amallarni BigDecimal bilan ishlatamiz
                            val expression = binding.tvAnswer.text.toString()

                            // Double natijasini BigDecimalga o'tkazishdan voz kechib, natijani to'liq BigDecimal bilan hisoblaymiz
                            val build = ExpressionBuilder(expression).build()

                            // Natijani katta aniqlik bilan hisoblash
                            val result = build.evaluate().toBigDecimal(MathContext.DECIMAL64)

                            // Aniqlikni saqlash va kasr qismini faqat kerakli joylarda ko'rsatish
                            val finalResult = result.stripTrailingZeros().toPlainString()

                            // Natijani chiqarish
                            binding.tvAnswer.text = finalResult
                        } catch (e: Exception) {
                            Toast.makeText(this, "Xatolik", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            R.id.btn_clear ->{
                vibrate2(this)
                binding.tvAnswer.text = "0"
                checkedText = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun emptyAdd() {
        val text = binding.tvAnswer.text.toString()
        if (text == "0"){
            binding.tvAnswer.text = ""
        }
    }

    private fun vibrate(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(40) // Old versions
        }
    }

    private fun vibrate2(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100) // Old versions
        }
    }
}