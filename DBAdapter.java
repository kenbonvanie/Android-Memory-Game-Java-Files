package com.kennethJoseph92.ken.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ken on 10/26/2014.
 */
public class DBAdapter {

    // lianne

    static final String TAG = "DBAdapter";

    // mine
    static final String KEY_ROWID_Mine = "_id";
    static final String KEY_NAME_Mine = "name";
    static final String KEY_Score = "score";


    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "info";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE_Mine =
            "create table info (_id integer primary key autoincrement, "
                    + "name text not null, score integer not null );";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                // put stuff here
                //db.execSQL("drop table contacts;");
                db.execSQL(DATABASE_CREATE_Mine);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertContact(String name, int score)
    {
        db.execSQL("drop table contacts;");
        //db.execSQL(DATABASE_CREATE_Mine);

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME_Mine, name);
        initialValues.put(KEY_Score, score);
        return db.insert(DATABASE_TABLE, null, initialValues);

    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID_Mine + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID_Mine, KEY_NAME_Mine,
                KEY_Score}, null, null, null, null, KEY_NAME_Mine, null);
    }

    public Cursor getAllContactsOrderByScore()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID_Mine, KEY_NAME_Mine,
                KEY_Score}, null, null, null, null, KEY_Score + " DESC", null);
    }


    public Cursor getScoreByPlayersName()
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[]{KEY_Score}, null, null,
                        null, null, "score", "1");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // insert user
    public void insertPlayer()
    {
        db.execSQL("insert into info(name, score)values('user', 0);");
    }


    // my update clause
    public void updatePlayersHighScore(int oldScore, int newScore )
    {
        //db.execSQL("update info set score = " + newScore + " where name = '" + name + "' and score = " + oldScore + ";");
        db.execSQL("update info set score = " + newScore + " where score = " + oldScore + ";");
        //db.execSQL(DATABASE_CREATE_Mine);
    }




}
