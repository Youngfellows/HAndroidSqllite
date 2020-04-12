package com.pandora.handroidsqllite.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pandora.handroidsqllite.R;
import com.pandora.handroidsqllite.adapter.StudentAdapter;
import com.pandora.handroidsqllite.base.PermissionsActivity;
import com.pandora.handroidsqllite.bean.Student;
import com.pandora.handroidsqllite.db.helper.StudentDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLBaseActivity extends PermissionsActivity {

    private String TAG = this.getClass().getSimpleName();
    private EditText mEtName;
    private EditText mEtSex;
    private EditText mEtAge;
    private RecyclerView mRvStudent;
    private StudentAdapter mStudentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlbase);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtSex = (EditText) findViewById(R.id.et_sex);
        mEtAge = (EditText) findViewById(R.id.et_age);
        mRvStudent = (RecyclerView) findViewById(R.id.rv_student);
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvStudent.setLayoutManager(layoutManager);
        List<Student> students = queryAll();
        //mStudentAdapter = new StudentAdapter(students);
        mStudentAdapter = new StudentAdapter();
        mRvStudent.setAdapter(mStudentAdapter);

        Log.d(TAG, "initData ");
        String dbPath = StudentDBHelper.getInstance().getDatabasePath();
        Toast.makeText(SQLBaseActivity.this, dbPath, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "initData : dbPath = " + dbPath);
    }

    private void initEvent() {
        mStudentAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private StudentAdapter.OnItemClickListener mOnItemClickListener = new StudentAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Log.d(TAG, "onItemClick ");
            String dbPath = StudentDBHelper.getInstance().getDatabasePath();
            Toast.makeText(SQLBaseActivity.this, dbPath, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onItemClick : dbPath = " + dbPath);
        }

        @Override
        public void onItemLongClick(View view, int position) {
            Log.d(TAG, "onItemLongClick ");
            Toast.makeText(SQLBaseActivity.this, "YYYY", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 查询数据库
     *
     * @return
     */
    public List<Student> queryAll() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = "小明" + (i + 1);
            String sex = (i % 2) == 0 ? "男" : "女";
            int age = new Random().nextInt(60);
            Student stu = new Student(name, sex, age);
            students.add(stu);
        }
        return students;
    }

    /**
     * 添加
     *
     * @param view
     */
    public void onAddData(View view) {
        Log.d(TAG, "onAddData ");
        String name = mEtName.getText().toString();
        String sex = mEtSex.getText().toString();
        int age = Integer.parseInt(mEtAge.getText().toString());
        Student student = new Student(name, sex, age);
        StudentDBHelper dbHelper = StudentDBHelper.getInstance();
        dbHelper.insert(student);

        List<Student> students = dbHelper.queryAll();
        mStudentAdapter.update(students);
    }

    /**
     * 更新
     *
     * @param view
     */
    public void onUpdataData(View view) {
        Log.d(TAG, "onUpdataData ");
        String name = mEtName.getText().toString();
        String sex = mEtSex.getText().toString();
        int age = Integer.parseInt(mEtAge.getText().toString());
        Student student = new Student(name, sex, age);
        StudentDBHelper dbHelper = StudentDBHelper.getInstance();
        boolean update = dbHelper.update(student);
        Log.d(TAG, "onUpdataData : update = " + update);

        List<Student> students = dbHelper.queryAll();
        mStudentAdapter.update(students);
    }

    /**
     * 删除
     *
     * @param view
     */
    public void onDelData(View view) {
        Log.d(TAG, "onDelData ");
        String name = mEtName.getText().toString();
        String sex = mEtSex.getText().toString();
        int age = Integer.parseInt(mEtAge.getText().toString());
        Student student = new Student(name, sex, age);
        StudentDBHelper dbHelper = StudentDBHelper.getInstance();
        dbHelper.delete(student);

        List<Student> students = dbHelper.queryAll();
        mStudentAdapter.update(students);
    }

    /**
     * 查询
     *
     * @param view
     */
    public void onQueryData(View view) {
        Log.d(TAG, "onQueryData  ");
        StudentDBHelper dbHelper = StudentDBHelper.getInstance();
        List<Student> students = dbHelper.queryAll();
        mStudentAdapter.update(students);
    }

    @Override
    public void onPermissionsFull() {
        Log.d(TAG, "onPermissionsFull ");
    }
}
