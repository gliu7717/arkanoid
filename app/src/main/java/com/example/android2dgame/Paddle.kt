package com.example.android2dgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable

class Paddle(var context: Context, var joystick: Joystick,
             var screenWidth: Int,
             var screenHieght:Int ) :GameObject((screenWidth * 3f)/8f,screenHieght - 105f) {

    val width = screenWidth/4
    val height = 25


    override fun draw(canvas: Canvas, d: Drawable?) {
        if (canvas != null) {
            d!!.setBounds(positionX.toInt(), positionY.toInt(), positionX.toInt()+ width, positionY.toInt() + height)
            d!!.draw(canvas)
        }
    }


    override fun update() {
        velocityX = joystick.actuatorX* GameObject.MAX_SPEED
        //velocityY = joystick.actuatorY* GameObject.MAX_SPEED
        positionX += velocityX
        if(positionX < -12f)
            positionX = -12f
        if(positionX > screenWidth - width + 12)
            positionX = screenWidth - width.toFloat() + 12
        //positionY += velocityY
    }
}