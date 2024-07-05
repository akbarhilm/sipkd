$(document).ready(function() {

});

var ketval, ketsaldoawal;

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
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listakun",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "saldoawal", value: ketsaldoawal}
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
                /*
                 var textnama = "<input type='hidden' id='nama" + aData['idKegiatan'] + "' value='" + aData['namaKeg']+"' />";
                 var textkode = "<input type='hidden' id='kode" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"' />";
                 var textket = "<input type='hidden' id='ket" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"/"+aData['namaKeg'] + "' />";
                 var textbeban = "<input type='hidden' id='beban" + aData['idKegiatan'] + "' value='" + aData['beban']+ "' />";
                 */

                var textnama = "<input type='hidden' id='nama" + aData['idBas'] + "' value='" + aData['namaakun'] + "' />";
                var textkode = "<input type='hidden' id='kode" + aData['idBas'] + "' value='" + aData['kodeakun'] + "' />";
                var textket = "<input type='hidden' id='ket" + aData['idBas'] + "' value='" + aData['kodeakun'] + "/" + aData['namaakun'] + "' />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idBas'] + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaakun", "bSortable": true, sClass: "left"},
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
