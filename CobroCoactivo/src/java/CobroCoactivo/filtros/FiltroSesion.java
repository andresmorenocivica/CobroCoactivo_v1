/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.filtros;

import CobroCoactivo.Beans.BeanLogin;
import CobroCoactivo.ModeloSistema.LoginUser;
import CobroCoactivo.Singleton.SessionSingleton;
import CobroCoactivo.Utility.Log_Handler;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Este filtro maneja la ruta /paginas/ donde se hospedan todos las vistas del
 * sistema. Se encarga de prevenir que dos sesiones con el mismo usuario se
 * ejecuten desde distintas terminales. También evita el acceso a las rutas del
 * sistema sin las credenciales validadas.
 *
 * @author Miguel Borja
 */
public class FiltroSesion implements Filter {

    /**
     * Tiempo entre sesiones con diferentes IP.
     */
    //private static final int SESION_VENCE = 15;
    /**
     *
     */
    public FiltroSesion() {

    }

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //Se establcen las abezeras de mensaje
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("X-FRAME-OPTIONS", "DENY");
        resp.setHeader("X-XSS-Protection", "1; mode=block");
        resp.setHeader("X-Content-Type-Options", "nosniff");
        resp.setHeader("Server", "La Civica Impresores SAS");
        resp.setHeader("X-Powered-By", "La Civica Impresores SAS");
        HttpSession session = req.getSession();
        String uri_ = req.getRequestURI(); //URI de la solicitud

        String ipAddress = req.getRemoteAddr();
        if(ipAddress == null){
            session.invalidate();
            resp.setStatus(302);
            req.getRequestDispatcher("/faces/SesionExpirada.jsp").forward(request, response); //Se redirige a la pagina de Inicio
            chain.doFilter(request, response);
            return;
        }

        BeanLogin userInSession = (BeanLogin) session.getAttribute("loginBean"); //Usuario en Sesión PROBAR
        if (userInSession == null
                || userInSession.getID_Usuario() == null
                || userInSession.getID_Usuario().isEmpty()
                || !userInSession.isHasAccess()) { //Se valida el bojeto de sesión
            Log_Handler.registrarEvento("Sesión no válida. IP: " + ipAddress, null, Log_Handler.WARN, getClass(), 0);
            session.invalidate();
            resp.setStatus(302);
            req.getRequestDispatcher("/faces/SesionExpirada.jsp").forward(request, response); //Se redirige a la pagina de Inicio
            chain.doFilter(request, response);
            return;
        }
//        String usuario = userInSession.getUser();
        int id_usuario = Integer.parseInt(userInSession.getID_Usuario()); //ID del usuario en Sesión

        LoginUser _obj = SessionSingleton.getInstancia().getUsuarioSesion(id_usuario);

        if (_obj == null || _obj.getSession() == null) { //En este punto ya está el ID de usuario en sesión
            _obj = new LoginUser();
            _obj.setSession(session);
            SessionSingleton.getInstancia().registrarSesionUsuario(id_usuario, _obj);
        }

        if (_obj.getIP() == null
                || _obj.getIP().isEmpty()) {
            _obj.setIP(ipAddress);
            _obj.setUltimo_acceso(new Date());
        } else if (!_obj.getSession().getId().equals(session.getId())) {
            Log_Handler.registrarEvento("Sesión ya encontrada. IP: " + _obj.getIP(), null, Log_Handler.WARN, getClass(), id_usuario);
            session.setAttribute("recurrente", _obj);
            session.setAttribute("uri_destino", uri_);
            session.setAttribute("ip_host", ipAddress);
            req.getRequestDispatcher("/faces/SesionConcurrente.jsp").forward(request, response);
            return;
        } else {
            _obj.setUltimo_acceso(new Date());
        }
        chain.doFilter(request, response);
    }

    /**
     *
     */
    @Override
    public void destroy() {

    }

}
