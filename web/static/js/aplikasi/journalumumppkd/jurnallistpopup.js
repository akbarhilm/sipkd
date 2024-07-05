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
            "sAjaxSource": getbasepath() + "/journalppkd/json/listjurnaljson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(

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
                //var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['kodeSkpd']+"/"+aData['namaSkpd'] + "' />"
                var nilaisaldo = accounting.formatNumber(aData['nilaiDebet']);
                var tgldok = formattgl(aData['tglDok']);
                var tglpost = formattgl(aData['tglPosting']);
                
                var textnoJournal  = "<input type='hidden' id='noJournal" + index + "' value='" + aData['noJournal']+"' />";
                var texttglPosting = "<input type='hidden' id='tglPosting" + index + "' value='" + tglpost +"' />";
                var textnoDok      = "<input type='hidden' id='noDok" + index + "' value='" + aData['noDok']+"' />";
                var texttglDok     = "<input type='hidden' id='tglDok" + index + "' value='" + tgldok +"' />";
                var textketJour    = "<input type='hidden' id='ketJour" + index + "' value='" + aData['ketJour']+"' />";
                var textkoreksi    = "<input type='hidden' id='koreksi" + index + "' value='" + aData['koreksiBPK']+"' />";
                
                //koreksiBPK
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(tglpost);
                $('td:eq(4)', nRow).html(tgldok);
                $('td:eq(6)', nRow).html(nilaisaldo);
                $('td:eq(7)', nRow).html(textnoJournal+texttglPosting+textnoDok+texttglDok+textketJour+textkoreksi+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + index + ")'></span>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noJournal","sWidth": "5%", "bSortable": true, sClass: "center"},
                {"mDataProp": "noJournal","sWidth": "10%", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting","sWidth": "10%", "bSortable": true, sClass: "center"},
                {"mDataProp": "noDok","sWidth": "15%", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglDok","sWidth": "10%", "bSortable": false, sClass: "center"},
                {"mDataProp": "ketJour","sWidth": "20%", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiDebet","sWidth": "15%", "bSortable": false, sClass: "right"},
                {"mDataProp": "noJournal","sWidth": "5%", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function formattgl(objdate) {
    return objdate.substr(6, 2) + "/" + objdate.substr(4, 2) + "/" + objdate.substr(0, 4);
}


