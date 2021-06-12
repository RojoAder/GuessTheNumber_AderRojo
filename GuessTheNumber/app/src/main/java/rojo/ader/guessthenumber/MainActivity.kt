package rojo.ader.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var maxNumber: Int = 100
    var minNumber: Int = 0
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val guessed: Button = findViewById(R.id.guessed)
        val generate: Button = findViewById(R.id.generate)

        generate.setOnClickListener {
            num = Random.nextInt(minNumber, maxNumber)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minNumber = num
            if (checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessing.setText(num.toString())
            } else {
                guessing.setText("Ganaste")
            }
        }

        down.setOnClickListener {
            maxNumber = num
            if (checkingLimits()) {
                num = Random.nextInt(minNumber, maxNumber)
                guessing.setText(num.toString())
            } else {
                guessing.setText("Ganaste")
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessing.setText("Adiviné, tu numero es " + num.toString())
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                generate.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                minNumber = 0
                maxNumber = 100
                num = 0
                won = false
            }
        }
    }

    fun checkingLimits(): Boolean {
        return minNumber != maxNumber
    }
}