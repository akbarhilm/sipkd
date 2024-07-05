<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(document).ready(function() {
        var nilai = accounting.formatNumber(window.localStorage.getItem("ketNilaiKontrak"), 2, '.', ",");

        //$("#nilaiKontrak").val(nilai);
        $("#idSpd").val(window.localStorage.getItem("ketIdSpd"));
        $("#idKegiatan").val(window.localStorage.getItem("ketIdKegiatan"));
        $("#idKontrak").val(window.localStorage.getItem("ketIdKontrak"));
        $("#namaKegiatan").val(window.localStorage.getItem("ketNamaKegiatan"));

        getNilaiKontrak();
    });

    function pindahhal() {
        parent.$.fancybox.close();
    }
    
</script>
<ul class="breadcrumb">

    <li>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Kontrak Rinci<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Input Kontrak Rinci</div>
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/kontrakrinci/simpan" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="tahun" id="tahun" cssClass="required" type="text" readonly="true" size="4" maxlength="6" value="${tahunAnggaran}"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                        <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="55" readOnly="true" />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idSpd" id='idSpd' />
                        <form:hidden path="idKegiatan" id='idKegiatan' readonly='true' />
                        <input type='text' id="namaKegiatan"  name="namaKegiatan" readonly="true" size ="70"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idKontrak" id="idKontrak"  />
                        <form:input path="nilaiKontrak" id="nilaiKontrak"  type="text" size="30" readonly='true'  />

                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <!-- <button id="buttoninduk"    type="submit" class="btn blue" > Simpan </button> -->
                    <button type="button" id="btnSimpan" class="btn blue" onclick='simpan()'>Simpan</button>
                    <a class="btn blue"  href="#" onclick="pindahhal()">Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

<div class="portlet box">

    <div >
        <input type="hidden" name="banyakakun" id="banyakakun" />
        <table id="akunpopup" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Nama Akun</th>
                    <th>Nilai Anggaran</th>
                    <th>Sisa UMK</th>
                    <th>Kontrak Sebelum</th>
                    <th>Sisa Kontrak</th>
                    <th>Nilai Kontrak</th>
                    <th>Nilai UMK</th>
                    <th></th>
                </tr>
            </thead>

            <tfoot>
                <tr>
                    <th colspan="6" style="text-align:right">Total:</th>
                    <th>
                        <input type='text' id="totalkontrak"  name="totalkontrak" readonly="true"  style='border:none;margin:0;width:99%; text-align: right;'    />
                    </th>

                </tr>
            </tfoot>

            <tbody>
            </tbody>
        </table>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/kontrakrinci/addkontrakrinci.js"></script>