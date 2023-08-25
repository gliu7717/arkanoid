package com.example.android2dgame

import android.view.SurfaceHolder

class GameLoop(game: Game, surfaceHolder: SurfaceHolder) : Thread(){
    private var averageFPS: Double = 0.0
    private var averageUPS :Double = 0.0
    var MAX_UPS = 30.0
    var UPS_PERIOD = 1E3/MAX_UPS

    var isRunning = false
    var mgame = game
    var msurfaceHolder = surfaceHolder
    fun getAverageUPS(): Double {
        return averageUPS
    }

    fun getAverageFPS(): Double {
        return averageFPS
    }
    public fun startLoop(){
        isRunning = true
        start()
    }

    override fun run() {
        super.run()
        var updateCount = 0
        var frameCount = 0
        var startTime = System.currentTimeMillis()
        var elapsedTime = 0L
        var sleepTime = 0.0

        while (isRunning){
            // try to update and render game
            var canvas = msurfaceHolder.lockCanvas()

            synchronized(msurfaceHolder)
            {
                mgame.update()
                updateCount ++
                mgame.draw(canvas)
            }
            msurfaceHolder.unlockCanvasAndPost(canvas)
            frameCount ++
            // pause game to  not exceed UPS
            sleepTime = (updateCount * UPS_PERIOD - elapsedTime)
            if(sleepTime> 0.0)
            {
                sleep(sleepTime.toLong())
            }
            // skip frames to keep up with target UPS
            while (sleepTime < 0.0 && updateCount < MAX_UPS -1 )
            {
                mgame.update()
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount * UPS_PERIOD - elapsedTime)

            }
            // calcuate average UPS FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if(elapsedTime >= 1000L)
            {
                averageUPS = updateCount / (1E-3 * elapsedTime)
                averageFPS = frameCount / (1E-3 * elapsedTime)
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()

            }
        }
    }

    companion object

}
