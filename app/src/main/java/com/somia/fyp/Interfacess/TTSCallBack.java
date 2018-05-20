package com.somia.fyp.Interfacess;


public interface TTSCallBack {
    void onStart(String utteranceId);

    void onDone(String utteranceId);

    void onError(String utteranceId);
}
