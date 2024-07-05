
$(document).ready(function() {
//gettime();
    //getKodeStan();
});
var kodestan, nomorref;
var update12, update13, update39;
var rbit4, rbit11, rbit12, rbit13, rbit37, rkodebank, rnorektujuan, rnamatujuan;
var ralamat, rnpwp, rnorekpengirim, rnamapengirim, rkodewil, rnodok, rnilaibruto;
var rnilaipot, rbeban, rketerangan, ridskpd, rkodeskpd, rnamaskpd, rkodeakun;
var namatujuanarray = new Array();

function gettime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    var mm = today.getMonth() + 1;
    var dd = today.getDate();
    var yy = today.getFullYear().toString().substr(2, 2);
    if (h.toString().length == 1) {
        h = "0" + h;
    }

    if (m.toString().length == 1) {
        m = "0" + m;
    }

    if (s.toString().length == 1) {
        s = "0" + s;
    }

    if (mm.toString().length == 1) {
        mm = "0" + mm;
    }

    if (dd.toString().length == 1) {
        dd = "0" + dd;
    }

    var tglkirim = dd + "/" + mm + "/" + yy + " " + "" + h + ":" + m + ":" + s;
}
function gridsppup() {
    var baseurl = getbasepath()
    var urljson = baseurl + "/sp2dsahtransfer/json/getlistsp2d";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            // "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "kproses", value: $('#kproses').val()}
                );
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
                var lskpd = $('#skpd').val();
                var stat; //= aData['sp2d']['kodeCetak'];
                var statusbank = aData['statusBank'];
                if (statusbank == '0') {
                    stat = "DALAM PROSES";
                } else if (statusbank == '9') {
                    stat = "GAGAL TRANSFER";
                } else {
                    stat = aData['sp2d']['kodeCetak'];
                }

                var nilaisp2d = "<input type='hidden'    id='nilaisp2d" + aData['id'] + "' value='" + aData['sp2d']['nilaiSp2d'] + "'  />";
                var nomorsp2d = "<input type='hidden'    id='nomorsp2d" + aData['id'] + "' value='" + aData['sp2d']['nomorSp2d'] + "'  />";
                var kodebank = "<input type='hidden' id='kodebank" + aData['id'] + "' value='" + aData['kodeBank'] + "'  />";
                var norektujuan = "<input type='hidden' id='norektujuan" + aData['id'] + "' value='" + aData['norekTujuan'] + "'  />";
                var namatujuan = "<input type='hidden' id='namatujuan" + aData['id'] + "' value='" + aData['namaTujuan'] + "'  />";
                namatujuanarray[aData['id']] = aData['namaTujuan'];
                var alamat = "<input type='hidden' id='alamat" + aData['id'] + "' value='" + aData['alamat'] + "'  />";
                var npwp = "<input type='hidden' id='npwp" + aData['id'] + "' value='" + aData['npwp'] + "'  />";
                var norekpengirim = "<input type='hidden' id='norekpengirim" + aData['id'] + "' value='" + aData['norekPengirim'] + "'  />";
                var namapengirim = "<input type='hidden' id='namapengirim" + aData['id'] + "' value='" + aData['namaPengirim'] + "'  />";
                var kodebilling = "<input type='hidden' id='kodebilling" + aData['id'] + "' value='" + aData['kodeBilling'] + "'  />";
                var idsp2d = "<input type='hidden' id='idsp2d" + aData['id'] + "' value='" + aData['id'] + "'  />";
                var tahun = "<input type='hidden' id='tahun" + aData['id'] + "' value='" + aData['tahun'] + "'  />";
                var kodewil = "<input type='hidden' id='kodewil" + aData['id'] + "' value='" + aData['sp2d']['kodeWilayahProses'] + "'  />";
                var nodoksp2d = "<input type='hidden' id='nodoksp2d" + aData['id'] + "' value='" + aData['noDokSp2d'] + "'  />";
                var nilaispp = "<input type='hidden' id='nilaispp" + aData['id'] + "' value='" + aData['nilaiSpp'] + "'  />";
                var nilaipot = "<input type='hidden' id='nilaipot" + aData['id'] + "' value='" + aData['nilaiPotongan'] + "'  />";
                var beban = "<input type='hidden' id='beban" + aData['id'] + "' value='" + aData['kodeBeban'] + "'  />";
                var ketsp2d = "<input type='hidden' id='ketsp2d" + aData['id'] + "' value='" + aData['ketSp2d'] + "'  />";
                var idskpd = "<input type='hidden' id='idskpd" + aData['id'] + "' value='" + aData['idSkpd'] + "'  />";
                var kodeskpd = "<input type='hidden' id='kodeskpd" + aData['id'] + "' value='" + aData['kodeSkpd'] + "'  />";
                var namaskpd = "<input type='hidden' id='namaskpd" + aData['id'] + "' value='" + aData['namaSkpd'] + "'  />";
                var statussp2d = "<input type='hidden' id='statussp2d" + aData['id'] + "' value='" + aData['sp2d']['kodeCetak'] + "'  />";
                var nilaiamount1 = "<input type='hidden' id='nilaiamountbalance" + aData['id'] + "' value='" + aData['nilaiAmountBalance'] + "'  />";
                var kodeakun = "<input type='hidden' id='kodeakun" + aData['id'] + "' value='" + aData['kodeAkun'] + "'  />";
                var nilaipotrekon = "<input type='hidden' id='nilaipotrekon" + aData['id'] + "' value='" + aData['nilaiPot'] + "'  />";
                var nilaibruto = "<input type='hidden' id='nilaibruto" + aData['id'] + "' value='" + aData['nilaiBruto'] + "'  />";
                var rnorekTujuan = "<input type='hidden' id='rnorekTujuan" + aData['id'] + "' value='" + aData['rnorekTujuan'] + "'  />";
                var rnorekPengirim = "<input type='hidden' id='rnorekPengirim" + aData['id'] + "' value='" + aData['rnorekPengirim'] + "'  />";
                var kodeva = "<input type='hidden' id='kodeva" + aData['id'] + "' value='" + aData['kodeVA'] + "'  />";

                $('td:eq(0)', nRow).html(index + kodebank + norektujuan + namatujuan + alamat + npwp + norekpengirim + namapengirim + kodebilling);
                $('td:eq(1)', nRow).html(aData['sp2d']['nomorSp2d'] + nomorsp2d + nilaisp2d + idsp2d + tahun + kodewil + nodoksp2d + nilaispp + nilaipot);
                $('td:eq(2)', nRow).html(getTanggal(aData['sp2d']['tglSp2d']) + beban + ketsp2d + idskpd + kodeskpd + namaskpd);
                $('td:eq(3)', nRow).html(aData['kodeBeban'] + statussp2d + nilaiamount1 + kodeakun + kodeva);
                $('td:eq(4)', nRow).html(aData['kodeJenis'] + nilaipotrekon + nilaibruto + rnorekTujuan + rnorekPengirim);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sp2d']['nilaiSp2d']));
                var nipPptk = "<input type='hidden'    id='nipPptk" + aData['id'] + "' value='" + aData['nipPptk'] + "'  />";
                //$('td:eq(6)', nRow).html(aData['sp2d']['kodeCetak']);
                $('td:eq(6)', nRow).html(stat);
                $('td:eq(7)', nRow).html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");
                var cekprint;
                var cektransfer;
                if (stat == 'CETAK') {
                    cekprint = "<input type='radio' name='cek'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                    cektransfer = "";
                }

                if (aData['kodeSah'] == "1") {
                    cekprint = "";
                    cektransfer = "<input type='radio' name='cek'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                }

                if (stat == 'DALAM PROSES') {
                    cekprint = "";
                    cektransfer = "";
                } else if (stat == 'VIRTUAL ACCOUNT') {
                    cekprint = "";
                    cektransfer = "Trasfer Manual untuk VA";
                }

                //$('td:eq(8)', nRow).html(cekprint);
                $('td:eq(8)', nRow).html(cektransfer);
                var manual = "<button type='button' class='btn blue' id='manual" + aData['id'] + "' onclick='bayarmanual(" + aData['id'] + ")'> Bayar Manual</button>";

                $('td:eq(9)', nRow).html(manual);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "sp2d.nomorSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.tglSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.nilaiSp2d", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "sp2d.kodeCetak", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}

            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function getKodeStan() {

    $.getJSON(getbasepath() + "/sp2dsahtransfer/json/getKodeStan", {},
            function(result) {
                kodestan = result.aData['kodeStan'];
                nomorref = result.aData['kodeStan'];
            });
}

