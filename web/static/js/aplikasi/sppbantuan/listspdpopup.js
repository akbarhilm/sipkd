$(document).ready(function () {
    grid();
});

function ambilspd(id) {
    $('#idBtlBantuan', window.parent.document).val($("#idBtlBantuan" + id).val()).change();
    $('#idSpd', window.parent.document).val($("#idSpd" + id).val());
    $('#noSpd', window.parent.document).val($("#noSpd" + id).val());
    $('#namaKegiatan', window.parent.document).val($("#namaKegiatan" + id).val());
    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val());
    $('#idAkun', window.parent.document).val($("#idAkun" + id).val());
    $('#namaAkun', window.parent.document).val($("#namaAkun" + id).val());
    $('#nilaiSpd', window.parent.document).val($("#nilaiSpd" + id).val());
    parent.$.fancybox.close();
}
function grid( ) {
    $("#fungsitable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/sppbantuan/json/listspdpopup",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idskpdkoor", value: $('#idskpdkordinator').val()}
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
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var idSpd = "<input type='hidden'  id='idSpd" + index + "'   name='idSpd" + aData['idBtlBantuan'] + "' value='" + aData['idSpd'] + "'/>  "
                var idBtlBantuan = "<input type='hidden'  id='idBtlBantuan" + index + "'   name='idBtlBantuan" + aData['idBtlBantuan'] + "' value='" + aData['idBtlBantuan'] + "'/>  "
                var noSpd = "<input type='hidden'  id='noSpd" + index + "'   name='noSpd" + aData['idBtlBantuan'] + "' value='" + aData['noSpd'] + "'/>  "
                var namaKegiatan = "<input type='hidden'  id='namaKegiatan" + index + "'   name='namaKegiatan" + aData['idBtlBantuan'] + "' value='" + aData['kegiatan']['namaKegiatan'] + "'/>  "
                var namaAkun = "<input type='hidden'  id='namaAkun" + index + "'   name='namaAkun" + aData['idBtlBantuan'] + "' value='" + aData['akun']['namaAkun'] + "'/>  "
                var idKegiatan = "<input type='hidden' id='idKegiatan" + index + "'   name='idKegiatan" + aData['idBtlBantuan'] + "'  value='" + aData['kegiatan']['idKegiatan'] + "'  />";
                var idAkun = "<input type='hidden' id='idAkun" + index + "'   name='idAkun" + aData['idBtlBantuan'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var nilaiSpd = "<input type='hidden'  id='nilaiSpd" + index + "'   name='nilaiSpd" + aData['idBtlBantuan'] + "' value='" + aData['nilaiSpd'] + "'/>  "
                var pilih = "<span class='icon-ok-sign linkpilihan' onclick='ambilspd(" + index + ")'></span>  "
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSpd']));
                $('td:eq(5)', nRow).html(idAkun + namaAkun + idBtlBantuan + idSpd + noSpd + namaKegiatan + nilaiSpd + idKegiatan + pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false },
                {"mDataProp": "akun.namaAkun", "bSortable": false },
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "right"},
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center"},
            ]
        });
        $("div.top").html("<a right'></a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}