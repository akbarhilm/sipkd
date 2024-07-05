$(document).ready(function() {
   
});

// global variable
var jenisdok;

function grid() {
    //console.log("listnodokume.js == masuk grid()");
    var jenis;
    
    if(jenisdok == "SP2D"){
        jenis = "JO";
    } else if(jenisdok == "SPJ"){
        jenis = "JJ";
    }
    
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
            "sAjaxSource": getbasepath()+"/bkupencarian/json/listnomordok",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "nodokumen", value: $('#nomor').val()},
                        {name: "tahun", value: $('#tahun').val()},
                        {name: "jenisdok", value: jenis},
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
                var textnodok = "<input type='hidden' id='nodok" + index + "' value='" + aData['noBukti']+"' />";
                var nilai = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",")
                $('td:eq(0)', nRow).html(index);
                $('td:eq(7)', nRow).html(nilai);
                $('td:eq(8)', nRow).html(textnodok+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "noBKU", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "beban", "bSortable": true, sClass: "center"},
                {"mDataProp": "jenis", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    
}
