package com.example.prueba.ui.Datos_Fisicos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DatosFisicosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DatosFisicosViewModel () {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}