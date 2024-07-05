$(document).ready(function() {

    if ($("#idskpd").val()) {
        getpagudanspd($("#idskpd").val());
    }

    $("#refsppup").validate();
    //bootbox.alert("total nilai spp = "+$("#jumlahspd").val());
    gettotalnilaispd();
    gridspprinci();
    getbanyakspdrinci();
    getDataBankDki();
});

// var global
var response76 = "";
var jumlahnilaisppgrid;
var totalSisaPaguMe;
var nilaisisa = 0;
var nilaisebelum = 0;
var jumlahRow;
var nilaispdval, totalanggaranval;

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/sppup/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
                nilaispdval = result.spd;
                totalanggaranval = result.anggaran;
            });
}
function getbanyakspdrinci() {
    $.getJSON(getbasepath() + "/sppup/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $('#id').val()},
    function(result) {
        $('#banyakrinci').val(result);
        jumlahRow = result;
        //bootbox.alert("banyak rinci = "+ result);
    });
}

function gettotalnilaispd() {
    $.getJSON(getbasepath() + "/sppup/json/gettotalnilaispd", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val(), idspp: $('#id').val()},
    function(result) {
        $("#jumlahspd").val(result);
        jumlahnilaisppgrid = result;
        //bootbox.alert("result = "+result);
    });
}

function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppup/indexspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/sppup/indexspppup");
    }

}
function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#spprincitable :input[type='text']").attr("readonly", false);
    } else {
        $("#spprincitable :input[type='text']").attr("readonly", "readonly");
    }
}

function bandingTotalSpd() {
    var paguUp = accounting.unformat($("#totalAngaran").val(), ","); //$('#totalAngaran').val();

    //totalspdgrid

    bootbox.alert("Nilai Pagu UP (txt box) = " + paguUp + " || nilai total spd (grid) = " + totalspdgrid);
}

function gridspprinci() {
    var urljson = getbasepath() + "/sppup/json/getlistspdbl";
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
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
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
                        // console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
                    }

                    $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","));
                    $("#totalSpp").val(accounting.formatNumber(spptotal, 2, '.', ","));
                } catch (e) {
                    console.log(e);
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
                var nilaisisa = accounting.formatNumber(aData['nilaiSppSisa'], 2, '.', ",");
                var nilaikontrak = accounting.formatNumber(aData['nilaiKontrak'], 2, '.', ",");
                var nilaispdhidden = "<input type='hidden' name='nilaispdorg" + index + "' id='nilaispdorg" + index + "' value='" + aData['nilaiSpd'] + "' />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "' id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var nilaisisaspp = "<input type='hidden' name='nilaisisaspp" + index + "' id='nilaisisaspp" + index + "' value='" + aData['nilaiSppSisa'] + "' />";
                var tglspp = getTanggal(aData['tglSpd']);
                var nilaispp = parseFloat(aData['nilaiSpp']) > 0 ? aData['nilaiSpp'] : totalanggaranval;
                var nilaispdbanding = totalanggaranval > jumlahnilaisppgrid ? aData['nilaiSpd'] : nilaispp; //aData['nilaiSpp'] : accounting.unformat($("#totalAngaran").val(), ",");

                var nilaisppakhir = $('#banyakrinci').val() > 1 ? 0 : nilaispdbanding;
                var inputspp = "<input type='text'   id='nilaiSpp" + index + "' name='nilaiSpp" + index + "' value='" + aData['nilaiSpp'] + "'   class='inputmoney  sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";
                //nilaisebelum = nilaispp - nilaisebelum;

                $('td:eq(0)', nRow).html(index + idspd);
                $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(nilaispd + nilaispdhidden);
                $('td:eq(4)', nRow).html(nilaikontrak);
                $('td:eq(5)', nRow).html(nilaisisaspp + nilaisisa);
                $('td:eq(6)', nRow).html(inputspp + nilaispphidden);
                hitungnilaispp();
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center", "sWidth": "8%"},
                {"mDataProp": "tglSpd", "bSortable": true, sClass: "center", "sWidth": "8%"},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "kanan", "sWidth": "20%"},
                {"mDataProp": "nilaiKontrak", "bSortable": false, sClass: "kanan", "sWidth": "20%"},
                {"mDataProp": "nilaiSppSisa", "bSortable": false, sClass: "kanan", "sWidth": "20%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "20%"}
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
        var nilaispp = $("#nilaispp" + idbl).autoNumeric('get'); //accounting.unformat($("#nilaispp" + idbl).val(), ",");
        var nilaispd = $("#nilaispd" + idbl).autoNumeric('get'); //accounting.unformat($("#nilaispd" + idbl).val(), ",");
        var nilaiisian = accounting.formatNumber((nilaispp == 0 ? nilaispd : nilaispp), 2, '.', ",")
        $("#nilaispp" + idbl).val(nilaiisian)
    }
    hitungnilaispp();
}

