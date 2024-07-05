<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/kontraklist.js"></script>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#"> DaftarKontrak</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Kontrak</div>
        <div class="actions">
            <a href="${pageContext.request.contextPath}/kontrak/addkontrak"  class="btn dark"><i class="icon-plus"></i> Tambah</a>
        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"   id="spdBTLMasterform"   action="${pageContext.request.contextPath}/kontrak/prosessimpankontrak" class="form-horizontal">


            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridkontrak()" readonly="true" />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  readonly="true" value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" onkeyup="if (event.keyCode == 13)
                                    gridkontrak()" value="${tahunAnggaran}" name="tahun" id="tahun" readonly="true" maxlength="4" size="10"  class="m-wrap medium inputnumber" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Metode:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input  name='kodeMetode' id='kodeMetode' onkeyup="if (event.keyCode == 13)
                                    gridkontrak()" />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listmetodepopup?target='_top'" title="Pilih Metode"  ><i class="icon-zoom-in"></i> Pilih</a>
                        <form:errors class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name='rekanan' id="rekanan" size="40"  onkeyup="if (event.keyCode == 13)
                                    gridkontrak()" />
                        <form:errors  class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <input name='nilai1' id="nilai1" size="30" class="inputinvoice valid"  onkeyup="if (event.keyCode == 13)
                                    gridkontrak()" />&nbsp; &nbsp; s/d &nbsp; &nbsp;<input name='nilai2' id="nilai2" size="30"  class="inputinvoice valid"  onkeyup="if (event.keyCode == 13)
                                                gridkontrak()" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input name='namakegiatan' id="namakegiatan" size="40"  onkeyup="if (event.keyCode == 13)
                                    gridkontrak()" />
                        <form:errors  class="error" />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='gridkontrak()'>Cari</button>
                </div>
            </div>

        </form:form>
    </div>
</div>
<div class="portlet box">
    <div class="portlet-title">

    </div>
    <div >
        <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover "  >
            <thead>
                <tr>
                    <th>No</th>
                    <th>No Kontrak</th>
                    <th>Kegiatan</th>
                    <th>Kode Metode</th>
                    <th>Nama Rekanan</th>
                    <th>Nilai Kontrak</th>
                    <th>Pilihan</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table>
    </div>
</div>
