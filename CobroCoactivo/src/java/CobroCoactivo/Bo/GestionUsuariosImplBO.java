/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionUsuarios;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Modelo.Personas;
import java.io.Serializable;

/**
 *
 * @author emadrid
 */
public class GestionUsuariosImplBO implements GestionUsuariosBO, Serializable {

    private ITUsuarios usuariosDAO;
    private ITPersonas personasDAO;
    
    public GestionUsuariosImplBO(){
        usuariosDAO = new DaoUsuarios();
        personasDAO = new DaoPersonas();
    
    }

    @Override
    public void consultarUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //List<CivUsuarios> listarCivUsuario = new ArrayList<CivUsuarios>();
        List<CivUsuarios> listaCivUsuario = new ArrayList<>();
        switch (bean.getTipoBusqueda()) {
            case 1:
                listaCivUsuario = getUsuariosDAO().listarUsuarios(session, bean.getNombreUsuario().toUpperCase());
                break;
        }
        if (listaCivUsuario != null && listaCivUsuario.size() > 0) {
            for (CivUsuarios civUsuarios : listaCivUsuario) {
                Usuarios usuarios = new Usuarios();
                usuarios.setUsuId(civUsuarios.getUsuId());
                usuarios.setUsuNombre(civUsuarios.getUsuNombre());
                usuarios.setUsuPass(civUsuarios.getUsuPass());
                
                CivPersonas personaUsuarios = getPersonasDAO().consultarPersonasById(session, civUsuarios.getCivPersonas().getPerId().intValue());
                Personas personas = new Personas();
                personas.setId(personaUsuarios.getPerId().intValue());
                personas.setNombre1(personaUsuarios.getPerNombre1());
                personas.setNombre2(personaUsuarios.getPerNombre2());
                personas.setApellido1(personaUsuarios.getPerApellido1());
                personas.setApellido2(personaUsuarios.getPerApellido2());
                personas.setDocumento(personaUsuarios.getPerDocumento());
                bean.getListadoUsuarios().add(usuarios);
            }
        }
        session.close();
    }

    /**
     * @return the usuariosDAO
     */
    public ITUsuarios getUsuariosDAO() {
        return usuariosDAO;
    }

    /**
     * @param usuariosDAO the usuariosDAO to set
     */
    public void setUsuariosDAO(ITUsuarios usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    /**
     * @return the personasDAO
     */
    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    /**
     * @param personasDAO the personasDAO to set
     */
    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

}
