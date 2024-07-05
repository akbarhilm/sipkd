$(document).ready(function () {
    //console.log("CEK CONSOLE");

    //$('#buttoninduk').attr("disabled", true);
    document.getElementById("labelmultiyear").style.display = "none"; // hide
    document.getElementById("labelnomultiyear").style.display = "none"; // hide
    getKodeMultiyear();

    if ($("#idskpd").val()) {
    }
    $("#spdBTLMasterform").valid();


    if ($("#idKontrak").val() == "") {
        console.log("input baru");
    } else {
        console.log("edit kontrak");
        getDataBankDki();
        //validasibastkontrak();
        //validasisisakontrakupdate();
        getSisaAnggaran();
    }

    $.validator.addMethod("ceknilaikontrakvsbast", function (value, element) {
        var result = false;
        if (!result) {
            $.ajax({
                type: "GET",
                async: false,
                url: getbasepath() + "/kontrak/json/gettotalnilaikontrakperbast",
                data: {
                    idskpd: $("#idskpd").val(),
                    idkontrak: $("#idKontrak").val(),
                    tahunAnggaran: $("#tahunAnggaran").val()
                },
                success: function (data) {
                    var valueAkhir = accounting.unformat(value, ",")
                    if (valueAkhir < data)
                        result = false;
                    else
                        result = true;
                }
            })
        }
        return result
    }, "Nilai Kontrak tidak boleh lebih kecil dari nilai BAST yang sudah ada");
});

var sisakontrak;
var nilaivalidasi;
var nilaiupdate;
var cekresult;
var nilaibastkontrak;
var response76 = "00";


function validasi2() {
    $("#spdBTLMasterform").valid();
}

function pindahhal() {
    window.location.replace(getbasepath() + "/kontrak");
}

function setBtnRinci() {
    $('#btnKontrakRinci').attr("disabled", false);
}

function pasangvalidatebesarperfield(idbl) {
    var status = Number(idbl) <= Number($("#nilaiKontrakOrg").val())
    if (!status) {
        bootbox.alert("Nilai Kontrak tidak boleh lebih besar dari nilai SPD", function () {
            $("#nilaiSpd").val($("#nilaiKontrakOrg").val());
            $("#nilaiSpd").focus();
        });

    } else {
        return true;
    }
}

//==================================================================================================

