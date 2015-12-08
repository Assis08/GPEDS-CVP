package br.edu.GPEDSCVP.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author rafa
 */
public class ValorLimitadoListener extends KeyAdapter {

    private double min = 0;
    private double max = 999999999;
    
    public ValorLimitadoListener( double min, double max){
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() instanceof JFormattedTextField) {
            validaValor((JFormattedTextField) e.getSource(), min, max);
        }
    }

    private void validaValor(JFormattedTextField campo, double min, double max) {
        if (campo.getFormatterFactory() instanceof DefaultFormatterFactory) {
            DefaultFormatterFactory defaultFormatterFactory = (DefaultFormatterFactory) campo.getFormatterFactory();
            if (defaultFormatterFactory.getDefaultFormatter() instanceof NumberFormatter) {
                NumberFormatter numberFormatter = (NumberFormatter) defaultFormatterFactory.getDefaultFormatter();
                if (numberFormatter.getFormat() instanceof DecimalFormat) {
                    DecimalFormat decimalFormat = (DecimalFormat) numberFormatter.getFormat();
                    double valor = 0;
                    try {
                        valor = decimalFormat.parse(campo.getText()).doubleValue();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    if (valor < min) {
                        campo.setText(decimalFormat.format(min));
                        return;
                    }
                    if (valor > max) {
                        campo.setText(decimalFormat.format(max));
                        return;
                    }
                    
                }
            }
        }
    }

}
