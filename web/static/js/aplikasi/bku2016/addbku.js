$(document).ready(function() {
    //gridspj();
    //setJenisPpkd();
    //$("#tglPosting").datepicker("setDate", new Date());
    getBebanSetorUP();
    //getKodeTutup();
    getBulanBySaldoAwal();
    getSuadana();
    document.getElementById("btnTambah").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D
    document.getElementById("btnHapus").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D
    document.getElementById("pilihKegSpj").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D
    document.getElementById("labelkegiatannm").style.display = "none"; // karena jenistransaksi selected = SP2D
    document.getElementById("labelkegiatannp").style.display = "none"; // karena jenistransaksi selected = SP2D
    document.getElementById("btnSimpan").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D; jenis SP2D tidak 
    document.getElementById("labelkegiatanpajak").style.display = "none";
    getSkpdBtl();
    setGrid();
    setBulan();
    setTanggalEnable();
    keteranganBulan();
    setBeban();
    getWilayah();

    if ($("#idskpd").val() == 761) {
        document.getElementById("labelwilayah").style.display = "block";
    } else {
        document.getElementById("labelwilayah").style.display = "none";
    }
    //setCurrentMonth();
});

// global variable
var idspp = new Array();
var tglpost = new Array();
var beban = new Array();
var jenis = new Array();
var akunnama = new Array();
var akunkode = new Array();
var jumbaris = 0;
var jumbarisspj = 0;
var jumbarisnm = 0;
var jumbarisnp = 0;
var idrow = 0;
var idbuton, idcombo, ketcombo;
var bulanval, sp2djenis, sp2dbeban;
var cek = "";
var banyakSetorUp;
var banyakTutupMax, kodeTutupMax, bulanTutupMax, saldoAwal, ketSaldoAwal;
var sisapajak = 0;
var saldoawalpajak = 0;

function setCurrentDate() {
    var dd = new Date();
    var m = dd.getMonth() + 1;
    var y = dd.getFullYear();
    var d = dd.getUTCDate();

    if (d < 10) {
        d = '0' + d;
    }
    if (m < 10) {
        m = '0' + m;
    }

    var tanggal = d + "/" + m + "/" + y;
    $('#tglPosting').val(tanggal);
    // console.log(" tglPosting == " + tanggal);

}

function setBulan() {

    $('#bulan').val(bulanval);
}

function setJenisBKU(suadana) {
    $("#jenisTransaksi").html("");
    //console.log("saldoAwal di setJenisBKU = "+saldoAwal);
    var idskpd = $("#idskpd").val();

    var opt = '<option value="-" selected>------------------------------------------------------- Pilih -----------------------------------------------------</option>';
    opt += '<option value="JO">1 - SP2D (melihat SP2D yang Belum di jurnal oleh KPKD)</option>';



    if (idskpd == 761) { // PPKD
        opt += '<option value="-"> PAJAK -------------------------------------------------------------------------------------------------------------</option>';
        opt += '<option value="P1">2.1 - PPH PS 21 (Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P2">2.2 - PPH PS 22 ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P3">2.3 - PPH PS 23 JASA I ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P4">2.4 - PPH PS 4 Ayat 2 ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P5">2.5 - PPN ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P6">2.6 - PPH Pasal 26 - Hadiah Undian ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="-">-----------------------------------------------------------------------------------------------------------------------</option>';
        //opt += '<option value="SB">3 - Sisa Belanja (khusus Beban=LS) diterima oleh Bendahara SKPD/UKPD</option>';
        opt += '<option value="KM">3 - Mencatat Kas Masuk (Transfer Pengisian KAS)</option>';
        /*
         if (saldoAwal > 0) { // if (suadana == "1" || saldoAwal > 0) { // swadana ditutup dulu
         opt += '<option value="-">-----------------------------------------------------------------------------------------------------------------------</option>';
         opt += '<option value="LL">4 - Lain-lain</option>';
         }
         */
    } else {
        //console.log("SET JENIS BKU");
        opt += '<option value="JJ">2 - SPJ - mencatat pengakuan belanja langsung per kegiatan dan per nomor bukti</option>';

        opt += '<option value="-">CEK ---------------------------------------------------------------------------------------------------------------</option>';
        opt += '<option value="C1">3 - Pencairan Cek dari Rekening Bank Bendahara Pengeluaran SKPD</option>';
        opt += '<option value="C2">4 - Setor Tunai ke Rekening Bendahara Pengeluaran SKPD</option>';
        opt += '<option value="-">NPD ---------------------------------------------------------------------------------------------------------------</option>';
        opt += '<option value="NP">5 - NPD KE PPTK/dll (Pemberian Uang Panjar kepada PPTK)</option>';
        opt += '<option value="NM">6 - PPTK/dll KE NPD (Pengembalian sisa Uang Panjar oleh PPTK)</option>';

        opt += '<option value="-"> PAJAK -------------------------------------------------------------------------------------------------------------</option>';
        opt += '<option value="P1">7.1 - PPH PS 21 ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P2">7.2 - PPH PS 22 ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P3">7.3 - PPH PS 23 JASA I ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P4">7.4 - PPH PS 4 Ayat 2 ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P5">7.5 - PPN ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="P6">7.6 - PPH Pasal 26 - Hadiah Undian ( Penerimaan / Penyetoran  Pajak)</option>';
        opt += '<option value="-">SETORAN --------------------------------------------------------------------------------------------------------</option>';
        opt += '<option value="ST">8 - Setoran ke PPKD (Setoran Sisa Belanja / Uang Persediaan)</option>';
        opt += '<option value="SB">9 - Sisa Belanja (khusus Beban=LS) diterima oleh Bendahara SKPD/UKPD</option>';
        /*
         if (saldoAwal > 0) { // if (suadana == "1" || saldoAwal > 0) { // swadana ditutup dulu
         opt += '<option value="-">-----------------------------------------------------------------------------------------------------------------------</option>';
         opt += '<option value="LL">10 - Lain-lain</option>';
         }
         */
    }

    $("#jenisTransaksi").html(opt);
}

function keteranganBulan() {
    var jenistransaksi = $('#jenisTransaksi').val();

    //getKodeTutup();
    getBulanBySaldoAwal();
    if (jenistransaksi == "JO") {
        getBanyakNoBukti();
        setNoBukti();
    }

    if (jenistransaksi == "ST") {
        setBeban();
    }
}

function gantiText(valjenis) {

    $("#keteranganKegPop").val("");
    $("#idKegpop").val("");

    $("#keteranganKegPopNm").val("");
    $("#idKegiatanNm").val("");

    $("#keteranganKegPopNp").val("");
    $("#idKegiatanNp").val("");

    $("#keteranganKegPopPj").val("");
    $("#idKegiatanPajak").val("");

    $('#btnTambah').attr("disabled", false);

    if (valjenis == 'JO') {
        var opt = '<select path="noBuktiDok" id="noBuktiDok" onchange="gantinobukti()" ><option </option>  </select>';
        $("#nobuktiketerangan").html(opt);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        getBanyakNoBukti();
        setNoBukti();
    } else if (valjenis == 'JJ') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "visible";

    } else if (valjenis == 'NM') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        //document.getElementById("pilihKegNM").style.visibility = "visible";

    } else if (valjenis == 'NP') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        //document.getElementById("pilihKegNP").style.visibility = "visible";

    } else if (valjenis == 'ST' && $("#beban").val() == "UP") {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } /*else if (valjenis == 'SB') {
     var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
     $("#nobuktiketerangan").html(teks);
     document.getElementById("btnTambah").style.visibility = "hidden";
     document.getElementById("pilihKegSpj").style.visibility = "hidden";
     clearrow();
     
     } */ else if (valjenis == 'C1' || valjenis == 'C2') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } else if (valjenis == 'LL') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } else if (valjenis == 'KM') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();

    } else if (valjenis.substr(0, 1) == 'P') {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok'  size='55' maxlength='50' />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "visible";
        document.getElementById("btnHapus").style.visibility = "visible";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();
        //$('#btnTambah').attr("disabled", true);
    } else {
        var teks = "<input type='text' name='noBuktiDok' id='noBuktiDok' size='55' maxlength='50'  />";
        $("#nobuktiketerangan").html(teks);
        document.getElementById("btnTambah").style.visibility = "visible";
        document.getElementById("btnHapus").style.visibility = "visible";
        document.getElementById("pilihKegSpj").style.visibility = "hidden";
        clearrow();
    }
}

function getBanyakNoBukti() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();

    $("#banyaknobukti").val(0); // refresh banyak no bukti;

    $.getJSON(getbasepath() + "/bku2016/json/getBanyakNoBukti", {tahun: tahun, idskpd: idskpd, bulan: bulan},
    function(result) {
        $("#banyaknobukti").val(result);
        //console.log("banyak no bukti = " + result);

    });
    //console.log("banyak no bukti val = " + $("#banyaknobukti").val()); // jangan di hapus, untuk triger.. ???
}

