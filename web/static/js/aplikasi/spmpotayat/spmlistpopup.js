$(document).ready(function() {
   grid(); 
});

 

function ambilspm(id) {
    $('#nospm', window.parent.document).val(id).change();

    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath()+"/spmpotayat/json/listspmjson";
    $("#spmtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#spmtable').dataTable({
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
                        {name: "spm", value: $('#spm').val()}
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
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilspm(" + aData['idSpm'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpp", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpm", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


