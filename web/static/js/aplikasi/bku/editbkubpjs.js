$(document).ready(function() {
    $('#jenisTransaksi').val($('#kodeTransaksi').val());// set jenis transaksi untuk popup pengeluaran
    getBulanBySaldoAwal();
    setjenisbayar();

});

var banyakTutupMax, kodeTutupMax, bulanTutupMax;
var saldobpjs = 0;

function setjenisbayar() {
    var nilaimasuk = parseFloat(accounting.unformat($('#nilaiMasuk').val(), ","));
    var nilaikeluar = parseFloat(accounting.unformat($('#nilaiKeluar').val(), ","));
    var nilai;
    var korekoreksi = $('#kodeKoreksi').val();
    var idbas = $('#idBas').val();
    // set jenis bpjs
    $('#jenisbpjs').val(idbas);

    if (korekoreksi == 0) {
        if (nilaimasuk > 0) {
            $('#jenisPembayaran').val("PN");
            nilai = nilaimasuk;

            document.getElementById("pilihjgbp").style.visibility = "hidden";
            document.getElementById('pengeluaran').readOnly = false;
            document.getElementById("labelSA").style.display = "none";
            $('#jenisbpjs').attr("disabled", false);

        } else {
            $('#jenisPembayaran').val("PG");
            nilai = nilaikeluar;
            
            if ($('#noBkuRef').val() == "-1"){ //SALDO AWAL
                document.getElementById("pilihjgbp").style.visibility = "hidden";
                document.getElementById("labelSA").style.display = "block";
                document.getElementById("cbSA").checked = true;
                $('#jenisbpjs').attr("disabled", false);
                
            } else {
                document.getElementById("labelSA").style.display = "none";
                document.getElementById("pilihjgbp").style.visibility = "visible";
                $('#jenisbpjs').attr("disabled", true);
            }
            
        }
    } else {
        if (nilaimasuk < 0) {
            $('#jenisPembayaran').val("PN");
            nilai = nilaimasuk;
        } else {
            $('#jenisPembayaran').val("PG");
            nilai = nilaikeluar;
        }
    }

    $('#pengeluaran').val(accounting.formatNumber(nilai, 2, '.', ","));
    //getSaldoBpjs();
}

function cekupdate() {
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var pengeluaran = $("#pengeluaran").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var tahunTglPost = tglPost.substr(6, 4);
    var bulan = $("#bulan").val();
    var uraian = $("#uraian").val();
    var filling = $('#inboxFile').val();

    var nilaibp = accounting.unformat($("#pengeluaran").val(), ",");
    var totalpn = nilaibp + saldobpjs;

    if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "") {
        bootbox.alert("Pengisian Data Harus Lengkap");
    } else if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
    } else if (tahunTglPost !== $("#tahun").val()) {
        bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
    } else if (nilaibp < 0) {
        bootbox.alert("Nilai BPJS Tidak Boleh Minus");
    } else if ($("#jenisPembayaran").val() == "PG" && (nilaibp == 0)) {
        bootbox.alert("Nilai Pengeluaran BPJS Tidak Boleh Kosong");
    } /*else if ($("#jenisPembayaran").val() == "PN" && (totalpn < 0)) {
        bootbox.alert("Nilai Penerimaan BPJS Tidak Boleh Lebih Kecil dari Saldo BPJS");
    } */else {
        update();
    }
}

function update() {
    var tglPost = $("#tglPosting").val();

    var varurl = getbasepath() + "/bku/json/prosesupdatebpjs";
    var dataac = [];

    var fileinbox = $('#inboxFile').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    var uraian, nilaimasuk, nilaikeluar;
    var jenisbayar = $("#jenisPembayaran").val();
    var nilaipengeluaran = (accounting.unformat($("#pengeluaran").val(), ",")).toString();
    var jenisbpjs = $("#jenisbpjs").val();
    var akun;

    if (jenisbayar == "PN") {
        nilaimasuk = nilaipengeluaran;
        nilaikeluar = "0";
    } else { // PG
        nilaimasuk = "0";
        nilaikeluar = nilaipengeluaran;
    }

    if (jenisbpjs == "3911") { // Kesehatan
        akun = "1.1.01.03.03.008";
    } else { // Ketenagakerjaan
        akun = "1.1.01.03.03.009";
    }

    uraian = $("#uraian").val();

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        nilaikeluar: nilaikeluar,
        nilaimasuk: nilaimasuk,
        fileinbox: fileinbox,
        noBKU: $('#noBKU').val(),
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        idbku: $("#idBku").val(),
        akun: akun,
        idbas: jenisbpjs,
        nobkuref: $("#noBkuRef").val(),
        jenisbayar: jenisbayar

    };
    dataac = datajour;

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
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        //getSaldoBpjs();
    });

}

