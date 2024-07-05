$(document).ready(function() {
    grid();
});
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
            "sAjaxSource": getbasepath() + "/ref/json/listbendaharajson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "namaskpd", value: $('#namaskpd').val()},
                        {name: "namabendahara", value: $('#namabendahara').val()}
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
            //edit lagi ntar



            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var isceked = aData['isAktif'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />";
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/bankrek/updatebankrek/" + aData['idBankrek'] + "'  ></a> - <a class='icon-remove' href='" + getbasepath() + "/bankrek/delbankrek/" + aData['idBankrek'] + "' ></a>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(aData['skpd']['kodeSkpd'] + " - " + aData['skpd']['namaSkpd']);
                $('td:eq(8)', nRow).html(cekaktif);
                $('td:eq(9)', nRow).html(pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBankrek", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpd.idSkpd", "bSortable": false},
                {"mDataProp": "kodeBankrek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaBankrek", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idBANKSTS", "bSortable": false, sClass: "center"},
                {"mDataProp": "idBANKSPM", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeThnBerlaku", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeThnBerakhir", "bSortable": false, sClass: "center"},
                {"mDataProp": "isAktif", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idBankrek", "bSortable": false, sDefaultContent: "-", sClass: "center"}

            ]
        });
       // $("div.top").html("<a  href='" + getbasepath() + "/bankrek/addbankrek' class='btn blue' style='float: right'>Tambah Data</a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

