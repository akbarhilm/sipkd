
$(document).ready(function() {
    getTglSp2dSah();
    $('#hide').hide();
});

var kodestan, bulkId, nomorref;
var kodeprosescek = new Array();
function popup() {
    var modal = document.getElementById('myModal');
    var img = document.getElementById('myImg');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");

    img.src = "/SP2D/static/assets/img/loading_blank.gif";

    modal.style.display = "block";
    img.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = "Silahkan Tunggu . . .";
}

//dari tombol UI
function updateCode() {
    $('#popup').val("0");
    cekstatus();
}
function cari(param) {
    //console.log("param cari = " + param)
    $('#' + param).keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridpajak();
        }
    });
}

function gridpajak() {
    var baseurl = getbasepath();
    var urljson = baseurl + "/sp2dpajaktransfer/json/getlistindex";
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
                        {name: "kodeskpdfilter", value: $('#kodeskpdfilter').val()},
                {name: "namaskpdfilter", value: $('#namaskpdfilter').val()},
                {name: "nosp2dfilter", value: $('#nosp2dfilter').val()},
                {name: "tglsah", value: $('#tglsah').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "kproses", value: $('#kproses').val()}
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "async": false,
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
                var indexset = aData['idSpmPot'];
                var jenis = "<input type='hidden' id='jenis" + indexset + "' value='" + aData['kodeJenis'] + "'/>";
                var idskpd = "<input type='hidden' id='idskpd" + indexset + "' value='" + aData['idSkpd'] + "'/>";
                var idspmpot = "<input type='hidden' id='idspmpot" + indexset + "' value='" + aData['idSpmPot'] + "'/>";
                var idsp2d = "<input type='hidden' id='idsp2d" + indexset + "' value='" + aData['idSp2d'] + "'/>";
                var npwpbud = "<input type='hidden' id='npwpbud" + indexset + "' value='" + aData['npwpBud'] + "'/>";
                var namabud = "<input type='hidden' id='namabud" + indexset + "' value='" + aData['namaBud'] + "'/>";
                var alamatbud = "<input type='hidden' id='alamatbud" + indexset + "' value='" + aData['alamatBud'] + "'/>";
                var kotabud = "<input type='hidden' id='kotabud" + indexset + "' value='" + aData['kotaBud'] + "'/>";
                var nosp2d = "<input type='hidden' id='nosp2d" + indexset + "' value='" + aData['noSp2d'] + "'/>";
                var tglsah = "<input type='hidden' id='tglsah" + indexset + "' value='" + aData['tglSp2dSah'] + "'/>";
                var kodetrx = "<input type='hidden' id='kodetrx" + indexset + "' value='" + aData['kodeTrx'] + "'/>";
                var kodepot = "<input type='hidden' id='kodepot" + indexset + "' value='" + aData['kodePotSpm'] + "'/>";
                var nilaipajak = "<input type='hidden' id='nilaipajak" + indexset + "' value='" + aData['nilaiPajak'] + "'/>";
                var uraianpajak = "<input type='hidden' id='uraianpajak" + indexset + "' value='" + aData['uraianPajak'] + "'/>";
                var nokontrak = "<input type='hidden' id='nokontrak" + indexset + "' value='" + aData['noKontrak'] + "'/>";
                var npwprekanan = "<input type='hidden' id='npwprekanan" + indexset + "' value='" + aData['npwpRekanan'] + "'/>";
                var namawp = "<input type='hidden' id='namawp" + indexset + "' value='" + aData['namaWp'] + "'/>";
                var alamatwp = "<input type='hidden' id='alamatwp" + indexset + "' value='" + aData['alamatWp'] + "'/>";
                var kotawp = "<input type='hidden' id='kotawp" + indexset + "' value='" + aData['kotaWp'] + "'/>";
                var kodebank = "<input type='hidden' id='kodebank" + indexset + "' value='" + aData['kodeBank'] + "'/>";
                var norek = "<input type='hidden' id='norek" + indexset + "' value='" + aData['noRekening'] + "'/>";
                var namapengirim = "<input type='hidden' id='namapengirim" + indexset + "' value='" + aData['namaPengirimBank'] + "'/>";
                var kjs = "<input type='hidden' id='kjs" + indexset + "' value='" + aData['kodeJenisSetor'] + "'/>";
                var map = "<input type='hidden' id='map" + indexset + "' value='" + aData['akunPajak'] + "'/>";
                var bulansah = "<input type='hidden' id='bulansah" + indexset + "' value='" + aData['bulanSah'] + "'/>";
                var masapajak = "<input type='hidden' id='masapajak" + indexset + "' value='" + aData['masaPajak'] + "'/>";
                var nosk = "<input type='hidden' id='nosk" + indexset + "' value='" + aData['noSk'] + "'/>";
                var kodebill = "<input type='hidden' id='kodebill" + indexset + "' value='" + aData['kodeBilling'] + "'/>";
                var tglbillexp = "<input type='hidden' id='tglbillexp" + indexset + "' value='" + aData['tglBillExp'] + "'/>";
                var tglbuku = "<input type='hidden' id='tglbuku" + indexset + "' value='" + aData['tglBuku'] + "'/>";
                var statusbpn = "<input type='hidden' id='statusbpn" + indexset + "' value='" + aData['statusBpn'] + "'/>";
                var cektransfer = "<input type='radio' name='cek'  id='cek" + indexset + "' value='" + indexset + "' class='checkbox' onclick='setidindex(" + indexset + ")' />";
                var kodeskpd = "<input type='hidden' id='kodeskpd" + indexset + "' value='" + aData['skpd']['kodeSkpd'] + "'/>";
                var idspmpotformat = "<input type='hidden' id='idspmpotformat" + indexset + "' value='" + aData['idSpmPotFormat'] + "'/>";
                var tglbayar = "<input type='hidden' id='tglbayar" + indexset + "' value='" + aData['tglBayar'] + "'/>";
                var ntpn = "<input type='hidden' id='ntpn" + indexset + "' value='" + aData['ntpn'] + "'/>";
                var ntb = "<input type='hidden' id='ntb" + indexset + "' value='" + aData['ntb'] + "'/>";
                var tahunpajak = "<input type='hidden' id='tahunpajak" + indexset + "' value='" + aData['tahunPajak'] + "'/>";
                var status = "";
                var namaskpd = "<textarea id='namaskpd" + indexset + "'readonly style='border:none;margin:0;width:200px;'>" + aData['skpd']['namaSkpd'] + "</textarea>";
                var statusbpn;
                var persen = aData['persenPot'];
                if (aData['persenPot'] == ".5") {
                    persen = "0.5";
                }

                var persenpot = "<input type='hidden' id='persenpot" + indexset + "' value='" + persen + "'/>";

                /*
                 // kalo patokannya tanggal bayar
                 if (aData['tglBayar'] == null) {
                 // set status BPN
                 if (aData['statusBpn'] == "1") {
                 statusbpn = "BPN Asli";
                 } else {
                 statusbpn = "";
                 }
                 
                 // set status pembayaran
                 status = "Pembayaran Gagal";
                 
                 } else {
                 // set status BPN
                 if (aData['statusBpn'] == "-") {
                 statusbpn = "";
                 } else if (aData['statusBpn'] == "1") {
                 statusbpn = "BPN Asli";
                 } else if (aData['statusBpn'] == "2") {
                 statusbpn = "BPN Sementara";
                 } else if (aData['statusBpn'] == "3") {
                 statusbpn = "Tidak Ada BPN";
                 }
                 
                 // set status pembayaran
                 if (aData['kodeBilling'] == "0") {
                 status = "Pembayaran Gagal";
                 } else {
                 if (aData['statusBpn'] == "3") { // sampai pembuatan kode billing saja
                 status = "Pembayaran Gagal";
                 } else {
                 status = "Pembayaran Berhasil";
                 }
                 }
                 }
                 */

                if (aData['statusBpn'] == "-") {
                    statusbpn = "";
                } else if (aData['statusBpn'] == "1") {
                    statusbpn = "BPN Asli";
                } else if (aData['statusBpn'] == "2") {
                    statusbpn = "BPN Sementara";
                } else if (aData['statusBpn'] == "3") {
                    statusbpn = "Tidak Ada BPN";
                }

                /* if (aData['kodeBilling'] == "0") {
                 status = "";
                 } else if (aData['statusBpn'] == "-") {
                 status = "Pembayaran Gagal";
                 } else {
                 status = "Pembayaran Berhasil";
                 } */


                if (aData['kodeBilling'] == "0") {
                    status = "";
                } else {
                    if (aData['statusBpn'] == "-" || aData['statusBpn'] == null) {
                        status = "";
                    } else if (aData['statusBpn'] == "3") { // sampai pembuatan kode billing saja
                        status = "Pembayaran Gagal";
                    } else {
                        status = "Pembayaran Berhasil";
                    }
                }
                //console.log(index + " - kodeBilling = " + aData['kodeBilling']);
                if (aData['kodeBilling'] == "0") {
                    kodeprosescek[indexset] = "PAYSSP";
                } else {
                    if (aData['tglBayar'] == null) {
                        kodeprosescek[indexset] = "PAYBILL";
                    } else {
                        kodeprosescek[indexset] = "INQBILL";
                    }
                }

                var kodemasa = aData['masaPajak'] + " - " + aData['tahunPajak'];

                $('td:eq(0)', nRow).html(index + jenis + kodeskpd + idspmpot + idspmpotformat + idsp2d + npwpbud + namabud + alamatbud + kotabud + nosp2d + tglsah + kodetrx);
                $('td:eq(2)', nRow).html(namaskpd + idskpd + tglbayar + ntpn + ntb + tahunpajak + persenpot);

                // $('td:eq(4)', nRow).html(getTanggal(aData['tglSp2dSah']));
                $('td:eq(4)', nRow).html(kodemasa);
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiPajak']));
                $('td:eq(8)', nRow).html(statusbpn);
                $('td:eq(9)', nRow).html(status + kodepot + nilaipajak + uraianpajak + nokontrak + npwprekanan + namawp + alamatwp + kotawp + kodebank + norek);
                $('td:eq(10)', nRow).html(cektransfer + namapengirim + kjs + map + bulansah + masapajak + nosk + kodebill + tglbillexp + tglbuku);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpd.kodeSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglSp2dSah", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianPajak", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiPajak", "bSortable": false, sClass: "right"},
                {"mDataProp": "kodeBilling", "bSortable": false, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": false, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": false, sClass: "left"},
                {"mDataProp": "idSp2d", "bSortable": false, sClass: "center"}

            ]
        });
    } else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function setidindex(id) {
    $('#idindex').val(id);
    $('#prosestransfer').attr("disabled", false);
    console.log("id = " + id);
    console.log("kodeproses = " + kodeprosescek[id]);
    if (kodeprosescek[id] == "PAYSSP") {
        document.getElementById('prosestransfer').innerHTML = 'Proses Permohonan Billing/Bayar';
    } else if (kodeprosescek[id] == "PAYBILL") {
        document.getElementById('prosestransfer').innerHTML = 'Proses Pembayaran Pajak';
    } else {
        document.getElementById('prosestransfer').innerHTML = 'Proses Cek NTPN';
    }
}
/*
 function getKodeStan() {
 $('#prosestransfer').attr("disabled", true);
 $.getJSON(getbasepath() + "/bkutf/json/getKodeStan", {},
 function(result) {
 kodestan = result.aData['kodeStan'];
 bulkId = result.aData['bulkIdRequest'];
 });
 }
 */

