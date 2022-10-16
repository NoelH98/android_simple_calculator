package com.noel.bar_hub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/*  @Author Noel.Eugene.Habaa */

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{


      // Variable declaration

       private Button btn_c, btn_bracket,btn_close_bracket,
               btn_div,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,
               btn_7,btn_8,btn_9,btn_0,btn_mul,btn_dot,btn_ac,
               btn_plus,btn_minus,btn_equal;

       private TextView result,solution;

  @Override
  public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);

      // check for permissions (needs update)
     // if(ContextCompat.checkSelfPermission( this, Manifest.permission) != PackageManager.PERMISSION_GRANTED)


      btn_c = (Button) findViewById(R.id.btn_c);
      btn_bracket = (Button)findViewById(R.id.btn_bracket);
      btn_close_bracket = (Button) findViewById(R.id.btn_close_bracket);
      btn_div = (Button)findViewById(R.id.btn_div);
      btn_1 = (Button) findViewById(R.id.btn_1);
      btn_2 = (Button)findViewById(R.id.btn_2);
      btn_3 = (Button) findViewById(R.id.btn_3);
      btn_4 = (Button)findViewById(R.id.btn_4);
      btn_5 = (Button) findViewById(R.id.btn_5);
      btn_6 = (Button)findViewById(R.id.btn_6);
      btn_7 = (Button) findViewById(R.id.btn_7);
      btn_8 = (Button)findViewById(R.id.btn_8);
      btn_9 = (Button) findViewById(R.id.btn_9);
      btn_0 = (Button)findViewById(R.id.btn_0);
      btn_ac = (Button) findViewById(R.id.btn_ac);
      btn_plus = (Button)findViewById(R.id.btn_plus);
      btn_minus = (Button) findViewById(R.id.btn_minus);
      btn_dot = (Button)findViewById(R.id.btn_dot);
      btn_mul = (Button) findViewById(R.id.btn_mul);
      btn_equal = (Button)findViewById(R.id.btn_equal);

      result = (TextView) findViewById(R.id.results);
      solution = (TextView) findViewById(R.id.sol);


      btn_c.setOnClickListener(this);
      btn_bracket.setOnClickListener(this);
      btn_close_bracket.setOnClickListener(this);
      btn_div.setOnClickListener(this);
      btn_1.setOnClickListener(this);
      btn_2.setOnClickListener(this);
      btn_3.setOnClickListener(this);
      btn_4.setOnClickListener(this);
      btn_5.setOnClickListener(this);
      btn_6.setOnClickListener(this);
      btn_7.setOnClickListener(this);
      btn_8.setOnClickListener(this);
      btn_9.setOnClickListener(this);
      btn_0.setOnClickListener(this);
      btn_ac.setOnClickListener(this);
      btn_plus.setOnClickListener(this);
      btn_minus.setOnClickListener(this);
      btn_dot.setOnClickListener(this);
      btn_mul.setOnClickListener(this);
      btn_equal.setOnClickListener(this);

      result = (TextView) findViewById(R.id.results);
      solution = (TextView) findViewById(R.id.sol);




  }

  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
      super.onActivityResult(requestCode,resultCode,data);
  }

  @Override
    public void onDestroy(){
      super.onDestroy();

  }

    @Override
    public void onClick(View v) {
          Button button = (Button) v;
          String buttonText = button.getText().toString();
          String data = solution.getText().toString();

          if(buttonText.equals("AC")){
              solution.setText("");
              result.setText("0");
              return;
          }

          if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
          }

          if(buttonText.equals("C")){
              data = data.substring(0,data.length()-1);
          } else {
              data = data+buttonText;
          }
          solution.setText(data);

          String res = getResult(data);

          if(!res.equals("Err")){
              result.setText(res);
          }
    }

    String getResult(String data){
      try{
          Context context = Context.enter();
          context.setOptimizationLevel(-1);
          Scriptable scriptable = context.initStandardObjects();
          String res = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
          if(res.endsWith(".0")){
              res = res.replace(".0","");
          }
          return res;
      }catch (Exception e){
          e.printStackTrace();
          return "Err";
      }
    }
}
