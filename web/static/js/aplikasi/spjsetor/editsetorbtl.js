$(document).ready(function() {
    setPanel($('#tahunAngg').val());

});

function setPanel(tahun) {

    if (tahun >= 2017) {
        document.getElementById("labelnobku").style.display = "block";
        $('#tabel2017').show();
        $('#tabel2016').hide();
        gridlistsetor();
        getdatabku();

    } else {
        document.getElementById("labelnobku").style.display = "none";
        $('#tabel2016').show();
        $('#tabel2017').hide();
        gridsetor();

    }
}

function getdatabku() {
    $.getJSON(getbasepath() + "/setor/json/getdatabku", {idskpd: $("#idskpd").val(), tahun: $("#tahunAngg").val(), nobku: $("#noBku").val()},
    function(result) {
        $('#ketbku').val(result.aData[0]['ketBku']).change();
    });
}

function getbanyaksetorrinci() {
    $.getJSON(getbasepath() + "/setor/json/getbanyaklistbtlls", {nobku: $("#noBku").val(), idskpd: $("#idskpd").val(), idSetor: $("#idSetor").val(), tahunAnggaran: $("#tahun").val()},
    function(result) {
        $('#banyakrinci').val(result);

    });
}

function gridsetor() {
    var urljson = getbasepath() + "/setor/json/getforsetorrincibtl";

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
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idSetor", value: $('#idSetor').val()},
                {name: "tahunAnggaran", value: $('#tahunAngg').val()},
                {name: "idskpd", value: $('#idskpd').val()}
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
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";

                var readonlyinput = aData['nilaiSetor'] > 0 ? "" : "readonly";
                var nilaianggaran = aData['nilaiAnggaran'];//accounting.formatNumber(aData['nilaiAnggaran']);
                var inputnilaianggaran = "<input type='text' id='nilaianggaran" + aData['idBl'] + "' class='inputmoney' name='nilaianggaran" + aData['idBl'] + "' value='" + nilaianggaran + "' readOnly='true' >";

                var nilaisetor = aData['nilaiSetor'];//accounting.formatNumber(aData['nilaiSetor']);
                //var inputnilaisetor = "<input type='text' id='nilaisetor" + aData['idBl'] + "' class='inputmoney' name='nilaisetor" + aData['idBl'] + "' value='" + nilaisetor + "'  readOnly='true'   onkeyup=pasangvalidatebesarperfield(" + aData['idBl'] + ") />";
                var inputnilaisetor = "<input type='text' id='nilaisetor" + aData['idBl'] + "' class='inputmoney' name='nilaisetor" + aData['idBl'] + "' value='" + nilaisetor + "'  " + readonlyinput + "   onkeyup=pasangvalidatebesarperfield(" + aData['idBl'] + ") />";

                var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idBl" + aData['idBl'] + "' name='idBl" + aData['idBl'] + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + aData['idBl'] + "' name='idAkun" + aData['idBl'] + "' >";
                var idsetorrinci = "<input type='hidden' value='" + aData['idSetorrinci'] + "' id='idSetorrinci" + aData['idBl'] + "' name='idSetorrinci" + aData['idBl'] + "' >";

                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(2)', nRow).html(inputnilaianggaran);
                $('td:eq(3)', nRow).html(inputnilaisetor);
                $('td:eq(4)', nRow).html(inputcek + idbl + idbas + idsetorrinci);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-"},
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

    // getbanyaksetorrinci();

}

function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaisetor" + idbl).attr("readonly", param);
    //  $("#sisaspj" + idbl).attr("readonly", param);
    if (param == false) {
        //   $("#nilaisetor" + idbl).val(nilaiisian)
    }

}

function pasangvalidatebesarperfield(idbl) {
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


function gridlistsetor() {
    //$('#buttoninduk').attr("disabled", false);

    var urljson = getbasepath() + "/setor/json/listsetorbtlls";

    //var urljson = getbasepath() + "/setor/json/getforsetorrincibtl"; // yang update

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
                        {name: "nobku", value: $('#noBku').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idskpd", value: $('#idskpd').val()}
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

                var nilaisetor = accounting.formatNumber(aData['nilaiSetor']);
                var inputnilaisetor = "<input type='text' id='nilaisetor" + index + "' class='inputmoney' name='nilaisetor" + index + "' value='" + aData['nilaiSetor'] + "'  readOnly='true' />";
                var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idBl" + index + "' name='idBl" + index + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + index + "' name='idAkun" + index + "' >";

                $('td:eq(2)', nRow).html(inputnilaisetor + idbl + idbas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "right"}
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