function cek() {
    var namatujuan;
    if ($("#kodeBankTransfer").val() == "111") {
        namatujuan = $("#namaRekananBank").val();
    } else {
        namatujuan = $("#namaRekananBank").val().substr(0, 30);
    }

    if ($("#kodeBelanja").val() == "-") {
        bootbox.alert("Pilih Jenis Belanja Terlebih Dulu");
        return false;
    } else {

        //if ($("#kodeBankTransfer").val() == "111" && $("#kodeVA").val() == "0") {
        if ($("#kodeVA").val() == "0") {
            if ($("#rekeningBank").val() !== $("#dkinorek").val()) {
                bootbox.alert("Cek Koneksi ke Bank DKI");
                return false;

            } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
                bootbox.alert("Cek Koneksi ke Bank DKI");
                return false;

            } else if (namatujuan !== $("#dkinama").val().trim()) {
                bootbox.alert("Nama Perusahaan (Bank) Harus Sama dengan Nama dari Bank DKI");
                return false;

            } else if ($("#kodeBankTransfer").val() !== "111" && response76 !== "00") {
                bootbox.alert("Data Nasabah Tidak Terdaftar");
                return false;

            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}

function validasisisakontrak() {

    var nilaikontrak = $("#nilaiKontrakOrg").val();

    var idskpd = $("#idskpd").val();
    var idspd = $("#idSpd").val();
    var idkegiatan = $("#idKegiatan").val();
    var idkontrak = 000000000000; // posisi tambah belum ada idkontrak, jangan di kosongin karena nanti ga sesuai

    //var nilaiTerkecil;

    $.getJSON(getbasepath() + "/kontrak/json/validasisisakontrak", {idskpd: idskpd, idspd: idspd, idkegiatan: idkegiatan, idkontrak: idkontrak},
    function (result) {
        sisakontrak = result;
        cekresult = result;


        if (nilaikontrak > sisakontrak) {
            //$("#nilaiSpd").val(sisakontrak);
            nilaivalidasi = sisakontrak;
        } else if (nilaikontrak <= sisakontrak) {
            // $("#nilaiSpd").val(nilaikontrak);
            nilaivalidasi = nilaikontrak;
        }
        $("#nilaiSpd").val("");

        //console.log("sisa kontrak nilaivalidasi = " + nilaivalidasi);

        getSisaKontrakSpj(nilaivalidasi);

    });
}

function getSisaKontrakSpj(validasi) { // ditambah 27 jan 16 by zainab, untuk tambah validasi sisa nilai kontrak yang bisa diinput
    var idskpd = $("#idskpd").val();
    var idkegiatan = $("#idKegiatan").val();
    var idkontrak = 000000000000; // add

    $.getJSON(getbasepath() + "/kontrak/json/getSisaKontrakSpj", {idskpd: idskpd, idkegiatan: idkegiatan, idkontrak: idkontrak},
    function (result) {

        var sisaspd = result.aData[0]['nilaiSisa'];
        //console.log("getSisaKontrakSpj sisa spd =========== " + sisaspd);
        if (validasi > sisaspd) {
            //$("#nilaiSpd").val(sisaspd);
            nilaivalidasi = sisaspd;
        }
        $("#nilaiSpd").val("");
        //console.log("nilaivalidasi =========== " + nilaivalidasi);
        getSisaKontrakSpd(validasi);
    });
}

function getSisaKontrakSpd(validasi) { // ditambah 17 april 17 by zainab, untuk tambah validasi sisa nilai kontrak yang bisa diinput berdasarkan sisa SPD
    var idskpd = $("#idskpd").val();
    var idspd = $("#idSpd").val();
    var idkontrak = 000000000000; // add

    $.getJSON(getbasepath() + "/kontrak/json/getSisaKontrakSpd", {idskpd: idskpd, idspd: idspd, idkontrak: idkontrak},
    function (result) {

        var sisaspd = result.aData[0]['nilaiSisa'];
        //console.log("getSisaKontrakSpj sisa spd =========== " + sisaspd);
        if (validasi > sisaspd) {
            //$("#nilaiSpd").val(sisaspd);
            nilaivalidasi = sisaspd;
        }
        $("#nilaiSpd").val("");
        //console.log("nilaivalidasi =========== " + nilaivalidasi);

    });
}


function validasibastkontrak() {

    var idskpd = $("#idskpd").val();
    //var idspd = $("#idSpd").val();
    //var idkegiatan = $("#idKegiatan").val();
    var idkontrak = $("#idKontrak").val();
    var nilaiTerkecil;
    var nilaikontrak = $("#nilaiSpd").val();

    $.getJSON(getbasepath() + "/kontrak/json/validasibastkontrak", {idskpd: idskpd, idkontrak: idkontrak},
    function (result) {

        if ($("#idKontrak").val() == "") {
            //console.log("input baru");
        } else if (result > 0) {
            nilaibastkontrak = result;
            // console.log("edit kontrak dan nilai bast > 0");
            nilaiTerkecil = Math.max(result, nilaikontrak);
            //$("#nilaiSpd").val(nilaiTerkecil);

        }

        //console.log("nilai terkecil = "+Math.min(2,7,9,1));

    });
}

function validasisisakontrakupdate() {
    var nilaikontrak = nilaiupdate;

    var idskpd = $("#idskpd").val();
    var idspd = $("#idSpd").val();
    var idkegiatan = $("#idKegiatan").val();
    var idkontrak = $("#idKontrak").val();

    $.getJSON(getbasepath() + "/kontrak/json/validasisisakontrak", {idskpd: idskpd, idspd: idspd, idkegiatan: idkegiatan, idkontrak: idkontrak},
    function (result) {
        sisakontrak = result;

        nilaivalidasi = sisakontrak;
        //console.log("validasi sisa kontrak = "+result);
        getSisaKontrakSpjUpdate(nilaivalidasi);
    });
}

function getSisaKontrakSpjUpdate(validasi) { // ditambah 27 jan 16 by zainab, untuk tambah validasi sisa nilai kontrak yang bisa diinput
    var idskpd = $("#idskpd").val();
    var idkegiatan = $("#idKegiatan").val();
    var idkontrak = $("#idKontrak").val();

    $.getJSON(getbasepath() + "/kontrak/json/getSisaKontrakSpj", {idskpd: idskpd, idkegiatan: idkegiatan, idkontrak: idkontrak},
    function (result) {

        var sisaspd = result.aData[0]['nilaiSisa'];
        if (validasi > sisaspd) {
            nilaivalidasi = sisaspd;
        }

        getSisaKontrakSpdUpdate(validasi);

    });
}

function getSisaKontrakSpdUpdate(validasi) { // ditambah 27 jan 16 by zainab, untuk tambah validasi sisa nilai kontrak yang bisa diinput
    var idskpd = $("#idskpd").val();
    var idspd = $("#idSpd").val();
    var idkontrak = $("#idKontrak").val();

    $.getJSON(getbasepath() + "/kontrak/json/getSisaKontrakSpd", {idskpd: idskpd, idspd: idspd, idkontrak: idkontrak},
    function (result) {

        var sisaspd = result.aData[0]['nilaiSisa'];
        if (validasi > sisaspd) {
            nilaivalidasi = sisaspd;
        }

    });
}

function validasi(nilai) {

    var nilaikontrak = accounting.unformat(nilai, ",");

    // $("#spdBTLMasterform").valid();
    //console.log("nilaibastkontrak = "+nilaibastkontrak);
    if (nilaibastkontrak > 0) {
        if (Number(nilaikontrak) < Number(nilaibastkontrak)) {
            var nilaival = accounting.formatNumber(nilaibastkontrak, 2, '.', ",");

            //bootbox.alert("Nilai == "+nilai+" || Nilai Kontrak Tidak Boleh Melebihi Nilai Sisa Kontrak = "+nilaivalidasi);
            bootbox.alert("Nilai Kontrak Tidak Boleh Lebih Kecil dari Nilai BAST Senilai Rp. " + nilaival);
            $("#nilaiSpd").val(nilaibastkontrak);
        }
    } else {

        //console.log("nilaivalidasi ============= "+nilaivalidasi);
        if (Number(nilaikontrak) > Number(nilaivalidasi)) {
            var nilaival = accounting.formatNumber(nilaivalidasi, 2, '.', ",");
            if (Number(nilaivalidasi) < 0) {
                nilaivalidasi = 0;
            }

            //bootbox.alert("Nilai == "+nilai+" || Nilai Kontrak Tidak Boleh Melebihi Nilai Sisa Kontrak = "+nilaivalidasi);
            bootbox.alert("Nilai Kontrak Tidak Boleh Melebihi Nilai Sisa Kontrak Senilai Rp. " + nilaival);
            $("#nilaiSpd").val(nilaivalidasi); // edit 20 jan 16 by zainab ; set nilai kontrak ke nilai valisadi
        }
    }

}

function ceknilaibast() {
    var nilaikontrak = $("#nilaiSpd").val();

    if (Number(nilaikontrak) < Number(nilaibastkontrak)) {
        var nilaival = accounting.formatNumber(nilaibastkontrak, 2, '.', ",");

        //bootbox.alert("Nilai == "+nilai+" || Nilai Kontrak Tidak Boleh Melebihi Nilai Sisa Kontrak = "+nilaivalidasi);
        bootbox.alert("Nilai Kontrak Tidak Boleh Lebih Kecil dari Nilai BAST Senilai Rp. " + nilaival);
        $("#nilaiSpd").val(nilaibastkontrak);
    }
}

function getKodeMultiyear() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $("#idKegiatan").val();

    $.getJSON(getbasepath() + "/kontrak/json/getKodeMultiyear", {tahun: tahun, idskpd: idskpd, idkegiatan: idkegiatan},
    function (result) {
        //console.log("Kode Multiyear = "+result);

        if (result == 1) {
            document.getElementById("labelmultiyear").style.display = "block"; // tampil
            document.getElementById("labelnomultiyear").style.display = "block"; // tampil
            $('#kodeMultiyear').val(1);
        } else {
            $('#kodeMultiyear').val(0);
            $('#noKontrakTotal').val("");
        }
    });
}

function unformat() {
    var nilaikontrak = accounting.unformat($('#nilaiSpd').val(), ",");
    $('#nilaiSpd').val(nilaikontrak);
    console.log("nilaikontrak unformat = " + nilaikontrak);

}


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function isNumberNilai(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode !== 44) {
        return false;
    }

    return true;
}

