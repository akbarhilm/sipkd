$(document).ready(function() {

});


function grid() {

    $("#banktable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#banktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/kontrak/json/listbankindukjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()}
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

                var textid = "<input type='hidden' id='id" + index + "' value='" + aData['idBankPfk'] + "' />";
                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaBankPfk'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeBankPfk'] + "' />";
                var textnamatf = "<input type='hidden' id='namatf" + index + "' value='" + aData['namaBankTransfer'] + "' />";
                var textkodetf = "<input type='hidden' id='kodetf" + index + "' value='" + aData['kodeBankTransfer'] + "' />";

                $('td:eq(0)', nRow).html(index + textnamatf + textkodetf);
                $('td:eq(3)', nRow).html(textid + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilketerangan(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBankPfk", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeBankTransfer", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaBankTransfer", "bSortable": true, sClass: "left"},
                {"mDataProp": "idBankPfk", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
