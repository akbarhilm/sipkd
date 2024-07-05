$(document).ready(function() {
    var jenis = $('#jenis').val();
    var beban = $('#beban').val();
    console.log("beban == " + beban);
    setakunpajak(); // set beban dulu baru set jenis, supaya jenis yang terbaca adalah jenis asli dari DB
    setJenisPpkd();
    getBulanBySaldoAwal();//getKodeTutup();
    getbanyakrinci();
    $('#jenis').val(jenis); // set value jenis setelah option jenis diset
    $('#beban').val(beban); // set value beban setelah option beban diset
    getSisaPajak();
});

// global variable
var idrow = 0;
var banyakdata = 0;
var idbuton;
var akunnama = new Array();
var akunkode = new Array();
var banyakTutupMax, kodeTutupMax, bulanTutupMax;
var sisapajak = 0;

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

function getKegiatan() {
    $('#idKegiatan').val($('#idKegpop').val());
    $("#ketKegiatan").val($('#ketKegpop').val());
    $("#kodeKeg").val($('#kodeKegpop').val());
    $('#btnTambah').prop("disabled", false);
    clearrow();
}

function setakunpajak() {
    //console.log("setakunpajak() jenis = " + $('#jenis').val());
    var opt = "";

    if ($('#jenis').val() == 1) {
        document.getElementById("labelkegiatanpajak").style.display = "none";
        $("#ketKegiatan").val("");
        clearrow();
        $('#btnTambah').prop("disabled", false);
        $('#keteranganKegPopPj').val("");
        opt = '<option value="LS" >LS</option>';

    } else if ($('#jenis').val() == 3) {
        document.getElementById("labelkegiatanpajak").style.display = "block";
        clearrow();
        $('#btnTambah').prop("disabled", false);
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        opt += '<option value="LS" >LS</option>';
    }

    $("#beban").html(opt);
}

function cektambah() {
    var kode = $("#kodeTransaksi").val();

    tambahRow();

}

function tambahRow() {
    idrow += 1;
    var jenisbayar = $('#jenisPembayaran').val();

    var table = document.getElementById('jourtablebody');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden' id='idBku" + idrow + "' name='idBku" + idrow + "' > <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 900px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";

    if (jenisbayar == "PN") {
        cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
        cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

    } else {
        cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
        cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    }
    setAkunCombo();
    formatnumberonkeyup();
}

function getbutton(id) {
    idbuton = id;
}

function setAkunCombo() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenis = $('#jenis').val();
    var jenistrans = $('#kodeTransaksi').val();
    var id = $('#idKegiatan').val();

    var pajak = "PAJAK";
    var ketcombo;

    if (jenis == 1) {
        ketcombo = "BTL";
    } else if (jenis == 3) {
        ketcombo = "SPJ";
    }

    console.log("jenis = " + jenis);
    console.log("ketcombo = " + ketcombo);

    $.getJSON(getbasepath() + "/bku/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: ketcombo, pajak: pajak},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        if (jenistrans == "NP" || jenistrans == "NM" || jenistrans == "P1" || jenistrans == "P2" || jenistrans == "LL") {
            opt = '<option value="" selected></option>';
        }

        try {
            if (banyak > 0) {
                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['kodeakun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                    akunnama[kode] = result.aData[i]['namaakun'];
                    akunkode[kode] = result.aData[i]['ketakun'];
                }
                $("#akun" + idrow).html(opt);
                var cek = "akun" + idrow;
                setNamaAkun(cek);
            }
        } catch (e) {
            console.log(e);
        }
    });
}

function setNamaAkun(textid) {
    var id = textid.substring(4);
    var idbas = $("#akun" + id).val();
    $("#namaakun" + id).val(akunnama[idbas]);
    $("#valkodeakun" + id).val(akunkode[idbas]);
}

function getbanyakrinci() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglpost = $("#noJournalDok").val();
    var nobukti = $("#noBukti").val();
    var ket = "SPJ";
    var nobku = $("#noBKU").val();

    $.getJSON(getbasepath() + "/bku/json/getBanyakEdit", {idskpd: idskpd, tahun: tahun, nobukti: nobukti, tglpos: tglpost, ket: ket, nobku: nobku},
    function(result) {
        banyakdata = result;

        $('#banyakrinci').val(result);

        getrincilist();
    });
}

