/*
 $(document).ready(function() {
 gridsetor();
 
 });
 */


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
        myTable2 = $('#forrinci').dataTable({
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

                if (result.aData.length == 0) {
                    if ($('#kegiatan').val() !== '') {
                        $('#namakegiatan').val('');
                        $('#idkegiatan').val('');
                        $('#pagu').val('');

                        myTable.fnClearTable(0);
                        myTable.fnDraw();
                    }
                } else {
                    if ($('#kegiatan').val() !== '') {
                        $('#namakegiatan').val(result.aData[0]['namaKeg']);
                        $('#idkegiatan').val(result.aData[0]['idKegiatan']).change();
                        $('#pagu').val(accounting.formatNumber(result.aData[0]['pagu'], 2, '.', ","));
                    } else {
                        bootbox.alert("Isi Kode Kegiatan Terlebih Dulu");
                    }
                }

            });
}

function temuanpemeriksa(kodebeban) {
    var opttemuan = '<option value="1">Temuan(SP2D Sudah Pengesahan)</option>';

    if (kodebeban != 'UP') {
        opttemuan += '<option value="0" selected="">Bukan Temuan</option>';
    }

    $("#temuan").html(opttemuan);
}


