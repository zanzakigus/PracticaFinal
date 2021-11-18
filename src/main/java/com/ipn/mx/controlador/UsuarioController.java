/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador;

import com.ipn.mx.dao.TipoUsuarioDAO;
import com.ipn.mx.dao.UsuarioDAO;
import com.ipn.mx.dto.TipoUsuarioDTO;
import com.ipn.mx.dto.UsuarioDTO;
import com.ipn.mx.entidades.TipoUsuario;
import com.ipn.mx.utilerias.EnviarMail;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 * @author zanzakigus
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

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
        if (Objects.equals(accion, "login")) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else if (Objects.equals(accion, "index")) {
            redirect(request, response);
        } else if (Objects.equals(accion, "requestLogin")) {
            loginSession(request, response);
        } else if (accion.equals("logOut")) {
            logoutSession(request, response);
        } else if (accion.equals("listadoDeUsuarios")) {
            listarUsuario(request, response);
        } else if (accion.equals("nuevo")) {
            agregarUsuario(request, response);
        } else if (accion.equals("eliminar") && UsuarioController.validateLevelAdminSession(request, response)) {
            eliminarUsuario(request, response);
        } else if (accion.equals("actualizar")) {
            actualizarUsuario(request, response);
        } else if (accion.equals("ver")) {
            mostrarUsuario(request, response);
        } else if (accion.equals("guardar") && UsuarioController.validateLevelAdminSession(request, response)) {
            almacenarUsuario(request, response);
        } else if (accion.equals("graficar")) {
            mostrarGrafica(request, response);
        } else if (accion.equals("verReporte")) {
            MostrarReporte(request, response);
        } else if (accion.equals("verReporteOne")) {
            MostrarReporteOne(request, response);
        } else if (accion.equals("crearEmail")) {
            crearEmail(request, response);
        } else if (accion.equals("enviarEmail")) {
            enviarEmail(request, response);
        } else {
            UsuarioController.redirect(request, response);
        }

        /*        try (PrintWriter out = response.getWriter()) {
         *//* TODO output your page here. You may use following sample code. *//*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

    private void enviarEmail(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher rd = request.getRequestDispatcher("email/envioEmail.jsp");
        String destinatario = request.getParameter("txtDestinatario");
        String asunto = request.getParameter("txtAsunto");
        String mensaje = request.getParameter("txtMensaje");
        EnviarMail em = new EnviarMail();
        boolean tipoR = em.enviarCorreo(destinatario, asunto, mensaje);
        String resp = "Error al enviar mensaje intentar mas tarde";
        if (tipoR) {
            resp = "Correo enviado exitosamente";
        }
        UsuarioDAO dao = new UsuarioDAO();
        try {
            request.setAttribute("tipoR", tipoR);
            request.setAttribute("msj", resp);
            request.setAttribute("listaDeUsuarios", dao.readAll());
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void crearEmail(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("email/envioEmail.jsp");
        UsuarioDAO dao = new UsuarioDAO();
        try {
            request.setAttribute("listaDeUsuarios", dao.readAll());
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void MostrarReporteOne(HttpServletRequest request, HttpServletResponse response) {
        /*UsuarioDAO dao = new UsuarioDAO();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", Integer.valueOf(request.getParameter("id")));
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report = new File(getServletConfig().getServletContext().getRealPath("/reports/reportOneUser.jasper"));
            byte[] b = JasperRunManager.runReportToPdf(report.getPath(), param, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b, 0, b.length);
            sos.flush();
            sos.close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }*/
    }

    private void MostrarReporte(HttpServletRequest request, HttpServletResponse response) {

 /*       UsuarioDAO dao = new UsuarioDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File report = new File(getServletConfig().getServletContext().getRealPath("/reports/reportUsers.jasper"));
            byte[] b = JasperRunManager.runReportToPdf(report.getPath(), null, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(b.length);
            sos.write(b, 0, b.length);
            sos.flush();
            sos.close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }*/
    }

    private void mostrarGrafica(HttpServletRequest request, HttpServletResponse response) {
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        String idString = request.getParameter("id");
        int idUsuario = 0;
        if (idString != null) {
            idUsuario = Integer.parseInt(idString);
        }
        TipoUsuarioDAO daoT = new TipoUsuarioDAO();
        TipoUsuarioDTO dtoT = new TipoUsuarioDTO();
        dtoT.getEntidad().setIdTipoUsuario(Integer.parseInt(request.getParameter("txtIdTipoUsuario")));
        daoT.read(dtoT);

        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));
        dto.getEntidad().setIdTipoUsuario(dtoT.getEntidad());
        String msg = "No se creo";

        if (idString != null) {
            dto.getEntidad().setIdUsuario(idUsuario);
            dao.update(dto);
            msg = "Actualizado!! el registro a sido actualizado exitosamente";
        } else {
            msg = "Creado!! el registro a sido creado exitosamente";
            dao.create(dto);

        }
        request.setAttribute("mensaje", msg);
        listarUsuario(request, response);

    }

    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios/datosUsuario.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("user", dto);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios/nuevoUsuarioForm.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("user", dto);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        dao.delete(dto);
        listarUsuario(request, response);

    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/usuarios/nuevoUsuarioForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listarUsuario(HttpServletRequest request, HttpServletResponse response) {

        UsuarioDAO dao = new UsuarioDAO();
        try {
            request.setAttribute("listaDeUsuarios", dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listaDeUsuarios.jsp");
            rd.forward(request, response);
        } catch ( ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void logoutSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.removeAttribute("tipo");
        /*  Se tiene que validar que efectivamente se haya removido el atributo ya que de no
            ser es necesario invaliar la sesion
         */
        if (session.getAttribute("usuario") != null || session.getAttribute("tipo") != null) {
            session.invalidate();
        }
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("login.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loginSession(HttpServletRequest request, HttpServletResponse response) {
        //  Se obtiene o en caso de que el request no tenga una sesión, crea una.
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setNombreUsuario(request.getParameter("txtUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtPassword"));
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO user;
        RequestDispatcher rd;
        try {
            if ((user = dao.login(dto)) != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", user.getEntidad().getNombreUsuario());
                session.setAttribute("tipo", user.getEntidad().getIdTipoUsuario().getTipoUsuario());
                rd = request.getRequestDispatcher("index.jsp");
            } else {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                rd = request.getRequestDispatcher("login.jsp");
                request.setAttribute("error", "Las credenciales no son validas, favor de verificar");
            }
            rd.forward(request, response);
        } catch ( ServletException | IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Metodo para saber si la sesion del request es valida.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return boolean validacion de la sesion
     */
    public static boolean validateSession(HttpServletRequest request, HttpServletResponse response) {
        // se obtiene la sesion actual, evitando crear una nueva
        HttpSession session = request.getSession(false);

        //   Existen dos casos en que la sesion no es valida, uno es que sea nula y la otra es que no exista el atributo nombre en ella

        if (session == null) {
            return false;
        } else {
            return session.getAttribute("usuario") != null;
        }
    }

    public static boolean validateLevelAdminSession(HttpServletRequest request, HttpServletResponse response) {
        // se obtiene la sesion actual, evitando crear una nueva
        HttpSession session = request.getSession(false);

        //   Existen dos casos en que la sesion no es valida, uno es que sea nula y la otra es que no exista el atributo nombre en ella

        if (session == null) {
            return false;
        } else {
            return (session.getAttribute("tipo") != null && (session.getAttribute("tipo")).toString().equals("admin"));
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
