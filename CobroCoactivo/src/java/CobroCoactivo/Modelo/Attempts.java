package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Attempts generated by hbm2java
 */
public class Attempts  implements java.io.Serializable {


     private int Id;
     private Usuarios usuarios;
     private int Intentos;
     private Date tppUltimoIntento;

    public Attempts() {
    }

	
    public Attempts(int Id, Usuarios usuarios) {
        this.Id = Id;
        this.usuarios = usuarios;
    }
    public Attempts(int Id, Usuarios usuarios, int Intentos, Date tppUltimoIntento) {
       this.Id = Id;
       this.usuarios = usuarios;
       this.Intentos = Intentos;
       this.tppUltimoIntento = tppUltimoIntento;
    }
   
    public int getTtpId() {
        return this.Id;
    }
    
    public void setTtpId(int Id) {
        this.Id = Id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public int getTtpIntentos() {
        return this.Intentos;
    }
    
    public void setTtpIntentos(int Intentos) {
        this.Intentos = Intentos;
    }
    public Date getTppUltimoIntento() {
        return this.tppUltimoIntento;
    }
    
    public void setTppUltimoIntento(Date tppUltimoIntento) {
        this.tppUltimoIntento = tppUltimoIntento;
    }




}


