package developer.boltech.ksustamobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView ictDepartment, mechDepartment, civilDepartment, eeDepartment, csDepartment,
            agrDepartment, phyDepartment, chemDepartment, bchemDepartment, mthDepartment, medDepartment,
            nurseDepartment;
    private LinearLayout ictNoData, mechNoData, eeNoData, civilNoData, csNoData, agrNoData, phyNoData,
            chemNoData, bchemNoData, mthNoData, medNoData, nurseNoData;
    private List<StaffData> list1, list2, list3, list4, list5, list6, list7, list8, list9, list10,
            list11, list12;
    private DatabaseReference reference, dbRef;
    private StaffAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty,container,false);

        ictDepartment = view.findViewById(R.id.ictDepartment);
        mechDepartment = view.findViewById(R.id.mechDepartment);
        civilDepartment = view.findViewById(R.id.civilDepartment);
        eeDepartment = view.findViewById(R.id.eeDepartment);

        ictNoData = view.findViewById(R.id.ictNoData);
        mechNoData = view.findViewById(R.id.mechNoData);
        civilNoData = view.findViewById(R.id.civilNoData);
        eeNoData = view.findViewById(R.id.eeNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("staff");

        ictDepartment();
        mechDepartment();
        civilDepartment();
        eeDepartment();

        return view;
    }

    private void ictDepartment() {
        dbRef = reference.child("Information Communication Technology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    ictNoData.setVisibility(View.VISIBLE);
                    ictDepartment.setVisibility(View.GONE);
                }else {
                    ictNoData.setVisibility(View.GONE);
                    ictDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        list1.add(data);
                    }
                    ictDepartment.setHasFixedSize(true);
                    ictDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StaffAdapter(list1, getContext());
                    ictDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechDepartment() {
        dbRef = reference.child("Mechanical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDepartment.setVisibility(View.GONE);
                }else {
                    mechNoData.setVisibility(View.GONE);
                    mechDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        list2.add(data);
                    }
                    mechDepartment.setHasFixedSize(true);
                    mechDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StaffAdapter(list2, getContext());
                    mechDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civilDepartment() {
        dbRef = reference.child("Civil Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    civilNoData.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }else {
                    civilNoData.setVisibility(View.GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        list3.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StaffAdapter(list3, getContext());
                    civilDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeDepartment() {
        dbRef = reference.child("Electrical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eeNoData.setVisibility(View.VISIBLE);
                    eeDepartment.setVisibility(View.GONE);
                }else {
                    eeNoData.setVisibility(View.GONE);
                    eeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        list4.add(data);
                    }
                    eeDepartment.setHasFixedSize(true);
                    eeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StaffAdapter(list4, getContext());
                    eeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}