package com.example.android2dgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

public  abstract  class Circle(c: Context,
                               color:Int,
                               x:Float,
                               y:Float,
                               r:Float) :  GameObject(x,y){
    var context = c
    var radius= r
    var paint = Paint()
    var color= color
    init {
        paint.setColor(color)
    }
    public override fun draw(canvas: Canvas, d: Drawable?) {
        if (canvas != null) {
            canvas.drawCircle(positionX, positionY, radius, paint)
        }
    }

    override fun update() {
    }
}