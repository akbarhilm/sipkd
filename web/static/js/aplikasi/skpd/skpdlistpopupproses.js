$(document).ready(function() {
   grid(); 
   
});

 

function ambilskpd(id) {
    $('#skpd', window.parent.document).val($("#namaSkpd" + id).val());
    $('#idskpd', window.parent.document).val(id).change();
   // alert(id);
    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath()+"/common/json/listskpdjsonwil";
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
            //"sScrollY": ($(window).height() * 108 / 100),1662
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
               
                aoData.push(
                        {name: "skpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "kodewilayah", value: $('#kodewilayah').val()} 
                        
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
                var kproses = $('#kodewilayah').val();
                //alert(kproses);
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['namaSkpd'] + "' />";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaskpd + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": true, sDefaultContent: "-"},
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