function cekBanyakTf() {
    var idpilih;
    $('.checkbox:checked').each(function() {
        idpilih = $(this).val();
    }
    );

    var tahun = $("#tahun" + idpilih).val();
    var idsp2d = $("#idsp2d" + idpilih).val();
    var status, idsp2d;

    $.getJSON(getbasepath() + "/sp2dsahtransfer/json/getBanyakTf", {tahun: tahun, idsp2d: idsp2d},
    function(result) {
        status = result.aData['statusBank'];
        idsp2d = result.aData['idSp2d'];
        //console.log("getBanyakTfInt result = " + result);
        //console.log("getBanyakTf result status = " + status);
        console.log("getBanyakTf result idsp2d = " + idsp2d);

        if (parseInt(status) > 0) {
            bootbox.alert("Data SP2D Ini Sudah Dibayarkan");
        } else {
            submittransfer();
        }
    });
}

function submittransfer() {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var statuscek = 0;
    var arrnospd = [];
    var status;
    var datacek;
    var tglkirim;
    var idpilih;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
        var nilai = $("#nilaisp2d" + idspd).val();
        idpilih = $(this).val();
        status = $("#statussp2d" + idspd).val();
        var nospd = $("#nomorsp2d" + idspd).val();
        selectednoskpd.push(idspd);
        arrnospd.push(nospd);
    }
    );
    document.getElementById("prosestransfer").style.visibility = "hidden";
    if (status !== "CETAK") {

        bootbox.confirm("Anda akan melakukan transfer untuk SP2D dengan nomor " + arrnospd.join(",") + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
            //console.log("kodestan = " + kodestan);
            if (result) {
                //document.getElementById("prosestransfer").style.visibility = "hidden";
                var sysdate;
                // get kode stan
                $.getJSON(getbasepath() + "/sp2dsahtransfer/json/getKodeStan", {},
                        // $.getJSON(getbasepath() + "/sp2dsahtransfer/json/getSysdate", {},

                                function(result) {
                                    sysdate = result.aData['tglProses']; // ddmmyy hh24miss
                                    kodestan = result.aData['kodeStan'];
                                    nomorref = result.aData['nomorRef'];
                                    //console.log("sysdate = " + sysdate);
                                    var dd = sysdate.substr(0, 2);
                                    var mm = sysdate.substr(2, 2);
                                    var yy = sysdate.substr(4, 2);
                                    var h = sysdate.substr(7, 2);
                                    var m = sysdate.substr(9, 2);
                                    var s = sysdate.substr(11, 2);
                                    var kodebank = $("#kodebank" + idpilih).val();
                                    var norektujuan = $("#norektujuan" + idpilih).val().trim();
                                    //var namatujuan = $("#namatujuan" + idpilih).val();
                                    var namatujuan = namatujuanarray[idpilih]; //$("#namatujuan" + idpilih).val();
                                    var alamat = $("#alamat" + idpilih).val();
                                    var npwp = $("#npwp" + idpilih).val();
                                    var nilaibayar = $("#nilaisp2d" + idpilih).val();
                                    var norekpengirim = $("#norekpengirim" + idpilih).val().trim();
                                    var namapengirim = $("#namapengirim" + idpilih).val();
                                    var kodebilling = $("#kodebilling" + idpilih).val();
                                    var idsp2d = $("#idsp2d" + idpilih).val();
                                    var tahun = $("#tahun" + idpilih).val();
                                    var kodewil = $("#kodewil" + idpilih).val();
                                    var nosp2d = $("#nodoksp2d" + idpilih).val();
                                    var nilaibruto = $("#nilaispp" + idpilih).val();
                                    var nilaipot = $("#nilaipot" + idpilih).val();
                                    var beban = $("#beban" + idpilih).val();
                                    var ketsp2d = $("#ketsp2d" + idpilih).val();
                                    var idskpd = $("#idskpd" + idpilih).val();
                                    var kodeskpd = $("#kodeskpd" + idpilih).val();
                                    var namaskpd = $("#namaskpd" + idpilih).val();
                                    var kodeakun = $("#kodeakun" + idpilih).val();
                                    var nilaiamountbalance = $("#nilaiamountbalance" + idpilih).val();
                                    tglkirim = dd + "/" + mm + "/" + yy + " " + "" + h + ":" + m + ":" + s;
                                    // set var untuk rekon
                                    rbit4 = nilaiamountbalance;
                                    rbit11 = kodestan;
                                    rbit12 = h + m + s; // hhmmss
                                    rbit13 = yy + mm + dd; // MMDD
                                    rbit37 = nomorref;
                                    rkodebank = kodebank;
                                    rnorektujuan = norektujuan; //$("#rnorekTujuan" + idpilih).val();
                                    rnamatujuan = namatujuan;
                                    ralamat = alamat;
                                    rnpwp = npwp;
                                    rnorekpengirim = $("#rnorekPengirim" + idpilih).val();
                                    rnamapengirim = namapengirim;
                                    rkodewil = kodewil;
                                    rnodok = nosp2d;
                                    rnilaibruto = $("#nilaibruto" + idpilih).val();
                                    rnilaipot = $("#nilaipotrekon" + idpilih).val();
                                    rbeban = beban;
                                    rketerangan = ketsp2d;
                                    ridskpd = idskpd;
                                    rkodeskpd = kodeskpd;
                                    rnamaskpd = namaskpd;
                                    rkodeakun = kodeakun;

                                    var var4 = "000000000000"; // amount
                                    var var7 = mm + dd + h + m + s; // MMDDhhmmss
                                    var var12 = h + m + s; // hhmmss
                                    var var13 = mm + dd; // MMDD
                                    var var14 = yy + mm; // YYMM
                                    var var15 = mm + dd; // MMDD
                                    var var32 = "000" + kodebank;
                                    var kodeauthor = yy + var7;

                                    //SET FIX LENGTH NOREK (20) UNTUK FIELD 120
                                    var panjangnorek = norektujuan.toString().length;
                                    var pspasi = 20 - panjangnorek;
                                    var spasi = "";
                                    var norek20 = "";

                                    for (var i = 0; i < pspasi; i++) {
                                        spasi = spasi + " ";
                                    }
                                    norek20 = (norektujuan.toString() + spasi).toString(); // udah bisa :D
                                    console.log(" spasi =" + spasi);
                                    console.log("norek 20 = " + norek20);


                                    // set var untuk kirim
                                    var var120 = kodebank + "|" + norek20 + "|" + namatujuan + "|" + alamat + "|" + npwp + "|" + nilaibayar + "|" + norekpengirim + "|" + namapengirim + "|" + kodebilling + "|" + idsp2d + "|" + tahun + "|" + kodewil + "|" + nosp2d + "|" + nilaibruto + "|" + nilaipot + "|" + beban + "|" + ketsp2d + "|" + idskpd + "|" + kodeskpd + "|" + namaskpd + "|" + kodeakun + "|" + kodeauthor + "|" + nilaiamountbalance;
                                    var var62 = tahun.toString() + "10" + idsp2d.toString(); // kode app diganti untuk SP2D jadi 1 (asalnya 3) agar sama dengan urutan kode app di ws pajak (pertanggal 15 jan 19, sebelumnya sudah ada 68 data tf dengan kode app bit62 nya 3)
                                    var var102 = norekpengirim; // no rek pengirim
                                    var var103 = norektujuan; // no rek penerima
                                    datacek = var120;

                                    //bootbox.alert("Data Transfer :: " + datacek);
                                    getDataBankDki(var4, var7, var12, var13, var14, var15, var32, var62, var102, var103, var120, tahun, idsp2d, nilaibayar, kodebilling, kodeauthor);
                                });
                    } else {
                bootbox.hideAll();
                document.getElementById("prosestransfer").style.visibility = "visible";
                return result;
            }
        });
    }
}

