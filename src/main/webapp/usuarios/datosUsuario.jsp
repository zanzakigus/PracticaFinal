<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: zanzakigus
  Date: 08/10/21
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@include file="../shared/redirect.jsp" %>
    <%@include file="../shared/levelRedirect.jsp" %>
    <title>Ver Categoria</title>
    <%@include file="../shared/resources.jsp" %>
    <link href="estilos/general.css" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp" %>

    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div form-mediano">
            <h3 class="title">Detalle de usuario</h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td> ID:</td>
                    <td><c:out value="${user.getEntidad().getIdUsuario()}"/></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><c:out value="${user.getEntidad().getNombre()}"/></td>
                </tr>
                <tr>
                    <td> Apellido paterno:</td>
                    <td><c:out value="${user.getEntidad().getPaterno()}"/></td>
                </tr>
                <tr>
                    <td>Apellido materno:</td>
                    <td><c:out value="${user.getEntidad().getMaterno()}"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><c:out value="${user.getEntidad().getEmail()}"/></td>
                </tr>
                <tr>
                    <td>Nombre de usuario:</td>
                    <td><c:out value="${user.getEntidad().getNombreUsuario()}"/></td>
                </tr>
                <tr>
                    <td>Tipo de usuario:</td>
                    <td><c:out value="${user.getEntidad().getIdTipoUsuario().getTipoUsuario()}"/></td>
                </tr>

                </tbody>
            </table>
        </div>
    </section>
    <a class="link-success" href="UsuarioController?accion=listadoDeUsuarios">
        <div class="alert alert-success" role="alert">
            Dar click para regresar a Lista de Usuarios
        </div>
    </a><a>
</a></div>
<a>


</a></body>
</html>
