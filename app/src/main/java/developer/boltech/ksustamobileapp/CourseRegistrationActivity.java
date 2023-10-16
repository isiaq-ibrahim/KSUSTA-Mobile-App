package developer.boltech.ksustamobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CourseRegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
        
        Spinner spinner1 = (Spinner) findViewById(R.id.course_category_1);
        Spinner spinner2 = (Spinner) findViewById(R.id.course_category_2);
        Spinner spinner3 = (Spinner) findViewById(R.id.course_category_3);
        Spinner spinner4 = (Spinner) findViewById(R.id.course_category_4);
        Spinner spinner5 = (Spinner) findViewById(R.id.course_category_5);
        Spinner spinner6 = (Spinner) findViewById(R.id.course_category_6);
        Spinner spinner7 = (Spinner) findViewById(R.id.course_category_7);
        Spinner spinner8 = (Spinner) findViewById(R.id.course_category_8);
        Spinner spinner9 = (Spinner) findViewById(R.id.course_category_9);
        Spinner spinner10 = (Spinner) findViewById(R.id.course_category_10);
        Spinner spinner11 = (Spinner) findViewById(R.id.course_category_11);
        Spinner spinner12 = (Spinner) findViewById(R.id.course_category_12);
        Spinner spinner13 = (Spinner) findViewById(R.id.course_category_13);
        Spinner spinner14 = (Spinner) findViewById(R.id.course_category_14);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_of_courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);
        spinner8.setAdapter(adapter);
        spinner9.setAdapter(adapter);
        spinner10.setAdapter(adapter);
        spinner11.setAdapter(adapter);
        spinner12.setAdapter(adapter);
        spinner13.setAdapter(adapter);
        spinner14.setAdapter(adapter);

//        courseCategory = findViewById(R.id.course_category);

//        String [] items = new String[]{"Select Course","Digital Signal Processing",
//                "Project Management","Mobile Communication and Networking",
//                "Java Technology and Programming", "Micro-computer Hardware & Programming",
//                "E-Commerce Technology", "Telecommunication System and Design",
//                "Computer Graphics and Design", "Computer Security Techniques", "Communication Electronics",
//                "Project II", "Control System Engineering", "Digital Systems", "Electrical and Electronics Laboratory III",
//                 "Management Information System","Java Technology & Programming", "Satellite Communication", "Software Engineering",
//                "Data Communication Network and Network", "Student Industrial Work Experience Scheme (SIWES)",
//                "Use of English", "Artificial Neural Networks",
//                "Database Design and Management", "Independence Day", "Other Event"};
//        courseCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner1 = (Spinner) findViewById(R.id.course_category_1);
        Spinner spinner2 = (Spinner) findViewById(R.id.course_category_2);
        Spinner spinner3 = (Spinner) findViewById(R.id.course_category_3);
        Spinner spinner4 = (Spinner) findViewById(R.id.course_category_4);
        Spinner spinner5 = (Spinner) findViewById(R.id.course_category_5);
        Spinner spinner6 = (Spinner) findViewById(R.id.course_category_6);
        Spinner spinner7 = (Spinner) findViewById(R.id.course_category_7);
        Spinner spinner8 = (Spinner) findViewById(R.id.course_category_8);
        Spinner spinner9 = (Spinner) findViewById(R.id.course_category_9);
        Spinner spinner10 = (Spinner) findViewById(R.id.course_category_10);
        Spinner spinner11 = (Spinner) findViewById(R.id.course_category_11);
        Spinner spinner12 = (Spinner) findViewById(R.id.course_category_12);
        Spinner spinner13 = (Spinner) findViewById(R.id.course_category_13);
        Spinner spinner14 = (Spinner) findViewById(R.id.course_category_14);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);
        spinner7.setOnItemSelectedListener(this);
        spinner8.setOnItemSelectedListener(this);
        spinner9.setOnItemSelectedListener(this);
        spinner10.setOnItemSelectedListener(this);
        spinner11.setOnItemSelectedListener(this);
        spinner12.setOnItemSelectedListener(this);
        spinner13.setOnItemSelectedListener(this);
        spinner14.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
    }
}