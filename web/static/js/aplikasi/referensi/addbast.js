$(document).ready(function() {
   


});
function addakun(){
    gridakun( )
}
function ubahlink( ) {
    var idkegiatan = $('#idkegiatan').val();
  // $("#spprincitable :input[type='text']").attr("readonly", false);
    $("#popupakun").attr("href", getbasepath() + "/bast/listpopupakun/" + idkegiatan + "?target='_top");
}

function gridakun( ) {
    var urljson = getbasepath() + "/bast/json/listpopupakun";
    $("#kontrakpopup").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#kontrakpopup').dataTable({
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
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "idkegiatan", value: $('#idkegiatan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()}
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
                
                
              
                var namaAkun = "<input type='hidden' id='namaAkun" + aData['akun']['idAkun'] + "' value='" + aData['akun']['namaAkun'] + "' />";
                 var idAkun = "<input type='hidden' id='idAkun" + aData['akun']['idAkun'] + "' value='" + aData['akun']['idAkun'] + "' />";
 

                var ceklis = "<span class='icon-ok-sign linkpilihan' onclick='ambilakun(" + aData['akun']['idAkun'] + ")'></span>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(idAkun+namaAkun +  ceklis);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sClass: "-"},
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "-"}

            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


