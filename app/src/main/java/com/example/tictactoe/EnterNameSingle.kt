package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EnterNameSingle : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_name_single)
        val openGame = findViewById<Button>(R.id.button3)
        val editNameOne = findViewById<EditText>(R.id.editNameOne)

        openGame.setOnClickListener {
            val playerOneName = editNameOne.text.toString()
            val intent = Intent(this, Tictactoe::class.java).apply {
                putExtra(PLAYER_ONE_NAME, playerOneName)
                putExtra(PLAYER_TWO_NAME, "Computer")
                putExtra(IS_TWO_PLAYER, false)
                putExtra(PLAYER_ONE_SCORE, 0)
                putExtra(PLAYER_TWO_SCORE, 0)
            }
            startActivity(intent)
        }
    }
}