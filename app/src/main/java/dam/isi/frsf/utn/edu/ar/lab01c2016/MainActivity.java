package dam.isi.frsf.utn.edu.ar.lab01c2016;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    EditText importe;
    SeekBar barraDias;
    TextView rendimiento;
    CheckBox chk_renovacion;
    Button btn_plazo_fijo;
    TextView mensajeFinal;
    TextView dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setParametros();
        btn_plazo_fijo.setOnClickListener(this);
        barraDias.setOnSeekBarChangeListener(this);
        barraDias.setProgress(30);
    }

    private void setParametros() {
        importe = (EditText)findViewById(R.id.editText_importe);
        barraDias = (SeekBar)findViewById(R.id.seekBar);
        rendimiento = (TextView)findViewById(R.id.textView_resultado);
        btn_plazo_fijo = (Button)findViewById(R.id.button_plazo_fijo);
        mensajeFinal = (TextView)findViewById(R.id.textView_mensajeFinal);
        chk_renovacion = (CheckBox)findViewById(R.id.chk_renovacion);
        dias = (TextView)findViewById(R.id.textView_dias);
    }

    public boolean validarCampos() {
        return true;
    }

    public void hacerPlazoFijo(){
        if (validarCampos()) {
            rendimiento.setText("$ " + calcularResultado());
            mensajeFinal.setText("Se ha realizado correctamente el plazo fijo !");
            mensajeFinal.setTextColor(getResources().getColor(R.color.mensaje_correcto));
        } else {
            mensajeFinal.setText("No se ha realizado correctamente el plazo fijo !");
            mensajeFinal.setTextColor(getResources().getColor(R.color.mensaje_error));
        }
    }

    private double calcularResultado(){
        return 0.0;
    }

    public void onClick(View b) {
        if(btn_plazo_fijo.getId()==b.getId())
            hacerPlazoFijo();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(i == 1)
            dias.setText(i + " Día");
        else
            dias.setText(i + " Días");
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}
}
