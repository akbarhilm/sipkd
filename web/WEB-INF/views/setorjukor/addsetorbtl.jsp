<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
   

</script>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Setoran Lewat Tahun (BTL)</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar Setor</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  Setor</a></li>
</ul>

<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/setorjukor/simpansetorbtl" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs" ></i>Form Setor</div>       
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Setor :</label>
                <div class="col-md-4">
                    <form:hidden path="idSetor" id='idSetor'    />
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="tahunSetor" id="tahunSetor" value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden"  name="idskpdlama"  id="idskpdlama" >
                        <form:hidden path="skpd.idSkpd" id='idskpd' name="idskpd" onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="90"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>
                   
            <div class="form-group">
                <label class="col-md-3 control-label">No Setor :</label>
                <div class="col-md-5">
                    <div class="input-group">                      
                        <form:input path="noSetor" type="text"  id="noSetor" readonly="true" class="m-wrap large" size="20"    />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor"    class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="14" value=""/>
                        <form:errors path="tglSetor" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="beban" readonly="true">
                            <option value="LS" selected>Langsung</option>
                        </form:select>
                        <form:errors path="beban" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="jenis" readonly="true">
                            <option value="1">Biaya Tidak Langsung</option>  
                             
                        </form:select>
                        <form:errors path="jenis" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="tahunAngg" id="tahunAngg" type="text" size="6" maxlength="4" readonly="true"/> 
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">No Jurnal Koreksi :</label>
                <div class="col-md-4">
                    <input type="hidden"  name="NoSetor"  id="NoSetor" value="-888">
                    <input type="hidden"  name="banyakrinci"  id="banyakrinci" readonly="true">
                    <form:input path="noJurnal" id="noJurnal" readonly="true" cssClass="required" type="text" onchange="gridsetor()" /> 
                    &nbsp;<a  class="fancybox fancybox.iframe btn green" id="pilihjukor" href="${pageContext.request.contextPath}/setorjukor/listjukorbtl?target='_top'" title="Pilih Jurnal Koreksi"  ><i class="icon-zoom-in"></i> Pilih</a> 
                    <form:errors path="noJurnal" class="error" />
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-5">
                    <div class="input-group">       
                        <form:textarea path="keterangan" id="keterangan" cols="50" rows="3" maxlength="150" readonly="false" /> 
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/setorjukor/indexbtl" >Kembali</a>
                </div>
            </div>

        </div>
    </div>


    <div class="portlet">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian Setor</div>       
        </div> 
        
        <div id="tabel2016">
            <table id="forrinci2016" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>Akun</th> 
                        <th>Nama Akun</th>
                        <th>Nilai Setoran</th>
                        
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div> 
        
    </div> 


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/setorjukor/addsetorbtl.js"></script>    

