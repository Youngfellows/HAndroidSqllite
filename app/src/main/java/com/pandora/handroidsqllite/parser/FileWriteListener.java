package com.pandora.handroidsqllite.parser;


public interface FileWriteListener {

    void onStart();

    void writeFileFailed(Exception e);

    void writeFileSuccess();

    void onEnd();
}
