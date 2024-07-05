
$(document).ready(function() {
    getPotUmk();

    $("#btlspdtablebody").hide();
    gettotalspm($("#nospm").val());
    //getnilaikontrak($("#nospm").val());
    getkontrak($("#nospm").val());
    //document.getElementById("cekpphps22").disabled = true;
});

var varpersen = new Array();
var varkjs = new Array();
var nilaikontrak = new Array();
var nilaijamsostek;
var nilaippn10persen;
var nilaipphps22;
var addoredit = new Array();

function getPotUmk() {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/getPotUmk", {idspm: $('#nospm').val(), idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        var potUmkSpm = result.aData['cPot'];
        var nilaiPotUmkSpm = result.aData['nilaiPot'];

        document.getElementById("gridpotongandiv").style.display = "none";
        document.getElementById("btnSimpan").style.visibility = "hidden";

        if (potUmkSpm == "1" && nilaiPotUmkSpm == 0) {
            bootbox.alert("Silahkan Input Nilai Potongan UMK terlebih Dulu.");
        } else {
            document.getElementById("gridpotongandiv").style.display = "block";
            document.getElementById("btnSimpan").style.visibility = "visible";
        }

    });
}


function gettotalspm(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/totspmjson/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        $('#totspm').val(accounting.formatNumber(result.aData[0]['totSpm'], 2, '.', ","));
        $('#totspmhide').val(result.aData[0]['totSpm']);
        getValTabel(idspm);
        hitung();
    });
}

function getValTabel(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/valtabel/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        // console.log(result)
        var i, c;
        var persenCek;

        for (i = 0; i < 11; i++) {

            c = i + 1;
            varpersen[c] = result.aData[i]['pPot'];
            totspm = $('#totspmhide').val();

            addoredit[c] = result.aData[i]['kodeEdit'];
            //alert(totspm);
            if (c == 1) {

            }
            else if (c == 2) {
            } else {
                $('#vpot' + c).val(accounting.formatNumber(result.aData[i]['nilaiPot'], 2, '.', ","));
            }
            //console.log(" c " + c + " = " + result.aData[i]['pot'])
            $("#tampildataholder" + c).append(result.aData[i]['pot']);
            //console.log(" tampildataholder " + c + " = " + $("#tampildataholder" + c).val());
            $('#cpot' + c).val(result.aData[i]['cPot']);
            $('#status' + c).val(result.aData[i]['status']);

            //tambahan ppn 1%
            //---begin---
            if (c == 1) {
                varpersen[1] = result.aData[i]['pPot'];
            }
            //---end---
            else if (c == 6) {
                var cekstatusppn = result.aData[i]['statusPPN'];
                //varpersen[5] = cekstatusppn == 1?result.aData[i]['pPot']/10:result.aData[i]['pPot'];
                varpersen[6] = result.aData[i]['pPot'];
                $("#cekpersen6status").val(cekstatusppn);
                //tambahan kode jenis setor pajak
                if (result.aData[i]['kodeJenisSetor'] == '0')
                    document.getElementById("csetorpajak6").value = 104;
                else
                    document.getElementById("csetorpajak6").value = result.aData[i]['kodeJenisSetor'];


                //tambahan kode jenis setor pajak
            } else if (c == 9) {
                console.log("kodeJenisSetor = " + result.aData[i]['kodeJenisSetor']);
                if (result.aData[i]['kodeJenisSetor'] == '0'){
					$('#tampilancsetorpajak9').append('100');
                    document.getElementById("csetorpajak9").value = 100;
				} else {
                    document.getElementById("csetorpajak9").value = result.aData[i]['kodeJenisSetor'];
				}

            } else if (c == 10) {
                var cekstatusppn = result.aData[i]['statusPPN'];
                $("#cekpersen10").val(cekstatusppn);
                //varpersen[9] = cekstatusppn == 1?result.aData[i]['pPot']/10:result.aData[i]['pPot'];
                varpersen[10] = result.aData[i]['pPot'];
                //console.log("Persen 10 = "+result.aData[i]['pPot']);
                //console.log("cekstatusppn = "+cekstatusppn);
                if (result.aData[i]['kodeJenisSetor'] == '0')
                    $('#tampilancsetorpajak10').empty();
                else {
                    $('#tampilancsetorpajak10').append(result.aData[i]['kodeJenisSetor']);
                    $('#csetorpajak10').val(result.aData[i]['kodeJenisSetor']);
                }
            } else if (c == 11) {
                var cekstatusppn = result.aData[i]['statusPPN'];
                $("#cekpersen11").val(cekstatusppn);
                //varpersen[9] = cekstatusppn == 1?result.aData[i]['pPot']/10:result.aData[i]['pPot'];
                varpersen[11] = result.aData[i]['pPot'];
                //console.log("Persen 10 = "+result.aData[i]['pPot']);
                //console.log("cekstatusppn = "+cekstatusppn);
                if (result.aData[i]['kodeJenisSetor'] == '0')
                    $('#tampilancsetorpajak11').empty();
                else {
                    $('#tampilancsetorpajak11').append(result.aData[i]['kodeJenisSetor']);
                    $('#csetorpajak11').val(result.aData[i]['kodeJenisSetor']);
                }
            }

            //console.log("index "+c+" - keterangan add or edit ==== "+addoredit[c]);

        }

        setPPN10(result.aData[0]['nilaiPot']);
        setPPH22(result.aData[1]['nilaiPot']);

        nilaippn10persen = $('#vpot1').val();
        nilaipphps22 = $('#vpot2').val();

        /*
         var nilaiasuransi = result.aData[2]['nilaiPot'];
         var banyak = nilaiasuransi/20000;
         
         if (nilaiasuransi > 0){
         $("#asuransi3").val(banyak);
         }
         */
        setJamsostek();

        persenCek = varpersen[6];

        if (persenCek == ".5") {
            persenCek = "0.5";
        }

        if (varpersen[10] == ".5") {
            varpersen[10] = "0.5";
        }

        document.getElementById("persen1").value = varpersen[1]; //tambahan ppn 1%
        document.getElementById("persen6").value = persenCek; //varpersen[5];
        document.getElementById("persen10").value = varpersen[10];
        document.getElementById("persen11").value = varpersen[11];


    });

    $("#btlspdtablebody").show();

}

