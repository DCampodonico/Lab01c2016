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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnFocusChangeListener {

    private EditText importe, correo, cuit;
    private SeekBar barraDias;
    private Button btn_plazo_fijo;
    private TextView mensajeFinal, dias, rendimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setParametros();
        btn_plazo_fijo.setOnClickListener(this);
        barraDias.setOnSeekBarChangeListener(this);
        importe.setOnFocusChangeListener(this);
        importe.setText("0");
        barraDias.setProgress(30);
    }

    private void setParametros() {
        importe = (EditText)findViewById(R.id.editText_importe);
        correo = (EditText)findViewById(R.id.editText_correo);
        cuit = (EditText)findViewById(R.id.editText_cuit);
        barraDias = (SeekBar)findViewById(R.id.seekBar);
        btn_plazo_fijo = (Button)findViewById(R.id.button_plazo_fijo);
        mensajeFinal = (TextView)findViewById(R.id.textView_mensajeFinal);
        dias = (TextView)findViewById(R.id.textView_dias);
        rendimiento = (TextView)findViewById(R.id.textView_resultado);
    }

    private String validarCampos() {
        String error = "";
        if(!validarEmail(correo.getText().toString().trim())){
            error += getResources().getString(R.string.msg_error_correo) + "\n";
        }
        if(!validarCuit(cuit.getText().toString().trim())) {
            error += getResources().getString(R.string.msg_error_cuit) + "\n";
        }
        if(!validarImporte(importe.getText().toString().trim())) {
            error += getResources().getString(R.string.msg_error_importe) + "\n";
        }
        return error;
    }

    private boolean validarEmail(String s) {
        return !TextUtils.isEmpty(s) && Patterns.EMAIL_ADDRESS.matcher(s).matches();
    }

    private boolean validarCuit(String s){
        return s.matches("[0-9]{11}");
    }

    private boolean validarImporte(String s){
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(s);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    private void hacerPlazoFijo(){
        String error = validarCampos();
        if (error.isEmpty()) {
            //Calcular interes
            double interes = calcularInteres();

            //Mostrar interes
            //Setear color
            mensajeFinal.setTextColor(ContextCompat.getColor(this,R.color.mensaje_correcto));

            mostrarRendimiento(interes);

            //Setear mensaje plazo fijo
            String mensajePlazoFijo = getResources().getString(R.string.msg_exito_plazo_fijo);
            mensajePlazoFijo = String.format(Locale.getDefault(),mensajePlazoFijo, interes);
            mensajeFinal.setText(mensajePlazoFijo);
        } else {
            //Setear mensaje error
            mensajeFinal.setTextColor(ContextCompat.getColor(this,R.color.mensaje_error));
            mensajeFinal.setText(error);
        }
    }

    private void mostrarRendimiento(double interes){
        String mensajeRendimiento = "$" + String.format(Locale.getDefault(),"%.3f", interes);
        rendimiento.setText(mensajeRendimiento);
    }

    private double calcularInteres(){
        double interes, capital, tasaInteres, plazo;

        try {
            capital = Double.parseDouble(importe.getText().toString().trim());
        } catch(Exception e){
            capital = 0;
        }

        plazo = barraDias.getProgress();

        int tasaInteresStrId;
        if(capital<5000 && plazo<30) {
            tasaInteresStrId = R.string.tasa_de_0_a_5000_menor_30_dias;
        }
        else if(capital<5000 && plazo>=30) {
            tasaInteresStrId = R.string.tasa_de_0_a_5000_mayor_30_dias;
        }
        else if(capital<99999 && plazo<30) {
            tasaInteresStrId = R.string.tasa_de_5000_a_99999_menor_30_dias;
        }
        else if(capital<99999 && plazo>=30) {
            tasaInteresStrId = R.string.tasa_de_5000_a_99999_mayor_30_dias;
        }
        else if(plazo<30) {
            tasaInteresStrId = R.string.tasa_de_mas_de_99999_menor_30_dias;
        }
        else {
            tasaInteresStrId = R.string.tasa_de_mas_de_99999_mayor_30_dias;
        }

        tasaInteres = Double.parseDouble(getResources().getString(tasaInteresStrId));

        interes = capital * (Math.pow(1 + (tasaInteres/100.0), (plazo/360.0)) - 1);

        return interes;
    }

    @Override
    public void onClick(View b) {
        if(btn_plazo_fijo.getId() == b.getId()){
            hacerPlazoFijo();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(barraDias.getId() == seekBar.getId()) {
            String textoDias = i + " ";
            if (i == 1) {
                textoDias += getResources().getString(R.string.dia);
            } else {
                textoDias += getResources().getString(R.string.dias);
            }
            dias.setText(textoDias);

            //Cantidad de dias cambiados, actualiza rendimiento
            mostrarRendimiento(calcularInteres());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(importe.getId() == v.getId()) {
            //Importe cambiado, actualiza rendimiento
            mostrarRendimiento(calcularInteres());
        }
    }
}
