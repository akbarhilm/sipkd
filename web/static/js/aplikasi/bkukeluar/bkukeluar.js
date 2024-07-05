/*
 * Last Edited By Mustakim
 * Date 17 Feb 2016
 * Tambah validasi tutup ke data bku yg sudah dijurnal
 */

$(document).ready(function() {
    setpanel(1);
});

function setpanel(jenis) {
    
    var idskpd = $('#idskpd').val();
    $('#bulan').val(""); //value bulan disetting "" (kosong)
    if (jenis == 1) { // BKU Pengeluaran
        console.log("set panel jenis = " + jenis);
        //$('#btnproses').attr("disabled", false);
        document.getElementById("panelTombol").style.display = "block"; //menampilkan panel tombol
        document.getElementById("labelkegiatan").style.display = "none";
        $('#idKegiatan').val(0);
        $('#namaKeg').val("");
        setComboBulan2();

    } else if (jenis == 2) { // BKU Per Kegiatan
        //$('#btnproses').attr("disabled", true);
        document.getElementById("panelTombol").style.display = "none"; //menghilangkan panel tombol
        document.getElementById("labelkegiatan").style.display = "block";
        setComboBulan2(); // pemanggilan combo bulan disamakan dengan BKU Pengeluaran
    }
    cekProsesTutup(); // edit 14 Jan 2015
    //setTanggalAkhir(); // edit 14 Jan 2015

    if (idskpd == "761") {
        $('#jenislaporan').attr("disabled", true);
    } else {
        $('#jenislaporan').attr("disabled", false);
    }
}

function setTanggalAkhir() {

    var tgl = $("#bulan").find(":selected").text();
    //console.log("combo bulan isinya == " + tgl);
    var index = tgl.indexOf("BELUM DIPROSES");
    var index2 = tgl.indexOf("DRAFT DIPROSES");
    //var belum = tgl.substring(5);
    var dd, mm, yy, tanggal;

    dd = tgl.substr(5, 2);
    mm = tgl.substr(8, 2);
    yy = tgl.substr(11, 4);

    tanggal = yy + mm + dd;

    $('#tgl2').val(tanggal);

    if (refcetak.bulan.selectedIndex == 0 || tgl == null || tgl == "") { // untuk tampilan awal atau saat memilih "--Pilih Bulan--" semua tombol disable
        $('#btndraft').attr("disabled", true);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", true);
        $('#btncetakxls').attr("disabled", true);
    } else if (index != -1) { //jika belum diproses
        ///bootbox.alert("belum proses = "+tgl+belum);
        //$("#bulan").val(15); // tidak ada data
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", true);
        $('#btncetakxls').attr("disabled", true);

    } else if (index2 != -1) {
        $('#btndraft').attr("disabled", false);
        $('#btnproses').attr("disabled", false);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }
    else {
        //bootbox.alert("sudah proses = "+index+" = "+tgl+belum);
        $('#btndraft').attr("disabled", true);
        $('#btnproses').attr("disabled", true);
        $('#btncetak').attr("disabled", false);
        $('#btncetakxls').attr("disabled", false);
    }

    gridbkupengeluaran();
}
/*
 function setComboBulan() {
 var idskpd = $('#idskpd').val();
 var tahun = $('#tahun').val();
 
 $.getJSON(getbasepath() + "/laporanbkukeluar/json/setComboBulan", {idskpd: idskpd, tahun: tahun},
 function(result) {
 var banyak, kode, ket;
 var opt = "<option>--Pilih Bulan--</option>"; // untuk tampilan awal combo bulan
 
 banyak = result.aData.length;
 
 if (banyak > 0) {
 
 for (var i = 0; i < banyak; i++) {
 kode = result.aData[i]['kodeBulan'];
 ket = result.aData[i]['bulanPosting'];
 
 opt += '<option value="' + kode + '" >' + ket + '</option>';
 }
 }
 
 $("#bulan").html(opt);
 
 });
 
 }
 */
