/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Utility;

import CobroCoactivo.Persistencia.CivDeudasImpuesto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public class Utility {
     public java.util.List obtenerCampos(String text) {
        java.util.List lista = new java.util.LinkedList();
        int start = 0;
        int end = 0;
        while (true) {
            end = text.indexOf(",", start);
            if (end != -1) {
                lista.add(text.substring(start, end));
                start = end + 1;
            } else {
                lista.add(text.substring(start, text.length()));
                break;
            }

        }
        return lista;
    }
     
   
}
