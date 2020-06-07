package com.gxun.exp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class getResult extends AppCompatActivity implements View.OnClickListener {

    private TextView expression;
    private Button btn_goBack;
    private String mark;
    private double data_1, data_2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);
        expression = findViewById(R.id.expression);
        btn_goBack = findViewById(R.id.goBack);
        btn_goBack.setOnClickListener(this);
        //接收数据
        Intent intent = getIntent();
        data_1 = intent.getExtras().getDouble("data_1");
        data_2 = intent.getExtras().getDouble("data_2");
        mark = intent.getExtras().getString("mark");
        if (mark.equals("*")){
            result = data_1*data_2;
        }else if (mark.equals("/")){
            result = data_1/data_2;
        }
        expression.setText(data_1+mark+data_2+"="+result);
    }
    public void onClick(View v){ //返回数据
        if(v.getId()==R.id.goBack){
            Intent intent = new Intent();
            if(mark.equals("*")){
                intent.putExtra("mark", "乘法");
            }else {
                intent.putExtra("mark", "除法");
            }
            intent.putExtra("result", result);
            intent.setClass(this, MainActivity.class);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
