
$(document).ready(function() {
    $('#tahunAngg').val("");
    setPanel($('#tahun').val());
    $('#buttoninduk').attr("disabled", true);
    $('#kodeSA').val("0");
    getNilaiSA();
});

//<form:hidden path="kodeSA" id="kodeSA" />
//<input type="checkbox" name="cbSA" id="cbSA" onclick="setSA()" />
function cekNilaiSA(nilai) {
    var saldo = accounting.unformat($("#nilaisaldoawal").val(), ",");


    if (nilai > saldo) {
        bootbox.alert("Nilai Setor Tidak Boleh Lebih Besar dari Nilai Saldo Awal");
        $("#nilaiSetor").val(saldo);
    }
}

function setSA() {
    var uraian = "";
    var tahun = $('#tahun').val();
    var TA = tahun -1;

    if (document.getElementById("cbSA").checked == true) {
        $('#kodeSA').val("1");

        document.getElementById("labelsaldoawal").style.display = "block";
        document.getElementById("labelnilaisa").style.display = "block";
        document.getElementById("labelkeg2017").style.display = "none";
        document.getElementById("labelkeg2016").style.display = "none";
        document.getElementById("labelnamakeg2016").style.display = "none";
        document.getElementById("labelpagu2016").style.display = "none";

        $('#tabel2016').hide();
        $('#tabel2017').hide();
        $('#buttoninduk').attr("disabled", false);

        uraian = "Pengembalian Sisa Uang Belanja Langsung atas Anggaran Tahun " + TA;
        $('#keterangan').val(uraian);
        
        $('#tahunAngg').val(TA);
        document.getElementById("tahunAngg").readOnly=true;

    } else {
        $('#buttoninduk').attr("disabled", true);
        $('#kodeSA').val("0");
        setPanel($('#tahun').val());
        $('#keterangan').val(uraian);
        
        $('#tahunAngg').val("");
        document.getElementById("tahunAngg").readOnly=false;
    }
}

function getNilaiSA() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idsetor = "-999";

    $.getJSON(getbasepath() + "/setor/json/getSalsoAwalLs", {tahun: tahun, idskpd: idskpd, idsetor: idsetor},
    function(result) {
        var banyak;

        var nilai = result.aData[0]['nilaiSA'];
        $('#nilaisaldoawal').val(accounting.formatNumber(nilai, 2, '.', ","));
        $('#nilaiSetor').val(nilai);
        $('#nilaisetoran').val(accounting.formatNumber(nilai, 2, '.', ","));

    });

}

function setPanel(tahun) {

    /*$('#idKegiatan').val('');
     $('#namakegiatan').val('');
     $('#namakegiatan2016').val('');
     $('#idkegiatan').val('');
     $('#pagu').val(''); */

    document.getElementById("labelnilaisa").style.display = "none";
    document.getElementById("labelsaldoawal").style.display = "none";

    if (tahun >= 2016) {
        document.getElementById("labelkeg2017").style.display = "block";
        document.getElementById("labelkeg2016").style.display = "none";
        document.getElementById("labelnamakeg2016").style.display = "none";
        document.getElementById("labelpagu2016").style.display = "none";

        $('#tabel2017').show();
        $('#tabel2016').hide();

    } else {
        document.getElementById("labelkeg2017").style.display = "none";
        document.getElementById("labelkeg2016").style.display = "block";
        document.getElementById("labelnamakeg2016").style.display = "block";
        document.getElementById("labelpagu2016").style.display = "block";

        $('#tabel2016').show();
        $('#tabel2017').hide();

    }
    
}

function getbanyaksetorrinci() {
    $.getJSON(getbasepath() + "/setor/json/getbanyaklistblls", {idskpd: $("#idskpd").val(), idkegiatan: $("#idKegiatan").val(), tahunAnggaran: $("#tahunAngg").val(), nobku: $("#noBku").val()},
    function(result) {
        $('#banyakrinci').val(result);
        // bootbox.alert("banyakrinci = "+ $('#banyakrinci').val());
    });
}

function temuanpemeriksa(kodebeban) {
    var opttemuan = '<option value="1">Temuan(SP2D Sudah Pengesahan)</option>';

    if (kodebeban != 'UP') {
        opttemuan += '<option value="0" selected="">Bukan Temuan</option>';
    }

    $("#temuan").html(opttemuan);
}

