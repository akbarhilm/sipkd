$(document).ready(function() {

});

var ketjenis;

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
            "sAjaxSource": getbasepath() + "/bku/json/listpajakterimajson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "kodekeg", value: $('#kodekeg').val()},
                {name: "jenis", value: ketjenis},
                {name: "tahun", value: $('#tahun').val()},
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
                var namakeg = aData['namaKeg'];
                var keterangan;

                if (namakeg == "null" || namakeg == null) {
                    keterangan = "/";
                    namakeg = "";
                } else {
                    keterangan = aData['kodeKeg'] + "/" + aData['namaKeg'];
                    namakeg = "<textarea id='txtnama" + index + "'readonly style='border:none;margin:0;width:700px;'>" + aData['namaKeg'] + "</textarea>";
                }

                var textnama = "<input type='hidden' id='nama" + index + "' value='" + aData['namaKeg'] + "' />";
                var textkode = "<input type='hidden' id='kode" + index + "' value='" + aData['kodeKeg'] + "' />";

                var textket = "<input type='hidden' id='ket" + index + "' value='" + keterangan + "' />";
                var textbeban = "<input type='hidden' id='beban" + index + "' value='" + aData['beban'] + "' />";
                var textjenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['jenis'] + "' />";
                var textidkeg = "<input type='hidden' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' />";
                var textnobku = "<input type='hidden' id='nobku" + index + "' value='" + aData['noBKU'] + "' />";

                var textbayar = "<input type='hidden' id='bayar" + index + "' value='" + aData['kodeUangPersediaan'] + "' />";

                var jenis;
                if (aData['jenis'] == "1") {
                    jenis = "BTL";
                } else if (aData['jenis'] == "3") {
                    jenis = "BL";
                }

                var nilaipajak = accounting.formatNumber(aData['nilaiPajak'], 2, '.', ",");

                $('td:eq(0)', nRow).html(index + textnobku);
                $('td:eq(1)', nRow).html(jenis + textjenis);
                $('td:eq(5)', nRow).html(namakeg + textbayar);
                $('td:eq(6)', nRow).html(nilaipajak);
                $('td:eq(7)', nRow).html(textidkeg + textbeban + textket + textkode + textnama + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "jenis", "bSortable": false, sClass: "center"},
                {"mDataProp": "beban", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiPajak", "bSortable": true, sClass: "right"},
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
