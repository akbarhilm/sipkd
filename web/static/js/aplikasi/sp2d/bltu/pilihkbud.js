$(document).ready(function() {
    gridpilihkbud();
});
function ambilkbud(kodedoktt) {
    $('#kodeDokTT', window.parent.document).val(kodedoktt).change();
    $('#nipKbud', window.parent.document).val($("#nipKbud" + kodedoktt).val());
    $('#nrkKbud', window.parent.document).val($("#nrkKbud" + kodedoktt).val());
    $('#namaKbud', window.parent.document).val($("#namaKbud" + kodedoktt).val());
    parent.$.fancybox.close();
}
function gridpilihkbud( ) {
    var urljson = getbasepath() + "/sp2dblls/json/getlistpopupkbud";
    $("#kbudtable").show();
    if (typeof myTablekbud == 'undefined') {
        myTablekbud = $('#kbudtable').dataTable({
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
                        {name: "kodewilayah", value: $('#kodewilayah').val()}
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
                var kodeDokTT = "<input type='hidden' id='kodeDokTT" + aData['kodeDokTT'] + "' value='" + aData['kodeDokTT'] + "' />";
                var namaKbud = "<input type='hidden' id='namaKbud" + aData['kodeDokTT'] + "' value='" + aData['namaKbud'] + "' />";
                var nrkKbud = "<input type='hidden' id='nrkKbud" + aData['kodeDokTT'] + "' value='" + aData['nrkKbud'] + "' />";
                var nipKbud = "<input type='hidden' id='nipKbud" + aData['kodeDokTT'] + "' value='" + aData['nipKbud'] + "' />";
               
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(namaKbud + nrkKbud + nipKbud + kodeDokTT + "<span class='icon-ok-sign linkpilihan' onclick=ambilkbud('" + aData['kodeDokTT'] +"')></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpp", "bSortable": false, "sWidth": "4%", sClass: "-"},
                {"mDataProp": "nipKbud", "bSortable": false, sClass: "-"},
                {"mDataProp": "nrkKbud", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "namaKbud", "bSortable": false, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "idSpp", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]

        });

    }
    else
    {
        myTablekbud.fnClearTable(0);
        myTablekbud.fnDraw();
    }
}

