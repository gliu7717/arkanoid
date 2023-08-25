package com.example.android2dgame

import android.content.Context
import androidx.core.content.ContextCompat

class Ball(context: Context, var paddle: Paddle, var wall:Wall,
           var width: Int, var height:Int, var r: Float) :
    Circle(context, ContextCompat.getColor(context, R.color.enemy), width/2f, height/3f, r) {
    var randomNumberx = (Math.random().toFloat() - 0.5f)*2.0f
    var randomNumbery = (Math.random().toFloat() - 0.5f)*2.0f
    init {
        if(randomNumberx<0f)
            randomNumberx = -1f
        else
            randomNumberx = 1f
        if(randomNumbery<0f)
            randomNumbery = -1f
        else
            randomNumbery = 1f
        velocityX = randomNumberx*ENEMY_MAX_SPEED
        velocityY = randomNumbery*ENEMY_MAX_SPEED
    }

    override fun update() {
        super.update()
        positionX += velocityX

        var brick = wall.findBrick(positionX.toInt(), positionY.toInt(), r.toInt()*2, r.toInt()*2)
        if(brick!= null) {
            brick.destroyed = true
            velocityX = -velocityX
        }

        positionY += velocityY

        brick = wall.findBrick(positionX.toInt(), positionY.toInt(), r.toInt()*2, r.toInt()*2)
        if(brick!= null) {
            brick.destroyed = true
            velocityY = -velocityY
        }

        if(positionX<=r || positionX>= width - r)
            velocityX = -velocityX
        if(positionY<=r || positionY >= height -r )
            velocityY = -velocityY
        if( positionX <= paddle.positionX + paddle.width &&
            positionX >= paddle.positionX  &&
            positionY >= paddle.positionY - paddle.height)
            velocityY = -velocityY

    }
}
