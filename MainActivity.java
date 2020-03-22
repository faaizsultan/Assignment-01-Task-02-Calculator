package pk.edu.pucit.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    String temp;
    int decimal=0;
    char previous;
    //pressing equal sign.
    public void solve(View v){
        TextView tvresult=(TextView) findViewById(R.id.tv_result);
        TextView tvexpression=(TextView) findViewById(R.id.tv_equation);
        String text=tvexpression.getText().toString();
        if(text.length()>0){

            char caracterToCheck = text.charAt(text.length() - 1);
            if(caracterToCheck>= '0' && caracterToCheck<= '9')
            {

                String expression=tvexpression.getText().toString();
                Expression e=new Expression(expression);
                String result=String.valueOf(e.calculate());

                tvresult.setText(result);
            }
            else
            {
                Toast.makeText(this, "OOPS! Not Solvable..", Toast.LENGTH_SHORT).show();
            }
        }
    }
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
        TextView tvresultequiation=(TextView) findViewById(R.id.tv_result);
        tvresultequiation.setText("");

    }

    public void clicktext(View v) {    //clicking the digits or operaors..

        TextView textclicked = (TextView) v; //getting the button pressed.

        TextView tvresult = (TextView) findViewById(R.id.tv_equation); //getting the result equation.


        String  equation=tvresult.getText().toString();
        temp = textclicked.getText().toString();

        char toCheck = temp.charAt(temp.length() - 1);

        if(equation.length()>0)
        {
            if((previous=='X' || previous=='/' || previous=='-' || previous=='+' || previous=='%') && (toCheck=='X' || toCheck=='/' || toCheck=='-' || toCheck=='+' || toCheck=='%') ){
                Toast.makeText(this, "Ab ye to ksi kitab mai nae likha...", Toast.LENGTH_SHORT).show();
            }
            else if(previous=='.'){
                if((toCheck=='X' || toCheck=='/' || toCheck=='-' || toCheck=='+' || toCheck=='%')){
                    Toast.makeText(this, "Operator right after decimal", Toast.LENGTH_SHORT).show();
                }
                else if(toCheck=='.'){
                    Toast.makeText(this, "There cant be 2 decimals in a single digit..", Toast.LENGTH_SHORT).show();
                }
                else{
                    tvresult.setText(tvresult.getText().toString()+textclicked.getText().toString());
                    previous=toCheck;
                }

            }
            else if(toCheck=='.')
            {

                if(decimal==0)
                {

                    String prev = tvresult.getText().toString();
                    tvresult.setText(prev + textclicked.getText().toString());
                    System.out.println(textclicked.getText().toString());
                    decimal = 1;
                    previous=toCheck;
                    //putting decimal.
                }
                else
                {
                    Toast.makeText(this, "Ae te fair nai ho skda na hun!!!Pea g!", Toast.LENGTH_SHORT).show();
                }
            }
            else if(toCheck=='X' || toCheck=='/' || toCheck=='-' || toCheck=='+' || toCheck=='%' ){
                decimal=0; //clearing decimal check if OPERATOR has appeared..
                tvresult.setText(tvresult.getText().toString()+textclicked.getText().toString());
                previous=toCheck;

            }
            else
            {
                String prev = tvresult.getText().toString();
                tvresult.setText(prev + textclicked.getText().toString());
                previous=toCheck;
            }

        }
        else {
            if(toCheck=='X' || toCheck=='/' || toCheck=='-' || toCheck=='+' || toCheck=='%' ){
                Toast.makeText(this,"OPERATOR FIRST",Toast.LENGTH_SHORT).show();
            }//trying to write operator without operand.
            else if(toCheck=='.')
            {
                decimal=1;
                tvresult.setText(tvresult.getText().toString()+textclicked.getText().toString());
                previous=toCheck;
            }
            else
                tvresult.setText(tvresult.getText().toString()+textclicked.getText().toString());
            previous=toCheck;
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
