$(document).ready(function() {

});

var nobkuval;

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
            "sAjaxSource": getbasepath() + "/bku/json/liststbtljson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "nobku", value: nobkuval},
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
                
                var uraian = "Disetor sisa belanja dengan nomor STS " + aData['noSts'];
                var textnovalidasi = "<input type='hidden' id='novalidasi" + index + "' value='" + aData['noValidasi'] + "' />";
                var textnosetor = "<input type='hidden' id='nosetor" + index + "' value='" + aData['noSetor'] + "' />";
                var nilai = accounting.formatNumber(aData['nilaiSetor'], 2, '.', ",");
                var texuraian = "<input type='hidden' id='uraian" + index + "' value='" + uraian + "' />";
                var texttglvalidasi = "<input type='hidden' id='tglvalidasi" + index + "' value='" + aData['tglValidasi'] + "' />";
                var texttgldok = "<input type='hidden' id='tgldok" + index + "' value='" + aData['tglSts'] + "' />";
                
                $('td:eq(0)', nRow).html(index + textnovalidasi + texttglvalidasi);
                $('td:eq(5)', nRow).html(nilai + textnosetor + texuraian + texttgldok);
                $('td:eq(6)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            }, //noSetor, tglSetor, noSts, noValidasi, nilaiSetor
            "aoColumns": [
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglValidasi", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSts", "bSortable": true, sClass: "left"},
                {"mDataProp": "noValidasi", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sClass: "right"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
