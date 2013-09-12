/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.punegdg.kinosense.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHandler class to store data in SQLite database. Data objects that are persistent in KinoSense Application are to be added in database
 * 
 * @author "Ashish Kalbhor"<ashish.kalbhor@gmail.com>
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "KINO_DB";

    // Table name
    private static final String TABLE_SIM_INFO = "simcardinfo";

    // Table Column names
    private static final String KEY_ID = "id"; // Primary Key
    private static final String KEY_SERIAL = "simSerialNumber";

    public DatabaseHandler(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called each time during instantiation
     * 
     * @param SQLiteDatabase
     */
    @Override
    public void onCreate(final SQLiteDatabase db) {
        String CREATE_SIM_TABLE = "CREATE TABLE " + TABLE_SIM_INFO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SERIAL + " TEXT" + ")";
        db.execSQL(CREATE_SIM_TABLE);
    }

    /**
     * Called whenever Database is updated/modified
     * 
     * @param SQLiteDatabase
     * @param OldVersion
     * @param NewVerison
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIM_INFO);

        // Create tables again
        this.onCreate(db);
    }

    /**
     * Method to Insert data into Sim-Info Table of the Database
     * 
     * @param serialNumber
     */
    public void addSimSerial(final String serialNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERIAL, serialNumber);

        // Inserting Row
        db.insert(TABLE_SIM_INFO, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Method to Read data from Sim-Info Table of the Database
     * 
     * @return SimSerialNumber
     */
    public String getSimSerial() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SIM_INFO, new String[] { KEY_ID, KEY_SERIAL }, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToLast();
        } else {
            return null;
        }

        String serialNumber = cursor.getString(1);
        return serialNumber;
    }
}
