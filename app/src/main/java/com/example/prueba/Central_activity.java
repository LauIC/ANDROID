package com.example.prueba;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.prueba.databinding.ActivityCentralBinding;
import com.example.prueba.ui.Datos_Fisicos.DatosFisicosFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

import BD.BBDD_Helper;
import BD.EstructuraBD;


public class Central_activity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityCentralBinding binding;

    private RadioButton rb_h, rb_m;
    private TextInputLayout peso, altura;
    private TextView textocalendario;
    private Button AceptarDF;





    DatosFisicosFragment fragment = new DatosFisicosFragment();

    View view;

    public Central_activity(){//Constructor por defecto

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final BBDD_Helper helper = new BBDD_Helper(this);

        binding = ActivityCentralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarCentral.toolbar);
        /*binding.appBarCentral.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();//accion floating button
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //configuramos y personalizamos el header
        View headerview = navigationView.getHeaderView(0);
        TextView useremail = (TextView) headerview.findViewById(R.id.textViewUserEmail);
        TextView username = (TextView) headerview.findViewById(R.id.textViewUserName);
        SQLiteDatabase db = helper.getReadableDatabase();
        Bundle extras = getIntent().getExtras();
        String Email1 = extras.getString("EMAIL_LOGIN");
        Cursor c = db.rawQuery(" SELECT email,nombre_usuario FROM USUARIOS WHERE email='" + Email1+ "'", null);

       if (c.moveToFirst()==true) {
            String user_email = c.getString(0); // 0 corresponde a email (segun select)
            String user_name =c.getString(1); //1 corresponde a name
            useremail.setText(user_email);
            username.setText(user_name);
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_datosfisicos, R.id.nav_patologias)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Evento para introducir o actualizar datosfisicos en la bbdd
        //obtenemos valores de los radiobutton contenidos en el radiogoup
        //RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup) ;

        /*rb_h = (RadioButton)findViewById(R.id.radio_button_h);
        rb_m = (RadioButton)findViewById(R.id.radio_button_m);
        peso = (TextInputLayout)findViewById(R.id.peso);
        altura = (TextInputLayout)findViewById(R.id.altura);
        textocalendario = (TextView)findViewById(R.id.textcalendario);
        //AceptarDF = (Button) findViewById(R.id.buttonDF);

        fragment.listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (existeDatosFisicos()== true){
                    //Update peso y altura
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(EstructuraBD.COLUMNA_PESO_DF, peso.getEditText().toString());
                    values.put(EstructuraBD.COLUMNA_ALTURA_DF, altura.getEditText().toString());
                    String Email = extras.getString("EMAIL_LOGIN");

                    String sql = "UPDATE DATOS_FISICOS_USUARIOS SET peso=" +  peso.getEditText().getText().toString() + " , altura= " + altura.getEditText().getText().toString() + " WHERE EXIST (SELECT NULL FROM USUARIOS WHERE USUARIOS.email = '"+ Email +"' AND USUARIOS.id= DATOS_FISICOS_USUARIOS.id_usuarios)";

                    db.execSQL(sql);

                    Toast toastdf1= Toast.makeText(Central_activity.this, "Datos actualizados",
                            Toast.LENGTH_SHORT);
                    toastdf1.setGravity(Gravity.CENTER, 0,0);
                    toastdf1.show();

                }else{
                    //Accedemos a la BDD
                    SQLiteDatabase db = helper.getWritableDatabase();

                    //capturamos el valor introducido por el usuario para almacenarlos en los campos de la BD
                    ContentValues values = new ContentValues();

                    String sexo = "Mujer";//Valor marcado por defecto

                    if(rb_h.isChecked()) {
                        sexo = "Hombre";
                    }

                    values.put(EstructuraBD.COLUMNA_SEXO_DF, sexo);
                    values.put(EstructuraBD.COLUMNA_FEC_NAC_DF, textocalendario.getText().toString());
                    values.put(EstructuraBD.COLUMNA_PESO_DF, peso.getEditText().toString());
                    values.put(EstructuraBD.COLUMNA_ALTURA_DF, altura.getEditText().toString());

                    String sql = "insert into DATOS_FISICOS_USUARIOS (sexo,fecha_nacimiento,peso, altura) values ('" +  sexo + "','" + textocalendario.getText().toString() + "','" + peso.getEditText().getText().toString() + "','" + peso.getEditText().getText().toString() +"')";

                   db.execSQL(sql);

                    //reportamos mensaje a usuario
                    Toast toastdf2= Toast.makeText(Central_activity.this, "Datos registrados",
                            Toast.LENGTH_SHORT);
                    toastdf2.setGravity(Gravity.CENTER, 0,0);
                    toastdf2.show();

                }
            }
        };*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.central_activity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Funcion para saber si ya hay datos fisicos del usuario introducidos
    public boolean existeDatosFisicos(){

        boolean existe_df = false;
        //final BBDD_Helper helper2 = new BBDD_Helper(this);
        final BBDD_Helper helper2 = new BBDD_Helper(this);

        Bundle extras = getIntent().getExtras();
        String Email = extras.getString("EMAIL_LOGIN");

        try {
            SQLiteDatabase bbdd = helper2.getReadableDatabase();
            Cursor c = bbdd.rawQuery(" SELECT DATOS_FISICOS_USUARIOS.* FROM DATOS_FISICOS_USUARIOS INNER JOIN USUARIOS on USUARIOS.email = '" + Email + "' AND USUARIOS.id =  DATOS_FISICOS_USUARIOS.id_usuarios", null);

            if (c.getCount() > 0) {
                existe_df=true; //ya exiten datos fisicos del usuario en la bd
            }else{
                existe_df=false;
            }

        }catch (Exception e){

        }

        return existe_df;
    }

    public void AceptarDF(String sexo, String textocalendario, String peso, String altura){


        if (existeDatosFisicos()== true){
            //Update peso y altura
            final BBDD_Helper helper = new BBDD_Helper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            Bundle extras = getIntent().getExtras();
            String Email = extras.getString("EMAIL_LOGIN");
            values.put(EstructuraBD.COLUMNA_PESO_DF, peso);
            values.put(EstructuraBD.COLUMNA_ALTURA_DF, altura);


            String sql = "UPDATE DATOS_FISICOS_USUARIOS SET peso=" +  peso + " , altura= " + altura + " WHERE EXIST (SELECT NULL FROM USUARIOS WHERE USUARIOS.email = '"+ Email +"' AND USUARIOS.id= DATOS_FISICOS_USUARIOS.id_usuarios)";

            db.execSQL(sql);

            Toast toastdf1= Toast.makeText(Central_activity.this, "Datos actualizados",
                    Toast.LENGTH_SHORT);
            toastdf1.setGravity(Gravity.CENTER, 0,0);
            toastdf1.show();

        }else{
            //Accedemos a la BDD
            final BBDD_Helper helper = new BBDD_Helper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            //capturamos el valor introducido por el usuario para almacenarlos en los campos de la BD
            ContentValues values = new ContentValues();


            values.put(EstructuraBD.COLUMNA_SEXO_DF, sexo);
            values.put(EstructuraBD.COLUMNA_FEC_NAC_DF, textocalendario);
            values.put(EstructuraBD.COLUMNA_PESO_DF, peso);
            values.put(EstructuraBD.COLUMNA_ALTURA_DF, altura);

            String sql = "insert into DATOS_FISICOS_USUARIOS (sexo,fecha_nacimiento,peso, altura) values ('" +  sexo + "','" + textocalendario + "'," + peso + "," + altura +")";

            db.execSQL(sql);

            //reportamos mensaje a usuario
            Toast toastdf2= Toast.makeText(Central_activity.this, "Datos registrados",
                    Toast.LENGTH_SHORT);
            toastdf2.setGravity(Gravity.CENTER, 0,0);
            toastdf2.show();

        }

    }


}