$(document).ready(function() {
    //gridspj();
    //setJenisPpkd();

    document.getElementById("btnTambah").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D
    document.getElementById("pilihKegSpj").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D

    getSkpdBtl();
    setGrid();
    setBulan();
    setTanggalEnable();
    keteranganBulan();
    setBeban();

    //setTesMonth(); 
    setCurrentMonth();
});

// global variable
var idspp = new Array();
var tglpost = new Array();
var beban = new Array();
var jenis = new Array();
var akunnama = new Array();
var jumbaris = 0;
var jumbarisspj = 0;
var idrow = 0;
var idbuton, idcombo, ketcombo;
var bulanval, sp2djenis, sp2dbeban;
var cek = "";


function setBulan() {

    $('#bulan').val(bulanval);
}

function keteranganBulan() {
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi == "JO") {
        getBanyakNoBukti();
        setNoBukti();
    }
}

function gantiText(valjenis) {

    $("#keteranganKegPop").val("");
    $("#idKegpop").val("");

    if (valjenis == 'JO') {
        var opt = '<select path="noBuktiDok" id="noBuktiDok" onchange="gantinobukti()" ><option </option>  </select>';
        $("#nobuktiketerangan").html(opt);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        getBanyakNoBukti();
        setNoBukti();
    } else if (valjenis == 'JJ') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "visible";

    } else if (valjenis == 'ST' && $("#beban").val() == "UP") {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } else if (valjenis == 'LL' && $("#beban").val() == "UP") {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } else {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50'  />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "visible";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();
    }
}
function getBanyakNoBukti() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();

    $("#banyaknobukti").val(0); // refresh banyak no bukti;

    $.getJSON(getbasepath() + "/bku/json/getBanyakNoBukti", {tahun: tahun, idskpd: idskpd, bulan: bulan},
    function(result) {
        $("#banyaknobukti").val(result);
    });
}

function setNoBukti() {
    var jenistransaksi = $('#jenisTransaksi').val();
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();


    $.getJSON(getbasepath() + "/bku/json/setNoBukti", {tahun: tahun, idskpd: idskpd, jenis: jenistransaksi, bulan: bulan},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = $("#banyaknobukti").val();

        try {

            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['idsp2d'];
                ket = result.aData[i]['noBukti'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';
                idspp[kode] = result.aData[i]['idspp'];
                tglpost[kode] = result.aData[i]['tglPosting'];
                beban[kode] = result.aData[i]['beban'];
                jenis[kode] = result.aData[i]['jenis'];
            }
            $("#noBuktiDok").html(opt);
            gantinobukti();

        } catch (e) {
            console.log(e);
        }

    });
}

function gantinobukti() {
    setTanggal();
    var nobukti = $("#noBuktiDok").val();
    var ketjenis = jenis[nobukti];
    var ketbeban = beban[nobukti];
    var ketidspp = idspp[nobukti];
    var keterangan;

    sp2djenis = ketjenis;
    sp2dbeban = ketbeban;

    if (ketbeban == "UP" || ketbeban == "GU" || ketbeban == "TU") {
        keterangan = "upgutu";
    } else if (ketbeban == "LS") {
        if (ketjenis == 1) {
            keterangan = "LS1";
        } else if (ketjenis == 2) {
            keterangan = "LS2";
        } else if (ketjenis == 3) {
            keterangan = "LS3";
        } else if (ketjenis == 4) {
            keterangan = "LS4";
        }
    }
    $("#kodebebanket").val(keterangan);
    $("#ketidspp").val(ketidspp);

    var jenistransaksi = $('#jenisTransaksi').val();
    if (jenistransaksi == "JO") {
        gridbku();
    }
}

function setTanggalEnable() {
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi == "JO") {
        $("#tglPosting").attr('readonly', 'readonly');
        $("#tglPosting").attr('disabled', 'disabled');

    } else {
        $("#tglPosting").attr('readonly', false);
        $("#tglPosting").attr('disabled', false);
    }
}