//langkah 1
function cekstatus() {
    var id = $('#idindex').val();
    var kodebill = $('#kodebill' + id).val();
    $('#prosestransfer').attr("disabled", true);

    console.log("persenpot = " + $('#persenpot' + id).val());
    console.log("kodetrx = " + $('#kodetrx' + id).val());
    if (id > 0) {
        var npwp, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor;

        if ($('#kodetrx' + id).val() == "P2" || $('#kodetrx' + id).val() == "P5" || ($('#kodetrx' + id).val() == "P4" && $('#persenpot' + id).val() == "0.5")) { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5; P4 : Pph Ps 4 Ayat 2 KHUSUS 0.5%
            console.log("masuk if beda npwp");
            npwp = $('#npwprekanan' + id).val();
            namawp = $('#namawp' + id).val();
            alamatwp = $('#alamatwp' + id).val();
            kotawp = $('#kotawp' + id).val();
            npwppenyetor = $('#npwpbud' + id).val();
            namapenyetor = $('#namabud' + id).val();

        } else { // npwp penyetor dan pemungut sama (BUD)
            console.log("masuk if sama npwp");
            npwp = $('#npwpbud' + id).val();
            namawp = $('#namabud' + id).val();
            alamatwp = $('#alamatbud' + id).val();
            kotawp = $('#kotabud' + id).val();
            npwppenyetor = $('#npwpbud' + id).val();
            namapenyetor = $('#namabud' + id).val();
        }
        var norek = $('#norek' + id).val().substr(0, 12);
        var npwprekanan = npwp.substr(0, 15); //"013072616054000", // buat ngetes
        var namawp = namawp.substr(0, 30); //"ASTRA GRAPHIA TBK.", //
        var alamatwp = alamatwp.substr(0, 50); //"JL. KRAMAT RAYA NO. 43, KRAMAT", //
        var kotawp = kotawp.substr(0, 30); //"JAKARTA PUSAT", //
        var akupajak = $('#map' + id).val(); //"411211", // buat ngetes
        var kjs = $('#kjs' + id).val(); //"100", // buat ngetes
        var masapajak = $('#masapajak' + id).val().toString() + $('#tahunpajak' + id).val().toString();
        var tahunpajak = $('#tahunpajak' + id).val(); //$('#tahun').val(); // untuk btl lewat tahun, tahunnya pake yg mana?
        var nosk = "000000000000000"; // default diisi 0 sebanyak 15
        var nop = "000000000000000000"; // default diisi 0 sebanyak 18
        var noidentitas = "0000000000000000"; // default diisi 0 sebanyak 16
        var nilaipajak = $('#nilaipajak' + id).val();
        var npwppenyetor = npwppenyetor.substr(0, 15); //"059454272077000", // buat ngetes
        var namapenyetor = namapenyetor.substr(0, 50); //"RIDWAN", //
        var uraian = $('#nosp2d' + id).val() + "|" + $('#kodetrx' + id).val() + "|" + $('#kodeskpd' + id).val();
        var idspmpotformat = $('#idspmpotformat' + id).val();
        if ($('#popup').val() == "0" && kodeprosescek[id] == "PAYBILL") {
            $('#billingsebelumpopup').val(kodebill);
            $('#idsebelumpopup').val($('#idspmpotformat' + id).val());
            $('#hide').click();
        } else {
            simpanPajak(norek, npwprekanan, namawp, alamatwp, kotawp, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian);
        }
    } else {
        bootbox.alert("Pilih Data Terlebih Dulu");
    }


}

