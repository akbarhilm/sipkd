$(document).ready(function() {

    gridsp2d();
});

function gridsp2d() {

    if (typeof myTable == 'undefined') {
        myTable = $('#sp2dtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbkusp2d",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "nobku", value: $("#noBKU").val()},
                {name: "idtransaksi", value: $("#idTransaksi").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"}
            ]

        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


function update() {

    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var idtransaksi = $("#idTransaksi").val();
    var fileinbox = $('#inboxFile').val();


    var varurl = getbasepath() + "/bku/json/prosesupdatesp2d";
    var dataac = [];
   
    var datajour = {
        tahun: tahun,
        idskpd: idskpd,
        idtransaksi: idtransaksi,
        fileinbox: fileinbox,
        nobku: $("#noBKU").val()

    }
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

        //$("#keteranganKegPop").val("");
        //$("#idKegpop").val("");
        //clearrowspj();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
    });

}

