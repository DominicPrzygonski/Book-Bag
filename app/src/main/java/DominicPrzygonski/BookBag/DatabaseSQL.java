package DominicPrzygonski.BookBag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseSQL extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookBag.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "PersonalBooks";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_LENGTH  = "length";
    private static final String COLUMN_SEQUELS = "sequels";
    private static final String COLUMN_PREQUELS = "prequels";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_COMPLETION = " completion";

    private static final String LOGIN_TABLE_NAME = "LoginInfo";
    private static final String COLUMN_LOGIN_ID = "_ID2";
    private static final String COLUMN_LOGIN_NAME = "LoginName";
    private static final String COLUMN_LOGIN_PASSWORD = "LoginPassword";

    public DatabaseSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT DEFAULT 'Empty', " +
                        COLUMN_LENGTH + " INTEGER DEFAULT 'Empty', " +
                        COLUMN_SEQUELS + " TEXT DEFAULT 'Empty', " +
                        COLUMN_PREQUELS + " TEXT DEFAULT 'Empty', " +
                        COLUMN_GENRE + " TEXT DEFAULT 'Empty', " +
                        COLUMN_COMPLETION + " TEXT); ";
        db.execSQL(query);

        String loginQuery = "CREATE TABLE " + LOGIN_TABLE_NAME +
                " (" + COLUMN_LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOGIN_NAME + " TEXT, " +
                COLUMN_LOGIN_PASSWORD + " TEXT ); ";
        db.execSQL(loginQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME);
        onCreate(db);
    }

    //Book methods
    public void addBook(String name, String author, int length, String sequel, String prequel, String genre, String completion ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(COLUMN_NAME, name);
        content.put(COLUMN_AUTHOR, author);
        content.put(COLUMN_LENGTH, length);
        content.put(COLUMN_SEQUELS, sequel);
        content.put(COLUMN_PREQUELS, prequel);
        content.put(COLUMN_GENRE, genre);
        content.put(COLUMN_COMPLETION, completion);
        long result = db.insert(TABLE_NAME, null, content);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = null;
        if(db != null){
            data = db.rawQuery(query, null);
        }
        return data;
    }
    
    void updateData(String id, String Name, String Author, String Length, String Prequel, String Sequel, String Genre, String Completion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, Name);
        cv.put(COLUMN_AUTHOR, Author);
        cv.put(COLUMN_LENGTH, Length);
        cv.put(COLUMN_PREQUELS, Prequel);
        cv.put(COLUMN_SEQUELS, Sequel);
        cv.put(COLUMN_GENRE, Genre);
        cv.put(COLUMN_COMPLETION, Completion);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }



    //Login Methods
    public void addAccount(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(COLUMN_LOGIN_NAME, name);
        content.put(COLUMN_LOGIN_PASSWORD, password);

        long result = db.insert(LOGIN_TABLE_NAME, null, content);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

        }
    }

    public Boolean checkAccount(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorName = db.rawQuery("SELECT * FROM " + LOGIN_TABLE_NAME + " WHERE " + COLUMN_LOGIN_NAME + " = ?" , new String[] {name});
        Cursor cursorPassword = db.rawQuery("SELECT * FROM " + LOGIN_TABLE_NAME + " WHERE " + COLUMN_LOGIN_PASSWORD + " = ?" , new String[] {password});

        if(cursorName.getCount() > 0 && cursorPassword.getCount() > 0){
            return true;
        } else {
            return false;
        }


    }
}