function hitung() {
    var totspm, dati;
    totspm = $('#totspmhide').val();
    dati = (totspm / 1.1) * (10 / 100);
    $('#vpot1').val(accounting.formatNumber(dati, 2, '.', ","));
    dati = (totspm / 1.1) * (1.5 / 100);
    $('#vpot2').val(accounting.formatNumber(dati, 2, '.', ","));
}

function hitung6() {
    var totspm, dati, persen;
    var cekstatus = $("#cekpersen6status").val();
    var pengali = cekstatus == 0 ? 1 : 1.1;
    totspm = $('#totspmhide').val();
    persen = $("#persen6").val();
    dati = (totspm / pengali) * (persen / 100);
    $('#vpot' + 6).val(accounting.formatNumber(dati, 2, '.', ","));
    varpersen[6] = $("#persen6").val();
}

function hitung10() {
    var totspm, dati, persen;
    var cekstatus = $("#cekpersen10").val();
    var pengali = cekstatus == 0 ? 1 : 1.1;
    //var pengali = 1.1; // default PPN
    totspm = $('#totspmhide').val();
    persen = $("#persen10").val();
    dati = (totspm / pengali) * (persen / 100);
    //console.log(" pengali " + pengali + "  cekstatus " + cekstatus + " dati " + dati)
    $('#vpot' + 10).val(accounting.formatNumber(dati, 2, '.', ","));
    varpersen[10] = $("#persen10").val();
}

function hitung11() {
    var totspm, dati, persen;
    var cekstatus = $("#cekpersen11").val();
    var pengali = cekstatus == 0 ? 1 : 1.1;
    //var pengali = 1.1; // default PPN
    totspm = $('#totspmhide').val();
    persen = $("#persen11").val();
    dati = (totspm / pengali) * (persen / 100);
    //console.log(" pengali " + pengali + "  cekstatus " + cekstatus + " dati " + dati)
    $('#vpot' + 11).val(accounting.formatNumber(dati, 2, '.', ","));
    varpersen[11] = $("#persen11").val();
}

function setSetorPajak9(cpns) {
    $("#tampilancsetorpajak9").empty();
    var jenisSetorPajak = 0;
    if (cpns == 0) {
        jenisSetorPajak = 100;
    } else {
        jenisSetorPajak = 402;
    }
    $("#tampilancsetorpajak9").append(jenisSetorPajak);
    $("#csetorpajak9").val(jenisSetorPajak);
}

