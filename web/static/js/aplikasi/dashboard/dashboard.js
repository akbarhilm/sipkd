

$(document).ready(function() {
    getsaldo();
    gridpenerimaan();
    gridpengeluaran();
    
    
});

function getsaldo(){
    $.getJSON(getbasepath()+"/dash/json/saldoakhir",
                function(data) {
                   $("#sa").append(accounting.formatNumber(data.aaData[0]['SALDOAKHIR'], 2, '.', ","));
                   $("#spn").append(accounting.formatNumber(data.aaData[0]['SALDOAKHIRPENERIMAAN'], 2, '.', ","));
                   $("#spg").append(accounting.formatNumber(data.aaData[0]['SALDOAKHIRPENGELUARAN'], 2, '.', ","));
                   
                }
         );
}
function gridpenerimaan(){
    
   
   
       $('#penerimaan').dataTable({
            "bPaginate": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": false,
            "bDestroy": true,
            //"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            //"iDisplayLength": 10,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/dash/json/listpenerimaan",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) { //nampilin table footer total
               
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['SALDOAKHIR'], 2, '.', ","));
                
                //$('td:eq(4)', nRow).html(pilih);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "NAMAREKENING", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMAREKENING", "bSortable": false, sClass: "center"},
                {"mDataProp": "SALDOAKHIR", "bSortable": false, sClass: "right"}
                
            ]
        });
        // $("div.top").html("<a class='fancybox fancybox.iframe btn blue' href='" + getbasepath() + "/sekolahpopup/listsekolahpa?target=_top'  style='float: right'>Tambah Data</a>");

  
}

function gridpengeluaran(){
    
   
   
       $('#pengeluaran').dataTable({
            "bPaginate": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": false,
            "bDestroy": true,
            //"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            //"iDisplayLength": 10,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/dash/json/listpengeluaran",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) { //nampilin table footer total
               
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['SALDOAKHIR'], 2, '.', ","));
                
                //$('td:eq(4)', nRow).html(pilih);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "NAMAREKENING", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMAREKENING", "bSortable": false, sClass: "center"},
                {"mDataProp": "SALDOAKHIR", "bSortable": false, sClass: "right"}
                
            ]
        });
        // $("div.top").html("<a class='fancybox fancybox.iframe btn blue' href='" + getbasepath() + "/sekolahpopup/listsekolahpa?target=_top'  style='float: right'>Tambah Data</a>");

  
}
