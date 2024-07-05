$(document).ready(function() {
    //idskpdval = window.localStorage.getItem("rowid");
    //console.log("idskpd value == "+idskpdval);
    
});

var idskpdval;

function grid() {
    
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
            "sAjaxSource": getbasepath()+"/akun/json/listakunbukubesar",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                        {name: "tahun", value: $('#tahun').val()},
                        {name: "idskpd", value: idskpdval},
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
                var textnama     = "<input type='hidden' id='nama" + aData['idBas'] + "' value='" + aData['namaAkun']+"' />";
                var textkode     = "<input type='hidden' id='kode" + aData['idBas'] + "' value='" + aData['kodeAkun']+"' />";
                var textnamaakun = "<input type='hidden' id='namaAkun" + aData['idBas'] + "' value='" + aData['kodeAkun']+"/"+aData['namaAkun'] + "' />"
                $('td:eq(0)', nRow).html(index);
                
                $('td:eq(3)', nRow).html(textnamaakun+textkode+textnama+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idBas'] + ")'></span>");
                
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
    grid();
 
}

function cek(){
    console.log("value di cek == "+idskpdval);   
}
