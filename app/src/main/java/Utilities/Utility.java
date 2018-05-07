package Utilities;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Palacios on 6/5/2018.
 */

public class Utility  extends AppCompatActivity {

    public Button getButton(int id){
        return (Button)findViewById(id);
    }

    public EditText getEditText(int id){
        return (EditText)findViewById(id);
    }

    public TextView getTextView(int id){
        return (TextView)findViewById(id);
    }

    public Spinner getSpinner(int id){
        return (Spinner) findViewById(id);
    }

    public boolean validarEditText(EditText editText,String Mensaje){
        boolean estaVacio = false;
        if(TextUtils.isEmpty(editText.toString().trim())){
            editText.setError(Mensaje);
            editText.requestFocus();
            estaVacio = true;
        }

        return estaVacio;

    }

}
