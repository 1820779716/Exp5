package com.gxun.exp5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText data_1, data_2;
    private TextView mark, result;
    private Button btn_mul, btn_div;

    private static final int REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_1 = findViewById(R.id.data_1);
        data_2 = findViewById(R.id.data_2);
        mark = findViewById(R.id.mark);
        result = findViewById(R.id.result);
        btn_mul = findViewById(R.id.mul);
        btn_mul.setOnClickListener(this);
        btn_div = findViewById(R.id.div);
        btn_div.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (data_1.getText().toString().equals("") || data_2.getText().toString().equals("")){
            warming("请填写数据");
        }else if(v.getId()==R.id.mul) {
            sendData("*");
        }else if(v.getId()==R.id.div){
            if(Double.parseDouble(data_2.getText().toString()) != 0){
                sendData("/");
            }else{
                warming("除数不能为0");
            }
        }
    }
    public void sendData(String m){ //发送数据
        Intent intent = new Intent();
        intent.putExtra("data_1", Double.parseDouble(data_1.getText().toString()));
        intent.putExtra("data_2", Double.parseDouble(data_2.getText().toString()));
        intent.putExtra("mark", m);
        intent.setClass(this, getResult.class);
        startActivityForResult(intent,REQUESTCODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent){  //接收返回的数据
        switch(requestCode){
            case REQUESTCODE:
                if(resultCode == RESULT_OK){ //正常返回且有结果
                    super.onActivityResult(requestCode, resultCode, intent);
                    String m = intent.getExtras().getString("mark");
                    double rs = intent.getExtras().getDouble("result");
                    mark.setText(m);
                    result.setText(Double.toString(rs));
                }
                break;
            default:
                Toast.makeText(this, "无返回结果", Toast.LENGTH_SHORT);
                break;
        }
    }
    public void warming(String errorMassage){ //错误提示
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示" ) ;
        builder.setMessage(errorMassage) ;
        builder.setPositiveButton("确定",null );
        builder.show();
    }
}
