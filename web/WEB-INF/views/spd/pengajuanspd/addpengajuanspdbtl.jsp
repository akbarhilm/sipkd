<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPD </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Pengajuan SPD</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Belanja Tidak Langsung <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form SPD Belanja Tidak Langsung</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/spd/pengajuan/btl/prosessimpan" class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="idSpd" id='idSpd'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahunAnggaran" id="tahunAnggaran" type="text" size="6" maxlength="4" readonly='true'  /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'  onChange="getpagudanspd(this.value)"  />
                        <form:input path="skpd.namaSkpd" id="skpd" type="text" size="55" maxlength="80" readonly='true'    />
                        <% /*&nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> */ %>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSpd" id="noSpd" type="text" size="25" maxlength="10" readonly='true'  /> 
                    </div>
                </div>
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Total Anggaran:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="totalAngaran" id="totalAngaran" cssClass="required inputinvoice"   size="25"   readonly='true'  /> 
                    </div>
                </div>
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nilaiSpd" id="nilaiSpd" cssClass="required inputinvoice"  size="25"   readonly='true' />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kebutuhan SPD :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="bulanAwal" id="bulanAwal"   cssClass="required  entrybulan ruleCekBulan"     size="8"    />mm/yyyy 
                        <form:errors path="bulanAwal" class="label label-important" />
                        &nbsp;S/D&nbsp; 
                        <form:input   path="bulanAkhir" id="bulanAkhir"  cssClass="required   entrybulan ruleCekBulan"   size="8" />mm/yyyy 
                        <form:errors path="bulanAkhir" class="label label-important" />

                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Uraian:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:textarea  path="uraian" id="uraian" size="100" maxlength="250" required="error" class="valid" style="margin: 0px; width: 500px; height: 80px;"/>
                        <form:errors path="uraian" class="error" style="color: #B0171F"  />
                    </div>
                </div>
            </div>  
            <%/*  <div class="form-group">
                 <label class="col-md-3 control-label">Pejabat PPKD:</label>
                 <div class="col-md-5">
                 <div class="input-group">
                 <form:hidden  path="pejabatPpkd.idPejabatPPKD" id="pejabatPpkd" />  
                 <input type="text" readonly="true" value="${spdBTLMaster.pejabatPpkd.nip}${not empty spdBTLMaster.pejabatPpkd.nip?"/":""}${spdBTLMaster.pejabatPpkd.namaPegawai}"  name="namaPejabatPPKD" id="namaPejabatPPKD" size="50" class="required" />
                 &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listpejabatppkd?target='_top'" title="Pilih PejabatPPKD"  ><i class="icon-zoom-in"></i> Pilih</a>
                 <form:errors path="pejabatPpkd.idPejabatPPKD" class="label label-important" />
                 </div>
                 </div>
                 </div>*/%>         

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
<form id="formbtlspdtable">
    <input type="hidden" id="nospdform" name="nospdform"  />
    <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
        <thead>
            <tr>
                <th>No</th>
                <th>Akun</th>
                <th>Nama Akun</th>
                <th>Nilai Anggaran</th>
                <th>Sisa SPD</th>
                <th>Nilai SPD</th>
            </tr>
        </thead>
        <tbody id="btlspdtablebody" >
        </tbody> 
    </table>
    <div class="form-actions">
        <div class="col-md-offset-3 col-md-9">
            <span  id="buttonanak"  onclick="simpan()" class="btn blue " style="${empty spdBTLMaster.noSpd?'display:none':''}"  >Simpan SPD Detail</span></div>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function() {
        $("#bulanAkhir").rules('add', {bulanLebihBesar: "#bulanAwal"});
        // getpagudanspd('<c:out value="${spdBTLMaster.skpd.idSkpd}" />'); 
        $("#spdBTLMasterform").validate();
        loadupdate("${pageContext.request.contextPath}/spd/pengajuan/json/getlistspddetailbyskpddantahun");
        statusbreadcrum();
       
        formatnumberonkeyup() ;
    });

    function getpagudanspd(id) {
        var idskpd = $("#idskpd").val();
        var idSpd = $("#idSpd").val();
        var tahunAnggaran = $("#tahunAnggaran").val();
        var noSpd = $("#noSpd").val();
        if (noSpd && $.trim(noSpd).length > 0) {
           // console.log(" kosong " + noSpd + "  : " + id);
            $("#btlspdtablebody").empty();
            gridspdbtllist("${pageContext.request.contextPath}/spd/pengajuan/json/getlistspddetailbyskpddantahun", id, idSpd, tahunAnggaran);
        }
        $.getJSON("${pageContext.request.contextPath}/spd/pengajuan/json/getanggarandanspd/" + id,
                function(result) {
                    $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                    $('#nilaiSpd').val(accounting.formatNumber(result.spd));
                });
          statusbreadcrum();
    }
    function simpan() {
        var idskpd = $("#idskpd").val();
        var idSpd = $("#idSpd").val();
        var tahunAnggaran = $("#tahunAnggaran").val();
        submispddetail('${pageContext.request.contextPath}/spd/pengajuan/json/addspddetail', "${pageContext.request.contextPath}/spd/pengajuan/json/getlistspddetailbyskpddantahun", idskpd, idSpd, tahunAnggaran);
    }
    function pindahhalamanadepan() {
        if ($("#idskpd").val().trim()) {
            window.location.replace("${pageContext.request.contextPath}/spd/pengajuan/btl/indexskpd/" + $("#idskpd").val().trim());
        } else {
            window.location.replace("${pageContext.request.contextPath}/spd/pengajuan/btl/index");
        }
    }

</script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spd/spdbtl.js"></script>   