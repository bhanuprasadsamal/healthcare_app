package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Ajit Saste", "Hospital Address : Bissamcuttack", "Exp : 5yrs", "Mobile No:8984827236", "600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Paralakhemundi", "Exp : 15yrs", "Mobile No:8018931867", "900"},
                    {"Doctor Name : Swapnil Kale", "Hospital Address : Brahmapur", "Exp : 8yrs", "Mobile No:8898560034", "300"},
                    {"Doctor Name : Deepak Deshukh", "Hospital Address : Jagapur", "Exp : 6yrs", "Mobile No:7989800067", "500"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Bhubaneswar", "Exp : 7yrs", "Mobile No:7798456634", "899"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Neelam Patil", "Hospital Address : Bhubaneswar", "Exp : 5yrs", "Mobile No:9898989898", "600"},
                    {"Doctor Name : Swati Pawar", "Hospital Address : Cuttack", "Exp : 15yrs", "Mobile No:7898989898", "900"},
                    {"Doctor Name : Neeraja Kale", "Hospital Address : Bhubaneswar", "Exp : 10yrs", "Mobile No:7798989898", "378"},
                    {"Doctor Name : Mayuri Deshmukh", "Hospital Address : Brahmapur", "Exp : 5yrs", "Mobile No:9898769898", "700"},
                    {"Doctor Name : Minakshi Panda", "Hospital Address : Jajpur", "Exp : 8yrs", "Mobile No:9898989435", "500"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Seema Patil", "Hospital Address : Cherrapunji", "Exp : 5yrs", "Mobile No:9890989898", "600"},
                    {"Doctor Name : Pnkaj Parab", "Hospital Address : Gunupur", "Exp : 15yrs", "Mobile No:7898989488", "900"},
                    {"Doctor Name : Monish Jain", "Hospital Address : Rourkela", "Exp : 10yrs", "Mobile No:7798982798", "1338"},
                    {"Doctor Name : Vishal Deshmukh", "Hospital Address : Rourkela", "Exp : 5yrs", "Mobile No:0198769898", "740"},
                    {"Doctor Name : Minakshi Panda", "Hospital Address : Puri", "Exp : 16yrs", "Mobile No:9898369435", "570"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Amol Gawade", "Hospital Address : Balangir", "Exp : 5yrs", "Mobile No:9898555898", "690"},
                    {"Doctor Name : Prasad Parwar", "Hospital Address : Puri", "Exp : 15yrs", "Mobile No:7898885898", "900"},
                    {"Doctor Name : Nilesh Kale", "Hospital Address : Baripada", "Exp : 6yrs", "Mobile No:7798916898", "389"},
                    {"Doctor Name : Deepak Deshpande", "Hospital Address : Bargarh", "Exp : 5yrs", "Mobile No:9098769898", "7000"},
                    {"Doctor Name : Ashok Singh", "Hospital Address : Sambalpur", "Exp : 8yrs", "Mobile No:198989435", "200"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Nilesh Borate", "Hospital Address : Bhubaneswer", "Exp : 5yrs", "Mobile No:5678989898", "600"},
                    {"Doctor Name : Pamkaj Pawar", "Hospital Address : Puri", "Exp : 15yrs", "Mobile No:7898918098", "900"},
                    {"Doctor Name : Swapnil lele", "Hospital Address : Sambalpur", "Exp : 10yrs", "Mobile No:7792789898", "600"},
                    {"Doctor Name : Deepak Kumar", "Hospital Address : Koraput", "Exp : 5yrs", "Mobile No:9898769783", "700"},
                    {"Doctor Name : Ankul Panda", "Hospital Address : Jajpur", "Exp : 5yrs", "Mobile No:2235564456", "500"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentists")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multil_ines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}