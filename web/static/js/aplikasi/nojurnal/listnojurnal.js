$(document).ready(function() {
   
});

function grid() {
    //bootbox.alert("CEK RADIO BUTTON  == "+nilairadio);
    //bootbox.alert("CEK N_AKUN  == "+$('#nama').val());
    
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
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/nojurnal/json/listnojurnal",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                        {name: "kodegrup", value: $('#kodegrup').val()}
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
                var textnoJur = "<input type='hidden' id='noJur" + index + "' value='" + aData['noJurnal']+"' />";
                $('td:eq(0)', nRow).html(index);
                
                $('td:eq(2)', nRow).html(textnoJur+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noJurnal", "bSortable": true, sClass: "center"},
                {"mDataProp": "noJurnal", "bSortable": true, sClass: "left"},
                {"mDataProp": "noJurnal", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    
}

function cekrb(nilai){
    nilairadio = nilai;
    grid();
 
}
