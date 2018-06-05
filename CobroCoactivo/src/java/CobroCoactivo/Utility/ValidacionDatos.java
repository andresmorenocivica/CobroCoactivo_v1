/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Roymer Camacho
 */
public class ValidacionDatos implements  Validator {

    private static final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT = "AaEeIiOoUuNnUu";

    /**
     *
     * @param str
     * @return
     */
    public static String quitarTildes(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    /**
     *
     * @param cadena
     * @return
     */
    public boolean isNumeric(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     *
     * @param pattern
     * @param campo
     * @return
     */
    public boolean validarFormatoDeCampos(String pattern, String campo) {
        boolean sw;
//        Pattern pat = Pattern.compile(".*1(?!2).*");
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(campo);
        sw = mat.matches(); //System.out.println("SI");
        //System.out.println("NO");
        return sw;
    }

    /**
     *
     * @param campo
     * @return
     */
    public boolean validarSolonumeros(String campo) {
        boolean sw;
//        Pattern pat = Pattern.compile("[0-9]*");
//        Matcher mat = pat.matcher("123456");
        sw = campo.matches("[0-9]*"); //System.out.println("SI");
        //System.out.println("NO");
        return sw;
    }
    
     public boolean validarSololetras(String campo) {
        boolean sw;
//        Pattern pat = Pattern.compile("[0-9]*");
//        Matcher mat = pat.matcher("123456");
        sw = campo.matches("[a-zA-Z]*"); //System.out.println("SI");
        //System.out.println("NO");
        return sw;
    }

    /**
     * valida la seguridad del nombre del usuario - Se debe empezar y terminar
     * con un dígito o carácter. - Debe ser exactamente 4 a 10 caracteres de
     * largo. - permiten caracteres especiales son _.-
     *
     * @param campo
     * @return
     */
    public boolean validarNombreUsuario(String campo) {
        boolean sw;
        sw = campo.matches("^[a-zA-Z0-9][a-zA-Z0-9-_.]{2,8}[a-zA-Z0-9]$");
        return sw;
    }

    /*Función para convertir la primera letra en mayúscula de una cadena*/
    public String letraMayuscula(String cadena) {
        String nombre = "";
        for (String p : cadena.split(" ")) {
            nombre += p.substring(0, 1).toUpperCase() + p.substring(1, p.length()).toLowerCase() + " ";
        }
        nombre = nombre.trim();
        return nombre;
    }

    public Map ordenarMap(Map map) {
        Map mapResultado = new LinkedHashMap();
        List misMapKeys = new ArrayList(map.keySet());
        List misMapValues = new ArrayList(map.values());
        TreeSet conjuntoOrdenado = new TreeSet(misMapValues);
        Object[] arrayOrdenado = conjuntoOrdenado.toArray();
        int size = arrayOrdenado.length;
        for (int i = 0; i < size; i++) {
            mapResultado.put(misMapKeys.get(
                    misMapValues.indexOf(
                            arrayOrdenado[i])
            ), arrayOrdenado[i]);
        }

        Iterator it1 = mapResultado.values().iterator();
        while (it1.hasNext()) {
            System.out.println((String) it1.next());
        }
        return mapResultado;
    }
    

    public String validarFechasReportes(Date campo1, Date campo2) throws Exception {
        if (campo1 == null || campo2 == null) {
            return "Campos vacíos";
        }
        if (campo1.after(campo2)) {
            return "La fecha final debe ser mayor a la fecha inicial";

        }
        if (campo1.after(new Date())) {
            return "La fecha inicial no puede ser mayor que la fecha actual";
        }
        
        if (campo2.after(new Date())) {
            return "La fecha final no puede ser mayor que la fecha actual";
        }
        return "true";
    }

    public String validarCampoAño(String campo) throws Exception {
        if (campo.equals("")) {
            return "Campos vacíos";
        }

        if (!validarSolonumeros(campo)) {
            return "Campo inválido. Ejemplo campo válido: 2018";
        }

        if (campo.length() != 4) {
            return "Año inválido";
        }
        return "true";
    }

    public static boolean validarVacios(String campo) {
        if (!campo.equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
