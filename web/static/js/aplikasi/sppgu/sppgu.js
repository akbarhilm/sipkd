$(document).ready(function() {
    gridsppgu();
    getStatusGU();
}); 
function gridsppgu() {
    var urljson = getbasepath() + "/sppgu/json/getlistsppgu";
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
                var banyak = myTable.fnGetData().length;
                if(banyak > 0){
                    $("#alamattambah").hide();
                }
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
               // {name: "namaskpd", value: $('#skpd').val()},
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
                var edit = "<a href='" + getbasepath() + "/sppgu/editsppgu/" + aData['id'] + "' class='icon-edit' ></a>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "bulan", "bSortable": true, sClass: "center"},
                {"mDataProp": "skpd.namaSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "noSpp", "bSortable": true, sDefaultContent: "-"},
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
        window.location.replace(getbasepath() + "/sppgu/addsppgu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/sppgu/addsppgu");
    }

}


function getStatusGU() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    
    $.getJSON(getbasepath() + "/sppgu/json/getStatusGU", {tahun: tahun, idskpd: idskpd},
    function (result) {
        console.log("result status GU = "+result);
        
        if ( result == 0){
            
            $("#alamattambah").hide();
            bootbox.alert("SPP-UP belum ada / belum validasi , Silahkan entry SPP-UP / validasi terlebih dulu");
        } else{
            
        }
        //$("#banyaknobukti").val(result);
    });
}