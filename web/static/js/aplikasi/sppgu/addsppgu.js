$(document).ready(function() {
    getBanyakGU();
    // gridspprinci();
    //$('#buttoninduk').attr("disabled", true);
    getDataBankDki();
    formatnumberonkeyup();
    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val());
        //getdatapagu($("#idskpd").val());
    }

    $("#refsppgu").validate();
});

// var global
var response76 = "";
var banyakgu;

function getBanyakGU() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/sppgu/json/getBanyakGU", {tahun: tahun, idskpd: idskpd},
    function(result) {
        banyakgu = result;
    });
}

function seturaian() {
    var tahun = $('#tahun').val();
    var bulan = "";
    var bln = $("#bulan").val().substr(0, 2);
    var uraian;

    if (bln == "01") {
        bulan = "Januari";
    } else if (bln == "02") {
        bulan = "Februari";
    } else if (bln == "03") {
        bulan = "Maret";
    } else if (bln == "04") {
        bulan = "April";
    } else if (bln == "05") {
        bulan = "Mei";
    } else if (bln == "06") {
        bulan = "Juni";
    } else if (bln == "07") {
        bulan = "Juli";
    } else if (bln == "08") {
        bulan = "Agustus";
    } else if (bln == "09") {
        bulan = "September";
    } else if (bln == "10") {
        bulan = "Oktober";
    } else if (bln == "11") {
        bulan = "November";
    } else if (bln == "12") {
        bulan = "Desember";
    }

    uraian = "Pengisian Ganti Uang Persediaan Bendahara Pengeluaran / Bendahara Pengeluaran Pembantu bulan " + bulan + " " + tahun + " (GU ke-" + banyakgu + " selama tahun " + tahun + ")";

    $('#uraian').val(uraian);
}


function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/sppgu/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getdatapagu(idskpd) {
    $.getJSON(getbasepath() + "/sppgu/json/getdatapagu/" + idskpd,
            function(result) {
                $('#totalSpp').val(accounting.formatNumber(result.totalSpp));
                $('#totalSpj').val(accounting.formatNumber(result.totalSpj));
                $('#nilaiSisaPaguSpp').val(accounting.formatNumber(result.nilaiSisaPaguSpp));
            });
}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/sppgu/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $('#id').val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}
function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppgu/indexsppgu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/sppgu/indexsppgu");
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
    var urljson = getbasepath() + "/sppgu/json/getlistspdbl";
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
            "aLengthMenu": [[25, 50, 100, 5000], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            "fnDrawCallback": function() {
                formatnumberonkeyup();
//$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                getbanyakspdrinci();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
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
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdhidden = "<input type='hidden' name='nilaispdorg" + index + "'     id='nilaispdorg" + index + "' value='" + aData['nilaiSpd'] + "' />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'     id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var tglspp = getTanggal(aData['tglSpd']);
                var nilaispp = parseFloat(aData['nilaiSpp']) > 0 ? aData['nilaiSpp'] : $("#totalAngaran").val();
                // accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text'   id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";

                $('td:eq(0)', nRow).html(index + idspd);
                $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(nilaispd + nilaispdhidden);
                $('td:eq(4)', nRow).html(inputspp + nilaispphidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "tglSpd", "bSortable": true, "sWidth": "8%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "25%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "25%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

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
        var nilaispp = $("#nilaispp" + idbl).autoNumeric('get');//  accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaispd = $("#nilaispd" + idbl).autoNumeric('get');// accounting.unformat($("#nilaispd" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = $("#nilaiSpp" + idbl).autoNumeric('get'); //accounting.unformat($("#nilaiSpp" + idbl).val(), ",") * 10;
    var nilaispd = $("#nilaispdorg" + idbl).autoNumeric('get'); //accounting.unformat($("#nilaispdorg" + idbl).val(), ",");
    var strtotalAngaran = $("#totalAngaran").val();
    var nilaiSisaPagu = accounting.unformat($("#nilaiSisaPaguSpp").val(), ",");// $("nilaiSisaPaguSpp").val();
    var totalAngaran = strtotalAngaran.split('.').join('') // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    var totalspp = accounting.unformat($("#totalspp").val(), ",");
    var status = nilaispp <= nilaiSisaPagu;
    // console.log((nilaispp < totalAngaran) + "  " + status + "  " + nilaispp + "  " + nilaispd + " " + totalAngaran);
    if (!status) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPP PAGU", function() {
            //  $("#nilaiSpp" + idbl).val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $("#nilaiSpp" + idbl).autoNumeric('set', nilaiSisaPagu);
            $("#nilaiSpp" + idbl).focus();
            hitungnilaispp();
        });

    } else {
        return true;
    }
}

function hitungnilaispp() {
    /* var searchIDs = $(".sppnilai").map(function () {
     return $(this).val();
     }).get();*/
    var total = 0;
    $(".sppnilai").each(function(index, item) {
        total += parseFloat($(item).autoNumeric('get'));
    });
    /* 
     $.each(searchIDs, function (idx, data) {
     total += parseFloat(accounting.unformat($("#nilaispp" + data).val(), ","));
     })*/
    $("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));


}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/sppgu/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                kodekegiatan: $("#kodekegiatanfilter").val(),
                spdno: $("#nospdfilter").val()
            },
    function(result) {
        $('#totalspd').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
    });

}

function cekPagu() {

}


function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#nomorRekBank').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");

    //var varurl = getbasepath() + "/postdata/json/kirimpostdata";
    var varurl = getbasepath() + "/postdata/json/ceknorek";
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
            //$('#dkinpwp').val(data.npwp);

        },
        error: function(xhr) {
            console.error(xhr);
        }
    }).always(function(data) {
        $('#buttoninduk').attr("disabled", false);

    });
}


function cek() {
    if ($("#nomorRekBank").val() !== $("#dkinorek").val()) {
        bootbox.alert("Cek Koneksi ke Bank DKI");
        return false;

    } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
        bootbox.alert("Cek Koneksi ke Bank DKI");
        return false;

    } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
        bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
        return false;

    } else {
        return true;
    }

}
