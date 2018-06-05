<%-- 
    Document   : CerrarSesion
    Created on : 3/08/2016, 09:22:56 AM
    Author     : Roymer Camacho
--%>


<%@page import="CobroCoactivo.ModeloSistema.LoginUser"%>
<%@page import="CobroCoactivo.Utility.Log_Handler"%>
<%@page import="CobroCoactivo.Singleton.SessionSingleton"%>
<%@page import="CobroCoactivo.Singleton.AuthSingleton"%>
<%@page import="CobroCoactivo.Beans.BeanLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrando Sesión</title>
    </head>
    <body>
        Cerrando Sesión...
    </body>
</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("X-FRAME-OPTIONS", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Server", "La Civica Impresores SAS");
    response.setHeader("X-Powered-By", "La Civica Impresores SAS");
    int id_usu = 0;
    try {
        BeanLogin bean = (BeanLogin) request.getSession().getAttribute("loginBean");
        if (bean != null) {
            if (bean.getID_Usuario() != null) {
                AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(bean.getID_Usuario()));
                LoginUser loginObj = SessionSingleton.getInstancia().getUsuarioSesion(Integer.parseInt(bean.getID_Usuario()));
                if (loginObj != null) {
                    SessionSingleton.getInstancia().invalidarSesion(loginObj.getId_usuario());
                }
            }
            id_usu = Integer.parseInt(bean.getID_Usuario());
        }
        request.getSession().invalidate();
    } catch (Exception e) {
        Log_Handler.registrarEvento("Error cerrando sesion: ", e, Log_Handler.ERROR, getClass(),id_usu);
    } finally {
        response.sendRedirect("/CobroCoactivo");
    }
%>
