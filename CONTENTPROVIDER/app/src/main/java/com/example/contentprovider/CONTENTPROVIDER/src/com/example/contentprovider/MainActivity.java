package com.example.contentprovider;

import com.example.contentprovider.R;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

public class MainActivity extends Activity {
	
	private static final Uri URI_CP = Uri.parse("content://mi.content.provider.contactos/contactos");
	private Uri uri;
	private Cursor c;
	
	private int id;
	private String nombre;
	private int telefono;
	private String email;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        ContentResolver CR = getContentResolver();
        
        
        // Insertamos 4 registros
        CR.insert(URI_CP, setVALORES(1, "Pedro", 111111111, "pedro@DB.es"));
        CR.insert(URI_CP, setVALORES(2, "Sandra", 222222222, "sandra@DB.es"));
        uri = CR.insert(URI_CP, setVALORES(3, "Maria", 333333333, "maria@DB.es"));
        Log.d("REGISTRO INSERTADO", uri.toString());
        uri = CR.insert(URI_CP, setVALORES(4, "Dani", 444444444, "dani@DB.es"));
        Log.d("REGISTRO INSERTADO", uri.toString());
        
        
        // Recuperamos todos los registros de la tabla
        String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
        c = CR.query(URI_CP, valores_recuperar, null, null, null);
        c.moveToFirst();
        do {
        	id = c.getInt(0);
        	nombre = c.getString(1);
            telefono = c.getInt(2);
            email = c.getString(3);
            Log.d("QUERY", "" +id+ ", " +nombre+ ", " +telefono+ ", " +email);
		} while (c.moveToNext());
        
        
        // Actualizamos un registro de la tabla
        uri = Uri.parse("content://mi.content.provider.contactos/contactos/3");
        CR.update(uri, setVALORES(3, "PPPPP", 121212121, "xxxx@xxxx.es"), 
        		null, null);
        // Y lo mostramos en el log
        c = CR.query(uri, valores_recuperar, null, null, null);
        c.moveToFirst();
        id = c.getInt(0);
        nombre = c.getString(1);
        telefono = c.getInt(2);
        email = c.getString(3);
        Log.d("QUERY", "" +id+ ", " +nombre+ ", " +telefono+ ", " +email);

        
        // Borramos un registro
        uri = Uri.parse("content://mi.content.provider.contactos/contactos/4");
        CR.delete(uri, null, null);
        
    }


    private ContentValues setVALORES(int id, String nom, int tlf, String email) {
    	ContentValues valores = new ContentValues();
    	valores.put("_id", id);
		valores.put("nombre", nom);
		valores.put("telefono", tlf);
		valores.put("email", email);
		return valores;
	}
}