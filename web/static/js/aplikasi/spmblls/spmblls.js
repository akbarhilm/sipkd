$(document).ready(function () {
    gridsppup();
    //alert("MASSUK");
});


function gridsppup() {
    var urljson = getbasepath() + "/spmblls/json/getlistspmblls";
   // var urljson = getbasepath() + "/sppup/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#blspdtable').dataTable({
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
                var edit = "<a href='" + getbasepath() + "/spmblls/editspmblls/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
                var potongannonayat = " - ";
                var potonganUangMuka = " - ";
                if(aData['potongan']==true){
                    potongannonayat = "<a href='" + getbasepath() + "/spmpotnonayat/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                }
                if(aData['potonganUangMuka']==true){
                    potonganUangMuka = "<a href='" + getbasepath() + "/spmpotuangmuka/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                }
                
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(5)', nRow).html(edit);
                $('td:eq(6)', nRow).html(potongannonayat);
                $('td:eq(7)', nRow).html(potonganUangMuka);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "noSpp", "bSortable": false, sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "20%"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "10%"},
                {"mDataProp": "namaKegiatan", "bSortable": false, sDefaultContent: "-", "sWidth": "30%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "10%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"}
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
        window.location.replace(getbasepath() + "/spmblls/addspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmblls/addspppup");
    }

}

