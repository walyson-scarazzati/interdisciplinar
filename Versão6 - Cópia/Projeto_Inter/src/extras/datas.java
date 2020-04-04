/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package extras;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Administrador
 */
public class datas {
    static SimpleDateFormat formataBrasil =
            new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat formataBanco =
            new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formataHora =
            new SimpleDateFormat("HH:mm:ss");
    
    public static String formata_data_banco(String data){
        return formataBanco.format(data);
    }

    public static String formata_data_brasileira(String data){
        return formataBrasil.format(data);

    }
    public static String pega_data_sistema(){
        java.util.Date data = new java.util.Date();
        return formataBrasil.format(data);
    }
    public static String pega_data_extenso_brasileira(){
        // criamos um locale para o Brasil
        Locale localeBR = new Locale("pt", "BR");
        // vamos obter a data e hora atual
        Calendar agora = Calendar.getInstance();
        // vamos formatar de acordo com o formato de
        // data longa no português brasileiro
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, localeBR);
        return(df.format(agora.getTime()));
    }
        public static String pega_hora_sistema(){
        java.util.Date agora = new java.util.Date();
        return formataHora.format(agora);
    }

}
