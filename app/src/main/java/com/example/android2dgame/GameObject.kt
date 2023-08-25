package com.example.android2dgame

import android.graphics.Canvas
import android.graphics.drawable.Drawable

public abstract  class GameObject (x:Float, y:Float){
    var positionX = x
    var positionY = y
    var velocityX = 0f
    var velocityY = 0f
    public abstract fun  draw(canvas: Canvas, d: Drawable? = null)
    public abstract fun  update()

    companion object {
        val  SPEED_PIXELS_PER_SECOND = 400f
        val MAX_SPEED = SPEED_PIXELS_PER_SECOND / 30f  // GameLoop.MAX_UPS
        val ENEMY_MAX_SPEED = SPEED_PIXELS_PER_SECOND / 30f *0.6f // GameLoop.MAX_UPS

        fun getDistanceBetweenObjects(obj1:GameObject, obj2:GameObject) : Float
        {
            return Math.sqrt( Math.pow(obj2.positionX.toDouble() - obj1.positionX,2.0 ) +
                    Math.pow(obj2.positionY.toDouble() - obj1.positionY,2.0 )).toFloat()
        }
    }
}