function updateInquiry(npwprekanan, namawp, alamatwp, nilaipajak, kodebilling) {
    $('#inpwprekanan').val(npwprekanan);
    $('#ialamatwp').val(alamatwp);
    $('#inamawp').val(namawp);
    $('#inilaipajak').val(nilaipajak);
    $('#ikodebilling').val(kodebilling);
}

//langkah 3
function createBilling(id) {

    var fnpwp, fnamawp, falamatwp, fkotawp, fnpwppenyetor, fnamapenyetor;

    if ($('#kodetrx' + id).val() == "P2" || $('#kodetrx' + id).val() == "P5" || ($('#kodetrx' + id).val() == "P4" && $('#persenpot' + id).val() == "0.5")) { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5
        fnpwp = $('#npwprekanan' + id).val();
        fnamawp = $('#namawp' + id).val();
        falamatwp = $('#alamatwp' + id).val();
        fkotawp = $('#kotawp' + id).val();
        fnpwppenyetor = $('#npwpbud' + id).val();
        fnamapenyetor = $('#namabud' + id).val();

    } else { // npwp penyetor dan pemungut sama (BUD)
        fnpwp = $('#npwpbud' + id).val();
        fnamawp = $('#namabud' + id).val();
        falamatwp = $('#alamatbud' + id).val();
        fkotawp = $('#kotabud' + id).val();
        fnpwppenyetor = $('#npwpbud' + id).val();
        fnamapenyetor = $('#namabud' + id).val();
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/createbilling"; // payment ssp
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        norek: $('#norek' + id).val().substr(0, 12),
        npwprekanan: fnpwp.substr(0, 15), //"013072616054000", // buat ngetes
        namawp: fnamawp.substr(0, 30), //"ASTRA GRAPHIA TBK.", //
        alamatwp: falamatwp.substr(0, 50), //"JL. KRAMAT RAYA NO. 43, KRAMAT", //
        kotawp: fkotawp.substr(0, 30), //"JAKARTA PUSAT", //
        akupajak: $('#map' + id).val(), //"411211", // buat ngetes
        kjs: $('#kjs' + id).val(), //"100", // buat ngetes
        masapajak: $('#masapajak' + id).val().toString() + $('#tahunpajak' + id).val().toString(),
        tahunpajak: $('#tahunpajak' + id).val(), // untuk btl lewat tahun, tahunnya pake yg mana?
        nosk: "000000000000000", // default diisi 0 sebanyak 15
        nop: "000000000000000000", // default diisi 0 sebanyak 18
        noidentitas: "0000000000000000", // default diisi 0 sebanyak 16
        nilaipajak: $('#nilaipajak' + id).val(),
        npwppenyetor: fnpwppenyetor.substr(0, 15), //"059454272077000", // buat ngetes
        namapenyetor: fnamapenyetor.substr(0, 50), //"RIDWAN", //
        uraian: $('#nosp2d' + id).val() + "|" + $('#kodetrx' + id).val() + "|" + $('#kodeskpd' + id).val(),
        idspmpotformat: $('#idspmpotformat' + id).val()

    };
    dataac = datapajak;
    if (isEmpty()) {
        bootbox.alert("Data Sudah Tidak Ada di Tabel, Silahkan Refresh Halaman Lagi.");
    } else {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: varurl,
            data: JSON.stringify(dataac),
            beforeSend: function() {
                popup();
            },
            success: function(data) {
                var response = data['response'];
                var error = data['error'];
                // DATA RESPON SELAIN DATA YG DIKIRIM
                var idrequest = data['idrequest'];
                var idresponse = data['idresponse'];
                var idchannel = data['idchannel'];
                var norek = data['debitaccountno'];
                var bulkidreq = data['bulkidrequest'];
                var npwprekanan = data['taxid'];
                var namawp = data['taxpayername'];
                var alamatwp = data['taxpayeraddress'];
                var kotawp = data['taxpayercity'];
                var kjs = data['subtypeoftax'];
                var namakjs = data['subtypeoftaxdescription'];
                var akupajak = data['typeoftax'];
                var namaakunpajak = data['typeoftaxdescription'];
                var masapajak = data['taxperiod'];
                var nosk = data['numberofprovisionletter'];
                var nop = data['numberoftaxobject'];
                var noidentitas = data['identitynumber'];
                var nilaipajak = data['paymentamount'];
                var npwppenyetor = data['taxpayerid'];
                var namapenyetor = data['taxpayeridname'];

                var uraian = data['paymentdescription'];
                var kodeproses = data['processingcode'];
                var koderesponse = data['responsecode'];
                var uraianresponse = data['responsecodedescription'];
                var kodebilling = data['billingcode'];
                $('#billingsebelumpopup').val(kodebilling);
                $('#idsebelumpopup').val($('#idspmpotformat' + id).val());
                var tglbillexp = data['expirydate'];
                var kodestan = data['stan'];
                var tglbayar = "";
                var tgltransmisi = "";
                var tglbuku = "";

                var ntb = "";
                var ntpn = "";
                var statusbpn = "";

                if (tglbillexp == "-") {
                    tglbillexp = "";
                }

                var idapp = data['idapp'];
                var kodeapp = data['kodeapp'];

                console.log("id app : " + idapp + " - kode app : " + kodeapp);

                if (koderesponse == null) {
                    bootbox.alert("Koneksi Terputus");
                } else if (kodeproses != "301100" && kodeproses != "400100") {
                    var deskripsi = "";
                    if (uraianresponse !== "-") {
                        deskripsi = " - " + uraianresponse;
                    }
                    bootbox.alert("Pembuatan Kode Billing Gagal" + deskripsi);
                } 
                /*else if (idrequest != $('#idrequest').val() ||
                        idapp != $('#idspmpotformat' + id).val() ||
                        kodeapp != '001' ||
                        npwprekanan != fnpwp.substr(0, 15) ||
                        //namawp != fnamawp.substr(0, 30) ||
                        //alamatwp != falamatwp.substr(0, 50) || 
                        //kotawp != fkotawp.substr(0, 30) ||
                        npwppenyetor != fnpwppenyetor.substr(0, 15) ||
                        //namapenyetor != fnamapenyetor.substr(0, 50) ||
                        kjs != $('#kjs' + id).val() ||
                        akupajak != $('#map' + id).val()) {
                    bootbox.alert("Data Response Tidak Sesuai Dengan Data Request.");
                }*/ 
                else if (response != 'valid') {
                    bootbox.alert({
                        size: "small",
                        title: "DATA RESPONSE ERROR",
                        message: "PROSES PEMBUATAN KODE BILLING GAGAL DIKARENAKAN DATA " + error.toUpperCase() + " TIDAK SESUAI, SILAHKAN HUBUNGI ADMIN"//xhr.responseText
                    });
                }
                else {
                    updatePajak("1", bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
                }
            },
            error: function(xhr) {
                bootbox.alert({
                    size: "small",
                    title: "DATA RESPONSE ERROR",
                    message: "PROSES PEMBAYARAN PAJAK GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
                });
                console.error(xhr);
            },
            complete: function() {
                document.getElementById('myModal').style.display = "none";
                document.getElementById('myImg').style.display = "none";
            }
        }).always(function(data) {
            //$('#prosestransfer').attr("disabled", false);
        });
    }
}

