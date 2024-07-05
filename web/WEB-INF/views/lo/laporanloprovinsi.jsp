<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Laporan Operasional</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Operasional</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input path="idskpdpop" id="idskpdpop" type="hidden" value="${skpdpop}" onchange="getIdInduk(),setComboBulan()"/>
                    <input path="namaskpdpop" id="namaskpdpop" type="hidden" value="${namaskpdpop}" />
                    <input path="kodeskpdpop" id="kodeskpdpop" type="hidden" value="${kodeskpdpop}" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>  
            
           <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan" onchange="">
                            
                        </select>
                    </div>
                </div>  
            </div> 
                
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenislap" id="jenislap" onchange="">
                            
                        </select>
                    </div>
                </div>  
            </div> 
           
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak</button>
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/lo/laporanloprovinsi.js"></script>  

