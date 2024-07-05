<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listkegiatanpopup.js"></script>    
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pilih KBUD</div>
    </div>
    <div class="portlet-body">
        <input type="hidden" id="kodewilayah"  name="kodewilayah" value="${sessionScope.pengguna.kodeProses}"  /> 
                    

    <div class="portlet box">
        <div >
            <table id="kbudtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>NIP KBUD</th>
                        <th>NRK KBUD</th>
                        <th>Nama KBUD</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div>    
    </div>
              
    <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/restitusi/pilihkbud.js"></script>  