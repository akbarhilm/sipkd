$(document).ready(function() {

});

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
            "sAjaxSource": getbasepath() + "/bankpfk/json/getlistbankinduk",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "nama", value: $('#nama').val()},
                {name: "kode", value: $('#kode').val()}
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

                var textidbank = "<input type='hidden' id='idbank" + index + "' value='" + aData['idBank'] + "' />";
                var textkodebank = "<input type='hidden' id='kodebank" + index + "' value='" + aData['kodeBankTransfer'] + ' / ' + aData['namaSkpd'] + "' />";
                var textnamabank = "<input type='hidden' id='namabank" + index + "' value='" + aData['namaBankTransfer'] + "' />";
                var textalamatbank = "<input type='hidden' id='alamatbank" + index + "' value='" + aData['alamatBank'] + "' />";

                $('td:eq(0)', nRow).html(index + textidbank + textkodebank + textnamabank + textalamatbank);
                $('td:eq(3)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilprogram(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBank", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "kodeBankTransfer", "bSortable": false, "sWidth": "10%", sClass: "center"},
                {"mDataProp": "namaBankTransfer", "bSortable": false, "sWidth": "30%", sClass: "left"},
                {"mDataProp": "alamatBank", "bSortable": false, "sWidth": "50%", sClass: "left"},
                {"mDataProp": "idBank", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
