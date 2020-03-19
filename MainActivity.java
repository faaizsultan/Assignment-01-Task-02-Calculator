package pk.edu.pucit.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        int decimal=0;
    //pressing DEL button.
        public void remove(View v){
            TextView tvresult=(TextView) findViewById(R.id.tv_equation);
            String text=tvresult.getText().toString();
            System.out.println(text.length());
            if(text.length()>0) {
                Character caracterToCheck = text.charAt(text.length() - 1);
                Log.i("Character", caracterToCheck.toString());
                //removing if there was a decimal removed.
                if (caracterToCheck.equals('.')) {
                    Log.i("Vlue", "Entered to remove");
                    decimal = 0;
                }
                String strnew = text.substring(0, text.length() - 1);
                tvresult.setText(strnew);
            }
    }
   //pressing AC button
    public void clearAll(View v){
        TextView tvresult=(TextView) findViewById(R.id.tv_equation);
        tvresult.setText("");
        decimal=0;
    }

    public void clicktext(View v){

        TextView textclicked=(TextView)v;
        TextView tvresult=(TextView) findViewById(R.id.tv_equation);
        if(textclicked.getText().toString().equals("."))
        {
            if(decimal==0) {
                String prev = tvresult.getText().toString();
                tvresult.setText(prev + textclicked.getText().toString());
                decimal=1;
            }
        }
        else{
            String prev = tvresult.getText().toString();
            tvresult.setText(prev + textclicked.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvresult=(TextView) findViewById(R.id.tv_result);
        tvresult.setText("");
        tvresult=(TextView) findViewById(R.id.tv_equation);
        tvresult.setText("");
    }
}
