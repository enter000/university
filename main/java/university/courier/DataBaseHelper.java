package university.courier;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "database.db";

    public static final  String USER_TABLE_NAME = "user_info_table";
    public static final  String ORDER_TABLE_NAME = "order_table";

    public static final  String ID_COLUMN = "_id";
    public static final  String NAME_COLUMN = "name";
    public static final  String LOGIN_COLUMN = "login";
    public static final  String PASSWORD_COLUMN = "password";
    public static final  String PHONE_COLUMN = "phone";
    public static final  String ROLE_COLUMN = "role";

    public static final  String ORDER_ID_COLUMN = "_id";
    public static final  String COURIER_ID_COLUMN = "COURIER_ID";
    public static final  String CUSTOMER_ID_COLUMN = "CUSTOMER_ID";
    public static final  String ORDER_STATE_COLUMN = "ORDER_STATE";
    public static final  String WHERE_TO_BUY_COLUMN = "WHERE_TO_BUY";
    public static final  String WHERE_TO_DELIVER_COLUMN = "WHERE_TO_DELIVER";
    public static final  String CUSTOMER_PHONE_COLUMN = "CUSTOMER_PHONE";
    public static final  String COURIER_PHONE_COLUMN = "COURIER_PHONE";
    public static final  String WHAT_TO_BUY_COLUMN = "WHAT_TO_BUY";

    SQLiteDatabase db;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + "(" + ID_COLUMN + " integer primary key,"
                + NAME_COLUMN + " string not null,"
                + LOGIN_COLUMN + " text not null,"
                + PASSWORD_COLUMN + " integer not null,"
                + PHONE_COLUMN + " text not null,"
                + ROLE_COLUMN + " text not null" +")");

        db.execSQL("create table " + ORDER_TABLE_NAME + "(ID INTEGER PRIMARY KEY, COURIER_ID INTEGER, CUSTOMER_ID INTEGER NOT NULL, ORDER_STATE INTEGER, WHERE_TO_BUY BLOB NOT NULL, WHERE_TO_DELIVER BLOB NOT NULL, CUSTUMER_PHONE TEXT NOT NULL, COURIER_PHONE TEXT, WHAT_TO_BUY BLOB NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDataToUserTable(String name, String login, String pass, String phone, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String query = "select * from " + USER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        contentValues.put(ID_COLUMN, count);
        contentValues.put(NAME_COLUMN,name);
        contentValues.put(LOGIN_COLUMN,login);
        contentValues.put(PASSWORD_COLUMN,pass);
        contentValues.put(PHONE_COLUMN,phone);
        contentValues.put(ROLE_COLUMN, role);

        long result = db.insert(USER_TABLE_NAME, null, contentValues);


        if (result == -1 ) {
            db.close();
            return false;

        } else {
            db.close();
            return true;
        }

    }

    public boolean insertDataToOrderTable(int courier_id, int customer_id, int order_state, String where_to_buy, String where_to_deliver, String customer_phone, String courier_phone, String what_to_buy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURIER_ID_COLUMN,courier_id);
        contentValues.put(CUSTOMER_ID_COLUMN,customer_id);
        contentValues.put(ORDER_STATE_COLUMN,order_state);
        contentValues.put(WHERE_TO_BUY_COLUMN,where_to_buy);
        contentValues.put(WHERE_TO_DELIVER_COLUMN,where_to_deliver);
        contentValues.put(CUSTOMER_PHONE_COLUMN,customer_phone);
        contentValues.put(COURIER_PHONE_COLUMN,courier_phone);
        contentValues.put(WHERE_TO_BUY_COLUMN,what_to_buy);


        long result = db.insert(ORDER_TABLE_NAME, null, contentValues);

        if (result == -1 ) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllDAtaAboutUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + USER_TABLE_NAME, null);
        return result;
    }

    public Cursor getAllDAtaAboutOrders() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + ORDER_TABLE_NAME, null);
        return result;
    }

    public boolean updateDataAboutUsers(String id, String name, String login, String pass, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_COLUMN,id);
        contentValues.put(NAME_COLUMN,name);
        contentValues.put(LOGIN_COLUMN,login);
        contentValues.put(PASSWORD_COLUMN,pass);
        contentValues.put(PHONE_COLUMN,phone);

        db.update(USER_TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;
    }

    public boolean updateDataAboutOrders(String id,int courier_id, int customer_id, int order_state, String where_to_buy, String where_to_deliver, String customer_phone, String courier_phone, String what_to_buy) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ORDER_ID_COLUMN, id);
        contentValues.put(COURIER_ID_COLUMN,courier_id);
        contentValues.put(CUSTOMER_ID_COLUMN,customer_id);
        contentValues.put(ORDER_STATE_COLUMN,order_state);
        contentValues.put(WHERE_TO_BUY_COLUMN,where_to_buy);
        contentValues.put(WHERE_TO_DELIVER_COLUMN,where_to_deliver);
        contentValues.put(CUSTOMER_PHONE_COLUMN,customer_phone);
        contentValues.put(COURIER_PHONE_COLUMN,courier_phone);
        contentValues.put(WHAT_TO_BUY_COLUMN,what_to_buy);

        db.update(USER_TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;
    }

    public int deleteDAta(String id) {
        db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME,"ID = ?", new String[] {id});
    }

    public boolean insertUser(User user) {
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME_COLUMN, user.getName());
        contentValues.put(LOGIN_COLUMN, user.getLogin());
        contentValues.put(PASSWORD_COLUMN, user.getPassword());
        contentValues.put(PHONE_COLUMN,user.getPhone());
        contentValues.put(ROLE_COLUMN, user.getRole());

        long result = db.insert(USER_TABLE_NAME, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
        /*db.execSQL("insert into user_info_table (name,login,password,phone,role) values("
                + user.getName() + ","
                + user.getLogin() + ","
                + user.getPassword() + ","
                + user.getPhone() + ","
                + user.getRole() + ")");

        db.close();
        */

    }

    public String searchData(String login) {
        db = this.getReadableDatabase();

        String query = "select * from " + USER_TABLE_NAME;

        Cursor cursor = db.query(USER_TABLE_NAME, null, null, null, null, null, null);

        String ulogin;
        String upass = "Совпадений не найдено";

        if (cursor.moveToFirst()) {
            do {
                int loginIndex = cursor.getColumnIndex(LOGIN_COLUMN);
                int passIndex = cursor.getColumnIndex(PASSWORD_COLUMN);

                ulogin = cursor.getString(loginIndex);


                if (ulogin.equals(login)) {
                    upass = cursor.getString(passIndex);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return upass;
    }

    public String searchRole(String login, String pass) {
        db = this.getReadableDatabase();

        String query = "select login, password, role from " + USER_TABLE_NAME + " where login = " + login + ", password = " + pass;

        Cursor cursor = db.rawQuery(query, null);

        return cursor.getString(2);
    }
}

