package com.example.propuesta8_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        paginador = (ViewPager) findViewById(R.id.contenedor2);

        Adaptador adaptador= new Adaptador(getSupportFragmentManager(), tabLayout.getTabCount());
        paginador.setAdapter(adaptador);
        paginador.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(paginador);

        TabLayout.Tab tab1=new TabLayout.Tab();
        tab1.setText("PRIMERO");
        tab1.setIcon(R.drawable.numero1);

        TabLayout.Tab tab2=new TabLayout.Tab();
        tab2.setText("SEGUNDO");
        tab2.setIcon(R.drawable.numero2);

        TabLayout.Tab tab3=new TabLayout.Tab();
        tab3.setText("TERCERO");
        tab3.setIcon(R.drawable.numero3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragmento=null;

                fragmento=adaptador.getItem(tab.getPosition());

                paginador.setCurrentItem(tab.getPosition());
                if(fragmento!=null){
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft= fm.beginTransaction();

                    ft.replace(R.id.contenedor, fragmento);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}