$(document).ready(function() {
  
  
});

function gridpnrm( ) {
    var tanggal = $('#tglvalidasi').val();
    
    //bootbox.alert("tgl validasi == "+ tanggal);
    
    if (tanggal !== null){
         if (typeof myTable == 'undefined') {
            myTable = $('#jourskpdtable').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "bInfo": true,
                "bScrollCollapse": true,
                //"sScrollY": ($(window).height() * 108 / 100),
                "bFilter": false,
                "sAjaxSource": getbasepath() + "/journalpnrm/json/listjournalpnrm",
                "aaSorting": [[1, "asc"]],
                "fnDrawCallback": function() {
                    //formatnumberonkeyup();
                    //$(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                },
                "fnServerParams": function(aoData) {
                    aoData.push(
                            {name: "tahun", value: $('#tahun').val()},
                            {name: "loket", value: $('#loket').val()},
                            {name: "tglvalidasi", value: $('#tglvalidasi').val()}
                    );
                },
                "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {  
                    //bootbox.alert("Panjang == "+ aaData.length);
                    if (aaData.length  > 0){
                        $('#buttoninduk').attr("disabled", false);
                    } else {
                        $('#buttoninduk').attr("disabled", true);
                    }
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

                    var nilai = accounting.formatNumber(aData['nilaipnrm']);
                    var inputnilai = "<input id='nilai" + index + "' class='inputmoney' name='nilai" + index + "' value='" + nilai + "'  readOnly='true'   />";

                    $('td:eq(0)', nRow).html(index);
                    $('td:eq(6)', nRow).html(inputnilai);

                    return nRow;
                },
                "aoColumns": [
                    {"mDataProp": "idpnrm", "sWidth": "5%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "d_validasi", "sWidth": "10%",  "bSortable": false, sClass: "center"},
                    {"mDataProp": "noloket", "sWidth": "7%",  "bSortable": false, sClass: "center"},
                    {"mDataProp": "kodeskpd", "sWidth": "7%",  "bSortable": false, sClass: "center"},
                    {"mDataProp": "namaskpd", "sWidth": "23%",  "bSortable": false, sClass: "left"},
                    {"mDataProp": "sts", "sWidth": "14%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "nilaipnrm", "sWidth": "15%",  "bSortable": false, sClass: "kanan"},
                    {"mDataProp": "kode", "sWidth": "19%", "bSortable": false, sClass: "left"}
                    
                ]
            });

        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }
    } 
    
}


function setTglValidasi(loket){
    $.getJSON(getbasepath() + "/journalpnrm/json/setTanggal", {loket: loket},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodetgl'];
                 ket = result.aData[i]['kettgl'];
                
                 opt += '<option value="'+ kode + '" selected>' + ket + '</option>';
            }
            $('#buttoninduk').attr("disabled", false);
        } else{
            $('#buttoninduk').attr("disabled", true);
        }
       
        $("#tglvalidasi").html(opt);
    
        gridpnrm();
    });
    
}
