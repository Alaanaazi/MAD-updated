package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class CriminalRegister extends AppCompatActivity {

    EditText txtid,txtage,txtheight,txtcrime,txtarea;
    ImageView criminalimg;
    Button btnaddcriminal;
    DatabaseReference dbref;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Criminal criminal;
    int id=0;

    public Uri uri;
    private String path="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_register);

        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();

        txtid=findViewById(R.id.criminalid);
        txtage=findViewById(R.id.criminalage);
        txtheight=findViewById(R.id.criminalheight);
        txtcrime=findViewById(R.id.crime);
        txtarea=findViewById(R.id.suspectedarea);
        criminalimg=findViewById(R.id.criminalpic);

        btnaddcriminal=findViewById(R.id.registercriminal);
        criminal=new Criminal();

        criminalimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });

        btnaddcriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(txtid.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"please enter a Name",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtage.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the age", Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(txtheight.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the height", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtarea.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the suspected area", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtcrime.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the crime", Toast.LENGTH_SHORT).show();
                }else if(path.equals("null")) {
                    Toast.makeText(getApplicationContext(), "please insert the criminal's image", Toast.LENGTH_SHORT).show();
                }else{

                    dbref = FirebaseDatabase.getInstance().getReference().child("Criminal");
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                                id= (int) snapshot.getChildrenCount();


                            if (criminal.ArealengthisValid(txtarea.getText().toString().trim())) {
                                if (criminal.AgeisValid(Integer.parseInt(txtage.getText().toString().trim()))) {
                                    if (criminal.HeightisValid(Integer.parseInt(txtheight.getText().toString().trim()))) {


                                        criminal.setId(++id);
                                        criminal.setName(txtid.getText().toString().trim());
                                        criminal.setAge(Integer.parseInt(txtage.getText().toString().trim()));
                                        criminal.setHeight(Integer.parseInt(txtheight.getText().toString().trim()));
                                        criminal.setArea(txtarea.getText().toString().trim());
                                        criminal.setCrime(txtcrime.getText().toString().trim());
                                        criminal.setPic(path);

                                        dbref.child(String.valueOf(criminal.getId())).setValue(criminal);
                                        Toast.makeText(getApplicationContext(),"Successfully added", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Police_Criminal_View.class);
                                        startActivity(intent);


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Height limit is between 145cm&&195cm", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Age limit must be between 18 and 70", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Area name has exceeded the maximum limit", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });





    }

    private void choosePic() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){

            uri=data.getData();
            criminalimg.setImageURI(uri);
            uploadPic();

        }


    }

    private void uploadPic() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uplaoding Image");
        progressDialog.show();

        final String randomkey= UUID.randomUUID().toString();
       // Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        final StorageReference riversRef = storageReference.child("images/"+randomkey);
        //path=String.valueOf(riversRef.getPath());

        riversRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        StorageMetadata storageMetadata=taskSnapshot.getMetadata();
                        Task<Uri> downloadurl=riversRef.getDownloadUrl();
                        downloadurl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                path=uri.toString();
                            }
                        });
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"image uploaded",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"failed to upload upload",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progresspercent=(100.00*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Percentage "+(int)progresspercent +"%");
                    }
                });


    }
}