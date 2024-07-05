$(document).ready(function() {
    grid();
});
function ambilkegiatan(id , kodebeban) {
    $('#idKegiatan', window.parent.document).val(id).change();
    //$('#kodeBeban', window.parent.document).val($("#kodeBeban" + id).val()).change();
    $('#Beban', window.parent.document).val(kodebeban);
   // $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val());
    $('#namaKegiatan', window.parent.document).val($("#namaKegiatan" + id).val());
    $('#namaProgram', window.parent.document).val($("#namaProgram" + id).val());
    $('#kodeBeban', window.parent.document).val(kodebeban);
    //$('#kodeBeban', window.parent.document).val('TEST');
    //console.log($("#kodeBeban" + id).val());
   // alert($("#kodeBeban" + id).val());
   // console.log($('#kodeBeban', window.parent.document).val());
    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath() + "/spj/json/listpilihkegiatan";
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
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspj", value: $('#idspj').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "namakegiatan", value: $('#namakegiatan').val()},
                {name: "namaprogram", value: $('#namaprogram').val()}

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
                var namaProgram = "<input type='hidden' id='namaProgram" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['program']['namaProgram'] + "' />";
                var idKegiatan = "<input type='hidden' id='idKegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['idKegiatan']  + "' />";
                var namaKegiatan = "<input type='hidden' id='namaKegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var namaProgramtext = "<input type='text' size='40' readOnly='true' id='namaProgram" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['program']['namaProgram'] + "' />";
                var kodeBeban = "<input type='hidden' id='kodeBeban" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kodeBeban'] + "' />";
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + aData['kegiatan']['idKegiatan'] + "' id='nilaispp" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney valid'  readonly='true'   value='" + nilaispp + "' />"
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(namaProgramtext);
                $('td:eq(5)', nRow).html(nilaispp);
                $('td:eq(5)', nRow).html(idKegiatan + namaProgram + namaKegiatan + kodeBeban + "<span class='icon-ok-sign linkpilihan' onclick=ambilkegiatan('" + aData['kegiatan']['idKegiatan'] +"','"+ aData['kodeBeban']+ "')></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": false, "sWidth": "4%", sClass: "-"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": false, "sWidth": "10%", sClass: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false, sClass: "-", "sWidth": "50%"},
                {"mDataProp": "program.namaProgram", "bSortable": false, sDefaultContent: "-", "sWidth": "21%"},
                {"mDataProp": "kodeBeban", "bSortable": false, sClass: "-", sDefaultContent: "-" , "sWidth": "5%"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": false, sClass: "center", "sWidth": "4%"}


            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

