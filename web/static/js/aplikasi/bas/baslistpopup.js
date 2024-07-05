$(document).ready(function() {
   grid(); 
});

 

function ambilBas(id) {
    $('#namaAkun', window.parent.document).val($("#namaAkun" + id).val());
    $('#idAkun', window.parent.document).val(id).change();
    $('#pagubtl', window.parent.document).val(0);
    $('#sisaspd', window.parent.document).val(0);

    parent.$.fancybox.close();
    
}
function grid() {
   if(!parent.$('#idSkpd').val()){
           parent.$('#idSkpd').val(0);
   }
    var urljson = getbasepath()+"/bas/json/listbaspopupjson";
    $("#bastable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#bastable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namaAkun", value: $('#akunParam').val()},
                {name: "idSkpd", value: parent.$('#idSkpd').val()}
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
                var textnamaBas = "<input type='hidden' id='namaAkun" + aData['idAkun'] + "' value='"+ aData['kodeAkun'] + " / " + aData['namaAkun'] +"' />";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaBas + "<span class='icon-ok-sign linkpilihan' onclick='ambilBas(" + aData['idAkun'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idAkun", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idAkun", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


