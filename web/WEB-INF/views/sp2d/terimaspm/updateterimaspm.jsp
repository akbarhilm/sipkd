<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spmtelahterima">Daftar SPM Terima</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Data SPM Terima <span id='statusaddedit'></span></a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah data SPM Terima</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd1"  id="progcmd1" onsubmit="return cek()"   action="${pageContext.request.contextPath}/spmtelahterima/prosesupdate" class="form-horizontal"   >
            <div class="form-group" id="labelidSpmCetak" >
                <label class="col-md-3 control-label">ID SPM Cetak :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input name="idSpmCetak"  path="idSpmCetak" id="idSpmCetak" size="30" maxlength="12" readonly="true"  />
                        <form:errors path="idSpmCetak" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelwilsp2d" >
                <label class="col-md-3 control-label">Wilayah SP2D Proses :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input name="codeWilSp2dproses"  path="codeWilSp2dproses" id="codeWilSp2dproses" size="30" maxlength="1" readonly="true"  />
                        <form:errors path="codeWilSp2dproses" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelNoSPM">
                <label class="col-md-3 control-label">No SPM :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input name="iSpmno"  path="iSpmno" id="iSpmno" size="30" maxlength="12" readonly="true" />
                        <form:errors path="iSpmno" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Dokumen SPM  :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="iSpmnoDok" id="iSpmnoDok" size="30" maxlength="50" readonly="true" onchange="setButtonakun()"  />
                        <form:errors path="iSpmnoDok" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group" id="labelSkpd2b" >
                <label class="col-md-3 control-label">Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="codeJenis" id="codeJenis" size="30" maxlength="6" readonly="true"   />
                        <form:errors path="codeJenis" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelSkpd2">
                <label class="col-md-3 control-label">Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="codeBeban" id="codeBeban" size="30" maxlength="6"  readonly="true"/>
                        <form:errors path="codeBeban" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-md-3 control-label">Kode SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="codeSkpd" id="codeSkpd" size="30" maxlength="30" readonly="true" />
                        <form:errors path="codeSkpd" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaSkpd" id="namaSkpd" size="30" maxlength="50" readonly="true" />
                        <form:errors path="namaSkpd" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label class="col-md-3 control-label">Tgl Cetak SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="dSpmCetak" id="dSpmCetak" size="30" maxlength="12" readonly="true" />
                        <form:errors path="dSpmCetak" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label class="col-md-3 control-label">Keterangan SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="eSpm" id="eSpm" size="30"   />
                        <form:errors path="eSpm" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label class="col-md-3 control-label">Petugas Penerima Dokumen SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPegTerimakpkd" id="namaPegTerimakpkd" size="30" maxlength="30"  />
                        <form:errors path="namaPegTerimakpkd" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" >
                <label class="col-md-3 control-label">Petugas Pemberi Dokumen SPM dari SKPD: </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPegPemberiskpd" id="namaPegPemberiskpd" size="30" maxlength="30"   />
                        <form:errors path="namaPegPemberiskpd" class="error" />
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9">
            <button id="buttoninduk"   type="submit" class="btn blue"   > Simpan </button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/spmterima" >Kembali</a>
        </div>
    </div>
</form:form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/terimaspm/terimaspmlist.js"></script>