function setComboBulan2() {
    var idskpd = $('#idskpd').val();
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/laporanbkukeluar/json/setComboBulan2", {idskpd: idskpd, tahun: tahun},
    function(result) {
        var banyak, kode, ket;
        var opt = "<option>--Pilih Bulan--</option>"; // untuk tampilan awal combo bulan

        banyak = result.aData.length;

        if (banyak > 0) {
            if (result.aData[0]['kodeBulan'] > '01') {
                opt += '<option value="01" >Januari : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '02') {
                opt += '<option value="02" >Februari : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '03') {
                opt += '<option value="03" >Maret : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '04') {
                opt += '<option value="04" >April : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '05') {
                opt += '<option value="05" >Mei : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '06') {
                opt += '<option value="06" >Juni : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '07') {
                opt += '<option value="07" >Juli : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '08') {
                opt += '<option value="08" >Agustus : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '09') {
                opt += '<option value="09" >September : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '10') {
                opt += '<option value="10" >Oktober : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '11') {
                opt += '<option value="11" >November : BELUM DIPROSES</option>';
            }
            if (result.aData[0]['kodeBulan'] > '12') {
                opt += '<option value="12" >Desember : BELUM DIPROSES</option>';
            }

            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeBulan'];
                ket = result.aData[i]['bulanPosting'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
        } else {
            opt += '<option value="01" >Januari : BELUM DIPROSES</option>';
        }
        //console.log(opt);

        $("#bulan").html(opt);
    });
}

function cetakjurnalxls() {
    window.location.href = getbasepath() + "/laporanbkukeluar/xls/bkuxls?tahun=" + $("#tahun").val()
            + "&idskpd=" + $('#idskpd').val() + "&bulan="
            + $('#bulan').val();
}
/*
 function simpan() {
 var tahun = $("#tahun").val();
 var idskpd = $("#idskpd").val();
 var bulan = $("#bulan").val();
 
 var urljson = getbasepath() + "/laporanbkukeluar/json/prosessimpan";
 
 //  var varurl = getbasepath() + "/bukubesarskpd/json/prosessimpan";
 var dataac = [];
 
 var datajour = {
 tahun: tahun,
 idskpd: idskpd,
 bulan: bulan
 }
 dataac = datajour;
 
 return   $.ajax({
 type: "POST",
 url: urljson,
 contentType: "text/plain; charset=utf-8",
 headers: {
 'Accept': 'application/json',
 'Content-Type': 'application/json'
 },
 data: JSON.stringify(dataac)
 }).always(function(data) {
 bootbox.alert("Proses Buku Kas Umum Berhasil");
 setComboBulan();
 });
 }
 */
