$(document).ready(function () {
    gridsp2drincibltu();
    settgl();
    getTotal();
});
function gridsp2drincibltu() {
    var urljson = getbasepath() + "/sp2dbltu/json/getlistsp2drincibltu";
    if (typeof myTable == 'undefined') {
        myTable = $('#btllssp2drincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idspp", value: $('#idSpp').val()}
                );
            }, "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                var sp2dTotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {
                        sp2dTotal += parseFloat(aaData[i]['nilaiSp2d']);
                    }
                }
               // $("#totalsp2d").val(accounting.formatNumber(sp2dTotal, 2, '.', ","))

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
                var nilaisp2d = accounting.formatNumber(aData['nilaiSp2d'], 2, '.', ",");

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaisp2d);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": false, sClass: "center"},
                {"mDataProp": "akun.namaAkun", "bSortable": false, sClass: "center"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": false},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false},
                {"mDataProp": "nilaiSp2d", "bSortable": false, sClass: "kanan", "sWidth": "20%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function settgl() {
    var isi = $("#sp2dtanggal").val();
    var tahun = isi.substr(0, 4);
    var bulan = isi.substr(5, 2);
    var hari = isi.substr(8, 2);
    var hasil = hari + "-" + bulan + "-" + tahun;
    $("#tanggalSp2d").val(hasil);
//        alert("#tanggalSp2d").val();
}

function getTotal() {
    $.getJSON(getbasepath() + "/sp2dbltu/json/getTotal", {idspp: $('#idSpp').val()},
    function(result) {
        
        var total = result.aData['nilaiSp2d'];
        $("#totalsp2d").val(accounting.formatNumber(total, 2, '.', ","));
        console.log("total = "+total);
    });
}

