package com.example.android2dgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Joystick (centerPositionX: Float,
                centerPositionY: Float,
                outCircleRadius:Float,
                innerCircleRadius:Float
               ){
    private var outCircleCenterPositionX = centerPositionX
    private var outCircleCenterPositionY = centerPositionY
    private var innerCircleCenterPositionX = centerPositionX
    private var innerCircleCenterPositionY = centerPositionY
    private var outCircleRadius = outCircleRadius
    private var innerCircleRadius = innerCircleRadius
    private var outerCirclePaint = Paint()
    private var innerCirclePaint = Paint()
    private var isPressed = false
    var actuatorX=0f
    var actuatorY=0f

    init {
        outerCirclePaint.setColor(Color.GRAY)
        outerCirclePaint.style = Paint.Style.FILL_AND_STROKE
        innerCirclePaint.setColor(Color.BLUE)
        innerCirclePaint.style = Paint.Style.FILL_AND_STROKE
    }

    fun draw(canvas: Canvas?) {
        if (canvas != null) {
            canvas.drawCircle(outCircleCenterPositionX,
                outCircleCenterPositionY,
                outCircleRadius,
                outerCirclePaint)
            canvas.drawCircle(innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint)
        }
    }

    fun update() {
        updateInnerCirclePosition()
    }
    fun updateInnerCirclePosition()
    {
        innerCircleCenterPositionX = outCircleCenterPositionX +actuatorX * outCircleRadius
        innerCircleCenterPositionY = outCircleCenterPositionY +actuatorY * outCircleRadius
    }

    fun isPressed(x: Float, y: Float): Boolean {
        var joystickCentertoTouchDistance = Math.sqrt(
            Math.pow((outCircleCenterPositionX -x).toDouble(), 2.0) +
                    Math.pow((outCircleCenterPositionX -x).toDouble(), 2.0)
        )
        return joystickCentertoTouchDistance < outCircleRadius

    }

    fun setIsPressed(isPressed: Boolean) {
        this.isPressed = isPressed
    }

    fun getIsPressed(): Boolean {
        return isPressed
    }

    fun setActuator(x: Float, y: Float) {
        var deltaX = x - outCircleCenterPositionX
        var deltaY = y - outCircleCenterPositionY
        var deltaDistance = Math.sqrt( deltaX.toDouble() * deltaX.toDouble() +
                        deltaY.toDouble()*deltaY.toDouble())
        if(deltaDistance < outCircleRadius){
            actuatorX = deltaX / outCircleRadius
            actuatorY = deltaY / outCircleRadius
        }else{
            actuatorX = (deltaX / deltaDistance).toFloat()
            actuatorY = (deltaY / deltaDistance).toFloat()
        }
    }

    fun resetActuator() {
        actuatorX = 0f
        actuatorY=0f
    }

}
