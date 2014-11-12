package com.example.practica5;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	
	private Button btnGraba, btnRepro, btnDetener;
	
	 private static final String LOG_TAG = "Grabadora";         
	 private MediaRecorder mediaRecorder;
	 private MediaPlayer mediaPlayer;
	 private static String grabacion = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnGraba = (Button) findViewById(R.id.btnGraba);
        btnDetener = (Button) findViewById(R.id.btnDetener);
        btnRepro = (Button) findViewById(R.id.btnRepro);
        btnDetener.setEnabled(false);
        btnRepro.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void graba(View view){
    	btnGraba.setEnabled(false);
    	btnDetener.setEnabled(true);
        btnRepro.setEnabled(false);
        
    	mediaRecorder = new MediaRecorder();
    	mediaRecorder.setOutputFile(grabacion);
    	mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    	mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    	mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    	
    	try{
    		mediaRecorder.prepare();
    	}catch (IOException e){
    		Log.e(LOG_TAG, "Fallo la grabación");
    	}
    	 mediaRecorder.start();	
    }
    
    public void detener(View view){
    	btnGraba.setEnabled(true);
    	btnDetener.setEnabled(false);
        btnRepro.setEnabled(true);
        
    	mediaRecorder.stop();
    	mediaRecorder.release();
    }
    
    public void repro(View view){
    	mediaPlayer = new MediaPlayer();
    	try{
    		mediaPlayer.setDataSource(grabacion);
    		mediaPlayer.prepare();
    		mediaPlayer.start();
    	}catch (IOException e){
    		Log.e(LOG_TAG, "Fallo al Reproducir");
    	}
    }
}
