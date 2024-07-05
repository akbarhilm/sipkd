$(document).ready(function() {

});

var nobkuval, bebanval, jenisval;

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
            "sAjaxSource": getbasepath() + "/bku/json/listsetoranjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                {name: "nama", value: $('#nama').val()},
                {name: "beban", value: bebanval},
                {name: "jenis", value: jenisval},
                {name: "nobku", value: nobkuval},
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
                var jenis;
                var beban;

                if (aData['beban'] == "UP") {
                    beban = "UP/GU"
                } else {
                    beban = aData['beban'];
                }

                if (aData['jenis'] == "1") {
                    jenis = "BTL";
                } else if (aData['jenis'] == "2") {
                    jenis = "BTL Bantuan";
                } else if (aData['jenis'] == "3") {
                    jenis = "BL";
                } else if (aData['jenis'] == "4") {
                    jenis = "BIAYA";
                }

                var uraian = "Disetor sisa belanja " + beban + " dengan nomor STS " + aData['noSts'];
                var textnovalidasi = "<input type='hidden' id='novalidasi" + index + "' value='" + aData['noValidasi'] + "' />";
                var textnosetor = "<input type='hidden' id='nosetor" + index + "' value='" + aData['noSetor'] + "' />";
                var texttglvalidasi = "<input type='hidden' id='tglvalidasi" + index + "' value='" + aData['tglValidasi'] + "' />";
                var texttgldok = "<input type='hidden' id='tgldok" + index + "' value='" + aData['tglSts'] + "' />";
                var nilai = accounting.formatNumber(aData['nilaiSetor'], 2, '.', ",");
                var texuraian = "<input type='hidden' id='uraian" + index + "' value='" + uraian + "' />";
                var texnilai = "<input type='hidden' id='nilai" + index + "' value='" + nilai + "' />";
                var textidspd = "<input type='hidden' id='idspd" + index + "' value='" + aData['idSpd'] + "' />";
                var textjenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['jenis'] + "' />";
                var textbeban = "<input type='hidden' id='beban" + index + "' value='" + aData['beban'] + "' />";
                var textkodesa = "<input type='hidden' id='kodesa" + index + "' value='" + aData['kodeSA'] + "' />";

                $('td:eq(0)', nRow).html(index + textnovalidasi + textnosetor + texnilai + textidspd + textbeban);
                $('td:eq(7)', nRow).html(nilai + texuraian + texttglvalidasi + texttgldok + textjenis + textkodesa);
                $('td:eq(3)', nRow).html(jenis);
                $('td:eq(4)', nRow).html(beban);
                $('td:eq(8)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");

                return nRow;
            }, //noSetor, tglSetor, noSts, noValidasi, nilaiSetor
            "aoColumns": [
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglValidasi", "bSortable": true, sClass: "center"},
                {"mDataProp": "jenis", "bSortable": false, sClass: "center"},
                {"mDataProp": "beban", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSts", "bSortable": true, sClass: "left"},
                {"mDataProp": "noValidasi", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sClass: "right"},
                {"mDataProp": "noSetor", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
