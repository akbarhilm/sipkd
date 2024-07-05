<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    $(document).ready(function() {
        setTglValidasi($('#loket').val());
        //gridpnrm();
        
    });
</script>
    
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Jurnal Penerimaan</a></li>
</ul>
 
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/journalpnrm/prosejournal" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Jurnal Penerimaan</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun 
                </label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="banyakrinci" name="banyakrinci"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
              
            <div class="form-group">
                <label class="col-md-3 control-label">Loket  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <form:select  path="loket" id="loket" onchange="setTglValidasi(this.value),gridpnrm()" >  
                            <form:options items="${listLoket}"  itemValue="idloket" itemLabel = "namaloket"  />
                        </form:select >
                        <form:errors path="loket" cssClass="error" />
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Validasi  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        
                        <form:select  path="tglvalidasi" id="tglvalidasi" onchange="gridpnrm()" >  
                            <form:options />
                        </form:select >
                        <form:errors path="tglvalidasi" cssClass="error" />
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
                            <th>Tgl Validasi</th>
                            <th>No Loket</th>
                            <th>Kode SKPD</th>
                            <th>Nama SKPD</th>
                            <th>STS</th> 
                            <th>Nilai</th> 
                            <th>Kode</th> 
                            
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalpnrm/journalpnrm.js"></script>  