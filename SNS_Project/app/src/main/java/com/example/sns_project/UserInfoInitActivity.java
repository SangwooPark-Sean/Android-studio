package com.example.sns_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfoInitActivity extends AppCompatActivity {
    private static final String TAG = "UserInfoInitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_init);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.checkButton:
                    registerUserInfo();
                    break;

            }
        }
    };

    private void registerUserInfo(){
        String name = ((EditText)findViewById(R.id.name_et)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.phoneNum_et)).getText().toString();
        String birthDate = ((EditText)findViewById(R.id.birthDate_et)).getText().toString();
        String address = ((EditText)findViewById(R.id.address_et)).getText().toString();

        if (name.length() > 0 && phoneNumber.length() > 9 && birthDate.length() > 5 && address.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            UserInfo userInfo = new UserInfo(name, phoneNumber, birthDate, address);

            if (user != null) {
                db.collection("users").document(user.getUid()).set(userInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("개인정보 등록 완료.");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("개인정보 등록 불가");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }

        }else{
            startToast("개인정보를 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void startActivity(Class cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}