function setTanggal() {
    //  var jenistransaksi = $('#jenisTransaksi').val();
    var nobukti = $("#noBuktiDok").val();
    var tgl = tglpost[nobukti];
    var dd, mm, yy, tglgabung;

    if (tgl) {
        dd = tgl.substring(6);
        mm = tgl.substr(4, 2);
        yy = tgl.substr(0, 4);

        tglgabung = dd + "/" + mm + "/" + yy;

    }

    $('#tglPosting').val(tglgabung);
    $('#tglDok').val(tglgabung);

}

function gridbku() {
    jumbaris = 0;

    if ($("#banyaknobukti").val() < 1) {
        $("#ketidspp").val(0);
        $("#kodebebanket").val("upgutu"); // syarat aja
    }

    if (typeof tablebku == 'undefined') {
        tablebku = $('#jourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbku",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "keterangan", value: $("#kodebebanket").val()},
                {name: "idspp", value: $("#ketidspp").val()}
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
                var dd, mm, yy, tglpost;
                var namaakunval = aData['namaakun'];

                if (namaakunval == null) {
                    namaakunval = "";
                }

                var tanggal = aData['tglDok'];
                dd = tanggal.substr(8, 2);
                mm = tanggal.substr(5, 2);
                yy = tanggal.substr(0, 4);
                tglpost = yy + mm + dd;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var kodetrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['kodeTransaksi'] + "' />";
                var idspp = "<input type='hidden' id='idspp" + index + "' value='" + aData['idspp'] + "' />";
                var idsp2d = "<input type='hidden' id='idsp2d" + index + "' value='" + aData['idsp2d'] + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var nodok = "<input type='hidden' id='nodok" + index + "' value='" + aData['noDok'] + "' />";
                var tgldok = "<input type='hidden' id='tgldok" + index + "' value='" + aData['tglDok'] + "' />";
                var tglposting = "<input type='hidden' id='tglpost" + index + "' value='" + tglpost + "' />";
                var kodekeg = "<input type='hidden' id='kodekegiatan" + index + "' value='" + aData['kodeKeg'] + "' />";
                var idkegiatan = "<input type='hidden' id='idkegiatan" + index + "' value='" + aData['idKegiatan'] + "' />";
                var uraianket = "<input type='hidden' id='uraianket" + index + "' value='" + aData['uraianBukti'] + "' />";
                var namaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['uraianBukti'] + "</textarea>";

                jumbaris = jumbaris + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(uraian + kodekeg + idkegiatan + uraianket + namaakun);
                //$('td:eq(2)', nRow).html(namaakun);
                $('td:eq(5)', nRow).html(nilaimasuk + kodetrans + idspp + idsp2d + idbas + nodok + tgldok + tglposting);
                $('td:eq(6)', nRow).html(nilaikeluar);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeKeg", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaKeg", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"}
            ]
        });
    }
    else
    {
        tablebku.fnClearTable(0);
        tablebku.fnDraw();
    }
}

function gridspj() {
    jumbarisspj = 0;
    var nobukti = $("#noBuktiDok").val();

    if (nobukti == "") {
        nobukti = " ";
    }

    if (typeof myTable == 'undefined') {
        myTable = $('#spjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku/json/listbkuspj",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatanSpj").val()},
                {name: "nobukti", value: nobukti}
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

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 0 + "' />";
                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var nilaiAngg = "<input type='text' name='nilaiAnggaran" + index + "' id='nilaiAnggaran" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisp2d = "<input type='text' name='nilaisp2d" + index + "' id='nilaisp2d" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSp2d'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' readOnly='true' onkeyup='pasangvalidatebesarperfield(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") />";
                var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaakun'] + "</textarea>";
                var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                jumbarisspj = jumbarisspj + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(nilaiAngg);
                $('td:eq(4)', nRow).html(nilaisp2d);
                $('td:eq(5)', nRow).html(nilaisebelum);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(nilaiinput);
                $('td:eq(8)', nRow).html(inputcek + idbas + textnamaakun + textkodeakun + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function enablerow(obj, index) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + index).val(1);
    } else {
        $("#penanda" + index).val(0);
    }
    setdisabled(param, index);
}
function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);

    if (param == false) {
        //var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
        //var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
        //var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
        //$("#nilaispj" + idbl).val(nilaiisian)
    }
}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai BKU tidak boleh lebih besar dari Sisa BKU.");
        $('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
    }
}

