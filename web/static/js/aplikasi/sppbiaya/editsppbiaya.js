$(document).ready(function() {
    gridspprinci();
    if ($("#idskpd").val()) {
      getpagudanspd($("#idskpd").val())
    }
     $('#pilihall').click(function () {
        $(':checkbox').prop('checked', this.checked);
        cekall(this);
        hitungnilaispp();
    });
    
    
    $("#nospdfilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang >= 1) {
            gridspprinci();
                        gettotalspddanspp( );
        }
    });

 });

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/biaya/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/biaya/json/getlistspdbiayabanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/biaya/listbiaya/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/biaya/listbiaya");
    }

}
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#spprincitable :input[type='text']").attr("readonly", false);
    } else {
        $("#spprincitable :input[type='text']").attr("readonly", "readonly");
    }
}
function gridspprinci() {
    var urljson = getbasepath() + "/biaya/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#spprincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;
                var spptotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                    }
                }

                $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))

            }
            ,
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
                var idspd = "<input type='hidden' id='idspd" + aData['idBiaya'] + "'   name='idspd" + aData['idBiaya'] + "'  value='" + aData['idSpd'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + aData['idBiaya'] + "'   name='idakun" + aData['idBiaya'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSpp'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                if (isceked === 'checked') {
                    readonly = '';
                }
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBiaya'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBiaya'] + ") " + isceked + " />";
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' />"
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBiaya'] + "' id='nilaispd" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                var sppsebelum = accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var sppsebelumtext = "<input type='text' name='nilaiSppSebelum" + aData['idBiaya'] + "' id='nilaiSppSebelum" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + sppsebelum + "' />"
                var sppsisa = accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var sppsisatext = "<input type='text' name='nilaiSppSisa" + aData['idBiaya'] + "' id='nilaiSppSisa" + aData['idBiaya'] + "'  class='inputmoney'  readonly='true'   value='" + sppsisa + "' />"
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + aData['idBiaya'] + "' id='nilaispp" + aData['idBiaya'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBiaya'] + ");hitungnilaispp()'   value='" + nilaispp + "'   />"

                $('td:eq(0)', nRow).html(index + idspd + idakun);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(4)', nRow).html(nilaispdtext);
                $('td:eq(5)', nRow).html(sppsebelumtext);
                $('td:eq(6)', nRow).html(sppsisatext);
                $('td:eq(7)', nRow).html(nilaispptext);
                $('td:eq(8)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBiaya", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "10%"},
                {"mDataProp": "idBiaya", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });


    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakspdrinci( );
}
function enablerow(obj, idbiaya) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbiaya)
}
function setdisabled(param, idbtl) {
    $("#nilaispp" + idbtl).attr("readonly", param);
    $("#nilaispd" + idbtl).attr("readonly", param);
    $("#nilaiSppSisa" + idbtl).attr("readonly", param);
    if (param == false) {
        var nilaispp = accounting.unformat($("#nilaispp" + idbtl).val(), ",");
        var nilaispd = accounting.unformat($("#nilaispd" + idbtl).val(), ",");
        var sppsisa = accounting.unformat($("#nilaiSppSisa" + idbtl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? sppsisa : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbtl).val(nilaiisian)
    }
    hitungnilaispp();
}
function pasangvalidatebesarperfield(idbl) {
   var nilaispp = accounting.unformat($("#nilaispp" + idbiaya).val(), ",");
    var nilaispd = accounting.unformat($("#nilaispd" + idbiaya).val(), ",");
    var strtotalAngaran = $("#totalAngaran").val();
    var totalAngaran = strtotalAngaran.split('.').join('') // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    var totalspp = accounting.unformat($("#totalspp" + idbiaya).val(), ",");
    var status = nilaispp <= nilaispd && nilaispp < totalAngaran && totalspp <= totalAngaran;
    console.log((nilaispp < totalAngaran) + "  " + status + "  " + nilaispp + "  " + nilaispd + " " + totalAngaran);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function() {
            $("#nilaispp" + idbiaya).val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $("#nilaispp" + idbiaya).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/sppbiaya/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                spdno: $("#nospdfilter").val()
            },
    function (result) {
        $('#totalspd').val(accounting.formatNumber(  result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber( result.V_SPP, 2, '.', ",") );
    });

}
 