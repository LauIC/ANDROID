package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import BD.BBDD_Helper;
import BD.EstructuraBD;

public class MainActivity extends AppCompatActivity {

    Button botonEntrar;
    TextInputLayout textoEmail, textoPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonEntrar=(Button) findViewById(R.id.buttonLog);
        textoEmail=(TextInputLayout) findViewById(R.id.EmailLog);
        textoPass=(TextInputLayout) findViewById(R.id.PassLog);

        //creamos helper bbdd sqlite
        final BBDD_Helper helper = new BBDD_Helper(this);

        //****EVENTO BOTON ENTRAR***
        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getReadableDatabase();

                //Campos a devolver en la consulta
                String[] getCamposLogin = {
                        EstructuraBD.COLUMNA_EMAIL,
                        EstructuraBD.COLUMNA_PASSWORD
                };

                //Filtrado de resultado
                String seleccion = EstructuraBD.COLUMNA_EMAIL + " = ? AND " + EstructuraBD.COLUMNA_PASSWORD + " = ?";
                String[] argSeleccion = {textoEmail.getEditText().toString(), textoPass.getEditText().toString()};


                //Ejecutamos la query
                Cursor c = db.rawQuery(" SELECT email,password FROM USUARIOS WHERE email='" + textoEmail.getEditText().getText().toString() + "' AND password = '" + textoPass.getEditText().getText().toString() + "'", null);

                //movemos el cursor al primer registro de nuestra resultset
                c.moveToFirst();
                //instruccion para comprobar si hay algun registro con los credenciales introducidos
                if (c.getCount() > 0) {
                    //vinculamos con la siguiente activity (navigation drawer) a traves de la funcion EntrarPrincipal
                    EntrarPrincipal(view);

                }else{
                    //mensaje emergente
                    Toast toastapp= Toast.makeText(MainActivity.this, "Credenciales incorrectas, vuelva a intentarlo",
                            Toast.LENGTH_SHORT);
                    toastapp.setGravity(Gravity.CENTER, 0,0);//centramos el toast
                    toastapp.show();
                }

            }
        });

    }

    //**** INTENT CON LA ACTIVITY REGISTRO (BOTON REGISTRAR)
    public void Registrar (View view){

        Intent i =new Intent(this, Registro_activity.class);
        startActivity(i);

    }

    //**** INTENT CON LA ACTIVITY BAJA (BOTON BAJA)
    public void DarBaja(View view){
        Intent i = new Intent(this, Baja_activity.class);
        startActivity(i);
    }

    ////**** INTENT CON LA ACTIVITY CENTRAL (BOTON ENTRAR)
    public void EntrarPrincipal(View view){
        Intent i=new Intent(this, Central_activity.class);
        String strName = null;
        //transportamos el valor del mail para que la actividad a la que vinculamos pueda usarlo (en el header)
        i.putExtra("EMAIL_LOGIN", textoEmail.getEditText().getText().toString());
        startActivity(i);

    }

}