function setNoBukti() {

    var jenistransaksi = $('#jenisTransaksi').val();
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();

    $.getJSON(getbasepath() + "/bku2016/json/setNoBukti", {tahun: tahun, idskpd: idskpd, jenis: jenistransaksi, bulan: bulan},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = $("#banyaknobukti").val();
        console.log("banyak == " + banyak); // jangan dihapus
        try {
            //console.log("masuk try");
            //console.log("banyak di setNoBukti = " + banyak);
            for (var i = 0; i < banyak; i++) {
                //console.log("idsp2d = " + result.aData[i]['idsp2d']);

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

        getBanyakNoBukti();
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

    setCurrentDate();
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
            "sAjaxSource": getbasepath() + "/bku2016/json/listbku",
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
    //jumbarisspj = 0;
    var beban = $("#beban").val();
    //console.log("BEBAN SPJ = "+beban);
    if (beban == "UP" || beban == "TU") {
        if (typeof myTable == 'undefined') {
            myTable = $('#spjtable').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "bInfo": true,
                "bScrollCollapse": true,
                "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
                "iDisplayLength": 100,
                //"sScrollY": ($(window).height() * 108 / 100),
                "bFilter": false,
                "sAjaxSource": getbasepath() + "/bku2016/json/listbkuspj",
                "aaSorting": [[1, "asc"]],
                "fnDrawCallback": function() {
                    $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                },
                "fnServerParams": function(aoData) {
                    aoData.push(
                            {name: "tahun", value: $("#tahun").val()},
                    {name: "idskpd", value: $("#idskpd").val()},
                    {name: "nobku", value: -1}, // tambah baru belum ada no bku
                    {name: "beban", value: $("#beban").val()},
                    {name: "idkegiatan", value: $("#idKegiatanSpj").val()},
                    {name: "nobukti", value: "-****-"}  // untuk inisialisasi awal, jangan di kosongin.. pendefinisian di atas pake var nobukti ga ngaruh -_-"
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
                    var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' readOnly='true' onkeyup='pasangvalidatebesarperfield(" + index + ")' onchange='pasangvalidatebesarperfield(" + index + ")' />";
                    var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") />";
                    var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaakun'] + "</textarea>";
                    var textnamaakun = "<input type='hidden' id='namaakun" + index + "' value='" + aData['namaakun'] + "' />";
                    var textkodeakun = "<input type='hidden' id='kodeakun" + index + "' value='" + aData['kodeakun'] + "' />";
                    //jumbarisspj = jumbarisspj + 1;

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
                    {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                    {"mDataProp": "kodeakun", "bSortable": false, sClass: "left", "sWidth": "8%"},
                    {"mDataProp": "namaakun", "bSortable": false, sClass: "left", "sWidth": "20%"},
                    {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", "sWidth": "13%"},
                    {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right", "sWidth": "13%"},
                    {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right", "sWidth": "13%"},
                    {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right", "sWidth": "13%"},
                    {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right", "sWidth": "13%"},
                    {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "3%"}
                ]
            });
        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }
        getBanyakListSPJ();
        getNamaNipSpjPanjar();
        getNilaiSisaSpj();
        getNilaiValidasiSisaSpj();

        $("#sumspj").val(0);
        $("#totalspjhidden").val(0);
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
    hitungnilaispj();
}
function setdisabled(param, index) {
    $("#nilaiinput" + index).attr("readonly", param);

}

function pasangvalidatebesarperfield(index) {
    var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");
    //var total = 0;

    if (nilaiinput > nilaisisa) {
        bootbox.alert("Nilai BKU tidak boleh lebih besar dari Sisa SPD."); // ganti pesan : sisa bku -> sisa spd
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaiinput' + index).autoNumeric('set', nilaisisa); // nilaisisa harus yang di unformat :)
    }

    hitungnilaispj();
}

function simpan() {

    $('#btnSimpan').attr("disabled", true);
document.getElementById("btnSimpan").style.visibility = "hidden";
    var filling = $('#fileInbox').val();
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

    var table4 = document.getElementById('nptablebody');
    var rows4 = table4.rows;
    var jumnp = rows4.length;

    var table5 = document.getElementById('nmtablebody');
    var rows5 = table5.rows;
    var jumnm = rows5.length;

    var tglPost = $("#tglPosting").val();
    var nobukti = $("#noBuktiDok").val();
    var tglDok = $("#tglDok").val();
    var beban = $("#beban").val();
    var jenis = $("#jenis").val();
    var pengeluaran = $("#pengeluaran").val();
    var datalengkap = true;
    var idKegiatanSpj = $("#idKegiatanSpj").val();
    var bulanTglPost = tglPost.substr(3, 2);
    var tahunTglPost = tglPost.substr(6, 4);
    var bulan = $("#bulan").val();
    var uraian = $("#uraian").val();
    var uraianpn = $("#uraianterima").val();
    var uraianpg = $("#uraiankeluar").val();
    var nilaisisaspj = accounting.unformat($("#sisaspj").val(), ",");
    var nilaitotalspj = $("#totalspjhidden").val();
    var nilaisisaspd = accounting.unformat($("#sisaspd").val(), ",");
    var validasinilaispj, ketvalidasi;
    var nippptk = $("#nipPptk").val();
    var namapptk = $("#namaPptk").val();
    var akunlain = $("#keteranganAkunLain").val();

    //console.log("tahunTglPost = "+tahunTglPost);
    if (jenistransaksi == "JO") { // SP2D
        //console.log("jum == " + jum);
        //console.log("uraianbukti1.val() == " + $("#uraianbukti1").val());

        if ((jum > 0) && ($("#uraianbukti1").val() !== "")) {
            if (filling == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else {
                simpanSP2D();
            }
        }
    }

    if (jenistransaksi == "JJ") { // SPJ
        datalengkap = true;
        var banyakcek = 0;
        /* validasi TU disamain kaya UP
         if (beban == "UP") {
         if (nilaisisaspj <= nilaisisaspd) { // validasi ke nilai sisa spj
         validasinilaispj = nilaisisaspj;
         ketvalidasi = "Sisa Uang Persediaan (Kas SKPD)";
         
         } else { // validasi ke sisa spd
         validasinilaispj = nilaisisaspd;
         ketvalidasi = "Total Sisa SPD";
         }
         } else if (beban == "TU") {
         validasinilaispj = nilaisisaspj;
         ketvalidasi = "Sisa Uang Persediaan (Kas SKPD)";
         }*/

        if (nilaisisaspj <= nilaisisaspd) { // validasi ke nilai sisa spj
            validasinilaispj = nilaisisaspj;
            ketvalidasi = "Sisa Uang Persediaan (Kas SKPD)";

        } else { // validasi ke sisa spd
            validasinilaispj = nilaisisaspd;
            ketvalidasi = "Total Sisa SPD";
        }

        if ((jum3 > 0) && ($("#akunnama1").val() !== "")) {


            for (var a = 1; a <= jum3; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jum3; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai < 0) { // validasinya ganti, nilai boleh 0 
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }


            if (tglPost == "" || nobukti == "" || tglDok == "" || idKegiatanSpj == "" || filling == "" || nippptk == "" || namapptk == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else if (nilaitotalspj > validasinilaispj) {
                bootbox.alert("Total SPJ Tidak Boleh Lebih Besar dari " + ketvalidasi);
            } else if ((nilaitotalspj > 50000000) && ($("#kodePembayaran").val() == "1")) { // Tunai

                bootbox.confirm("Apakah Transaksi Senilai Rp " + $("#sumspj").val() + " Akan Dilakukan Secara Tunai ? ", function(result) {
                    if (result) {

                        simpanSPJ();
                    } else {
                        bootbox.hideAll();
                        return result;
                    }
                });

            } else {
                simpanSPJ();
            }

        }
    }

    if (jenistransaksi == "C1" || jenistransaksi == "C2") { // CEK

        if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraianpn == "" || uraianpg == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            simpanCek();
        }
    }

    if (jenistransaksi == "NM") { // NM
        datalengkap = true;
        var banyakcek = 0;

        if ((jumnm > 0) && ($("#nilaisebelum1").val() !== "")) {

            for (var a = 1; a <= jumnm; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jumnm; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai <= 0) {
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }


            if (tglPost == "" || nobukti == "" || tglDok == "" || $("#idKegiatanNm").val() == "" || filling == "" || uraianpn == "" || uraianpg == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else {
                simpanNPNM();
            }
        }
    }

    if (jenistransaksi == "NP") { // NP
        datalengkap = true;
        var banyakcek = 0;

        if ((jumnp > 0) && ($("#nilaisebelum1").val() !== "")) {

            for (var a = 1; a <= jumnp; a++) {
                if ($('#penanda' + a).val() == 1) {
                    banyakcek = banyakcek + 1;
                }
            }

            if (banyakcek > 0) {
                for (var a = 1; a <= jumnp; a++) {
                    var nilai = parseFloat(accounting.unformat($('#nilaiinput' + a).val(), ","));

                    if ($('#penanda' + a).val() == 1 && nilai <= 0) {
                        datalengkap = false;
                    }
                }
            } else if (banyakcek == 0) {
                datalengkap = false;
            }

            if (tglPost == "" || nobukti == "" || tglDok == "" || $("#idKegiatanNp").val() == "" || filling == "" || uraianpn == "" || uraianpg == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else {
                simpanNPNM();
            }
        }

        /*if (jum2 > 0) {
         for (var a = 1; a <= idrow; a++) {
         if ($('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
         datalengkap = false;
         }
         }
         
         if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || datalengkap == false) {
         bootbox.alert("Pengisian Data Harus Lengkap");
         } else if (bulanTglPost !== bulan) {
         bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
         } else {
         simpanNPNM();
         }
         } */
    }

    if (jenistransaksi == "SB") { // SETORAN BENDAHARA
        /*
         if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "") {
         bootbox.alert("Pengisian Data Harus Lengkap");
         } else if (bulanTglPost !== bulan) {
         bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
         } else {
         simpanSetorBendahara();
         }
         */

        if (jum2 > 0) {
            if (jenis == 1) { // BTL = tanpa kegiatan
                for (var a = 1; a <= idrow; a++) {

                    if ($('#akun' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                        datalengkap = false;
                    }
                }

                if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                    bootbox.alert("Pengisian Data Harus Lengkap");
                } else if (bulanTglPost !== bulan) {
                    bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
                } else if (tahunTglPost !== $("#tahun").val()) {
                    bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
                } else {
                    simpanSTtanpakeg();
                }

            } else if (jenis == 3) { // BL = dengan kegiatan
                for (var a = 1; a <= idrow; a++) {

                    if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                        datalengkap = false;
                    }
                }

                if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                    bootbox.alert("Pengisian Data Harus Lengkap");
                } else if (bulanTglPost !== bulan) {
                    bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
                } else if (tahunTglPost !== $("#tahun").val()) {
                    bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
                } else {
                    simpanNPD();
                }
            }
        }

    }

    if (jenistransaksi == "ST") { // SETORAN
        if (beban == "UP") {
            if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "") {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
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

                    if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                        bootbox.alert("Pengisian Data Harus Lengkap");
                    } else if (bulanTglPost !== bulan) {
                        bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
                    } else if (tahunTglPost !== $("#tahun").val()) {
                        bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
                    } else {
                        simpanSTtanpakeg();
                    }

                } else if (jenis == 2 || jenis == 3) { // BL dan BTL Bantuan = dengan kegiatan
                    for (var a = 1; a <= idrow; a++) {

                        if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
                            datalengkap = false;
                        }
                    }

                    if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                        bootbox.alert("Pengisian Data Harus Lengkap");
                    } else if (bulanTglPost !== bulan) {
                        bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
                    } else if (tahunTglPost !== $("#tahun").val()) {
                        bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
                    } else {
                        simpanNPD();
                    }
                }
            }
        }
    }

    if (jenistransaksi == "P1" || jenistransaksi == "P2" || jenistransaksi == "P3" || jenistransaksi == "P4" || jenistransaksi == "P5" || jenistransaksi == "P6") { // PAJAK
        //console.log("jenis bayar : " + $("#jenisPembayaran").val());
        var nilaivalid; // accounting.unformat($("#pengeluaran").val(), ",")
        var totalpajakkeluar = 0;

        for (var i = 1; i <= idrow; i++) {
            totalpajakkeluar += accounting.unformat($("#nilaiKeluar" + i).val(), ",");
        }

        if ($("#jenisPembayaran").val() == "PN") {
            nilaivalid = '#nilaiMasuk';

        }
        if ($("#jenisPembayaran").val() == "PG") {
            nilaivalid = '#nilaiKeluar';
        }

        if (jum2 > 0) {
            for (var a = 1; a <= idrow; a++) {

                if ($('#akun' + a).val() == "" || accounting.unformat($(nilaivalid + a).val(), ",") <= 0) {
                    datalengkap = false;
                }
            }
            console.log("sisapajak = " + sisapajak);
            if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
                bootbox.alert("Pengisian Data Harus Lengkap");
            } else if (bulanTglPost !== bulan) {
                bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
            } else if (tahunTglPost !== $("#tahun").val()) {
                bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
            } else if ((totalpajakkeluar > sisapajak) && ($("#jenisPembayaran").val() == "PG")) {
                bootbox.alert("Total Pengeluaran Pajak Tidak Boleh Lebih Besar Dari Sisa Pajak");
            } else {
                simpanPajak();
            }
        }
    }

    if (jenistransaksi == "LL") {
        if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "" || akunlain == "" || nippptk == "" || namapptk == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            simpanLainLain();
        }
    }

    if (jenistransaksi == "KM") {
        if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "" || nippptk == "" || namapptk == "") {
            bootbox.alert("Pengisian Data Harus Lengkap");
        } else if (bulanTglPost !== bulan) {
            bootbox.alert("Tanggal Transaksi Harus Sesuai Bulan yang Dipilih");
        } else if (tahunTglPost !== $("#tahun").val()) {
            bootbox.alert("Tahun Transaksi Harus Sesuai Tahun yang Login");
        } else {
            simpanKasMasuk();
        }
    }

    $('#btnSimpan').attr("disabled", false);
document.getElementById("btnSimpan").style.visibility = "visible";
    /* if (jenistransaksi == "LL") {
     if (beban == "UP") {
     if (tglPost == "" || nobukti == "" || tglDok == "" || pengeluaran == "" || filling == "" || uraian == "") {
     bootbox.alert("Pengisian Data Harus Lengkap");
     } else if (bulanTglPost !== bulan) {
     bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
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
     
     if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
     bootbox.alert("Pengisian Data Harus Lengkap");
     } else if (bulanTglPost !== bulan) {
     bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
     } else {
     simpanSTtanpakeg();
     }
     
     } else if (jenis == 2 || jenis == 3) { // BL dan BTL Bantuan = dengan kegiatan
     for (var a = 1; a <= idrow; a++) {
     
     if ($('#akun' + a).val() == "" || $('#idkeg' + a).val() == "" || $('#nilaiMasuk' + a).val() == "" || $('#nilaiKeluar' + a).val() == "") {
     datalengkap = false;
     }
     }
     
     if (tglPost == "" || nobukti == "" || tglDok == "" || filling == "" || uraian == "" || datalengkap == false) {
     bootbox.alert("Pengisian Data Harus Lengkap");
     } else if (bulanTglPost !== bulan) {
     bootbox.alert("Tanggal Transaksi Harus Sesua Bulan yang Dipilih");
     } else {
     simpanNPD();
     }
     }
     }
     }
     }*/
}

function simpanSetorBendahara() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK;
    var akun, idbas;
    var nilaipengeluaran = (accounting.unformat($("#pengeluaran").val(), ",")).toString();

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraian = $("#uraian").val();
    nipPPTK = $("#nipPptk").val();
    namaPPTK = $("#namaPptk").val();

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

    var varurl = getbasepath() + "/bku2016/json/prosessimpansetorsb";
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
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        nilaimasuk: nilaipengeluaran,
        nilaikeluar: "0",
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#uraian").val("");

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });

}