function getrincilist() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglpost = $("#noJournalDok").val();
    var nobukti = $("#noBukti").val();
    var ket = "SPJ";
    var valbanyak = $('#banyakrinci').val();
    var nobku = $("#noBKU").val();

    $.getJSON(getbasepath() + "/bku/json/valtabel", {idskpd: idskpd, tahun: tahun, nobukti: nobukti, tglpos: tglpost, ket: ket, nobku: nobku},
    function(result) {
        var i, c;
        var kode = $("#kodeTransaksi").val();

        console.log("nilai masuk = " + result.aData[0]['nilaiMasuk']);
        console.log("nilai masuk = " + result.aData[0]['nilaiKeluar']);
        if (result.aData[0]['nilaiMasuk'] > 0) {
            $("#jenisPembayaran").val("PN");
        } else {
            $("#jenisPembayaran").val("PG");
        }

        for (i = 1; i <= valbanyak; i++) {
            if (kode == "JJ") {
                tambahRowSpj();
            } else if (kode == "NP") {
                tambahRowNP();
            } else if (kode == "NM") {
                tambahRowNM();
            } else {
                tambahRow();
            }
        }

        for (i = 0; i < valbanyak; i++) {
            c = i + 1;
            $('#idBku' + c).val(result.aData[i]['idBku']);
            $('#idkeg' + c).val(result.aData[i]['idKegiatan']);
            $('#nilaiMasuk' + c).val(accounting.formatNumber(result.aData[i]['nilaiMasuk'], 2, '.', ","));
            $('#nilaiKeluar' + c).val(accounting.formatNumber(result.aData[i]['nilaiKeluar'], 2, '.', ","));
            setAkunComboEdit(c, result.aData[i]['idBas']);
        }
    });
}

function setAkunComboEdit(idbaris, idbas) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenis = $("#jenis").val();
    var id = $('#idKegiatan').val();
    var pajak = "PAJAK";
    var ketcombo;

    if (jenis == 1) {
        ketcombo = "BTL";
    } else if (jenis == 3) {
        ketcombo = "SPJ";
    }

    $.getJSON(getbasepath() + "/bku/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: ketcombo, pajak: pajak},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        opt = '<option value="" selected></option>';

        try {
            if (banyak > 0) {
                for (var i = 0; i < banyak; i++) {

                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['kodeakun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                    akunnama[kode] = result.aData[i]['namaakun'];
                    akunkode[kode] = result.aData[i]['ketakun'];

                }
                $("#akun" + idbaris).html(opt);
                var cek = "akun" + idbaris;
                $("#akun" + idbaris).val(idbas);
                setNamaAkunEdit(cek, idbas);
            }
        } catch (e) {
            console.log(e);
        }
    });
}

function setNamaAkunEdit(textid, ketidbas) {
    var id = textid.substring(4);
    var idbas = $("#akun" + id).val();
    $("#akun" + id).val(ketidbas);
    $("#namaakun" + id).val(akunnama[idbas]);
    $("#valkodeakun" + id).val(akunkode[idbas]);
}

function cekLengkap() {
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var datalengkap = true;
    var jenistrans = $("#kodeTransaksi").val();
    var filling = $("#inboxFile").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();
    var uraian = $("#uraian").val();

    if (jum > 0) {
        var totalpajakkeluar = 0;

        for (var i = 1; i <= idrow; i++) {
            totalpajakkeluar += accounting.unformat($("#nilaiKeluar" + i).val(), ",");
        }

        for (var a = 1; a <= idrow; a++) {
            if ($('#akun' + a).val() == "") {
                datalengkap = false;
            }
        }

        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
        } else if ((totalpajakkeluar > sisapajak) && ($("#jenisPembayaran").val() == "PG")) {
            bootbox.alert("Total Pengeluaran Pajak Tidak Boleh Lebih Besar Dari Sisa Pajak");
        } else {
            update();
        }
    }
}

