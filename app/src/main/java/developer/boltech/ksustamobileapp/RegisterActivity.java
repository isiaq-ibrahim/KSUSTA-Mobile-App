package developer.boltech.ksustamobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextFullName, editTextEmailAddress, editTextAdmissionNo, editTextPhone, editTextPassword;
    private Button register;
    private TextView already_registered;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextFullName = findViewById(R.id.fullName);
        editTextEmailAddress = findViewById(R.id.emailAddress);
        editTextAdmissionNo = findViewById(R.id.admissionNo);
        editTextPhone = findViewById(R.id.phone);
        editTextPassword = findViewById(R.id.password);

        already_registered = findViewById(R.id.already_registered);
        already_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackToMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(goBackToMainActivity);
            }
        });

        progressBar = findViewById(R.id.progressBar);

        register = findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.learn6);

        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }

    private void registerUser() {

        String fullName = editTextFullName.getText().toString().trim();
        String emailAddress = editTextEmailAddress.getText().toString().trim();
        String admissionNo = editTextAdmissionNo.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if (emailAddress.isEmpty()){
            editTextEmailAddress.setError("Email Address is required!");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (admissionNo.isEmpty()){
            editTextAdmissionNo.setError("Admission no is required!");
            editTextAdmissionNo.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            editTextEmailAddress.setError("Please enter a valid email address!");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            editTextPhone.setError("Phone number is required!");
            editTextPhone.requestFocus();
            return;
        }

//        if (phone.length() > 10){
//            editTextPhone.setError("Phone number maximum limit exceeded!");
//            editTextPhone.requestFocus();
//            return;
//        }
//
//        if (phone.length() < 10){
//            editTextPhone.setError("Check your phone number and try again!");
//            editTextPhone.requestFocus();
//            return;
//        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Minimum password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(fullName, emailAddress, admissionNo, phone, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }else {
                                                Toast.makeText(RegisterActivity.this, "Failed to register user. Try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(RegisterActivity.this, "Failed to register user. Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}