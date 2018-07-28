/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Utility.Log_Handler;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author jvergara
 */
@ManagedBean(name = "visorPdfBean")
@ApplicationScoped
public class BeanVisorPdf {

    private String ruta;

    @PostConstruct
    public void init() {

    }

    /**
     * Creates a new instance of BeanVisorPdf
     */
    public BeanVisorPdf() {
    }

    public StreamedContent getPdfDocument() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params
                    = fc.getExternalContext().getRequestParameterMap();
            String data1 = params.get("para1").trim();

            System.out.println(data1);

            String data = "D:\\Archivo\\apuesta.pdf";
            if (data.equals(data1)) {
                System.out.println("true");
            }
            File pdf = new File(data);
            FileInputStream fis = new FileInputStream(pdf);

            return new DefaultStreamedContent(fis, "application/pdf",data,"test.pdf");

        } catch (Exception e) {
            return null;
        }

    }

   

    public void establecerRuta(String ruta) {

        this.ruta = ruta;
    }

    public String getIdFile() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
