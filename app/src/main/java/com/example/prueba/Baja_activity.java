package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import BD.BBDD_Helper;
import BD.EstructuraBD;

public class Baja_activity extends AppCompatActivity {


    ImageButton botonEliminar;
    TextInputLayout textoEmail, textoPass;

    final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baja_layout);

        botonEliminar=(ImageButton) findViewById(R.id.imageButton1);
        textoEmail=(TextInputLayout) findViewById(R.id.EmailBaja);
        textoPass=(TextInputLayout) findViewById(R.id.PassBaja);



        //****EVENTO BOTON ELIMINAR***
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();

                //Campos a devolver en la consulta
                String[] getCamposLogin = {
                        EstructuraBD.COLUMNA_EMAIL,
                        EstructuraBD.COLUMNA_PASSWORD
                };

                //Filtrado de resultado
                String seleccion = EstructuraBD.COLUMNA_EMAIL + " = ? AND " + EstructuraBD.COLUMNA_PASSWORD + " = ?";
                String[] argSeleccion = {textoEmail.getEditText().toString(), textoPass.getEditText().toString()};


                //Ejecutamos la query
                String query= "DELETE from USUARIOS WHERE email='" + textoEmail.getEditText().getText().toString() + "' AND password = '" + textoPass.getEditText().getText().toString() + "'";
                db.execSQL(query);

                //comprobamos si el usuario existe en bbdd
                if(Existe(textoEmail.getEditText().getText().toString())==false){
                    Toast.makeText(Baja_activity.this, "Su cuenta ha sido eliminada",
                            Toast.LENGTH_SHORT).show();
                    Entrar(view);

                }else{
                    Toast.makeText(Baja_activity.this, "No se ha podido eliminar su cuenta ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });


        }

    //*** FUNCION PARA VERIFICAR SI EL USUARIO EXISTE EN LA BBDD
    private boolean Existe(String email){

        boolean existe;
        //accedemos a la bbdd
        SQLiteDatabase db2 = helper.getReadableDatabase();
        Cursor c = db2.rawQuery(" SELECT email FROM USUARIOS WHERE email='" + email+ "'", null);

        //si hay registro se puede realizar la eliminacion
        if (c.getCount() > 0) {
            existe=true; //no se ha eliminado
        }else{
            existe=false;
        }
        return existe;

    }

    //**** INTENT CON LA ACTIVITY MAIN PARA REGRESAR A LA PANTALLA DE LOGIN EN CASO DE QUE LA BAJA SE HAYA RELAIZADO CON ÉXITO
    public void Entrar(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);

    }
}