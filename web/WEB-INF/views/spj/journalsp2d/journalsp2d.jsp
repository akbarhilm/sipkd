<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    $(document).ready(function() {
        setTanggal($('#wilayah').val());
        //gridsp2d();
        
    });
</script>
    
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Jurnal SP2D</a></li>
</ul>
 
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/journalsp2d/prosejournal" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Jurnal SP2D</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="banyakrinci" name="banyakrinci"  />
                    <form:hidden path="pengguna.kodeGrup" id='kodegrup' value="${pengguna.kodeGrup}"  />
                    <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
              
            <div class="form-group">
                <label class="col-md-3 control-label">Wilayah  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <form:select  path="wilayah" id="wilayah" onchange="setTanggal(this.value),gridsp2d()" >  
                            <form:options items="${listWilayah}"  itemValue="kodewil" itemLabel = "ketwilayah"  />
                        </form:select >
                        <form:errors path="wilayah" cssClass="error" />
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal SP2D Sah  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="tglsah" id="tglsah" onchange="gridsp2d()" >  
                            <form:options  />
                        </form:select >
                        <form:errors path="tglsah" cssClass="error" />
                    </div>
                </div>
            </div>
                    
        </div>
    </div> 
                        
    <div class="portlet box">
        
            <div class="portlet-body">
                <table id="jourskpdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>No SP2D</th>
                            <th>SKPD</th>
                            <th>Kode</th>
                            <th>Nilai</th> 
                            
                        </tr>
                    </thead>
                    
                    <tbody id="jourskpdtablebody" >
                    
                    </tbody>    
                    
                </table>
            </div>
    </div>
               
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button id="buttoninduk" type="submit" class="btn blue" > Proses Jurnal </button>
            <a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a>
            
        </div>
    </div>
            
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalsp2d/journalsp2d.js"></script>  