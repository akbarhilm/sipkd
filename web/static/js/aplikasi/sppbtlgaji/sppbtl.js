$(document).ready(function() {
    gridsppbtl();
    
}); 
function gridsppbtl() {
    var urljson = getbasepath() + "/btlgaji/json/getlistsppbtl";
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
                var edit = "<a href='" + getbasepath() + "/btlgaji/editsppbtl/" + aData['id'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/btlgaji/deletesppbtl/" + aData['id'] + "' class='icon-remove' ></a>";
                var kodesimpeg = aData['kodeSimpeg'] == 1 ? 'GAJI' : 
                        aData['kodeSimpeg'] == 2 ? 'TKD' : 
                        aData['kodeSimpeg'] == 3 ? 'TRANSPORT' : 'PPH' ;
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(kodesimpeg);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(5)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpp", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "tglSpp", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kodeSimpeg", "bSortable": true, sDefaultContent: "-"},
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
    if (idskpd) {
        window.location.replace(getbasepath() + "/btlgaji/addsppbtl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/btlgaji/addsppbtl");
    }

}

