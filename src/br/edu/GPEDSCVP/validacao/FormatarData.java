package br.edu.GPEDSCVP.validacao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe útil para conversão de tipos
 *
 * @author Willys
 */
public class FormatarData {

    /**
     * Converte uma String para Integer
     *
     * @param valor
     * @return
     */
    public static int stringParaInteger(String valor) {
        try {
            return Integer.parseInt(valor.trim());
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * Converte uma String para Double
     *
     * @param valor
     * @return
     */
    public static double stringParaDouble(String valor) {
        try {
            return Double.parseDouble(valor.trim());
        } catch (Exception ex) {
            return 0.0;
        }
    }

    /**
     * Converte para Date uma String no formato "dd/MM/yyyy"
     *
     * @param data
     * @return
     */
    public static Date stringParaDate(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(data);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Converte para String um Date no formato "dd/MM/yyyy"
     *
     * @param data
     * @return
     */
    public static String dateParaString(Date data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(data);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Converte o tipo String "dd/MM/yyyy" para o tipo java.sql.Date.
     * O tipo Date garante que será gravado somente Data no banco de dados.
     *
     * @param data
     * @return
     */
    public static java.sql.Date stringParaSQLDate(String data) {
        try {
            Date date = stringParaDate(data);
            return dateParaSQLDate(date);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Converte o tipo java.util.Date para o tipo java.sql.Date.
     * O tipo Date garante que será gravado somente Data no banco de dados.
     *
     * @param data
     * @return
     */
    public static java.sql.Date dateParaSQLDate(Date data) {
        try {
            return new java.sql.Date(data.getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Converte o tipo java.util.Date para String no formato "dd/MM/yy HH:mm"
     * @param data
     * @return 
     */
    public static String dateTimeParaString(Date data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
            return format.format(data);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Converte o tipo java.util.Date para o tipo java.sql.Timestamp.
     * O tipo Timestamp garante que será gravado Data e Hora no banco de dados.
     *
     * @param data
     * @return
     */
    public static Timestamp dateParaTimeStamp(Date data) {
        try {
            return new Timestamp(data.getTime());
        } catch (Exception ex) {
            return null;
        }
    }
    
   
     /** retorna a data atual 
     *
     * @param 
     * @return
     */
    public String DataAtual()
    {
        java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String data_atual = fmt.format(data);
        return data_atual;
    }
    
    //Calcula a Idade baseado em uma String data;
    public static int calculaIdade(String dataNasc, String pattern){

        DateFormat sdf = new SimpleDateFormat(pattern);
        Date dataNascInput = null;

        try {
            dataNascInput= sdf.parse(dataNasc);
        } catch (Exception e) {}

        Calendar dateOfBirth = new GregorianCalendar();

        dateOfBirth.setTime(dataNascInput);

        // Cria um objeto calendar com a data atual

        Calendar today = Calendar.getInstance();

        // Obtém a idade baseado no ano

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        dateOfBirth.add(Calendar.YEAR, age);

        if (today.before(dateOfBirth)) {
            age--;
        }

        return age;
    }
    
    
    //Organiza uma data que não esta na sequencia adequada para ser vista pelo usuário (datas que retornam do banco)
    public String organizaData(Object data){
        
        SimpleDateFormat formatTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        
        if (data instanceof Timestamp) {
            return formatTimeStamp.format(data);
        }else if (data instanceof Date){
            return formatDate.format(data);
        }else if (data instanceof java.sql.Date){
            return formatTimeStamp.format(data);
        }else if (data instanceof String){ 
           
            return formatDate.format(data);
        }
        return null;
    }
}

