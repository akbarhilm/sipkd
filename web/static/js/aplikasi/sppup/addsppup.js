$(document).ready(function () {
    gridspprinci();
    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val())
    }
    /* $('#pilihall').click(function () {
     $(':checkbox').prop('checked', this.checked);
     cekall(this);
     hitungnilaispp();
     });
     $("#kodekegiatanfilter").keyup(function () {
     var panjang = $(this).val().length;
     if (panjang > 3) {
     gridspprinci();
     gettotalspddanspp( );
     }
     });
     $("#nospdfilter").keyup(function () {
     var panjang = $(this).val().length;
     if (panjang > 1) {
     gridspprinci();
     gettotalspddanspp( );
     }
     });*/
    $("#refsppup").validate();
});
function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/sppup/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/sppup/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $('#id').val()},
    function (result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppup/indexspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/sppup/indexspppup");
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
    var urljson = getbasepath() + "/sppup/json/getlistspdbl";
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
            "sDom": '<"toolbar">flrtip',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "iDisplayLength": 25,
            "fnDrawCallback": function () {
                formatnumberonkeyup();
                 //  $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                getbanyakspdrinci( );
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                try {

                    var pageTotal = 0;
                    var spptotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                       // console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
                    }


                    $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                    $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))

                } catch (e) {
                    console.log(e)
                }
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
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var nilaispd =  accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdhidden = "<input type='hidden' name='nilaispdorg" + index + "'  class='inputmoney'   id='nilaispdorg" + index + "' value='" + aData['nilaiSpd'] + "' />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'  class='inputmoney'   id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var tglspp = getTanggal(aData['tglSpd']);
                var nilaispp = aData['nilaiSpp'];//accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text'   id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";

                $('td:eq(0)', nRow).html(index + idspd);
                $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(nilaispd + nilaispdhidden);
                $('td:eq(4)', nRow).html(inputspp + nilaispphidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "tglSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "25%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "25%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaispp" + idbl).attr("readonly", param);
    $("#nilaispd" + idbl).attr("readonly", param);
    if (param == false) {
        var nilaispp =  $("#nilaispp" + idbl).autoNumeric('get');//accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaispd =  $("#nilaispd" + idbl).autoNumeric('get');//accounting.unformat($("#nilaispd" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = $("#nilaiSpp" + idbl).autoNumeric('get');// accounting.unformat($("#nilaiSpp" + idbl).val(), ",") * 10;
    var nilaispd = $("#nilaispdorg" + idbl).autoNumeric('get'); // accounting.unformat($("#nilaispdorg" + idbl).val(), ",");
    var strtotalAngaran = $("#totalAngaran").val();
    var totalAngaran = strtotalAngaran.split('.').join('') // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    var totalspp =  $("#totalspp").val();//=$("#totalspp" + idbl).autoNumeric('get');// accounting.unformat($("#totalspp").val(), ",");
    var status = parseFloat(nilaispp) <= parseFloat(nilaispd) && parseFloat(nilaispp) <= parseFloat(totalAngaran) && parseFloat(totalspp) <= parseFloat(totalAngaran);
   //  console.log((parseFloat(nilaispp) <= parseFloat(nilaispd))+" (parseFloat(nilaispp) <= parseFloat(totalAngaran)) "+parseFloat(nilaispp) <= parseFloat(totalAngaran) + "  " + status + "  " + nilaispp + "  " + nilaispd + " " + totalAngaran);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function () {
            $("#nilaiSpp" + idbl).autoNumeric('set', 0);
            //$("#nilaiSpp" + idbl).val(nilaispd);
            $("#nilaiSpp" + idbl).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    
    var total = 0;
    $(".sppnilai").each(function (index, item) {
        total += parseFloat(  $(item).autoNumeric('get') );
    });
     
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/sppup/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                kodekegiatan: $("#kodekegiatanfilter").val(),
                spdno: $("#nospdfilter").val()
            },
    function (result) {
        $('#totalspd').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
    });

}
 