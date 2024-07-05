$(document).ready(function() {
    grid();
});



function ambilkegiatan(id) {
    $('#namaKegiatan', window.parent.document).val($("#namakegiatan" + id).val());
    $('#namaProgram', window.parent.document).val($("#namaprogram" + id).val());
    $('#idKegiatan', window.parent.document).val(id).change();
    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath() + "/npd/json/listkegiatanpopup";
    $("#kegiatantable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#kegiatantable').dataTable({
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
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "program", value: $('#program').val()},
                {name: "kegiatan", value: $('#kegiatan').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd'] + " / " + aData['namaSkpd'] + "' />";
                var nilaiAnggaranTapd = accounting.formatNumber(aData['nilaiAnggaranTapd'], 2, '.', ",");
                var nilaiAnggaranTapdText = "<input type='text' name='nilaiAnggaranTapd" + aData['kegiatan']['idKegiatan'] + "' id='nilaiAnggaranTapd" + aData['kegiatan']['idKegiatan'] +"'  class='inputmoney'  readonly='true'   value='" + nilaiAnggaranTapd + "' />";
                var kegiatanText = "<input type='text' name='kegiatan" + aData['kegiatan']['idKegiatan'] + "'  id='kegiatan" + aData['kegiatan']['idKegiatan'] +"'  size='140'  readonly='true'   value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var idkegiatan = "<input type='hidden' name='idbas" + aData['kegiatan']['idKegiatan'] + "' id='idbas" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['idKegiatan'] + "'   />";
                var namakegiatan = "<input type='hidden' name='namakegiatan" + aData['kegiatan']['idKegiatan'] + "' id='namakegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['namaKegiatan'] + "'   />";
                var namaprogram = "<input type='hidden' name='namaprogram" + aData['kegiatan']['idKegiatan'] + "' id='namaprogram" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['program']['namaProgram'] + "'   />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(kegiatanText);
                $('td:eq(2)', nRow).html(nilaiAnggaranTapdText);
               // $('td:eq(3)', nRow).html(index);
                $('td:eq(3)', nRow).html(idkegiatan + namakegiatan + namaprogram + "<span class='icon-ok-sign linkpilihan' onclick='ambilkegiatan(" + aData['kegiatan']['idKegiatan'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false, sDefaultContent: "-"},
            //    {"mDataProp": "program.namaProgram", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaranTapd", "bSortable": false, sDefaultContent: "-"},
               {"mDataProp": "nilaiAnggaranTapd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


