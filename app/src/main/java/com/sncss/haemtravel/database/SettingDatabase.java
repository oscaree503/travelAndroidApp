package com.sncss.haemtravel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sncss.haemtravel.Bean.DiagnoseBehandlungGetSet;
import com.sncss.haemtravel.Bean.DoctorBean;
import com.sncss.haemtravel.Bean.HeimatzentrumBean;
import com.sncss.haemtravel.Bean.MeinFavorites;
import com.sncss.haemtravel.Bean.MeineDatenGetSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer1 on 11/3/16.
 * All main database action are performed from here
 */
public class SettingDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "setting_DB";

    private static final String TABLE_MEINE_DATEN = "meinedetan_tbl";
    private static final String KEY_ID_M_D = "id";
    private static final String KEY_NAME="name";
    private static final String KEY_STRABE="strabe";
    private static final String KEY_PLZORT_CODE="plz_ort_code";
    private static final String KEY_PLZORT_MAIN="plz_ort_main";
    private static final String KEY_TELEFON_CODE="telefon_ort_code";
    private static final String KEY_TELEFON_MAIN="telefon_ort_main";
    private static final String KEY_EMAIL="e_mail";
    private static final String KEY_GEBURTSTAG="geburtstag";
    String CREATE_TABLE_MEINEDETAN = "CREATE TABLE IF NOT EXISTS meinedetan_tbl  ( id  INTEGER,  name  TEXT,  strabe  TEXT,  plz_ort_code  INTEGER NULL,  plz_ort_main  INTEGER NULL,  telefon_ort_code  INTEGER,  telefon_ort_main  INTEGER,  e_mail  TEXT,  geburtstag  TEXT);";
   // String INSERT_MEINEDETAN_ROW = "INSERT INTO  meinedetan_tbl  VALUES           (1,'demo','demo', '456', 'Indore', +49, 123456, 'demo@gmail.com', '12-03-2016');";
    private static final String[] COLUMNS_MEINDATEN = {KEY_ID_M_D,KEY_NAME,KEY_STRABE,KEY_PLZORT_CODE,KEY_PLZORT_MAIN,KEY_TELEFON_CODE,KEY_TELEFON_MAIN,KEY_EMAIL,KEY_GEBURTSTAG};


    private static final String TABLE_DIAGNOSE_BEHANDLUNG = "diabehand_tbl";
    private static final String KEY_ID_D_B = "id";
    private static final String KEY_DIAGNOSE = "diagnose";
    private static final String KEY_RESTAKTIVITAT = "restaktivitat";
    private static final String KEY_THERAPIEART = "therapieart";
    private static final String KEY_MEINFATOR = "meinfaktor";
    private static final String KEY_DOSIERUNG = "dosierung";
    private static final String KEY_DOSIERUNGSFREQUEN = "dosierungsfrequen";
    String CREATE_TABLE_DIAGNOSEBEHANDLUNG = "CREATE TABLE IF NOT EXISTS diabehand_tbl (  id   INTEGER,   diagnose   TEXT,   restaktivitat   TEXT,   therapieart   TEXT,   meinfaktor   TEXT,   dosierung   TEXT,   dosierungsfrequen  TEXT);";
  //  String INSERT_DIAGNOSEBEHANDLUNG_ROW ="INSERT INTO diabehand_tbl  VALUES ( 1, 'Dummy' , 0, 'Dummy', '5', 'Dummy', 'Dummy');";
    private static final String[] COLUMNS_DIAGNOSEBEHANDLUNG = {KEY_ID_D_B,KEY_DIAGNOSE,KEY_RESTAKTIVITAT,KEY_THERAPIEART,KEY_MEINFATOR,KEY_DOSIERUNG,KEY_DOSIERUNGSFREQUEN};


    private static final String TABLE_HEIMANTZENTRUM = "heimatzentrum_tbl";
    private static final String KEY_ID_H = "id";
    private static final String KEY_KLINIK_H = "klinik";
    private static final String KEY_STRABE_H = "strabe";
    private static final String KEY_Center_ID_H = "center_id_h";
    private static final String KEY_PLZORT_CODE_H = "plz_ort_code";
    private static final String KEY_PLZORT_MAIN_H = "plz_ort_main";
    private static final String KEY_TELEFON_CODE_H = "telefon_ort_code";
    private static final String KEY_TELEFON_MAIN_H = "telefon_ort_main";
    private static final String KEY_TELEFON_CODE2_H = "telefon_ort_code2";
    private static final String KEY_TELEFON_MAIN2_H = "telefon_ort_main2";
    private static final String KEY_KONTAKTPERSON_TELEFON_NAME = "kontaktperson_name";
    private static final String KEY_TELEFON_CODE3_H = "telefon_ort_code3";
    private static final String KEY_TELEFON_MAIN3_H = "telefon_ort_main3";
    private static final String KEY_TELEFON_CODE4_H = "telefon_ort_code4";
    private static final String KEY_TELEFON_MAIN4_H = "telefon_ort_main4";
    private static final String KEY_KOMMENTAR = "kommentar";

    String CREATE_TABLE_HEIMANTZENTRUM = "CREATE TABLE IF NOT EXISTS heimatzentrum_tbl  ( id  INTEGER,  klinik  TEXT,  strabe  TEXT,  plz_ort_code  NUMERIC NULL,  plz_ort_main  NUMERIC NULL, telefon_ort_main  NUMERIC,  telefon_ort_main2  NUMERIC, email  TEXT, kontaktperson_name  TEXT,  telefon_ort_main3  NUMERIC,  telefon_ort_main4  NUMERIC,  kommentar  TEXT, center_id_h TEXT);";
    private static final String[] COLUMNS_HEIMANTZENTRUM = {KEY_ID_H, KEY_KLINIK_H, KEY_STRABE_H, KEY_PLZORT_CODE_H, KEY_PLZORT_MAIN_H, KEY_TELEFON_CODE_H, KEY_TELEFON_MAIN_H,
            KEY_TELEFON_CODE2_H, KEY_TELEFON_MAIN2_H, KEY_KONTAKTPERSON_TELEFON_NAME, KEY_TELEFON_CODE3_H, KEY_TELEFON_MAIN3_H, KEY_TELEFON_CODE4_H, KEY_TELEFON_MAIN4_H, KEY_KOMMENTAR, KEY_Center_ID_H};

    String CREATE_TABLE_DOCTOR = "CREATE TABLE IF NOT EXISTS doctor  ( id  INTEGER PRIMARY KEY AUTOINCREMENT, center_id TEXT , klinik  TEXT, telefon_ort_code  NUMERIC,  telefon_ort_main  NUMERIC,  email  TEXT);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEINEDETAN);
        db.execSQL(CREATE_TABLE_DIAGNOSEBEHANDLUNG);
        db.execSQL(CREATE_TABLE_HEIMANTZENTRUM);
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_MEINFAVORITEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS meinedetan_tbl");
          db.execSQL("DROP TABLE IF EXISTS heimatzentrum_tbl");
        db.execSQL("DROP TABLE IF EXISTS diagnosebehandlung_tbl");
        this.onCreate(db);*/
    }

    public SettingDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public int getHDCount(){
        String countQuery = "SELECT  * FROM "+TABLE_HEIMANTZENTRUM;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery(countQuery, null);
        return cursor.getCount();

    }

    public long heimatzentrum_add(HeimatzentrumBean heimatzentrumBean) {
        Log.d("heimatzentrum_add", heimatzentrumBean.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_H, heimatzentrumBean.getId());

        values.put(KEY_KLINIK_H, heimatzentrumBean.getKlinik());
        values.put(KEY_STRABE_H, heimatzentrumBean.getStrabe());

        values.put(KEY_PLZORT_CODE_H, heimatzentrumBean.getPlz_ort_code());
        values.put(KEY_PLZORT_MAIN_H, heimatzentrumBean.getPlz_ort_main());

        values.put(KEY_TELEFON_MAIN_H, heimatzentrumBean.getTelefon_ort_main());

        values.put(KEY_TELEFON_MAIN2_H, heimatzentrumBean.getTelefon_ort_main2());

        values.put("email", heimatzentrumBean.getEmail());

        values.put(KEY_KONTAKTPERSON_TELEFON_NAME, heimatzentrumBean.getKontaktperson_name());

        values.put(KEY_TELEFON_MAIN3_H, heimatzentrumBean.getTelefon_ort_main3());

        values.put(KEY_TELEFON_MAIN4_H, heimatzentrumBean.getTelefon_ort_main4());

        values.put(KEY_KOMMENTAR, heimatzentrumBean.getKommentar());
        values.put(KEY_Center_ID_H,heimatzentrumBean.getCenterID());

        long i = db.insert(TABLE_HEIMANTZENTRUM, null, values);
        db.close();
        return i;
    }

    public HeimatzentrumBean heimatzentrumBean_get(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HEIMANTZENTRUM, COLUMNS_HEIMANTZENTRUM, " id = 1", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        HeimatzentrumBean heimatzentrumBean = new HeimatzentrumBean();

        heimatzentrumBean.setKlinik(cursor.getString(1));
        heimatzentrumBean.setStrabe(cursor.getString(2));
        heimatzentrumBean.setPlz_ort_code(cursor.getString(3));
        heimatzentrumBean.setPlz_ort_main(cursor.getString(4));
        heimatzentrumBean.setTelefon_ort_code(cursor.getString(5));
        heimatzentrumBean.setTelefon_ort_main(cursor.getString(6));
        heimatzentrumBean.setTelefon_ort_code2(cursor.getString(7));
        heimatzentrumBean.setTelefon_ort_main2(cursor.getString(8));
        heimatzentrumBean.setKontaktperson_name(cursor.getString(9));
        heimatzentrumBean.setTelefon_ort_code3(cursor.getString(10));
        heimatzentrumBean.setTelefon_ort_main3(cursor.getString(11));
        heimatzentrumBean.setTelefon_ort_code4(cursor.getString(12));
        heimatzentrumBean.setTelefon_ort_main4(cursor.getString(13));
        heimatzentrumBean.setKommentar(cursor.getString(14));
        heimatzentrumBean.setCenterID(cursor.getString(15));
        return heimatzentrumBean;
    }


    public long updateHeimatzentrum(HeimatzentrumBean heimatzentrumBean){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", heimatzentrumBean.getId());
        contentValues.put("klinik", heimatzentrumBean.getKlinik());
        contentValues.put("strabe", heimatzentrumBean.getStrabe());
        contentValues.put("plz_ort_code", heimatzentrumBean.getPlz_ort_code());
        contentValues.put("plz_ort_main", heimatzentrumBean.getPlz_ort_main());
       // contentValues.put("telefon_ort_code", heimatzentrumBean.getTelefon_ort_code());
        contentValues.put("telefon_ort_main", heimatzentrumBean.getTelefon_ort_main());
        //contentValues.put("telefon_ort_code2", heimatzentrumBean.getTelefon_ort_code2());
        contentValues.put("telefon_ort_main2", heimatzentrumBean.getTelefon_ort_main2());
        contentValues.put("email", heimatzentrumBean.getEmail());
        contentValues.put("kontaktperson_name", heimatzentrumBean.getKontaktperson_name());
        //contentValues.put("telefon_ort_code3", heimatzentrumBean.getTelefon_ort_code3());
        contentValues.put("telefon_ort_main3", heimatzentrumBean.getTelefon_ort_main3());
        //contentValues.put("telefon_ort_code4", heimatzentrumBean.getTelefon_ort_code4());
        contentValues.put("telefon_ort_main4", heimatzentrumBean.getTelefon_ort_main4());
        contentValues.put("kommentar", heimatzentrumBean.getKommentar());
        contentValues.put(KEY_Center_ID_H,heimatzentrumBean.getCenterID());
        long i = database.update(TABLE_HEIMANTZENTRUM, contentValues,KEY_ID_H+" =?",new String[]{String.valueOf(heimatzentrumBean.getId())});
        database.close();
        return i;
    }
    // DELETE_ROW_MEINFAVORITEN
    public void deletHeimatzentrum(String centerID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HEIMANTZENTRUM, KEY_Center_ID_H + " = ? ", new String[]{String.valueOf(centerID)});
    }
    public boolean checkHeimatzentrum(String centerId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_HEIMANTZENTRUM+" WHERE "+KEY_Center_ID_H+" LIKE'" + centerId + "'", null);
        if(c.moveToFirst())
        {
            return true;
        }else {
            return false;
        }    }

    public List<HeimatzentrumBean> getAllDetails_H(){
        List<HeimatzentrumBean> heimatzentrumBeansList = new ArrayList<>();
        String query = "select * from "+TABLE_HEIMANTZENTRUM;
        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery(query, null);
        HeimatzentrumBean heimatzentrumBean = null;
        if(cursor.moveToFirst()){
            do{
                heimatzentrumBean = new HeimatzentrumBean();
                heimatzentrumBean.setId(Integer.parseInt(cursor.getString(0)));
                heimatzentrumBean.setKlinik(cursor.getString(1));
                heimatzentrumBean.setStrabe(cursor.getString(2));
                heimatzentrumBean.setPlz_ort_code(cursor.getString(3));
                heimatzentrumBean.setPlz_ort_main(cursor.getString(4));
               // heimatzentrumBean.setTelefon_ort_code(cursor.getString(5));
                heimatzentrumBean.setTelefon_ort_main(cursor.getString(5));
               // heimatzentrumBean.setTelefon_ort_code2(cursor.getString(7));
                heimatzentrumBean.setTelefon_ort_main2(cursor.getString(6));
                heimatzentrumBean.setEmail(cursor.getString(7));
                heimatzentrumBean.setKontaktperson_name(cursor.getString(8));
              //  heimatzentrumBean.setTelefon_ort_code3(cursor.getString(10));
                heimatzentrumBean.setTelefon_ort_main3(cursor.getString(9));
              //  heimatzentrumBean.setTelefon_ort_code4(cursor.getString(12));
                heimatzentrumBean.setTelefon_ort_main4(cursor.getString(10));
                heimatzentrumBean.setKommentar(cursor.getString(11));
                heimatzentrumBean.setCenterID(cursor.getString(12));
                heimatzentrumBeansList.add(heimatzentrumBean);
            }while (cursor.moveToNext());
        }
        Log.d("getAll Details", heimatzentrumBean.toString());
        return heimatzentrumBeansList;
    }


    public int getDBCount(){
        String countQuery = "SELECT  * FROM "+TABLE_DIAGNOSE_BEHANDLUNG;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public long diagnosebehandlung_add(DiagnoseBehandlungGetSet diagnoseBehandlungGetSet){
        Log.d("diagnosebehandlung_add", diagnoseBehandlungGetSet.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_D_B,diagnoseBehandlungGetSet.getId());
        values.put(KEY_DIAGNOSE, diagnoseBehandlungGetSet.getDiagnose());
        values.put(KEY_RESTAKTIVITAT, diagnoseBehandlungGetSet.getRestaktivitat());
        values.put(KEY_THERAPIEART, diagnoseBehandlungGetSet.getTherapieart());
        values.put(KEY_MEINFATOR, diagnoseBehandlungGetSet.getMeinfaktor());
        values.put(KEY_DOSIERUNG, diagnoseBehandlungGetSet.getDosierung());
        values.put(KEY_DOSIERUNGSFREQUEN, diagnoseBehandlungGetSet.getDosierungsfrequen());
        long i = db.insert(TABLE_DIAGNOSE_BEHANDLUNG, null, values);
        db.close();
        return i;
    }

    public DiagnoseBehandlungGetSet behandlungGetSet(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DIAGNOSE_BEHANDLUNG, COLUMNS_DIAGNOSEBEHANDLUNG, " id = 1", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DiagnoseBehandlungGetSet diagnoseBehandlungGetSet = new DiagnoseBehandlungGetSet();
        diagnoseBehandlungGetSet.setDiagnose(cursor.getString(1));
        diagnoseBehandlungGetSet.setRestaktivitat(cursor.getString(2));
        diagnoseBehandlungGetSet.setTherapieart(cursor.getString(3));
        diagnoseBehandlungGetSet.setMeinfaktor(cursor.getString(4));
        diagnoseBehandlungGetSet.setDosierung(cursor.getString(5));
        diagnoseBehandlungGetSet.setDosierungsfrequen(cursor.getString(6));
        return diagnoseBehandlungGetSet;
    }


    public int updateDiagnoseBehandlung(DiagnoseBehandlungGetSet diagnoseBehandlungGetSet){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();/*
        contentValues.put("id",diagnoseBehandlungGetSet.getId());*/
        contentValues.put("diagnose",diagnoseBehandlungGetSet.getDiagnose());
        contentValues.put("restaktivitat", diagnoseBehandlungGetSet.getRestaktivitat());
        contentValues.put("therapieart", diagnoseBehandlungGetSet.getTherapieart());
        contentValues.put("meinfaktor", diagnoseBehandlungGetSet.getMeinfaktor());
        contentValues.put("dosierung", diagnoseBehandlungGetSet.getDosierung());
        contentValues.put("dosierungsfrequen", diagnoseBehandlungGetSet.getDosierungsfrequen());
        int i = database.update(TABLE_DIAGNOSE_BEHANDLUNG, contentValues, KEY_ID_D_B + " = ?", new String[]{String.valueOf(diagnoseBehandlungGetSet.getId())});
        database.close();
        return i;
    }


    public List<DiagnoseBehandlungGetSet> getAllDetails_DB(){

        List<DiagnoseBehandlungGetSet> diagnoseBehandlungList = new ArrayList<>();
        String query = "select * from "+ TABLE_DIAGNOSE_BEHANDLUNG;
        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery(query, null);
        DiagnoseBehandlungGetSet diagnoseBehandlungGetSet = null;
        if(cursor!=null){
        cursor.moveToFirst();
            do{
                diagnoseBehandlungGetSet = new DiagnoseBehandlungGetSet();
                diagnoseBehandlungGetSet.setId(cursor.getInt(0));
                diagnoseBehandlungGetSet.setDiagnose(cursor.getString(1));
                diagnoseBehandlungGetSet.setRestaktivitat(cursor.getString(2));
                diagnoseBehandlungGetSet.setTherapieart(cursor.getString(3));
                diagnoseBehandlungGetSet.setMeinfaktor(cursor.getString(4));
                diagnoseBehandlungGetSet.setDosierung(cursor.getString(5));
                diagnoseBehandlungGetSet.setDosierungsfrequen(cursor.getString(6));
                diagnoseBehandlungList.add(diagnoseBehandlungGetSet);
            }while (cursor.moveToNext());
        }
        return diagnoseBehandlungList;
    }



    public Long meineDaten_add(MeineDatenGetSet meineDatenGetSet){
        Log.d("meineDaten_data", meineDatenGetSet.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_M_D,meineDatenGetSet.getId());
        values.put(KEY_NAME, meineDatenGetSet.getName());
        values.put(KEY_STRABE, meineDatenGetSet.getStrabe());
        values.put(KEY_PLZORT_CODE, meineDatenGetSet.getPlz_ort_code());
        values.put(KEY_PLZORT_MAIN, meineDatenGetSet.getPlz_ort_main());
        values.put(KEY_TELEFON_CODE, meineDatenGetSet.getTelefon_code());
        values.put(KEY_TELEFON_MAIN, meineDatenGetSet.getTelefon_main());
        values.put(KEY_EMAIL, meineDatenGetSet.getE_mail());
        values.put(KEY_GEBURTSTAG, meineDatenGetSet.getGeburtstag());
        long i = db.insert(TABLE_MEINE_DATEN, null, values);
        db.close();
        return i;
    }

    public MeineDatenGetSet getMeineDaten(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEINE_DATEN, COLUMNS_MEINDATEN, " id = 1", new String[]{String.valueOf(id)}, null, null, null, null);
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
        ContentValues contentValues = new ContentValues();/*
        contentValues.put("id", meineDatenGetSet.getId());*/
        contentValues.put("name", meineDatenGetSet.getName());
        contentValues.put("strabe", meineDatenGetSet.getStrabe());
        contentValues.put("plz_ort_code", meineDatenGetSet.getPlz_ort_code());
        contentValues.put("plz_ort_main", meineDatenGetSet.getPlz_ort_main());
        contentValues.put("telefon_ort_code", meineDatenGetSet.getTelefon_code());
        contentValues.put("telefon_ort_main", meineDatenGetSet.getTelefon_main());
        contentValues.put("e_mail", meineDatenGetSet.getE_mail());
        contentValues.put("geburtstag", meineDatenGetSet.getGeburtstag());
        int i = database.update(TABLE_MEINE_DATEN, contentValues,KEY_ID_M_D+" = ?",new String[]{String.valueOf(meineDatenGetSet.getId())});
        database.close();
        return i;
    }
    public int getMDCount(){
        String countQuery = "SELECT  * FROM "+TABLE_MEINE_DATEN;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery(countQuery, null);
        return cursor.getCount();

    }
    public List<MeineDatenGetSet> getAllDetails_MD(){

        List<MeineDatenGetSet> meineDatenGetSetList = new ArrayList<MeineDatenGetSet>();
        String query = "select * from "+TABLE_MEINE_DATEN;
        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery(query, null);
        MeineDatenGetSet meineDatenGetSet = null;
        if(cursor!=null){
            cursor.moveToFirst();
            do{
                meineDatenGetSet = new MeineDatenGetSet();
                meineDatenGetSet.setId(cursor.getInt(0));
                meineDatenGetSet.setName(cursor.getString(1));
                meineDatenGetSet.setStrabe(cursor.getString(2));
                meineDatenGetSet.setPlz_ort_code(cursor.getString(3));
                meineDatenGetSet.setPlz_ort_main(cursor.getString(4));
                meineDatenGetSet.setTelefon_code(cursor.getString(5));
                meineDatenGetSet.setTelefon_main(cursor.getString(6));
                meineDatenGetSet.setE_mail(cursor.getString(7));
                meineDatenGetSet.setGeburtstag(cursor.getString(8));
                meineDatenGetSetList.add(meineDatenGetSet);
            }while (cursor.moveToNext());
        }
//        Log.d("getAll Details", meineDatenGetSet.toString());
        return meineDatenGetSetList;
    }
    public int getDocCount() {
        String countQuery = "SELECT  * FROM doctor";
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public void setDocDetail(DoctorBean bean, String centerID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("center_id", centerID); // centerId
        values.put("klinik",bean.getDocName());
        values.put("telefon_ort_code",bean.getTelefoneCode());
        values.put("telefon_ort_main",bean.getTelefone());
        values.put("email",bean.getEmail());
        db.insert("doctor", null, values);
        db.close();

    }
    public void updateDocDetail(DoctorBean bean, int docID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("klinik",bean.getDocName());
        values.put("telefon_ort_code",bean.getTelefoneCode());
        values.put("telefon_ort_main", bean.getTelefone());
        values.put("email", bean.getEmail());
        db.update("doctor", values, "id" + " = ?", new String[]{String.valueOf(docID)});

    }
    public ArrayList<DoctorBean> getAllDoc(String  centerNum) {
        ArrayList<DoctorBean> docList = new ArrayList<DoctorBean>();
        // Select All Query
        String selectQuery = "SELECT * FROM doctor WHERE center_id ='"+centerNum+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DoctorBean doc = new DoctorBean();
                doc.setDocId(cursor.getInt(0));
                doc.setDocName(cursor.getString(2));
                doc.setTelefoneCode(cursor.getString(3));
                doc.setTelefone(cursor.getString(4));
                doc.setEmail(cursor.getString(5));
                // Adding contact to list
                docList.add(doc);
            } while (cursor.moveToNext());
        }

        // return contact list
        return docList;
    }

    public ArrayList<DoctorBean> getFirstDoc(String  centerNum) {
        ArrayList<DoctorBean> docList = new ArrayList<DoctorBean>();
        // Select All Query
        String selectQuery = "SELECT * FROM doctor WHERE center_id ='"+centerNum+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DoctorBean doc = new DoctorBean();
                doc.setDocId(cursor.getInt(0));
                doc.setDocName(cursor.getString(2));
                doc.setTelefoneCode(cursor.getString(3));
                doc.setTelefone(cursor.getString(4));
                doc.setEmail(cursor.getString(5));
                // Adding contact to list
                docList.add(doc);
            } while (cursor.moveToNext());
        }

        // return contact list
        return docList;
    }

    //  TABLE_MEINFAVORITEN
    private static final String TABLE_MEINFAVORITEN = "meinfavoriten_tbl";
    private static final String KEY_ID_MEINFAVORITEN = "id";
    private static final String KEY_COUNTRY_ISO_MEINFAVORITEN = "country_iso";
    private static final String KEY_COUNTRY_NAME_MEINFAVORITEN = "country_name";
    private static final String KEY_CENTER_ID_MEINFAVORITEN = "center_id";
    private static final String KEY_CENTER_NAME_MEINFAVORITEN = "center_name";
    String CREATE_TABLE_MEINFAVORITEN = "CREATE TABLE IF NOT EXISTS meinfavoriten_tbl (id INTEGER PRIMARY KEY  AUTOINCREMENT  UNIQUE, center_id TEXT,center_name TEXT, country_iso TEXT, country_name TEXT);";
    private static final String[] COLUMNS_MEINFAVORITEN = {KEY_ID_MEINFAVORITEN,KEY_COUNTRY_ISO_MEINFAVORITEN,KEY_COUNTRY_NAME_MEINFAVORITEN,KEY_CENTER_ID_MEINFAVORITEN,KEY_CENTER_NAME_MEINFAVORITEN};

    //  INSERT_TABLE_MEINFAVORITEN
    public void insert_meinfavoriten(MeinFavorites meinFavorites){
        Log.d("meinfavoriten_Insert", meinFavorites.toString());
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTRY_ISO_MEINFAVORITEN, meinFavorites.getCountry_iso());
        values.put(KEY_COUNTRY_NAME_MEINFAVORITEN, meinFavorites.getCountry_name());
        values.put(KEY_CENTER_ID_MEINFAVORITEN, meinFavorites.getCenter_id());
        values.put(KEY_CENTER_NAME_MEINFAVORITEN, meinFavorites.getCenter_name());
        database.insert(TABLE_MEINFAVORITEN, null, values);
        database.close();
    }

    // DELETE_ROW_MEINFAVORITEN
    public void delet_row_meinfavoriten(String centerId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEINFAVORITEN, KEY_CENTER_ID_MEINFAVORITEN + " = ? ", new String[]{String.valueOf(centerId)});
    }
    public boolean checkDataMeinfavoriten(String centerId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_MEINFAVORITEN+" WHERE "+KEY_CENTER_ID_MEINFAVORITEN+" LIKE'" + centerId + "'", null);
        if(c.moveToFirst())
        {
            return true;
        }else {
            return false;
        }
    }

    // GET_TABLE_MEINFAVORITEN
    public List<MeinFavorites>get_table_meinfavoriten(){
        List<MeinFavorites> meinFavoritesList = new ArrayList<>();
        String query = "select * from "+TABLE_MEINFAVORITEN+" ORDER BY "+KEY_COUNTRY_NAME_MEINFAVORITEN+" ASC";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        MeinFavorites meinFavorites = null;
        if (cursor.moveToFirst()){
            do{
                meinFavorites = new MeinFavorites();
                meinFavorites.setId(Integer.parseInt(cursor.getString(0)));
                meinFavorites.setCenter_id(cursor.getString(1));
                meinFavorites.setCenter_name(cursor.getString(2));
                meinFavorites.setCountry_iso(cursor.getString(3));
                meinFavorites.setCountry_name(cursor.getString(4));
                meinFavoritesList.add(meinFavorites);
            }while (cursor.moveToNext());
        }
        return meinFavoritesList;
    }

}