/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Exception;

/**
 * Clase para definir los errores del módulo de Liquidación
 *
 * @author Roymer Camacho
 */
public class LoginException extends Exception {

    /**
     * Constuctor
     */
    public LoginException() {
    }

    /**
     * Constructor donde se establece el mensaje de error
     *
     * @param message Mensaje de error
     */
    public LoginException(String message) {
        super(message);
    }

}
