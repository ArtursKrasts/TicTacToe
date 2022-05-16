package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Tictactoe : AppCompatActivity()  {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tictactoe)
        val squareOneOne = findViewById<ImageButton>(R.id.squareOneOne)
        val squareOneTwo = findViewById<ImageButton>(R.id.squareOneTwo)
        val squareOneThree = findViewById<ImageButton>(R.id.squareOneThree)
        val squareTwoOne = findViewById<ImageButton>(R.id.squareTwoOne)
        val squareTwoTwo = findViewById<ImageButton>(R.id.squareTwoTwo)
        val squareTwoThree = findViewById<ImageButton>(R.id.squareTwoThree)
        val squareThreeOne = findViewById<ImageButton>(R.id.squareThreeOne)
        val squareThreeTwo = findViewById<ImageButton>(R.id.squareThreeTwo)
        val squareThreeThree = findViewById<ImageButton>(R.id.squareThreeThree)
        val reset = findViewById<Button>(R.id.reset)
        val playerOneText = findViewById<TextView>(R.id.textView)
        val playerTwoText = findViewById<TextView>(R.id.textView2)
        val playersTurnText = findViewById<TextView>(R.id.textView3)
        var gameEnd = false
        var turnCounter = 0
        val buttonArray = arrayOf(squareOneOne, squareOneTwo, squareOneThree,
                                    squareTwoOne, squareTwoTwo, squareTwoThree,
                                    squareThreeOne, squareThreeTwo, squareThreeThree)
        val tictactoeMap = mutableMapOf("squareOneOne" to "blank", "squareOneTwo" to "blank", "squareOneThree" to "blank",
                                        "squareTwoOne" to "blank", "squareTwoTwo" to "blank", "squareTwoThree" to "blank",
                                        "squareThreeOne" to "blank", "squareThreeTwo" to "blank", "squareThreeThree" to "blank")
        val victoryCombinations = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
                                intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
                                intArrayOf(0, 4, 8), intArrayOf(6, 4, 2))
        var playerOneScore = intent.getIntExtra(PLAYER_ONE_SCORE, 0)
        var playerTwoScore = intent.getIntExtra(PLAYER_TWO_SCORE, 0)
        val twoPlayer = intent.getBooleanExtra(IS_TWO_PLAYER, false)
        val playerOneName = intent.getStringExtra(PLAYER_ONE_NAME)
        val playerTwoName = intent.getStringExtra(PLAYER_TWO_NAME)
        playerOneText.text = "$playerOneName's Score: $playerOneScore"
        playersTurnText.text = "$playerOneName's turn"
        playerTwoText.text = "$playerTwoName's Score: $playerTwoScore"

        fun squareWorth(square:Int, values:List<String>):Int {
            var squareWorth = 0
            for (victoryCombination in victoryCombinations) {
                if(square in victoryCombination) {
                    var crossCount = 0
                    var circleCount = 0
                    for(x in victoryCombination) {
                        if(x == square) continue
                        if(values[x] == "cross") crossCount++
                        if(values[x] == "circle") circleCount++
                    }
                    if(circleCount == 2) squareWorth+=1000
                    else if(crossCount == 2) squareWorth+=100
                    else if(crossCount == 0 && circleCount == 0) squareWorth+=2
                    else if(crossCount == 1 && circleCount == 0) squareWorth+=1
                    else if(crossCount == 0 && circleCount == 1) squareWorth+=3
                }
            }
            return squareWorth
        }

        fun computersTurn() {
            val values = tictactoeMap.values.toString().replace("[", "").replace("]", "").replace(" ", "").split(",")
            var bestSquareWorth = 0
            var bestSquare = 0
            for ((i, value) in values.withIndex()) {
                if (value == "blank") {
                    val squareWorth = squareWorth(i ,values)
                    if(squareWorth > bestSquareWorth) {
                        bestSquareWorth = squareWorth
                        bestSquare = i
                    }
                }
            }
            val button = buttonArray[bestSquare]
            val tag = button.tag.toString()
            tictactoeMap[tag] = "circle"
            button.setImageResource(R.drawable.circle)
            playersTurnText.text = "$playerOneName's turn"
            turnCounter++
        }

        fun checkWinner() {
            val values = tictactoeMap.values.toString().replace("[", "").replace("]", "").replace(" ", "").split(",")
            for (victoryCombination in victoryCombinations) {
                if(values[victoryCombination[0]] == "cross" && values[victoryCombination[1]] == "cross" && values[victoryCombination[2]] == "cross") {
                    gameEnd = true
                    playersTurnText.text = "$playerOneName's victory!"
                    playerOneScore++
                    return
                }
                else if(values[victoryCombination[0]] == "circle" && values[victoryCombination[1]] == "circle" && values[victoryCombination[2]] == "circle") {
                    gameEnd = true
                    playersTurnText.text = "$playerTwoName's victory!"
                    playerTwoScore++
                    return
                }
            }
            if(turnCounter == 9) {
                gameEnd = true
                playersTurnText.text = "Draw!"
            }
        }

        fun takeTurn(button:ImageButton) {
            val tag = button.tag.toString()
            if(tictactoeMap[tag] == "blank" && !gameEnd && turnCounter < 9) {
                if(turnCounter%2 == 0) {
                    tictactoeMap[tag] = "cross"
                    button.setImageResource(R.drawable.cross)
                    playersTurnText.text = "$playerTwoName's turn"
                    turnCounter++
                }
                else{
                    tictactoeMap[tag] = "circle"
                    button.setImageResource(R.drawable.circle)
                    playersTurnText.text = "$playerOneName's turn"
                    turnCounter++
                }
                checkWinner()
                if(!twoPlayer && !gameEnd) {
                    computersTurn()
                    checkWinner()
                }
            }
        }

        squareOneOne.setOnClickListener {
            takeTurn(squareOneOne)
        }
        squareOneTwo.setOnClickListener {
            takeTurn(squareOneTwo)
        }
        squareOneThree.setOnClickListener {
            takeTurn(squareOneThree)
        }
        squareTwoOne.setOnClickListener {
            takeTurn(squareTwoOne)
        }
        squareTwoTwo.setOnClickListener {
            takeTurn(squareTwoTwo)
        }
        squareTwoThree.setOnClickListener {
            takeTurn(squareTwoThree)
        }
        squareThreeOne.setOnClickListener {
            takeTurn(squareThreeOne)
        }
        squareThreeTwo.setOnClickListener {
            takeTurn(squareThreeTwo)
        }
        squareThreeThree.setOnClickListener {
            takeTurn(squareThreeThree)
        }
        reset.setOnClickListener {
            val intent = Intent(this, Tictactoe::class.java).apply {
                putExtra(PLAYER_ONE_NAME, intent.getStringExtra(PLAYER_ONE_NAME))
                putExtra(PLAYER_TWO_NAME, intent.getStringExtra(PLAYER_TWO_NAME))
                putExtra(IS_TWO_PLAYER, intent.getBooleanExtra(IS_TWO_PLAYER, false))
                putExtra(PLAYER_ONE_SCORE, playerOneScore)
                putExtra(PLAYER_TWO_SCORE, playerTwoScore)
            }
            startActivity(intent)
            finish()
        }
    }
}