function simpan() {
    var jenistransaksi = $('#jenisTransaksi').val();
    var table = document.getElementById('jourtablebody');
    var rows = table.rows;
    var jum = rows.length;

    var table2 = document.getElementById('jourtablebody2');
    var rows2 = table2.rows;
    var jum2 = rows2.length;

    var table3 = document.getElementById('spjtablebody');
    var rows3 = table3.rows;
    var jum3 = rows3.length;

    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var beban = $("#beban").val();
    var jenis = $("#jenis").val();
    var pengeluaran = $("#pengeluaran").val();
    var datalengkap = true;
    var idKegiatanSpj = $("#idKegiatanSpj").val();

    if (jenistransaksi == "JO") { // SP2D
        if (jum > 1) {
            simpanSP2D();
        }
    }

    if (jenistransaksi == "JJ") { // SPJ
        if (jum3 > 1) {

            if (tglPost == "" || nobukti == "" || tglDok == "" || idKegiatanSpj == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                simpanSPJ();
            }
        }
    }

    if (jenistransaksi == "NP" || jenistransaksi == "NM") { // NP
        if (jum2 > 0) {

            for (var a = 1; a <= idrow; a++) {

                if ($('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                    datalengkap = false;
                }
            }

            if (tglPost == "" || nobukti == "" || tglDok == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                simpanNPD();
            }
        }
    }

    if (jenistransaksi == "ST") { // SETORAN
        if (beban == "UP") {
            if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                simpanSetorUP();
            }

        } else {
            if (jum2 > 0) {
                if (jenis == 1 || jenis == 4) { // BTL dan Biaya = tanpa kegiatan
                    for (var a = 1; a <= idrow; a++) {

                        if ($('#akun' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                            datalengkap = false;
                        }
                    }

                    if (tglPost == "" || nobukti == "" || tglDok == "" || datalengkap == false) {
                        bootbox.alert("Pengisian Data Harus Lengkap");
                    } else {
                        simpanSTtanpakeg();
                    }

                } else if (jenis == 2 || jenis == 3) { // BL dan BTL Bantuan = dengan kegiatan
                    for (var a = 1; a <= idrow; a++) {

                        if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                            datalengkap = false;
                        }
                    }

                    if (tglPost == "" || nobukti == "" || tglDok == "" || datalengkap == false) {
                        bootbox.alert("Pengisian Data Harus Lengkap");
                    } else {
                        simpanNPD();
                    }
                }
            }
        }
    }

    if (jenistransaksi == "P1" || jenistransaksi == "P2" || jenistransaksi == "P3" || jenistransaksi == "P4" || jenistransaksi == "P5") { // PAJAK PPH PS 23 JASA I / PPH PS 21
        if (jum2 > 0) {

            if (tglPost == "" || nobukti == "" || tglDok == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                simpanNPD();
            }
        }
    }

    if (jenistransaksi == "LL") {
        if (jum2 > 0) {
            if (jenis == 1 || jenis == 4) { // BTL dan Biaya = tanpa kegiatan
                for (var a = 1; a <= idrow; a++) {

                    if ($('#akun' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                        datalengkap = false;
                    }
                }

                if (tglPost == "" || nobukti == "" || tglDok == "" || datalengkap == false) {
                    bootbox.alert("Pengisian Data Harus Lengkap");
                } else {
                    simpanSTtanpakeg();
                }

            } else if (jenis == 2 || jenis == 3) { // BL dan BTL Bantuan = dengan kegiatan
                for (var a = 1; a <= idrow; a++) {

                    if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                        datalengkap = false;
                    }
                }

                if (tglPost == "" || nobukti == "" || tglDok == "" || datalengkap == false) {
                    bootbox.alert("Pengisian Data Harus Lengkap");
                } else {
                    simpanNPD();
                }
            }
        }
    }
}

function simpanSetorUP() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    var varurl = getbasepath() + "/bku/json/prosessimpansetorup";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: "3",
        beban: $("#beban").val(),
        nilaikeluar: $('#pengeluaran').val(), //parseFloat(accounting.unformat($('#pengeluaran').val(), ","))
        fileinbox: fileinbox,
        nilaimasuk: 0
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

