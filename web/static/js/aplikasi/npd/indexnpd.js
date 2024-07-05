$(document).ready(function() {
    gridnpd();
});
function gridnpd() {
    var urljson = getbasepath() + "/npd/json/getlistnpd";
    if (typeof myTable == 'undefined') {
        myTable = $('#npdspjtable').dataTable({
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
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                 {name: "idskpd", value: $('#idskpd').val()},
                //  {name: "namaskpd", value: $('#skpd').val()},
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
                var isceked = aData['nihil'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var edit = "<a href='" + getbasepath() + "/npd/editnpd/" + aData['idNpd'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/npd/deletenpd/" + aData['idNpd'] + "' class='icon-remove' ></a>";
                var kegiatan = "<input type='text' value='"+ aData['kegiatan']['namaKegiatan'] +"' size='61' readOnly='true'>";
                var pptks = aData['nipPptk'] +"/"+ aData['namaPptk'] ;
                var pptk = "<input type='text' value='"+ aData['nipPptk'] +"/"+ aData['namaPptk'] +"' size='61' readOnly='true'>";
                var nilainpd = accounting.formatNumber(aData['nilaiNpd'], 2, '.', ",");
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(kegiatan);
                $('td:eq(3)', nRow).html(pptks);
                $('td:eq(4)', nRow).html(nilainpd);
                //$('td:eq(5)', nRow).html(cekaktif);
                $('td:eq(5)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idNpd", "bSortable": true, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noNpd", "bSortable": true, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sClass: "center"},
                {"mDataProp": "idNpd", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idNpd", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"}
           
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/npd/addnpd/");
    } else {
        window.location.replace(getbasepath() + "/npd/addnpd");
    }

}

