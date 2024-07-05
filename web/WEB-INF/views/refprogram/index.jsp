<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3 class="page-title">
            Daftar PROGRAM
            <small>Data Referensi PROGRAM</small>
        </h3>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <!--th>id</th>
                    <th>ID Urusan</th-->
                    <th>Kode Urusan</th>
                    <th>Kode Program</th>
                    <th>Nama Program</th>
                </tr>
            </thead>
            <tbody>
                 
                <c:forEach varStatus="idx" var="prog"    items="${listprogramjsp}"  >
                    <tr>
                        <td align="right"> ${idx.index+1} </td>
                        <!--td align="right">${prog.idRefProgram}</td>
                        <td align="center">${prog.kodeUrusan}</td-->
                        <td align="center">${fn:substring(prog.kodeProgram,0, 4)}</td>
                        <td align="center">${fn:substring(prog.kodeProgram,5, 7)}</td>
                        <td>${prog.namaProgram}</td>
                         
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
