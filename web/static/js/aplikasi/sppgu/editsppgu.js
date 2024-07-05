$(document).ready(function () {
    gridspprinci();
    getBanyakGU();
    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val());
        //getdatapagu($("#idskpd").val());

    }
    
    $("#refsppgu").validate();
    
    //getTotalPagu();
});

var totalSisaPaguMe;
var jumlahSPP = 0;
var banyakgu;

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/sppgu/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function (result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}
function getdatapagu(idskpd) {
    $.getJSON(getbasepath() + "/sppgu/json/getdatapagu/" + idskpd,
            function (result) {
                $('#totalSpp').val(accounting.formatNumber(result.totalSpp));
                $('#totalSpj').val(accounting.formatNumber(result.totalSpj));
                $('#nilaiSisaPaguSpp').val(accounting.formatNumber(result.nilaiSisaPaguSpp));
            });
}
function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/sppgu/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $('#id').val()},
    function (result) {
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

function getTotalPagu(jumspp){
    //var total =accounting.unformat( $("#totalspp").val(), ",");
    var sisapagu = accounting.unformat( $("#nilaiSisaPaguSpp").val(), ",");
    var jumlahsisapagu = jumspp + sisapagu;
    
    bootbox.alert("Total di grid = "+jumspp+" || Sisa Pagu yang ada = "+sisapagu+" || Total sisa pagu = "+jumlahsisapagu);
    //$("#nilaiSisaPaguSpp").val(jumlahsisapagu);
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
            "fnDrawCallback": function () {
               // formatnumberonkeyup();
                $(".inputmoney2").autoNumeric('init', {aSep: '.', aDec: ','});
// $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                getbanyakspdrinci();
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) {
                try {

                    var pageTotal = 0;
                    var spptotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpdSisa']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                        // console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
                    }

                    //jumlahSPP = spptotal;
                    //getTotalPagu(spptotal);
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
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var nilaispd = accounting.formatNumber(aData['nilaiSpd'], 2, '.', ",");
                var nilaispdhidden = "<input type='hidden' class='inputmoney' name='nilaispdorg" + index + "'     id='nilaispdorg" + index + "' value='" + aData['nilaiSpd'] + "' />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'     id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var tglspp = getTanggal(aData['tglSpd']);
                var nilaispp =aData['nilaiSpp'];// accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text'   id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney2 sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";
                var nilaisppsebelum = accounting.formatNumber(aData['nilaiSppSebelum'], 2, '.', ",");
                var nilaikontrak = accounting.formatNumber(aData['nilaiKontrak'], 2, '.', ",");
                var nilaispdsisa = accounting.formatNumber(aData['nilaiSpdSisa'], 2, '.', ",");
                var nilaisisaspdhidden = "<input type='hidden' name='nilaisisaspdhidden" + index + "'     id='nilaisisaspdhidden" + index + "' value='" + aData['nilaiSpdSisa'] + "' />";
                
                $('td:eq(0)', nRow).html(index + idspd);
                $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(nilaispd + nilaispdhidden);
                $('td:eq(4)', nRow).html(nilaisppsebelum+ nilaisisaspdhidden);
                $('td:eq(5)', nRow).html(nilaikontrak);
                $('td:eq(6)', nRow).html(nilaispdsisa);
                $('td:eq(7)', nRow).html(inputspp + nilaispphidden);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center", "sWidth": "8%"},
                {"mDataProp": "tglSpd", "bSortable": true, sClass: "center", "sWidth": "8%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "16%"},
                {"mDataProp": "nilaiSppSebelum", "bSortable": false, sClass: "kanan", "sWidth": "16%"},
                {"mDataProp": "nilaiKontrak", "bSortable": false, sClass: "kanan", "sWidth": "16%"},
                {"mDataProp": "nilaiSpdSisa", "bSortable": false, sClass: "kanan", "sWidth": "16%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "16%"}
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
        var nilaispp = $("#nilaispp" + idbl).autoNumeric('get');// accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaispd = $("#nilaispd" + idbl).autoNumeric('get');// accounting.unformat($("#nilaispd" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function hitungtotalExceptMe(myIndex){
    var total = 0;
    $(".sppnilai").each(function (index, item) {
        if((myIndex-1) !== index){
            total += parseFloat($(item).autoNumeric('get'));
            //bootbox.alert("My Index = "+myIndex+" || current index = "+index);
        }
    });
    
    totalSisaPaguMe = total;
    //$("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));
}


function pasangvalidatebesarperfield(idbl) {
    var nilaispp = $("#nilaiSpp" + idbl).autoNumeric('get');// accounting.unformat($("#nilaiSpp" + idbl).val(), ",");
    var nilaispd = accounting.unformat($("#nilaispdorg" + idbl).val(), ",");//$("#nilaispdorg" + idbl).autoNumeric('get'); //
    var strtotalAngaran = $("#totalAngaran").val();
    var nilaiSisaPagu =accounting.unformat( $("#validasiPaguSpp").val(), ",");
    var nilaiSisaSPD =accounting.unformat( $("#nilaisisaspdhidden"+idbl).val(), ",");
    var totalAngaran = strtotalAngaran.split('.').join(''); // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    var totalspp = $("#totalspp" + idbl).val();// accounting.unformat($("#totalspp").val(), ",");
    var status;
    var statuspaguup = parseFloat(nilaispp) <= parseFloat(totalAngaran);
  // console.log((nilaispp <= totalAngaran) + "  " + status + "  nilaispp = " + nilaispp + " nilaispd =  " + nilaispd + " totalAngaran = " + totalAngaran + " nilaiSisaPagu " + nilaiSisaPagu);
    var nilaivalidasi;
    var pesan ="";
    
    hitungtotalExceptMe(idbl);
    //bootbox.alert("Total Nilai Sisa Pagu SPD == "+totalSisaPaguMe);
    var totalNilaiSisaPagu = nilaiSisaPagu - totalSisaPaguMe;
    
    if(nilaiSisaSPD < totalNilaiSisaPagu){
        nilaivalidasi = nilaiSisaSPD;
        pesan="Nilai SPP tidak boleh lebih besar dari nilai Sisa SPD";
    } else{
        nilaivalidasi = totalNilaiSisaPagu;
        pesan="Nilai SPP tidak boleh lebih besar dari nilai SPP PAGU (1-2+3)";
    }
    
    status = parseFloat(nilaispp) <= parseFloat(nilaivalidasi);
    
    if (!status) {
        bootbox.alert(pesan, function () {
            //  $("#nilaiSpp" + idbl).val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $("#nilaiSpp" + idbl).autoNumeric('set', nilaivalidasi);
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
    $(".sppnilai").each(function (index, item) {
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
    function (result) {
        $('#totalspd').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
    });

}


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
