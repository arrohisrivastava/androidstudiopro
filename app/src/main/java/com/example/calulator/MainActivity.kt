package com.example.calulator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import org.mariuszgromada.math.mxparser.Expression
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultTextView: TextView
    private lateinit var finResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.textView)
        finResultTextView = findViewById(R.id.textView22)
    }

    fun clearExpression() {
        resultTextView.text = ""
        finResultTextView.text = ""
    }

    fun appendToExpression(txt: String) {
        resultTextView.append(txt)
    }

    fun deleteLastCharacter() {
        val currentText = resultTextView.text.toString()

        if (currentText.isNotEmpty()) {
            val newText = currentText.substring(0, currentText.length - 1)
            resultTextView.text = newText
        }
    }

    fun operason() {
        val expres1 = resultTextView.text.toString()
        var expres = expres1.replace('×', '*').replace('÷', '/').replace('−', '-')
        val result: Any = eval(expres)
        finResultTextView.append(result.toString())

    }

    private fun eval(expres: String): Any {
        return try {
            Expression(expres).calculate().toInt()
        } catch (e: Exception) {
            print("Invalid Expression")
        }
    }

//    fun percent(expresan: String): Any {
//        var result:Any=0
//        val numbers = expresan.split("%")
//        if (numbers != null && numbers.size == 2) {
//            val num1 = numbers[0].toDoubleOrNull()
//            val num2 = numbers[1].toDoubleOrNull()
//            if (num1!=null&&num2!=null){
//                if (num1>num2){
//                    result= (num1/100)*num2
//                }
//                else{
//                    result= (num2/100)*num1
//                }
//            }
//        }
//        return result
//    }

    fun buttSlapped(view: View) {
        var c:Int=0
        val butt = view as Button
        val buttText = butt.text.toString()
        when (buttText) {
            "AC" -> clearExpression()
            "DEL" -> deleteLastCharacter()
            "=" -> {
                if(c>0){
                    clearExpression()
                    c=0
                }
                else{
                    operason()
                }
            c++}
            else -> appendToExpression(buttText)
        }
    }
}