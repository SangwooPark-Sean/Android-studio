package com.example.sns_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserInfoInitActivity extends AppCompatActivity {
    private static final String TAG = "UserInfoInitActivity";
    private ImageView profileImageView;
    private String profilePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_init);

        profileImageView = findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(onClickListener);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    profilePath = data.getStringExtra("profilePath");
                    Log.e("로그 : ", "profilePath : "+profilePath);
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    profileImageView.setImageBitmap(bmp);

                }
                break;
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.checkButton:
                    registerUserInfo();
                    break;
                case R.id.profileImageView:
                    startActivity(CameraActivity.class);
                    break;

            }
        }
    };

    private void registerUserInfo(){
        final String name = ((EditText)findViewById(R.id.name_et)).getText().toString();
        final String phoneNumber = ((EditText)findViewById(R.id.phoneNum_et)).getText().toString();
        final String birthDate = ((EditText)findViewById(R.id.birthDate_et)).getText().toString();
        final String address = ((EditText)findViewById(R.id.address_et)).getText().toString();

        if (name.length() > 0 && phoneNumber.length() > 9 && birthDate.length() > 5 && address.length() > 0) {

            FirebaseStorage storage = FirebaseStorage.getInstance();
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();
            // Create a reference to 'images/mountains.jpg'

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference mountainImagesRef = storageRef.child("users/"+user.getUid()+"/profileImage.jpg");

            try{
                InputStream stream = new FileInputStream(new File(profilePath));

                UploadTask uploadTask = mountainImagesRef.putStream(stream);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return mountainImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) { // 성공
                            Uri downloadUri = task.getResult();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            UserInfo userInfo = new UserInfo(name, phoneNumber, birthDate, address, downloadUri.toString());
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
                        } else { // 실패
                            // Handle failures
                        }
                    }
                });
            }catch(FileNotFoundException e){
                Log.e("로그 : ", "에러:"+e.toString());
            }

            // Access a Cloud Firestore instance from your Activity


        }else{
            startToast("개인정보를 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void startActivity(Class cls){
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, 0);
    }
    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}