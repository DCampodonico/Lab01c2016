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
