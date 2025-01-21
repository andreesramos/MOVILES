package com.example.andresramos_pruebatema9;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class Fragmento1 extends Fragment {

    public interface FragmentInteractionListener{
        void enviarTexto(String texto);
    }

    private FragmentInteractionListener listener;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragmento1, container, false);

        Button boton = view.findViewById(R.id.boton);
        EditText textoLeido = view.findViewById(R.id.textoLeido);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = textoLeido.getText().toString();
                listener.enviarTexto(texto);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener=(FragmentInteractionListener) context;
    }
}