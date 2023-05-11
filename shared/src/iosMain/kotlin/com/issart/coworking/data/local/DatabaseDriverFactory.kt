package com.issart.coworking.data.local

import com.issart.coworking.database.CoworkingDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CoworkingDatabase.Schema, "coworkingDB.db")
    }
}