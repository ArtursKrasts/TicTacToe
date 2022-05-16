package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

const val PLAYER_ONE_NAME = "com.example.tictactoe.PLAYER_ONE_NAME"
const val PLAYER_TWO_NAME = "com.example.tictactoe.PLAYER_TWO_NAME"
const val IS_TWO_PLAYER = "com.example.tictactoe.IS_TWO_PLAYER"
const val PLAYER_ONE_SCORE = "com.example.tictactoe.PLAYER_ONE_SCORE"
const val PLAYER_TWO_SCORE = "com.example.tictactoe.PLAYER_TWO_SCORE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val openSinglePlayer = findViewById<Button>(R.id.button)
        val openTwoPlayer = findViewById<Button>(R.id.button2)

        openSinglePlayer.setOnClickListener {
            val intent = Intent(this, EnterNameSingle::class.java)
            startActivity(intent)
        }

        openTwoPlayer.setOnClickListener {
            val intent = Intent(this, EnterNameTwo::class.java)
            startActivity(intent)
        }
    }
}