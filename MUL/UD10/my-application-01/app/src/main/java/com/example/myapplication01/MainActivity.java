package com.example.myapplication01;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etCodigo, etNombre, etApellidos;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodigo = findViewById(R.id.et_codigo);
        etNombre = findViewById(R.id.et_nombre);
        etApellidos = findViewById(R.id.et_apellidos);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(getApplicationContext());
        db = FirebaseDatabase.getInstance();
    }

    // Método para buscar usuario
    public void buscarUsuario(View v) {

        String codigo = etCodigo.getText().toString();

        if (!codigo.isEmpty()) {
            DatabaseReference refUsuario = db.getReference("usuarios").child(codigo);
            refUsuario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nombre = dataSnapshot.child("nombre").getValue(String.class);
                    String apellidos = dataSnapshot.child("apellido").getValue(String.class);
                    // Hacer algo con los datos del usuario
                    if (nombre != null) {
                        etNombre.setText(nombre);
                        if (apellidos != null) {
                            etApellidos.setText(apellidos);
                        }
                        Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_encontrado), Toast.LENGTH_LONG).show();
                    } else {
                        etNombre.setText("");
                        etApellidos.setText("");
                        Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_no_encontrado), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Manejar el error aquí
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), getText(R.string.toast_rellenar_codigo), Toast.LENGTH_LONG).show();
        }
    }

    // Método para insertar usuario
    public void insertarUsuario(View v) {

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();

        if (!(codigo.isEmpty() || nombre.isEmpty() || apellidos.isEmpty())) {

            // Obtener referencia a la ubicación deseada en la base de datos
            DatabaseReference refUsuario = db.getReference("usuarios").child(codigo);

            // Crear un nuevo objeto de datos en formato JSON
            Map<String, String> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("apellido", apellidos);

            // Agregar el nuevo objeto a la base de datos utilizando el método setValue
            refUsuario.setValue(datos);
            etCodigo.setText("");
            etNombre.setText("");
            etApellidos.setText("");

            Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_insertado), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getText(R.string.toast_rellenar_campos), Toast.LENGTH_LONG).show();
        }
    }

    // Método para modificar usuario
    public void modificarUsuario(View v) {

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();

        if (!(codigo.isEmpty() || nombre.isEmpty() || apellidos.isEmpty())) {

            DatabaseReference refUsuario = db.getReference("usuarios").child(codigo);

            refUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Si el usuario con ese código existe, actualizamos
                    if (snapshot.exists()) {
                        Map<String, Object> usuarioModificado = new HashMap<>();
                        usuarioModificado.put("nombre", nombre);
                        usuarioModificado.put("apellido", apellidos);

                        refUsuario.updateChildren(usuarioModificado);

                        Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_modificado), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_no_encontrado), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getText(R.string.toast_rellenar_campos), Toast.LENGTH_LONG).show();
        }
    }

    // Método para eliminar usuario
    public void eliminarUsuario(View v) {

        String codigo = etCodigo.getText().toString();

        if (!codigo.isEmpty()) {
            DatabaseReference refUsuario = db.getReference("usuarios").child(codigo);
            refUsuario.removeValue();
            Toast.makeText(getApplicationContext(), getText(R.string.toast_usuario_borrado), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getText(R.string.toast_rellenar_codigo), Toast.LENGTH_LONG).show();
        }
    }
}