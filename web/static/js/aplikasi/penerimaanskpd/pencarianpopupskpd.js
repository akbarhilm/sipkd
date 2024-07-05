$(document).ready(function() {
  
   grid(); 
  
});

 

function ambilPenerimaan(id) {
    
   
    $('#idObjekPajak',window.parent.document).val( $("#idObjekPajak").val()  &" - " & $("#idObjekPajak").text());


    $('#nopd', window.parent.document).val(id).change();
    

    parent.$.fancybox.close();

}
function grid() {
    

   
    var urljson = getbasepath()+"/penerimaanskpd/json/listskpd";
    $("#pencarianPenerimaanSkpdTable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#pencarianPenerimaanSkpdTable').dataTable({
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
                        {name: "noSkpd", value: $('#noSkpd').val()},
                {name: "nopd", value: $("#nopd").val()},
                {name: "namaObjekPajak", value: $("#namaObjekPajak").val()},
                 {name: "tahunPajak", value: $("#tahunPajak").val()}
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
                 var nilaiPenerimaan;
                if (aData['nilaiPenerimaan'] == 0) {
                    nilaiPenerimaan = "placeholder='0.00'";

                } else {
                    nilaiPenerimaan = "value='" + aData['nilai'] + "'";

                }
               var inputForm = "<input type='text' readonly='true' name='inputMoney" + index + "' " + nilaiPenerimaan + " id='inputMoney" + index + "' class='inputmoney ' onkeyup=inputMoney() />";
               
                $('td:eq(6)', nRow).html(inputForm);
              $('td:eq(7)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick=ambilPenerimaan('"+nopd+"')></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nopd", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSkpd", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "dateSkpd", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "nopd", "bSortable": false, sDefaultContent: "-"},
               
                        {"mDataProp": "namaObjekPajak", "bSortable": false, sDefaultContent: "-"},
          
               {"mDataProp": "alamatObjekPajak", "bSortable": false, sDefaultContent: "-"},
                 {"mDataProp": "nilai", "bSortable": false, sDefaultContent: "-"},
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


