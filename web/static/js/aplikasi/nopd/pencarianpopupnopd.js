$(document).ready(function() {
  
  // grid(); 
  
});

 

function ambilPenerimaan(id) {
    
   
    $('#idObjekPajak',window.parent.document).val( $("#idObjekPajak").val()  &" - " & $("#idObjekPajak").text());


    $('#nopd', window.parent.document).val(id).change();
    

    parent.$.fancybox.close();

}
function grid() {
    

   
    var urljson = getbasepath()+"/nopd/json/listnopd";
    $("#pencarianNopdTable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#pencarianNopdTable').dataTable({
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
            "aaSorting": [[1, "asc"]], "fnDrawCallback": function () {
            
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idObjekPajak", value: $('#idObjekPajak').val()},
                {name: "nopd", value: $("#nopd").val()},
                {name: "namaObjekPajak", value: $("#namaObjekPajak").val()}
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
            }, "fnInitComplete": function() {
               
    
               
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                    $('td:eq(0)', nRow).html(index);
              var nopd = aData['nopd'];
              $('td:eq(6)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick=ambilPenerimaan('"+nopd+"')></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nopd", "bSortable": false, sClass: "center"},
                {"mDataProp": "nopd", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "npwpd", "bSortable": false, sDefaultContent: "-"},
                        {"mDataProp": "namaObjekPajak", "bSortable": false, sDefaultContent: "-"},
            {"mDataProp": "namaWajibPajak", "bSortable": false, sDefaultContent: "-"},
               {"mDataProp": "alamatObjekPajak", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "nopd", "bSortable": false, sClass: "center"}
            ]
            
            
        });
 
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


