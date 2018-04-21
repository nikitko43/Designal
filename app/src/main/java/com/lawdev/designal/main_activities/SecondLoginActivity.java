package com.lawdev.designal.main_activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lawdev.designal.R;
import com.lawdev.designal.entities.Group;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SecondLoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView mPhoto;
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://designal-7d5de.appspot.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_login);
        mPhoto = findViewById(R.id.imageView);
    }

    private int PICK_IMAGE_REQUEST = 545;
    private int REQUEST_CROP_ICON = 454;

    public void onClickPhotoChoose(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            try {
                final Uri imageUri = data.getData();

                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                // indicate image type and Uri
                cropIntent.setDataAndType(imageUri, "image/*");
                // set crop properties
                cropIntent.putExtra("crop", "true");
                // indicate aspect of desired crop
                cropIntent.putExtra("aspectX", 1);
                cropIntent.putExtra("aspectY", 1);
                // indicate output X and Y
                cropIntent.putExtra("outputX", 280);
                cropIntent.putExtra("outputY", 280);

                // retrieve data on return
                cropIntent.putExtra("return-data", true);
                // start the activity - we handle returning in onActivityResult
                startActivityForResult(cropIntent, REQUEST_CROP_ICON);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mPhoto.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(SecondLoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
    }
    public void onClickFinished(View v){
        final Intent intent = getIntent();
        final Map<String, Object> user = new HashMap<>();
        user.put("name", intent.getStringExtra("name"));
        user.put("surname", intent.getStringExtra("surname"));
        user.put("position", ((EditText) findViewById(R.id.et_registration_position)).getText().toString());
        user.put("email", intent.getStringExtra("email"));

        StorageReference storage1 = storage.getReference();

        mPhoto.setDrawingCacheEnabled(true);
        mPhoto.buildDrawingCache();
        Bitmap bitmap = mPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference storageRef = storage1.child("images/" + intent.getStringExtra("email"));
        UploadTask uploadTask = storageRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            }
        });

        user.put("description", ((EditText) findViewById(R.id.et_description_registration)).getText().toString());


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SecondLoginActivity.this, "Успешно",
                                Toast.LENGTH_SHORT).show();

                        final Map<String, Object> group = new HashMap<>();

                        group.put("admin", Arrays.asList(intent.getStringExtra("email")));
                        group.put("name", "Личные задачи");
                        group.put("main_task", "main");
                        group.put("users", Arrays.asList(intent.getStringExtra("email")));

                        db.collection("groups")
                                .add(group)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        final Map<String, Object> task = new HashMap<>();

                                        task.put("creator", intent.getStringExtra("email"));
                                        task.put("users", Arrays.asList(intent.getStringExtra("email")));
                                        task.put("name", "Главная задача");
                                        task.put("description", "Здесь вы можете добавить описание к задаче!");

                                        db.collection("task")
                                                .add(task)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Intent intent = new Intent(SecondLoginActivity.this, GroupsActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(SecondLoginActivity.this, "Ошибка",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SecondLoginActivity.this, "Ошибка",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                        Intent intent = new Intent(SecondLoginActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SecondLoginActivity.this, "Ошибка",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
