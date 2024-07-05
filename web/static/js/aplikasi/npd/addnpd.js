$(document).ready(function() {
    gridnpdrincikegiatan();
    
});
function getbanyaknpdrinci() {
    $.getJSON(getbasepath() + "/npd/json/getlistnpdrincibanyak", {idskpd: $("#idskpd").val(), idKegiatan: $("#idKegiatan").val(), tahunAnggaran: $("#tahun").val(), idNpd: $("#idNpd").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });
}

function gridnpdrincikegiatan() {
    var urljson = getbasepath() + "/npd/json/getlistnpdrinci";
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#kegiatantable').dataTable({
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
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idnpd", value: $('#idNpd').val()});
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var npdTotal = 0;
                var sisaNpdTotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        sisaNpdTotal += parseFloat(aaData[i]['sisaNpd']);
                        npdTotal += parseFloat(aaData[i]['nilaiNpd']);
                    }
                }
                $("#totalnpd").val(accounting.formatNumber(npdTotal, 2, '.', ","));
                $("#totalsisanpd").val(accounting.formatNumber(sisaNpdTotal, 2, '.', ","));

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
                var isceked = aData['nilaiNpd'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['akun']['idAkun'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['akun']['idAkun'] + ") " + isceked + " />";
                var nilaiAnggaranTapd = accounting.formatNumber(aData['nilaiAnggaranTapd'], 2, '.', ",");
                var idAkun = "<input type='hidden' name='idAkun" + aData['akun']['idAkun'] + "' id='idAkun" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + aData['akun']['idAkun'] + "' />";
                var nilaiAnggaranTapdText = "<input type='text' name='nilaiAnggaranTapd" + aData['akun']['idAkun'] + "' id='nilaiAnggaranTapd" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiAnggaranTapd + "' />";
                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaiSpdText = "<input type='text' name='nilaiSpd" + aData['akun']['idAkun'] + "' id='nilaiSpd" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiSpd + "' />";
                var nilaiSpdLs = accounting.formatNumber(aData['nilaiSpdLs'], 2, '.', ",");
                var nilaiSpdLsText = "<input type='text' name='nilaiSpdLs" + aData['akun']['idAkun'] + "' id='nilaiSpdLs" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiSpdLs + "' />";
                var nilaiNpdSebelum = accounting.formatNumber(aData['nilaiNpdSebelum'], 2, '.', ",");
                var nilaiNpdSebelumText = "<input type='text' name='nilaiNpdSebelum" + aData['akun']['idAkun'] + "' id='nilaiNpdSebelum" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + nilaiNpdSebelum + "' />";
                var sisaNpd = accounting.formatNumber(aData['sisaNpd'], 2, '.', ",");
                var sisaNpdText = "<input type='text' name='sisaNpd" + aData['akun']['idAkun'] + "' id='sisaNpd" + aData['akun']['idAkun'] + "'  class='inputmoney'  readonly='true'   value='" + sisaNpd + "' />";
                var nilaiNpd = accounting.formatNumber(aData['nilaiNpd'], 2, '.', ",");
                var nilaiNpdText = "<input type='text' name='nilaiNpd" + aData['akun']['idAkun'] + "' id='nilaiNpd" + aData['akun']['idAkun'] + "'  class='inputmoney'   value='" + nilaiNpd + "' "+ readonly +" onkeyup='pasangvalidatebesarperfield(" + aData['akun']['idAkun'] + ");hitungnilainpd()'/>";


                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(nilaiAnggaranTapdText);
                $('td:eq(4)', nRow).html(nilaiSpdText);
                $('td:eq(5)', nRow).html(nilaiSpdLsText);
                $('td:eq(6)', nRow).html(nilaiNpdSebelumText);
                $('td:eq(7)', nRow).html(sisaNpdText);
                $('td:eq(8)', nRow).html(nilaiNpdText);
                $('td:eq(9)', nRow).html(idAkun + inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "11%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiAnggaranTapd", "bSortable": true, "sWidth": "12%", sClass: "kanan", },
                {"mDataProp": "nilaiSpd", "bSortable": true, "sWidth": "12%", sClass: "kanan", },
                {"mDataProp": "nilaiSpdLs", "bSortable": true, "sWidth": "10%", sClass: "kanan", },
                {"mDataProp": "nilaiNpdSebelum", "bSortable": true, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "sisaNpd", "bSortable": true, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });
    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    getbanyaknpdrinci();
}
function enablerow(obj, idakun) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idakun)
}
function setdisabled(param, idakun) {
    $("#nilaiNpd" + idakun).attr("readonly", param);
    //  $("#sisaspj" + idbl).attr("readonly", param);
    if (param == false) {
        var nilaispj = accounting.unformat($("#nilaiNpd" + idakun).val(), ",");
        var sisaspj = accounting.unformat($("#sisaNpd" + idakun).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
        $("#nilaiNpd" + idakun).val(nilaiisian)
    }
    hitungnilainpd();
}

function pasangvalidatebesarperfield(idakun) {
    var nilainpd = accounting.unformat($("#nilaiNpd" + idakun).val(), ",");
    var sisanpd = accounting.unformat($("#sisaNpd" + idakun).val(), ",");
    var status = nilainpd > sisanpd ? false : true;
    // console.log(status+"  "+nilaispp+"  "+nilaispd);
    if (!status) {
        bootbox.alert("Nilai NPD tidak boleh lebih besar dari Sisa NPD", function() {
            $("#nilaiNpd" + idakun).val(accounting.formatNumber(sisanpd, 2, '.', ","));
            $("#nilaiNpd" + idakun).focus();
            hitungnilainpd();
        });
    } else {
        return true;
    }
}
function hitungnilainpd() {
    var searchIDs = $("#kegiatantable input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaiNpd" + data).val(), ","));
    })
    // $("#totalsisaspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalnpd").val(accounting.formatNumber(total, 2, '.', ","));

}