function simpanSp2dBank(tahun, idsp2d, nilai, msgkirim, kodebilling, kodeauthor, update12, update13, update39, status, msg, var4, var62) {


    var mm = update13.substr(0, 2);
    var dd = update13.substr(2, 2);
    //var tahun = $("#tahun").val();
    var tglproses = dd + mm + tahun + " " + update12;
    var pesan = update39 + " - " + msg;


    var varurl = getbasepath() + "/sp2dsahtransfer/json/simpansp2dbank";
    var dataac = [];
    if (kodebilling == "") {
        kodebilling = "-";
    }

    var datajour = {
        tahun: tahun,
        idsp2d: idsp2d,
        nilaibayar: nilai,
        msgkirim: msgkirim,
        kodebilling: kodebilling,
        bit4: rbit4,
        bit11: rbit11,
        bit12: rbit12,
        bit13: rbit13,
        bit37: rbit37,
        kodebank: rkodebank,
        norektujuan: rnorektujuan,
        namatujuan: rnamatujuan,
        alamat: ralamat,
        npwp: rnpwp,
        norekpengirim: rnorekpengirim,
        namapengirim: rnamapengirim,
        kodewil: rkodewil,
        nodok: rnodok,
        nilaibruto: rnilaibruto,
        nilaipot: rnilaipot,
        beban: rbeban,
        keterangan: rketerangan,
        idskpd: ridskpd,
        kodeskpd: rkodeskpd,
        namaskpd: rnamaskpd,
        kodeakun: rkodeakun,
        kodeauthor: kodeauthor,
        bit62: var62
                //statusbank: carabayarpg
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
        console.log("simpan data berhasil");
        updateSp2dBank(idsp2d, status, "-", tglproses, var4, pesan);

        //gridsppup();
    });
}

