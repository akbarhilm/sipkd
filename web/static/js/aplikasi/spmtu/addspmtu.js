$(document).ready(function() {

    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val())
    }
    gridspprinci();


});
function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/spptu/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/spptu/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $("#id").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spmtu/indexspmtu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmtu/indexspmtu");
    }

}
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#spprincitable :input[type='text']").attr("readonly", false);
    } else {
        $("#spprincitable :input[type='text']").attr("readonly", "readonly");
    }
}
function gridspprinci() { 
    var urljson = getbasepath() + "/spptu/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#spprincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"toolbar">flrtip',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "iDisplayLength": 25,
            "fnDrawCallback": function () {
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                getbanyakspdrinci( );
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                try {

                    var pageTotal = 0;
                    var spptotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                        console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
                    }


                    $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                    $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))

                } catch (e) {
                    console.log(e)
                }
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
                var idkegiatan = "<input type='hidden' id='idkegiatan" + aData['idBl'] + "'   name='idkegiatan" + aData['idBl'] + "'  value='" + aData['kegiatan']['idKegiatan'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + aData['idBl'] + "'   name='idakun" + aData['idBl'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' />"
                var namaKegiatan = "<input type='text' name='namaKegiatan'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />"
                
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdhidden = "<input type='hidden' name='nilaispdorg" + index + "'     id='nilaispdorg" + index + "' value='" + aData['nilaiSpd'] + "' />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'     id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var tglspp = getTanggal(aData['tglSpd']);
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text'   id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";

                $('td:eq(0)', nRow).html(index + idspd + idkegiatan + idakun);
                $('td:eq(3)', nRow).html(namaakun);
                $('td:eq(5)', nRow).html(namaKegiatan);
                $('td:eq(6)', nRow).html(nilaispd + nilaispdhidden);
                $('td:eq(7)', nRow).html(nilaispp + nilaispphidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "12%"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, "sWidth": "12%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "12%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "12%"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, "sWidth": "12%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "12%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "12%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

 