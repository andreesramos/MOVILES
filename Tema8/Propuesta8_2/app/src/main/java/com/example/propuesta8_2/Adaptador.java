package com.example.propuesta8_2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adaptador extends FragmentStatePagerAdapter {

    int numTab;

    public Adaptador(FragmentManager fm, int numTab){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numTab=numTab;

    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new PrimerFragmento();
            case 1:
                return new SegundoFragmento();
            case 2:
                return new TercerFragmento();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return numTab;
    }
}