function simpanSTtanpakeg() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    var varurl = getbasepath() + "/bku/json/simpantanpakeg";
    var dataac = [];
    var nilailist = [];
    var i;


    for (i = 0; i < idrow; i++) { // list
        var id = i + 1;

        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            uraianbukti: $("#namaakun" + id).val(),
            kodeakun: $('select[name="akun' + id + '"]').find(":selected").text()
        };
        nilailist[i] = pararr;

    }

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: $("#jenis").val(),
        beban: $("#beban").val(),
        fileinbox: fileinbox,
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
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        clearrow();
    });
}

function simpanNPD() {

    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal, jenis;
    var jenistrans = $('#jenisTransaksi').val();
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)
    var fileinbox = $("#fileInbox").val();
    var nipPPTK, namaPPTK; // = $("#nipPptk").val();


    if (jenistrans == "JJ" || jenistrans == "NP" || jenistrans == "NM" || jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5") {
        jenis = "3";
    } else {
        jenis = $('#jenis').val();
    }

    if (jenistrans == "NP" || jenistrans == "NM") {
        nipPPTK = $("#nipPptk").val();
        namaPPTK = $("#namaPptk").val();
    } else {
        nipPPTK = "";
        namaPPTK = "";
    }

    var varurl = getbasepath() + "/bku/json/prosessimpannpd";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < idrow; i++) { // list
        var id = i + 1;

        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            kodekegiatan: $("#kegiatan" + id).val(),
            uraianbukti: $("#namaakun" + id).val(),
            idkegiatan: $("#idkeg" + id).val(),
            kodeakun: $('select[name="akun' + id + '"]').find(":selected").text()
        };
        nilailist[i] = pararr;

    }

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: jenis,
        beban: $("#beban").val(),
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        fileinbox: fileinbox,
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
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $("#noBuktiDok").val("");
        clearrow();
    });
}

function simpanSP2D() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var fileinbox = $("#fileInbox").val();
    var kodeakun, uraiankode, uraianket, kodekeg, idkeg, tgldok, tglgabung;

    if (jumbaris > 0) {

        var varurl = getbasepath() + "/bku/json/prosessimpan";
        var dataac = [];
        var nilailist = [];
        var i;

        for (i = 0; i < jumbaris; i++) { // list
            var id = i + 1;
            uraiankode = $("#uraianbukti" + id).val();

            if (uraiankode.length > 20) {
                kodeakun = "";
                uraianket = $("#uraianket" + id).val();
            } else {
                kodeakun = uraiankode;
                uraianket = $("#namaakun" + id).val();
            }

            kodekeg = $("#kodekegiatan" + id).val();
            idkeg = $("#idkegiatan" + id).val();

            if (kodekeg == "null" || kodekeg == "") {
                kodekeg = "";
            }

            if (idkeg == null) {
                idkeg = "";
            }

            tgldok = $("#tgldok" + id).val();
            tglgabung = tgldok.substr(8, 2) + "/" + tgldok.substr(5, 2) + "/" + tgldok.substr(0, 4) + " " + tgldok.substring(11);

            var pararr = {
                nilaimasuk: parseFloat(accounting.unformat($('#nilaimasuk' + id).val(), ",")), //$("#nilaimasuk" + id).val(),
                nilaikeluar: parseFloat(accounting.unformat($('#nilaikeluar' + id).val(), ",")), //$("#nilaikeluar" + id).val(),
                kodetransaksi: $("#kodetransaksi" + id).val(),
                idsp2d: $("#idsp2d" + id).val(),
                idbas: $("#idbas" + id).val(),
                nodok: $("#nodok" + id).val(),
                tgldok: tglgabung, //$("#tgldok" + id).val(),
                tglpost: $("#tglpost" + id).val(),
                kodekegiatan: kodekeg, //$("#kodekegiatan" + id).val(),
                uraianbukti: uraianket, //$("#uraianket" + id).val(), 
                idkegiatan: idkeg, //$("#idkegiatan" + id).val(),
                kodeakun: kodeakun
            };
            nilailist[i] = pararr;
        }

        var datajour = {
            tahun: tahun,
            idskpd: idskpd,
            jenis: sp2djenis,
            beban: sp2dbeban,
            fileinbox: fileinbox,
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
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
            getBanyakNoBukti();
            setNoBukti();
        });

    } else {

    }

}

