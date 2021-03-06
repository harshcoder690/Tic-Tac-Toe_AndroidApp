package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER_X= true
    var TURN_COUNT =0
    var boardStatus =Array(3){IntArray(3)}

    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
                arrayOf(button,button2,button3),
                arrayOf(button4,button5,button6),
                arrayOf(button7,button8,button9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()

        resetbtn.setOnClickListener{
            TURN_COUNT=0
            PLAYER_X=true
            initializeBoardStatus()

        }

    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.button->{
                updateValue(row = 0, col =0,player = PLAYER_X)
            }
            R.id.button2->{
                updateValue(row = 0, col =1,player = PLAYER_X)
            }
            R.id.button3->{
                updateValue(row = 0, col =2,player = PLAYER_X)
            }
            R.id.button4->{
                updateValue(row = 1, col =0,player = PLAYER_X)
            }
            R.id.button5->{
                updateValue(row = 1, col =1,player = PLAYER_X)
            }
            R.id.button6->{
                updateValue(row = 1, col =2,player = PLAYER_X)
            }
            R.id.button7->{
                updateValue(row = 2, col =0,player = PLAYER_X)
            }
            R.id.button8->{
                updateValue(row = 2, col =1,player = PLAYER_X)
            }
            R.id.button9->{
                updateValue(row = 2, col =2,player = PLAYER_X)
            }

        }
        TURN_COUNT++
        PLAYER_X = !PLAYER_X
        if(PLAYER_X){
            updateDisplay(" X Turn " )
        }else{
            updateDisplay("0 Turn")
        }
        if(TURN_COUNT== 9){
            updateDisplay("Game Draw")
        }
        CheckWinner();
    }
    private fun CheckWinner() {
       //horizontal row
        for(i in 0..2){
            if( boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2] ){
                if(boardStatus[i][0]==1){
                    updateDisplay("X wins")
                    Toast.makeText(this,"yay! X wins",Toast.LENGTH_SHORT).show()
                    break
                }else if (boardStatus[i][0]==0){
                    updateDisplay("0 wins")
                    Toast.makeText(this,"yay! 0 wins",Toast.LENGTH_SHORT).show()
                    break
                }
            }
        }
        //vertical columns
        for(i in 0..2){
            if( boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i] ){
                if(boardStatus[0][i]==1){
                    updateDisplay("X wins")
                    Toast.makeText(this,"yay! X wins",Toast.LENGTH_SHORT).show()
                    break
                }else if(boardStatus[0][i]==0) {
                    updateDisplay("0 wins")
                    Toast.makeText(this,"yay! 0 wins",Toast.LENGTH_SHORT).show()
                    break
                }
            }
        }
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2])
        //first diagonal
        if ( boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("X wins")
                Toast.makeText(this,"yay! X wins",Toast.LENGTH_SHORT).show()

            } else if (boardStatus[0][0] == 0) {
                updateDisplay(" 0 wins")
                Toast.makeText(this,"yay! 0 wins",Toast.LENGTH_SHORT).show()

            }
        }

        //second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("X wins")
                Toast.makeText(this,"yay! X wins",Toast.LENGTH_SHORT).show()


            } else if (boardStatus[0][2] == 0) {
                updateDisplay("0 wins")
                Toast.makeText(this,"yay! 0 wins",Toast.LENGTH_SHORT).show()


            }
        }
    }

    private fun updateDisplay(text: String) {
        textView.text=text
        if(text.contains("wins")){
            DisableButton()
        }else{

        }


    }
    private fun DisableButton(){
        for(i in board){
            for (button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text =if(player) "X" else "0"
        val value =if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        boardStatus[row][col]=value

    }
    private fun initializeBoardStatus() {
        updateDisplay(" X Turn")
        for (i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }
        for (i in board){
            for(button in i){
                button.isEnabled= true
                button.text=""
            }
        }

    }



}