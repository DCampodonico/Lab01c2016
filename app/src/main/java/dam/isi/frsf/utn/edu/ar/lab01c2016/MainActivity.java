/*
 * Copyright (c) 2016 Daniel Campodonico; Emiliano Gioria; Lucas Moretti.
 * This file is part of Lab01c2016.
 *
 * Lab01c2016 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Lab01c2016 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Lab01c2016.  If not, see <http://www.gnu.org/licenses/>.
 */

package dam.isi.frsf.utn.edu.ar.lab01c2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private EditText importe;
    private SeekBar barraDias;
    private TextView rendimiento;
    private CheckBox chk_renovacion;
    private Button btn_plazo_fijo;
    private TextView mensajeFinal;
    private TextView dias;

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

    private void hacerPlazoFijo(){
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