function gridbkupengeluaran() {
    var urljson = getbasepath() + "/laporanbkukeluar/json/gridbkupengeluaran";
    if (typeof myTable == 'undefined') {
        myTable = $('#bkutable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "bulan", value: $('#bulan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "jenislaporan", value: $('#jenislaporan').val()}
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
                var nilaimasuk = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaikeluar = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                var nilaikas = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(nilaikas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": true, sClass: "left"},
                {"mDataProp": "uraianBukti", "bSortable": true, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "kanan"}

            ]
        });

    }
    else {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


function cetakbkukeluar() {

    var aaa = $("#tahun").val();
    // var bbb = $("#kproses").val();
    var ccc = $("#tgl1").val();
    var ddd = $("#tgl2").val();
    var fff = $("#idKegiatan").val();
    var ggg = $("#jenislaporan").val();
    var hhh = $("#idskpd").val();
    var bulan = $("#bulan").val();
    var qqq = ccc.split("/").join("-");
    //console.log("tahun = "+aaa);  console.log("tgl awal = "+ccc);  console.log("tgl akhir = "+ddd);  console.log("id kegiatan = "+fff);  console.log("jenis = "+ggg); console.log("idskpd = "+hhh);
    var eee = aaa + "-" + "REALISASI_BKU_PERKEGIATAN" + ".pdf";

    window.location.href = getbasepath() + "/laporanbkukeluar/json/prosescetak?tahun=" + aaa + "&tgl1=" + ccc + "&bulan=" + bulan + "&tgl2=" + ddd + "&jenislaporan=" + ggg + "&idskpd=" + hhh + "&idkegiatan=" + fff + "&namafile=" + eee;

}

/*
 * UPDATE 13 Jan 2016 by Zainab
 * Fungsi methode : dissable button proses tutup buku jika bulan sebelum belum diproses
 */

function cekProsesTutup() {

    var index = refcetak.bulan.selectedIndex;
    if (index > 1) { // untuk item pertama ga usah di cek // Last Edited : untuk item index ke 2, karena ketambahan "--Pilih Bulan--" di index 1
        var indexSebelum = index - 1; // diinisialisasi setelah cek index paling awal agar sebelum <> -1

        var teksSebelum = document.getElementById("bulan").options.item(indexSebelum).text;
        var teksLenSebelum = String(teksSebelum).length;
        var keteranganSebelum = String(teksSebelum).substring(teksLenSebelum, teksLenSebelum - 14); //14 = length "BELUM DIPROSES"

        var teksSaatIni = document.getElementById("bulan").options.item(index).text;
        var teksLenSaatIni = String(teksSaatIni).length;
        var keteranganSaatIni = String(teksSaatIni).substring(teksLenSaatIni, teksLenSaatIni - 14);
        //console.log("teksSebelum Sebelum = " + teksSebelum);
        //console.log("teksLenSebelum Sebelum = " + teksLenSebelum);
        //console.log("keterangan Sebelum = " + keteranganSebelum);

        if (keteranganSebelum == "BELUM DIPROSES" || keteranganSebelum == "DRAFT DIPROSES") { // jika bulan sebelumnya belum diproses, maka bulan ini ga bisa proses // Last Edited : menambahkan kondisi keteranganSebelum == "DRAFT DIPROSES"
            /*
             * Last Edited : semua tombol disable bila bulan sebelumnya belum diproses
             */
            $('#btnproses').attr("disabled", true);
            $('#btndraft').attr("disabled", true);
            $('#btncetak').attr("disabled", true);
            $('#btncetakxls').attr("disabled", true);

        } else {
            if (keteranganSaatIni == "BELUM DIPROSES") {
                // bila bisa diproses dan keterangan bulannya "BELUM DIPROSES", maka hanya tombol draft yang aktif
                $('#btnproses').attr("disabled", true);
                $('#btndraft').attr("disabled", false);
                $('#btncetak').attr("disabled", true);
                $('#btncetakxls').attr("disabled", true);
            }
            else if (keteranganSaatIni == "DRAFT DIPROSES") {
                // bila bisa diproses dan keterangan bulannya "DRAFT DIPROSES", maka yg aktif semua tombol
                $('#btnproses').attr("disabled", false);
                $('#btndraft').attr("disabled", false);
                $('#btncetak').attr("disabled", false);
                $('#btncetakxls').attr("disabled", false);
            }
            else {
                // bila bisa diproses dan keterangan bulannya "DRAFT DIPROSES", maka yg aktif tombol draft dan proses tutup
                $('#btnproses').attr("disabled", true);
                $('#btndraft').attr("disabled", true);
                $('#btncetak').attr("disabled", false);
                $('#btncetakxls').attr("disabled", false);
            }
        }

        gridbkupengeluaran(); // refresh tampilan grid
    } else {
        //console.log("else");
        setTanggalAkhir(); // mengecek lagi aturan dasar nya
    }

}

/*
 * Fungsi untuk validasi tutup buku harus data bku sudah dijurnal semua, jika belum maka tidak bisa tutup buku
 */
function cekJurnal() {
    var bulan = $('#bulan').val();
    var idskpd = $('#idskpd').val();
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/laporanbkukeluar/json/getTanggalBelumJurnal", {idskpd: idskpd, tahun: tahun, bulan: bulan},
    function(result) {
        var ket = "";
        var banyak = result.aData.length;
        if (banyak > 0) { // jika datanya ada / ada yg belum dijurnal
            for (var i = 0; i < banyak; i++) {
                ket += (i + 1) + ". " + result.aData[i]['tglPosting'] + "<br/>";
            }
            bootbox.alert({//tampilkan peringatan tanggal berapa yg belum dijurnal
                title: "Masih Ada Data BKU yang Belum Dijurnal.",
                message: "Data yang belum dijurnal pada tanggal :<br/>" + ket + "<br/><br/>Silahkan lakukan jurnal BKU pada menu BKU-Proses Jurnal BKU.",
                callback: function() {
                    $.fancybox.close();
                }
            });
        }
    });
}
