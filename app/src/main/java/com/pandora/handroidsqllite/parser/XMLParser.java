package com.pandora.handroidsqllite.parser;


import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.pandora.handroidsqllite.MyApp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class XMLParser {

    public static String TAG = XMLParser.class.getSimpleName();

    /**
     * 上下文
     */
    private Context mContext;

    private static XMLParser instance;

    /**
     * 线程池
     */
    private ExecutorService mExecutor;

    /**
     * 是否正在读写文件
     */
    private boolean isWriting = false;


    private XMLParser(Context context) {
        this.mContext = context;
        this.mExecutor = Executors.newCachedThreadPool();
    }

    public static XMLParser getInstance(Context context) {
        if (instance == null) {
            synchronized (XMLParser.class) {
                if (instance == null) {
                    instance = new XMLParser(context);
                }
            }
        }
        return instance;
    }

    /**
     * 将XML转换为json
     *
     * @param inputStream
     * @return
     */
    private String xml2Json(InputStream inputStream) throws Throwable {
        JSONObject result = null;
        JSONArray cmdArray = null;
        String cmdKey = null;
        String from = null;
        String to = null;
        List<String> itemArray = null;

        //创建XmlPullParser
        XmlPullParser parser = Xml.newPullParser();
        //解析文件输入流
        parser.setInput(inputStream, "UTF-8");
        //得到当前的解析对象
        int eventType = parser.getEventType();
        //调用next（）方法得到下一个解析事件
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    //解析开始的时候初始化
                    Log.d(TAG, "xml2Json: 解析开始");
                    result = new JSONObject();
                    break;
                case XmlPullParser.START_TAG:
                    //获得解析器当前指向的元素的名字
                    //当指向元素的名字和command，string这些属性重合时可以返回他们的值
                    String XPPname = parser.getName();
                    if ("command".equals(XPPname)) {
                        //通过解析器获取cmd,from,to的元素值
                        cmdArray = new JSONArray();
                        cmdKey = parser.getAttributeValue(null, "cmd");
                        from = parser.getAttributeValue(null, "from");
                        to = parser.getAttributeValue(null, "to");
                        //Log.d(TAG, "cmdKey: " + cmdKey + " ,from: " + from + " ,to: " + to);
                        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
                            itemArray = new ArrayList<>();
                        }
                    }
                    if (cmdArray != null) {
                        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
                            //Log.d(TAG, "put item ,form " + from + " ,to " + to);
                            if ("string".equals(XPPname)) {
                                //得到当前指向元素的值并赋值给列表
                                String item = parser.nextText();
                                //Log.w(TAG, "item = " + item);
                                itemArray.add(item);
                            }
                        } else {
                            //Log.d(TAG, "put item ,form " + from + " ,to " + to);
                            if ("string".equals(XPPname)) {
                                //得到当前指向元素的值并赋值给列表
                                String item = parser.nextText();
                                //Log.w(TAG, "item = " + item);
                                cmdArray.put(item);
                            }
                        }
                    }
                    break;
                //结束元素事件
                case XmlPullParser.END_TAG:
                    if ("command".equals(parser.getName())) {
                        //Log.d(TAG, "command end ,cmdKey = " + cmdKey + " ,from: " + from + " ,to: " + to + " ,itemArray = " + itemArray + ",cmdArray = " + cmdArray.toString());
                        //Log.i(TAG, "command result = " + result);
                        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
                            int ifrom = Integer.parseInt(from);
                            int j = Integer.parseInt(to);
                            if (itemArray != null) {
                                int size = itemArray.size();
                                for (int ia = ifrom; ia <= j; ia++) {
                                    cmdArray = new JSONArray();
                                    for (int i = 0; i < size; i++) {
                                        String item2 = itemArray.get(i);
                                        //Log.i(TAG, "item2 = " + item2);
                                        String replaceItem = item2.replace("%", ia + "");
                                        //Log.w(TAG, "replaceItem = " + replaceItem);
                                        cmdArray.put(replaceItem);
                                    }
                                    String tempKey = cmdKey + ia;
                                    //Log.w(TAG, ia + " ,tempKey = " + tempKey + " ,cmdArray = " + cmdArray.toString());
                                    result.put(tempKey, cmdArray);
                                }
                            }
                        } else {
                            result.put(cmdKey, cmdArray);
                        }
                        cmdArray = null;
                        cmdKey = null;
                        from = null;
                        to = null;
                        itemArray = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }
        Log.d(TAG, "xml2Json: result = \n" + result.toString());
        if (result != null) {
            return result.toString(4);
        }
        return null;
    }

    public void xmlParses(XMLParseListener xmlParseListener) {
        mExecutor.execute(new ParseTask(xmlParseListener));
    }


    private class ParseTask implements Runnable {

        private XMLParseListener xmlParseListener;

        public ParseTask(XMLParseListener xmlParseListener) {
            this.xmlParseListener = xmlParseListener;
        }

        @Override
        public void run() {
            Log.d(TAG, "DriverJSONParserTask run: isWriting = " + isWriting);
            if (!isWriting) {
                try {
                    isWriting = true;
                    AssetManager asset = mContext.getAssets();
                    InputStream inputStream = asset.open("cmd_strings.xml");
                    String result = xml2Json(inputStream);
                    Log.d(TAG, "start copy json to sdcard ...");
                    FileUtils.writeStringToFile(FileUtils.configPath(MyApp.getContext()), result, new FileWriteListener() {
                        @Override
                        public void onStart() {
                            Log.w(TAG, "DriverJSONParserTask run: onStart ");
                            isWriting = true;
                        }

                        @Override
                        public void writeFileFailed(Exception e) {
                            Log.e(TAG, "DriverJSONParserTask run: writeFileFailed ");
                            if (xmlParseListener != null) {
                                xmlParseListener.onError(e);
                            }
                        }

                        @Override
                        public void writeFileSuccess() {
                            Log.w(TAG, "DriverJSONParserTask run: writeFileSuccess ");
                            if (xmlParseListener != null) {
                                xmlParseListener.onFinish();
                            }
                        }

                        @Override
                        public void onEnd() {
                            Log.w(TAG, "DriverJSONParserTask run: onEnd ");
                            isWriting = false;
                        }
                    });

                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface XMLParseListener {

        void onFinish();

        void onError(Exception e);
    }
}

