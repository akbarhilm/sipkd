<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">
    function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spptu/indexspptu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spptu/indexspptu");
    }

}
</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP TU</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus  SPP TU</a></li>
</ul>
        <div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refspptu"  id="refspptu"   action="${pageContext.request.contextPath}/spptu/prossesdelete" class="form-horizontal">
  <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP TU</div>       
        </div>
        <div class="portlet-body flip-scroll">
           <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan ruleCekBulan"     size="8"  readonly="true"  />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" readonly='true'  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>           
            <div class="form-group">
                <label class="col-md-3 control-label">No SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div> 

         
           <div class="form-group">
                <label class="col-md-3 control-label">NIP/NAMA Bendahara:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="20" maxlength="18" readonly="true"  /> / <form:input   path="namaPptk" id="namaPptk"  cssClass="required"   size="45" maxlength="50" readonly="true"  />  
                        <form:errors path="nipPptk"  cssClass="error"  />
                        
                        
                    </div>
                </div>
            </div>      
            

            <div class="form-group">
                <label class="col-md-3 control-label">Alasan Pengajuan SPP-TU:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="keterangan" id="uraian"    cssClass="required"   size="55" maxlength="110" readonly="true"  />  
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  
            <div class="form-group" style="display: none">
                <label class="col-md-3 control-label">Program:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="program.namaProgram" id="namaProgram"  size="55" maxlength="21" readonly="true"  />  
                        <form:errors path="program.namaProgram"  cssClass="error"  />
                    </div>
                </div>
            </div>  
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Hapus':'Hapus'}</button>
                    <span class="btn blue" style="cursor: pointer;"  onclick="pindahhalamanadepan()" href="#">Kembali</span>
                </div>
            </div>

        </div>
    </div>
</form:form>