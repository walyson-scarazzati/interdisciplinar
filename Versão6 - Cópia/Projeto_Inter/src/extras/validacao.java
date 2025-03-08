/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

//import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class validacao {

    static JTextField jt = null;
    static JPasswordField jpf = null;
    static JRadioButton jr = null;
    static JPanel jp = null;
    static JMoneyField jm = null;
    static JComboBox jcb = null;
    // static JDateChooser jdc = null;

    public static boolean validar_campos(JFrame form) {
        Component[] componentes
                = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                if (jt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, jt.getToolTipText(), "Validação", JOptionPane.ERROR_MESSAGE);
                    jt.requestFocus();
                    return false;
                }
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                if (jpf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, jp.getToolTipText(), "Validação", JOptionPane.ERROR_MESSAGE);
                    jpf.requestFocus();
                    return false;
                }
            }

        }

        return true;
    }

    public static boolean validar_campos(JInternalFrame form) {
        Component[] componentes = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                if (jt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, jt.getToolTipText(), "Validação", JOptionPane.ERROR_MESSAGE);
                    jt.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, new java.awt.Color(0, 102, 102)));
                    jt.requestFocus();
                    return false;
                }
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                if (jpf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, jp.getToolTipText(), "Validação", JOptionPane.ERROR_MESSAGE);
                    jpf.requestFocus();
                    return false;
                }
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                if (jcb.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, jp.getToolTipText(), "Validação", JOptionPane.ERROR_MESSAGE);
                    jpf.requestFocus();
                    return false;
                }

            }

        }

        return true;
    }

    public static void limpar_campos(JFrame form) {
        Component[] componentes
                = form.getContentPane().getComponents();
        for (Component c : componentes) {

            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setText("");
                jt.setBorder(null);
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setText("");
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setSelected(false);
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setSelectedIndex(0);
            }
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }
    }

    public static void limpar_campos(JInternalFrame form) {
        Component[] componentes = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setText("");
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setText("");
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setSelected(false);
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setSelectedIndex(0);
            }
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }
    }

    public static void habilitar_campos(JFrame form) {
        Component[] componentes
                = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setEditable(true);
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setEditable(true);
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setEnabled(true);
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setEnabled(true);
            }

            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }
    }

    public static void habilitar_campos(JInternalFrame form) {
        Component[] componentes = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setEditable(true);
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setEditable(true);
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setEnabled(true);
            }
            if (c instanceof JMoneyField) {
                jm = (JMoneyField) c;
                jm.setEditable(true);
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setEnabled(true);
            }
//    if (c instanceof JDateChooser) {
//       jdc = (JDateChooser) c;
//       jdc.setEnabled(true);
//       }
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }
    }

    public static void desabilitar_campos(JFrame form) {
        Component[] componentes = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setEditable(false);
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setEditable(false);
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setEnabled(false);
            }
            if (c instanceof JMoneyField) {
                jm = (JMoneyField) c;
                jm.setEditable(false);
            }
            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setEnabled(false);
            }
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }

    }

    public static void desabilitar_campos(JInternalFrame form) {
        Component[] componentes = form.getContentPane().getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                jt = (JTextField) c;
                jt.setEditable(false);
            }
            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setEditable(false);
            }
            if (c instanceof JRadioButton) {
                jr = (JRadioButton) c;
                jr.setEnabled(false);
            }
//   if (c instanceof JDateChooser) {
//       jdc = (JDateChooser) c;
//       jdc.setEnabled(false);
//       }
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                Component[] painel = jp.getComponents();
                for (Component p : componentes) {

                }
            }
        }

    }

}
