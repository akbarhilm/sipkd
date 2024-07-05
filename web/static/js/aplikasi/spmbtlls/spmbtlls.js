$(document).ready(function () {
    gridsppup();

});
function gridsppup() {
    var urljson = getbasepath() + "/spmbtlls/json/getlistspmbtlls";
   // var urljson = getbasepath() + "/sppup/json/getlistspdbl";
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
            "fnDrawCallback": function () {
                
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()}
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var edit = "<a href='" + getbasepath() + "/spmbtlls/editspmbtlls/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
                var potongan = " - ";
                if(aData['potongan']==true){
                    potongan = "<a href='" + getbasepath() + "/spmpotayat/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                }
                
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(4)', nRow).html(edit);
                $('td:eq(5)', nRow).html(potongan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpp", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"}
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
        window.location.replace(getbasepath() + "/spmbtlls/addspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmbtlls/addspppup");
    }

}