function cekDataBank() {
    var kodebank = $('#kodeBankTransfer').val();
    var npwp = $('#idRekananNpwp').val();

    if (kodebank == "111") { // BANK DKI
        console.log("bank utama = bank DKI");

    } else {

    }

}

function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#rekeningBank').val().replace(/\D/g, '');

    var panjangnorek = norek.length;
    var kodeva = $('#kodeVA').val();

    console.log("getDataBankDki()+ panjang norek == " + panjangnorek);

    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkialamat').val("");
    $('#dkinama').val("");


    if ((norek !== "") && (kodeva == "0")) {
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
            success: function (data) {

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    response76 = "00";
                    $('#namaRekananBank').val(data.nama);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"].trim());
                    $('#dkialamat').val("");
                    response76 = data["response"];
                    $('#namaRekananBank').val(data["name"].trim());
                }
                /*
                 if (data.nama !== "GENERAL ERROR") {
                 $('#namaRekananBank').val(data.nama);
                 }
                 */

                if (kodebank !== '111' && response76 == "76") {
                    bootbox.alert("Data Rekening Tidak Ditemukan");
                }

            },
            error: function (xhr) {
                console.error(xhr);
                bootbox.alert("Sambungan Bank DKI Terputus");
                //$('#btnSimpan').attr("disabled", true);
            }
        }).always(function (data) {

        });
    }
    /* yang langsung ke ws bank dki
     if ((kodebank == "111") && (norek !== "") && (kodeva == "0")) {
     var varurl = getbasepath() + "/kontrak/json/kirimpostdata";
     
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
     
     if (data.nama !== "GENERAL ERROR"){
     $('#rekanan').val(data.nama);
     }
     
     },
     error: function(xhr) {
     console.error(xhr);
     }
     }).always(function(data) {
     $('#buttoninduk').attr("disabled", false);
     $("#spdBTLMasterform").valid();
     });
     
     } */

}

