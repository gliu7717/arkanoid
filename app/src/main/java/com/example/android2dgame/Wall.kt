package com.example.android2dgame

import android.R
import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable


class Wall(var context: Context, var rows:Int, var colomn:Int, var screenWidth:Int, var screenHeight:Int) {
    lateinit var matrix: Array<Array<Brick>>
    init {
        val width = screenWidth / colomn
        val height = 100
        matrix = Array(rows) { row ->
            Array(colomn) { col ->
                Brick(row, col,width,height)
            }
        }
    }

    fun findBrick(x: Int, y:Int, w:Int, h:Int) : Brick?
    {
        for(i in 0..matrix.size - 1)
            for(j in 0..matrix[i].size -1) {
                if(!matrix[i][j].destroyed) {
                    // left top -> (j*matrix[i][j].w,i*matrix[i][j].h ,
                    // right bottom  ->   (j+1)*matrix[i][j].w, (i+1)*matrix[i][j].h)
                    val r1 = Rect(x,y,x+w, y+h)
                    val r2 = Rect(j*matrix[i][j].w,i*matrix[i][j].h ,
                        (j+1)*matrix[i][j].w, (i+1)*matrix[i][j].h)
                    if(r1.intersect(r2))
                        return matrix[i][j]
                }
            }
        return null
    }

    fun draw(canvas: Canvas?, d:Drawable) {
        if (canvas != null) {
            for(i in 0..matrix.size - 1)
                for(j in 0..matrix[i].size -1) {
                    if(!matrix[i][j].destroyed) {
                        d.setBounds(j*matrix[i][j].w,i*matrix[i][j].h ,
                            (j+1)*matrix[i][j].w, (i+1)*matrix[i][j].h)
                        d.draw(canvas)
                    }
                }
        }
    }

    fun update()
    {

    }
}