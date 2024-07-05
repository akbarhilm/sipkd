$(document).ready(function() {
   
});
function grid() {
    $("#fungsitable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
           // "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource":  getbasepath()+"/statusspm/json/listspmjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                   {name: "tahun", value: $("#tahun").val()},
                   {name: "wilayahsp2d", value: $("#wilayahSp2d").val()}    
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
            
            
            //edit lagi ntar
            
            
            
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var isceked = aData['isAktif'] == '1'?'checked':'';
                var cekaktif = "<input type='checkbox' name='isaktif"+index+"' id='isaktif"+index+"' disabled  "+isceked+" />";
               //var pilih="<a class='icon-edit' href='"+getbasepath()+"/potspm/updatepotspm/"+aData['idPotongan']+"'  ></a> - <a class='icon-remove' href='"+getbasepath()+"/potspm/deletepotspm/"+aData['idPotongan']+"' ></a>";
                var skpd=aData['kodeSkpd']+" / "+aData['namaSkpd'];
                $('td:eq(0)', nRow).html(index);
               // $('td:eq(4)', nRow).html(cekaktif );
               $('td:eq(8)', nRow).html(skpd );
           
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "i_id","sWidth": "5%", "bSortable": false, sClass: "center"},
                {"mDataProp": "nodokSpm", "sWidth": "10%", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "jenis", "sWidth": "10%","bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "beban","sWidth": "10%", "bSortable": false,sDefaultContent: "-", sClass: "center"}, 
                {"mDataProp": "tglterimaKpkd", "bSortable": false,sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "jumlahHari","sWidth": "10%", "bSortable": false, sDefaultContent: "-",sClass: "center"},
                {"mDataProp": "noSp2d","sWidth": "10%", "bSortable": false, sDefaultContent: "-",sClass: "center"},
                {"mDataProp": "tanggalSp2d", "sWidth": "15%","bSortable": false,sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kodeSkpd", "bSortable": false, sDefaultContent: "-",sClass: "left"}, 
                {"mDataProp": "ketSpm","sWidth": "10%", "bSortable": false,sDefaultContent: "-", sClass: "left"},
              
            ]
        });
        }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
  if ($("#wilayahSp2d").val() == 8){
       
    $("#car").prop('disabled', true);
}
    else{
        $("#car").prop('disabled', false);
    } 
 
 }
 function enadis(){
     
 }
 function cetak() {
     
    var aaa = $("#tahun").val();
    var eee = aaa+"-"+"STATUS-SPM.pdf";
    var bbb = $("#wilayahSp2d").val();
    
   window.location.href= getbasepath() + "/statusspm/json/prosescetak?wilayahsp2d="+bbb+"&tahun="+aaa+"&namafile="+eee;
  
    
}