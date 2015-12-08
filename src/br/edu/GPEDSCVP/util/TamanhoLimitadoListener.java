package br.edu.GPEDSCVP.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;

/**
 *
 * @author rafa
 */
public class TamanhoLimitadoListener extends KeyAdapter {

    private int max = 10;
    private String ultimoValor = "";

    public TamanhoLimitadoListener(int max) {
        this.max = max;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() instanceof JFormattedTextField) {
            validaTamanho((JFormattedTextField) e.getSource(), max);
        }
    }

    private void validaTamanho(JFormattedTextField campo, int max) {
        int dif = campo.getText().replace(".", "").replace(",", "").length() - max;
        if (dif > 0) {
            campo.setText(ultimoValor);
        }
        ultimoValor = campo.getText();
    }

}
