$(document).ready(function() {
    gridcabang();
});


function ambilidskpd() {
    window.localStorage.setItem("idskpdtapd", $("#idskpd").val());
    window.localStorage.setItem("ketskpdtapd", $("#ketskpd").val());
    
}

function carikode() {
    $("#kodefilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 1 || panjang == 0) {
            gridcabang();
        }
    });
}

function carinama() {
    $("#namafilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridcabang();
        }
    });
}

function gridcabang() {

    var urljson = getbasepath() + "/bankpfk/json/getlistbankinduk";
    if (typeof myTable == 'undefined') {
        myTable = $('#banktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            // "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kodefilter').val()},
                {name: "nama", value: $('#namafilter').val()}
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
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {///cetakspp
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var pilihan, cabang;
                
                pilihan = "<a href='" + getbasepath() + "/bankpfk/editbankinduk/" + aData['idBank'] + "'' class='icon-edit linkpilihan' ></a> - <a class='icon-remove' href='" + getbasepath() + "/bankpfk/deletebankinduk/" + aData['idBank'] + "' ></a>";
                cabang = "<a href='" + getbasepath() + "/bankpfk/addbankpfk/" + aData['idBank'] + "'' class='icon-plus linkpilihan' ></a>";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(pilihan);
                $('td:eq(8)', nRow).html(cabang);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeBankTransfer", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBankTransfer", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBank", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeBankRtgs", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBankRtgs", "bSortable": false, sClass: "left"},
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
