package com.pandora.handroidsqllite.db.base;

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
    public List<Student> queryAll() {
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
        return list;
    }

    @Override
    public void insert(Student student) {
        String sql = "insert into student(name,sex,age) values('" + student.getName() + "','" + student.getSex() + "','" + student.getAge() + "');";
        Log.d(TAG, "insert sql: " + sql);
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);
    }

    @Override
    public void delete(Student student) {
        String sql = "delete from student where name='" + student.getName() + "';";
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);
    }

    @Override
    public void update(Student student) {
        String sql = "update student set age=" + student.getAge() + " where name='" + student.getName() + "';";
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);
    }
}
