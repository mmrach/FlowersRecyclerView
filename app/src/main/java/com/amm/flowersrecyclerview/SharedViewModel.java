package com.amm.flowersrecyclerview;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> selectedFlower;

    //Constructor sin parametros para que podamos usar la default Application Factory.
    public SharedViewModel() {
    }

    public LiveData<String> getSharedData() {
        if (selectedFlower == null){
            selectedFlower = new MutableLiveData<String>();
        }
        return selectedFlower;
    }

    public void setSharedData(String string) {
        if (selectedFlower==null){
            selectedFlower = new MutableLiveData<String>();
        }
        this.selectedFlower.setValue(string);
    }
}
