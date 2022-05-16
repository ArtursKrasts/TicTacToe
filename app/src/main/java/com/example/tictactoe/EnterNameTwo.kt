package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EnterNameTwo : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_name_two)
        val openGame = findViewById<Button>(R.id.button3)
        val editNameOne = findViewById<EditText>(R.id.editNameOne)
        val editNameTwo = findViewById<EditText>(R.id.editNameTwo)

        openGame.setOnClickListener {
            val playerOneName = editNameOne.text.toString()
            val playerTwoName = editNameTwo.text.toString()
            val intent = Intent(this, Tictactoe::class.java).apply {
                putExtra(PLAYER_ONE_NAME, playerOneName)
                putExtra(PLAYER_TWO_NAME, playerTwoName)
                putExtra(IS_TWO_PLAYER, true)
                putExtra(PLAYER_ONE_SCORE, 0)
                putExtra(PLAYER_TWO_SCORE, 0)
            }
            startActivity(intent)
        }
    }
}