function getKodeTutupMax() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    console.log("masuk getKodeTutupMax");
    $.getJSON(getbasepath() + "/bku/json/getKodeTutupMax", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;

        banyakTutupMax = result.aData.length;
        console.log("masuk banyakTutupMax = " + banyakTutupMax);
        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];

            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";

            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";

            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";

            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";

            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";

            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";

            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";

            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";

            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";

            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";

            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";

            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';

                kodeTutupMax = kodebulan;
                bulanTutupMax = bulan;
            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
                kodeTutupMax = nextkodebulan;
                bulanTutupMax = nextbulan;
            }

        } else {
            opt = '<option value="01" >01 - Januari</option>';
        }

        $("#bulan").html(opt);

    });

}

function getKodeTutup() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getKodeTutup", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;


        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];
            tgltutup = result.aData[0]['kodeTglTutup'];

            if (kodebulan == "01") {
                nextkodebulan = "02";
                nextbulan = "02 - Februari";

            } else if (kodebulan == "02") {
                nextkodebulan = "03";
                nextbulan = "03 - Maret";

            } else if (kodebulan == "03") {
                nextkodebulan = "04";
                nextbulan = "04 - April";

            } else if (kodebulan == "04") {
                nextkodebulan = "05";
                nextbulan = "05 - Mei";

            } else if (kodebulan == "05") {
                nextkodebulan = "06";
                nextbulan = "06 - Juni";

            } else if (kodebulan == "06") {
                nextkodebulan = "07";
                nextbulan = "07 - Juli";

            } else if (kodebulan == "07") {
                nextkodebulan = "08";
                nextbulan = "08 - Agustus";

            } else if (kodebulan == "08") {
                nextkodebulan = "09";
                nextbulan = "09 - September";

            } else if (kodebulan == "09") {
                nextkodebulan = "10";
                nextbulan = "10 - Oktober";

            } else if (kodebulan == "10") {
                nextkodebulan = "11";
                nextbulan = "11 - November";

            } else if (kodebulan == "11") {
                nextkodebulan = "12";
                nextbulan = "12 - Desember";

            } else if (kodebulan == "12") {
                nextkodebulan = "-";
                nextbulan = "Sudah Tutup Buku";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            // menentukan bulan by tgl tutup
            if (tgltutup == "null" || tgltutup == null) {
                opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';

            } else {
                opt = '<option value="' + nextkodebulan + '" >' + nextbulan + '</option>';
            }

            $("#bulan").html(opt);
        } else { // jika awal tahun, belum ada yang insert data
            /*if (banyakTutupMax > 0) {
             opt = '<option value="' + kodeTutupMax + '" >' + bulanTutupMax + '</option>';
             } else {
             opt = '<option value="01" >01 - Januari</option>';
             }*/
            getKodeTutupMax();
        }

        // $("#bulan").html(opt);
    });

}

function getBulanBySaldoAwal() {
    //getSuadana();
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getSaldoAwal", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var saldo, opt;

        if (banyak > 0) {
            saldo = result.aData[0]['saldoKas'];
            saldoAwal = result.aData[0]['saldoKas'];

            if (saldo > 0) {
                opt = '<option value="01" >01 - Januari</option>';
                $("#bulan").html(opt);

            } else {
                getBulanByRekap();
                //getKodeTutup();
            }
        }
    });
}


function setformatpengeluaran(nilai) {
    //var pengeluaran = $('#nilaiinput').val();
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#pengeluaran').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function getBulanByRekap() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getBulanByRekap", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan;
        var opt;


        if (banyak > 0) {
            kodebulan = result.aData[0]['kodeBulan'];
            bulan = result.aData[0]['bulan'];

            if (kodebulan == "13") {
                kodebulan = "";
                bulan = "Sudah Tutup Buku Akhir Tahun";
                $("#jenisTransaksi").attr('disabled', true);
                /* kalo uda akhir tahun dan sudah tutup semua (desember sudah ditutup), maka tidak bisa input lagi
                 * jadi pilihan jenis transaksi dikunci agar tidak bisa simpan
                 */
            }

            opt = '<option value="' + kodebulan + '" >' + kodebulan + " - " + bulan + '</option>';

            $("#bulan").html(opt);

        } else { // jika awal tahun, belum ada yang insert data
            opt = '<option value="01" >01 - Januari</option>';

            $("#bulan").html(opt);
        }

    });

}

function getSaldoBpjs() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var nobku = $('#noBKU').val();
    var akun = $('#jenisbpjs').val();

    $.getJSON(getbasepath() + "/bku/json/getSaldoBpjs", {tahun: tahun, idskpd: idskpd, nobku: nobku, akun: akun},
    function(result) {
        saldobpjs = result.aData[0]['nilaiSisa'];
        $("#saldobpjs").val(accounting.formatNumber(saldobpjs, 2, '.', ","));
    });

}

function getSaldoAawalJGBP() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var akun = $('#jenisbpjs').val();
    var kodetrx = $('#kodeTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();
    var nobku = $('#noBKU').val();

    if (jenisbayar == "PG" ) {
        $.getJSON(getbasepath() + "/bku/json/getSaldoAawalJGBP", {tahun: tahun, idskpd: idskpd, akun: akun, kodetrx: kodetrx, nobku: nobku},
        function(result) {

            saldoawaljgbp = result.aData['nilaiSA'];
            $('#pengeluaran').val(accounting.formatNumber(saldoawaljgbp, 2, '.', ","));

        });
    }

}