function update() {

    var varurl = getbasepath() + "/bku/json/prosesupdatepajak";
    var dataac = [];
    var nilainr = [];
    var nilailist = [];
    var i;
    var banyakNR;
    var jumNR = 0;
    var uraian = $("#uraian").val();

    var fileinbox = $('#inboxFile').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    var namaPPTK, nipPPTK;
    var transaksi = $("#kodeTransaksi").val();
    var idkegiatan, kodekegiatan;

    if ($("#jenis").val() == 1) {
        idkegiatan = "";
        kodekegiatan = "";

    } else if ($("#jenis").val() == 3) {
        idkegiatan = $("#idKegiatan").val();
        kodekegiatan = $("#kodeKeg").val();
    }

    namaPPTK = "";
    nipPPTK = "";

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    banyakNR = idrow - banyakdata; // menghitung jumlah row baru (insert)

    for (i = 0; i < banyakdata; i++) { // list update
        var id2 = i + 1;
        var idbas;

        if ($("#akun" + id2).val() == null) {
            idbas = "";
        } else {
            idbas = $("#akun" + id2).val();
        }
        console.log("idbku 1 = " + $("#idBku1").val());
        console.log("id2 = " + id2);

        var pararr2 = {
            idBku: $("#idBku" + id2).val(),
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id2).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id2).val(), ",")),
            idbas: idbas,
            uraianbukti: uraian, //$("#namaakun" + id2).val(),
            kodeakun: $("#valkodeakun" + id2).val()//$('select[name="akun' + id2 + '"]').find(":selected").text()
        };
        nilailist[i] = pararr2;
    }

    for (i = 0; i < banyakNR; i++) { // list insert
        var id = banyakdata + i + 1;

        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            uraianbukti: uraian, //$("#namaakun" + id).val(),
            kodeakun: $("#valkodeakun" + id).val() //$('select[name="akun' + id + '"]').find(":selected").text()
        };
        nilainr[jumNR] = pararr;
        jumNR += 1; // untuk index nilainr[]
    }

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#kodeTransaksi').val(),
        nobukti: $("#noBukti").val(),
        tgldok: $("#tglDok").val(),
        jenis: $("#jenis").val(),
        beban: $("#beban").val(),
        jumNR: jumNR,
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
        noBKU: $('#noBKU').val(),
        nilailist: nilailist,
        carabayar: $("#kodePembayaran").val(),
        kodekegiatan: kodekegiatan,
        idkegiatan: idkegiatan,
        nilainr: nilainr
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
        clearrow();
        getbanyakrinci();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
    });
}

function clearrow() {
    var i;
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-1 sampai jumlahnya habis
    }
}

function jenisBayar() {
    hapusDataLama();
    banyakdata = 0;
    clearrow();
}

function CEK() {
    console.log("id bas ==== " + $("#akun" + id2).val());
}

function hapusDataLama() {
    console.log("banyak data lama : " + banyakdata);


    if (banyakdata > 0) {
        var varurl = getbasepath() + "/bku/json/hapusdatalama";
        var dataac = [];
        var nilailist = [];
        var i;

        for (i = 0; i < banyakdata; i++) { // list
            var id = i + 1;

            var pararr = {
                idBku: $("#idBku" + id).val()
            };
            nilailist[i] = pararr;
        }

        var datajour = {
            banyakdata: banyakdata,
            nilailist: nilailist

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

        });
    }

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


function getSisaPajak() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenis = $('#kodeTransaksi').val();
    var nobku = $('#noBKU').val();

    $.getJSON(getbasepath() + "/bku/json/getSisaPajak", {tahun: tahun, idskpd: idskpd, jenis: jenis, keterangan: "EDIT", nobku: nobku},
    function(result) {
        document.getElementById("labelsisapajak").style.display = "block";
        sisapajak = result.aData['sisaPajak'];
        $('#sisapajak').val(accounting.formatNumber(result.aData['sisaPajak'], 2, '.', ","));

    });
}
