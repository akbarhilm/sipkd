$(document).ready(function () {
    grid();
});
function ambilbast(id, nokontrak) {
    $('#idKegiatan', window.parent.document).val(id).change();
    $('#namaKegiatan', window.parent.document).val($("#namaKegiatan" + id).val());
    $('#nilaiKontrak', window.parent.document).val("0");
    $('#noKontrak', window.parent.document).val(nokontrak);
    $('#idKontrak', window.parent.document).val($("#idKontrak" + id).val());
    // alert($('#noKontrak', window.parent.document).val());
    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath() + "/bl/json/listbastpopup";
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
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()}
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
                var kodeKegiatan = "<input type='hidden' id='kodeKegiatan" + aData['kegiatan']['kodeKegiatan'] + "' value='" + aData['kegiatan']['kodeKegiatan'] + "' />";
                var namaKegiatan = "<input type='hidden' id='namaKegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var namaRekanan = "<input type='hidden' id='namaRekanan" + aData['rekanan']['namaRekanan'] + "' value='" + aData['rekanan']['namaRekanan'] + "' />";
                var noKontrak = "<input type='hidden' id='noKontrak" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kontrak']['noKontrak'] + "' />";
                var idKontrak = "<input type='hidden' id='idKontrak" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kontrak']['idKontrak'] + "' />";
                var ceklis = "<span class='icon-ok-sign linkpilihan></span>";
                var namakegiatan = " <textarea style='border: none;width: 99%'>" + aData['kegiatan']['namaKegiatan'] + "</textarea>"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namakegiatan);
                $('td:eq(5)', nRow).html(kodeKegiatan + namaKegiatan + noKontrak + namaRekanan + idKontrak + "<span class='icon-ok-sign linkpilihan' onclick=ambilbast('" + aData['kegiatan']['idKegiatan'] + "','" + aData['kontrak']['noKontrak'] + "')></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sClass: "-"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sClass: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "rekanan.namaRekanan", "bSortable": false, sClass: "-"},
                {"mDataProp": "kontrak.noKontrak", "bSortable": false, sClass: "-"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sClass: "-"},
            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}