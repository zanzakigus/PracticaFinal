<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<c:choose>
    <c:when test="${mensaje==null}">
        <c:set var="banderaMensaje" value="${null}"/>
    </c:when>
    <c:otherwise>
        <c:set var="banderaMensaje" value="${true}"/>
        <c:choose>
            <c:when test="${mensaje=='no'}">
                <c:set var="tipo" value="danger"/>
                <c:set var="textoMensaje" value="Hubo un error al realizar la operación :c"/>
            </c:when>
            <c:otherwise>
                <c:set var="tipo" value="success"/>
                <c:set var="textoMensaje" value="${mensaje}"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../shared/redirect.jsp" %>
    <%@include file="../shared/levelRedirect.jsp" %>
    <title>Mostrar Usuarios</title>
    <%@include file="../shared/resources.jsp"%>
    <link href="estilos/listado.css" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp"%>
    <div class="d-flex justify-content-around">

        <div class="d-flex p-2 bd-highlight"><a href="UsuarioController?accion=nuevo" type="button"
                                                class="btn btn-dark">Crear nuevo
            registro</a></div>
<%--        <div class="d-flex p-2 bd-highlight"><a href="CategoriaController?accion=graficar" type="button"
                                                class="btn btn-secondary">Graficar</a></div>--%>
        <div class="d-flex p-2 bd-highlight"><a href="UsuarioController?accion=verReporte" type="button"
                                                class="btn btn-dark">Generar pdf</a></div>
    </div>

    <c:choose>
        <c:when test="${banderaMensaje}">
            <div style="margin-top: 1rem" class="alert alert-<c:out value="${tipo}"/> alert-dismissible fade show"
                 role="alert">
                <c:out value="${textoMensaje}"/>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:when>
    </c:choose>

    <section class=" section d-flex align-items-center">

        <div class="mb-3 form-div">
            <h3 class="title">Listado de usuarios</h3>
            <div class="overflow-y-table">
                <table class="table table-striped" style="max-height: 50px; overflow-y: auto">

                    <thead>
                    <tr>
                        <td> Clave</td>
                        <td> Nombre</td>
                        <td> Paterno</td>
                        <td> Materno</td>
                        <td> Email</td>
                        <td> Nombre de usuario</td>
                        <td> Tipo Usuario</td>
                        <td> Eliminar</td>
                        <td> Actualizar</td>
                        <td> Reporte</td>
                    </tr>

                    </thead>
                    <tbody>

                    <c:choose>
                        <c:when test="${listaDeUsuarios.size()>0}">
                            <c:forEach var="dto" items="${listaDeUsuarios}">

                                <tr>
                                    <td>
                                        <a href="UsuarioController?accion=ver&id=<c:out value="${ dto.getEntidad().getIdUsuario()}"/>"
                                           type="button" class="btn btn-warning"><c:out
                                                value="${dto.getEntidad().getIdUsuario()}"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getNombre()}"/>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getPaterno()}"/>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getMaterno()}"/>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getEmail()}"/>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getNombreUsuario()}"/>
                                    </td>
                                    <td><c:out value="${dto.getEntidad().getIdTipoUsuario().getTipoUsuario()}"/>
                                    </td>
                                    <td class="text-center"><a
                                            href="UsuarioController?accion=eliminar&id=<c:out value="${dto.getEntidad().getIdUsuario()}"/>"
                                            type="button" class="btn btn-danger">Eliminar</a>
                                    </td>
                                    <td class="text-center"><a
                                            href="UsuarioController?accion=actualizar&id=<c:out value="${dto.getEntidad().getIdUsuario()}"/>"
                                            type="button" class="btn btn-info">Actualizar</a></td>
                                    <td class="text-center"><a
                                            href="UsuarioController?accion=verReporteOne&id=<c:out value="${dto.getEntidad().getIdUsuario()}"/>"
                                            type="button" class="btn btn-secondary">Reporte</a></td>
                                </tr>

                            </c:forEach>

                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class='text-center' colspan='10'>No hay registros</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>

        </div>
    </section>
</div>


</body>
<script defer type="text/javascript">
    navbar_update(6);
</script>
</html>


