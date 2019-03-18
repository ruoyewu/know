package com.wuruoye.know.util.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wuruoye.know.model.beans.RecordType;
import com.wuruoye.know.util.sql.table.RecordTypeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 2019/3/18 12:21 by wuruoye
 * Description:
 */
public class SqlUtil {
    private static volatile SqlUtil sInstance;

    private SqliteHelper sh;

    private SqlUtil(Context context) {
        sh = new SqliteHelper(context);
    }

    public static SqlUtil getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SqlUtil.class) {
                if (sInstance == null) {
                    sInstance = new SqlUtil(context);
                }
            }
        }
        return sInstance;
    }

    public List<RecordType> getRecordTypeWithoutItems() {
        List<RecordType> result = new ArrayList<>();
        SQLiteDatabase db = sh.getReadableDatabase();
        try {
            List<RecordTypeTable> typeTables = RecordTypeTable.queryAll(db);

            for (RecordTypeTable table : typeTables) {
                result.add(new RecordType(table.getId(), table.getTitle(), null,
                        table.getCreateTime(), table.getUpdateTime()));
            }
        } finally {
            db.close();
        }
        return result;
    }
}
