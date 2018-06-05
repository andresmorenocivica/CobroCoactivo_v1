<%-- 
    Document   : 500
    Created on : 18/07/2016, 04:03:43 PM
    Author     : Miguel Borja
--%>


<%@page import="com.contravenciones.model.LoginUser"%>
<%@page import="com.contravenciones.singleton.SessionSingleton"%>
<%@page import="com.contravenciones.jsf.bean.BeanLogin"%>
<%@page import="com.contravenciones.singleton.AuthSingleton"%>
<%@page import="com.contravenciones.utility.Log_Handler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%

    /*
    
    MENSAJE DE ERROR DEBE ELIMINARSE PARA PRODUCCIÓN    
    
     */
    request.setCharacterEncoding("UTF-8");
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("X-FRAME-OPTIONS", "DENY");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Server", "La Civica Impresores SAS");
    response.setHeader("X-Powered-By", "La Civica Impresores SAS");
    response.setHeader("Content-Type", "text/html;charset=utf-8");
    int id_usu = 0;
    try {
        BeanLogin bean = (BeanLogin) request.getSession().getAttribute("loginBean");
        if (bean != null) {
            if (bean.getID_Usuario() != null && !bean.getID_Usuario().isEmpty()) {
                AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(bean.getID_Usuario()));
                LoginUser loginObj = SessionSingleton.getInstancia().getUsuarioSesion(Integer.parseInt(bean.getID_Usuario()));
                if (loginObj != null) {
                    loginObj.invalidarSesion();
                }
                id_usu = Integer.parseInt(bean.getID_Usuario());
            }

        }
    } catch (java.lang.IllegalStateException E) {
        Log_Handler.registrarEvento("Error de Sesión: La sesión ya se habia invalidado o no estaba en el estado esperado.", null, Log_Handler.WARN, getClass(), id_usu);
    }
    String texto = "";
    Throwable causa = null;
    String mensaje = "";
    try {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        //System.out.print(statusCode);
        //System.out.print(throwable);
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        mensaje = throwable.getMessage();
        String local = throwable.getLocalizedMessage();
        StackTraceElement stack = throwable.getStackTrace()[0];
        String archivo = stack.getFileName();
        int linea = stack.getLineNumber();
        texto = stack.toString();
        causa = throwable.getCause();
        Log_Handler.registrarEvento("Error fatal en vista: ", throwable, Log_Handler.ERROR, getClass(), id_usu);
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
    } catch (Exception w) {
        w.printStackTrace();
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error en la aplicación</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="background-color:  #efefef;">
        <div style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;margin: 0px;">
            <br><br><br><br>
            <table border="0px" style="margin: 0 auto">
                <tr>
                    <td valign="middle">
                        <img style="width: 80px;" src="/Civitrans/resources/images/error.PNG"/>
                    </td>
                    <td valign="middle">
                        <h3 style="margin: 2px;"><span style="color: darkred">Error Interno de la Aplicación</span></h2>
                            <h4 style="margin: 0px;"><%=mensaje%> <br><span style="color: crimson">Causa: </span> <%=causa%> </h3>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <button  onclick="window.history.back();">Regresar</button>
                    </td>
                </tr><tr>
                    <td colspan="2">
                        <h6 style="text-align: center">La Cívica Impresores S.A.S</h6>
                    </td>
                </tr>
            </table>
        </div>
    </body>

</html>