function setSetorPajak10(persen) {
    $("#tampilancsetorpajak10").empty();
    var jenisSetorPajak = 0;
    if (persen == 0) {
        jenisSetorPajak = 0;
    } else if (persen == 0.5) {
        jenisSetorPajak = 420;
    } else if (persen == 10) {
        jenisSetorPajak = 403;
    } else {
        jenisSetorPajak = 409;
    }
    $("#tampilancsetorpajak10").append(jenisSetorPajak);
    $("#csetorpajak10").val(jenisSetorPajak);
}

function setSetorPajak11(persen) {
    $("#tampilancsetorpajak11").empty();
    var jenisSetorPajak = 0;
    if (persen == 2.64) {
        jenisSetorPajak = 411;
    } else if (persen == 1.2) {
        jenisSetorPajak = 410;
    } else if (persen == 1.8) {
        jenisSetorPajak = 412;
    } else {
        jenisSetorPajak = 0;
    }
    $("#tampilancsetorpajak11").append(jenisSetorPajak);
    $("#csetorpajak11").val(jenisSetorPajak);
}

function hitungasuransi() {
    var jumlah, banyak;
    banyak = $("#asuransi3").val();
    jumlah = (banyak * 20000);

    $('#vpot3').val(accounting.formatNumber(jumlah, 2, '.', ","));
}

function validateNumber(evt) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            // numbers
            key >= 48 && key <= 57 ||
            // Numeric keypad
            key >= 96 && key <= 105 ||
            // Backspace and Tab and Enter
            key == 8 || key == 9 || key == 13 ||
            // Home and End
            key == 35 || key == 36 ||
            // left and right arrows
            key == 37 || key == 39 ||
            // Del and Ins
            key == 46 || key == 45) {
        // input is VALID
    }
    else {
        // input is INVALID
        e.returnValue = false;
        if (e.preventDefault)
            e.preventDefault();
    }
}

function cekmoney() {
    // $(".edit2").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
}

function ceksubmitnilai( ) {
    var nilaipph21 = accounting.unformat($('#vpot9').val(), ",");
    var cpegpns = $('#cpns').val() == 0 ? $('#cpeg').val() : $('#cpnsgol').val();
    console.log("nilaipph21 = "+nilaipph21);
    if(nilaipph21 > 0 && cpegpns == 0){
        bootbox.alert("Jika PPh 21 ada nilainya, Anda Harus Pilih Jenis Pegawai.");
    } else {
        //bootbox.alert("proses");
        submitnilai( );
    }
}

