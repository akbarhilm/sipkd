/*
 * Last Edited by Mustakim
 * Date 14 Juni 2016
 * 
 * Edit : tambah validasi tidak boleh ada saldo minus saat tutup bku
 */

$(document).ready(function() {
    id_value = window.localStorage.getItem("idskpdval");
    bln_value = window.localStorage.getItem("bulandval");
    bton_value = window.localStorage.getItem("kodebutton");
    console.log("bton_value == " + bton_value);
    $('#bulan').val(bln_value);
    $('#idskpd').val(id_value);

    setKeteranganBulan(bln_value);

    $("#tanggal").datepicker("setDate", new Date()); // set sysdate untuk tanggal
    getNilaiSaldo();
    getNilaiKas();
    setTanggalTutup();
    getBendahara();
    //console.log("kodetombol = "+bton_value);

});

//global variable
var id_value, bln_value, bton_value;

function getNilaiSaldo() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();
    var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    $.getJSON(getbasepath() + "/tutupbuku/json/getnilaisaldo", {tahun: tahun, idskpd: idskpd, bulan: bulan, kodewilproses: kodewilproses},
    function(result) {

        var saldotunai = result.aData['saldoTunai'];
        var saldobank = result.aData['saldoBank'];
        var saldopanjar = result.aData['saldoLain'];


        $('#saldoTunai').val(accounting.formatNumber(saldotunai));
        $('#saldoBank').val(accounting.formatNumber(saldobank));
        $('#saldoLain').val(accounting.formatNumber(saldopanjar));


        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function getNilaiKas() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();
    var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    //console.log("bulan == " + bulan);

    $.getJSON(getbasepath() + "/tutupbuku/json/getnilaikas", {tahun: tahun, idskpd: idskpd, bulan: bulan, kodewilproses: kodewilproses},
    function(result) {

        var kasterimalalu = result.aData['kasTerimaLalu'];
        var kaskeluarlalu = result.aData['kasKeluarLalu'];
        var kasterima = result.aData['kasTerima'];
        var kaskeluar = result.aData['kasKeluar'];
        var kassaatini = result.aData['kasSaatIni'];

        $('#kasTerimaLalu').val(accounting.formatNumber(kasterimalalu));
        $('#kasKeluarLalu').val(accounting.formatNumber(kaskeluarlalu));
        $('#kasTerima').val(accounting.formatNumber(kasterima));
        $('#kasKeluar').val(accounting.formatNumber(kaskeluar));
        $('#kasSaatIni').val(accounting.formatNumber(kassaatini));

        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function getBendahara() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/tutupbuku/json/getBendahara", {tahun: tahun, idskpd: idskpd},
    function(result) {

        $('#nipPA').val(result.aData['nipPA']);
        $('#nrkPA').val(result.aData['nrkPA']);
        $('#namaPA').val(result.aData['namaPA']);
        $('#nipBendahara').val(result.aData['nipBendahara']);
        $('#nrkBendahara').val(result.aData['nrkBendahara']);
        $('#namaBendahara').val(result.aData['namaBendahara']);


        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function setTanggalTutup() {
    var tgl = $('#tanggal').val();
    var dd, mm, yy, tanggal;

    yy = tgl.substring(6);
    mm = tgl.substr(3, 2);
    dd = tgl.substr(0, 2);
    tanggal = yy + mm + dd;

    // console.log("tanggal tutup = "+tanggal);

    $('#tglPenutupan').val(tanggal);

}

function setKeteranganBulan(bulan) {
    var ketbulan;

    if (bulan == "01") {
        ketbulan = "01 - Januari";

    } else if (bulan == "02") {
        ketbulan = "02 - Februari";

    } else if (bulan == "03") {
        ketbulan = "03 - Maret";

    } else if (bulan == "04") {
        ketbulan = "04 - April";

    } else if (bulan == "05") {
        ketbulan = "05 - Mei";

    } else if (bulan == "06") {
        ketbulan = "06 - Juni";

    } else if (bulan == "07") {
        ketbulan = "07 - Juli";

    } else if (bulan == "08") {
        ketbulan = "08 - Agustus";

    } else if (bulan == "09") {
        ketbulan = "09 - September";

    } else if (bulan == "10") {
        ketbulan = "10 - Oktober";

    } else if (bulan == "11") {
        ketbulan = "11 - November";

    } else if (bulan == "12") {
        ketbulan = "12 - Desember";

    } else if (bulan == "13") {
        ketbulan = "Audited";
    }

    $('#ketBulan').val(ketbulan);
}

function cekSaldoMinus() {
    var cek = false;
    var tunai = accounting.unformat($("#saldoTunai").val());
    var bank = accounting.unformat($("#saldoBank").val());
    var lain = accounting.unformat($("#saldoLain").val());
    //if (bton_value == 1) {
    if ((tunai < 0) || (bank < 0) || (lain < 0)) {
        cek = true;
    }
    //}
    return cek;
}

function simpan() {
    //var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var kodewilproses = window.localStorage.getItem("wilayahsp2dproses_val");

    var varurl = getbasepath() + "/tutupbuku/json/prosessimpan2";
    var dataac = [];
    var kodeTombol = bton_value;

    var datajour = {
        kodewilproses: kodewilproses,
        kodetombol: kodeTombol,
        tahun: tahun,
        idskpd: idskpd,
        bulan: $("#bulan").val(),
        tglpenutupan: $("#tglPenutupan").val(),
        nrkpa: $("#nrkPA").val(),
        nippa: $("#nipPA").val(),
        namapa: $("#namaPA").val(),
        nrkbend: $("#nrkBendahara").val(),
        nipbend: $("#nipBendahara").val(),
        namabend: $("#namaBendahara").val(),
        kasterimalalu: accounting.unformat($("#kasTerimaLalu").val()),
        kaskeluarlalu: accounting.unformat($("#kasKeluarLalu").val()),
        kasterima: accounting.unformat($("#kasTerima").val()),
        kaskeluar: accounting.unformat($("#kasKeluar").val()),
        kassaatini: accounting.unformat($("#kasSaatIni").val()),
        saldotunai: accounting.unformat($("#saldoTunai").val()),
        saldobank: accounting.unformat($("#saldoBank").val()),
        saldolain: accounting.unformat($("#saldoLain").val())
    }
    dataac = datajour;

    if (cekSaldoMinus() && kodeTombol == 1) { // jika saldo minus dan proses tutup buku, maka pesan error dan tidak bisa simpan tutup BKU
        bootbox.alert("Ada Saldo Minus, Anda Tidak Bisa Melanjutkan Proses Tutup BKU, Silahkan Cek Lagi.");
    }
    else if (cekSaldoMinus() && kodeTombol == 0) { // jika saldo minus dan proses draft, maka pesan error dan draft bisa diproses
        bootbox.alert("Ada Saldo Minus, Silahkan Cek Lagi Data BKU Anda.", function(result) {
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
                setbulantutup();
                bootbox.alert("Tutup BKU Pengeluaran Berhasil Disimpan", function(result) {
                    parent.$.fancybox.close();
                });
            });
        });

    }
    else { // jika saldo tidak minus, bisa disimpan
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
            setbulantutup();
            bootbox.alert("Tutup BKU Pengeluaran Berhasil Disimpan", function(result) {
                parent.$.fancybox.close();
            });
        });
    }

}

function setbulantutup() {
    $('#updatetgl', window.parent.document).val("update").change();
    //parent.$.fancybox.close();

}