function getDataBankDki(var4, var7, var12, var13, var14, var15, var32, var62, var102, var103, var120, tahun, idsp2d, nilaibayar, kodebilling, kodeauthor) {
    var procode = "500004";
    if ($("#kodeva" + idsp2d).val() && $("#kodebank" + idsp2d).val() == "111") { // jika VA Bank DKI procode nya beda
        procode = "500006";
    }
    var var0 = "0200"; // ??
    var var2 = "000000000000000000";
    var var3 = procode;
    var var11 = kodestan;
    var var18 = "7012";
    var var22 = "001";
    var var33 = "111";
    var var37 = nomorref;
    var var41 = "SP2D";
    var var49 = "360";
    // var var52 = "1234567890123456"; // ???? ga usah
    var var54 = var4; //"000000000000";//nilaiamountbalance; // Amount Balance


    var msg, status;
    var varurl = getbasepath() + "/sp2dsahtransfer/json/transfersp2d";
    var dataac = [];
    var datasp2d = {
        0: var0,
        2: var2,
        3: var3,
        4: var4,
        7: var7,
        11: var11,
        12: var12,
        13: var13,
        14: var14,
        15: var15,
        18: var18,
        22: var22,
        32: var32,
        33: var33,
        37: var37,
        41: var41,
        49: var49,
        //52: var0,
        54: var54,
        62: var62,
        102: var102,
        103: var103,
        120: var120

    };
    dataac = datasp2d;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

            var data39 = data[39];
            if (data39 == "00") {
                status = 1;
                msg = "Transaksi SP2D Berhasil";
            } else {
                status = 9;
                if (data39 == "76") {
                    msg = "Transaksi Gagal - No Rekening Tidak Terdaftar";
                } else if (data39 == "63") {
                    msg = "Transaksi Gagal - Duplikat Data SP2D";
                } else if (data39 == "68") {
                    msg = "Transaksi Gagal - Core Bank DKI Mati";
                } else if (data39 == "12") {
                    msg = "Data Transaksi Sudah Tidak Berlaku";
                } else if (data39 == "13") {
                    msg = "Transaksi Gagal - Jumlah Tidak Valid";
                } else if (data39 == "18") {
                    msg = "Transaksi Gagal - Sudah Dibayar / No Bill";
                } else if (data39 == "20") {
                    msg = "Transaksi Gagal - Posting Core Gagal";
                } else if (data39 == "30") {
                    msg = "Transaksi Gagal - Pesan Format Kesalahan";
                } else if (data39 == "51") {
                    msg = "Transaksi Gagal - Saldo tidak cukup untuk melakukan transaksi";
                } else if (data39 == "53") {
                    msg = "Transaksi Gagal - Tidak ada Tabungan";
                } else if (data39 == "57") {
                    msg = "Transaksi Gagal - Transaksi tidak diizinkan untuk Pemegang Kartu / Penerbit";
                } else if (data39 == "78") {
                    msg = "Transaksi Gagal - Rekening Ditutup";
                } else if (data39 == "90") {
                    msg = "Transaksi Gagal - Terjadi Kesalahan";
                } else if (data39 == "91") {
                    msg = "Transaksi Gagal - Server melakukan transaksi lainnya, coba lagi nanti";
                } else if (data39 == "94") {
                    msg = "Transaksi Gagal - Waktu koneksi habis";
                } else {
                    msg = "Transaksi Gagal";
                }

            }

            var cek = "FIELD 37 : " + data[37] + " <br/> FIELD 39 : " + data[39] + " <br/> FIELD 120 : " + data[120];
            /*bootbox.alert({
             size: "small",
             title: "DATA RESPONSE",
             message: cek
             });*/

            bootbox.alert(msg);
            console.log(data);
            //console.log("data 39 = " + data[39]);
            //console.log("data 120 = " + data[120]);
            update12 = data[12];
            update13 = data[13];
            update39 = data[39];
            // console.log("var 4 - amount balance = " + var4);

            simpanSp2dBank(tahun, idsp2d, nilaibayar, var120, kodebilling, kodeauthor, update12, update13, update39, status, msg, var4, var62);


        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI BANK DKI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {
        $('#buttoninduk').attr("disabled", false);

        //getKodeStan();

    });

}