function submitnilai( ) {
    var varurl = getbasepath() + "/spmpotnonayat/json/prosespindahsimpan";
    var dataac = [];
    var cekps22 = $("#cekpphps22status").val();
    var persenps22 = cekps22 == 0 ? 1.5 : 1.5;
    var cekppnjenis = $("#persen1").val(); //tambahan ppn 1%
    var persenppnjenis = document.getElementById("cekppn10").checked == true ? cekppnjenis == 1 ? 1 : 10 : 0; //tambahan ppn 1%
    var cpnsdb = $('#cpns').val() == 0 ? $('#cpns').val() : $('#cpnsgol').val();
    var cpegdb = $('#cpeg').val();
    var datapeg = {
        cpot1: $("#cpot1").val(),
        cpot2: $("#cpot2").val(),
        cpot3: $("#cpot3").val(),
        cpot4: $("#cpot4").val(),
        cpot5: $("#cpot5").val(),
        cpot6: $("#cpot6").val(),
        cpot7: $("#cpot7").val(),
        cpot8: $("#cpot8").val(),
        cpot9: $("#cpot9").val(),
        cpot10: $("#cpot10").val(),
        cpot11: $("#cpot11").val(),
        vpot1: $("#vpot1").val(),
        vpot2: $("#vpot2").val(),
        vpot3: $("#vpot3").val(),
        vpot4: $("#vpot4").val(),
        vpot5: $("#vpot5").val(),
        vpot6: $("#vpot6").val(),
        vpot7: $("#vpot7").val(),
        vpot8: $("#vpot8").val(),
        vpot9: $("#vpot9").val(),
        vpot10: $("#vpot10").val(),
        vpot11: $("#vpot11").val(),
        status1: $("#status1").val(),
        status2: $("#status2").val(),
        status3: $("#status3").val(),
        status4: $("#status4").val(),
        status5: $("#status5").val(),
        status6: $("#status6").val(),
        status7: $("#status7").val(),
        status8: $("#status8").val(),
        status9: $("#status9").val(),
        status10: $("#status10").val(),
        status11: $("#status11").val(),
        persen1: persenppnjenis, //tambahan ppn 1% sebelumnya ==> persen1: 0,
        persen2: persenps22,
        persen3: 0,
        persen4: 0,
        persen5: 0,
        persen6: varpersen[6],
        persen7: 0,
        persen8: 0,
        persen9: 0,
        persen10: varpersen[10],
        persen11: varpersen[11],
        kap1: $("#cakunpajak1").val(), //tambahan ppn 1% sebelumnya ==> persen1: 0,
        kap2: $("#cakunpajak2").val(),
        kap3: 0,
        kap4: 0,
        kap5: 0,
        kap6: $("#cakunpajak6").val(),
        kap7: 0,
        kap8: 0,
        kap9: $("#cakunpajak9").val(),
        kap10: $("#cakunpajak10").val(),
        kap11: $("#cakunpajak11").val(),
        kjs1: $("#csetorpajak1").val(), //tambahan ppn 1% sebelumnya ==> persen1: 0,
        kjs2: $("#csetorpajak2").val(),
        kjs3: 0,
        kjs4: 0,
        kjs5: 0,
        kjs6: $("#csetorpajak6").val(),
        kjs7: 0,
        kjs8: 0,
        kjs9: $("#csetorpajak9").val(),
        kjs10: $("#csetorpajak10").val(),
        kjs11: $("#csetorpajak11").val(),
        addoredit1: addoredit[1],
        addoredit2: addoredit[2],
        addoredit3: addoredit[3],
        addoredit4: addoredit[4],
        addoredit5: addoredit[5],
        addoredit6: addoredit[6],
        addoredit7: addoredit[7],
        addoredit8: addoredit[8],
        addoredit9: addoredit[9],
        addoredit10: addoredit[10],
        addoredit11: addoredit[11],
        cekpphps22status: $("#cekpphps22status").val(),
        cekpersen6status: $("#cekpersen6status").val(),
        cekpersen10: $("#cekpersen10").val(),
        cekpersen11: $("#cekpersen11").val(),
        cpnsdb: cpnsdb,
        cpegdb: cpegdb,
        idspm: $("#nospm").val()
    };
    dataac = datapeg;

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
        //grid();
        bootbox.alert("Data SPM Potongan Ayat berhasil disimpan");
    });
}

function settgl() {
    var isi = $("#ssspm").val();
    var tahun = isi.substr(0, 4);
    var bulan = isi.substr(5, 2);
    var hari = isi.substr(8, 2);
    var hasil = hari + "/" + bulan + "/" + tahun;

    $("#sispm").val(hasil);
    // alert("#sspm");
}

function cek() {

    //bootbox.alert("Nilai kontrak = " +nilaikontrak+ " || Nilai jamsostek = " + nilaijamsostek);
    bootbox.alert("Nilai ppn 10 = " + $('#vpot1').val());
}

// sudah tidak digunakan function ini karena diganti dengan getkontrak per tanggal 13122018 by mus
function getnilaikontrak(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/nilaikontrak/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        nilaikontrak = result.aData[0]['nilaiKontrak'];

        getjamsostek(nilaikontrak);
    });
}

function getjamsostek(nilaikontrak) {
    $.getJSON(getbasepath() + "/spmpotnonayat/json/getjamsostek", {nilaikontrak: nilaikontrak},
    function(result) {
        if (result.aData.length > 0) {
            nilaijamsostek = result.aData[0]['nilaiJamsostek'];
        }

    });
}

function hitungPPN10() {
    var hasil;
    totspm = $('#totspmhide').val();
    //tambahan ppn 1% permintaan pak gunawan di wa tgl 09-12-2016
    //---begin---
    var jenisppn = $("#persen1").val();
    //console.log("jenis ==== " + jenisppn);
    if (jenisppn == 10) {
        hasil = (totspm / 1.1) * (10 / 100);
    } else if (jenisppn == 1) {
        hasil = (totspm / 1.01) * (1 / 100);
    } else {
        hasil = 0;
    }
    //---end---

    //awalnya sebelum ada tambahan 1%
    //hasil = (totspm / 1.1) * (10 / 100);

    if (document.getElementById("cekppn10").checked == true) {
        $('#vpot1').val(accounting.formatNumber(hasil, 2, '.', ","));

    } else {
        //bootbox.alert("NOL?");
        $('#persen1').val(0); //tambahan ppn 1% permintaan pak gunawan di wa tgl 09-12-2016
        $('#vpot1').val(0);
    }
}