function simpanSPJ() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;

    if (jumbarisspj > 0) {

        var varurl = getbasepath() + "/bku/json/prosessimpanspj";
        var dataac = [];
        var nilailist = [];
        var i;

        for (i = 0; i < jumbarisspj; i++) { // list
            var id = i + 1;

            var pararr = {
                nilaimasuk: "0",
                nilaikeluar: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")),
                idbas: $("#idbas" + id).val(),
                uraianbukti: $("#namaakun" + id).val(),
                penanda: $("#penanda" + id).val(),
                kodeakun: $("#kodeakun" + id).val()
            };
            nilailist[i] = pararr;
        }

        var datajour = {
            tahun: tahun,
            idskpd: idskpd,
            tglposting: tanggal,
            kodetransaksi: $('#jenisTransaksi').val(),
            nobukti: $("#noBuktiDok").val(),
            tgldok: $("#tglDok").val(),
            jenis: "3",
            beban: $("#beban").val(),
            idkegiatan: $('#idKegiatanSpj').val(),
            kodekegiatan: $('#kodeKegSpj').val(),
            fileinbox: fileinbox,
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

            $("#keteranganKegPop").val("");
            $("#idKegpop").val("");
            clearrowspj();
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        });

    } else {

    }

}

function clearrowspj() {
    var i;
    var table = document.getElementById('spjtablebody');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function clearrow() {
    var i;
    var table = document.getElementById('jourtablebody2');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function cektambah() {
    var jenistrans = $("#jenisTransaksi").val();
    var jenis = $("#jenis").val();

    if (jenistrans == "ST" && (jenis == 1 || jenis == 4)) {
        idcombo = 1;

        if (jenis == 1) {
            ketcombo = "BTL";
        } else if (jenis == 4) {
            ketcombo = "BIAYA";
        }
        tambahRow2();   // tanpa kolom kegiatan dan nama kegiatan

    } else if (jenistrans == "JJ") {
        ketcombo = "SPJ";
        tambahRowSpj();

    } else if (jenistrans == "LL" && (jenis == 1 || jenis == 4)) {
        idcombo = 1;

        if (jenis == 1) {
            ketcombo = "BTL";
        } else if (jenis == 4) {
            ketcombo = "BIAYA";
        }
        tambahRow2();   // tanpa kolom kegiatan dan nama kegiatan

    } else if (jenistrans == "NP") {
        ketcombo = "SPJ";

        tambahRowNP();   // keluar aktif

    } else if (jenistrans == "NM") {
        ketcombo = "SPJ";

        tambahRowNM();   // masuk aktif

    } else {
        idcombo = 2;

        if (jenistrans == "ST") {
            if (jenis == 2) {
                ketcombo = "BANTUAN";
            } else if (jenis == 3) {
                ketcombo = "BL";
            }
        } /*else if (jenistrans == "NP") {    // NPD
         ketcombo = "SPJ";
         }*/ else if (jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5") {    // Pajak
            ketcombo = "SPJ";
        } else if (jenistrans == "LL") {    // Lain-Lain
            if (jenis == 2) {
                ketcombo = "BANTUAN";
            } else if (jenis == 3) {
                ketcombo = "BL";
            }
        }

        tambahRow();
    }
}

function tambahRow2() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:600px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
    cell5.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();
    setAkunCombo(0);

}

function tambahRow() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowNP() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowNM() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowSpj() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "</td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function getbutton(id) {
    idbuton = id;
}

function getKegiatan() {
    var jenistrans = $('#jenisTransaksi').val();
    var id = idbuton;

    if (jenistrans == "JJ") {
        $('#idKegiatanSpj').val($('#idKegpop').val());
        $("#keteranganKegPop").val($('#ketKegpop').val());
        $("#kodeKegSpj").val($('#kodeKegpop').val());

        gridspj();

    } else {
        $('#idkeg' + id).val($('#idKegpop').val());
        $('#kegiatan' + id).val($('#kodeKegpop').val());
        $('#namakeg' + id).val($('#namaKegpop').val());

        setAkunCombo($('#idKegpop').val());
    }
}

