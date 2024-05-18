package ru.je_dog.core.feature.base.service.vibration

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class VibrationServiceImpl(
    private val context: Context
): VibrationService {

    override fun vibrate(
        interval: Long
    ) {
        val vibrationEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createOneShot(
                interval,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        }else {
            null
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            require(vibrationEffect != null)
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val combinedVibration = CombinedVibration.createParallel(vibrationEffect)

            vibratorManager.vibrate(combinedVibration)
        } else {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                require(vibrationEffect != null)
                vibrator.vibrate(vibrationEffect)
            }else {
                vibrator.vibrate(interval)
            }
        }
    }
}