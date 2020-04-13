package com.pandora.handroidsqllite.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pandora.handroidsqllite.R;
import com.pandora.handroidsqllite.adapter.StudentAdapter;
import com.pandora.handroidsqllite.base.PermissionsActivity;
import com.pandora.handroidsqllite.bean.Car;
import com.pandora.handroidsqllite.bean.Phone;
import com.pandora.handroidsqllite.bean.Poi;
import com.pandora.handroidsqllite.bean.Student;
import com.pandora.handroidsqllite.db.DBManager;
import com.pandora.handroidsqllite.db.helper.CarDBHelper;
import com.pandora.handroidsqllite.db.helper.PhoneDBHelper;
import com.pandora.handroidsqllite.db.helper.PoiDBHelper;
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

    private Handler mMainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlbase);
        mMainHandler = new Handler(Looper.getMainLooper());
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


    /**
     * 拷贝Assets数据库
     *
     * @param view
     */
    public void onCopyDB(View view) {
        Log.d(TAG, "onCopyDB ");
        DBManager.getInstance();
    }

    @Override
    public void onPermissionsFull() {
        Log.d(TAG, "onPermissionsFull ");
    }

    /**
     * 查询POI位置
     *
     * @param view
     */
    public void onQueryPoi(View view) {
        Log.d(TAG, "onQueryPoi ");
        new Thread(poiQueryTask).start();
    }

    private Runnable poiQueryTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "poiQueryTask: run ");
            List<Poi> poiList = PoiDBHelper.getInstance().queryAll();
            Log.d(TAG, "poiQueryTask run : query result poi size " + poiList.size());

            List<Student> students = new ArrayList<>();
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = poiList.get(i);
                String pos_str = poi.getName() + "," + poi.getAddress() + ",位置(" + poi.getLatitude() + "," + poi.getLongitude() + ")";
                Log.d(TAG, pos_str);

                //测试使用
                Student student = new Student();
                student.setName(pos_str);
                students.add(student);
            }

            //更新UI
            mMainHandler.post(new ShowPoiTask(students));
        }
    };

    /**
     * 更新UI
     */
    private class ShowPoiTask implements Runnable {

        private List<Student> mStudents;

        public ShowPoiTask(List<Student> students) {
            mStudents = students;
        }

        @Override
        public void run() {
            mStudentAdapter.update(mStudents);
        }
    }


    /**
     * 插入car表
     *
     * @param view
     */
    public void onInsertCar(View view) {
        new Thread(insertCarTask).start();
    }

    /**
     * 插入phone表
     *
     * @param view
     */
    public void onInsertPhone(View view) {
        Log.d(TAG, "onInsertPhone ");
        new Thread(insertPhoneTask).start();
    }

    /**
     * 插入driver表
     *
     * @param view
     */
    public void onInsertDriver(View view) {
        Log.d(TAG, "onInsertDriver ");
        String serialNumber = "";//编号
        String name = "";//姓名
        String idCard = "";//身份证号
        String birthday = ""; //生日
        String drivingLicence = ""; //驾照号
        String phoneNumber = ""; //电话号码
        String contactAddress = ""; //联络地址
        String address = ""; //户籍地址
    }

    /**
     * 插入passenger表
     *
     * @param view
     */
    public void onInsertPassenger(View view) {
        Log.d(TAG, "onInsertPassenger ");

        String serialNumber = "";//编号
        String name = "";//姓名
        String nickName = "";//昵称
        String birthday = "";//生日
        String phoneNumber = "";//电话号码
    }

    private Runnable insertCarTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "onInsertCar ");
            String carNumber = "RC00000";  //车辆编号
            String vin = "RUXLROBOTL000000"; // 车架号
            String plateNumber = "RA000"; //车牌号
            String brand = "Benz"; //品牌
            String colour = "黑色"; //车颜色
            for (int i = 0; i < 100; i++) {
                Car car = new Car(carNumber + i, vin + i, plateNumber + i, brand, colour);
                CarDBHelper.getInstance().insert(car);
            }

            Log.d(TAG, "run insert car table success ... ");
            List<Student> students = new ArrayList<>();
            List<Car> carList = CarDBHelper.getInstance().queryAll();
            int size = carList.size();
            for (int i = 0; i < size; i++) {
                Car car = carList.get(i);
                String car_str = car.toString();
                Log.d(TAG, "run : " + car_str);

                //测试使用
                Student student = new Student();
                student.setName(car_str);
                students.add(student);
            }

            //更新UI
            mMainHandler.post(new ShowPoiTask(students));
        }
    };


    private Runnable insertPhoneTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "insertPhoneTask run ... ");
            String brand = "OPPO";//品牌
            String androidId = "0005cb85e8ee4415";//Android ID
            String imei = "353384097181144";//imei
            String serialNumber = "DRGGAM2872110891"; //序列号
            String mac = "20:39:56:8a:0f:47"; //MAC地址

            for (int i = 0; i < 100; i++) {
                Phone phone = new Phone(brand + i, androidId + i, imei + i, serialNumber + i, mac + i);
                PhoneDBHelper.getInstance().insert(phone);
            }

            Log.d(TAG, "run insert car table success ... ");
            List<Student> students = new ArrayList<>();
            List<Phone> phoneList = PhoneDBHelper.getInstance().queryAll();
            int size = phoneList.size();
            for (int i = 0; i < size; i++) {
                Phone phone = phoneList.get(i);
                String phone_str = phone.toString();
                Log.d(TAG, "insertPhoneTask: run , " + phone_str);
                //测试使用
                Student student = new Student();
                student.setName(phone_str);
                students.add(student);
            }

            //更新UI
            mMainHandler.post(new ShowPoiTask(students));
        }
    };
}
