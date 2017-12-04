package university.courier;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static university.courier.DataBaseHelper.USER_TABLE_NAME;



public class ExternalDbOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "db.sqlite3"; // Название файла с БД
        private static final int DATABASE_VERSION = 1;            //Версия БД
        private static final String SP_KEY_DB_VER = "db_ver";
        private final Context mContext;

        public ExternalDbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.mContext = context;
            initialize();
        }

        /**
         * Инициализация БД. Создание новой если ранее не существовала.
         */
        private void initialize() {
            if (databaseExists()) {
                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(mContext);
                int dbVersion = prefs.getInt(SP_KEY_DB_VER, 1);
                if (DATABASE_VERSION != dbVersion) {
                    File dbFile = mContext.getDatabasePath(DATABASE_NAME);
                    if (!dbFile.delete()) {
                        // Log.w(TAG, "Невозможно обновить БД");
                    }
                }
            } else {
                createDatabase();
            }
        }

        /**
         * Проверка существования файла БД. Если существует - возвращает true.
         * @return
         */
        private boolean databaseExists() {
            File dbFile = mContext.getDatabasePath(DATABASE_NAME);
            return dbFile.exists();
        }

        /**
         * Создание БД, копирование файла из Assets.
         */
        private void createDatabase() {
            String parentPath = mContext.getDatabasePath(DATABASE_NAME).getParent();
            String path = mContext.getDatabasePath(DATABASE_NAME).getPath();

            File file = new File(parentPath);
            if (!file.exists()) {
                if (!file.mkdir()) {
                    //  Log.w(TAG, "Невозможно создать папку БД");
                    return;
                }
            }

            InputStream is = null;
            OutputStream os = null;
            try {
                is = mContext.getAssets().open(DATABASE_NAME);
                os = new FileOutputStream(path);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(SP_KEY_DB_VER, DATABASE_VERSION);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}






