package com.dika.quranku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dika.quranku.Modules.Database.DatabaseHelper
import com.dika.quranku.Modules.ImportFromFile.Literation
import com.dika.quranku.Modules.ImportFromFile.LiterationInteractor

class MainActivity : AppCompatActivity(), Literation {

    val interactor = LiterationInteractor(this, this)
    val databaseHelper = DatabaseHelper(this)

    override fun successInputDatabase() {
        openNextActivity()
    }

    override fun failedInputDatabase() {
        databaseHelper.clearTable()
        interactor.setData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (databaseHelper.isDataAvailable()){
            openNextActivity()
        }else{
            databaseHelper.clearTable()
            interactor.setData()
        }
    }

    fun openNextActivity(){
        val intent = Intent(this, ListSurahActivity::class.java)
        startActivity(intent)
        finish()
    }
}