function hitungPPHPS22() {
    var hasil;
    totspm = $('#totspmhide').val();
    var statuspph22 = $("#cekpphps22status").val();
    if (statuspph22 == 0) {
        hasil = (totspm) * (1.5 / 100);
    } else {
        hasil = (totspm / 1.1) * (1.5 / 100);
    }



    if (document.getElementById("cekpphps22").checked == true) {
        $('#vpot2').val(accounting.formatNumber(hasil, 2, '.', ","));

    } else {
        $('#vpot2').val(0);
    }
}

function hitungJamostek() {
    if (document.getElementById("cekjamsostek").checked == true) {
        if (nilaikontrak >= 110000000) {
            $('#vpot5').val(accounting.formatNumber(nilaijamsostek, 2, '.', ","));
        } else {
            nilaijamsostek = 0.0024 * (nilaikontrak / 1.1);
            $('#vpot5').val(accounting.formatNumber(nilaijamsostek, 2, '.', ","));
        }
    } else {
        $('#vpot5').val(0);
    }
}

function setJamsostek() {
    var nilai = parseFloat(accounting.unformat($('#vpot5').val(), ",")); // jamsostek

    if (nilai !== 0) {
        $("#cekjamsostek").prop("checked", true);
    } else {
        $("#cekjamsostek").prop("checked", false);
    }

}

function setPPN10(nilai) {

    if (nilai !== 0) {
        $("#cekppn10").prop("checked", true);
    } else {
        $("#cekppn10").prop("checked", false);

    }
    $('#vpot1').val(accounting.formatNumber(nilai, 2, '.', ","));
    //$('#vpot1').val(nilai);

}

function setPPH22(nilai) {

    if (nilai !== 0) {
        $("#cekpphps22").prop("checked", true);
    } else {
        $("#cekpphps22").prop("checked", false);

    }
    $('#vpot2').val(accounting.formatNumber(nilai, 2, '.', ","));
    //$('#vpot2').val(nilai);
    //---------
    var nilaitotspm = parseFloat($('#totspmhide').val());
    var vpot = nilai;

    var hasilnonppn = vpot == (nilaitotspm) * (1.5 / 100) ? 0 : 1;
    $('#cekpphps22status').val(hasilnonppn).change();

}


function getkontrak(idspm) {
    $.getJSON(getbasepath() + "/spmpotnonayat/json/getkontrak/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        nilaikontrak = result.aData[0]['nilaiKontrak'];
        var pkp = result.aData[0]['kodePkp'];
        var skb = result.aData[0]['kodeSkb'];
        var blj = result.aData[0]['kodeBlj'];
        setlistpajak(blj, skb, pkp);
        getjamsostek(nilaikontrak);
    });
}

function setlistpajak(blj, skb, pkp) {
    document.getElementById("cpnsgol").style.display = "none";

    var statppn = 1;
    var statpph21 = 1;
    var statpph22 = 1;
    var statpph23 = 1;
    var statpph4 = 1;

    if (blj == '1') {
        statppn = 0;
        statpph21 = 1;
        statpph22 = 0;
        statpph23 = 0;
		statpph4 = 0;
    } else if ((blj == '2' && skb == '1' && pkp == '1') || (blj == '3' && skb == '1' && pkp == '1')) {
        statppn = 1;
        statpph21 = 0;
        statpph22 = 0;
        statpph23 = 0;
		statpph4 = 1;
    } else if ((blj == '2' && skb == '1' && pkp == '0') || (blj == '3' && skb == '1' && pkp == '0')) {
        statppn = 0;
        statpph21 = 0;
        statpph22 = 0;
        statpph23 = 0;
		statpph4 = 1;
    } else if (blj == '2' && skb == '0' && pkp == '1') {
        statppn = 1;
        statpph21 = 0;
        statpph22 = 1;
        statpph23 = 0;
		statpph4 = 1;
    } else if (blj == '2' && skb == '0' && pkp == '0') {
        statppn = 0;
        statpph21 = 0;
        statpph22 = 1;
        statpph23 = 0;
		statpph4 = 1;
    } else if (blj == '3' && skb == '0' && pkp == '1') {
        statppn = 1;
        statpph21 = 0;
        statpph22 = 0;
        statpph23 = 1;
		statpph4 = 1;
    } else if (blj == '3' && skb == '0' && pkp == '0') {
        statppn = 0;
        statpph21 = 0;
        statpph22 = 0;
        statpph23 = 1;
		statpph4 = 1;
    }

    settampilanppn(statppn);
    settampilanpph21(statpph21);
    settampilanpph22(statpph22);
    settampilanpph23(statpph23);
	settampilanpph4(statpph4);
}

