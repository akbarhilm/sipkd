$(document).ready(function() {

});


function grid() {

    $("#banktable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#banktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bankbantuan/json/listsppbantuanjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
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

                var tidspp = "<input type='hidden' id='idspp" + index + "' value='" + aData['idSpp'] + "' />";
                var tnospp = "<input type='hidden' id='nospp" + index + "' value='" + aData['noSpp'] + "' />";
                var tskpd = "<input type='hidden' id='skpd" + index + "' value='" + aData['ketSkpd'] + "' />";
                var tbendahara = "<input type='hidden' id='bendahara" + index + "' value='" + aData['ketBendahara'] + "' />";
                var tnorek = "<input type='hidden' id='norek" + index + "' value='" + aData['noRek'] + "' />";
                var tpenerima = "<input type='hidden' id='penerima" + index + "' value='" + aData['namaPenerima'] + "' />";
                var talamat = "<input type='hidden' id='alamat" + index + "' value='" + aData['alamatPenerima'] + "' />";
                var tnamaorg = "<input type='hidden' id='namaorg" + index + "' value='" + aData['namaOrg'] + "' />";
                var turaian = "<input type='hidden' id='uraian" + index + "' value='" + aData['uraian'] + "' />";
                var nilai = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var tnilaispp = "<input type='hidden' id='nilaispp" + index + "' value='" + nilai + "' />";

                var txtskpd = "<textarea id='txtskpd" + index + "'readonly style='border:none;margin:0;width:450px;'>" + aData['ketSkpd'] + "</textarea>";
                var txturaian = "<textarea id='txturaian" + index + "'readonly style='border:none;margin:0;width:450px;'>" + aData['uraian'] + "</textarea>";

                $('td:eq(0)', nRow).html(index + tidspp + tnospp);
                $('td:eq(2)', nRow).html(txtskpd + tskpd + tbendahara);
                $('td:eq(3)', nRow).html(txturaian + tnorek + talamat);
                $('td:eq(4)', nRow).html(nilai + tpenerima + tnamaorg);
                $('td:eq(5)', nRow).html(turaian + tnilaispp + "<span class='icon-ok-sign linkpilihan' onclick='ambilketerangan(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpp", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpp", "bSortable": true, sClass: "center"},
                {"mDataProp": "ketSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraian", "bSortable": true, sClass: "center"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sClass: "right"},
                {"mDataProp": "idSpp", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