//langkah 2
function simpanPajak(norek, npwprekanan, namawp, alamatwp, kotawp, kjs, akupajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian) {
    var id = $('#idindex').val();
    var kodereq;
    //console.log("idindex = " + kodeprosescek[id])
    if (kodeprosescek[id] == "PAYSSP") {
        kodereq = "1";
    } else if (kodeprosescek[id] == "PAYBILL") {
        kodereq = "2"; // payment billing
    } else {
        kodereq = "3"; // re-inquiry status bpn
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/simpandjpbank";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsp2d: $('#idsp2d' + id).val(),
        idspmpot: $('#idspmpot' + id).val(),
        kodepotspm: $('#kodepot' + id).val(),
        kodetrx: $('#kodetrx' + id).val(),
        norek: norek,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        kjs: kjs,
        akupajak: akupajak,
        masapajak: masapajak,
        tahunpajak: $('#tahunpajak' + id).val(),
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: nilaipajak,
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        koderequest: kodereq,
        kodeapp: "001" // 001 untuk Pajak SP2D
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
        $('#idrequest').val(data.idrequest);
        $('#bulkid').val(data.bulkid);
        document.getElementById('prosestransfer').innerHTML = 'Proses';
//        updatePajak(bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses2, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
//        gridpajak();
//        $('#prosestransfer').attr("disabled", false);
        var id = $('#idindex').val();
        var kodebill = $('#kodebill' + id).val();
        //$('#hide').click();
        if (kodeprosescek[id] == "PAYSSP") {
            createBilling(id); // create billing code
        } else if (kodeprosescek[id] == "PAYBILL") {
            paymentBilling(id, kodebill); // payment billing
        } else {
            reInquiryBilling(id, kodebill); // re-inquiry bpn status
        }

    });
}

