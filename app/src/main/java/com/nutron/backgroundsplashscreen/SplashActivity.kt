package com.nutron.backgroundsplashscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** No need to create layout for splash screen **/
//       setContentView(R.layout.activirty_splash)

        // Initial some stuff that take time before go to MainActivity
        initialSomeTakeTimeStuffs()
    }

    private fun initialSomeTakeTimeStuffs() {
        val dummyProcessTime = 3L
        val disposable = Observable.timer(dummyProcessTime, TimeUnit.SECONDS).subscribe {
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }
        disposeBag.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}
