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
        importe.setText("0");
        btn_plazo_fijo.setOnClickListener(this);
        barraDias.setProgress(30);
        barraDias.setOnSeekBarChangeListener(this);
        importe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                importeCambiado();
            }
        });

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
            mensajeFinal.setTextColor(getResources().getColor(R.color.mensaje_correcto));
            String s = getResources().getString(R.string.msg_exito_plazo_fijo).toString();
            s = String.format(s, calcularResultado());
            mensajeFinal.setText(s);
        } else {
            mensajeFinal.setTextColor(getResources().getColor(R.color.mensaje_error));
            mensajeFinal.setText(getResources().getString(R.string.msg_error));
        }
    }

    private double calcularResultado(){
        double I, M, i, n;
        M = Double.parseDouble(importe.getText().toString());
        n = barraDias.getProgress();
        if(M<5000 && n<30)
            i = Double.parseDouble(getResources().getString(R.string.tasa_de_0_a_5000_menor_30_dias));
        else if(M<5000 && n>=30)
            i= Double.parseDouble(getResources().getString(R.string.tasa_de_0_a_5000_mayor_30_dias));
        else if(M<99999 && n<30)
            i= Double.parseDouble(getResources().getString(R.string.tasa_de_5000_a_99999_menor_30_dias));
        else if(M<99999 && n>=30)
            i= Double.parseDouble(getResources().getString(R.string.tasa_de_5000_a_99999_mayor_30_dias));
        else if(n<30)
            i= Double.parseDouble(getResources().getString(R.string.tasa_de_mas_de_99999_menor_30_dias));
        else
            i= Double.parseDouble(getResources().getString(R.string.tasa_de_mas_de_99999_mayor_30_dias));
        I = M * (Math.pow(1 + (i/100.0), (n/360.0)) - 1);
        rendimiento.setText("$" + String.format("%.3f", I));
        return I;
    }

    public void importeCambiado(){
        calcularResultado();
    }

    @Override
    public void onClick(View b) {
        if(btn_plazo_fijo.getId() == b.getId())
            hacerPlazoFijo();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(i == 1)
            dias.setText(Integer.toString(i) + " Día");
        else
            dias.setText(Integer.toString(i) + " Días");
        calcularResultado();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