function setAkunCombo(id) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenistrans = $('#jenisTransaksi').val();

    $.getJSON(getbasepath() + "/bku/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: ketcombo},
    function(result) {
        var banyak, kode, ket, baris;
        var opt = "";
        banyak = result.aData.length;

        if (jenistrans == "NP" || jenistrans == "NM" || jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5") {
            opt = '<option value="" selected></option>';
        }

        try {
            if (idcombo == 1) {
                baris = idrow;
            } else {
                baris = idbuton;
            }

            if (banyak > 0) {
                for (var i = 0; i < banyak; i++) {
                    kode = result.aData[i]['idBas'];
                    ket = result.aData[i]['kodeakun'];

                    opt += '<option value="' + kode + '" >' + ket + '</option>';
                    akunnama[kode] = result.aData[i]['namaakun'];

                }
                $("#akun" + baris).html(opt);
                var cek = "akun" + baris;
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
}

function setBeban() {
    clearrow();
    var opt = "";
    var jenistransaksi = $('#jenisTransaksi').val();

    $('#jourtable2').show();

    document.getElementById("beban").style.visibility = "visible";
    document.getElementById("labelbeban").style.visibility = "visible";
    document.getElementById("labelbeban").style.display = "block";
    
    document.getElementById("labeljenis").style.display = "none";
    document.getElementById("labelpengeluaran").style.display = "none";
    document.getElementById("labelnippptk").style.display = "none";
    document.getElementById("labelnamapptk").style.display = "none";

    if (jenistransaksi == "NP" || jenistransaksi == "NM") {   // NPD 
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        
        document.getElementById("labelkegiatanspj").style.display = "none";

        document.getElementById("nipPptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.visibility = "visible";
        document.getElementById("labelkegiatanspj").style.display = "block";

        document.getElementById("namaPptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.visibility = "visible";
        document.getElementById("labelkegiatanspj").style.display = "block";

        setColumn(2);


    } else if (jenistransaksi == "P1" || jenistransaksi == "P2" || jenistransaksi == "P3" || jenistransaksi == "P4" || jenistransaksi == "P5") {   //  Pajak 
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        //document.getElementById("keteranganKegPop").style.visibility = "hidden";
        //document.getElementById("labelkegiatanspj").style.visibility = "hidden";
        document.getElementById("labelkegiatanspj").style.display = "none";
        setColumn(2);


    } else if (jenistransaksi == "JJ") {
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        document.getElementById("keteranganKegPop").style.visibility = "visible";
        document.getElementById("labelkegiatanspj").style.visibility = "visible";
        
        document.getElementById("labelkegiatanspj").style.display = "block";
        ketcombo = "SPJ";

    } else if (jenistransaksi == "ST" || jenistransaksi == "LL") {   // SETORAN / Lain2
        if ($('#idskpd').val() == 761) {
            opt = '<option value="LS" >LS</option>';
        } else {
            opt = '<option value="UP" >UP/GU</option>';
            opt += '<option value="TU" >TU</option>';
            opt += '<option value="LS" >LS</option>';
        }

        //document.getElementById("keteranganKegPop").style.visibility = "hidden";
        //document.getElementById("labelkegiatanspj").style.visibility = "hidden";
        document.getElementById("labelkegiatanspj").style.display = "none";
        
        document.getElementById("jenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.display = "block";

        setColumn($("#jenis").val());

    } else {
        //document.getElementById("keteranganKegPop").style.visibility = "hidden";
        //document.getElementById("labelkegiatanspj").style.visibility = "hidden";
        
        document.getElementById("labelkegiatanspj").style.display = "none";
        
        document.getElementById("labelbeban").style.display = "none";
        
        //document.getElementById("labelbeban").style.visibility = "hidden";
        //document.getElementById("beban").style.visibility = "hidden";
    }

    $("#beban").html(opt);
    setPengeluaran();
}

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

function setPengeluaran() {
    var jenistrans = $('#jenisTransaksi').val();
    var beban = $("#beban").val();

    if (jenistrans !== "JJ") {
        clearrow();
    }

    if ((jenistrans == "ST" && beban == "UP") || (jenistrans == "LL" && beban == "UP")) {
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').hide();

    } else if (jenistrans == "ST" && beban == "TU") {
        document.getElementById("pengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.visibility = "hidden";
        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').show();
        setColumn(3);

    } else {
        //console.log("jenis = " + $("#jenis").val());
        document.getElementById("pengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.visibility = "hidden";
        $("#jenis").val(1);
        $('#jenis').attr("disabled", false);
        $('#jourtable2').show();
        setColumn($("#jenis").val());
    }

    if (jenistrans == "ST" && beban == "UP") {
        document.getElementById("btnTambah").style.visibility = "hidden";
    } else if ((jenistrans == "ST" && beban == "LS") || (jenistrans == "ST" && beban == "TU")) {
        document.getElementById("btnTambah").style.visibility = "visible";
    } else if (jenistrans == "LL" && beban == "UP") {
        console.log("masuk jenis LL dan beban UP");
        document.getElementById("btnTambah").style.visibility = "hidden";
    } else if (jenistrans == "LL" && beban == "LS") {
        document.getElementById("btnTambah").style.visibility = "visible";
    }

}

function setColumn(jenis) {
    var dgTest = document.getElementById("jourtable2");

    if (jenis == 1 || jenis == 4) {
        dgTest.rows[0].cells[3].style.display = "none";
        dgTest.rows[0].cells[4].style.display = "none";
        clearrow();

    } else {
        dgTest.rows[0].cells[3].style.display = "table-cell";
        dgTest.rows[0].cells[4].style.display = "table-cell";
        clearrow();
    }
}

function setGrid() {
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi == "JO") {
        $('#tabelDLL').hide();
        $('#tabelSP2D').show();
        $('#tabelSPJ').hide();

    } else if (jenistransaksi == "JJ") {
        $('#tabelDLL').hide();
        $('#tabelSP2D').hide();
        $('#tabelSPJ').show();
        gridspj();


    } else {
        $('#tabelSP2D').hide();
        $('#tabelDLL').show();
        $('#tabelSPJ').hide();
    }
}

function clearforadd() {
    var table = document.getElementById('jourtablebody2');
    var rows = table.rows;
    var jum = rows.length;

    if (idrow < jum) {
        clearrow();
    }
}

function getSkpdBtl() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku/json/getSkpdBtl", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak;
        banyak = result.aData.length;
        var opt = "";

        if (banyak == 0) {

            if ($('#idskpd').val() == 761) {
                opt = '<option value="2" >BTL BANTUAN</option>';
                opt += '<option value="4" >BIAYA</option>';
            }

        } else {

            if ($('#idskpd').val() == 761) {
                opt = '<option value="1" >BTL</option>';
                opt += '<option value="2" >BTL BANTUAN</option>';
                opt += '<option value="4" >BIAYA</option>';
            } else {
                opt = '<option value="1" >BTL</option>';
            }
        }

        $("#jenis").html(opt);

        getSkpdBl();
    });
}

function getSkpdBl() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    $.getJSON(getbasepath() + "/bku/json/getSkpdBl", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak;
        banyak = result.aData.length;
        var opt = $("#jenis").html();

        if (banyak == 0) {

        } else {
            if ($('#idskpd').val() !== 761) {
                opt += '<option value="3" >BL</option>';
            }
        }

        $("#jenis").html(opt);
    });
}

function setCurrentMonth() {
    //  $("#tglPosting").val("");
   // $('#tglPosting').datepicker('destroy');
    var bulan = parseInt($("#bulan").val());
    console.log("bulan = " + bulan);

    var currentTime = new Date();
    var startDateFrom = new Date(currentTime.getFullYear(), bulan - 1, 1);
    var startDateTo = new Date(currentTime.getFullYear(), bulan, 0);

    console.log("start Date From = " + startDateFrom);
    console.log("start Date To === " + startDateTo);

    //$("#datepicker").datepicker("setDate", startDateFrom);

    $("#tglPosting").datepicker({
        //dateFormat: 'dd.mm.yy',
        startDate: startDateFrom,
        endDate: startDateTo,
        onSelect: function() {
            $('#tglPosting').datepicker('option', 'startDate', startDateFrom);
             $('#tglPosting').datepicker('option', 'endDate', startDateTo);
        }
    });


}

