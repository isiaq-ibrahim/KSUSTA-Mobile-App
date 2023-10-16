package developer.boltech.ksustamobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView deleteNoticeRecycler;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;

    private ViewPager viewPager;
    private DepartmentAdapter departmentAdapter;
    private List<DepartmentModel> departmentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.smartSlider);

        ViewPager viewPager1 = view.findViewById(R.id.viewPager);

        List<SliderItem> sliderItems = new ArrayList<>();
        List<SliderItem> sliderItems1 = new ArrayList<>();

        sliderItems.add(new SliderItem(R.drawable.ksusta1,"image 1"));
        sliderItems.add(new SliderItem(R.drawable.ksusta2,"Image 2"));
        sliderItems.add(new SliderItem(R.drawable.ksusta3,"Image 3"));
        sliderItems.add(new SliderItem(R.drawable.ksusta4,"Image 4"));
        sliderItems.add(new SliderItem(R.drawable.ksusta5,"Image 5"));
//        sliderItems.add(new SliderItem("https://atikulislam.xyz/images/hero.jpg","Image from url"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2,5000));

//        new SliderAdapter((position, title, view1) -> {
//            Toast.makeText(getContext(), "Position: "+position+" Title: "+title, Toast.LENGTH_SHORT).show();
//        });

        departmentList = new ArrayList<>();
        departmentList.add(new DepartmentModel(R.drawable.ic_ict, "Information Comm. Technology", "The Department of Information Communication Technology was constructed in 2014, under the Faculty of Engineering. The department took about 43 students as their first intake, ranking the highest department to ever admit new students into their department."));
        departmentList.add(new DepartmentModel(R.drawable.ic_mechanical, "Mechanical Engineering", "The Department of Mechanical Engineering was constructed in 2014, under the Faculty of Engineering. The first intake of students into the department started in 2015. About 38 students were admitted in the 2015/2016 academic session."));
        departmentList.add(new DepartmentModel(R.drawable.ic_electric, "Electrical Engineering", "The Department of Electrical Engineering was constructed in 2014, under the Faculty of Engineering. The first intake of students into the department started in 2015. About 38 students were admitted in the 2015/2016 academic session."));
        departmentList.add(new DepartmentModel(R.drawable.ic_civil, "Civil Engineering", "The Department of Civil Engineering was constructed in 2014, under the Faculty of Engineering. The first intake of students into the department started in 2015. About 38 students were admitted in the 2015/2016 academic session."));
        departmentList.add(new DepartmentModel(R.drawable.ic_computer, "Computer Science", "The Department of Computer Science was constructed in 2008, under the Faculty of Engineering. The first intake of students into the department started in 2015. About 38 students were admitted in the 2015/2016 academic session."));
        departmentList.add(new DepartmentModel(R.drawable.ic_biochemistry, "Biochemistry", "The Department of Biochemistry was created in 2014, under the Faculty of Engineering. The first intake of students into the department started in 2015. About 38 students were admitted in the 2015/2016 academic session."));

        departmentAdapter = new DepartmentAdapter( getContext(), departmentList);
        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(departmentAdapter);
//        ImageView imageView = view.findViewById(R.id.college_image);
//
//        Glide.with(getContext())
//                .load("https://drive.google.com/file/d/1Zjt-MmdzyRdZ-XYbjeycMlysZE7_9ahb/view?usp=sharing")
//                .into(imageView);

        deleteNoticeRecycler = view.findViewById(R.id.deleteNoticeRecycler);
        progressBar = view.findViewById(R.id.progresssBar);

        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        deleteNoticeRecycler.setHasFixedSize(true);

        getNotice();

        return view;
    }
    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NoticeData data = snapshot.getValue(NoticeData.class);
                    list.add(0, data);
                }

                adapter = new NoticeAdapter(getContext(), list);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                deleteNoticeRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}