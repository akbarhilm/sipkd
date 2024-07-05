$(document).ready(function() {

});

var  ketjenis, ketidsetor;

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
            "sAjaxSource": getbasepath() + "/setor/json/listkegiatanbtljson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "idsetor", value: ketidsetor},
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
               
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['noBku'] + "/" + aData['noBuktiDok'] + "' />";
                var textnobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBku'] + "' />";
                
                $('td:eq(0)', nRow).html(index + textnobku);
                $('td:eq(5)', nRow).html(textket + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglBku", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBku", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBuktiDok", "bSortable": true, sClass: "left"},
                {"mDataProp": "ketBku", "bSortable": true, sClass: "left"},
                {"mDataProp": "noBku", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