function settampilanpph21(status) {
    if (status == '1') {
        document.getElementById("vpot9").readOnly = false;
        document.getElementById("cpns").disabled = false;
        document.getElementById("cpnsgol").disabled = false;
        document.getElementById("cpeg").disabled = false;
    }
    else {
        document.getElementById("vpot9").readOnly = true;
        document.getElementById("cpns").disabled = true;
        document.getElementById("cpnsgol").disabled = true;
        document.getElementById("cpeg").disabled = true;
    }
}

function settampilanpph22(status) {
    if (status == '1') {
        document.getElementById("cekpphps22").disabled = false;
        document.getElementById("cekpphps22status").disabled = false;
    }
    else {
        document.getElementById("cekpphps22").disabled = true;
        document.getElementById("cekpphps22status").disabled = true;
    }
}

function settampilanpph23(status) {
    if (status == '1') {
        document.getElementById("cekpersen6status").disabled = false;
        document.getElementById("persen6").disabled = false;
    }
    else {
        document.getElementById("cekpersen6status").disabled = true;
        document.getElementById("persen6").disabled = true;
    }
}

function settampilanppn(status) {
    if (status == '1') {
        document.getElementById("cekppn10").disabled = false;
        document.getElementById("persen1").disabled = false;
    }
    else {
        document.getElementById("cekppn10").disabled = true;
        document.getElementById("persen1").disabled = true;
    }
}

function settampilanpph4(statpph4) {
	if (statpph4 == '1') {
        document.getElementById("cekpersen10").disabled = false;
        document.getElementById("persen10").disabled = false;
		document.getElementById("cekpersen11").disabled = false;
        document.getElementById("persen11").disabled = false;
    }
    else {
        document.getElementById("cekpersen10").disabled = true;
        document.getElementById("persen10").disabled = true;
		document.getElementById("cekpersen11").disabled = true;
        document.getElementById("persen11").disabled = true;
    }
}

function setgolpeg() {
    var cpns = $('#cpns').val();
    if (cpns == '0') {
        document.getElementById("cpnsgol").style.display = "none";
        document.getElementById("cpeg").style.display = "block";
        $('#cpeg').val(0);
        //$('#vpot9').val(0);
        $('#vpot9').autoNumeric('set', 0);
    } else {
        document.getElementById("cpnsgol").style.display = "block";
        document.getElementById("cpeg").style.display = "none";
        $('#cpnsgol').val(0);
        $('#vpot9').autoNumeric('set', 0);
    }
}

function hitungpph21() {
    var cpeg = $('#cpeg').val();
    var cpns = $('#cpns').val();
    var cpnsgol = $('#cpnsgol').val();
    var totspm = $('#totspmhide').val();
    var hasil = 0;
    //masih hitung manual nantinya akan dipindahkan ke service hitung pajak
    if (cpns == '0') {
        if (cpeg == '1') {
            hasil = 0;
        } else if (cpeg == '2') {
            if (totspm <= 50000000)
                hasil = 0.05 * 0.5 * totspm;
            else if (totspm > 50000000 && totspm <= 250000000)
                hasil = 0.15 * 0.5 * totspm;
            else if (totspm > 250000000 && totspm <= 500000000)
                hasil = 0.25 * 0.5 * totspm;
            else
                hasil = 0.30 * 0.5 * totspm;
        } else if (cpeg == '3') {
            if (totspm <= 50000000)
                hasil = 0.05 * totspm;
            else if (totspm > 50000000 && totspm <= 250000000)
                hasil = 0.15 * totspm;
            else if (totspm > 250000000 && totspm <= 500000000)
                hasil = 0.25 * totspm;
            else
                hasil = 0.30 * totspm;
        }
        else {
            hasil = 0;
        }
    } else {
        if (cpnsgol == '3') {
            hasil = 0.05 * totspm;
        } else if (cpnsgol == '4') {
            hasil = 0.15 * totspm;
        } else {
            hasil = 0;
        }
    }

    $('#vpot9').autoNumeric('set', hasil);
}