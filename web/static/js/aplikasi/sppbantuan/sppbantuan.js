$(document).ready(function() {
    gridsppbantuan();
    
}); 
function gridsppbantuan() {
    var urljson = getbasepath() + "/sppbantuan/json/getlistsppbantuan";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
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
                //{name: "idskpd", value: $('#idskpd').val()},
                {name: "idskpdkoor", value: $('#idskpdkoor').val()},
                //{name: "namaskpd", value: $('#skpd').val()},
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
                var namaKegiatan = "<textarea name='namaKegiatan' readonly='true'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan'   >" + aData['kegiatan']['namaKegiatan'] + "</textarea>";
                var idBtlBantuan = "<input type='hidden'  id='idBtlBantuan" + aData['idBtlBantuan'] + "'   name='idBtlBantuan" + aData['idBtlBantuan'] + "' value='" + aData['idBtlBantuan'] + "'/>  ";
                var idspd = "<input type='hidden'  id='idSpd" + aData['idSpd'] + "'   name='idSpd" + aData['idSpd'] + "' value='" + aData['idSpd'] + "'/>  ";
                var idskpdkoor = "<input type='hidden'  id='idskpdkoor" + aData['idBtlBantuan'] + "'   name='idskpdkoor" + aData['idBtlBantuan'] + "' value='" + aData['idskpdkoor'] + "'/>  "
                var kooridskpd = $('#idskpdkoor').val();
                //var idspd = $('#idSpd').val();
                var edit = "<a href='" + getbasepath() + "/sppbantuan/edisppbantuan/" + aData['id'] + "/"+ aData['idBtlBantuan'] +"/"+ kooridskpd +"/" +aData['idSpd']+" ' class='icon-edit' ></a> - <a href='" + getbasepath() + "/sppbantuan/delsppbantuan/" + aData['id'] + "/"+ kooridskpd +"' class='icon-remove' ></a>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                $('td:eq(3)', nRow).html(namaKegiatan+idBtlBantuan+idspd);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(6)', nRow).html(edit+idBtlBantuan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "noSpp", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "tglSpp", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sDefaultContent: "-", sClass: "right"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
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
    var idskpdkoor = $.trim($("#idskpdkoor").val());
    if (idskpdkoor) {
        window.location.replace(getbasepath() + "/sppbantuan/addsppbantuan/" + idskpdkoor);
    } else {
        window.location.replace(getbasepath() + "/sppbantuan/addsppbantuan");
    }

}

