$(document).ready(function () {
    grid();

});

$("#kodekegiatanfilter").keyup(function () {
    var panjang = $(this).val().length;
    if (panjang > 3) {
        grid();
    }
});
$("#namakegiatanfilter").keyup(function () {
    var panjang = $(this).val().length;
    if (panjang > 5) {
        grid();
    }
});
$("#nomorbastfilter").keyup(function () {
    var panjang = $(this).val().length;
    if (panjang > 1) {
        grid();
    }
});
function grid( ) {
    $("#basttable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#basttable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[10, 25, 50, 100, 5000], [10, 25, 50, 100, "All"]],
            "iDisplayLength": 25,
            "sAjaxSource": getbasepath() + "/bast/json/listbastjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        //{name: "bast", value: $('#namabast').val()},
                                {name: "idskpd", value: $('#idskpd').val()},
                        {name: "tahun", value: $('#tahun').val()},
                        {name: "namakegiatan", value: $('#namakegiatanfilter').val()},
                        {name: "nomorbast", value: $('#nomorbastfilter').val()},
                        {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                        );
                    },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var isceked = aData['isAktif'] == '1' ? 'checked' : '';
                var idspp = "<input type='hidden' id='idspp" + aData['idBast'] + "'   name='idspp" + aData['idBast'] + "'  value='" + aData['idspp'] + "'  />";
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />";

                var pilih = "";
                pilih = "<a class='icon-edit' href='" + getbasepath() + "/bast/updatebast/" + aData['noBast'] + "/" + aData['skpd']['idSkpd'] + "/" + aData['tahunAnggaran'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/bast/deletebast/" + aData['noBast'] + "/" + aData['skpd']['idSkpd'] + "/" + aData['tahunAnggaran'] + "' ></a>";

                /* if (parseInt(aData['idspp']) == 0 ) 
                 {
                 
                 pilih = "<a class='icon-edit' href='" + getbasepath() + "/bast/updatebast/" + aData['noBast']+"/"+ aData['skpd']['idSkpd']+"/"+ aData['tahunAnggaran']   + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/bast/deletebast/" + aData['noBast']+"/"+ aData['skpd']['idSkpd']+"/"+ aData['tahunAnggaran']   + "' ></a>";
                 }*/



                $('td:eq(0)', nRow).html(index + idspp);
                $('td:eq(1)', nRow).html(getTanggal(aData['tglBast']));
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiBast']));

                $('td:eq(9)', nRow).html(pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBast", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglBast", "bSortable": false},
                {"mDataProp": "noBast", "bSortable": false, "sWidth": "3%"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, "sWidth": "10%"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true},
                {"mDataProp": "nilaiBast", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "namaPptk", "bSortable": false},
                {"mDataProp": "nipPptk", "bSortable": false},
                {"mDataProp": "ketBast", "bSortable": false},
                {"mDataProp": "idBast", "bSortable": false, sDefaultContent: "-", sClass: "center"}

            ]
        });
        $("div.top").html("<a  href='" + getbasepath() + "/bast/addbast' class='btn blue' style='float: right'>Tambah Data</a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}