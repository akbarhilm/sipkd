$(document).ready(function() {
    grid();
});

function ambilskpdkoor(id) {
    $('#idskpdkoor', window.parent.document).val(id).change();
    $('#namaSkpdKoor', window.parent.document).val($("#kodeSkpdKoor" + id).val() + "/" + $("#namaSkpdKoor" + id).val());
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
            "sAjaxSource": getbasepath() + "/monitoringspd/json/listjsonskpdkoorpopup",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                      {name: "tahun", value: $('#tahun').val()},
                      {name: "namaSkpdKoor", value: $('#namaSkpdKoor').val()}
                      
              //{name: "idskpdkoor", value: $('#idskpdkoor').val()}
                //{name: "namaskpd", value: $('#skpd').val()}
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
                //var kodeSkpdKoor = "<input type='hidden'  id='kodeSkpdKoor" + aData['idskpdkoor'] + "' value='" + aData['kodeSkpdKoor'] + "'/>  "
                var namaSkpdKoor = "<input type='hidden'  id='namaSkpdKoor" + aData['idskpdkoor'] + "' value='" + aData['namaSkpdKoor'] + "'/>  "
                var kodeSkpdKoor = "<input type='hidden'  id='kodeSkpdKoor" + aData['idskpdkoor'] + "' value='" + aData['kodeSkpdKoor'] + "'/>  "
               var pilih = "<span class='icon-ok-sign linkpilihan' onclick='ambilskpdkoor(" + aData['idskpdkoor'] + ")'></span>  "
                $('td:eq(0)', nRow).html(index);

                $('td:eq(3)', nRow).html(kodeSkpdKoor + namaSkpdKoor + pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idskpdkoor", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeSkpdKoor", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpdKoor", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idskpdkoor", "bSortable": false, sClass: "center"}
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