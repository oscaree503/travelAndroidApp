package com.sncss.haemtravel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sncss.haemtravel.Bean.MeineDatenGetSet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by developer1 on 11/3/16.
 * uses by seeting activity tab 1 to perform the sql opertaion
 */
public class MeineDaten extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "meineDaten_DB";


    public MeineDaten(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE meinedetan_table   (  id   INTEGER PRIMARY KEY  NOT NULL ,   name   TEXT,  strabe TEXT,  plz_ort_code   TEXT,   plz_ort_main   TEXT,   telefon_ort_code   TEXT,   telefon_ort_main   TEXT,   e_mail   TEXT,   geburtstag   TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS meinedetan_table");
        this.onCreate(db);
    }

    private static final String TABLE_MEINE_DATEN = "meinedetan_table";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME="name";
    private static final String KEY_STRABE="strabe";
    private static final String KEY_PLZORT_CODE="plz_ort_code";
    private static final String KEY_PLZORT_MAIN="plz_ort_main";
    private static final String KEY_TELEFON_CODE="telefon_ort_code";
    private static final String KEY_TELEFON_MAIN="telefon_ort_main";
    private static final String KEY_EMAIL="e_mail";
    private static final String KEY_GEBURTSTAG="geburtstag";


    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_STRABE,KEY_PLZORT_CODE,KEY_PLZORT_MAIN,KEY_TELEFON_CODE,KEY_TELEFON_MAIN,KEY_EMAIL,KEY_GEBURTSTAG};

    public void meineDaten_add(MeineDatenGetSet meineDatenGetSet){
        Log.d("meineDaten_data", meineDatenGetSet.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, meineDatenGetSet.getName());
        values.put(KEY_STRABE, meineDatenGetSet.getStrabe());
        values.put(KEY_PLZORT_CODE, meineDatenGetSet.getPlz_ort_code());
        values.put(KEY_PLZORT_MAIN, meineDatenGetSet.getPlz_ort_main());
        values.put(KEY_TELEFON_CODE, meineDatenGetSet.getTelefon_code());
        values.put(KEY_TELEFON_MAIN, meineDatenGetSet.getTelefon_main());
        values.put(KEY_EMAIL, meineDatenGetSet.getE_mail());
        values.put(KEY_GEBURTSTAG, meineDatenGetSet.getGeburtstag());
        db.insert(TABLE_MEINE_DATEN, null, values);
        db.close();
    }


    public MeineDatenGetSet getMeineDaten(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEINE_DATEN, COLUMNS, " id = 1", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        MeineDatenGetSet meineDaten = new MeineDatenGetSet();
        meineDaten.setName(cursor.getString(1));
        meineDaten.setStrabe(cursor.getString(2));
        meineDaten.setPlz_ort_code(cursor.getString(3));
        meineDaten.setPlz_ort_main(cursor.getString(4));
        meineDaten.setTelefon_code(cursor.getString(5));
        meineDaten.setTelefon_main(cursor.getString(6));
        meineDaten.setE_mail(cursor.getString(7));
        meineDaten.setGeburtstag(cursor.getString(8));
        return meineDaten;
    }

    public int updateMeinDaten(MeineDatenGetSet meineDatenGetSet){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", meineDatenGetSet.getName());
        contentValues.put("strabe", meineDatenGetSet.getStrabe());
        contentValues.put("plz_ort_code", meineDatenGetSet.getPlz_ort_code());
        contentValues.put("plz_ort_main", meineDatenGetSet.getPlz_ort_main());
        contentValues.put("telefon_ort_code", meineDatenGetSet.getTelefon_code());
        contentValues.put("telefon_ort_main", meineDatenGetSet.getTelefon_main());
        contentValues.put("e_mail", meineDatenGetSet.getE_mail());
        contentValues.put("geburtstag", meineDatenGetSet.getGeburtstag());
        int i = database.update(TABLE_MEINE_DATEN, contentValues,KEY_ID+"=?",new String[]{String.valueOf(meineDatenGetSet.getId())});
        database.close();
        return i;
    }


    public List<MeineDatenGetSet>getAllDetails(){

        List<MeineDatenGetSet> meineDatenGetSetList = new LinkedList<>();
        String query = "select * from "+TABLE_MEINE_DATEN;
        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery(query, null);
        MeineDatenGetSet meineDatenGetSet = null;
        if(cursor.moveToFirst()){
            do{
                meineDatenGetSet = new MeineDatenGetSet();
                meineDatenGetSet.setId(Integer.parseInt(cursor.getString(0)));
                meineDatenGetSet.setName(cursor.getString(1));
                meineDatenGetSet.setStrabe(cursor.getString(2));
                meineDatenGetSet.setPlz_ort_code(cursor.getString(3));
                meineDatenGetSet.setPlz_ort_main(cursor.getString(4));
                meineDatenGetSet.setTelefon_code(cursor.getString(5));
                meineDatenGetSet.setTelefon_main(cursor.getString(6));
                meineDatenGetSet.setE_mail(cursor.getString(7));
                meineDatenGetSet.setGeburtstag(cursor.getString(8));
            }while (cursor.moveToNext());
        }
        Log.d("getAll Details",meineDatenGetSet.toString());
    return getAllDetails();
    }

}