function setkodeVA() {

    if (document.getElementById('cbVA').checked) {
        $("#kodeVA").val("1");
    } else {
        $("#kodeVA").val("0");
    }

    var kodebank = $('#kodeBankTransfer').val();

    // if (kodebank == "111") {
    getDataBankDki();
    //}

    $("#spdBTLMasterform").valid();
}

function cekNoKontrak() {
    var nokontrak = $("#noKontrak").val();
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkontrak = $('#idKontrak').val();

    if (idkontrak == "" || idkontrak == null || idkontrak == "null") {
        idkontrak = "-1111";
    }
    //console.log("cekNoKontrak");

    $.getJSON(getbasepath() + "/kontrak/json/getNoKontrakCek", {tahun: tahun, idskpd: idskpd, nokontrak: nokontrak, idkontrak: idkontrak},
    function (result) {
        var banyak = result.aData.length;

        //console.log("banyak = "+banyak);
        if (banyak > 0) {
            var kegiatan = result.aData[0]['pesan'];
            var spd = result.aData[0]['noSpd'];

            bootbox.alert("No Kontrak Tersebut Telah Digunakan Pada SPD No " + spd + " Kegiatan " + kegiatan);
            $("#noKontrak").val("");
        }
    });
}


function getDataNPWP() {
    var varurl = getbasepath() + "/kontrak/json/inquirynpwp";
    var dataac = [];
    var datajour = {
        npwp: $('#idRekananNpwp').val()
    };
    dataac = datajour;
//
    $.ajax({
        url: varurl,
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataac),
        contentType: 'text/plain; charset=utf-8',
        headers: {
            'Accept': 'application/json, text/javascript',
            'Content-Type': 'application/json',
        },
        success: function (result) {

            if (result['error'] == null) {
                $("#namaNpwp").val(result['NAMA']);
                $("#alamatNpwp").val(result['ALAMAT']);
                $("#kotaNpwp").val(result['KABKOT']);
                $("#kodePkp").val(result['STATUS_PKP']);
            } else {
                bootbox.alert(result['error']);
            }
        },
        error: function () {
            bootbox.alert("Sambungan DJP Terputus");
        }
    });
}

function validasiAngg(nilai) {
    var nilaikontrak = accounting.unformat(nilai, ",");
    var sisaangg = accounting.unformat($("#nilaiSisa").val(), ",");

    console.log("nilaikontrak = " + nilaikontrak);
    console.log("sisaangg = " + sisaangg);


    if (Number(nilaikontrak) > Number(sisaangg)) {
        console.log("masuk nilai kontrak > sisaangg ");
        bootbox.alert("Nilai Kontrak Tidak Boleh Lebih Besar dari Sisa Anggaran ");
        //$('#nilaiSpd').autoNumeric('set', 0);
        $('#nilaiSpd').val(0);

    }

}

function getSisaAnggaran() {
    var idskpd = $("#idskpd").val();
    var idkegiatan = $("#idKegiatan").val();
    var idkontrak = $("#idKontrak").val();

    $.getJSON(getbasepath() + "/kontrak/json/getSisaAnggaran", {idskpd: idskpd, idkegiatan: idkegiatan, idkontrak: idkontrak},
    function (result) {

        var sisa = result.aData['nilaiSisa'];
        $("#nilaiSisa").val(accounting.formatNumber(sisa, 2, '.', ","));
        $("#nilaiKontrakOrg").val(sisa);
    });
}

function setSKP() {

    if (document.getElementById('cbSkp').checked) {
        $("#kodeSuratKet").val("1");
    } else {
        $("#kodeSuratKet").val("0");
    }

}

function setLabelVA() {
    if ($("#kodeBankTransfer").val() == "111") {
        document.getElementById("labelva").style.display = "block";
    } else {
        document.getElementById("labelva").style.display = "none";
    }
}