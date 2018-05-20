package com.somia.fyp.utial;

import android.content.Context;

import com.somia.fyp.Interfacess.INoNeedCommander;


public class sendToActivtys {
    private INoNeedCommander nonHindiQuery;
    public void sendingDataToActivitys(Context context , String date, String ActivtyName)throws Exception
    {


        nonHindiQuery = (INoNeedCommander) context;




            nonHindiQuery.onNoCommandrExcute(date);


    }
}
