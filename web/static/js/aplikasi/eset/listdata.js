/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var opt = "";
$(document).ready(function() {

    timepick(1);
    $('#tmp').attr('disabled', 'disabled');
    $('#tmp2').attr('disabled', 'disabled');
    wilayah();
});

function timepick(id) {
    $('#tglb').val('');
    $('#tglb').datepicker('remove');
     $('#tmp').attr('disabled', 'disabled');
      $('#tmp2').attr('disabled', 'disabled');
    if (id == 9) {
        $('#tglb').datepicker({
            format: "mm/yyyy",
            viewMode: "months",
            minViewMode: "months"
        });

    } 
    if(id==1) {
        
        $("#tglb").datepicker({
           // startDate: new Date(tahun, 0, 1),
          //  endDate: new Date(tahun, 11, 31)
        });
    }
    
    if(id==0) {
      
        $("#tglb").datepicker({
           format: "yyyy",
            viewMode: "years",
            minViewMode: "years"
        });
    }
}
function wilayah() {
    var i = 0;

    var url = getbasepath() + "/eset/json/wilayah"
    $.getJSON(url, function(data) {

        for (i; i < data.iTotalRecords; i++) {
            opt += "<option value='" + data.aaData[i].C_WIL_SP2DPROSES + "'>" + data.aaData[i].N_WIL_SP2DPROSES + "</option>";

        }
        $("#wil").html(opt);
    })

}
var myTable;
function grid() {
    var tglb='';
    var sb = $("#cbBankStatus").val();
    
     if(sb==9){
         var tgl = $("#tglb").val();
    var m = tgl.substr(0,2);
    var y = tgl.substr(3,4);
   
     tglb = y+m;
    }
    
    if(sb==1){
        var tgl = $("#tglb").val();
    var d = tgl.substr(0,2);
    var m = tgl.substr(3,2);
    var y = tgl.substr(6);
     tglb = y+m+d;
    }
    
     if(sb==0){
        var tgl = $("#tglb").val();
 
    var y = tgl.substr(0,4);
     tglb = y;
    }
    //$("#fungsitable").show();
    //if (typeof myTable == 'undefined') {
         $('#fungsitable').dataTable({
            "bPaginate": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": false,
            "bInfo": false,
            "bDestroy": true,
            //"iDisplayLength": [[25, 50, 100, 10000], [25, 50, 100, 10000]],
            // "aLengthMenu": 1,
            "iDisplayStart": 0,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": true,
            "sAjaxSource": getbasepath() + "/listdatabank/json/listsp2dsc",
            "aaSorting": [[0, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "statusbank", value: sb},
                {name: "tglbayar", value: tglb},
                {name: "wilayah", value: $('#wil').val()},
                {name: "nosp2d", value: $('#nosp2d').val()},
                {name: "kodeskpd", value: $('#kodeskpd').val()}
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
            "fnRowCallback": function(nRow, aData, iTotalRecords, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                
               
                var nilai = accounting.formatNumber(aData['NILAI']);
               
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(nilai);
               
                //$('td:eq(3)', nRow).html(rouk + kour + pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "TANGGAL_VALIDASI", "bSortable": false, sClass: "center"},
                {"mDataProp": "TANGGAL_VALIDASI", "bSortable": false, sClass: "center", sWidth: "7%"},
                {"mDataProp": "NO_SP2D", "bSortable": false, sClass: "center", sWidth: "15%"},
                {"mDataProp": "SKPD", "bSortable": false, sClass: "center"},
                {"mDataProp": "NILAI", "bSortable": false, sClass: "right"},
                {"mDataProp": "BANK_TUJUAN", "bSortable": false, sClass: "center"},
                {"mDataProp": "NOREK_TUJUAN", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMA_TUJUAN", "bSortable": false, sClass: "center", sWidth: "15%"},
                {"mDataProp": "NOREK_PENGIRIM", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMA_PENGIRIM", "bSortable": false, sClass: "center"},
                {"mDataProp": "BIT11", "bSortable": false, sClass: "center"},
                {"mDataProp": "BIT62", "bSortable": false, sClass: "center"},
                {"mDataProp": "KODE_RESPON", "bSortable": false, sClass: "center"},
            ]
        });

    }
   
$(function() {
    $('#tglb').change(function() {
        
            $('#tmp').removeAttr('disabled');
             $('#tmp2').removeAttr('disabled');
        
    });
    
     $('#cbBankStatus').change(function() {
        if(this.value == 0){
           $("#fwil").hide();
        }else{
             $("#fwil").show();
        }
        
    });
});

function cetak() {
   var tglb='';
    var sb = $("#cbBankStatus").val();
    
    if(sb==9){
         var tgl = $("#tglb").val();
    var m = tgl.substr(0,2);
    var y = tgl.substr(3,4);
   
     tglb = y+m;
    }
    
    if(sb==1){
        var tgl = $("#tglb").val();
    var d = tgl.substr(0,2);
    var m = tgl.substr(3,2);
    var y = tgl.substr(6);
     tglb = y+m+d;
    }
    
     if(sb==0){
        var tgl = $("#tglb").val();
 
    var y = tgl.substr(4);
     tglb = y;
    }
    
    var wil = $('#wil').val();
    
    
   window.location.href= getbasepath() + "/listdatabank/json/prosescetak?wilsp2d="+wil+"&tglbayar="+tglb+"&statusbank="+sb;
  
    
}
