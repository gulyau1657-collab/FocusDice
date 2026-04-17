package com.example.focusdice

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val missions = listOf(
        "25 минут без телефона",
        "Выпей стакан воды",
        "Разбери 10 файлов",
        "Сделай 20 приседаний",
        "Прогуляйся 5 минут",
        "Ответь на 3 важных сообщения",
        "Наведи порядок на столе",
        "Закрой 1 мелкую задачу",
        "Проветри комнату",
        "Сделай 10 глубоких вдохов"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("focus_dice", Context.MODE_PRIVATE)
        val taskText = findViewById<TextView>(R.id.taskText)
        val rollCountText = findViewById<TextView>(R.id.rollCountText)
        val doneCountText = findViewById<TextView>(R.id.doneCountText)
        val rollButton = findViewById<Button>(R.id.rollButton)
        val doneButton = findViewById<Button>(R.id.doneButton)

        fun renderStats() {
            rollCountText.text = getString(R.string.rolls_template, prefs.getInt("rolls", 0))
            doneCountText.text = getString(R.string.done_template, prefs.getInt("done", 0))
        }

        renderStats()

        rollButton.setOnClickListener {
            val mission = missions.random()
            taskText.text = mission
            val newRolls = prefs.getInt("rolls", 0) + 1
            prefs.edit().putInt("rolls", newRolls).apply()
            renderStats()
        }

        doneButton.setOnClickListener {
            val newDone = prefs.getInt("done", 0) + 1
            prefs.edit().putInt("done", newDone).apply()
            taskText.text = getString(R.string.success_text)
            renderStats()
        }
    }
}
