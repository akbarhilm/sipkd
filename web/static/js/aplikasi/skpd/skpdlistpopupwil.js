$(document).ready(function() {
    grid();
});
function ambilskpd(id) {
    $('#skpd', window.parent.document).val($("#namaSkpd" + id).val());
    $('#levelSkpd', window.parent.document).val( $("#levelSkpd" + id).val());
    $('#idskpd', window.parent.document).val(id).change();
   //alert( $("#levelSkpd" + id).val());
    parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath() + "/common/json/listskpdjsonwil";
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "skpd", value: $('#skpd').val()},
                {name: "kodeskpd", value: $('#kodeskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                 {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "kodewilayah", value: $('#kodewilayah').val()}


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
                 var l =  aData['levelSkpd'];
                var level = "<input type='hidden'    id='levelSkpd" + aData['idSkpd'] + "' value='" + aData['levelSkpd'] + "'  />"; 
             //   alert(level);
               var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd'] + " / " + aData['namaSkpd'] + "' />";
              // alert(textnamaskpd);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaskpd +level+ "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
              // alert(level);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeSkpd", "bSortable": false, sClass: "left" ,sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": false, sClass: "left" ,sDefaultContent: "-"},
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


