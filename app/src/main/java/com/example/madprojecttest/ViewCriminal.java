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
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class ViewCriminal extends AppCompatActivity {
    EditText Name,Age,Height,Location,Case;
    Button btnUpdate,btnDelete;
    DatabaseReference upRef,delRef;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Criminal criminal;
    ImageView pic;
    public Uri uri;
    private String path="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_criminal);

        criminal=new Criminal();

        Bundle extras = getIntent().getExtras();

        final String value = extras.getString("id");


        pic=findViewById(R.id.criminalpic1);

        Name = findViewById(R.id.criminalName);
        Name.setEnabled(false);
        Age = findViewById(R.id.criminalage1);
        Height = findViewById(R.id.criminalheight1);
        Location = findViewById(R.id.suspectedarea1);
        Case = findViewById(R.id.crime1);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });

        upRef=FirebaseDatabase.getInstance().getReference().child("Criminal").child(value);
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Name.setText(snapshot.child("name").getValue().toString());
                Age.setText(snapshot.child("age").getValue().toString());
                Height.setText(snapshot.child("height").getValue().toString());
                Location.setText(snapshot.child("area").getValue().toString());
                Case.setText(snapshot.child("crime").getValue().toString());
                path=snapshot.child("pic").getValue().toString();
                Picasso.get().load(path).into(pic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUpdate=findViewById(R.id.updatecriminal);
        btnDelete=findViewById(R.id.deletecriminal);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(Name.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"please enter a Name",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Age.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the age", Toast.LENGTH_SHORT).show();
                }else  if(TextUtils.isEmpty(Height.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the height", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Location.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the suspected area", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Case.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "please enter the crime", Toast.LENGTH_SHORT).show();
                }else if(path.equals("null")) {
                    Toast.makeText(getApplicationContext(), "please insert the criminal's image", Toast.LENGTH_SHORT).show();
                }else{

                    if (criminal.ArealengthisValid(Location.getText().toString().trim())) {
                        if (criminal.AgeisValid(Integer.parseInt(Age.getText().toString().trim()))) {
                            if (criminal.HeightisValid(Integer.parseInt(Height.getText().toString().trim()))) {


                                upRef=FirebaseDatabase.getInstance().getReference().child("Criminal");
                                criminal.setId(Integer.parseInt(value));
                                criminal.setName(Name.getText().toString().trim());
                                criminal.setAge(Integer.parseInt(Age.getText().toString().trim()));
                                criminal.setHeight(Integer.parseInt(Height.getText().toString().trim()));
                                criminal.setArea(Location.getText().toString().trim());
                                criminal.setCrime(Case.getText().toString().trim());
                                criminal.setPic(path);

                                upRef.child(value).setValue(criminal);
                                Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),Police_Criminal_View.class);
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
            }
        });


          btnDelete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  delRef= FirebaseDatabase.getInstance().getReference().child("Criminal").child(value);
                  delRef.removeValue();
                  Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(getApplicationContext(),Police_Criminal_View.class);
                  startActivity(intent);


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
            pic.setImageURI(uri);
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