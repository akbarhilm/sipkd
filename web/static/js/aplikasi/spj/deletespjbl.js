$(document).ready(function() {
    gridspjrinci();
    var kode = $('#nihil').val();
     if (kode == "0"){
   $('#untuknihil').val("ADA SPJ");    
     }else if(kode == "1"){
   $('#untuknihil').val("NIHIL");    
     }else if(kode == "2"){
   $('#untuknihil').val("NIHIL - NHIL");    
     }
});
function gridspjrinci() {
    var urljson = getbasepath() + "/spj/json/getlistspjrinci";
    if (typeof myTable == 'undefined') {
        myTable = $('#spjbltable').dataTable({
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
                {name: "namaskpd", value: $('#skpd').val()},
                // NANTI INI DIRUBAH ....
                {name: "idspj", value: $('#idspj').val()},
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
                var idspj = $('#idspj').val();
                var urldel = "#"
                var del = "<a href='"+ urldel +"' onclick=hapusspjrinci(" + aData['kegiatan']['idKegiatan'] + "," + idspj + ",'" + aData['kodeBeban'] + "','"+ aData['kegiatan']['kodeKegiatan'] +"') class='icon-remove linkpilihan' ></a>";
                var edit = "<a href=" + getbasepath() + "/spj/editkegiatan/" + aData['kodeBeban'] + "/" + aData['kegiatan']['idKegiatan'] + "/" + idspj + " ?target=_top' class='fancybox fancybox.iframe icon-edit'></a> - " + del;
                var nilaispj = accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['kegiatan']['idKegiatan'] + "' id='nilaispj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispj + "' />"
                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['kegiatan']['idKegiatan'] + "' id='sisaspj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var nilaisudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var nilaisudahspjtext = "<input type='text' name='nilaisudahspj" + aData['kegiatan']['idKegiatan'] + "' id='nilaisudahspj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney valid'  readonly='true'   value='" + nilaisudahspj + "' />"


                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(nilaisudahspjtext);
                $('td:eq(5)', nRow).html(nilaispjtext);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.kodeKegiatan", "sWidth": "4%", "bSortable": true, },
                {"mDataProp": "kegiatan.kodeKegiatan", "sWidth": "10%", "bSortable": true, },
                {"mDataProp": "kegiatan.namaKegiatan", "sWidth": "20%", "bSortable": true, },
                {"mDataProp": "kodeBeban", "bSortable": true, "sWidth": "4%", sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": true, "sWidth": "20%", sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": true, "sWidth": "20%", sDefaultContent: "-", sClass: "center"},
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

    function pindahhalamanadepan() {
        window.location.replace(getbasepath() + "/spj/indexspj");
    }

}
