$(document).ready(function() {

});

var jenistrans, nobkuedit, ketaddedit;

function grid() {

    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listjgbp",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "kodetrx", value: jenistrans},
                {name: "tahun", value: $('#tahun').val()},
                {name: "nobku", value: nobkuedit},
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
                
                var nilai = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var textnobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBKU'] + "' />";
                var nilaiterima = "<input type='text' id='nilaiterima" + index + "'  class='inputmoney'  value='" + nilai + "' readOnly='true' />";
                var textidbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                
                $('td:eq(0)', nRow).html(index + textnobku);
                $('td:eq(4)', nRow).html(nilaiterima);
                $('td:eq(5)', nRow).html(textidbas + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "noDok", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": true, sClass: "right"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
