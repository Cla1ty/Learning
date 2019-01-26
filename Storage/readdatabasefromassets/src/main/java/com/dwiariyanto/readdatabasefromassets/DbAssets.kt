package com.dwiariyanto.readdatabasefromassets

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by Dwi Ariyanto on 26/01/19.
 */
class DbAssets(private val context: Context, private val name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    private var database: SQLiteDatabase? = null
    private val path by lazy { context.filesDir.path + name }
    private val file by lazy { File(path) }

    init {
        createDatabase()
    }

    override fun getWritableDatabase(): SQLiteDatabase {
        synchronized(this) {
            if (database == null) {
                database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE)
            }
            return database!!
        }
    }

    override fun getReadableDatabase() = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        createDatabase()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        deleteDatabase()
        createDatabase()
    }

    override fun close() {
        super.close()

        if (database?.isOpen == true) {
            database?.close()
            database = null
        }
    }

    private fun createDatabase() {
        if (!file.exists()) {
            try {
                copyDataBase()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun copyDataBase() {
        val mInput = context.assets.open(name)
        Log.d("Trace", "INPUT $mInput")
        val mOutput = FileOutputStream(path)
        val mBuffer = ByteArray(2024)
        var mLength: Int = mInput.read(mBuffer)
        while ((mLength) > 0) {
            mOutput.write(mBuffer, 0, mLength)
            mLength = mInput.read(mBuffer)
        }
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    private fun deleteDatabase() {
        if (file.exists()) {
            file.delete()
            println("delete database file.")
        }
    }
}
