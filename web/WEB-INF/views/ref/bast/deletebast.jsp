<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">

    $(document).ready(function() {
        gridakun();
    });

    function gridakun() {

        var urljson = getbasepath() + "/bast/json/listakunkontrakrinci";
        $("#akunpopup").show();
        if (typeof myTable == 'undefined') {
            myTable = $('#akunpopup').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "iDisplayLength": 50,
                "bInfo": true,
                "bScrollCollapse": true,
                "bAutoWidth": false,
                //"sScrollY": ($(window).height() * 108 / 100),
                "bFilter": false,
                "sAjaxSource": urljson,
                "aaSorting": [[1, "asc"]],
                "fnServerParams": function(aoData) {
                    aoData.push(
                            {name: "idkegiatan", value: $('#idkegiatan').val()},
                    {name: "idskpd", value: $('#idskpd').val()},
                    {name: "idbast", value: 0000},
                    {name: "idkontrak", value: $('#idKontrak').val()},
                    {name: "nobast", value: $('#noBast').val()},
                    {name: "tahun", value: $('#tahunAnggaran').val()}
                    );
                }, "fnDrawCallback": function() {
                    formatnumberonkeyup();
                    $(".inputmoney2").autoNumeric('init', {aSep: '.', aDec: ','});
                },
                "fnServerData": function(sSource, aoData, fnCallback) {
                    $.ajax({
                        "dataType": 'json',
                        "type": "GET",
                        "url": sSource,
                        "data": aoData,
                        "success": fnCallback
                    });
                },
                "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                    var numStart = this.fnPagingInfo().iStart;
                    var index = numStart + iDisplayIndexFull + 1;

                    var readonly = 'readonly';


                    var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") />";
                    //var tesnilai = 300000000000;

                    var namaAkun = "<input type='hidden' id='namaAkun" + index + "' value='" + index + "' />";
                    var idAkun = "<input type='hidden' id='idAkun" + index + "'  name='idAkun" + index + "' value='" + aData['akun']['idAkun'] + "' />";
                    var prestasi = "<input type='text'   id='nilaiprestasi" + index + "'    name='nilaiprestasi" + index + "'   value='" + aData['nilaiPrestasi'] + "'  class='inputmoney sppnilai'      />";
                    var bast = "<input type='text'   id='nilaibast" + index + "'    name='nilaibast" + index + "'   value='" + aData['nilaiBast'] + "'   class='inputmoney sppnilai'  " + readonly + "  onkeyup=pasangvalidasi(" + index + ");hitungtotalbast() />"; //pasangvalidatebesarperfield(" + index + "),cekTotal(" + index + ")

                    var nilaianggaran = "<input type='hidden'  class='inputmoney'  id='nilaianggaran" + index + "'  name='nilaianggaran" + index + "'  value='" + aData['sisaSpd'] + "'       />";
                    var idBl = "<input type='hidden' id='idBl" + index + "'  name='idBl" + index + "' value='" + aData['idBl'] + "' />";
                    var idspd = "<input type='hidden' id='idspd" + index + "'  name='idspd" + index + "' value='" + aData['idSpd'] + "' />";
                    var idbast = "<input type='hidden' id='idbast" + index + "'  name='idbast" + index + "' value='" + aData['idBast'] + "' />";
                    var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:99%;'>" + aData['akun']['namaAkun'] + "</textarea>"

                    var addoreditval;

                    if (aData['nilaiBast'] > 0 || aData['idBast'] !== 0) {
                        addoreditval = "edit";
                    } else {
                        addoreditval = "add";
                    }

                    var addoredit = "<input type='hidden'  id='addoredit" + index + "'  name='addoredit" + index + "'  value='" + addoreditval + "'  />";

                    $('td:eq(0)', nRow).html(index);
                    $('td:eq(2)', nRow).html(namaakuntext + idAkun + namaAkun);
                    $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiSpd']));
                    $('td:eq(4)', nRow).html(accounting.formatNumber(aData['sisaSpd']));
                    $('td:eq(5)', nRow).html(prestasi);
                    $('td:eq(6)', nRow).html(bast + idBl + nilaianggaran + idspd);


                    return nRow;
                },
                "aoColumns": [
                    {"mDataProp": "idSpd", "bSortable": false, sClass: "center", sWidth: '3%'},
                    {"mDataProp": "noSpd", "bSortable": true, sClass: "center", sWidth: '8%'},
                    {"mDataProp": "akun.namaAkun", "bSortable": false, sClass: "right", sWidth: '35%'},
                    {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "right", sWidth: '15%'},
                    {"mDataProp": "sisaSpd", "bSortable": false, sClass: "right", sWidth: '15%'},
                    {"mDataProp": "nilaiPrestasi", "bSortable": false, sClass: "right", sWidth: '11%'},
                    {"mDataProp": "nilaiBast", "bSortable": false, sClass: "-", sWidth: '15%'}
                ]

            });
        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }


    }

</script>



<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bast"  >Daftar  Berita Acara Serah Terima (BAST)</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  Berita Acara Serah Terima<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah  Berita Acara Serah Terima (BAST)</div>
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bast/deletebast" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="tahunAnggaran"  id="tahunAnggaran" cssClass="required" type="text" readonly="true" size="4" maxlength="6" value="${tahunAnggaran}"  />
                        <form:errors path="tahunAnggaran" class="tahunAnggaran" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="kontrak.idKontrak" id="idKontrak" value="${kontrak.idKontrak}"  cssClass="required" size="10" maxlength="180" readonly='true'  />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                        <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="55" readOnly="true" />
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idkegiatan" id='idkegiatan' readonly='true' value="${idkegiatan}" onchange="gridakun();getbanyakakun()"  />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  value="${kegiatan.namaKegiatan}" cssClass="required"   type="text" size="80" readonly='true'  />
                        <input type="hidden" name="banyakakun" id="banyakakun"  value="${fn:length(listbast)}" />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor BAST:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noBast" id="noBast"  readonly="true"      size="10" maxlength="50"  />
                        <form:errors path="noBast" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAST :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglBast" path="tglBast" id="tglBast" readonly="true"  class="required form-control form-control-inline input-small   entrytanggal2 valid" size="14"  />
                        <form:errors path="tglBast" class="error" />
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"  readonly="true"   size="55" maxlength="50"   />
                        <form:errors path="namaPptk" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk" readonly="true"    size="20" maxlength="18"   />
                        <form:errors path="nipPptk" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPemeriksaBarang" id="namaPemeriksaBarang"   readonly="true"    size="55" maxlength="50"   />
                        <form:errors path="namaPemeriksaBarang" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPemeriksaBarang" id="nipPemeriksaBarang"   readonly="true"    size="20" maxlength="18"   />
                        <form:errors path="nipPemeriksaBarang" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketBast" id="ketBast"  readonly="true"   size="55" maxlength="250"   />
                        <form:errors path="ketBast" class="error" />
                    </div>
                </div>
            </div>


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Hapus </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bast" >Kembali</a>
                </div>
            </div>

            <div id="tabelAkun" class="portlet-body">
                <table id="akunpopup" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>No SPD</th>
                            <th>Nama Akun</th>
                            <th>Nilai SPD</th>
                            <th>Sisa SPD</th>
                            <th>Prestasi</th>
                            <th>Nilai BAST</th>

                        </tr>
                    </thead>

                    <tbody  >
                    </tbody>
                </table>
            </div>
        </form:form>