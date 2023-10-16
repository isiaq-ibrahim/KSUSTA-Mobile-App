package developer.boltech.ksustamobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private CardView biodata, medical, course, payment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullNameTextView = (TextView) view.findViewById(R.id.fullName);
        final TextView emailAddressTextView = (TextView) view.findViewById(R.id.emailAddress);
        final TextView admissionNumberTextView = (TextView) view.findViewById(R.id.admissionNo);
        final TextView phoneTextView = (TextView) view.findViewById(R.id.phone);

        final CardView biodata = (CardView) view.findViewById(R.id.student_biodata);
        final CardView medical = (CardView) view.findViewById(R.id.medical_form);
        final CardView course = (CardView) view.findViewById(R.id.course_registration);
        final CardView payment = (CardView) view.findViewById(R.id.payment_invoice);

        biodata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentBiodataActivity.class);
                startActivity(intent);
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MedicalFormActivity.class);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CourseRegistrationActivity.class);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PaymentInformationActivity.class);
                startActivity(intent);
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){

                    String fullName = userProfile.fullName;
                    String emailAddress = userProfile.emailAddress;
                    String admissionNo = userProfile.admissionNo;
                    String phone = userProfile.phone;

                    fullNameTextView.setText(fullName);
                    emailAddressTextView.setText(emailAddress);
                    admissionNumberTextView.setText(admissionNo);
                    phoneTextView.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
