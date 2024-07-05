$(document).ready(function() {

    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val())
    }
    gridspprinci();
    
    setCurrentDate();

});

function setCurrentDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }

    var bulan = dd + "/" + mm + "/" + yy;

    $('#tglSpm').val(bulan);

}

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/spmbiayals/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/sppbiaya/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spmbiayals/indexspmbiayals/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmbiayals/indexspmbiayals");
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
    var urljson = getbasepath() + "/spmbiayals/json/getlistspdbiaya";
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
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
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
                        console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
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
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'     id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                //var tglspp = getTanggal(aData['tglSpp']);
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text' readonly='true'  id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";

                $('td:eq(0)', nRow).html(index + idspd);
               // $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(inputspp + nilaispphidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idspp", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "cAkun", "bSortable": true, "sWidth": "20%"},
                {"mDataProp": "nAkun", "bSortable": true, "sWidth": "25%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "20%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
