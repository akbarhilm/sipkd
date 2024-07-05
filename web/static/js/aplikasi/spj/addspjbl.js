$(document).ready(function() {
    gridspjrinci();
    var adanilaispj = $('#adanilaispj').val().toString();
    if (adanilaispj == "1") {
        var kode = $('#nihiloption').val();
        if (kode == "0") {
            $('#untuknihil').val("ADA SPJ");
        } else if (kode == "1") {
            $('#untuknihil').val("NIHIL");
        } else if (kode == "2") {
            $('#untuknihil').val("NIHIL - NHIL");
        }
        $('#untuknihil').show();
          $('#nihiloption').hide();
    } else {
        $('#nihiloption').show();
        $('#untuknihil').hide();
    }
    var kodenihil = $('#nihiloption').val();
    if (kodenihil != "0") {
         $('#rincibl').hide();
    }
     
   
});
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
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
                var kode = aData['kodeBeban'];
                var kodebeban;
                var edit;
                var del;
                if (kode == "UP/GU") {
                    kodebeban = "UP";
                    del = "<a href='" + urldel + "' onclick=hapusspjrinci(" + aData['kegiatan']['idKegiatan'] + "," + idspj + ",'" + kodebeban + "','" + aData['kegiatan']['kodeKegiatan'] + "') class='icon-remove linkpilihan' ></a>";
                    edit = "<a href=" + getbasepath() + "/spj/editkegiatan/UP/" + aData['kegiatan']['idKegiatan'] + "/" + idspj + " ?target=_top' class='fancybox fancybox.iframe icon-edit'></a> - " + del;
                } else {
                    kodebeban = kode;
                    del = "<a href='" + urldel + "' onclick=hapusspjrinci(" + aData['kegiatan']['idKegiatan'] + "," + idspj + ",'" + kodebeban + "','" + aData['kegiatan']['kodeKegiatan'] + "') class='icon-remove linkpilihan' ></a>";
                    edit = "<a href=" + getbasepath() + "/spj/editkegiatan/" + aData['kodeBeban'] + "/" + aData['kegiatan']['idKegiatan'] + "/" + idspj + " ?target=_top' class='fancybox fancybox.iframe icon-edit'></a> - " + del;
                }
                var nilaispj = accounting.formatNumber(aData['spjrinci']['nilai_spj'], 2, '.', ",");
                var nilaispjtext = "<input type='text' name='nilaispj" + aData['kegiatan']['idKegiatan'] + "' id='nilaispj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispj + "' />"
                var sisaspj = accounting.formatNumber(aData['spjrinci']['sisa_spj'], 2, '.', ",");
                var sisaspjtext = "<input type='text' name='sisaspj" + aData['kegiatan']['idKegiatan'] + "' id='sisaspj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney'  readonly='true'   value='" + sisaspj + "' />"
                var nilaisudahspj = accounting.formatNumber(aData['spjrinci']['sudah_spj'], 2, '.', ",");
                var nilaisudahspjtext = "<input type='text' name='nilaisudahspj" + aData['kegiatan']['idKegiatan'] + "' id='nilaisudahspj" + aData['kegiatan']['idKegiatan'] + "'  class='inputmoney valid'  readonly='true'   value='" + nilaisudahspj + "' />"


                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(nilaisudahspjtext);
                $('td:eq(5)', nRow).html(nilaispjtext);
                $('td:eq(6)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kegiatan.kodeKegiatan", "sWidth": "4%", "bSortable": false, },
                {"mDataProp": "kegiatan.kodeKegiatan", "sWidth": "10%", "bSortable": false, },
                {"mDataProp": "kegiatan.namaKegiatan", "sWidth": "20%", "bSortable": false, },
                {"mDataProp": "kodeBeban", "bSortable": true, "sWidth": "4%", sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "spjrinci.sisa_spj", "bSortable": true, "sWidth": "20%", sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": true, "sWidth": "20%", sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "spjrinci.nilai_spj", "bSortable": true, "sWidth": "8%", sDefaultContent: "-", sClass: "center"},
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


function hapusspjrinci(idkegiatan, idspj, beban, kodekegiatan) {
    var urlhapusspd = getbasepath() + "/spj/json/proseshapusspjrinci";
    var dataac = [];
    var datapeg = {"idkegiatan": idkegiatan, "idspj": idspj, "kodebeban": beban};
    dataac.push(datapeg);
    var kodebeban;
    if (beban == "UP") {
        kodebeban = "UP/GU";
    } else {
        kodebeban = beban;
    }
    bootbox.confirm("Anda akan menghapus data SPJ rinci dengan kode kegiatan " + kodekegiatan + " dan kode beban " + kodebeban + " lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(dataac)
            }).always(function(data) {
                // gridspdbtlmasterlist(baseurl);
                bootbox.alert(data.responseText);
                gridspjrinci();
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });
}