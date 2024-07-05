$(document).ready(function() {
    gridsp2dupgu();
    var kodewilayah = $('#kodewilayah').val();
    if (kodewilayah == "0" || kodewilayah == "7") {
        $('#popupbeberapaskpd').hide();
        $('#popupsemuaskpd').show();
    } else {
        $('#popupbeberapaskpd').show();
        $('#popupsemuaskpd').hide();
    }

});
function gridsp2dupgu() {
    var urljson = getbasepath() + "/sp2dupgu/json/getlistsp2dupgu";
    if (typeof myTable == 'undefined') {
        myTable = $('#upgusp2dtable').dataTable({
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
                var nilaipotsp2d = accounting.formatNumber(aData['nilaiPotSp2d'], 2, '.', ",");
                //var edit = "<a href='" + getbasepath() + "/sp2dupgu/editsp2dupgu/" + aData['idSpp'] + "/" + aData['idspm'] + "/" + $('#idskpd').val() + "'  title='Klik disini untuk melakukan edit SP2D BTL LS' class='icon-edit' ></a>";
                var edit = "<a href='" + getbasepath() + "/sp2dupgu/editsp2dupgu/" + aData['idSpp'] + "/" + aData['idspm'] + "/" + $('#idskpd').val() + "'  title='Klik disini untuk melakukan edit SP2D UP/GU' class='icon-edit' > - <a href='" + getbasepath() + "/sp2dupgu/deletesp2dupgu/" + aData['idSpp'] + "/" + aData['idspm'] + "/" + $('#idskpd').val() + "'  title='Klik disini untuk melakukan hapus SP2D UP/GU' class='icon-remove' ></a> </a>";

                var tambah = "<a href='" + getbasepath() + "/sp2dupgu/addsp2dupgu/" + aData['idSpp'] + "/" + aData['idspm'] + "/" + $('#idskpd').val() + "'  title='Klik disini untuk melakukan entry SP2D' class='icon-plus' ></a>";
                var pilihan = "";
                var nosp2d = aData['noSp2d'];
                if (nosp2d == null) {
                    pilihan = tambah;
                } else {
                    pilihan = edit;
                }
                
                if (aData['terimaSpm'] == "BELUM ADA"){
                    pilihan = "Belum Ada Tanda Terima SPM";
                }
                
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(nilaisp2d);
                $('td:eq(4)', nRow).html(nilaipotsp2d);
                $('td:eq(10)', nRow).html(pilihan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSpm", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiSp2d", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiPotSp2d", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "kodeBeban", "bSortable": false, sClass: "center"},
                {"mDataProp": "keteranganSpm", "bSortable": false, sClass: "center", sDefaultContent:"-"},
                {"mDataProp": "noSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "tanggalSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "status", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function pindahhalamanadd() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppup/addspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/sppup/addspppup");
    }

}

