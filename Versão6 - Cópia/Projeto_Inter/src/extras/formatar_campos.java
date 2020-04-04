/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package extras;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Administrador
 */
public class formatar_campos {
    
    static MaskFormatter objMascara;

            /*    //http://java.sun.com/j2se/1.4.2/docs/api/javax/swing/text/MaskFormatter.html

            # => Usa número válido, usa Character.isDigit
            ' => Caractere de escape, usado para escape de qualquer caractere de formato especial
            U =>Qualquer caractere(Character.isLetter). Todas as letras minúsculas são transformadas em maiúsculas.
            L => Qualquer caractere(Character.isLetter). Todas as letras maiúsculas são transformadas em minúsculas.
            A => Qualquer caractere ou digito (Character.isLetter ou Character.isDigit)
            ? => Qualquer caractere
            * => Qualquer coisa
            H => Qualquer caractere hexadecima(0-9, a-f ou A-F)

            */
              
    public static void mascara_data(JFormattedTextField campo) {
        try {
            objMascara = new MaskFormatter("##/##/####");
            objMascara.setValidCharacters("1234567890"); // caracteres válidos
            objMascara.setPlaceholderCharacter(' '); // preenchimento            
            objMascara.install(campo);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!", "Formatação", JOptionPane.INFORMATION_MESSAGE);                  
        }
    }
    
    public static void mascara_telefone(JFormattedTextField campo) {
        try {
            objMascara = new MaskFormatter("(##)####-####");
            objMascara.setValidCharacters("1234567890"); // caracteres válidos
            objMascara.setPlaceholderCharacter(' '); // preenchimento            
            objMascara.install(campo);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!", "Formatação", JOptionPane.INFORMATION_MESSAGE);                  
        }
    }
    
     public static void mascara_cep(JFormattedTextField campo) {
        try {
            objMascara = new MaskFormatter("#####-###");
            objMascara.setValidCharacters("1234567890"); // caracteres válidos
            objMascara.setPlaceholderCharacter(' '); // preenchimento            
            objMascara.install(campo);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!", "Formatação", JOptionPane.INFORMATION_MESSAGE);                  
        }
    }

    public static void mascara_numero(JFormattedTextField campo) {
        try {
            objMascara = new MaskFormatter("######");
            objMascara.setValidCharacters("1234567890"); // caracteres válidos
            objMascara.setPlaceholderCharacter(' '); // preenchimento
            objMascara.install(campo);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!", "Formatação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void fixa_tamanho(JFormattedTextField campo) {
        try {
            campo.setDocument(new fixedLengthDocument(6));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro!!!", "Formatação", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
