$(document).ready(function() {

});

var ketval;
var bebanval;

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
            "sAjaxSource": getbasepath() + "/bku2016/json/listkegiatanNPDjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "keterangan", value: "SETORAN"},
                {name: "beban", value: bebanval},
                {name: "idskpd", value: $('#idskpd').val()}
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
                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaKeg'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeKeg'] + "' />";
                var textket = "<input type='hidden' id='ket" + index + "' value='" + aData['kodeKeg'] + "/" + aData['namaKeg'] + "' />";
                var textidkegiatan = "<input type='hidden' id='idkegiatan" + index + "' value='" + aData['idKegiatan'] + "' />";
                var textidspd = "<input type='hidden' id='idspd" + index + "' value='" + aData['idSpd'] + "' />";

                $('td:eq(0)', nRow).html(index + textidspd + textidkegiatan);
                $('td:eq(4)', nRow).html(textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center"},
                {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
