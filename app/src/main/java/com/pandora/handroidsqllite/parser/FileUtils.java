package com.pandora.handroidsqllite.parser;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.pandora.handroidsqllite.utils.CachePathUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 文件夹
     * /storage/emulated/0/Android/data/com.mobiledrivetech.voiceassistant/files/json
     */
    public static String CONFIG_JSON = "json";
    public static String CONFIG_JSON_BACK = "back";

    /**
     * 文件名
     */
    public static String CONFIG_FILE_NAME = "res_txz_adapter_cmd_keywords.json";

    public FileUtils(Context context) {
        this.mContext = context;
    }

    /**
     * 拷贝数据库到数据库路径下
     *
     * @param name 数据库名称
     * @return 应用数据库路径
     */
    public String copyDB(String name) {

        String dbPath = "/data/data/" + mContext.getPackageName() + "/databases/";

        File dbPathFile = new File(dbPath);

        if (!dbPathFile.mkdirs()) {
            dbPathFile.mkdirs();
        }

        String dbFile = dbPath + name;

        if (new File(dbFile).exists()) {
            new File(dbFile).delete();
        }

        try {

            InputStream im = mContext.getAssets().open(name);
            OutputStream om = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = im.read(buffer)) > 0) {
                om.write(buffer, 0, length);
            }

            om.flush();
            om.close();
            im.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return dbPath + name;
    }


    /**
     * 获取配置文件保存路径
     *
     * @param context
     * @return
     */
    public static String configPath(Context context) {
        //        return targetPath(context) + File.separator + CONFIG_FILE_NAME;
        return configFilePath(context) + File.separator + CONFIG_FILE_NAME;
    }

    /**
     * 获取配置文件保存路径
     *
     * @param context
     * @return
     */
    public static String configFilePath(Context context) {
        String path = CachePathUtil.getCacheDirectory(context, CONFIG_JSON).getAbsolutePath();
        Log.d(TAG, "configFilePath: json file path = " + path);
        return path;
    }

    public static String backConfigPath(Context context) {
        String path = CachePathUtil.getCacheDirectory(context, CONFIG_JSON_BACK).getAbsolutePath();
        Log.d(TAG, "configFilePath: back json file  path = " + path);
        return path;
    }

    /**
     * 获取配置保存路径
     * *@param context
     *
     * @return
     */
    public static String targetPath(Context context) {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            try {
                //                path = context.getExternalCacheDir().getAbsolutePath();
                Log.i(TAG, "path1 = " + path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(path)) {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                Log.i(TAG, "path2 = " + path);
            }
        } else {
            path = context.getCacheDir().getAbsolutePath();
            Log.i(TAG, "path3 = " + path);
        }
        Log.i(TAG, "path4 = " + path);
        return path;
    }

    /**
     * 按自定编码读取字符创
     *
     * @param context
     * @param file    读取的文件
     * @return 读取到的字符串
     */
    public static String readFile(Context context, File file) {
        if (file == null) {
            return null;
        }
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文本写入到文件中
     *
     * @param filePath 文件路径
     * @param content  内容
     * @param isAppend 是否追加
     */
    public static void saveStringToFile(final String filePath, final String content, final boolean isAppend) {
        if (TextUtils.isEmpty(filePath) || filePath.matches("null\\/.*")) {
            Log.w(TAG, "The file to be saved is null!");
            return;
        }
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            fw = new FileWriter(file, isAppend);
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入文件到指定目录
     *
     * @param targetPath 目标路径
     * @param content    写入的内容
     * @return
     */
    public static void writeStringToFile(String targetPath, String content, FileWriteListener fileWriteListener) {
        if (fileWriteListener != null) {
            fileWriteListener.onStart();
        }
        File file = new File(targetPath);
        Log.d(TAG, " write file exists = " + file.exists());
        if (file.exists()) {
            Log.d(TAG, "file exits , delete file");
            file.delete();
        } else {
            //            fileWriteListener.writeFileFailed();
        }
        File parentFile = file.getParentFile();
        Log.d(TAG, " write parentFile exists = " + parentFile.exists());
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        try {
            boolean createNewFile = file.createNewFile();
            Log.d(TAG, " write createNewFile = " + createNewFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes("utf-8"));
            fos.flush();
            Log.d(TAG, " write success ...");
            fileWriteListener.writeFileSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " write failed  ...");
            fileWriteListener.writeFileFailed(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fileWriteListener != null) {
            fileWriteListener.onEnd();
        }
    }


    /**
     * 读取指定文件的字符串
     *
     * @param path
     * @return
     */
    public static String readStringFromFile(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        BufferedReader bufferReader = null;
        StringBuffer sb = null;
        Log.d(TAG, "read file exites =  " + file.exists());
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                bufferReader = new BufferedReader(new InputStreamReader(fis));
                sb = new StringBuffer();
                String line = "";
                Log.d(TAG, "read bufferReader =  " + bufferReader);
                try {
                    while ((line = bufferReader.readLine()) != null) {
                        sb.append(line);
                        Log.d(TAG, "read line =  " + line);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferReader != null) {
                        bufferReader.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return null;
        }
        Log.d(TAG, "read sb.toString() =  " + sb.toString());
        return sb.toString();
    }


    public static boolean existsConfigFile(Context context) {
        File file = new File(configPath(context));
        return file.exists();
    }

    /**
     * 读取assets本地json
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String readAssets(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            InputStream inputStream = assetManager.open(fileName);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = stringBuilder.toString();
        Log.d(TAG, "readAssets: fileName = " + fileName + "\n" + result);
        return result;
    }

    /**
     * 遍历读取Assets目录文件
     *
     * @param context
     * @return
     */
    public String readAsset(Context context) {
        AssetManager am = context.getAssets();
        String[] path = null;
        try {
            // 列出files目录下的文件
            path = am.list("files");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String data = "";

        // 遍历assets目录下，files文件夹下的所有文件，读取这些文件的数据并输出。
        for (int i = 0; i < path.length; i++) {
            InputStream is = null;
            try {
                // 根据上文的 ‘files’+文件名，拼成一个路径，用AssetManager打开一个输入流，读写数据。
                is = am.open("files/" + path[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 读取一个文件完成，加上换行符（主要是为了观察输出结果，无他）。
            data = data + readDataFromInputStream(is) + "\n";

            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public String readDataFromInputStream(InputStream is) {
        BufferedInputStream bis = new BufferedInputStream(is);
        String str = "", s = "";
        int c = 0;
        byte[] buf = new byte[1024];
        while (true) {
            try {
                c = bis.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (c == -1)
                break;
            else {
                try {
                    s = new String(buf, 0, c, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                str += s;
            }
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