//langkah 4
function updatePajak(kodereq, bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn) {
    var id = $('#idindex').val();

    if (tglbayar == "-") {
        tglbayar = "";
    }

    if (tgltransmisi == "-") {
        tgltransmisi = "";
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/updatedjpbank";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsp2d: $('#idsp2d' + id).val(),
        idspmpot: $('#idspmpot' + id).val(),
        bulkidreq: bulkidreq,
        idrequest: idrequest,
        idresponse: idresponse,
        norek: norek,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        kjs: kjs,
        namakjs: namakjs,
        akupajak: akupajak,
        namaakunpajak: namaakunpajak,
        masapajak: masapajak,
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: nilaipajak,
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
        kodeproses: kodeproses,
        koderesponse: koderesponse,
        uraianresponse: uraianresponse,
        kodebilling: kodebilling,
        tglbillexp: tglbillexp,
        kodestan: kodestan,
        tglbayar: tglbayar,
        tgltransmisi: tgltransmisi,
        tglbuku: tglbuku,
        ntb: ntb,
        ntpn: ntpn,
        statusbpn: statusbpn,
        koderequest: kodereq
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
        var idpilih = $('#idindex').val();
        console.log("INI KODE PROSES = " + kodeprosescek[idpilih] + " " + idpilih);

        if (kodeprosescek[idpilih] == "INQBILL") {
            updateSpmReInq(kodebilling, npwprekanan, namawp, alamatwp, tglbuku, statusbpn, tglbayar, ntb, ntpn, kodestan, masapajak); // re-inquiry status bpn
            getTglSp2dSah();

        } else if (kodeprosescek[idpilih] == "PAYBILL" || kodeprosescek[idpilih] == "PAYSSP") {
            if (kodeproses == "400100" || kodeproses == "301100" || kodeproses == "501011") { //create billing success
                updateSpm(kodereq, bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, kodestan); // payment billing
                gridpajak();
                if (kodeproses == "400100" || kodeproses == "301100") {
                    $('#hide').click();
                }
            } else if (kodeproses == "500100") { // jika proses simpan berhasil sampai mendapatkan NTPN (Status BPN Asli)
                updateSpm(kodereq, bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, kodestan); // payment billing
                getTglSp2dSah();
            } else {
                gridpajak();
            }

        } else {
            gridpajak();
        }

    });
}

//langkah 5
function updateSpm(kodereq, bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn, tglbayar, ntb, ntpn, kodestan) {
    var id = $('#idindex').val();

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/updatespmpot";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idspmpot: $('#idspmpot' + id).val(),
        bulkidreq: bulkidreq,
        idrequest: idrequest,
        kodebilling: kodebilling,
        tglbillexp: tglbillexp,
        tglbuku: tglbuku,
        statusbpn: statusbpn,
        tglbayar: tglbayar,
        ntb: ntb,
        ntpn: ntpn,
        kodestan: kodestan,
        koderequest: kodereq
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

    });
}

