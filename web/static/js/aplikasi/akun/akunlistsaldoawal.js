$(document).ready(function() {
   
});

var nilairadio;

function grid() {
    var filkode = $('#kode').val();
    //var filnama = $('#nama').val();
    
    var subk;
    
    if (filkode !== '' ){
        subk = filkode.substring(0,1);
        //bootbox.alert("FILTER KODE == " + subk);
        
        if (subk == 1 || subk == 2 || subk == 3){
            filkode = $('#kode').val();
        } else {
            filkode = '';
            bootbox.alert("Kode Akun Yang Tersedia Meliputi Kode 1,2 dan 3");
        }
        
    }
    
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
            "sAjaxSource": getbasepath()+"/akun/json/listakun123",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kodeAkunref", value: nilairadio},
                        {name: "kode", value: filkode},
                        {name: "nama", value: $('#nama').val()}
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
                var textnama     = "<input type='hidden' id='nama" + aData['idBas'] + "' value='" + aData['namaAkun']+"' />"
                var textkode     = "<input type='hidden' id='kode" + aData['idBas'] + "' value='" + aData['kodeAkun']+"' />"
                //var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idBas'] + "' value='" + aData['namaAkun']+"' />"
                $('td:eq(0)', nRow).html(index);
                
                $('td:eq(3)', nRow).html(textkode+textnama+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idBas'] + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeAkun", "bSortable": true, sClass: "left"},
                {"mDataProp": "namaAkun", "bSortable": true, sClass: "left"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"}
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
    $('#kode').val('');
    $('#nama').val('');
    
    grid();
 
}
