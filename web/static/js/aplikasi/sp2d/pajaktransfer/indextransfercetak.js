$(document).ready(function () {
    getTglSp2dSah();
});

var kodestan, bulkId, nomorref;
var kodeproses = new Array();

function cari(param) {
    console.log("param cari = " + param)
    $('#' + param).keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridpajak();
        }
    });
}
function gridpajak() {
    var baseurl = getbasepath();
    var urljson = baseurl + "/sp2dpajaktransfer/json/getlistindexcetak";
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
            "fnDrawCallback": function () {
                // $(".checkbox").hide();
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "kodeskpdfilter", value: $('#kodeskpdfilter').val()},
                        {name: "namaskpdfilter", value: $('#namaskpdfilter').val()},
                        {name: "nosp2dfilter", value: $('#nosp2dfilter').val()},
                        {name: "tglsah", value: $('#tglsah2').val()},
                        {name: "tahun", value: $('#tahun').val()},
                        {name: "kproses", value: $('#kproses').val()}
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var jenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['kodeJenis'] + "'/>";
                var idskpd = "<input type='hidden' id='idskpd" + index + "' value='" + aData['idSkpd'] + "'/>";
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
                var cetak = "<a href='" + getbasepath() + "/sp2dpajaktransfer/json/prosescetak"
                        + "?nosp2d=" + aData['noSp2d']
                        + "&kodetrx=" + aData['kodeTrx']
                        + "&idspmpot=" + aData['idSpmPot'] + "' class='icon-print' ></a>";
                var kodeskpd = "<input type='hidden' id='kodeskpd" + index + "' value='" + aData['skpd']['kodeSkpd'] + "'/>";
                var idspmpotformat = "<input type='hidden' id='idspmpotformat" + index + "' value='" + aData['idSpmPotFormat'] + "'/>";
                var tglbayar = "<input type='hidden' id='tglbayar" + index + "' value='" + aData['tglBayar'] + "'/>";
                var ntpn = "<input type='hidden' id='ntpn" + index + "' value='" + aData['ntpn'] + "'/>";
                var ntb = "<input type='hidden' id='ntb" + index + "' value='" + aData['ntb'] + "'/>";
                var status = "";
                var namaskpd = "<textarea id='namaskpd" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['skpd']['namaSkpd'] + "</textarea>";
                var statusbpn;


                if (aData['statusBpn'] == "-") {
                    statusbpn = "Belum Ada BPN";
                } else if (aData['statusBpn'] == "1") {
                    statusbpn = "BPN Asli";
                } else if (aData['statusBpn'] == "2") {
                    statusbpn = "BPN Sementara";
                } else if (aData['statusBpn'] == "3") {
                    statusbpn = "Tidak Ada BPN";
                }

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
                //consol
                /*if (aData['kodeBilling'] == "0") {
                 status = "";
                 } else if (aData['kodeBilling'] !== "0" && aData['statusBpn'] == "-") {
                 status = "Kode Billing Berhasil, Pembayaran Gagal";
                 } else {
                 status = "Pembayaran Gagal";
                 }*/
                //console.log(index + " - kodeBilling = " + aData['kodeBilling']);
                if (aData['kodeBilling'] == "0") {
                    kodeproses[index] = "PAYSSP";
                } else {
                    if (aData['tglBayar'] == null) {
                        kodeproses[index] = "PAYBILL";
                    } else {
                        kodeproses[index] = "INQBILL";
                    }

                }

                //console.log(index + " - kodeproses = " + kodeproses[index]);

                $('td:eq(0)', nRow).html(index + jenis + kodeskpd + idspmpot + idspmpotformat + idsp2d + npwpbud + namabud + alamatbud + kotabud + nosp2d + tglsah + kodetrx);
                $('td:eq(2)', nRow).html(namaskpd + idskpd + tglbayar + ntpn + ntb);
                $('td:eq(4)', nRow).html(getTanggal(aData['tglSp2dSah']));
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiPajak']));
                $('td:eq(8)', nRow).html(statusbpn);
                $('td:eq(9)', nRow).html(status + kodepot + nilaipajak + uraianpajak + nokontrak + npwprekanan + namawp + alamatwp + kotawp + kodebank + norek);
                $('td:eq(10)', nRow).html(cetak + namapengirim + kjs + map + bulansah + masapajak + nosk + kodebill + tglbillexp + tglbuku);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSp2d", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpd.kodeSkpd", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSp2d", "bSortable": true, sClass: "center"},
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
    console.log("kodeproses = " + kodeproses[id]);
//    if (kodeproses[id] == "PAYSSP") {
//        document.getElementById('prosestransfer').innerHTML = 'Proses Permohonan Kode Billing';
//    } else if (kodeproses[id] == "PAYBILL") {
//        document.getElementById('prosestransfer').innerHTML = 'Proses Pembayaran Pajak';
//    } else {
//        document.getElementById('prosestransfer').innerHTML = 'Proses Re-Inquiry Billing';
//    }
}

function cekstatus() {
    var id = $('#idindex').val();
    var kodebill = $('#kodebill' + id).val();

    if (kodeproses[id] == "PAYSSP") {
        transferPaymentSsp(id); // create billing code
    } else if (kodeproses[id] == "PAYBILL") {
        transferPaymentBilling(id, kodebill); // payment billing
    } else {
        reInquiryBilling(id, kodebill); // re-inquiry bpn status
    }

}

function getTglSp2dSah() {

    $.getJSON(getbasepath() + "/sp2dpajaktransfer/json/getTglSp2dSahCetak", {tahun: $('#tahun').val()},
            function (result) {
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

            });
}



