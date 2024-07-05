<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD </a> 
        <span class="icon-angle-right"></span>
    </li>     
    <li><a href="#">Pembatalan SPD</a></li>
</ul>
        
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pembatalan SPD</div>
        
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input type="hidden" id="idskpd" onchange="gridspdbtlvalidasilist()" name="idskpd" value="${idskpd}"  /> 
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" />
                    <a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>
                
        </form>
    </div>
</div>
<div class="portlet box">
    
    <div>
        <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>No SPD</th>
                    <th>Jenis</th>
                    <th>Tgl SPD</th>
                    <th>Nilai SPD</th>
                    <th>No Dokumen SPD</th>
                    <th>Tgl SPD Sah</th>
                    <th>Pembatalan</th> 
                </tr>
            </thead>
            <tbody id="btlspdtablebody" >
            </tbody>
        </table>
    </div>
</div>                    
<div id="tempatiframe">  </div>         

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/spdpembatalan.js"></script>  
