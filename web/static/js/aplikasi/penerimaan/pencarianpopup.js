$(document).ready(function() {
   grid(); 
  
});

 

function ambilPenerimaan(id) {
  
    $('#noLoket', window.parent.document).val(id).change();
    

   

   parent.$.fancybox.close();

}
function grid() {
    

   
    var urljson = getbasepath()+"/pajak/json/listpenerimaan";
    $("#pencarianPajakTable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#pencarianPajakTable').dataTable({
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
              
             $(".inputmoney").priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15,centsLimit: 0});
     
             
              
//           //  gettotalspddanspp( );
               // getbanyakspdrinci();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idLoket", value: $('#idLoket',window.parent.document).val()},
                {name: "idSts", value: $("#idSts").val()},
                {name: "noValidasi", value: $("#noValidasi").val()}
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
                var textnamaskpd = "<input type='hidden' id='noPenerimaan" + aData['noPenerimaan'] + "' value='"+ aData['noPenerimaan'] +"' />";
                $('td:eq(0)', nRow).html(index);
              var noPenerimaan = aData['noPenerimaan'];
                $('td:eq(4)',nRow).html("<input type='text' class='inputmoney' readonly='true' value='"+ aData['nilaiPenerimaan'] +"'>")
                $('td:eq(5)', nRow).html(textnamaskpd + "<span class='icon-ok-sign linkpilihan' onclick=ambilPenerimaan('"+noPenerimaan+"')></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPenerimaan", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSts", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "noPenerimaan", "bSortable": false, sDefaultContent: "-"},
                        {"mDataProp": "idValidasi", "bSortable": false, sDefaultContent: "-"},
            {"mDataProp": "nilaiPenerimaan", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "idPenerimaan", "bSortable": false, sClass: "center"}
            ]
            
            
        });
 
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


