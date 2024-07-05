$(document).ready(function() {
    idkontrak = $('#idKontrak', window.parent.document).val();
    grid();

});
var idkontrak;

function ambilkegiatan(id) {

    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val());
    $('#namaKegiatan', window.parent.document).val($("#namaKegiatan" + id).val());
    $('#nilaiSisa', window.parent.document).val(accounting.formatNumber($("#nilaisisa" + id).val()));
    $('#nilaiKontrakOrg', window.parent.document).val($("#nilaisisa" + id).val());
    // $('#idKegiatan', window.parent.document).val(id).change();
    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val()).change();

    parent.$.fancybox.close();
}
function grid() {
    var urljson = getbasepath() + "/kontrak/json/listpopupkegiatan";
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
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namaskpd", value: $('#namaskpd').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "kodekeg", value: $('#kodekeg').val()},
                {name: "namakeg", value: $('#namakeg').val()},
                {name: "idkontrak", value: idkontrak},
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

                var nilaiupgutu = aData['nilaiUPGU'] + aData['nilaiTU'];

                var idKegiatan = "<input type='hidden' id='idKegiatan" + index + "' value='" + aData['kegiatan']['idKegiatan'] + "' />";
                var namaKegiatan = "<input type='hidden' id='namaKegiatan" + index + "' value='" + aData['kegiatan']['kodeKegiatan'] + " / " + aData['kegiatan']['namaKegiatan'] + "' />";
                var namakegiatan = "<textarea cols=50 rows=4 style='border: none;width: 99%'>" + aData['kegiatan']['kodeKegiatan'] + " / " + aData['kegiatan']['namaKegiatan'] + "</textarea>"

                var nilaisisa = "<input type='hidden' id='nilaisisa" + index + "' value='" + aData['nilaiSisa'] + "' />";
                var ceklis;
                if (aData['nilaiSisa'] > 0) {
                    ceklis = "<span class='icon-ok-sign linkpilihan></span>";
                } else {
                    ceklis = "";
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(namakegiatan);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiAngg']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(nilaiupgutu));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiKontrakSebelum']));
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiKontrak']));
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiSetoran']));
                $('td:eq(7)', nRow).html(accounting.formatNumber(aData['nilaiSisa']));
                $('td:eq(8)', nRow).html(idKegiatan + namaKegiatan + nilaisisa + "<span class='icon-ok-sign linkpilihan' onclick='ambilkegiatan(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sClass: "center"},
                {"mDataProp": "nilaiAngg", "bSortable": true, sDefaultContent: "kanan"},
                {"mDataProp": "nilaiUPGU", "bSortable": true, sClass: "kanan"},
                {"mDataProp": "nilaiKontrakSebelum", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiKontrak", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiSetoran", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": false, sClass: "center"}

            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


