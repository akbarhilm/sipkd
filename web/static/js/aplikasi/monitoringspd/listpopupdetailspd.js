$(document).ready(function() {
    gridspd( );

});



function gridspd( ) {

    var kodetab = $("#kodetab").val();
    var jenis = $("#jenis").val();
    var idakun = $("#idakun").val();
    var idkegiatan = $("#idkegiatan").val();
    var idskpd = $("#idskpd").val();
    var idbtlbantuan = $("#idbtlbantuan").val();
    

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
            "sAjaxSource": getbasepath() + "/monitoringspd/json/detailspd",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kodetab", value: kodetab},
                {name: "idakun", value: idakun},
                {name: "idkegiatan", value: idkegiatan},
                {name: "idskpd", value: idskpd},
                {name: "jenis", value: jenis},
                {name: "idbtlbantuan", value: idbtlbantuan}
                
                        
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
                var isceked = aData['isAktif'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd']);
                var akun = aData['akun']['kodeAkun'] + "/" + aData['akun']['namaAkun'];

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(akun);
                $('td:eq(4)', nRow).html(nilaiSpd);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "tglSpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "ketSpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpd", "bSortable": false, sClass: "kanan"}
            ]
        });
        $("div.top").html("<a right'></a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}