function paymentBilling(id, kodebill) {
    var fnpwp;
    if ($('#kodetrx' + id).val() == "P2" || $('#kodetrx' + id).val() == "P5" || ($('#kodetrx' + id).val() == "P4" && $('#persenpot' + id).val() == "0.5")) { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5
        fnpwp = $('#npwprekanan' + id).val();

    } else { // npwp penyetor dan pemungut sama (BUD)
        fnpwp = $('#npwpbud' + id).val();
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/paymentbilling";
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        norek: $('#norek' + id).val().substr(0, 12),
        kodebilling: kodebill,
        npwppenyetor: $('#npwpbud' + id).val().substr(0, 15),
        namapenyetor: $('#namabud' + id).val().substr(0, 30),
        uraian: $('#nosp2d' + id).val() + "|" + $('#kodetrx' + id).val() + "|" + $('#kodeskpd' + id).val(),
        idapp: $('#idspmpotformat' + id).val(),
        kjs: $('#kjs' + id).val(),
        map: $('#map' + id).val(),
        npwp: fnpwp
    };
    dataac = datapajak;
    if (isEmpty()) {
        bootbox.alert("Data Sudah Tidak Ada di Tabel, Silahkan Refresh Halaman Lagi.");
    } else {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        beforeSend: function() {
            popup();
        },
        success: function(data) {
            var response = data['response'];
            var error = data['error'];
            // DATA RESPON SELAIN DATA YG DIKIRIM

            var bulkidreq = data['bulkidrequest'];
            var idrequest = data['idrequest'];
            var idchannel = data['idchannel'];
            var idresponse = data['idresponse'];
            var norek = data['debitaccountno'];
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var kjs = data['subtypeoftax'];
            var namakjs = data['subtypeoftaxdescription'];
            var akupajak = data['typeoftax'];
            var namaakunpajak = data['typeoftaxdescription'];
            var masapajak = data['taxperiod'];
            var nosk = data['numberofprovisionletter'];
            var nop = data['numberoftaxobject'];
            var nilaipajak = data['paymentamount'];
            var npwppenyetor = data['taxpayerid'];
            var namapenyetor = data['taxpayeridname'];
            var uraian = data['paymentdescription'];
            var kodeproses = data['processingcode'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            var kodestan = data['stan'];
            var tglbayar = data['datetimeofpayment'];
            var tgltransmisi = data['datetimeoftransmission'];
            var tglbuku = data['settlementdate'];
            var ntb = data['ntbnumber'];
            var ntpn = data['ntpnnumber'];
            var statusbpn = data['bpnstatus'];
            var kotawp = "-";
            var noidentitas = "-";
            var tglbillexp = "";

            if (nilaipajak == "-") {
                nilaipajak = "0";
            }

            var idapp = data['idapp'];
            var kodeapp = data['kodeapp'];

            if (koderesponse == null) {
                bootbox.alert("Konensi Terputus");
            } 
            /*else if (idrequest != $('#idrequest').val() ||
                    idapp != $('#idspmpotformat' + id).val() ||
                    kodeapp != '001' ||
                    npwprekanan != fnpwp.substr(0, 15) ||
                    //namawp != namawp.substr(0, 30) ||
                    //alamatwp != alamatwp.substr(0, 50) || 
                    //kotawp != kotawp.substr(0, 30) ||
                    npwppenyetor != $('#npwpbud' + id).val().substr(0, 15) ||
                    kjs != $('#kjs' + id).val() ||
                    akupajak != $('#map' + id).val()) {
                bootbox.alert("Data Response Tidak Sesuai Dengan Data Request.");
            } */
            else if (response != 'valid') {
                bootbox.alert({
                    size: "small",
                    title: "DATA RESPONSE ERROR",
                    message: "PROSES PEMBUATAN KODE BILLING GAGAL DIKARENAKAN DATA " + error.toUpperCase() + " TIDAK SESUAI, SILAHKAN HUBUNGI ADMIN"//xhr.responseText
                });
            } else {
                var deskripsi = "";
                if (uraianresponse !== "-") {
                    deskripsi = " - " + uraianresponse;
                }

                if (kodeproses == "400100") {
                    bootbox.alert("Proses Pembuatan Kode Billing DJP Berhasil");
                } else if (kodeproses == "301100") {
//                    npwprekanan = $('#inpwprekanan').val();
//                    namawp = $('#inamawp').val();
//                    alamatwp = $('#ialamatwp').val();
//                    if (nilaipajak != "0") {
//                        nilaipajak = $('#inilaipajak').val();
//                    }
                    //bootbox.alert("Pembuatan Kode Billing Sudah Berhasil dan Valid");
                    bootbox.alert("Proses Pembayaran Pajak Gagal" + deskripsi + ", Silahkan Diulang Kembali");
                } else if (kodeproses == "501011") {
                    if (koderesponse == "00") {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Belum Mendapat NTPN");
                    } else {
                        bootbox.alert("Proses Pembayaran Pajak Gagal" + deskripsi + ", Silahkan Diulang Kembali");
                    }
                } else if (kodeproses == "500100") {
                    if (statusbpn == 3) {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Status BPN Belum Ada");
                    } else if (statusbpn == 2) {
                        bootbox.alert("Proses Pembayaran Pajak Berhasil Namun Status BPN Masih Sementara");
                    } else {
                        bootbox.alert("Proses Pengesahan MPN dan NTPN Berhasil");
                    }
                }

                updatePajak("2", bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
                // bootbox.alert(uraianresponse);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        },
        complete: function() {
            document.getElementById('myModal').style.display = "none";
            document.getElementById('myImg').style.display = "none";
        }
    }).always(function(data) {
        //$('#prosestransfer').attr("disabled", false);
    });}
}

function getTglSp2dSah() {

    $.getJSON(getbasepath() + "/sp2dpajaktransfer/json/getTglSp2dSah", {tahun: $('#tahun').val()},
    function(result) {
        //console.log(result);

        var banyak, kode, ket;
        var opt = '<option value="-"> Pilih </option>';

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['tglSp2dSahFormat'];
                ket = result.aData[i]['tglSp2dSahFormat'];

                opt += '<option value="' + kode + '">' + ket + '</option>';
            }
        }

        $("#tglsah").html(opt);
        gridpajak();

    });
}

