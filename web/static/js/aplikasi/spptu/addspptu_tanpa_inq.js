
$(document).ready(function() {
    getBanyakTU();
   // $('#buttoninduk').attr("disabled", true);
    //getDataBankDki();
});

var banyakgu;

function getBanyakTU() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var uraian;

    $.getJSON(getbasepath() + "/spptu/json/getBanyakTU", {tahun: tahun, idskpd: idskpd},
    function(result) {
        banyakgu = result;

        uraian = "Tambahan Uang Persediaan Bendahara Pengeluaran / Bendahara Pengeluaran Pembantu (TU ke-" + banyakgu + " selama tahun " + tahun + ")";

        $('#uraian').val(uraian);
    });
}

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/spptu/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}

function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/spptu/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spptu/indexspptu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spptu/indexspptu");
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
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "id", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                /* var pageTotal = 0;
                 var spptotal = 0;
                 if (aaData.length > 0) {
                 for (var i = iStart; i < iEnd; i++) {
                 pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                 spptotal += parseFloat(aaData[i]['nilaiSpp']);
                 }
                 }
                 
                 $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","))
                 $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","))
                 */
            }
            ,
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
                var idspd = "<input type='hidden' id='idspd" + aData['idBl'] + "'   name='idspd" + aData['idBl'] + "'  value='" + aData['idSpd'] + "'  />";
                var idkegiatan = "<input type='hidden' id='idkegiatan" + aData['idBl'] + "'   name='idkegiatan" + aData['idBl'] + "'  value='" + aData['kegiatan']['idKegiatan'] + "'  />";
                var idakun = "<input type='hidden' id='idakun" + aData['idBl'] + "'   name='idakun" + aData['idBl'] + "'  value='" + aData['akun']['idAkun'] + "'  />";
                var noSpd = "<input type='hidden' name='noSpd'     kode='noSpd' value='" + aData['noSpd'] + "' />";
                var isceked = aData['nilaiSpp'] > 0 ? 'checked' : '';
                var readonly = 'readonly'
                if (isceked === 'checked') {
                    readonly = '';
                }
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";
                var namaakun = "<input type='text' name='namaAkun'  style='border:none;margin:0;width:99%;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' />"
                //var namaKegiatan = "<input type='text' name='namaKegiatan'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />"
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdtext = "<input type='text' name='nilaispd" + aData['idBl'] + "' id='nilaispd" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaispd + "' />"
                var nilaisppsisa = accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var nilaisppsisatext = "<input type='text' name='nilaiSppSisa" + aData['idBl'] + "' id='nilaiSppSisa" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaisppsisa + "' />"
                var nilaisppsebelum = accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var nilaisppsebelumtext = "<input type='text' name='nilaiSppSebelum" + aData['idBl'] + "' id='nilaiSppSebelum" + aData['idBl'] + "'  class='inputmoney'  readonly='true'   value='" + nilaisppsebelum + "' />"
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var nilaispptext = "<input type='text' name='nilaispp" + aData['idBl'] + "' id='nilaispp" + aData['idBl'] + "'  class='inputmoney'  " + readonly + "    onkeyup='pasangvalidatebesarperfield(" + aData['idBl'] + ");hitungnilaispp()'   value='" + nilaispp + "'   />"
                var namaKegiatan = "<textarea name='namaKegiatan'  style='border:none;margin:0;width:99%;'>" + aData['kegiatan']['namaKegiatan'] + "</textarea>"
                $('td:eq(0)', nRow).html(index + idspd + idkegiatan + idakun);
                $('td:eq(5)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(namaKegiatan);
                $('td:eq(6)', nRow).html(nilaispdtext);
                $('td:eq(7)', nRow).html(nilaisppsebelumtext);
                $('td:eq(8)', nRow).html(nilaisppsisatext);
                $('td:eq(9)', nRow).html(nilaispptext);
                $('td:eq(10)', nRow).html(inputcek);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "1%"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, "sWidth": "17%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "12%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": false, sClass: "kanan", "sWidth": "12%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": false, sClass: "kanan", "sWidth": "12%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "15%"},
                {"mDataProp": "idBl", "bSortable": false, sClass: "center", "sWidth": "4%"}
            ]
        });


    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    getbanyakspdrinci( );
}
function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaispp" + idbl).attr("readonly", param);
    $("#nilaispd" + idbl).attr("readonly", param);

    if (param == false) {
        var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaispd = accounting.unformat($("#nilaispd" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = accounting.unformat($("#nilaispp" + idbl).val(), ",");
    var nilaispd = accounting.unformat($("#nilaispd" + idbl).val(), ",");

    var status = nilaispp > nilaispd ? false : true;
    // console.log(status+"  "+nilaispp+"  "+nilaispd);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function() {
            $("#nilaispp" + idbl).val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $("#nilaispp" + idbl).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    var searchIDs = $("#spprincitable input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();
    var total = 0;
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
    })
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#nomorRekBank').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");

    var varurl = getbasepath() + "/postdata/json/kirimpostdata";
    var dataac = [];

    var datajour = {
        kodebank: kodebank,
        norek: norek
    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            console.log(data);

            $('#dkinamabank').val(data.namabank);
            $('#dkikodebank').val(data.kodebank);
            $('#dkinorek').val(data.norek);
            $('#dkialamat').val(data.alamat);
            $('#dkinama').val(data.nama);
            $('#dkinpwp').val(data.npwp);

        },
        error: function(xhr) {
            console.error(xhr);
        }
    }).always(function(data) {
        $('#buttoninduk').attr("disabled", false);
        $("#refspptu").valid();
    });
}