function simpanLainLain() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var jenisbayar = $("#jenisPembayaran").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK;
    var nilaimasuk, nilaikeluar;
    var beban, jenis, akun, idbas;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraian = $("#uraian").val();
    nipPPTK = $("#nipPptk").val();
    namaPPTK = $("#namaPptk").val();
    beban = "UP";
    jenis = "3";
    akun = $("#kodeAkunLain").val();
    idbas = parseInt($("#idbaslain").val());

    if (jenisbayar == "PN") {
        nilaimasuk = (accounting.unformat($("#pengeluaran").val(), ",")).toString();
        nilaikeluar = "0";

    } else if (jenisbayar == "PG") {
        nilaimasuk = "0";
        nilaikeluar = (accounting.unformat($("#pengeluaran").val(), ",")).toString();

    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpansetorsb";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: jenis,
        beban: beban,
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        nilaimasuk: nilaimasuk,
        nilaikeluar: nilaikeluar,
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#keteranganAkunLain").val("");
        $("#nipPptk").val("");
        $("#namaPptk").val("");
        $("#pengeluaran").val("");

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });

}

function simpanKasMasuk() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK;
    var nilaimasuk, nilaikeluar;
    var beban, jenis, akun, idbas;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraian = $("#uraian").val();
    nipPPTK = $("#nipPptk").val();
    namaPPTK = $("#namaPptk").val();
    beban = "";
    jenis = "";
    akun = "";
    idbas = 0;

    nilaimasuk = (accounting.unformat($("#pengeluaran").val(), ",")).toString();
    nilaikeluar = "0";

    var varurl = getbasepath() + "/bku2016/json/prosessimpansetorsb";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: jenis,
        beban: beban,
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        nilaimasuk: nilaimasuk,
        nilaikeluar: nilaikeluar,
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#keteranganAkunLain").val("");
        $("#nipPptk").val("");
        $("#namaPptk").val("");
        $("#pengeluaran").val("");

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });

}

function simpanSetorUP() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK;
    var nilaipengeluaran = (accounting.unformat($("#pengeluaran").val(), ",")).toString();
    var beban, jenis, akun, idbas;
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraian = $("#uraian").val();


    if ($('#jenisTransaksi').val() == "LL") {
        nipPPTK = $("#nipPptk").val();
        namaPPTK = $("#namaPptk").val();
        beban = "";
        jenis = "";
        akun = $("#kodeAkunLain").val();
        idbas = $("#idbaslain").val();

    } else {
        namaPPTK = "";
        nipPPTK = "";
        beban = $("#beban").val();
        jenis = "3";
        akun = "";
        idbas = "";
    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpansetorup";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: $('#jenisTransaksi').val(),
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        jenis: jenis,
        beban: beban,
        nilaikeluar: nilaipengeluaran, //accounting.unformat($("#pengeluaran").val(), ","),
        fileinbox: fileinbox,
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
        carabayar: $("#kodePembayaran").val(),
        nilaimasuk: 0,
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#keteranganAkunLain").val("");
        $("#nipPptk").val("");
        $("#namaPptk").val("");
        $("#pengeluaran").val("");

        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
        banyakSetorUp = 1;
        setBeban();
    });

}

function simpanCek() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    var jenistransaksi = $('#jenisTransaksi').val();
    var nilaimasuk, nilaikeluar;
    var akunpn, akunpg, idbaspn, idbaspg, uraianpn, uraianpg, carabayarpn, carabayarpg;
    var nilaipengeluaran = (accounting.unformat($("#pengeluaran").val(), ",")).toString();
    //console.log("nilaipengeluaran = " + nilaipengeluaran);

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraianpn = $('#uraianterima').val();
    uraianpg = $('#uraiankeluar').val();

    if (jenistransaksi == "C1") { // Pencairan cek dari rekening bendahara pengeluaran skpd
        nilaimasuk = (accounting.unformat($("#pengeluaran").val(), ",")).toString(); //$('#pengeluaran').val();
        nilaikeluar = "0";
        akunpn = "1.1.1.03.01.001"; // tunai
        akunpg = "1.1.1.03.01.002"; // bank
        carabayarpn = "1";
        carabayarpg = "2";
        idbaspn = "9947";
        idbaspg = "9948";

    } else if (jenistransaksi == "C2") { // Setor ke rekening bendahara pengeluaran skpd
        nilaimasuk = "0";
        nilaikeluar = (accounting.unformat($("#pengeluaran").val(), ",")).toString();//$('#pengeluaran').val();
        akunpn = "1.1.1.03.01.002"; // bank
        akunpg = "1.1.1.03.01.001"; // tunai
        carabayarpn = "2";
        carabayarpg = "1";
        idbaspn = "9948";
        idbaspg = "9947";
    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpancek";
    var dataac = [];

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        tglposting: tanggal,
        kodetransaksi: jenistransaksi,
        nobukti: $("#noBuktiDok").val(),
        tgldok: $("#tglDok").val(),
        nilaikeluar: nilaikeluar,
        nilaimasuk: nilaimasuk,
        akunpn: akunpn,
        akunpg: akunpg,
        idbaspn: idbaspn,
        idbaspg: idbaspg,
        nilaicek: nilaipengeluaran, //accounting.unformat($("#pengeluaran").val(), ","), // $('#pengeluaran').val(),
        fileinbox: fileinbox,
        uraianpn: uraianpn,
        uraianpg: uraianpg,
        carabayarpn: carabayarpn,
        carabayarpg: carabayarpg
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $('#pengeluaran').val("");
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });

}

