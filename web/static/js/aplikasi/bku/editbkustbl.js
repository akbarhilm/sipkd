$(document).ready(function() {
    getKodeSA();
    getBulanBySaldoAwal(); //getKodeTutup();

});

// global variable
var jumbarisstbl, banyakTutupMax, kodeTutupMax, bulanTutupMax, kodeSA, idbku;

function getKodeSA() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var nobku = $('#noBKU').val();

    $.getJSON(getbasepath() + "/bku/json/getKodeSA", {tahun: tahun, idskpd: idskpd, nobku: nobku},
    function(result) {

        var kode = result.aData['kodeSA'];
        idbku = result.aData['idBku'];
        var nilai = accounting.formatNumber(result.aData['nilaiBku'], 2, '.', ",");

        if (kode > 0) {
            kodeSA = "0";
            $('#tabelStBl').show();
            document.getElementById("labelpengeluaran").style.display = "none";
            document.getElementById('pilihAkunLain').style.visibility = 'visible';
            gridstbl();

        } else {
            kodeSA = "1";
            $('#tabelStBl').hide();
            document.getElementById('pilihAkunLain').style.visibility = 'hidden';
            document.getElementById("labelpengeluaran").style.display = "block";
            $('#pengeluaran').val(nilai);
        }

    });
}

function gridstbl() {
    jumbarisstbl = 0;

    if (typeof myTablestbl == 'undefined') {
        myTablestbl = $('#stbltable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listakunstbl",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "nobku", value: $("#noBKU").val()},
                {name: "beban", value: $("#beban").val()},
                {name: "novalidasi", value: $("#noBukti").val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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

                var nilaisetor = "<input type='text' name='nilaisetor" + index + "' id='nilaisetor" + index + "'  class='inputmoney'  value='" + aData['nilaiSetor'] + "' readOnly='true' />";
                var kodeakun = "<input type='hidden' name='valkodeakun" + index + "' id='valkodeakun" + index + "'  value='" + aData['kodeakun'] + "' />";
                var idbas = "<input type='hidden' id='akun" + index + "' name='akun" + index + "'value='" + aData['idBas'] + "' />";
                var nilaimasuk = "<input type='hidden' id='nilaiMasuk" + index + "' name='nilaiMasuk" + index + "'value='" + "0" + "' />";
                var nilaikeluar = "<input type='hidden' id='nilaiKeluar" + index + "' name='nilaiKeluar" + index + "'value='" + aData['nilaiSetor'] + "' />";
                var idkeg = "<input type='hidden' name='idkeg" + index + "' id='idkeg" + index + "' value='" + aData['idKegiatan'] + "' readOnly='true' />";
                var kodekeg = "<input type='hidden' id='kegiatan" + index + "' name='kegiatan" + index + "'value='" + aData['kodeKeg'] + "' />";
                var namaakun = "<textarea id='namaakun" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaakun'] + "</textarea>";
                var namakeg = "<textarea id='namakeg" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaKeg'] + "</textarea>";

                jumbarisstbl = jumbarisstbl + 1;

                $('td:eq(0)', nRow).html(index + kodeakun + idbas + nilaimasuk + nilaikeluar);
                $('td:eq(2)', nRow).html(namakeg);
                $('td:eq(4)', nRow).html(namaakun);
                $('td:eq(5)', nRow).html(nilaisetor + idkeg + kodekeg);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "right"}
            ]
        });
    }
    else
    {
        myTablestbl.fnClearTable(0);
        myTablestbl.fnDraw();
    }
}


function cekLengkap() {
    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBukti").val();
    var tglDok = $("#tglDok").val();
    var filling = $("#inboxFile").val();
    var datalengkap = true;
    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();
    var uraian = $("#uraian").val();

    if (kodeSA == "1") {
        if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
        } else {
            updateSA();
        }
    } else {
        if (jumbarisstbl > 0) {

            for (var a = 1; a <= jumbarisstbl; a++) {

                if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                    datalengkap = false;
                }
            }

            if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
            } else {
                update();
            }
        }
    }

}

function update() {

    var varurl = getbasepath() + "/bku/json/updatestbl";
    var dataac = [];
    var nilailist = [];
    var i;

    var fileinbox = $('#inboxFile').val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK, idbas, kodeakun;
    var idspd = "";
    var idbas, kodeakun;

    uraian = $("#uraian").val();
    namaPPTK = "";
    nipPPTK = "";

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    if ($("#beban").val() == "TU") {
        idspd = $("#idSpd").val();
    }


    for (i = 0; i < jumbarisstbl; i++) { // list update
        var id2 = i + 1;

        /*
         if ($('#kodeTransaksi').val() == "ST") {
         if ($("#beban").val() == "TU") {
         idbas = $("#akun" + id2).val();
         kodeakun = $("#valkodeakun" + id2).val();
         
         } else { //LS
         idbas = "3897";
         kodeakun = "1.1.01.03.01.002";
         }
         
         } else {
         idbas = $("#akun" + id2).val();
         kodeakun = $("#valkodeakun" + id2).val();
         } */

        idbas = $("#akun" + id2).val();
        kodeakun = $("#valkodeakun" + id2).val();

        var pararr2 = {
            idBku: $("#idBku" + id2).val(),
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id2).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id2).val(), ",")),
            idbas: idbas, //$("#akun" + id2).val(),
            kodekegiatan: $("#kegiatan" + id2).val(),
            uraianbukti: uraian, //$("#namaakun" + id2).val(),
            idkegiatan: $("#idkeg" + id2).val(),
            kodeakun: kodeakun, //$("#valkodeakun" + id2).val(), //$('select[name="akun' + id2 + '"]').find(":selected").text()
            idspd: idspd
        };
        nilailist[i] = pararr2;

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
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: $("#uraian").val(),
        noBKU: $('#noBKU').val(),
        carabayar: $("#kodePembayaran").val(),
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
        gridstbl();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
    });
}

function updateSA() {
    var tglPost = $("#tglPosting").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var bulan = $("#bulan").val();

    if (bulanTglPost !== bulan) {
        bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
    } else {
        var varurl = getbasepath() + "/bku/json/prosesupdatesetorup";
        var dataac = [];

        var fileinbox = $('#inboxFile').val();
        var tglPost = $("#tglPosting").val();
        var dd, mm, yy, tanggal;
        var uraian, namaPPTK, nipPPTK;
        var akun, idbas;

        uraian = $("#uraian").val();

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
            idbku: idbku.toString(),
            akun: "",
            idbas: 0

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
