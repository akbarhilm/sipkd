$(document).ready(function () {
    getNoValidasiByIdSkpd( );

});

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/btl/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/btl/json/getlistspdbtlbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function (result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/restitusi/indexres/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/restitusi/indexres");
    }

}
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#restitusirinci :input[type='text']").attr("readonly", false);
    } else {
        $("#restitusirinci :input[type='text']").attr("readonly", "readonly");
    }
}

function enablerow(obj, idbtl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbtl)
}
function setdisabled(param, idbtl) {
    $("#nilaiSppSaatini" + idbtl).attr("readonly", param);
    /* if (param == false) {
     var nilaispp = accounting.unformat($("#nilaispp" + idbtl).val(), ",");
     var nilaispd = accounting.unformat($("#nilaispd" + idbtl).val(), ",");
     var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
     $("#nilaispp" + idbtl).val(nilaiisian)
     }*/
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = Number($("#nilaiSppSaatini" + idbl).autoNumeric('get'));
    var nilaispd = Number($("#sisaRestitusi" + idbl).autoNumeric('get'));
    var status = nilaispp <= nilaispd ? true : false;
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari sisa restitusi", function () {
            $("#nilaiSppSaatini" + idbl).autoNumeric('set', nilaispd);
            $("#nilaiSppSaatini" + idbl).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    var searchIDs = $("#restitusirinci input:checkbox:checked").not("#pilihall").map(function () {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function (idx, data) {
        total += $("#nilaiSppSaatini" + data).autoNumeric('get');
    })
    $("#totalspp").autoNumeric('set', total);


}

function getNoValidasiByIdSkpd( ) {
    $.getJSON(getbasepath() + "/restitusi/json/ceknovalidasi?noValidasi=" + $("#validasi").val(),
            function (result) {
                if (result.id == '0') {
                    bootbox.alert(result.status);
                } else if (result.id == '1') {
                    getrincipnrmbynovalidasi();
                    gettotalspprestitusi();
                    getbanyakrinci();
                    /*bootbox.alert(result.status, function () {
                     getrincipnrmbynovalidasi();
                     gettotalspprestitusi();
                     getbanyakrinci();
                     });*/

                }
            });
}

function getrincipnrmbynovalidasi() {
    var urljson = getbasepath() + "/restitusi/json/getrinciakunbynomorvalidasi";
    if (typeof myTable == 'undefined') {
        myTable = $('#restitusirinci').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "bDestroy": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                formatnumberonkeyup();
                // $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15, allowNegative: true, insertPlusSign: 'true'});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "novalidasi", value: $("#validasi").val()}
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var isceked = aData['nilaiSppSaatini'] != 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                console.log("readonly = "+readonly);
                
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun' value='" + aData['namaAkun'] + "' />"
                var nilaiststext = "<input type='text' name='nilaiPnrm" + index + "' id='nilaiPnrm" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiPnrm'] + "' />"
                var nilaisppsebelum = "<input type='text' name='nilaiSppSebelum" + index + "' id='nilaiSppSebelum" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['nilaiSppSebelum'] + "' />";
                var nilaisppsisa = "<input type='text' name='sisaRestitusi" + index + "' id='sisaRestitusi" + index + "'  class='inputmoney'  readonly='true'   value='" + aData['sisaRestitusi'] + "' />";
                var nilaiSppSaatini = "<input type='text' name='nilaiSppSaatini" + index + "' id='nilaiSppSaatini" + index + "'  class='inputmoney'  " + readonly + "  onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'  value='" + aData['nilaiSppSaatini'] + "' />";
                
                var idPnrmRinci = "<input type='hidden' name='idPnrmRinci" + index + "'  id='idPnrmRinci" + index + "' value='" + aData['idPnrmRinci'] + "' />";
                var idAkun = "<input type='hidden' name='idAkun" + index + "'  id='idAkun" + index + "' value='" + aData['idAkun'] + "' />";
                var idPnrm = "<input type='hidden' name='idPnrm" + index + "'  id='idPnrm" + index + "' value='" + aData['idPnrm'] + "' />";

                
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "'  onchange=enablerow(this," + index + ") " + isceked + " />";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaiststext);
                $('td:eq(4)', nRow).html(nilaisppsebelum);
                $('td:eq(5)', nRow).html(nilaisppsisa);
                $('td:eq(6)', nRow).html(nilaiSppSaatini);
                $('td:eq(7)', nRow).html(inputcek + idPnrmRinci + idAkun + idPnrm);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPnrmRinci", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeAkun", "bSortable": false, "sWidth": "8%"},
                {"mDataProp": "namaAkun", "bSortable": false, "sWidth": "17%"},
                {"mDataProp": "nilaiPnrm", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": false, "sWidth": "10%"},
                {"mDataProp": "sisaRestitusi", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSppSaatini", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "idPnrmRinci", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });


    }
    else
    {
        myTable.fnClearTable();
        myTable.fnDestroy();
    }

}

function gettotalspprestitusi() {
    var urljson = getbasepath() + "/restitusi/json/gettotalnilaiakunbynomorvalidasi";
    $.getJSON(urljson,
            {novalidasi: $("#validasi").val(), idspp: $('#id').val(), idskpd: $('#idskpd').val(), tahunAnggaran: $('#tahun').val()},
    function (result) {
        $("#totalpnrm").autoNumeric('set', result.V_PNRM);
        $("#totalsebelum").autoNumeric('set', result.V_SPP_SEBELUM);
        $("#totalsisa").autoNumeric('set', result.SISA_RESTITUSI);
        $("#totalspp").autoNumeric('set', result.V_SPP_SAATINI);
    });
}

function getbanyakrinci() {
    var urljson = getbasepath() + "/restitusi/json/getbanyakrinci";
    $.getJSON(urljson,
            {novalidasi: $("#validasi").val(), idspp: $('#id').val(), idskpd: $('#idskpd').val(), tahunAnggaran: $('#tahun').val()},
    function (result) {
        $("#banyakrinci").val(result);
    });

}

 