$(document).ready(function() {
    grid();
});

var statusSaldo = 0;
var totalindex = 0;

function simpan() {

    var varurl = getbasepath() + "/saldoAwalBku/json/prosessimpan";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < totalindex; i++) { // list
        var id = i + 1;

        var pararr = {
            idbas: $("#idBas" + id).val(),
            kodeakun: $("#kodeakun" + id).val(),
            nilaisaldo: $("#nilaiSaldo" + id).val(),
            status: $("#addoredit" + id).val()
        };
        nilailist[i] = pararr;
    }

    var datajour = {
        tahun: $('#tahun').val(),
        idskpd: $('#idskpd').val(),
        nilailist: nilailist

    };
    dataac = datajour;

    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        //grid();
        bootbox.alert("Data Saldo Kas Berhasil Disimpan");
    });

}

function grid() {

    if (typeof myTable == 'undefined') {
        myTable = $('#jourskpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/saldoAwalBku/json/listsaldoawal",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                formatnumberonkeyup();
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idskpd", value: $('#idskpd').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;

                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSaldo']);
                    }
                }
                //console.log(" pageTotal " + pageTotal)
                $("#totalsaldo").val(accounting.formatNumber(pageTotal, 2, '.', ","));

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

                statusSaldo = statusSaldo + aData['kodeStatus'];
                totalindex = totalindex + 1;

                var idBas = "<input type='hidden' id='idBas" + index + "'  name='idBas" + index + "' value='" + aData['idBas'] + "' />";
                var kodeakun = "<input type='hidden' id='kodeakun" + index + "'  name='kodeakun" + index + "' value='" + aData['kodeAkun'] + "' />";
                var nilaisaldo = "<input type='text' id='nilaiSaldo" + index + "' name='nilaiSaldo" + index + "'   value='" + aData['nilaiSaldo'] + "'  class='inputmoney sppnilai'  onkeyup= hitungtotal() />";
                //var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:400px;' >" + aData['namaAkun'] + "</textarea>";
                var addoreditval;

                if (aData['nilaiSaldo'] > 0) {
                    addoreditval = "EDIT";
                } else {
                    addoreditval = "ADD";
                }
                var addoredit = "<input type='hidden'  id='addoredit" + index + "'  name='addoredit" + index + "'  value='" + addoreditval + "'  />";

                /*if (statusSaldo > 0){
                 $('#btnsimpan').attr("disabled", true);
                 } */

                $('td:eq(0)', nRow).html(index + idBas + kodeakun);
                $('td:eq(3)', nRow).html(nilaisaldo + addoredit);
                //$('td:eq(2)', nRow).html(namaakuntext);

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "idBas", "sWidth": "5%", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeAkun", "sWidth": "15%", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaAkun", "sWidth": "65%", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiSaldo", "sWidth": "15%", "bSortable": false, sClass: "rihgt"}

            ]
        });


    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getStatusSaldo();
}


function hitungtotal() {
    var total = 0;
    var i;

    for (i = 1; i <= totalindex; i++) {
        total = total + parseFloat(accounting.unformat($('#nilaiSaldo' + i).val(), ","));
    }

    $("#totalsaldo").val(accounting.formatNumber(total, 2, '.', ","));
}

function getStatusSaldo() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/saldoAwalBku/json/getStatusSaldo", {tahun: tahun, idskpd: idskpd},
    function(result) {
        //console.log("result getStatusSaldo = " + result);

        if (result > 0) {
            $('#btnsimpan').attr("disabled", true);
            bootbox.alert("Data Tidak Bisa Diubah Karena Sudah Proses TUTUP BKU");

        } else {
            $('#btnsimpan').attr("disabled", false);
        }

    });
}