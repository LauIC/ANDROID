package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import BD.BBDD_Helper;
import BD.EstructuraBD;

public class Registro_activity  extends AppCompatActivity {

    Button botonEnviar;
    TextInputLayout textoNombre, textoEmail1, textoEmail2, textoPass1, textoPass2;

    final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_layout);


        /*Toolbar custom_toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(custom_toolbar);*/



        botonEnviar=(Button) findViewById(R.id.buttonEnv);
        textoNombre=(TextInputLayout) findViewById(R.id.NameReg);
        textoEmail1=(TextInputLayout) findViewById(R.id.EmailReg1);
        textoEmail2=(TextInputLayout) findViewById(R.id.EmailReg2);
        textoPass1=(TextInputLayout) findViewById(R.id.PassReg1);
        textoPass2=(TextInputLayout) findViewById(R.id.PassReg2);




        //***EVENTO BOTON ENVIAR (envio de datos a la bbdd)
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validacion de datosa través de los diferentes metodos creados
                //validación datos pasa saber si el usuario y contraseña coinciden en ambos campos
                if(validarDatos(textoEmail1.getEditText().getText().toString(), textoEmail2.getEditText().getText().toString(),
                        textoPass1.getEditText().getText().toString(), textoPass2.getEditText().getText().toString() )==true){

                    //validación datos para saber si el usuario y contraseña tiene el formato adecuado
                    if(validarEmail(textoEmail1.getEditText().getText().toString())==true && validarPass(textoPass1.getEditText().getText().toString())==true){

                        //validación datos para saber si el usuario está ya registrado
                        if(existeUsuario(textoEmail1.getEditText().getText().toString())==false){

                            //Accedemos a la BD
                            SQLiteDatabase db = helper.getWritableDatabase();

                            //capturamos el valor introducido por el usuario para almacenarlos en los campos de la BD
                            ContentValues values = new ContentValues();
                            values.put(EstructuraBD.COLUMNA_NOMBRE, textoNombre.getEditText().toString());
                            values.put(EstructuraBD.COLUMNA_EMAIL, textoEmail1.getEditText().toString());
                            values.put(EstructuraBD.COLUMNA_PASSWORD, textoPass1.getEditText().toString());

                            //insertamos nueva fila devolviendo el valor de la PK del nuevo registro.
                            //a db le pasamos el nombre de nuestra tabla, así como los valores capturados
                            String sql = "insert into USUARIOS (nombre_usuario,email,password) values ('" +  textoNombre.getEditText().getText().toString() + "','" + textoEmail1.getEditText().getText().toString() + "','" + textoPass1.getEditText().getText().toString() + "')";
                            //ejecutamos la select
                            db.execSQL(sql);
                            //Vinculamos a trvés de la función entrar con la pantalla del login
                            Entrar(view);


                        }else{
                            //ventana emergente
                            Snackbar.make(view, "El email ya está registrado", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }

                    }else{
                        //ventana emergente
                        Snackbar.make(view, "El email o contraseña no son válidos, por favor, vuelva a intentarlo", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }


                }else{
                    //ventana emergente
                    Snackbar.make(view, "El email o contraseña no coinciden", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                }

            }
        });
    }

    //*** FUNCION PARA VALIDAR LOS DATOS INTRODUCIDOS DEL FORMULARIO COINCIDEN EN AMBOS CAMPOS (EMAIL Y CONTRASEÑA)
    private boolean validarDatos(String Email1, String Email2, String Pass1, String Pass2){

        boolean valido;

        if(Email2.equals(Email1) &&  Pass2.equals(Pass1)){
            valido=true;
        }else{
            valido=false;
        }
        return valido;
    }

    //*** FUNCION PARA SABER SI EL USUARIO YA ESTÁ PREVIAMNETE REGISTRADO (a través del email)
    private boolean existeUsuario(String Email1){

        boolean existe;
        //accedemos a la bbdd
        SQLiteDatabase db2 = helper.getReadableDatabase();
        Cursor c = db2.rawQuery(" SELECT email FROM USUARIOS WHERE email='" + Email1+ "'", null);

        //si hay más de un registro con ese mail no es posible registro, si el resultado es 0 es posible el registro.
        if (c.getCount() > 0) {
            existe=true; //no es posible registro
        }else{
            existe=false;
        }
        return existe;
    }

    //*** FUNCION PARA VALIDAR EL FORMATO DE EMAIL
    private boolean validarEmail(String Email1){

        boolean emailvalido;
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        //buscamos coincidencias entre el email y patrón definido
        Matcher mather = pattern.matcher(Email1);
        emailvalido = mather.find();

        return emailvalido;

    }
    //*** FUNCION PARA VALIDAR EL FORMATO DE CONTRASEÑA
    private boolean validarPass(String Pass1){
        boolean PassValido;

        if(Pass1.length()>=8){
            PassValido=true;
        }else{
            PassValido=false;
        }
        return PassValido;

    }


    //**** INTENT CON LA ACTIVITY MAIN PARA REGRESAR A LA PANTALLA DE LOGIN EN CASO DE QUE EL REGISTRO SE HAYA RELAIZADO CON ÉXITO
    public void Entrar(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);

    }

}

