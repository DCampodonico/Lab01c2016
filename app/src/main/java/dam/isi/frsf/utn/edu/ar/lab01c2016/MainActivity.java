package dam.isi.frsf.utn.edu.ar.lab01c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText importe;
    SeekBar barraDias;
    TextView rendimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setParametros();

        
    }

    private void setParametros(){
        EditText importe = (EditText) findViewById(R.id.editText_importe);
        SeekBar barraDias = (SeekBar) findViewById(R.id.seekBar);
        TextView rendimiento = (TextView) findViewById(R.id.textView_rendimiento);
    }

    public void validarCampos(){

    }

    public void hacerPlazoFijo(){
        int dias = barraDias.getProgress();

    }

}