function simpanSTtanpakeg() {

    var tglPost = $("#tglPosting").val();
    var fileinbox = $("#fileInbox").val();
    var dd, mm, yy, tanggal;
    var uraian, namaPPTK, nipPPTK;

    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)

    uraian = $("#uraian").val();

    if ($('#jenisTransaksi').val() == "LL" || $('#jenisTransaksi').val() == "SB") {
        //uraian = $("#uraian").val();
        nipPPTK = $("#nipPptk").val();
        namaPPTK = $("#namaPptk").val();

    } else {
        // uraian = "";
        namaPPTK = "";
        nipPPTK = "";
    }


    var varurl = getbasepath() + "/bku2016/json/simpantanpakeg";
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
            kodeakun: $("#valkodeakun" + id).val() //$('select[name="akun' + id + '"]').find(":selected").text()
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
        namapptk: namaPPTK,
        nippptk: nipPPTK,
        uraian: uraian,
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
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#uraian").val("");
        $('#btnSimpan').attr("disabled", false);
        clearrow();
    });
}

function simpanPajak() {

    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal, jenis;
    var jenistrans = $('#jenisTransaksi').val();
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)
    var fileinbox = $("#fileInbox").val();
    var nipPPTK, namaPPTK, uraian; // = $("#nipPptk").val();
    var idkegiatan, kodekegiatan;

    jenis = $("#jenis").val();
    nipPPTK = "";
    namaPPTK = "";
    uraian = $("#uraian").val();

    if (jenis == 1) {
        idkegiatan = "";
        kodekegiatan = "";

    } else if (jenis == 3) {
        idkegiatan = $("#idKegiatanPajak").val();
        kodekegiatan = $("#kodeKegPajak").val();
    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpanpajak";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < idrow; i++) { // list
        var id = i + 1;

        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            uraianbukti: $("#uraian").val(), //$("#namaakun" + id).val(),
            kodeakun: $("#valkodeakun" + id).val() //$('select[name="akun' + id + '"]').find(":selected").text()
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
        uraian: uraian,
        fileinbox: fileinbox,
        carabayar: $("#kodePembayaran").val(),
        kodekegiatan: kodekegiatan,
        idkegiatan: idkegiatan,
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
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#uraian").val("");
        $('#btnSimpan').attr("disabled", false);
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
    var nipPPTK, namaPPTK, uraian; // = $("#nipPptk").val();
    var idspd = "";

    if (jenistrans == "JJ" || jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {
        jenis = "3";
    } else {
        jenis = $('#jenis').val();
    }

    if (jenistrans == "SB") {
        nipPPTK = $("#nipPptk").val();
        namaPPTK = $("#namaPptk").val();
    } else {
        nipPPTK = "";
        namaPPTK = "";
    }

    if (jenistrans == "ST" || jenistrans == "SB") {
        uraian = $("#uraian").val();

    } else {
        uraian = "";
    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpannpd";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < idrow; i++) { // list
        var id = i + 1;
        if ($("#beban").val() == "TU"){
            idspd = $("#idspd" + id).val();
        } 
        
        var pararr = {
            nilaimasuk: parseFloat(accounting.unformat($('#nilaiMasuk' + id).val(), ",")),
            nilaikeluar: parseFloat(accounting.unformat($('#nilaiKeluar' + id).val(), ",")),
            idbas: $("#akun" + id).val(),
            kodekegiatan: $("#kegiatan" + id).val(),
            uraianbukti: $("#uraian").val(), //$("#namaakun" + id).val(),
            idkegiatan: $("#idkeg" + id).val(),
            kodeakun: $("#valkodeakun" + id).val(), //$('select[name="akun' + id + '"]').find(":selected").text()
            idspd: idspd
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
        uraian: uraian,
        fileinbox: fileinbox,
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
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#uraian").val("");
        $('#btnSimpan').attr("disabled", false);
        clearrow();
    });
}

function simpanNPNM() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tglPost = $("#tglPosting").val();
    var dd, mm, yy, tanggal, jumbaris;
    var akunpn, akunpg, idkeg, kodekeg, idbaspn, idbaspg, carabayarpn, carabayarpg;
    var jenistrans = $('#jenisTransaksi').val();
    dd = tglPost.substr(0, 2);
    mm = tglPost.substr(3, 2);
    yy = tglPost.substring(6);
    tanggal = yy + mm + dd;
    var fileinbox = $("#fileInbox").val();
    var uraianpn = $('#uraianterima').val();
    var uraianpg = $('#uraiankeluar').val();
    var carabayar = $('#kodePembayaran').val();
    var akunbayar, idbayar;

    if (carabayar == 1) {
        akunbayar = "1.1.1.03.01.001"; // tunai
        idbayar = "9947";

    } else if (carabayar == 2) {
        akunbayar = "1.1.1.03.01.002"; // bank
        idbayar = "9948";
    }

    if (jenistrans == "NP") {
        akunpn = "1.1.1.03.01.003"; // panjar
        akunpg = akunbayar; // "1.1.1.03.01.001"; // tunai
        jumbaris = jumbarisnp;
        idkeg = $('#idKegiatanNp').val();
        kodekeg = $('#kodeKegNp').val();
        idbaspn = "9949";
        idbaspg = idbayar; //"9947";
        carabayarpn = "3";
        carabayarpg = carabayar;

    } else if (jenistrans == "NM") {
        akunpn = akunbayar; //  "1.1.1.03.01.001"; // tunai
        akunpg = "1.1.1.03.01.003"; // panjar
        jumbaris = jumbarisnm;
        idkeg = $('#idKegiatanNm').val();
        kodekeg = $('#kodeKegNm').val();
        idbaspn = idbayar; //"9947";
        idbaspg = "9949";
        carabayarpn = carabayar;
        carabayarpg = "3";
    }

    var varurl = getbasepath() + "/bku2016/json/prosessimpannpnm";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < jumbaris; i++) { // list
        var id = i + 1;

        var pararr = {
            nilainpd: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")),
            penanda: $("#penanda" + id).val()
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
        idkegiatan: idkeg,
        kodekegiatan: kodekeg,
        fileinbox: fileinbox,
        akunpn: akunpn,
        akunpg: akunpg,
        idbaspn: idbaspn,
        idbaspg: idbaspg,
        namapptk: $("#namaPptk").val(),
        nippptk: $("#nipPptk").val(),
        uraianpn: uraianpn,
        uraianpg: uraianpg,
        carabayarpn: carabayarpn,
        carabayarpg: carabayarpg,
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

        $("#keteranganKegPopNm").val("");
        $("#keteranganKegPopNp").val("");
        $("#idKegpop").val("");
        $("#tglPosting").val("");
        $("#fileInbox").val("");
        $("#noBuktiDok").val("");
        $("#tglDok").val("");
        $("#namaPptk").val("");
        $("#nipPptk").val("");
        clearrownp();
        clearrownm();
        bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
        $('#btnSimpan').attr("disabled", false);
    });
}

