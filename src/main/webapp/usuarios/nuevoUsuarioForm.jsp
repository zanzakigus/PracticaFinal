<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <%@include file="../shared/redirect.jsp" %>
    <c:choose>
        <c:when test="${categoria==null}">
            <title>Categor&iacute;a Form</title>
            <c:set var = "active"  value="active" />
            <c:set var = "styles"  value="estilos/general.css" />
            <c:set var = "link"  value="UsuarioController?accion=guardar" />
        </c:when>
        <c:otherwise>
            <title>Actualizar categoría Form</title>
            <c:set var = "active"  value="" />
            <c:set var = "styles"  value="estilos/general.css" />
            <c:set var = "link"  value="UsuarioController?accion=guardar" />
        </c:otherwise>
    </c:choose>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="../shared/resources.jsp"%>
    <%@include file="../shared/levelRedirect.jsp" %>
    <link href="<c:out value="${styles}"/>" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp"%>
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <c:choose>
                <c:when test="${user==null}">
                    <h3 class="title">Crear una nueva usuario </h3>
                </c:when>
                <c:otherwise>
                    <h3 class="title">Actualizar usuario</h3>
                </c:otherwise>
            </c:choose>
            <form method="post" action="<c:out value="${link}"/>">
                <c:choose>
                    <c:when test="${user!=null}">
                        <input value="<c:out value="${user.getEntidad().getIdUsuario()}"/>" type="hidden" id="id" name="id">
                    </c:when>
                </c:choose>
                <label for="txtNombre" class="form-label"> Nombre :</label>
                <input value="<c:out value="${user.getEntidad().getNombre()}"/>" class="form-control" type="text" name="txtNombre" id="txtNombre"
                       placeholder="Nombre" required="required"/>
                <label for="txtPaterno" class="form-label"> Apellido paterno :</label>
                <input value="<c:out value="${user.getEntidad().getPaterno()}"/>" class="form-control" type="text" name="txtPaterno" id="txtPaterno"
                       placeholder="Apellido paterno" required="required"/>
                <label for="txtMaterno" class="form-label"> Apellido materno :</label>
                <input value="<c:out value="${user.getEntidad().getMaterno()}"/>" class="form-control" type="text" name="txtMaterno" id="txtMaterno"
                       placeholder="Apellido materno" required="required"/>
                <label for="txtEmail" class="form-label"> Email :</label>
                <input value="<c:out value="${user.getEntidad().getEmail()}"/>" class="form-control" type="text" name="txtEmail" id="txtEmail"
                       placeholder="Email" required="required"/>
                <label for="txtNombreUsuario" class="form-label"> Nombre de usuario :</label>
                <input value="<c:out value="${user.getEntidad().getNombreUsuario()}"/>" class="form-control" type="text" name="txtNombreUsuario" id="txtNombreUsuario"
                       placeholder="Nombre de usuario" required="required"/>
                <label for="txtClaveUsuario" class="form-label"> Password :</label>
                <input value="<c:out value="${user.getEntidad().getClaveUsuario()}"/>" class="form-control" type="password" name="txtClaveUsuario" id="txtClaveUsuario"
                       placeholder="Password" required="required"/>

                <label for="txtIdTipoUsuario" class="form-label"> Tipo de usuario :</label>
                <select class="form-select" aria-label="Default select example" name="txtIdTipoUsuario" id="txtIdTipoUsuario">
                    <option
                            <c:if test="${user==null}">
                                selected
                            </c:if>
                    >Seleccionar tipo de usuario
                    </option>
                        <option value="1"
                                <c:if test="${user!=null && user.getEntidad().getIdTipoUsuario().getIdTipoUsuario() == 1 }">
                                    selected
                                </c:if>
                        >1-admin
                        </option>
                    <option value="2"
                            <c:if test="${user!=null && user.getEntidad().getIdTipoUsuario().getIdTipoUsuario() == 2 }">
                                selected
                            </c:if>
                    >2-lectura
                    </option>
                </select>
                <br/>
                <c:choose>
                    <c:when test="${user==null}">
                        <input class=" form-control btn btn-success" type="submit" value="Guardar" name="cmdEnviar"/>
                    </c:when>
                    <c:otherwise>
                        <input class=" form-control btn btn-success" type="submit" value="Actualizar" name="cmdEnviar"/>
                    </c:otherwise>
                </c:choose>

            </form>
        </div>
    </section>

</div>
</body>
<script defer type="text/javascript">
    navbar_update(7);
</script>
</html>
