/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    var tahun = $("#tahun").val()
    $("#tglv").datepicker({
     
        startDate: new Date(tahun, 0, 1),
        endDate: new Date(tahun, 11, 31)});
        
    wilayah();
});
var banyak;
var opt = "";
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
var tglv
var banyak;
function grid( ) {
    var tgl = $("#tglv").val();
    var d = tgl.substr(0,2);
    var m = tgl.substr(3,2);
    var y = tgl.substr(6);
     tglv = y+m+d;
    $("#fungsitable").show();
   // if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bDestroy":true,
            //"iDisplayLength": [[25, 50, 100, 10000], [25, 50, 100, 10000]],
           // "aLengthMenu": 1,
            "iDisplayStart":0,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/eset/json/listsp2d",
            "aaSorting": [[0, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "tahun", value: $('#tahun').val()},
                {name: "tglvalid", value: tglv},
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
                banyak = this.fnPagingInfo().iEnd;
                var isceked = aData['isAktif'] == '1' ? 'checked' : '';
                var nilai = accounting.formatNumber(aData['NILAISP2D']);
               var id = "<input type='hidden' value ="+aData['IDSP2D']+" id=id"+index+">";
                var view = "<a href="+getbasepath()+"/popup/sp2d?target='_top'&idsp2d="+aData['IDSP2D']+"&tahun="+$('#tahun').val()+"&tglvalid="+tglv+"&wilayah="+aData['WILSP2D']+" class='fancybox fancybox.iframe'><i class='icon-zoom-in linkpilihan'></i></a>"
                var pilih = "<input type='checkbox' name='cek' onclick='test();' id='check"+index+"'>";
                $('td:eq(0)', nRow).html(index);
                 $('td:eq(5)', nRow).html(nilai);
                $('td:eq(8)', nRow).html(view);
                 $('td:eq(9)', nRow).html(pilih+id);
                //$('td:eq(3)', nRow).html(rouk + kour + pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "IDSP2D", "bSortable": false, sClass: "center"},
                {"mDataProp": "IDSP2D", "bSortable": false, sClass: "center",sWidth:"7%"},
                {"mDataProp": "NODOKSP2D", "bSortable": false, sClass: "center",sWidth:"15%"},
                {"mDataProp": "KODESKPD", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMASKPD", "bSortable": false, sClass: "center"},
                {"mDataProp": "NILAISP2D", "bSortable": false, sClass: "right"},
                {"mDataProp": "BIT11", "bSortable": false, sClass: "center"},
                {"mDataProp": "PESANBANK", "bSortable": false, sClass: "center",sWidth:"15%"},
                {"mDataProp": "IDSP2D", "bSortable": false, sClass: "center"},
                {"mDataProp": "IDSP2D", "bSortable": false, sClass: "center"}
            ]
        });
        sumsp2d();
        $("div.top").html("<label>Select All&nbsp;&nbsp;</label><input type='checkbox' id='selector' onclick='selectall();'>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn green' onclick='proses();'>&nbsp;Proses&nbsp;</button>");
        //$("div.midtop").html("");
   // }
//    else
//    {
//        myTable.fnClearTable(0);
//        myTable.fnDraw();
//    }
}
function sumsp2d() {
   

   
    $.getJSON(getbasepath() + "/eset/json/sumsp2d", {tahun: $('#tahun').val(),tglvalid : tglv,wilayah: $('#wil').val()},
    function(data) {

        
        $("#sumfooter").val(accounting.formatNumber(data.aaData[0].SUMSP2D));
       
    })

}
function test(){
    $('input[name=cek]').each(function () {
    
     if($('input[name=cek]:checked').length==$('input[name=cek]').length){
         $('#selector').prop('checked',true);
     }else{
         $('#selector').prop('checked',false);
     }
   
});
}


function selectall(){
    if($('#selector').is(':checked')){
        $('input[name=cek]').prop('checked',true);
    }else{
    $('input[name=cek]').prop('checked',false);
    }
}

function proses(){
    var url = getbasepath() + "/eset/json/prosessp2d";
    var listid = [];
    var dataac = [];
    var indexarr=0;
    var banyakcek = $('input[name=cek]').length;
    for(i=1;i<=banyakcek;i++){
        
        if($("#check"+i).is(':checked')){
            
             var par = {
            idsp2d:$("#id"+i).val()
        };
        listid[indexarr]=par;
        indexarr+=1;
        
        }     
        
    }
    var datajour = {
        listid:listid
    };
    dataac = datajour;
    return   $.ajax({
            type: "POST",
            url: url, contentType: "text/plain; charset=utf-8",
            data: JSON.stringify(dataac),
            headers: {
                'Accept': 'text/plain',
                'Content-Type': 'application/json'
            },
           success: function () {
           bootbox.alert('Berhasil di proses!');
           grid();
        },
        error: function () {
            bootbox.alert('Gagal di proses!');
            grid();
        }
    });
        
}