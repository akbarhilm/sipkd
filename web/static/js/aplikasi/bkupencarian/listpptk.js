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
            "sAjaxSource": getbasepath()+"/bkupencarian/json/listpptkjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "nip", value: $('#nip').val()},
                        {name: "nama", value: $('#nama').val()},
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
                
                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaPptk']+"' />";
                var textnip = "<input type='hidden' id='nip" + index + "' value='" + aData['nipPptk']+"' />";
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['nipPptk']+"/"+aData['namaPptk'] + "' />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnama+textket+textnip+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nipPptk", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "nipPptk", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "left"},
                {"mDataProp": "nipPptk", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    
}
