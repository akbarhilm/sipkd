$(document).ready(function() {
    gridsp2dbtlls();
});
function gridsp2dbtlls() {
    var urljson = getbasepath() + "/sp2dbiayals/json/getlistsp2dbiayals";
    if (typeof myTable == 'undefined') {
        myTable = $('#btllssp2dtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "wilproses", value: $('#kodewilayah').val()}
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
                var nilaisp2d = accounting.formatNumber(aData['nilaiSp2d'], 2, '.', ",");
                var edit = "<a href='" + getbasepath() + "/sp2dbiayals/editsp2dbiayals/" + aData['idSpp'] + "/"+ aData['idspm']+"'  title='Klik disini untuk melakukan edit SP2D BTL LS' class='icon-edit' ></a>";
                var tambah = "<a href='" + getbasepath() + "/sp2dbiayals/addsp2dbiayals/" + aData['idSpp'] + "/"+ aData['idspm']+"'  title='Klik disini untuk melakukan entry SP2D' class='icon-plus' ></a>";
                var pilihan = "";
                var nosp2d = aData['noSp2d'];
                if (nosp2d == null) {
                    pilihan = tambah;
                } else {
                    pilihan = edit;
                }
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(nilaisp2d);
                $('td:eq(8)', nRow).html(pilihan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiSp2d", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "keteranganSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "tanggalSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "status", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


