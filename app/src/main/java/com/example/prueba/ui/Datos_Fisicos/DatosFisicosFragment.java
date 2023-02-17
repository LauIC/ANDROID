package com.example.prueba.ui.Datos_Fisicos;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba.Central_activity;
import com.example.prueba.Imc_activity;
import com.example.prueba.MainActivity;
import com.example.prueba.R;
import com.example.prueba.databinding.FragmentDfisicoBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import BD.EstructuraBD;

public class DatosFisicosFragment extends Fragment {

    private FragmentDfisicoBinding binding;

    public View.OnClickListener listener;

    private RadioButton rb_h, rb_m;
    private TextInputLayout peso, altura;
    private TextView textocalendario;
    private Button AceptarDF;

    public DatosFisicosFragment(){ //constructor por defecto

    }



    View vista;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DatosFisicosViewModel  dfViewModel =
                new ViewModelProvider(this).get(DatosFisicosViewModel .class);


        vista=inflater.inflate(R.layout.fragment_dfisico, container, false); //Cargamos imagen del fragment

        Button AceptarDF =vista.findViewById(R.id.buttonDF);
        AceptarDF.setOnClickListener(listener);



       binding = FragmentDfisicoBinding.inflate(inflater, container, false);
       View root = binding.getRoot();

        ImageButton abrircalendario= (ImageButton) vista.findViewById(R.id.imageButtonCalendario);
        TextView textCalendario = (TextView) vista.findViewById(R.id.textcalendario);

        //Evento para insertar fecha
        abrircalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes=cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String fecha = String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                        textCalendario.setText(fecha);
                    }
                }, anio, mes, dia);
                dpd.show();
            }
        });

        AceptarDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rb_h = (RadioButton)vista.findViewById(R.id.radio_button_h);
                rb_m = (RadioButton)vista.findViewById(R.id.radio_button_m);
                peso = (TextInputLayout)vista.findViewById(R.id.peso);
                altura = (TextInputLayout)vista.findViewById(R.id.altura);
                textocalendario = (TextView)vista.findViewById(R.id.textcalendario);

                String sexo, texto_calendario, peso_usuario, altura_usuario;

                sexo = "Mujer";

                if(rb_h.isChecked()) {
                    sexo = "Hombre";
                }

                texto_calendario = textocalendario.getText().toString();
                peso_usuario = peso.getEditText().getText().toString();
                altura_usuario = altura.getEditText().getText().toString();

                Central_activity c = new Central_activity();

                c.AceptarDF(sexo, texto_calendario, peso_usuario, altura_usuario);

            }
        });

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return vista;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

    public void CalcIMC(View view){
        Intent i=new Intent(getActivity(), Imc_activity.class);
        startActivity(i);

    }



}