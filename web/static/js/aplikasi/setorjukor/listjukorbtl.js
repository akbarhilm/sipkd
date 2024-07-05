$(document).ready(function() {

});

var idskpdlama, tahunangg, nosetor;

function grid() {
    var idskpdval;

    if ($('#tahun').val() == "2018") {
        idskpdval = idskpdlama;
    } else {
        idskpdval = $('#idskpd').val();
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
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/setorjukor/json/listkegjukorbtl",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: tahunangg},
                {name: "nosetor", value: nosetor},
                {name: "nojurnal", value: $('#nojur').val()},
                {name: "idskpd", value: idskpdval},
                {name: "idskpdbaru", value: $('#idskpd').val()}

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

                var tgl = aData['tglSetor'];
                var yy = tgl.substr(0, 4);
                var mm = tgl.substr(5, 2);
                var dd = tgl.substr(8, 2);
                
                var tglSetor = dd + "/" + mm + "/" + yy;
                var tanggal = "<input type='hidden' id='tanggal" + index + "' value='" + tglSetor + "' />";
                var nojur = "<input type='hidden' id='nojurnal" + index + "' value='" + aData['noJurnal'] + "' />";
                
                var nilai = accounting.formatNumber(aData['nilaiSetor'], 2, '.', ",");

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tglSetor);
                $('td:eq(3)', nRow).html(nilai + tanggal + nojur );
                $('td:eq(4)', nRow).html("<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noJurnal", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSetor", "bSortable": false, sClass: "center"},
                {"mDataProp": "noJurnal", "bSortable": true, sClass: "center"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "right"},
                {"mDataProp": "noJurnal", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
