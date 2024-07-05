$(document).ready(function() {
    gridkontrak();
});

function gridkontrak() {
    $("#fungsitable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 50,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/kontrak/json/getlistkontrak",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namaskpd", value: $('#skpd').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "namakegiatan", value: $('#namakegiatan').val()},
                {name: "kodemetode", value: $('#kodeMetode').val()},
                {name: "rekanan", value: $('#rekanan').val()},
                {name: "nilai1", value: $('#nilai1').val()},
                {name: "nilai2", value: $('#nilai2').val()}

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
                var thn = $('#tahun').val();
                var iskpd = $('#idskpd').val();
                var tk = aData['tanggalKontrak'];
                var namafile = "KONTRAK" + "--" + tk + "--" + iskpd + "--" + aData['idKontrak'] + ".pdf"
                var pilih = "";


                //if (parseInt(aData['idspp']) == 0) {

                if (parseInt(aData['idBast']) == 0 && parseInt(aData['idspp']) == 0) {
                    pilih = "<a class='icon-edit' href='" + getbasepath() + "/kontrak/updatekontrak/" + aData['idKontrak'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/kontrak/delkontrak/" + aData['idKontrak'] + "' ></a> - <a class='icon-book' href='" + getbasepath() + "/kontrak/" + aData['idKontrak'] + "/" + iskpd + "/" + namafile + "' ></a>";

                } else {
                    pilih = "<a class='icon-edit' href='" + getbasepath() + "/kontrak/updatekontrakketerangan/" + aData['idKontrak'] + "'  ></a> - <a class='icon-book' href='" + getbasepath() + "/kontrak/" + aData['idKontrak'] + "/" + iskpd + "/" + namafile + "' ></a>";
                }
                /*} else {
                 pilih = "<a class='icon-book' href='" + getbasepath() + "/kontrak/" + aData['idKontrak'] +"/"+iskpd+ "/" +namafile + "' ></a>";

                 }*/

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiKontrak'], 2, '.', ","));
                $('td:eq(6)', nRow).html(pilih);

                return nRow;

            },
            "aoColumns": [
                {"mDataProp": "idKontrak", "bSortable": false, sClass: "-"},
                {"mDataProp": "noKontrak", "bSortable": true, sClass: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "metode.kodeMetode", "bSortable": false, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "namaRekanan", "bSortable": false, sClass: "-"},
                {"mDataProp": "nilaiKontrak", "bSortable": true, sClass: "kanan"},
                {"mDataProp": "idKontrak", "bSortable": false, sClass: "center"}

            ]
        });
        $("div.top").html("<a   style='float: right'></a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}