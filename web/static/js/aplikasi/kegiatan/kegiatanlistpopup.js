
var kodeakunval; 

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
            "sAjaxSource": getbasepath()+"/kegiatan/json/listkegiatanjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                    {name: "kode", value: $('#kode').val()},
                    {name: "nama", value: $('#nama').val()},
                    {name: "tahun", value: $('#tahun').val()},
                    {name: "idskpd", value: $('#idskpd').val()},
                    {name: "kodeakun", value: kodeakunval}
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
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"/"+aData['namaKeg'] + "' />"
                var textnama     = "<input type='hidden' id='nama" + aData['idKegiatan'] + "' value='" + aData['namaKegiatan']+"' />";
                var textkode     = "<input type='hidden' id='kode" + aData['idKegiatan'] + "' value='" + aData['kodeKegiatan']+"' />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnama + textkode + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idKegiatan'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeKegiatan", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKegiatan", "bSortable": true, sClass: "left"},
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


