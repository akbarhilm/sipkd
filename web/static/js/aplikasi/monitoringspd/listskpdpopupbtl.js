$(document).ready(function() {
    grid();
});

function ambilskpd(id) {
    $('#idskpdbtl', window.parent.document).val(id).change();
    $('#namaskpdbtl', window.parent.document).val($("#kodeSkpd" + id).val() + "/" + $("#namaSkpd" + id).val());
   // $('#popupskpdkoor', window.parent.document).attr('href', getbasepath() + "/sppbantuan/addsppbantuan/"+$("#idskpdkoor" + id).val());
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
            "sAjaxSource": getbasepath() + "/monitoringspd/json/listjsonskpdbtlpopup",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                      {name: "tahun", value: $('#tahun').val()},
              //{name: "idskpdkoor", value: $('#idskpdkoor').val()}
                {name: "namaskpd", value: $('#namaskpd').val()}
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
                var isceked = aData['isAktif'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var kodeSkpd = "<input type='hidden'  id='kodeSkpd" + aData['skpd']['idSkpd'] + "' value='" + aData['skpd']['kodeSkpd'] + "'/>  "
                var namaSkpd= "<input type='hidden'  id='namaSkpd" + aData['skpd']['idSkpd'] + "' value='" + aData['skpd']['namaSkpd'] + "'/>  "
                var pilih = "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['skpd']['idSkpd'] + ")'></span>  "
                $('td:eq(0)', nRow).html(index);

                $('td:eq(3)', nRow).html(kodeSkpd + namaSkpd + pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpd.kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "skpd.namaSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"}
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