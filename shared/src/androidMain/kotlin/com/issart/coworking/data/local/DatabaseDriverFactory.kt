package com.issart.coworking.data.local

import android.content.Context
import com.issart.coworking.database.CoworkingDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CoworkingDatabase.Schema, context, "coworkingDB.db")
    }
}