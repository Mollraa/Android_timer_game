package com.example.prac01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random
import java.util.Timer
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    var p_num = 3
    var j = 1
    val point_list = mutableListOf<Float>()

    fun start(){
        setContentView(R.layout.start)
    }


    fun main() {
        setContentView(R.layout.activity_main) //activity_main.xml의 화면 불러와라

        var timerTask: Timer? = null
        var stage = 1 //첫번째 경우
        var sec: Int = 0 //조기값
        val rn: TextView = findViewById(R.id.randomNum)
        val tn: TextView = findViewById(R.id.timerNum)
        val pn: TextView = findViewById(R.id.pointNum)
        val perN: TextView = findViewById(R.id.pNum)
        val btn: Button = findViewById(R.id.button)
        val random_box = Random()
        val num = random_box.nextInt(1001)

        rn.text = ((num.toFloat()) / 100).toString() //랜덤 숫자 표기
        btn.text = "시작"
        perN.text = "참가자 $j"

        btn.setOnClickListener {
            stage++
            if (stage == 2) { //시작 누름
                timerTask = kotlin.concurrent.timer(period = 10) {
                    sec++
                    runOnUiThread {
                        tn.text = (sec.toFloat() / 100).toString()
                    }
                }
                btn.text = "정지"
            } else if(stage == 3){ //정지 누름
                timerTask?.cancel()
                val point = abs(sec - num).toFloat() / 100
                point_list.add(point) //리스트에 포인트 담아줌
                pn.text = point.toString()
                btn.text = "다음"
                stage = 0
            } else if (stage == 1){ //다음 누르면 초기화
                if(j < p_num){
                    j++
                    main()
                } else{
                    println(point_list)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }
}