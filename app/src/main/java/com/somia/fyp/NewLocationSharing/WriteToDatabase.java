package com.somia.fyp.NewLocationSharing;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Somia on 4/24/2018.
 */

public class WriteToDatabase {
    private String TAG="WriteToDatabase";
    private DatabaseReference mDatabase;
    public void saveToDabase(MyLocation loc,String UID ){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("MyLocation").child(UID).setValue(loc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG,"value save to database ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"onFailure"+e.getMessage());
            }
        });

    }

}
