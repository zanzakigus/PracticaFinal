/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador;


import com.ipn.mx.dao.CategoriaDAO;
import com.ipn.mx.dao.ProductoDAO;
import com.ipn.mx.dto.CategoriaDTO;
import com.ipn.mx.dto.ProductoDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zanzakigus
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if (accion.equals("listadoDeProductos")) {
            listarProducto(request, response);
        } else if (accion.equals("nuevo")) {
            agregarProducto(request, response);
        } else if (accion.equals("eliminar") && UsuarioController.validateLevelAdminSession(request, response)) {
            eliminarProducto(request, response);
        } else if (accion.equals("actualizar")) {
            actualizarProducto(request, response);
        } else if (accion.equals("ver")) {
            mostrarProducto(request, response);
        } else if (accion.equals("guardar") && UsuarioController.validateLevelAdminSession(request, response)) {
            almacenarProducto(request, response);
        } else if (accion.equals("graficar")) {
            mostrarGrafica(request, response);
        } else if (accion.equals("verReporte")) {
            MostrarReporte(request, response);
        } else if (accion.equals("verReporteOne")) {
            MostrarReporteOne(request, response);
        } else {
            UsuarioController.redirect(request, response);
        }
    }

    private void MostrarReporteOne(HttpServletRequest request, HttpServletResponse response) {

/*        ProductoDAO dao = new ProductoDAO();
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("id",Integer.valueOf(request.getParameter("id")));
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report =  new File(getServletConfig().getServletContext().getRealPath("/reports/reportProductosDetalle.jasper"));
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
/*        ProductoDAO dao = new ProductoDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report =  new File(getServletConfig().getServletContext().getRealPath("/reports/reportProductos.jasper"));
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
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        String idString = request.getParameter("id");
        int idProducto = 0;
        if (idString != null) {
            idProducto = Integer.parseInt(idString);
        }
        CategoriaDAO daoC = new CategoriaDAO();
        CategoriaDTO dtoC = new CategoriaDTO();
        dtoC.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtCategoria")));
        dtoC = daoC.read(dtoC);


        dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
        dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
        dto.getEntidad().setPrecioProducto(Double.parseDouble(request.getParameter("txtPrecio")));
        dto.getEntidad().setExistenciaProducto(Integer.parseInt(request.getParameter("txtExistencia")));
        dto.getEntidad().setIdCategoria(dtoC.getEntidad());
        String msg = "No se creo";

        if (idString != null) {
            dto.getEntidad().setIdProducto(idProducto);
            dao.update(dto);
            msg = "Actualizado!! el registro a sido actualizado exitosamente";
        } else {
            msg = "Creado!! el registro a sido creado exitosamente";
            dao.create(dto);


        }
        request.setAttribute("mensaje", msg);
        listarProducto(request, response);


    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/productos/datosProducto.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/productos/nuevoProductoForm.jsp");
        CategoriaDAO daoc = new CategoriaDAO();
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            request.setAttribute("listaDeCategorias", daoc.readAll());
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        dao.delete(dto);
        listarProducto(request, response);


    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher rd = request.getRequestDispatcher("/productos/nuevoProductoForm.jsp");
        CategoriaDAO dao = new CategoriaDAO();
        try {
            request.setAttribute("listaDeCategorias", dao.readAll());
            rd.forward(request, response);
        } catch (ServletException | IOException  e) {
            e.printStackTrace();
        }
    }

    private void listarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        try {
            request.setAttribute("listaDeProductos", dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("/productos/listaDeProducto.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
