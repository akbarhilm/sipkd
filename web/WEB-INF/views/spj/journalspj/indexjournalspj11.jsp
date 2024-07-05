<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    $(document).ready(function() {
       
        
    });
</script>
    
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Jurnal SPJ</a></li>
</ul>
 
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/journalspj/prosejournal11" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Jurnal SPJ</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="adanilaispj" name="adanilaispj" value="${adanilaispj}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
                
           <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <form:hidden path="skpd.idSkpd" id='idskpd'  value="${skpd.idSkpd}"  />
                        
                    <input path="idskpdpop" id="idskpdpop" type="hidden" value="${skpdpop}" onchange="setBulan(this.value); setIdskpd(this.value)" />
                    <input path="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${idskpdpop}" class="m-wrap large"  />
                    <a id="pilihskpd"  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/skpdpopup/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                </div>
            </div>
                   
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan / No SPJ  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select  path="bulan" id="bulan" onchange="gridspj()">  
                            <form:options  />
                        </form:select >
                        <form:errors path="bulan" cssClass="error"  />
                        <form:hidden path="idSpj" id="idSpj"  value="${idSpj}"  />
                        
                    </div>
                </div>
            </div>
                  
        </div>
    </div> 
                        
    <div class="portlet box">
        
            <div class="portlet-body">
                <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Kegiatan</th>
                            <th>Nama Kegiatan</th>
                            <th>Akun Belanja</th> 
                            <th>Nilai SPJ</th>
                        </tr>
                    </thead>
                    <tbody id="btlspdtablebody" >
                    </tbody>                
                </table>
            </div>
       
    </div>
                        
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
           <!-- <button id="buttoninduk" type="submit" class="btn blue" > Proses Jurnal </button> -->
            <button type="button" class="btn blue" onclick='simpan()' href="#" > Proses Jurnal </button>
            <a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a>
            
        </div>
    </div> 
            
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalspj/indexjournalspj11.js"></script>  