$(document).ready(function() {
    if ($('#kodeTransaksi').val() == "LL" || $('#kodeTransaksi').val() == "SB") {
        //document.getElementById("labeluraian").style.display = "block";
        document.getElementById("labelnippptk").style.display = "block";
        document.getElementById("labelnamapptk").style.display = "block";

    } else {
        //document.getElementById("labeluraian").style.display = "none";
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
    }

    if ($('#kodeTransaksi').val() == "SB") {
        //setJenisPpkd();
        document.getElementById('textNipPptk').innerHTML = 'NIP PPTK / Bendahara :';
        document.getElementById('textNamaPptk').innerHTML = 'Nama PPTK / Bendahara :';

    } else {
        document.getElementById('textNipPptk').innerHTML = 'NIP PPTK :';
        document.getElementById('textNamaPptk').innerHTML = 'Nama PPTK :';

    }

    getBulanBySaldoAwal();//getKodeTutup();
    unformatnilai();
});

var banyakTutupMax, kodeTutupMax, bulanTutupMax;

function setJenisPpkd() {

    var opt = "";

    if ($('#idskpd').val() == 761) {
        opt = '<option value="1" >BTL</option>';
        opt += '<option value="2" >BTL BANTUAN</option>';
        opt += '<option value="4" >BIAYA</option>';

    } else {
        opt = '<option value="1" >BTL</option>';
        opt += '<option value="3" >BL</option>';
    }

    $("#jenis").html(opt);

}

function unformatnilai() {
    var nilai;
    if ($('#kodeTransaksi').val() == "SB") {
        nilai = parseFloat(accounting.unformat($('#nilaiMasuk').val(), ","));
    } else {
        nilai = parseFloat(accounting.unformat($('#nilaiKeluar').val(), ","));
    }

    $('#pengeluaran').val(nilai);
}
function update() {
    var tglPost = $("#tglPosting").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();

    if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
    } else {
        var varurl = getbasepath() + "/bku2016/json/prosesupdatesetorup";
        var dataac = [];

        var fileinbox = $('#inboxFile').val();
        var tglPost = $("#tglPosting").val();
        var dd, mm, yy, tanggal;
        var uraian, namaPPTK, nipPPTK;
        var akun, idbas;

        uraian = $("#uraian").val();

        if ($("#idskpd").val() == 761) {
            akun = "1.1.1.01.01";
            idbas = 27;

        } else {
            if ($("#kodePembayaran").val() == 1) {
                akun = "1.1.1.03.01.001";
                idbas = 9947;

            } else if ($("#kodePembayaran").val() == 2) {
                akun = "1.1.1.03.01.002";
                idbas = 9948;
            }
        }

        if ($('#kodeTransaksi').val() == "LL" || $('#kodeTransaksi').val() == "SB") {
            //uraian = $("#uraian").val();
            nipPPTK = $("#nipPptk").val();
            namaPPTK = $("#namaPptk").val();

        } else {
            //uraian = "";
            namaPPTK = "";
            nipPPTK = "";
        }

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
            jenis: $("#jenis").val(),
            beban: $("#beban").val(),
            nilaikeluar: $("#pengeluaran").val(),
            fileinbox: fileinbox,
            noBKU: $('#noBKU').val(),
            namapptk: namaPPTK,
            nippptk: nipPPTK,
            uraian: uraian,
            carabayar: $("#kodePembayaran").val(),
            idbku: $("#idBku").val(),
            akun: akun,
            idbas: idbas

        }
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
        });
    }

}

function getKodeTutupMax() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    console.log("masuk getKodeTutupMax");
    $.getJSON(getbasepath() + "/bku2016/json/getKodeTutupMax", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
        var kodebulan, bulan, tgltutup, nextkodebulan, nextbulan;
        var opt;

        banyakTutupMax = result.aData.length;
        console.log("masuk banyakTutupMax = "+banyakTutupMax);
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

    $.getJSON(getbasepath() + "/bku2016/json/getKodeTutup", {tahun: tahun, idskpd: idskpd},
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

    $.getJSON(getbasepath() + "/bku2016/json/getSaldoAwal", {tahun: tahun, idskpd: idskpd},
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

    $.getJSON(getbasepath() + "/bku2016/json/getBulanByRekap", {tahun: tahun, idskpd: idskpd},
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
