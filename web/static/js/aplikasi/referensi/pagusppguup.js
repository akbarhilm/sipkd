$(document).ready(function() {
    gridpagusppup();
});
function gridpagusppup() {
    var urljson = getbasepath() + "/referensi/spp/json/getlistspppaguup";
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
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
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
                var inputcek = "<input type='checkbox' value='" + aData['id'] + "' name='pilih" + index + "' id='pilih" +index + "'  />"
                var inputspp = "<input type='text'   id='nilaispp" + aData['id'] + "'    name='nilaisppX" + aData['id'] + "'   value='" + accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",") + "'   class='inputmoney'        />";
                var idskpd = "<input type='hidden'   id='idskpd" + aData['id'] + "'    name='idskpdX" + aData['id'] + "'    value='" + aData['skpd.idSkpd'] + "'       />";
                var inputspp = "<input type='hidden'   id='idskpd" + aData['id'] + "'    name='idskpdX" + aData['id'] + "'    value='" + aData['skpd.idSkpd'] + "'       />";
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(inputspp);
                $('td:eq(4)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "skpd.namaSkpd", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sClass: "kanan", sDefaultContent: "-"},
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

function submitnilaispp( ) {
    var dataac = $('#formpagusppgup').serializeObject();
    var varurl= getbasepath() + "/referensi/spp/json/prosessimpan";
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        gridpagusppup();
        bootbox.alert(data.responseText);
    });

}