function pasangvalidatebesarperfield(idbl) {
    var nilaispp = accounting.unformat($("#nilaiSpp" + idbl).val(), ",");
    var nilaispd = accounting.unformat($("#nilaisisaspp" + idbl).val(), ","); // GANTI VALISADI NILAI SPD -> NILAI SISA SPP
    var strtotalAngaran = $("#totalAngaran").val();
    var jumspd = $("#jumlahspd").val();
    var totalAngaran = strtotalAngaran.split('.').join('') // accounting.unformat($("#totalAngaran" + idbl).val(), ",");
    //var status = parseFloat(nilaispp) <= parseFloat(nilaispd) && parseFloat(nilaispp) <= parseFloat(totalAngaran) && parseFloat(totalspp) <= parseFloat(totalAngaran);

    var nilaivalidasianggaran;
    var nilaivalidasi;
    hitungtotalExeptMe(idbl);
    var sisapaguup;
    //console.log("total anggaran = "+totalAngaran);
    //console.log("jumlah spd = "+jumspd);
    if (Number(totalAngaran) > Number(jumspd)) {
        nilaivalidasianggaran = jumspd;
    } else {
        nilaivalidasianggaran = totalAngaran;
    }

    if (nilaispd > nilaivalidasianggaran) {
        nilaivalidasi = nilaivalidasianggaran;
    } else {
        nilaivalidasi = nilaispd;
    }

    sisapaguup = totalAngaran - totalSisaPaguMe;
    //console.log("sisa pagu up == "+sisapaguup);

    if (nilaivalidasi > sisapaguup) {
        nilaivalidasi = sisapaguup;
    }
//console.log("nilai validasi == "+nilaivalidasi);

    var status2 = parseFloat(nilaispp) <= parseFloat(nilaivalidasi);
    if (!status2) {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari Anggaran", function() {
            $("#nilaiSpp" + idbl).autoNumeric('set', nilaivalidasi);
            $("#nilaiSpp" + idbl).focus();
            hitungnilaispp();
        });
    } else {
        return true;
    }

}

function hitungnilaispp() {

    var total = 0;
    $(".sppnilai").each(function(index, item) {
        total += parseFloat($(item).autoNumeric('get'));
    });
    $("#totalSpp").val(accounting.formatNumber(total, 2, '.', ","));
}

function gettotalspddanspp() {
    $.getJSON(getbasepath() + "/sppup/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                kodekegiatan: $("#kodekegiatanfilter").val(),
                spdno: $("#nospdfilter").val()
            },
    function(result) {
        $('#totalspd').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
        $('#totalSpp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
    });
}

function bandingtotal() {
    hitungnilaispp();
    var totalspp = accounting.unformat($("#totalSpp").val(), ",");
    var totalpagu = accounting.unformat($("#totalAngaran").val(), ",");
    if (totalspp > totalpagu) {
        bootbox.alert("Total SPP tidak boleh melebihi nilau Pagu UP");
    }
}

function cek() {
    if ($("#nomorRekBank").val() !== $("#dkinorek").val()){
        bootbox.alert("Cek Koneksi ke Bank DKI");
        return false;
        
    } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR"){
        bootbox.alert("Cek Koneksi ke Bank DKI");
        return false;
        
    } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
        bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
        return false;

    } else {
        return true;
    }
    
}

function hitungtotalExeptMe(myIndex) {
    var total = 0;
    $(".sppnilai").each(function(index, item) {
        if ((myIndex - 1) !== index) {
            total += parseFloat($(item).autoNumeric('get'));
            //bootbox.alert("My Index = "+myIndex+" || current index = "+index);
        }
    });
    totalSisaPaguMe = total;
    //$("#totalspp").val(accounting.formatNumber(total, 2, '.', ","));
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

function setUraian() {
    var bulan= "";
    var tahun = $('#tahun').val();
    
    var bln = $("#bulan").val().substr(0,2);
    
    if (bln == "01"){
        bulan = "Januari";
    } else if (bln == "02"){
        bulan = "Februari";
    } else if (bln == "03"){
        bulan = "Maret";
    } else if (bln == "04"){
        bulan = "April";
    } else if (bln == "05"){
        bulan = "Mei";
    } else if (bln == "06"){
        bulan = "Juni";
    } else if (bln == "07"){
        bulan = "Juli";
    } else if (bln == "08"){
        bulan = "Agustus";
    } else if (bln == "09"){
        bulan = "September";
    } else if (bln == "10"){
        bulan = "Oktober";
    } else if (bln == "11"){
        bulan = "November";
    } else if (bln == "12"){
        bulan = "Desember";
    } 
    
    var uraian = "Pengisian Kas Bendahara Pengeluaran / Bendahara Pengeluaran Pembantu bulan "+bulan+" "+tahun;

    $('#uraian').val(uraian);
}