function simpanSP2D() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var fileinbox = $("#fileInbox").val();
    var kodeakun, uraiankode, uraianket, kodekeg, idkeg, tgldok, tglgabung;

    if (jumbaris > 0) {

        var varurl = getbasepath() + "/bku2016/json/prosessimpan";
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
            $('#btnSimpan').attr("disabled", false);
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

        var varurl = getbasepath() + "/bku2016/json/prosessimpanspj";
        var dataac = [];
        var nilailist = [];
        var i;
        var uraianbukti;

        for (i = 0; i < jumbarisspj; i++) { // list
            var id = i + 1;
            uraianbukti = "Dibayar " + $('#keteranganKegPop').val() + " " + $("#namaakun" + id).val() + " - " + $("#uraian").val();

            var pararr = {
                nilaimasuk: "0",
                nilaikeluar: parseFloat(accounting.unformat($('#nilaiinput' + id).val(), ",")),
                idbas: $("#idbas" + id).val(),
                uraianbukti: uraianbukti.substr(0, 400),
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
            uraianinput: $("#uraian").val(),
            carabayar: $("#kodePembayaran").val(),
            nipPPTK: $("#nipPptk").val(),
            namaPPTK: $("#namaPptk").val(),
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
            $("#tglPosting").val("");
            $("#fileInbox").val("");
            $("#noBuktiDok").val("");
            $("#tglDok").val("");
            clearrowspj();
            $("#sumspj").val(0);
            $("#totalspjhidden").val(0);
            bootbox.alert("Data Buku Kas Umum Berhasil Disimpan");
            $('#btnSimpan').attr("disabled", false);
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

function clearrownp() {
    var i;
    var table = document.getElementById('nptablebody');
    var rows = table.rows;
    var jum = rows.length;

    idrow = 0;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}

function clearrownm() {
    var i;
    var table = document.getElementById('nmtablebody');
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
        tambahRowSTBtl();   // tanpa kolom kegiatan dan nama kegiatan

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

    } else {
        idcombo = 2;

        if (jenistrans == "ST") {
            if (jenis == 2) {
                ketcombo = "BANTUAN";
                tambahRowSTBl();
            } else if (jenis == 3) {
                ketcombo = "BL";
                if ($("#beban").val() == "TU") {
                    tambahRowSTTU();
                } else {
                    tambahRowSTBl();
                }
            }


        } else if (jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {    // Pajak
            //ketcombo = "SPJ";
            if (jenis == 1) {
                ketcombo = "BTL";
            } else if (jenis == 3) {
                ketcombo = "SPJ";
            }

            tambahRowSTBtl();

        } else if (jenistrans == "LL") {    // Lain-Lain
            if (jenis == 2) {
                ketcombo = "BANTUAN";
            } else if (jenis == 3) {
                ketcombo = "BL";
            }
            tambahRow();

        } else if (jenistrans == "SB") {    // Setoran Bendahara

            if (jenis == 1) {
                idcombo = 1;
                ketcombo = "BTL";
                tambahRowSBBtl();
            } else if (jenis == 3) {
                idcombo = 2;
                ketcombo = "BL";
                tambahRowSBBl();
            }

        }

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
    //var cell5 = row.insertCell(4);

    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 900px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    //cell3.innerHTML = "<td> <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
    cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();
    setAkunCombo(0,0);

}

function tambahRow() {
    clearforadd();

    var jenistrans = $('#jenisTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    //var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "<input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 500px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    //cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    //cell3.innerHTML = "<td> <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > </td>";
    cell3.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell4.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:250px;' readonly='true' > </textarea> </td>";

    if (jenistrans == "LL") {
        cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
        cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    } else {
        if (jenisbayar == "PN") {
            cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
            cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

        } else {
            cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
            cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

        }
    }

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
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
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
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
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
    cell4.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatan?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell5.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:350px;' readonly='true' > </textarea> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
    cell7.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowSTBtl() {
    clearforadd();
    var jenistrans = $('#jenisTransaksi').val();
    var jenisbayar = $('#jenisPembayaran').val();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    //var cell5 = row.insertCell(4);

    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 900px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";

    if (jenistrans.substr(0, 1) == "P") {
        if (jenisbayar == "PN") {
            cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
            cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

        } else {
            cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
            cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

        }
    } else {
        cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";
        cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    }

    formatnumberonkeyup();
    setAkunCombo(0,0);

}

function tambahRowSTBl() {
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
    //var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "<input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 500px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    //cell3.innerHTML = "<td class='center'><textarea  id='namaakun" + idrow + "' name='namaakun" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:300px;' readonly='true' > </textarea> </td>";
    //cell3.innerHTML = "<td> <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > </td>";
    cell3.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatansetoran?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell4.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:250px;' readonly='true' > </textarea> </td>";
    cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'   readonly='true' value = '0' /> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}

function tambahRowSTTU() {
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
    //var cell7 = row.insertCell(6);

    cell1.innerHTML = "<td class= 'center' >" + idrow + "<input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='idspd" + idrow + "' name='idspd" + idrow + "' / >  <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 500px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatansetoranTU?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell4.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:250px;' readonly='true' > </textarea> </td>";
    cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'   readonly='true' value = '0' /> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' value = '0' /> </td>";

    formatnumberonkeyup();

}


function tambahRowSBBtl() {
    clearforadd();

    idrow += 1;

    var table = document.getElementById('jourtablebody2');
    var row = table.insertRow();

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = "<td class= 'center' >" + idrow + " <input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 900px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney'  value = '0' /> </td>";
    cell4.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

    formatnumberonkeyup();
    setAkunCombo(0,0);
    //console.log("cek idrow di tambahRowSBBtl = " + idrow);
}

function tambahRowSBBl() {
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

    cell1.innerHTML = "<td class= 'center' >" + idrow + "<input type='hidden'  id='namaakun" + idrow + "' name='namaakun" + idrow + "' / > <input type='hidden'  id='valkodeakun" + idrow + "' name='valkodeakun" + idrow + "' / > </td>";
    cell2.innerHTML = "<td class='center'><select id='akun" + idrow + "' name='akun" + idrow + "' style='width: 500px;' onchange='setNamaAkun(this.id)' ><option></option></select> </td>";
    cell3.innerHTML = "<td class='center'><input type='text' id='kegiatan" + idrow + "' size='13' name='kegiatan" + idrow + "' readonly='true' > <input type='hidden' id='idkeg" + idrow + "' name='idkeg" + idrow + "' > <a id='pilihkeg" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getbutton(" + idrow + "), ambilketerangan()' href='" + getbasepath() + "/bku2016/listkegiatansetoran?target='_top'' title='Pilih Kegiatan'  > <i class='icon-zoom-in'></i> Pilih</a> </td>";
    cell4.innerHTML = "<td class='center'><textarea  id='namakeg" + idrow + "' name='namakeg" + idrow + "' class='m-wrap large valid' style='border:none;margin:0;width:250px;' readonly='true' > </textarea> </td>";
    cell5.innerHTML = "<td> <input type='text' name='nilaiMasuk" + idrow + "' id='nilaiMasuk" + idrow + "' class='inputmoney' value = '0' /> </td>";
    cell6.innerHTML = "<td> <input type='text' name='nilaiKeluar" + idrow + "' id='nilaiKeluar" + idrow + "' class='inputmoney' readonly='true' value = '0' /> </td>";

    formatnumberonkeyup();

}


function getbutton(id) {
    idbuton = id;
}

function getKegiatan() {
    var jenistrans = $('#jenisTransaksi').val();
    var id = idbuton;

    //console.log("idkegiatan = " + $('#idKegpop').val());
    if (jenistrans == "JJ") {
        var beban = $('#bebanSpjpop').val();
        var grid = document.getElementById('spjtable');
        if (beban == "UP/GU") {
            grid.rows[0].cells[4].innerText = 'Nilai LS (Kontrak)';
            grid.rows[0].cells[6].innerText = 'Sisa SPD';
            document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan UP/GU (Kas SKPD) :';
            document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai SPP TU (4) :';
            beban = "UP";

        } else {
            grid.rows[0].cells[4].innerText = 'Nilai SPP TU';
            grid.rows[0].cells[6].innerText = 'Sisa SPP TU';
            document.getElementById('textlabelsisaspj').innerHTML = 'Sisa Uang Persediaan TU (Kas SKPD) :';
            document.getElementById('txtlabelnilaitu').innerHTML = 'Nilai BKU TU (4) :';

        }

        //console.log("beban SPJ = " + beban);
        $('#beban').prop("disabled", true);

        $('#idKegiatanSpj').val($('#idKegpop').val());
        $("#keteranganKegPop").val($('#ketKegpop').val());
        $("#kodeKegSpj").val($('#kodeKegpop').val());
        $("#beban").val(beban);

        gridspj();

    } else if (jenistrans == "NP") {
        //console.log("masuk NP");
        $('#idKegiatanNp').val($('#idKegpop').val());
        $("#keteranganKegPopNp").val($('#ketKegpop').val());
        $("#kodeKegNp").val($('#kodeKegpop').val());
        gridnp();

    } else if (jenistrans == "NM") {
        //console.log("masuk NM");
        $('#idKegiatanNm').val($('#idKegpop').val());
        $("#keteranganKegPopNm").val($('#ketKegpop').val());
        $("#kodeKegNm").val($('#kodeKegpop').val());
        getNipPPTK();
        gridnm();

    } else if (jenistrans.substr(0, 1) == "P") {
        //console.log("masuk NM");
        $('#idKegiatanPajak').val($('#idKegpop').val());
        $("#keteranganKegPopPj").val($('#ketKegpop').val());
        $("#kodeKegPajak").val($('#kodeKegpop').val());
        $('#btnTambah').prop("disabled", false);
        clearrow();

    } else {
        $('#idkeg' + id).val($('#idKegpop').val());
        $('#kegiatan' + id).val($('#kodeKegpop').val());
        $('#namakeg' + id).val($('#namaKegpop').val());
        
        if (jenistrans == "ST" && $("#beban").val() == "TU") {
            console.log("set idspd kegiatan ST TU");
            $('#idspd' + id).val($('#idspdKegpop').val());
            setAkunCombo($('#idKegpop').val(), $('#idspdKegpop').val());
        } else {
            setAkunCombo($('#idKegpop').val(),0);
        }
        
    }
}

function setAkunCombo(id, idspd) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenistrans = $('#jenisTransaksi').val();
    var pajak;
    var beban = $('#beban').val();
    
    if (jenistrans.substr(0, 1) == "P") {
        id = tahun = $('#idKegiatanPajak').val();
        idcombo = 1;
        pajak = "PAJAK";

    } else {
        pajak = "BUKANPAJAK";
    }

    $.getJSON(getbasepath() + "/bku2016/json/getAkunCombo", {tahun: tahun, idskpd: idskpd, idkegiatan: id, keterangan: ketcombo, pajak: pajak, beban:beban, idspd:idspd},
    function(result) {
        var banyak, kode, ket, baris;
        var opt = "";
        banyak = result.aData.length;

        if (jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {
            opt = '<option value="" selected></option>';
        }

        try {
            //console.log("idcombo ============== " + idcombo);

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
                    akunkode[kode] = result.aData[i]['ketakun'];
                    //console.log ("akunnama[kode] ============== "+akunnama[kode]);
                    //console.log ("akunkode[kode] ============== "+akunkode[kode]);
                }
                //console.log("akun baris ============== " + baris);

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
    $("#valkodeakun" + id).val(akunkode[idbas]);
}

function setBeban() {

    var opt = "";
    var bulan = $('#bulan').val();
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi.substr(0, 1) !== "P") {
        clearrow();
    }

    getBebanSetorUP();

    $('#beban').prop("disabled", false); // untuk nanti SPJ read only

    $('#jourtable2').show();

    document.getElementById("beban").style.visibility = "visible";
    document.getElementById("labelbeban").style.visibility = "visible";
    document.getElementById("labelbeban").style.display = "block";

    document.getElementById("labeljenis").style.display = "none";
    document.getElementById("labelpengeluaran").style.display = "none";
    document.getElementById("labelnippptk").style.display = "none";
    document.getElementById("labelnamapptk").style.display = "none";
    document.getElementById("labelsisapajak").style.display = "none";
    document.getElementById("labelkegiatanspj").style.display = "none";
    document.getElementById("labelkegiatannm").style.display = "none";
    document.getElementById("labelkegiatannp").style.display = "none";
    document.getElementById("labelakunlainlain").style.display = "none";
    document.getElementById("labelkegiatanpajak").style.display = "none";
    //console.log("setBeban - banyakSetorUp == "+banyakSetorUp);
    $("#idKegiatanSpj").val("");

    if (jenistransaksi == "NP") {   // NPD ke PPTK
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';

        document.getElementById("labelkegiatanspj").style.display = "none";
        //document.getElementById("labeluraian").style.display = "none";

        document.getElementById("nipPptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.display = "block";

        document.getElementById("namaPptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.display = "block";

        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "block";

        //setColumn(2);

    } else if (jenistransaksi == "NM") {   // PPTK ke NPD 
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';

        document.getElementById("labelkegiatanspj").style.display = "none";
        //document.getElementById("labeluraian").style.display = "none";

        document.getElementById("nipPptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.display = "block";

        document.getElementById("namaPptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.display = "block";

        document.getElementById("labelkegiatannm").style.display = "block";
        document.getElementById("labelkegiatannp").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "block";

        //setColumn(2);


    } else if (jenistransaksi == "P1" || jenistransaksi == "P2" || jenistransaksi == "P3" || jenistransaksi == "P4" || jenistransaksi == "P5" || jenistransaksi == "P6") {   //  Pajak 

        if ($('#jenis').val == 1) {
            opt = '<option value="LS" >LS</option>';

        } else {

            opt = '<option value="UP" >UP/GU</option>';
            opt += '<option value="TU" >TU</option>';
            opt += '<option value="LS" >LS</option>';
        }
        getSisaPajak();

        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";
        document.getElementById("labelkegiatanpajak").style.display = "block";
        document.getElementById("labeljenis").style.display = "block";
        setColumn(2);

    } else if (jenistransaksi == "JJ") {
        $('#beban').prop("disabled", true);
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        document.getElementById("keteranganKegPop").style.visibility = "visible";
        document.getElementById("labelkegiatanspj").style.visibility = "visible";

        document.getElementById("labelkegiatanspj").style.display = "block";
        ketcombo = "SPJ";

        document.getElementById("labelnippptk").style.display = "block";
        document.getElementById("labelnamapptk").style.display = "block";
        //  document.getElementById("labeluraian").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

    } else if (jenistransaksi == "LL") { // LAIN - LAIN
        /*
         if ($('#idskpd').val() == 761) {
         opt = '<option value="LS" >LS</option>';
         } else {
         opt = '<option value="UP" >UP/GU</option>';
         opt += '<option value="TU" >TU</option>';
         opt += '<option value="LS" >LS</option>';
         }*/

        document.getElementById("labelbeban").style.display = "none";
        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labelakunlainlain").style.display = "block";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById("labelakunlainlain").style.display = "block";

        //document.getElementById("labeljenis").style.display = "block";

        document.getElementById("nipPptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.display = "block";

        document.getElementById("namaPptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.display = "block";

        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

        setColumn(2);

    } else if (jenistransaksi == "KM") { // Kas Masuk PPKD

        document.getElementById("labelbeban").style.display = "none";
        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labelakunlainlain").style.display = "none";
        document.getElementById("labelpengeluaran").style.display = "block";

        //document.getElementById("labeljenis").style.display = "block";

        document.getElementById("nipPptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.visibility = "visible";
        document.getElementById("labelnippptk").style.display = "block";

        document.getElementById("namaPptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.visibility = "visible";
        document.getElementById("labelnamapptk").style.display = "block";

        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

        setColumn(2);

    } else if (jenistransaksi == "ST") {   // SETORAN 
        if ($('#idskpd').val() == 761) {
            opt = '<option value="LS" >LS</option>';
        } else {
            //console.log("banyakSetorUp = " + banyakSetorUp)
            if ((bulan == "01" || bulan == "12" || bulan == "02" || bulan == "03" || bulan == "04" || bulan == "05") && banyakSetorUp <= 5) { // jika belum ada setoran UP/GU; persemester
                opt = '<option value="UP" >UP/GU</option>';
                opt += '<option value="TU" >TU</option>';
                opt += '<option value="LS" >LS</option>';
            } else {
                opt = '<option value="TU" >TU</option>';
                opt += '<option value="LS" >LS</option>';
            }
        }

        document.getElementById("labelkegiatanspj").style.display = "none";
        //  document.getElementById("labeluraian").style.display = "none";

        document.getElementById("jenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.display = "block";

        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

        setColumn($("#jenis").val());

    } else if (jenistransaksi == "C1") {
        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labeljenis").style.display = "none";
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        document.getElementById("labelbeban").style.display = "none";
        //document.getElementById("labeluraian").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

    } else if (jenistransaksi == "C2") {
        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labeljenis").style.display = "none";
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        document.getElementById("labelbeban").style.display = "none";
        // document.getElementById("labeluraian").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

    } else if (jenistransaksi == "SB") {   // SETORAN BENDAHARA
        opt = '<option value="LS" >LS</option>';

        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("jenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.visibility = "visible";
        document.getElementById("labeljenis").style.display = "block";

        document.getElementById("labelnippptk").style.display = "block";
        document.getElementById("labelnamapptk").style.display = "block";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

        setColumn($("#jenis").val());

    } else {
        document.getElementById("labelkegiatanspj").style.display = "none";
        document.getElementById("labelbeban").style.display = "none";
        document.getElementById("labelnippptk").style.display = "none";
        document.getElementById("labelnamapptk").style.display = "none";
        // document.getElementById("labeluraian").style.display = "none";
        document.getElementById("labelkegiatannm").style.display = "none";
        document.getElementById("labelkegiatannp").style.display = "none";

        //document.getElementById("labelbeban").style.visibility = "hidden";
        //document.getElementById("beban").style.visibility = "hidden";
    }

    $("#beban").html(opt);
    setPengeluaran();
    //setUraian();
    setSimpan();
}

function setJenis() {
    var jenistransaksi = $('#jenisTransaksi').val();
//jenistransaksi.substr(0, 1) == 'P'
    if (jenistransaksi == "SB") {
        setJenisPpkd();
        document.getElementById('textNipPptk').innerHTML = 'NIP PPTK / Bendahara :';
        document.getElementById('textNamaPptk').innerHTML = 'Nama PPTK / Bendahara :';
        $("#nipPptk").val("");
        $("#namaPptk").val("");

    } else if (jenistransaksi == "LL" || jenistransaksi == "KM") {
        setJenisPpkd();
        document.getElementById('textNipPptk').innerHTML = 'NIP Bendahara :';
        document.getElementById('textNamaPptk').innerHTML = 'Nama Bendahara :';

    } else if (jenistransaksi.substr(0, 1) == 'P') {
        setJenisPajak();

    } else {
        getSkpdBtl();
        document.getElementById('textNipPptk').innerHTML = 'NIP PPTK :';
        document.getElementById('textNamaPptk').innerHTML = 'Nama PPTK :';
        $("#nipPptk").val("");
        $("#namaPptk").val("");

    }

}

function setSimpan() {
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi == "JO") {
        document.getElementById("btnSimpan").style.visibility = "hidden"; // karena jenistransaksi selected = SP2D; jenis SP2D tidak 
    } else {
        document.getElementById("btnSimpan").style.visibility = "visible";
    }
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

function setJenisPajak() {

    var opt = "";

    if ($('#idskpd').val() == 396 || $('#idskpd').val() == 381) {
        opt = '<option value="1" >BTL</option>';
        opt += '<option value="3" selected >BL</option>';

    } else {
        opt += '<option value="3" >BL</option>';
    }

    $("#jenis").html(opt);

}

function setPengeluaran() {
    var jenistrans = $('#jenisTransaksi').val();
    var beban = $("#beban").val();

    if (jenistrans !== "JJ" && jenistrans !== "NM" && jenistrans !== "NP" && jenistrans.substr(0, 1) !== "P") {
        clearrow();
    }

    if (jenistrans == "JJ") { // ditambah 25 Jan 2016 by zainab.. tambah validasi2 untuk SPj berdasarkan hasil diskusi pelampauan
        getNilaiSisaSpj();

    } /* else if (jenistrans == "SB") {
     document.getElementById("pengeluaran").style.visibility = "visible";
     document.getElementById("labelpengeluaran").style.visibility = "visible";
     document.getElementById("labelpengeluaran").style.display = "block";
     document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Penerimaan :';
     
     //$("#jenis").val(3);
     $('#jenis').attr("disabled", false);
     $('#jourtable2').hide();
     
     } */ else if ((jenistrans == "ST" && beban == "UP")) {
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Pengeluaran :';

        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').hide();

    } else if (jenistrans == "LL") {
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai :';

        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').hide();

    } else if (jenistrans == "KM") {
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Penerimaan :';

        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').hide();

    } else if (jenistrans == "C1") {
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Tunai yang Diterima';
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";

        $('#jourtable2').hide();

    } else if (jenistrans == "C2") {
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
        document.getElementById("pengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.visibility = "visible";
        document.getElementById("labelpengeluaran").style.display = "block";
        document.getElementById('labelpengeluarantext').innerHTML = 'Nilai Tunai yang Disetor';

        $('#jourtable2').hide();

    } else if (jenistrans == "ST" && beban == "TU") {
        document.getElementById("pengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.display = "none";

        $("#jenis").val(3);
        $('#jenis').attr("disabled", true);
        $('#jourtable2').show();
        setColumn(3);

    } else if (jenistrans.substr(0, 1) == "P") {
        document.getElementById("pengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.display = "none";

        //$("#jenis").val(3);
        //$('#jenis').attr("disabled", true);
        $('#jourtable2').show();
        // setColumn(3);

    } else {
        //console.log("jenis = " + $("#jenis").val());
        document.getElementById("pengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.visibility = "hidden";
        document.getElementById("labelpengeluaran").style.display = "none";

        $("#jenis").val(1);
        $('#jenis').attr("disabled", false);
        $('#jourtable2').show();
        setColumn($("#jenis").val());
    }

    if (jenistrans == "ST" && beban == "UP") {
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
    } else if (jenistrans == "SB") {
        document.getElementById("btnTambah").style.visibility = "visible";
        document.getElementById("btnHapus").style.visibility = "visible";
    } else if ((jenistrans == "ST" && beban == "LS") || (jenistrans == "ST" && beban == "TU")) {
        document.getElementById("btnTambah").style.visibility = "visible";
        document.getElementById("btnHapus").style.visibility = "visible";
    } else if (jenistrans == "LL" || jenistrans == "KM") {
        document.getElementById("btnTambah").style.visibility = "hidden";
        document.getElementById("btnHapus").style.visibility = "hidden";
    }

    setUraian();
}


function setUraian() {
    var jenistrans = $('#jenisTransaksi').val();
    var beban = $("#beban").val();

    document.getElementById("labeluraian").style.display = "none";
    document.getElementById("labeluraianterima").style.display = "none";
    document.getElementById("labeluraiankeluar").style.display = "none";


    if (jenistrans == "ST") {
        document.getElementById("labeluraian").style.display = "block";
        $("#uraian").val("Disetor sisa belanja ");

    } else if (jenistrans == "SB") {
        document.getElementById("labeluraian").style.display = "block";
        $("#uraian").val("");

    } else if (jenistrans == "LL" || jenistrans == "KM") {
        document.getElementById("labeluraian").style.display = "block";
        $("#uraian").val("");

    } else if (jenistrans == "NP") {
        document.getElementById("labeluraianterima").style.display = "block";
        document.getElementById("labeluraiankeluar").style.display = "block";
        $("#uraianterima").val("Diterima uang panjar ");
        $("#uraiankeluar").val("Dibayar dari kas tunai ");

    } else if (jenistrans == "NM") {
        document.getElementById("labeluraianterima").style.display = "block";
        document.getElementById("labeluraiankeluar").style.display = "block";
        $("#uraianterima").val("Diterima pada kas tunai ");
        $("#uraiankeluar").val("Dibayar dari uang panjar ");

    } else if (jenistrans == "C1") {
        document.getElementById("labeluraianterima").style.display = "block";
        document.getElementById("labeluraiankeluar").style.display = "block";
        $("#uraianterima").val("Diterima pada kas tunai ");
        $("#uraiankeluar").val("Pencairan bank ");

    } else if (jenistrans == "C2") {
        document.getElementById("labeluraianterima").style.display = "block";
        document.getElementById("labeluraiankeluar").style.display = "block";
        $("#uraianterima").val("Diterima pada bank ");
        $("#uraiankeluar").val("Setoran dari kas tunai ");

    } else if (jenistrans == "JJ" || jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {
        document.getElementById("labeluraian").style.display = "block";
        $("#uraian").val("");

    }

    setCaraBayar();

}

function setCaraBayar() {
    var jenistrans = $('#jenisTransaksi').val();
    var beban = $("#beban").val();

    document.getElementById("labelcarabayar").style.display = "none";
    document.getElementById("labeljenisbayar").style.display = "none";
    document.getElementById("labelsisaspj").style.display = "none";
    document.getElementById("labelnilaispd").style.display = "none";
    document.getElementById("labelnilaikontrak").style.display = "none";
    document.getElementById("labelnilaibku").style.display = "none";
    document.getElementById("labelnilaispptu").style.display = "none";
    document.getElementById("labelnilaisetorantu").style.display = "none";
    document.getElementById("labelsisaspd").style.display = "none";


    var opt = '<option value="1" >1 - Tunai</option>';
    opt += '<option value="2" >2 - Bank/Transfer/Cek</option>';

    if (jenistrans == "JJ") {

        document.getElementById("labelnilaispd").style.display = "block";
        document.getElementById("labelnilaikontrak").style.display = "block";
        document.getElementById("labelnilaibku").style.display = "block";
        document.getElementById("labelsisaspd").style.display = "block";
        document.getElementById("labelnilaispptu").style.display = "block";
        document.getElementById("labelnilaisetorantu").style.display = "block";
        document.getElementById("labelcarabayar").style.display = "block";
        document.getElementById("labelsisaspj").style.display = "block";
        opt += '<option value="3">3 - Panjar</option>';
        $("#kodePembayaran").val(2);

    }
    if (jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {
        document.getElementById("labelcarabayar").style.display = "block";
        document.getElementById("labeljenisbayar").style.display = "block";
        $("#kodePembayaran").val(2);

    }
    if (jenistrans == "ST" || jenistrans == "SB") {
        document.getElementById("labelcarabayar").style.display = "block";
        $("#kodePembayaran").val(2);

    }
    if (jenistrans == "LL") {
        document.getElementById("labelcarabayar").style.display = "block";
        document.getElementById("labeljenisbayar").style.display = "block";
        $("#kodePembayaran").val(2);

    }
    if (jenistrans == "KM") {
        document.getElementById("labelcarabayar").style.display = "block";
        opt = '<option value="2" >2 - Bank/Transfer/Cek</option>';

    }

    if (jenistrans == "NP" || jenistrans == "NM") {
        document.getElementById("labelcarabayar").style.display = "block";
        $("#kodePembayaran").val(1);

    }

    $("#kodePembayaran").html(opt);

    if (jenistrans == "NP" || jenistrans == "NM") {
        $("#kodePembayaran").val(1);
    } else {
        $("#kodePembayaran").val(2);
    }

}

function cekPanjar(bayar) {
    var jenistrans = $('#jenisTransaksi').val();
    var idkeg = $('#idKegiatanSpj').val();
    if ((jenistrans == "JJ")) {
        if (bayar == "3" && idkeg !== "") {
            getNamaNipSpjPanjar();
        } else {
            $("#nipPptk").val("");
            $("#namaPptk").val("");
        }
    }

    if ((jenistrans == "NP")) {
        if (bayar == "1") {
            $("#uraiankeluar").val("Dibayar dari kas tunai ");
        } else if (bayar == "2") {
            $("#uraiankeluar").val("Dibayar dari bank ");

        }

    }

    if ((jenistrans == "NM")) {
        if (bayar == "1") {
            $("#uraianterima").val("Diterima pada kas tunai ");
        } else if (bayar == "2") {
            $("#uraianterima").val("Diterima pada bank ");
        }
    }
}


function setColumn(jenis) {
    var dgTest = document.getElementById("jourtable2");
    var jenistrans = $('#jenisTransaksi').val();

    if (jenis == 1 || jenis == 4) {
        dgTest.rows[0].cells[2].style.display = "none";
        dgTest.rows[0].cells[3].style.display = "none";


    } else {
        dgTest.rows[0].cells[2].style.display = "table-cell";
        dgTest.rows[0].cells[3].style.display = "table-cell";
        //clearrow();
    }


    if (jenistrans == "P1" || jenistrans == "P2" || jenistrans == "P3" || jenistrans == "P4" || jenistrans == "P5" || jenistrans == "P6") {
        dgTest.rows[0].cells[2].style.display = "none";
        dgTest.rows[0].cells[3].style.display = "none";
        setakunpajak();
        //clearrow();
    }

    if (jenistrans.substr(0, 1) != "P") {
        // console.log("setColumn, masuk untuk clear row = ");
        clearrow();
    }
}

function setakunpajak() {
    //console.log("setakunpajak() jenis = " + $('#jenis').val());
    var opt = "";

    if ($('#jenis').val() == 1) {
        document.getElementById("labelkegiatanpajak").style.display = "none";
        clearrow();
        $('#btnTambah').prop("disabled", false);
        $('#keteranganKegPopPj').val("");
        opt = '<option value="LS" >LS</option>';

    } else if ($('#jenis').val() == 3) {
        document.getElementById("labelkegiatanpajak").style.display = "block";
        clearrow();
        $('#btnTambah').prop("disabled", true);
        opt = '<option value="UP" >UP/GU</option>';
        opt += '<option value="TU" >TU</option>';
        opt += '<option value="LS" >LS</option>';
    }

    $("#beban").html(opt);
}

function setGrid() {
    var jenistransaksi = $('#jenisTransaksi').val();

    if (jenistransaksi == "JO") {
        $('#tabelSP2D').show();
        $('#tabelDLL').hide();
        $('#tabelSPJ').hide();
        $('#tabelNM').hide();
        $('#tabelNP').hide();

        //gridbku();

    } else if (jenistransaksi == "JJ") {
        $('#tabelSPJ').show();
        $('#tabelDLL').hide();
        $('#tabelSP2D').hide();
        $('#tabelNM').hide();
        $('#tabelNP').hide();

        gridspj();


    } else if (jenistransaksi == "NM") {
        $('#tabelNM').show();
        $('#tabelSPJ').hide();
        $('#tabelDLL').hide();
        $('#tabelSP2D').hide();
        $('#tabelNP').hide();
        gridnm();


    } else if (jenistransaksi == "NP") {
        $('#tabelNP').show();
        $('#tabelSPJ').hide();
        $('#tabelDLL').hide();
        $('#tabelSP2D').hide();
        $('#tabelNM').hide();
        gridnp();


    } else if (jenistransaksi == "LL" || jenistransaksi == "C1" || jenistransaksi == "C2" || jenistransaksi == "KM") {
        $('#tabelNP').hide();
        $('#tabelSPJ').hide();
        $('#tabelDLL').hide();
        $('#tabelSP2D').hide();
        $('#tabelNM').hide();

    } else {
        $('#tabelDLL').show();
        $('#tabelSP2D').hide();
        $('#tabelSPJ').hide();
        $('#tabelNM').hide();
        $('#tabelNP').hide();
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

    $.getJSON(getbasepath() + "/bku2016/json/getSkpdBtl", {tahun: tahun, idskpd: idskpd},
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
    $.getJSON(getbasepath() + "/bku2016/json/getSkpdBl", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak;
        banyak = result.aData.length;
        var opt = $("#jenis").html();

        if (banyak == 0) {

        } else {
            if ($('#idskpd').val() !== 761) {
                opt += '<option value="3" selected >BL</option>';
            }
        }

        $("#jenis").html(opt);

        if ($('#jenisTransaksi').val() == "ST") {
            if ($('#beban').val() == "TU" || $('#beban').val() == "UP") {

            }
            $("#jenis").val(3);
        }
    });
}

function setCurrentMonth() {
    //  $("#tglPosting").val("");
    // $('#tglPosting').datepicker('destroy');
    var bulan = parseInt($("#bulan").val());

    var currentTime = new Date();
    var startDateFrom = new Date(currentTime.getFullYear(), bulan - 1, 1);
    var startDateTo = new Date(currentTime.getFullYear(), bulan, 0);

    //console.log("start Date From = " + startDateFrom);
    //console.log("start Date To === " + startDateTo);

    $("#tglPosting").datepicker("refresh");

    $("#tglPosting").datepicker({
        //dateFormat: 'dd.mm.yy',
        //showClear: true,     
        //changeMonth: true,
        clearDates: true,
        startDate: startDateFrom,
        endDate: startDateTo,
        autoclose: true,
        // inline: true,
        onSelect: function() {
            $('#tglPosting').datepicker('option', 'startDate', startDateFrom);
            $('#tglPosting').datepicker('option', 'endDate', startDateTo);
            $('#tglPosting').datepicker('show');
        }
    });

}

function getNipPPTK() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $('#idKegiatanNm').val();

    $.getJSON(getbasepath() + "/bku2016/json/getNipPPTK", {tahun: tahun, idskpd: idskpd, idkegiatan: idkegiatan},
    function(result) {
        var banyak;

        $("#nipPptk").val(result.aData[0]['nipPptk']);
        $("#namaPptk").val(result.aData[0]['namaPptk']);
        $("#beban").val(result.aData[0]['beban']);

    });

}

function gridnm() {
    jumbarisnm = 0;

    if (typeof myTablenm == 'undefined') {
        myTablenm = $('#nmtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku2016/json/listbkunm",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatanNm").val()},
                {name: "nobku", value: "0"}
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

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 1 + "' />";
                var nilainpd = "<input type='text' name='nilainpd" + index + "' id='nilainpd" + index + "'  class='inputmoney'  value='" + aData['nilaiNpd'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") checked />";
                jumbarisnm = jumbarisnm + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(nilainpd);
                $('td:eq(2)', nRow).html(nilaisebelum);
                $('td:eq(3)', nRow).html(nilaisisa);
                $('td:eq(4)', nRow).html(nilaiinput);
                $('td:eq(5)', nRow).html(inputcek + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTablenm.fnClearTable(0);
        myTablenm.fnDraw();
    }
}

function gridnp() {
    jumbarisnp = 0;

    if (typeof myTablenp == 'undefined') {
        myTablenp = $('#nptable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bku2016/json/listbkunp",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "idkegiatan", value: $("#idKegiatanNp").val()},
                {name: "nobku", value: "0"}
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

                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + 1 + "' />";
                var nilainpd = "<input type='text' name='nilainpd" + index + "' id='nilainpd" + index + "'  class='inputmoney'  value='" + aData['nilaiNpd'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['nilaiSisa'] + "' readOnly='true' />";
                var nilaiinput = "<input type='text' name='nilaiinput" + index + "' id='nilaiinput" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuInput'] + "' onkeyup='pasangvalidatebesarperfield(" + index + ")' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") checked />";
                var nilaianggaran = "<input type='text' name='nilaiangg" + index + "' id='nilaiangg" + index + "'  class='inputmoney'  value='" + aData['nilaiAnggaran'] + "' readOnly='true' />";
                var nilaisp2d = "<input type='text' name='nilaisp2d" + index + "' id='nilaisp2d" + index + "'  class='inputmoney'  value='" + aData['nilaiBkuSp2d'] + "' readOnly='true' />";
                var nilaispj = "<input type='text' name='nilaispj" + index + "' id='nilaispj" + index + "'  class='inputmoney'  value='" + aData['nilaiSpj'] + "' readOnly='true' />";
                jumbarisnp = jumbarisnp + 1;

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(nilaianggaran);
                $('td:eq(2)', nRow).html(nilaisp2d);
                $('td:eq(3)', nRow).html(nilaisebelum);
                $('td:eq(4)', nRow).html(nilainpd);
                $('td:eq(5)', nRow).html(nilaispj);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(nilaiinput);
                $('td:eq(8)', nRow).html(inputcek + penanda);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSp2d", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuSebelum", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiNpd", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSpj", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiSisa", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiBkuInput", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTablenp.fnClearTable(0);
        myTablenp.fnDraw();
    }
}


function deleteendrow() {
    var table = document.getElementById('jourtablebody2');

    console.log(" idrow = " + idrow);

    if (idrow > 0) {
        table.deleteRow(idrow - 1);
        idrow = idrow - 1;
    }
}


function setformatpengeluaran(nilai) {
    //var pengeluaran = $('#nilaiinput').val();
    var nilaiunformat = accounting.unformat(nilai, ",");
    $('#pengeluaran').val(accounting.formatNumber(nilaiunformat, 2, '.', ","));
}

function getSuadana() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/bku2016/json/getStatusSuadana", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var suadana = result.aData['statusSuadana'];
        //console.log("suadana == " + suadana);

        setJenisBKU(suadana);
    });

}

function hitungnilaispj() {
    var total = 0;

    var searchIDs = $("#spjtable input:checkbox:checked").map(function() {
        return $(this).val();
    }).get();

    //console.log("searchIDs = "+ searchIDs);
    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaiinput" + data).val(), ","));
    })
    $("#sumspj").val(accounting.formatNumber(total, 2, '.', ","));
    $("#totalspjhidden").val(total);
}

function getNilaiSisaSpj() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var beban = $('#beban').val();
    var idkegiatan = $("#idKegiatanSpj").val();

    $.getJSON(getbasepath() + "/bku2016/json/getNilaiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: "0", beban: beban, idkegiatan: idkegiatan},
    function(result) {
        var banyak;

        var nilai = result.aData['nilaiSisa'];
        $('#sisaspj').val(accounting.formatNumber(nilai, 2, '.', ","));
        //console.log("nilai sisa spj = "+result.aData['nilaiSisa']);
    });

}

function getNilaiValidasiSisaSpj() { // dibuat 22 jan 16 by zainab, untuk memvalidasi total bku 

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $("#idKegiatanSpj").val();
    var beban = $('#beban').val();

    //console.log("idkegiatan = " + idkegiatan);
    if (idkegiatan !== "") {
        $.getJSON(getbasepath() + "/bku2016/json/getNilaiValidasiSisaSpj", {tahun: tahun, idskpd: idskpd, nobku: "0", idkegiatan: idkegiatan, beban: beban},
        function(result) {

            var nilaispd = result.aData['nilaiSpd'];
            var nilaikontrak = result.aData['nilaiKontrak'];
            var nilaibku = result.aData['nilaiBku'];
            var nilaisisa = result.aData['nilaiSisa'];
            var nilaitu = result.aData['nilaiTU'];
            var setortu = result.aData['nilaiSetoranTU'];

            $('#nilaispd').val(accounting.formatNumber(nilaispd, 2, '.', ","));
            $('#nilaikontrak').val(accounting.formatNumber(nilaikontrak, 2, '.', ","));
            $('#nilaibku').val(accounting.formatNumber(nilaibku, 2, '.', ","));
            $('#sisaspd').val(accounting.formatNumber(nilaisisa, 2, '.', ","));
            $('#nilaispptu').val(accounting.formatNumber(nilaitu, 2, '.', ","));
            $('#nilaisetorantu').val(accounting.formatNumber(setortu, 2, '.', ","));

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
                ketSaldoAwal = "SALDO AWAL";
                //getKodeTutup();
                getBulanByRekap();


            } else {
                //getKodeTutup();
                getBulanByRekap();
                ketSaldoAwal = "BUKAN SALDO AWAL";
            }
        }
    });
}


function getNamaNipSpjPanjar() {

    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $('#idKegiatanSpj').val();
    var kodePembayaran = $('#kodePembayaran').val();
    $("#nipPptk").val("");
    $("#namaPptk").val("");

    if (kodePembayaran == 3 && idkegiatan !== "") {
        $.getJSON(getbasepath() + "/bku2016/json/getNamaNipSpjPanjar", {tahun: tahun, idskpd: idskpd, idkegiatan: idkegiatan},
        function(result) {
            var banyak = result.aData.length; // untuk ngambil length nya, type keluarannya harus list
            var nama, nip;
            var opt;

            if (banyak > 0) {
                nama = result.aData[0]['namaPptk'];
                nip = result.aData[0]['nipPptk'];

                $("#nipPptk").val(nip);
                $("#namaPptk").val(nama);
            }
        });
    }
}

function getBebanSetorUP() {
    var bulan = parseInt($("#bulan").val());
    var semester;
    //console.log("parseInt bulan = " + bulan);
    if (bulan < 7) {
        semester = "AWAL";
    } else {
        semester = "AKHIR";
    }

    $.getJSON(getbasepath() + "/bku2016/json/getSetorUPSemester", {idskpd: $("#idskpd").val(), semester: semester},
    function(result) {

        var banyak = result.aData;
        banyakSetorUp = banyak;
    });
}

function getBendahara() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenistrans = $('#jenisTransaksi').val();

    if (jenistrans == "LL") {
        $.getJSON(getbasepath() + "/bku2016/json/getBendahara", {tahun: tahun, idskpd: idskpd},
        function(result) {
            $("#nipPptk").val(result.aData[0]['nipPptk']);
            $("#namaPptk").val(result.aData[0]['namaPptk']);
        });
    }
}

function getWilayah() {

    $.getJSON(getbasepath() + "/bku2016/json/getWilayahByLogin", {},
            function(result) {
                var kode = result.aData[0]['kodeWilayah'];
                var ket = result.aData[0]['ketWilayah'];
                $("#wilayah").val("  " + ket);

            });
}


function cekcek() {
    console.log("idrow == " + idrow);
    var totalpajak = 0;

    for (var i = 1; i <= idrow; i++) {
        totalpajak += accounting.unformat($("#nilaiKeluar" + i).val(), ",");
    }

    console.log("totalpajak == " + totalpajak);
}

function getBanyakListSPJ() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkeg = $("#idKegiatanSpj").val();
    var nobku = "-1";
    var beban = $("#beban").val();
    var nobukti = "#########";

    if (nobukti == "") {
        nobukti = " ";
    }

    $.getJSON(getbasepath() + "/bku2016/json/getBanyakListSPJ", {tahun: tahun, idskpd: idskpd, idkegiatan: idkeg, nobku: nobku, beban: beban, nobukti: nobukti},
    function(result) {

        jumbarisspj = result;

    });
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

function getSaldoAwalPajak() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenis = $('#jenisTransaksi').val();

    $.getJSON(getbasepath() + "/bku2016/json/getSaldoAwalPajak", {tahun: tahun, idskpd: idskpd, jenis: jenis},
    function(result) {

        saldoawalpajak = result.aData['saldoAwalPajak'];

        if (saldoawalpajak == 0) {
            getSisaPajak();
            document.getElementById("labelsisapajak").style.display = "block";
        }
    });
}

function getSisaPajak() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var jenis = $('#jenisTransaksi').val();

    $.getJSON(getbasepath() + "/bku2016/json/getSisaPajak", {tahun: tahun, idskpd: idskpd, jenis: jenis, keterangan: "ADD"},
    function(result) {
        document.getElementById("labelsisapajak").style.display = "block";
        sisapajak = result.aData['sisaPajak'];
        $('#sisapajak').val(accounting.formatNumber(result.aData['sisaPajak'], 2, '.', ","));

    });
}

