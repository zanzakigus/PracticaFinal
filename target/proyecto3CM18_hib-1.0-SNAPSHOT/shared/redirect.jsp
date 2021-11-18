<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    <c:choose>
    <c:when test="${!(sessionScope.usuario!=null)}">
        //window.location.href="/UsuarioController?accion=login";
        window.location.replace("/UsuarioController?accion=login");
        //window.location.replace("/proyecto3CM18_jstl/UsuarioController?accion=login");
    </c:when>
    </c:choose>

</script>