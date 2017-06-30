package com.example.administrator.tabzzf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

// TODO Create separate classes for each table
public class DBHelp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TrashTrackerDB";
    //// TODO: 22.6.2017 г. foreign key in Trash Table
    public static final String Trash_TABLE_NAME = "Trash";
    public static final String TrashId_COL = "idTrash";
    public static final String TrashName_COL = "Name";
    public static final String TrashLatitude_COL = "Latitude";
    public static final String TrashLongitude_COL = "Longitude";
    public static final String TrashDescription_COL = "Description";
    public static final String TrashIsCleaned_COL = "IsCleaned";
    public static final String TrashSize_COL = "Size_IdSize";

    public static final String Size_TABLE_NAME = "Size";
    public static final String SizeId_COL = "idSize";
    public static final String SizeName_COL = "Name";

    //// TODO: 22.6.2017 г. foreign key in Image Table
    public static final String Image_TABLE_NAME = "Image";
    public static final String ImageId_COL = "idImage";
    public static final String Image_COL = "Image";

    public static final String Type_TABLE_NAME = "Type";
    public static final String TypeId_COL = "idType";
    public static final String TypeName_COL = "Name";

    //// TODO: 22.6.2017 г. foreign keys in Trash_has_Type Table
    public static final String Trash_has_Type_TABLE_NAME = "Trash_has_Type";
    public static final String Trash_idTrash_COL = "Trash_idTrash";
    public static final String Type_idType_COL = "Type_idType";

    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Trash_TABLE_NAME + " (idTrash INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, " +
                "Latitude DOUBLE, Longitude DOUBLE, Description TEXT, IsCleaned BOOL, Size_IdSize INTEGER) " +
                "create table " + Size_TABLE_NAME + " (idSize INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT) " +
                "create table " + Image_TABLE_NAME + " (idImage INTEGER PRIMARY KEY AUTOINCREMENT, Image BLOB) " +
                "create table " + Type_TABLE_NAME + " (idType INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT) " +
                "create table " + Trash_has_Type_TABLE_NAME + " (Trash_idTrash INTEGER PRIMARY KEY, Type_idType INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Trash_TABLE_NAME +
                " DROP TABLE IF EXISTS " + Size_TABLE_NAME +
                " DROP TABLE IF EXISTS " + Image_TABLE_NAME +
                " DROP TABLE IF EXISTS " + Type_TABLE_NAME +
                " DROP TABLE IF EXISTS " + Trash_has_Type_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDataIntoTrashTable(String name, String description, double latitude, double longitude, byte isCleaned, int size) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TrashName_COL, name);
        contentValues.put(TrashLatitude_COL, latitude);
        contentValues.put(TrashLongitude_COL, longitude);
        contentValues.put(TrashDescription_COL, description);
        contentValues.put(TrashIsCleaned_COL, isCleaned);
        contentValues.put(TrashSize_COL, size);

        long result = db.insert(Trash_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataIntoSizeTable(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SizeName_COL, name);

        long result = db.insert(Size_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataIntoImageTable(byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Image_COL, String.valueOf(image));

        long result = db.insert(Image_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataIntoTypeTable(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TypeName_COL, name);

        long result = db.insert(Type_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataIntoTrashHasTypeTable(int Trash_idTrash, int Type_idType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Trash_idTrash_COL, Trash_idTrash);
        contentValues.put(Type_idType_COL, Type_idType);

        long result = db.insert(Trash_has_Type_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataFromTrashTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Trash_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataFromSizeTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Size_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataFromImageTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Image_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataFromTypeTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Type_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataFromTrashHasTypeTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Trash_has_Type_TABLE_NAME, null);
        return res;
    }


    public boolean updateDataIntoTrashTable(String idTrash, String name, double latitude, double longitude, String description, boolean isCleaned, int size) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TrashId_COL, idTrash);
        contentValues.put(TrashName_COL, name);
        contentValues.put(TrashLatitude_COL, latitude);
        contentValues.put(TrashLongitude_COL, longitude);
        contentValues.put(TrashDescription_COL, description);
        contentValues.put(TrashIsCleaned_COL, isCleaned);
        contentValues.put(TrashSize_COL, size);
        db.update(Trash_TABLE_NAME, contentValues, "idTrash = ?", new String[]{idTrash});
        return true;
    }

    public boolean updateDataIntoSizeTable(String idSize, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SizeId_COL, idSize);
        contentValues.put(SizeName_COL, name);
        db.update(Size_TABLE_NAME, contentValues, "idSize = ?", new String[]{idSize});
        return true;
    }

    public boolean updateDataIntoImageTable(String idImage, Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ImageId_COL, idImage);
        contentValues.put(Image_COL, String.valueOf(image));
        db.update(Size_TABLE_NAME, contentValues, "idImage = ?", new String[]{idImage});
        return true;
    }

    public boolean updateDataIntoTypeTable(String idType, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TypeId_COL, idType);
        contentValues.put(TypeName_COL, name);
        db.update(Type_TABLE_NAME, contentValues, "idType = ?", new String[]{idType});
        return true;
    }

    public boolean updateDataIntoTrashHasTypeTable(int Trash_idTrash, int Type_idType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Trash_idTrash_COL, Trash_idTrash);
        contentValues.put(Type_idType_COL, Type_idType);
        db.update(Type_TABLE_NAME, contentValues, "Trash_idTrash = ?", new String[]{Integer.toString(Trash_idTrash)});
        return true;
    }

    public Integer deleteDataFromTrashTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Trash_TABLE_NAME, "idTrash = ?", new String[]{id});
    }

    public Integer deleteDataFromSizeTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Size_TABLE_NAME, "idSize = ?", new String[]{id});
    }

    public Integer deleteDataFromImageTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Image_TABLE_NAME, "idImage = ?", new String[]{id});
    }

    public Integer deleteDataFromTypeTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Type_TABLE_NAME, "idType = ?", new String[]{id});
    }

    public Integer deleteDataFromTrashHasTypeTable(String Trash_idTrash) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Type_TABLE_NAME, "Trash_idTrash = ?", new String[]{Trash_idTrash});
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}