function inquirynpwpdjp() {

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/inquirynpwpdjp";
    var dataac = [];
    var datapajak = {
        npwp: "017937715022000",
        akun: "411121",
        kjs: "100",
        idapp: "000000000505091"

    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        beforeSend: function() {
            popup();
        },
        success: function(data) {
            console.log("success data = " + data.toString());
            // DATA RESPON SELAIN DATA YG DIKIRIM
            /*
             var bulkidreq = data['bulkidrequest'];
             var idrequest = data['idrequest'];
             var idchannel = data['idchannel'];
             var idresponse = data['idresponse'];
             var norek = data['debitaccountno'];
             var npwprekanan = data['taxid'];
             var namawp = data['taxpayername'];
             var alamatwp = data['taxpayeraddress'];
             var kjs = data['subtypeoftax'];
             var namakjs = data['subtypeoftaxdescription'];
             var akupajak = data['typeoftax'];
             var namaakunpajak = data['typeoftaxdescription'];
             var masapajak = data['taxperiod'];
             var nosk = data['numberofprovisionletter'];
             var nop = data['numberoftaxobject'];
             var nilaipajak = data['paymentamount'];
             var npwppenyetor = data['taxpayerid'];
             var namapenyetor = data['taxpayeridname'];
             var uraian = data['paymentdescription'];
             var kodeproses = data['processingcode'];
             var koderesponse = data['responsecode'];
             var uraianresponse = data['responsecodedescription'];
             var kodebilling = data['billingcode'];
             var kodestan = data['stan'];
             var tglbayar = data['datetimeofpayment'];
             var tgltransmisi = data['datetimeoftransmission'];
             var tglbuku = data['settlementdate'];
             var ntb = data['ntbnumber'];
             var ntpn = data['ntpnnumber'];
             var statusbpn = data['bpnstatus'];
             var kotawp = "-";
             var noidentitas = "-";
             var tglbillexp = "";
             
             
             bootbox.alert(uraianresponse);
             */
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        },
        complete: function() {
            document.getElementById('myModal').style.display = "none";
            document.getElementById('myImg').style.display = "none";
        }
    }).always(function(data) {
        //$('#prosestransfer').attr("disabled", false);
    });
}

function reInquiryBilling(id, kodebill) {

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/reinquirybilling";
    var dataac = [];
    var datapajak = {
        bulkid: $('#bulkid').val(),
        idrequest: $('#idrequest').val(),
        ntb: $('#ntb' + id).val().substr(0, 12),
        kodebilling: kodebill,
        idapp: $('#idspmpotformat' + id).val()
    };
    dataac = datapajak;
    if (isEmpty()) {
        bootbox.alert("Data Sudah Tidak Ada di Tabel, Silahkan Refresh Halaman Lagi.");
    } else {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        beforeSend: function() {
            popup();
        },
        success: function(data) {

            // DATA RESPON SELAIN DATA YG DIKIRIM

            var bulkidreq = data['bulkidrequest'];
            var idrequest = data['idrequest'];
            var idchannel = data['idchannel'];
            var idresponse = data['idresponse'];
            var norek = "-";//data['debitaccountno'];
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var kjs = data['subtypeoftax'];
            var namakjs = data['subtypeoftaxdescription'];
            var akupajak = data['typeoftax'];
            var namaakunpajak = data['typeoftaxdescription'];
            var masapajak = data['taxperiod'];
            var nosk = "-";//data['numberofprovisionletter'];
            var nop = data['numberoftaxobject'];
            var nilaipajak = data['paymentamount'];
            var npwppenyetor = data['taxpayerid'];
            var namapenyetor = data['taxpayeridname'];
            var uraian = data['paymentdescription'];
            var kodeproses = "-"; //data['processingcode'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];
            var kodestan = data['stan'];
            var tglbayar = data['datetimeofpayment'];
            var tgltransmisi = data['datetimeoftransmission'];
            var tglbuku = data['settlementdate'];
            var ntb = data['ntbnumber'];
            var ntpn = data['ntpnnumber'];
            var statusbpn = data['bpnstatus'];
            var kotawp = "-";
            var noidentitas = "0000000000000000";
            var tglbillexp = "";

            if (nilaipajak == "-") {
                nilaipajak = "0";
            }

            if (uraianresponse == null) {
                bootbox.alert("Konensi Terputus");
            } else {

                if (koderesponse == "00") {
                    bootbox.alert("Proses Pengesahan MPN dan NTPN Berhasil");
                } else {
                    bootbox.alert("Status BPN Masih Sementara");
                }

                // bootbox.alert(uraianresponse);
                updatePajak("3", bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        },
        complete: function() {
            document.getElementById('myModal').style.display = "none";
            document.getElementById('myImg').style.display = "none";
        }
    }).always(function(data) {

    });}
}

function updateSpmReInq(kodebilling, npwprekanan, namawp, alamatwp, tglbuku, statusbpn, tglbayar, ntb, ntpn, kodestan, masapajak) {

    var id = $('#idindex').val();

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/updatespmpotreinq";
    var dataac = [];
    var datajour = {
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        masapajak: masapajak,
        tahun: $('#tahun').val(),
        idspmpot: $('#idspmpot' + id).val(),
        tglbuku: tglbuku,
        statusbpn: statusbpn,
        tglbayar: tglbayar,
        kodestan: kodestan,
        ntb: ntb,
        ntpn: ntpn,
        kodebilling: kodebilling
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

    });
}