function gridlistsetor() {
    $('#buttoninduk').attr("disabled", false);
    var urljson = getbasepath() + "/setor/json/listsetorblls";
    if (typeof myTable == 'undefined') {
        myTable = $('#forrinci').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                formatnumberonkeyup();
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahunAngg').val()},
                {name: "nobku", value: $('#noBku').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()}
                );
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

                var edit = "<a href='" + getbasepath() + "/spptu/edispptu/" + aData['id'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spptu/delspptu/" + aData['id'] + "' class='icon-remove' ></a>";

                var nilaisetor = accounting.formatNumber(aData['nilaiSetor']);
                var inputnilaisetor = "<input type='text' id='nilaisetor" + index + "' class='inputmoney' name='nilaisetor" + index + "' value='" + aData['nilaiSetor'] + "'  readOnly='true'  />";
                //var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idBl" + index + "' name='idBl" + index + "' >";
                var idkegiatan = "<input type='hidden' value='" + aData['idKegiatan'] + "' id='idKegiatan" + index + "' >";
                var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idbl" + index + "' name='idbl" + index + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + index + "' name='idAkun" + index + "' >";

                $('td:eq(0)', nRow).html(index + idbl + idkegiatan + idbas);
                $('td:eq(3)', nRow).html(inputnilaisetor);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBl", "bSortable": false, sClass: "center"},
                {"mDataProp": "akun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "kanan"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyaksetorrinci();

}
// yang tahun 2016 --------------------------------------------------------------------------------------------------------


function getbanyaksetorrinci2016() {
    $.getJSON(getbasepath() + "/setor/json/getlistsetorrincibanyak", {idskpd: $("#idskpd").val(), idkegiatan: $("#idkegiatan").val(), tahunAnggaran: $("#tahunAngg").val(), idspj: $("#idspj").val()},
    function(result) {
        $('#banyakrinci').val(result);
        // bootbox.alert("banyakrinci = "+ $('#banyakrinci').val());
    });
}
function gridsetor2016() {
    var urljson = getbasepath() + "/setor/json/getforsetorrinci";
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#forrinci2016').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                formatnumberonkeyup();
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahunAnggaran", value: $('#tahunAngg').val()},
                {name: "idkegiatan", value: $('#idkegiatan').val()}
                );
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
                var isceked = aData['nilaiSetor'] > 0 ? 'checked' : '';
                var edit = "<a href='" + getbasepath() + "/spptu/edispptu/" + aData['id'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spptu/delspptu/" + aData['id'] + "' class='icon-remove' ></a>";
                // $('td:eq(0)', nRow).html(index);
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow2016(this," + aData['idBl'] + ") " + isceked + " />";

                var nilaianggaran = aData['nilaiAnggaran'];//accounting.formatNumber(aData['nilaiAnggaran']);
                var inputnilaianggaran = "<input type='text' id='nilaianggaran" + aData['idBl'] + "' class='inputmoney' name='nilaianggaran" + aData['idBl'] + "' value='" + nilaianggaran + "' readOnly='true' >";

                var nilaisetor = accounting.formatNumber(aData['nilaiSetor']);
                var inputnilaisetor = "<input type='text' id='nilaisetor" + aData['idBl'] + "' class='inputmoney' name='nilaisetor" + aData['idBl'] + "' value='" + nilaisetor + "'  readOnly='true'   onkeyup=pasangvalidatebesarperfield2016(" + aData['idBl'] + ") />";

                //alert(inputnilaisetor);
                var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idBl" + aData['idBl'] + "' name='idBl" + aData['idBl'] + "' >";
                var idkegiatan = "<input type='hidden' value='" + aData['idKegiatan'] + "' id='idKegiatan" + aData['idBl'] + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + aData['idBl'] + "' name='idAkun" + aData['idBl'] + "' >";
                var idsetorrinci = "<input type='hidden' value='" + aData['idSetorrinci'] + "' id='idSetorrinci" + aData['idBl'] + "' name='idSetorrinci" + aData['idBl'] + "' >";

                $('td:eq(2)', nRow).html(aData['nilaiAnggaran']);
                $('td:eq(2)', nRow).html(inputnilaianggaran);
                $('td:eq(3)', nRow).html(inputnilaisetor);
                $('td:eq(4)', nRow).html(inputcek + idbl + idkegiatan + idbas + idsetorrinci);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "center"}
            ]
        });

    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    getbanyaksetorrinci2016();

}
function enablerow2016(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled2016(param, idbl)
}
function setdisabled2016(param, idbl) {
    $("#nilaisetor" + idbl).attr("readonly", param);
    //  $("#sisaspj" + idbl).attr("readonly", param);
    if (param == false) {
        // $("#nilaisetor" + idbl).val(nilaiisian)
    }

}

function pasangvalidatebesarperfield2016(idbl) {
    var nilaiAngg = parseFloat($("#nilaianggaran" + idbl).autoNumeric('get'));// parseFloat($("#nilaianggaran" + idbl).val());
    var nilaiSetor = parseFloat($("#nilaisetor" + idbl).autoNumeric('get'));//parseFloat($("#nilaisetor" + idbl).val());
    var status = nilaiSetor > nilaiAngg ? false : true;
    // console.log("NILAI ANGGARAN === " + nilaiAngg + "  -  NILAI SETOR === " + nilaiSetor)
    if (!status) {
        $("#nilaianggaran" + idbl).prop('readonly', true);

        bootbox.alert("Nilai Setoran tidak boleh lebih besar dari Nilai Anggaran", function() {
            $("#nilaisetor" + idbl).autoNumeric('set', nilaiAngg);
            $("#nilaisetor" + idbl).focus();
            $("#nilaisetor" + idbl).prop('readonly', false);

        });

    } else {
        $("#nilaianggaran" + idbl).val($("#nilaianggaran" + idbl).val());
        return true;
    }
}

function getdata() {
    $.getJSON(getbasepath() + "/setor/json/getdata/" + $('#kegiatan').val() + "/" + $('#tahunAngg').val(),
            function(result) {
                console.log(result);
                console.log(result.aData[0]);
                console.log(result.aData[0]['namaKeg']);
                console.log(result.aData[0].namaKeg);

                if (result.aData.length == 0) {
                    if ($('#kegiatan').val() !== '') {
                        $('#namakegiatan2016').val('');
                        $('#idkegiatan').val('');
                        $('#pagu').val('');

                        myTable2.fnClearTable(0);
                        myTable2.fnDraw();
                    }
                } else {
                    if ($('#kegiatan').val() !== '') {
                        $('#namakegiatan2016').val(result.aData[0]['namaKeg']);
                        $('#idkegiatan').val(result.aData[0]['idKegiatan']).change();
                        $('#pagu').val(accounting.formatNumber(result.aData[0]['pagu'], 2, '.', ","));

                        $('#buttoninduk').attr("disabled", false);

                    } else {
                        bootbox.alert("Isi Kode Kegiatan Terlebih Dulu");
                    }
                }

            });
}
