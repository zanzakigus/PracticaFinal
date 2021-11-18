/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador;

import com.ipn.mx.dao.CategoriaDAO;

import com.ipn.mx.dto.CategoriaDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zanzakigus
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if(accion.equals("listadoDeCategorias")){
            listarCategorias(request,response);
        }
        else if(accion.equals("nuevo")){
            agregarCategoria(request,response);
        }
        else if (accion.equals("eliminar") && UsuarioController.validateLevelAdminSession(request,response) ){
            eliminarCategoria(request,response);
        }
        else if (accion.equals("actualizar")){
            actualizarCategoria(request,response);
        }
        else if (accion.equals("ver")){
            mostrarCategoria(request,response);
        }
        else if (accion.equals("guardar") && UsuarioController.validateLevelAdminSession(request,response)){
            almacenarCategoria(request,response);
        }
        else if (accion.equals("graficar")){
            mostrarGrafica(request,response);
        }
        else if (accion.equals("verReporte")){
            MostrarReporte(request,response);
        }
        else if (accion.equals("verReporteOne")){
            MostrarReporteOne(request,response);
        }else{
            UsuarioController.redirect(request,response);
        }
    }


    private void MostrarReporteOne(HttpServletRequest request, HttpServletResponse response) {
/*        CategoriaDAO dao = new CategoriaDAO();
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("id",Integer.valueOf(request.getParameter("id")));
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report =  new File(getServletConfig().getServletContext().getRealPath("/reports/Cherry.jasper"));
            byte[] b = JasperRunManager.runReportToPdf(report.getPath(),param, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b,0, b.length);
            sos.flush();
            sos.close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }*/

    }




    private void MostrarReporte(HttpServletRequest request, HttpServletResponse response) {


/*        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report =  new File(getServletConfig().getServletContext().getRealPath("/reports/report.jasper"));
            byte[] b = JasperRunManager.runReportToPdf(report.getPath(),null, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b,0, b.length);
            sos.flush();
            sos.close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }*/



    }

    private void mostrarGrafica(HttpServletRequest request, HttpServletResponse response) {
        JFreeChart grafica = ChartFactory.createPieChart("Productos por categoria",obtenerDatosGraficaProductosPorCategoria(),true,true, Locale.getDefault());
        String archivo = getServletConfig().getServletContext().getRealPath("/grafica.png");
        try {
            ChartUtils.saveChartAsPNG(new File(archivo),grafica,500,500);
            RequestDispatcher vista = request.getRequestDispatcher("grafica.jsp");
            vista.forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private PieDataset obtenerDatosGraficaProductosPorCategoria(){


        DefaultPieDataset dpd= new DefaultPieDataset();
/*        GraficaDAO dao = new GraficaDAO();

        try {
            List datos = dao.obtenerDatosGrafica();
            for (int i = 0; i < datos.size(); i++) {
                GraficaDTO dto = (GraficaDTO) datos.get(i);
                dpd.setValue(dto.getNombreCategoria(), dto.getCantidad());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        */
        return dpd;

    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        String idString = request.getParameter("id");
        int idCategoria = 0;
        if(idString != null){
            idCategoria = Integer.parseInt(idString) ;
        }

        dto.getEntidad().setNombreCategoria(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcion"));
        String msg = "No se creo";
        if(idString != null){
            dto.getEntidad().setIdCategoria(idCategoria);
            dao.update(dto);
            msg = "Actualizado!! el registro a sido actualizado exitosamente";
        }else{
            msg = "Creado!! el registro a sido creado exitosamente";
            dao.create(dto);


        }
        request.setAttribute("mensaje",msg);
        listarCategorias(request,response);


    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {

        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/categorias/datosCategoria.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            rd.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/categorias/nuevoCategoriaForm.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            rd.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        dao.delete(dto);
        listarCategorias(request,response);

    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/categorias/nuevoCategoriaForm.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            request.setAttribute("listaDeCategorias",dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("/categorias/listaDeCategorias.jsp");
            rd.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
