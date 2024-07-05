
$(document).ready(function() {

});

var kodestan, bulkId, nomorref;
var kodeproses = new Array();

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
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tahun", value: $('#tahun').val()},
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
                var jenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['kodeJenis'] + "'/>";
                var idspmpot = "<input type='hidden' id='idspmpot" + index + "' value='" + aData['idSpmPot'] + "'/>";
                var idsp2d = "<input type='hidden' id='idsp2d" + index + "' value='" + aData['idSp2d'] + "'/>";
                var npwpbud = "<input type='hidden' id='npwpbud" + index + "' value='" + aData['npwpBud'] + "'/>";
                var namabud = "<input type='hidden' id='namabud" + index + "' value='" + aData['namaBud'] + "'/>";
                var alamatbud = "<input type='hidden' id='alamatbud" + index + "' value='" + aData['alamatBud'] + "'/>";
                var kotabud = "<input type='hidden' id='kotabud" + index + "' value='" + aData['kotaBud'] + "'/>";
                var nosp2d = "<input type='hidden' id='nosp2d" + index + "' value='" + aData['noSp2d'] + "'/>";
                var tglsah = "<input type='hidden' id='tglsah" + index + "' value='" + aData['tglSp2dSah'] + "'/>";
                var kodetrx = "<input type='hidden' id='kodetrx" + index + "' value='" + aData['kodeTrx'] + "'/>";
                var kodepot = "<input type='hidden' id='kodepot" + index + "' value='" + aData['kodePotSpm'] + "'/>";
                var nilaipajak = "<input type='hidden' id='nilaipajak" + index + "' value='" + aData['nilaiPajak'] + "'/>";
                var uraianpajak = "<input type='hidden' id='uraianpajak" + index + "' value='" + aData['uraianPajak'] + "'/>";
                var nokontrak = "<input type='hidden' id='nokontrak" + index + "' value='" + aData['noKontrak'] + "'/>";
                var npwprekanan = "<input type='hidden' id='npwprekanan" + index + "' value='" + aData['npwpRekanan'] + "'/>";
                var namawp = "<input type='hidden' id='namawp" + index + "' value='" + aData['namaWp'] + "'/>";
                var alamatwp = "<input type='hidden' id='alamatwp" + index + "' value='" + aData['alamatWp'] + "'/>";
                var kotawp = "<input type='hidden' id='kotawp" + index + "' value='" + aData['kotaWp'] + "'/>";
                var kodebank = "<input type='hidden' id='kodebank" + index + "' value='" + aData['kodeBank'] + "'/>";
                var norek = "<input type='hidden' id='norek" + index + "' value='" + aData['noRekening'] + "'/>";
                var namapengirim = "<input type='hidden' id='namapengirim" + index + "' value='" + aData['namaPengirimBank'] + "'/>";
                var kjs = "<input type='hidden' id='kjs" + index + "' value='" + aData['kodeJenisSetor'] + "'/>";
                var map = "<input type='hidden' id='map" + index + "' value='" + aData['akunPajak'] + "'/>";
                var bulansah = "<input type='hidden' id='bulansah" + index + "' value='" + aData['bulanSah'] + "'/>";
                var masapajak = "<input type='hidden' id='masapajak" + index + "' value='" + aData['masaPajak'] + "'/>";
                var nosk = "<input type='hidden' id='nosk" + index + "' value='" + aData['noSk'] + "'/>";
                var kodebill = "<input type='hidden' id='kodebill" + index + "' value='" + aData['kodeBilling'] + "'/>";
                var tglbillexp = "<input type='hidden' id='tglbillexp" + index + "' value='" + aData['tglBillExp'] + "'/>";
                var tglbuku = "<input type='hidden' id='tglbuku" + index + "' value='" + aData['tglBuku'] + "'/>";
                var statusbpn = "<input type='hidden' id='statusbpn" + index + "' value='" + aData['statusBpn'] + "'/>";
                var cektransfer = "<input type='radio' name='cek'  id='cek" + index + "' value='" + index + "' class='checkbox' onclick='setidindex(" + index + ")' />";
                var kodeskpd = "<input type='hidden' id='kodeskpd" + index + "' value='" + aData['skpd']['kodeSkpd'] + "'/>";
                var idspmpotformat = "<input type='hidden' id='idspmpotformat" + index + "' value='" + aData['idSpmPotFormat'] + "'/>";
                var status = "";
                /* if (aData['kodeBilling'] == "0") {
                 status = "";
                 } else if (aData['kodeBilling'] !== "0" && aData['statusBpn'] == "-") {
                 status = "Kode Billing Berhasil, Pembayaran Gagal";
                 } else {
                 status = "Pembayaran Gagal";
                 } */
                //console.log(index + " - kodeBilling = " + aData['kodeBilling']);
                if (aData['kodeBilling'] == "0") {
                    kodeproses[index] = "PAYSSP";
                } else {
                    kodeproses[index] = "PAYBILL";
                }

                //console.log(index + " - kodeproses = " + kodeproses[index]);

                $('td:eq(0)', nRow).html(index + jenis + kodeskpd + idspmpot + idspmpotformat + idsp2d + npwpbud + namabud + alamatbud + kotabud + nosp2d + tglsah + kodetrx);
                $('td:eq(2)', nRow).html(getTanggal(aData['tglSp2dSah']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiPajak']));
                $('td:eq(7)', nRow).html(status + kodepot + nilaipajak + uraianpajak + nokontrak + npwprekanan + namawp + alamatwp + kotawp + kodebank + norek);
                $('td:eq(8)', nRow).html(cektransfer + namapengirim + kjs + map + bulansah + masapajak + nosk + kodebill + tglbillexp + tglbuku + statusbpn);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSp2d", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglSp2dSah", "bSortable": true, sClass: "center"},
                {"mDataProp": "uraianPajak", "bSortable": true, sClass: "center"},
                {"mDataProp": "nilaiPajak", "bSortable": true, sClass: "right"},
                {"mDataProp": "kodeBilling", "bSortable": true, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": true, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": true, sClass: "left"},
                {"mDataProp": "idSp2d", "bSortable": true, sClass: "center"}

            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
function gridmulti() {
    var baseurl = getbasepath();
    var urljson = baseurl + "/sp2dpajaktransfer/json/getlistindexmulti";
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
                        {name: "idsp2d", value: $('#idsp2d').val()},
                {name: "tahun", value: $('#tahun').val()}
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
                var jenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['kodeJenis'] + "'/>";
                var idspmpot = "<input type='hidden' id='idspmpot" + index + "' value='" + aData['idSpmPot'] + "'/>";
                var idsp2d = "<input type='hidden' id='idsp2d" + index + "' value='" + aData['idSp2d'] + "'/>";
                var npwpbud = "<input type='hidden' id='npwpbud" + index + "' value='" + aData['npwpBud'] + "'/>";
                var namabud = "<input type='hidden' id='namabud" + index + "' value='" + aData['namaBud'] + "'/>";
                var alamatbud = "<input type='hidden' id='alamatbud" + index + "' value='" + aData['alamatBud'] + "'/>";
                var kotabud = "<input type='hidden' id='kotabud" + index + "' value='" + aData['kotaBud'] + "'/>";
                var nosp2d = "<input type='hidden' id='nosp2d" + index + "' value='" + aData['noSp2d'] + "'/>";
                var tglsah = "<input type='hidden' id='tglsah" + index + "' value='" + aData['tglSp2dSah'] + "'/>";
                var kodetrx = "<input type='hidden' id='kodetrx" + index + "' value='" + aData['kodeTrx'] + "'/>";
                var kodepot = "<input type='hidden' id='kodepot" + index + "' value='" + aData['kodePotSpm'] + "'/>";
                var nilaipajak = "<input type='hidden' id='nilaipajak" + index + "' value='" + aData['nilaiPajak'] + "'/>";
                var uraianpajak = "<input type='hidden' id='uraianpajak" + index + "' value='" + aData['uraianPajak'] + "'/>";
                var nokontrak = "<input type='hidden' id='nokontrak" + index + "' value='" + aData['noKontrak'] + "'/>";
                var npwprekanan = "<input type='hidden' id='npwprekanan" + index + "' value='" + aData['npwpRekanan'] + "'/>";
                var namawp = "<input type='hidden' id='namawp" + index + "' value='" + aData['namaWp'] + "'/>";
                var alamatwp = "<input type='hidden' id='alamatwp" + index + "' value='" + aData['alamatWp'] + "'/>";
                var kotawp = "<input type='hidden' id='kotawp" + index + "' value='" + aData['kotaWp'] + "'/>";
                var kodebank = "<input type='hidden' id='kodebank" + index + "' value='" + aData['kodeBank'] + "'/>";
                var norek = "<input type='hidden' id='norek" + index + "' value='" + aData['noRekening'] + "'/>";
                var namapengirim = "<input type='hidden' id='namapengirim" + index + "' value='" + aData['namaPengirimBank'] + "'/>";
                var kjs = "<input type='hidden' id='kjs" + index + "' value='" + aData['kodeJenisSetor'] + "'/>";
                var map = "<input type='hidden' id='map" + index + "' value='" + aData['akunPajak'] + "'/>";
                var bulansah = "<input type='hidden' id='bulansah" + index + "' value='" + aData['bulanSah'] + "'/>";
                var masapajak = "<input type='hidden' id='masapajak" + index + "' value='" + aData['masaPajak'] + "'/>";
                var nosk = "<input type='hidden' id='nosk" + index + "' value='" + aData['noSk'] + "'/>";
                var kodebill = "<input type='hidden' id='kodebill" + index + "' value='" + aData['kodeBilling'] + "'/>";
                var tglbillexp = "<input type='hidden' id='tglbillexp" + index + "' value='" + aData['tglBillExp'] + "'/>";
                var tglbuku = "<input type='hidden' id='tglbuku" + index + "' value='" + aData['tglBuku'] + "'/>";
                var statusbpn = "<input type='hidden' id='statusbpn" + index + "' value='" + aData['statusBpn'] + "'/>";
                var cektransfer = "<input type='radio' name='cek'  id='cek" + index + "' value='" + index + "' class='checkbox' onclick='setidindex(" + index + ")' />";
                var kodeskpd = "<input type='hidden' id='kodeskpd" + index + "' value='" + aData['skpd']['kodeSkpd'] + "'/>";
                var idspmpotformat = "<input type='hidden' id='idspmpotformat" + index + "' value='" + aData['idSpmPotFormat'] + "'/>";
                var status = "";
                /* if (aData['kodeBilling'] == "0") {
                 status = "";
                 } else if (aData['kodeBilling'] !== "0" && aData['statusBpn'] == "-") {
                 status = "Kode Billing Berhasil, Pembayaran Gagal";
                 } else {
                 status = "Pembayaran Gagal";
                 } */
                //console.log(index + " - kodeBilling = " + aData['kodeBilling']);
                if (aData['kodeBilling'] == "0") {
                    kodeproses[index] = "PAYSSP";
                } else {
                    kodeproses[index] = "PAYBILL";
                }

                //console.log(index + " - kodeproses = " + kodeproses[index]);

                $('td:eq(0)', nRow).html(index + jenis + kodeskpd + idspmpot + idspmpotformat + idsp2d + npwpbud + namabud + alamatbud + kotabud + nosp2d + tglsah + kodetrx);
                $('#tglsp2d').val(getTanggal(aData['tglSp2dSah']));
                $('#nosp2d').val(aData['noSp2d']);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiPajak']));
                $('td:eq(7)', nRow).html(status + kodepot + nilaipajak + uraianpajak + nokontrak + npwprekanan + namawp + alamatwp + kotawp + kodebank + norek);
                $('td:eq(8)', nRow).html(cektransfer + namapengirim + kjs + map + bulansah + masapajak + nosk + kodebill + tglbillexp + tglbuku + statusbpn);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianPajak", "bSortable": true, sClass: "center"},
                {"mDataProp": "nilaiPajak", "bSortable": true, sClass: "right"},
                {"mDataProp": "kodeBilling", "bSortable": true, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": true, sClass: "left"},
                {"mDataProp": "statusBpn", "bSortable": true, sClass: "left"},
                {"mDataProp": "idSp2d", "bSortable": true, sClass: "center"}

            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function setidindex(id) {
    $('#idindex').val(id);
    $('#prosestransfer').attr("disabled", false);
    console.log("id = " + id);
    console.log("kodeproses = " + kodeproses[id]);
    if (kodeproses[id] == "PAYSSP") {
        document.getElementById('prosestransfer').innerHTML = 'Proses Permohonan Kode Billing';
    } else {
        document.getElementById('prosestransfer').innerHTML = 'Proses Pembayaran Pajak';
    }
}

function getKodeStan() {
    $('#prosestransfer').attr("disabled", true);
    $.getJSON(getbasepath() + "/bkutf/json/getKodeStan", {},
            function(result) {
                kodestan = result.aData['kodeStan'];
                bulkId = result.aData['bulkIdRequest'];
            });
}

function cekstatus() {
    var id = $('#idindex').val();
    var kodebill = $('#kodebill' + id).val();
    window.location.href = getbasepath() + "/sp2dpajaktransfer/transaksipajakmulti?idskpd=" + $('#idskpd').val() + "&tahun=" + $('#tahun').val() + "&kproses=" + $('#kproses').val() + "&idsp2d=" + $('#idsp2d' + id).val();

//    if (kodeproses[id] == "PAYSSP") {
//        transferPaymentSsp(id); // transfer awal
//    } else {
//        transferPaymentBilling(id, kodebill); // transfer jila kode billing sudah ada
//    }

}

function transferPaymentSsp(id) {

    var npwp, namawp, alamatwp, kotawp, npwppenyetor, namapenyetor;

    if ($('#kodetrx' + id).val() == "P1" || $('#kodetrx' + id).val() == "P3") { // jika pajak apa npwp nya beda ???
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

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/ssp"; // payment ssp
    var dataac = [];
    var datapajak = {
        norek: $('#norek' + id).val().substr(0, 12),
        npwprekanan: "013072616054000", //npwp.substr(0, 15), buat ngetes
        namawp: "ASTRA GRAPHIA TBK.", //namawp.substr(0, 30),
        alamatwp: "JL. KRAMAT RAYA NO. 43, KRAMAT", //alamatwp.substr(0, 50),
        kotawp: "JAKARTA PUSAT", //kotawp.substr(0, 30),
        akupajak: "411211", //$('#map' + id).val(), buat ngetes
        kjs: "100", //$('#kjs' + id).val(), buat ngetes
        masapajak: $('#masapajak' + id).val().toString() + $('#tahun').val().toString(),
        tahunpajak: $('#tahun').val(), // untuk btl lewat tahun, tahunnya pake yg mana?
        nosk: "000000000000000", // default diisi 0 sebanyak 15
        nop: "000000000000000000", // default diisi 0 sebanyak 18
        noidentitas: "0000000000000000", // default diisi 0 sebanyak 16
        nilaipajak: $('#nilaipajak' + id).val(),
        npwppenyetor: "059454272077000", //npwppenyetor.substr(0, 15), buat ngetes
        namapenyetor: "RIDWAN", //namapenyetor.substr(0, 50),
        uraian: $('#uraianpajak' + id).val() + "/" + $('#nosp2d' + id).val() + "/" + $('#kodeskpd' + id).val(), // format uraian pajak nya?? apakan jadi dengan format: no sp2d / kode skpd ??
        idspmpotformat: $('#idspmpotformat' + id).val()

    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            // console.log("data = " + data[0]['debitaccountno']);
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

            var namakjs = data['subtypeoftaxdescription'].toString();
            var akupajak = data['typeoftax'];
            var namaakunpajak = data['typeoftaxdescription'].toString();
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
            var tglbillexp = data['expirydate'];
            var kodestan = data['stan'];
            var tglbayar = data['datetimeofpayment'];
            var tgltransmisi = data['datetimeoftransmission'];
            var tglbuku = data['settlementdate'];

            var ntb = data['ntbnumber'];
            var ntpn = data['ntpnnumber'];
            var statusbpn = data['bpnstatus'];

            if (tglbillexp == "-") {
                tglbillexp = "";
            }

            if (uraianresponse == null) {
                bootbox.alert("Konensi Terputus");
            } else {
                if (koderesponse = "00") {
                    bootbox.alert("Proses Pembuatan Kode Billing DJP Berhasil");
                } else {
                    bootbox.alert(uraianresponse);
                }

                simpanPajak(bulkidreq, idrequest, idchannel, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {
        $('#prosestransfer').attr("disabled", false);
    });
}

function simpanPajak(bulkidreq, idrequest, idchannel, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses2, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn) {
    var id = $('#idindex').val();
    var kodereq;
    //console.log("idindex = " + kodeproses[id])
    if (kodeproses[id] == "PAYSSP") {
        kodereq = "1";
    } else {
        kodereq = "2"; // payment billing
    }

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/simpandjpbank";
    var dataac = [];
    var datajour = {
        tahun: $('#tahun').val(),
        idsp2d: $('#idsp2d' + id).val(),
        idspmpot: $('#idspmpot' + id).val(),
        kodepotspm: $('#kodepot' + id).val(),
        kodetrx: $('#kodetrx' + id).val(),
        bulkidreq: bulkidreq,
        idrequest: idrequest,
        idchannel: idchannel,
        norek: norek,
        npwprekanan: npwprekanan,
        namawp: namawp,
        alamatwp: alamatwp,
        kotawp: kotawp,
        kjs: kjs,
        akupajak: akupajak,
        masapajak: masapajak,
        tahunpajak: $('#tahun').val(),
        nosk: nosk,
        nop: nop,
        noidentitas: noidentitas,
        nilaipajak: nilaipajak,
        npwppenyetor: npwppenyetor,
        namapenyetor: namapenyetor,
        uraian: uraian,
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
        updatePajak(bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses2, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
        gridpajak();
    });
}

function updatePajak(bulkidreq, idrequest, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn) {
    var id = $('#idindex').val();

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
        statusbpn: statusbpn
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
        if (koderesponse == "00") { // jika berhasil
            updateSpm(bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn);

        }
    });
}

function updateSpm(bulkidreq, idrequest, kodebilling, tglbillexp, tglbuku, statusbpn) {
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
        statusbpn: statusbpn
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

function transferPaymentBilling(id, kodebill) {

    var varurl = getbasepath() + "/sp2dpajaktransfer/json/billing";
    var dataac = [];
    var datapajak = {
        norek: $('#norek' + id).val().substr(0, 12),
        kodebilling: kodebill,
        npwppenyetor: "059454272077000", //$('#npwpbud' + id).val().substr(0, 15),
        namapenyetor: "RIDWAN", //$('#namabud' + id).val().substr(0, 30),
        uraian: $('#nosp2d' + id).val() + "/" + $('#kodeskpd' + id).val() + "/" + $('#uraianpajak' + id).val(),
        idapp: $('#idspmpotformat' + id).val()
    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {

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

            if (uraianresponse == null) {
                bootbox.alert("Konensi Terputus");
            } else {
                bootbox.alert(uraianresponse);
                simpanPajak(bulkidreq, idrequest, idchannel, idresponse, norek, npwprekanan, namawp, alamatwp, kotawp, kjs, namakjs, akupajak, namaakunpajak, masapajak, nosk, nop, noidentitas, nilaipajak, npwppenyetor, namapenyetor, uraian, kodeproses, koderesponse, uraianresponse, kodebilling, tglbillexp, kodestan, tglbayar, tgltransmisi, tglbuku, ntb, ntpn, statusbpn);
            }
        },
        error: function(xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function(data) {
        $('#prosestransfer').attr("disabled", false);
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
                /*
                 017937715022000
                 059454272077000

                 kdmap-kjs untuk ssp ber npwp :
                 411211	100
                 411121	100
                 */
    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
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
        }
    }).always(function(data) {
        //$('#prosestransfer').attr("disabled", false);
    });
}
