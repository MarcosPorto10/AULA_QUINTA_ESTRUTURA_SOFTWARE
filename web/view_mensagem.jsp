<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- A linha abaixo faz a importação do codigo do topo --%>
<c:import url="topo.jsp" />
                    <div class="alert-success text-center espaco">
                        Tela de Mensagens
                    </div>
                    <%-- A variavel abaixo e substituida pelo envio do controller--%>
                    <c:out value="${msg_ra}" /><br>
                    <c:out value="${msg_nome}" /><br>
                    <c:out value="${msg_curso}" />
                    
                    <%--A linha abaixo faz importação do codigo do rodape--%>
                    <c:import url="rodape.jsp" />
