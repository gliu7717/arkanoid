package com.example.android2dgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback  {
    private var surfaceHolder : SurfaceHolder = holder
    private var gameLoop= GameLoop(this, surfaceHolder)
    private var joystick : Joystick
    private var ball: Ball
    private var brickDrawable: Drawable
    private var paddleDrawable: Drawable
    private var wall:Wall
    private var paddle :Paddle
    init {
        holder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        joystick = Joystick(width*9f/10f, height*9f/10f,70f, 40f)
        brickDrawable = getResources().getDrawable(R.drawable.block05, null)
        paddleDrawable = getResources().getDrawable(R.drawable.paddle, null)
        wall = Wall(context, 6,5, width, height)
        paddle = Paddle(context,joystick, width, height)
        //ball = Ball(context,paddle,wall,500f, 750f, 30f)
        ball = Ball(context,paddle,wall,width, height, 30f)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (event.action == MotionEvent.ACTION_DOWN)
            {
                if(joystick.isPressed(event.getX()  ,  event.getY())){
                    joystick.setIsPressed(true)
                }
                return true
                // player.setPosition( event.getX()  ,  event.getY())
            }
            else if (event.action == MotionEvent.ACTION_MOVE)
            {
                if(joystick.getIsPressed()){
                    joystick.setActuator(event.getX()  ,  event.getY())
                }
                return true
            }
            else if (event.action == MotionEvent.ACTION_UP){
                joystick.setIsPressed(false)
                joystick.resetActuator()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
    override fun surfaceCreated(p0: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)
        joystick.draw(canvas)
        wall.draw(canvas,brickDrawable )
        paddle.draw(canvas,paddleDrawable )
        ball.draw(canvas)
    }
    public fun drawUPS(canvas: Canvas?){
        var averageUPS : String
        averageUPS =gameLoop.getAverageUPS().toString()
        var paint = Paint()
        var color = ContextCompat.getColor(context, R.color.magenta)
        paint.color = color
        paint.textSize = 60F
        if (canvas != null) {
            canvas.drawText("UPS: " + averageUPS,100f, 100f,paint)
        }
    }
    public fun drawFPS(canvas: Canvas?){
        var averageFPS : String
        averageFPS =gameLoop.getAverageFPS().toString()
        var paint = Paint()
        var color = ContextCompat.getColor(context, R.color.magenta)
        paint.setColor(color)
        paint.textSize = 60F
        if (canvas != null) {
            canvas.drawText("FPS: " + averageFPS,100f, 200f,paint)
        }
    }

    fun update() {
        joystick.update()
        paddle.update()
        ball.update()
    }
}
