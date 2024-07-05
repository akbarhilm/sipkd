function grid( ) {
   
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
            "sAjaxSource": getbasepath()+"/common/json/listskpdjsonrev",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "skpd", value: $('#skpd').val()},
                  {name: "levelSkpd", value: $('#levelSkpd').val()}
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
                var level = "<input type='hidden'    id='levelSkpd" + aData['idSkpd'] + "' value='" + aData['levelSkpd'] + "'  />"; 
                //alert(level);
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd']+"/"+aData['namaSkpd'] + "' />"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaskpd + level + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": true, sDefaultContent: "-"},
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


function gridbtl( ) {
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
            "sAjaxSource": getbasepath()+"/common/json/listskpdbtlrevjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "skpd", value: $('#skpd').val()}
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
                
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd']+"/"+aData['namaSkpd'] + "' />"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(textnamaskpd + level + "<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idSkpd'] + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSkpd", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaSkpd", "bSortable": true, sDefaultContent: "-"},
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

