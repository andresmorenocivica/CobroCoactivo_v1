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
import java.util.ArrayList;
import java.util.List;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Persistencia.CivEstadoUsuarios;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author emadrid
 */
public class GestionUsuariosImplBO implements GestionUsuariosBO, Serializable {

    private ITUsuarios usuariosDAO;
    private ITPersonas personasDAO;

    public GestionUsuariosImplBO() {
        usuariosDAO = new DaoUsuarios();
        personasDAO = new DaoPersonas();

    }

    @Override
    public void consultarUsuario(BeanGestionUsuarios bean) throws Exception {
        //List<CivUsuarios> listarCivUsuario = new ArrayList<CivUsuarios>();
        List<CivUsuarios> listaCivUsuario = new ArrayList<>();
        switch (bean.getTipoBusqueda()) {
            case 1:
                listaCivUsuario = getUsuariosDAO().listarUsuarios(bean.getNombreUsuario().toUpperCase());
                break;
        }
        if (listaCivUsuario != null && listaCivUsuario.size() > 0) {
            for (CivUsuarios civUsuarios : listaCivUsuario) {
                Usuarios usuarios = new Usuarios(civUsuarios, civUsuarios.getCivEstadoUsuarios(), civUsuarios.getCivPersonas());
                bean.getListadoUsuarios().add(usuarios);
            }
        }
    }

    @Override
    public void actualizarContraseña(BeanGestionUsuarios bean) throws Exception {
        CivUsuarios civUsuarios = getUsuariosDAO().find(new BigDecimal(bean.getDetalleUsuario().getId()));
        civUsuarios.setUsuPass(bean.getContraseñaConfirmacion());
        getUsuariosDAO().update(civUsuarios);
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