function prosesEC() {
    var id = $('#idindex').val();
    var npwp, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor;

    if ($('#kodetrx' + id).val() == "P2" || $('#kodetrx' + id).val() == "P5") { // jika pajak apa npwp nya beda ??? P2 -> PPh Ps P2; PPN : P5
        npwp = $('#npwprekanan' + id).val();
        namawp = $('#namawp' + id).val();
        alamatwp = $('#alamatwp' + id).val();
        kotawp = $('#kotawp' + id).val();
        npwppenyetor = $('#npwpbud' + id).val();
        namapenyetor = $('#namabud' + id).val();

    } else { // npwp penyetor dan pemungut sama (BUD)
        npwp = $('#npwpbud' + id).val();
        namawp = $('#namabud' + id).val();
        alamatwp = $('#alamatbud' + id).val();
        kotawp = $('#kotabud' + id).val();
        npwppenyetor = $('#npwpbud' + id).val();
        namapenyetor = $('#namabud' + id).val();
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/prosesEC"; // payment ssp
    var dataac = [];
    var datapajak = {
        tahun: $('#tahun').val(),
        idsp2d: $('#idsp2d' + id).val(),
        idspmpot: $('#idspmpot' + id).val(),
        norek: $('#norek' + id).val().substr(0, 12),
        npwprekanan: npwp.substr(0, 15), //"013072616054000", // buat ngetes
        namawp: namawp.substr(0, 30), //"ASTRA GRAPHIA TBK.", //
        alamatwp: alamatwp.substr(0, 50), //"JL. KRAMAT RAYA NO. 43, KRAMAT", //
        kotawp: kotawp.substr(0, 30), //"JAKARTA PUSAT", //
        akupajak: $('#map' + id).val(), //"411211", // buat ngetes
        kjs: $('#kjs' + id).val(), //"100", // buat ngetes
        masapajak: $('#masapajak' + id).val().toString() + $('#tahunpajak' + id).val().toString(),
        tahunpajak: $('#tahunpajak' + id).val(), // untuk btl lewat tahun, tahunnya pake yg mana?
        nosk: "000000000000000", // default diisi 0 sebanyak 15
        nop: "000000000000000000", // default diisi 0 sebanyak 18
        noidentitas: "0000000000000000", // default diisi 0 sebanyak 16
        nilaipajak: $('#nilaipajak' + id).val(),
        npwppenyetor: npwppenyetor.substr(0, 15), //"059454272077000", // buat ngetes
        namapenyetor: namapenyetor.substr(0, 50), //"RIDWAN", //
        uraian: $('#nosp2d' + id).val() + "|" + $('#kodetrx' + id).val() + "|" + $('#kodeskpd' + id).val(),
        idspmpotformat: $('#idspmpotformat' + id).val()

    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            bootbox.alert("Proses Update Berhasil.");
//            // DATA RESPON SELAIN DATA YG DIKIRIM
//            var idrequest = data['idrequest'];
//            var idresponse = data['idresponse'];
//            var idchannel = data['idchannel'];
//            var norek = data['debitaccountno'];
//            var bulkidreq = data['bulkidrequest'];
//            var npwprekanan = data['taxid'];
//            var namawp = data['taxpayername'];
//            var alamatwp = data['taxpayeraddress'];
//            var kotawp = data['taxpayercity'];
//            var kjs = data['subtypeoftax'];
//            var namakjs = data['subtypeoftaxdescription'];
//            var akupajak = data['typeoftax'];
//            var namaakunpajak = data['typeoftaxdescription'];
//            var masapajak = data['taxperiod'];
//            var nosk = data['numberofprovisionletter'];
//            var nop = data['numberoftaxobject'];
//            var noidentitas = data['identitynumber'];
//            var nilaipajak = data['paymentamount'];
//            var npwppenyetor = data['taxpayerid'];
//            var namapenyetor = data['taxpayeridname'];
//
//            var uraian = data['paymentdescription'];
//            var kodeproses = data['processingcode'];
//            var koderesponse = data['responsecode'];
//            var uraianresponse = data['responsecodedescription'];
//            var kodebilling = data['billingcode'];
////            $('#billingsebelumpopup').val(kodebilling);
////            $('#idsebelumpopup').val($('#idspmpotformat' + id).val());
//            var tglbillexp = data['expirydate'];
//            var kodestan = data['stan'];
//            var tglbayar = "";
//            var tgltransmisi = "";
//            var tglbuku = "";
//
//            var ntb = "";
//            var ntpn = "";
//            var statusbpn = "";
//
//            if (tglbillexp == "-") {
//                tglbillexp = "";
//            }
//
//            if (uraianresponse == null) {
//                bootbox.alert("Koneksi Terputus");
//            } else if (kodeproses != "301100" && kodeproses != "400100") {
//                var deskripsi = "";
//                if (uraianresponse !== "-") {
//                    deskripsi = " - " + uraianresponse;
//                }
//                bootbox.alert("Pembuatan Kode Billing Gagal" + deskripsi);
//            }
            //updatePajak("1", bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "PROSES PEMBAYARAN PAJAK GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {
        //$('#prosestransfer').attr("disabled", false);
    });
}

function isEmpty(){
    var empty = false;
    var idspmpot = $('#idindex').val();
    if (idspmpot == null || idspmpot == '' || idspmpot == 'undefined')
        empty = true;
    else
        empty = false;
    
    return empty;
}