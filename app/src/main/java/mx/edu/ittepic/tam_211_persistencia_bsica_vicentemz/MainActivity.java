package mx.edu.ittepic.tam_211_persistencia_bsica_vicentemz;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText correo;
    RadioGroup radio;
    RadioButton masculino,femenino;
    CheckBox fut,voli,basquet;
    Button guardar,mostrar;
    Spinner zodiaco;
    String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo=findViewById(R.id.correo);
        radio=findViewById(R.id.radio);
        masculino=findViewById(R.id.mascu);
        femenino=findViewById(R.id.feme);
        fut=findViewById(R.id.fut);
        voli=findViewById(R.id.voli);
        basquet=findViewById(R.id.basq);
        zodiaco=findViewById(R.id.zodiaco);
        guardar=findViewById(R.id.guardar);
        mostrar=findViewById(R.id.mostrar);

        cargarPreferencias();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences= getSharedPreferences("Credenciales",MODE_PRIVATE);

                String email=correo.getText().toString();

                SharedPreferences.Editor editor=preferences.edit();

                editor.putString("email", email);

                if (masculino.isChecked())
                {
                    editor.putBoolean("masculino",true);
                }
                else
                {
                    editor.putBoolean("masculino",false);
                }
                if(fut.isChecked()){
                    editor.putBoolean("fut",true);
                }
                if(voli.isChecked()){
                    editor.putBoolean("voli",true);
                }
                if(basquet.isChecked()){
                    editor.putBoolean("basq",true);
                }

                editor.putString("zodiaco", zodiaco.getSelectedItem().toString());

                editor.commit();

                Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_SHORT).show();
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarPreferencias();
            }
        });

    }

    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences("Credenciales",MODE_PRIVATE);

        String email=preferences.getString("email","");
        Boolean mascu=preferences.getBoolean("masculino",true);
        correo.setText(email);
        if (mascu) {masculino.setChecked(true);} else {femenino.setChecked(true);}

        if(preferences.getBoolean("fut",false)){
            fut.setChecked(true);
        }else{fut.setChecked(false);}

        if(preferences.getBoolean("voli",false)){
            voli.setChecked(true);
        }else{voli.setChecked(false);}

        if(preferences.getBoolean("basq",false)){
            basquet.setChecked(true);
        }else{basquet.setChecked(false);}

        zodiaco.setSelection(((ArrayAdapter<String>)zodiaco.getAdapter()).getPosition(preferences.getString("zodiaco","Aries")));


    }
}
