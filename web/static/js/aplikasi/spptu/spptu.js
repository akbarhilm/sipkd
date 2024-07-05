$(document).ready(function() {
    gridspptu();
    
}); 
function gridspptu() {
    var urljson = getbasepath() + "/spptu/json/getlistspptu";
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
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
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
                var edit = "<a href='" + getbasepath() + "/spptu/edispptu/" + aData['id'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spptu/delspptu/" + aData['id'] + "' class='icon-remove' ></a>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(4)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "noSpp", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "tglSpp", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sDefaultContent: "-"},
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
    if (idskpd) {
        window.location.replace(getbasepath() + "/spptu/addspptu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spptu/addspptu");
    }

}

