package com.pandora.handroidsqllite.db.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Student;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生表帮助类
 */
public class StudentDBHelper extends DBHelper<Student> {

    private StudentDBHelper() {
        Log.d(TAG, "StudentDBHelper ");
    }

    private static class Holder {
        private static StudentDBHelper INSTANCE = new StudentDBHelper();
    }

    public static StudentDBHelper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Student query() {
        return null;
    }

    @Override
    public Student besidesQuery(Student student) {
        return null;
    }

    @Override
    public List<Student> limitQuery(long counts) {
        return null;
    }

    @Override
    public List<Student> queryAll() {
        // 1.使用java提供的sql查询
        List<Student> list = new ArrayList<>();
        //COLLATE NOCASE 忽略大小写查询
        //Cursor cursor = getDateBase().rawQuery("select * from T_cpz where isqy='True' COLLATE NOCASE;", null);
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery("select * from student", null);
        while (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex("name");
            int sex_index = cursor.getColumnIndex("sex");
            int age_index = cursor.getColumnIndex("age");
            String name = cursor.getString(name_index);
            String sex = cursor.getString(sex_index);
            int age = cursor.getInt(age_index);
            Student student = new Student(name, sex, age);
            list.add(student);
        }
        cursor.close();

        // 2.android中提供的方法查询数据
        //list = queryAllByAndroid();

        return list;
    }

    /**
     * 2.android中提供的方法查询数据
     *
     * @return
     */
    private List<Student> queryAllByAndroid() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db2 = getDatebase();
        Cursor cursor2 = db2.query("student", null, null, null, null, null, null);
        while (cursor2.moveToNext()) {
            int name_index = cursor2.getColumnIndex("name");
            int sex_index = cursor2.getColumnIndex("sex");
            int age_index = cursor2.getColumnIndex("age");
            String name = cursor2.getString(name_index);
            String sex = cursor2.getString(sex_index);
            int age = cursor2.getInt(age_index);
            Student student = new Student(name, sex, age);
            list.add(student);
        }
        cursor2.close();
        return list;
    }

    @Override
    public boolean insert(Student student) {
        // 1.使用java提供的sql插入
        String sql = "insert into student(name,sex,age) values('" + student.getName() + "','" + student.getSex() + "','" + student.getAge() + "');";
        Log.d(TAG, "insert sql: " + sql);
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);

        // 2.android中提供的方法插入数据
        // 使用ContentValues进行插入操作ContentValues相当于java中的Map以键值对的形式存在
        //        SQLiteDatabase db = getDatebase();
        //        ContentValues cv = new ContentValues();
        //        cv.put("name", student.getName());
        //        cv.put("sex", student.getSex());
        //        cv.put("age", student.getAge());
        //        long rowId = db.insert("student", null, cv);
        //return rowId > 0;
        return true;
    }

    @Override
    public boolean delete(Student student) {
        // 1.使用SQL方式删除
        //        String sql = "delete from student where name='" + student.getName() + "';";
        //        SQLiteDatabase db = getDatebase();
        //        db.execSQL(sql);

        // 2.使用android中的方式删除
        SQLiteDatabase db = getDatebase();
        String whereClause = "name=?"; //删除条件
        String[] whereArgs = {student.getName()};
        int rowId = db.delete("student", whereClause, whereArgs);
        return rowId > 0;
    }

    @Override
    public boolean update(Student student) {
        // 1.使用SQL方式更新
        //        String sql = "update student set age=" + student.getAge() + " where name='" + student.getName() + "';";
        //        SQLiteDatabase db = getDatebase();
        //        db.execSQL(sql);

        // TODO: 2020/4/12 有bug，更新不成功
        // 2.使用安卓中的方式更新
        SQLiteDatabase db = getDatebase();
        ContentValues cv = new ContentValues();
        cv.put("sex", student.getSex()); //要更改的字段
        cv.put("age", student.getAge()); //要更改的字段

        String whereClause = "name=?";//更新条件
        String[] whereArgs = {student.getName()};
        int rowId = db.update("student", cv, whereClause, whereArgs);
        return rowId > 0;
    }
}