function updateSp2dBank(idsp2d, status, trxterima, tglproses, nilai, msg) {

    var varurl = getbasepath() + "/sp2dsahtransfer/json/updatesp2dbank";
    var dataac = [];
    var tahun = $('#tahun').val();
    var datajour = {
        tahun: tahun,
        idsp2d: idsp2d,
        statusbank: status.toString(),
        trxterimabank: trxterima,
        tglprosesbank: tglproses,
        nilaibayarbank: nilai,
        msgterimabank: msg

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
        console.log("update data berhasil");
        document.getElementById("prosestransfer").style.visibility = "visible";
        gridsppup();
        update12 = "";
        update13 = "";
        update39 = "";
    });
}

function bayarmanual(id) {

    var nospd = $("#nomorsp2d" + id).val();

    bootbox.confirm("Anda akan melakukan pembayaran MANUAL untuk SP2D dengan nomor " + nospd + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        //console.log("kodestan = " + kodestan);
        if (result) {
            var varurl = getbasepath() + "/sp2dsahtransfer/json/simpansp2dbankmanual";
            var dataac = [];

            var idsp2d = $("#idsp2d" + id).val();
            var var62 = $('#tahun').val().toString() + "30" + idsp2d.toString();
            var kodebank = $("#kodebank" + id).val();
            var norektujuan = $("#norektujuan" + id).val();
            var namatujuan = $("#namatujuan" + id).val();
            var nilaibayar = $("#nilaisp2d" + id).val();
            var norekpengirim = $("#norekpengirim" + id).val();
            var namapengirim = $("#namapengirim" + id).val();
            var kodewil = $("#kodewil" + id).val();
            var nosp2d = $("#nodoksp2d" + id).val();
            var nilaibruto = $("#nilaispp" + id).val();
            var nilaipot = $("#nilaipot" + id).val();
            var beban = $("#beban" + id).val();
            var ketsp2d = $("#ketsp2d" + id).val();
            var idskpd = $("#idskpd" + id).val();
            var kodeskpd = $("#kodeskpd" + id).val();
            var namaskpd = $("#namaskpd" + id).val();
            var kodeakun = $("#kodeakun" + id).val();


            var datajour = {
                tahun: $('#tahun').val(),
                idsp2d: idsp2d,
                nilaibayar: nilaibayar,
                kodebank: kodebank,
                norektujuan: norektujuan,
                namatujuan: namatujuan,
                norekpengirim: norekpengirim,
                namapengirim: namapengirim,
                kodewil: kodewil,
                nodok: nosp2d,
                nilaibruto: nilaibruto,
                nilaipot: nilaipot,
                beban: beban,
                keterangan: ketsp2d,
                idskpd: idskpd,
                kodeskpd: kodeskpd,
                namaskpd: namaskpd,
                kodeakun: kodeakun,
                bit62: var62

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
                console.log("simpan data berhasil");
                gridsppup();
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });

}