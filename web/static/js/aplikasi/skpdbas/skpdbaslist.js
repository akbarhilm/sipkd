$(document).ready(function() {
 
    hanyaAngka();
  
});

function hanyaAngka(){
     $("#tahunBerlaku").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
     $("#tahunBerakhir").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
}

function grid() {
  
    $("#skpdBasTable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdBasTable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top">irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource":  getbasepath()+"/skpdbas/json/listskpdbasjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "idSkpd", value: $('#idSkpd').val()}
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
                var isceked = aData['isAktif'] == '1'?'checked':'';
                var cekaktif = "<input type='checkbox' name='isAktif"+index+"' id='isAktif"+index+"' disabled  "+isceked+" />";
                var pilih="<a class='icon-edit' href='"+getbasepath()+"/skpdbas/editskpdbas/"+aData['idSkpdBas']+"'  ></a>" /* - <!!-<a class='icon-remove' href='"+getbasepath()+"/skpdbas/delskpdbas/"+aData['idSkpdBas']+"' ></a>"*/;
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(cekaktif );
                $('td:eq(4)', nRow).html(pilih );
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpdBas", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "isAktif", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idSkpdBas", "bSortable": false, sClass: "center"}
            ]
        });
        
      $("div.portlet-body-tambah").html("<a  href='"+getbasepath()+"/skpdbas/addskpdbas/" + $('#idSkpd').val()+"' class='btn blue' style='float: right'>Tambah Data</a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}