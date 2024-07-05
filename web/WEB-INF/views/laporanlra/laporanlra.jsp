<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Laporan Keuangan SKPD</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Keuangan SKPD</div>       
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
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="pilihSkpd" id="pilihSkpd" onchange="getIdIndukOnChange(),setComboBulan()" >  
                          <!--  <form:options items="${listSkpd}"  itemValue="kodeSkpd" itemLabel = "ketSkpd"  /> -->
                            </form:select >
                        <form:errors path="pilihSkpd" cssClass="error"  />
                    </div>
                </div>
            </div>
                    
           <div class="form-group">
                <label class="col-md-3 control-label">Bulan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="bulan" id="bulan" onchange="setbulan(this.value)">
                            
                        </select>
                    </div>
                </div>  
            </div> 
                    
           <div class="form-group">
                <label class="col-md-3 control-label">Jenis Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select path="jenislap" id="jenislap" onchange="setComboBulan()">
                            <option value="1" selected>01 - laporan</option> 
                        </select>
                    </div>
                </div>  
            </div> 
                    
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetakjurnal()' href="#" > Cetak</button>
                   <!-- <button type="button" class="btn blue" onclick='cetaktes()' href="#" > Cetak tes</button> --> 
                </div>
            </div>   
           
        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/laporanlra/laporanlra.js"></script>  

