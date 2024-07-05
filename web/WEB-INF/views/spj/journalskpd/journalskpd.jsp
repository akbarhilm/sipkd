<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    $(document).ready(function() {
        //gridspj();
        
    });
</script>
    
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Saldo Awal SKPD/PPKD</a></li>
</ul>
 
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/journalskpd/simpanJournal" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Saldo Awal SKPD/PPKD</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="banyakrinci" name="banyakrinci"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
              
           <!--
           <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="90"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>   
            -->
            
            <div class="form-group">
                <label class="col-md-3 control-label">Unit  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="totalDebet" id='totalDebet'  value="${totalDebet}"  />
                        <form:hidden path="totalKredit" id='totalKredit' value="${totalKredit}"  />
                        
                        <input type="hidden" id="idakunpop" name="idakunpop"  onchange="getidakun()" value="${idakunpop}">
                        <input type="hidden" id="namaakunpop" name="namaakunpop"  onchange="getidakun()" value="${namaakunpop}">
                        <input type="hidden" id="idbaspop" name="idbaspop"  onchange="getidakun()" value="${idbaspop}">

                        <form:select  path="unit" id="unit" onchange="getbanyakrinci(),gridskpd()">  
                            <form:options items="${listUnit}"  itemValue="kodeSkpd" itemLabel = "namaSkpd"  />
                        </form:select >
                        <form:errors path="unit" cssClass="error" />
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
                            <th>Pilih</th>
                            <th>Kode Akun</th>
                            <th>Nama Akun</th>
                            <th>Debet</th> 
                            <th>Kredit</th> 
                            
                        </tr>
                    </thead>
                    
                    <tbody id="jourskpdtablebody" >
                    </tbody> 
                    
                    <tfoot id="jourskpdtablefoot" >
                        <tr>
                            <th colspan="4" style="text-align:right">Grand Total:</th>
                            <th colspan="1">
                                <input  type='text' id="totdebet"  name="totdebet" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                            </th>
                            <th colspan="1" >
                                <input type='text' id="totkredit"   name="totkredit" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                            </th>
                        </tr>
                    </tfoot>
                    
                    <tfoot id="jourskpdtablefoot2" >
                    </tfoot>

                </table>
            </div>
    </div>
               
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" class="btn blue" onclick='submitnilai()'>Simpan</button>
            <a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a>
            <button type="button" class="btn blue" onclick='tambahRow()' >Tambah Data</button>
            <!-- <button type="button" class="btn blue" onclick='tampilCek()' >Cek</button> -->
        </div>
    </div>
            
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journalskpd/journalskpd.js"></script>  