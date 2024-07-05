$(document).ready(function () {
    $('#potpersen').hide();
    inquirybilling();
    getPajak();
});

function getPajak() {

    var id = $('#idindex', window.parent.document).val();
    var idspmpot = $('#idspmpot' + id, window.parent.document).val();
    $.getJSON(getbasepath() + "/sp2dpajaktransfer/json/getPajak", {idspmpot: idspmpot},
            function (data) {
                var tahunPajak = data['tahunPajak'];
                var kodeSkpd = data['skpd']['kodeSkpd'];
                var namaSkpd = data['skpd']['namaSkpd'];
                var tglSp2dSah = data['tglSp2dSahFormat'];
                var noSp2d = data['noSp2d'];
                var masaPajak = data['masaPajak'];
                var namaKJS = data['namaKJS'];
                var namaMAP = data['namaMAP'];
                var jenisPajak = data['uraianPajak'];

                $('#masaPajak').val(masaPajak);
                $('#jenisPajak').val(jenisPajak);
                var mp1 = $('#masaPajak').val().substr(0, 2);
                var mp2 = $('#masaPajak').val().substr(2, 2);

                $('#masapajak1').val(mp1);
                $('#masapajak2').val(mp2);

                $('#tahunpajak').val(tahunPajak);
                $('#skpd').val(kodeSkpd + ' / ' + namaSkpd);
                $('#nosp2d').val(noSp2d + ' / ' + tglSp2dSah);
                $('#kjs').val(namaKJS);
                $('#map').val(namaMAP);
            });
}
function inquirybilling() {
    var id = $('#idindex', window.parent.document).val();
    //var kodebilling = $('#kodebill' + id, window.parent.document).val();
    //var idapp = $('#idspmpotformat' + id, window.parent.document).val();
    var kodebilling = $('#billingsebelumpopup', window.parent.document).val();
    var idapp = $('#idsebelumpopup', window.parent.document).val();
    var varurl = getbasepath() + "/sp2dpajaktransfer/json/inquirybilling";
    var dataac = [];
    var datapajak = {
        billingcode: kodebilling,
        idapp: idapp

    };
    dataac = datapajak;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function (data) {
            console.log("success data = " + data.toString());
            // DATA RESPON SELAIN DATA YG DIKIRIM
            var npwprekanan = data['taxid'];
            var namawp = data['taxpayername'];
            var alamatwp = data['taxpayeraddress'];
            var nilaipajak = data['paymentamount'];
            var koderesponse = data['responsecode'];
            var uraianresponse = data['responsecodedescription'];
            var kodebilling = data['billingcode'];

            var map = data['typeoftax'];
            var kjs = data['subtypeoftax'];
            var masapajak = data['taxperiod'];
            $('#npwp').val(npwprekanan);
            $('#namanpwp').val(namawp);
            $('#alamatnpwp').val(alamatwp);
            $('#nilaipajak').val(nilaipajak);
            $('#kodebilling').val(kodebilling);
            if (koderesponse != "00") {
//                bootbox.alert("Inquiry Billing Gagal");
                bootbox.alert(uraianresponse);
                $('#btnBayar').hide();
            } else {
                updateInquiry(npwprekanan, namawp, alamatwp, kodebilling, map, kjs, masapajak);
            }
        },
        error: function (xhr) {
            bootbox.alert({
                size: "small",
                title: "DATA RESPONSE ERROR",
                message: "DATA TRANSFER GAGAL KARENA KONEKSI TERPUTUS"//xhr.responseText
            });
            console.error(xhr);
        }
    }).always(function (data) {
        //$('#prosestransfer').attr("disabled", false);
    });
}
function bayar() {
    $('#popup', window.parent.document).val("1").change();
    parent.gridpajak();
    parent.cekstatus();
    parent.$.fancybox.close();
}

function updateInquiry(npwprekanan, namawp, alamatwp, kodebilling, map, kjs, masapajak) {
    var id = $('#idindex', window.parent.document).val();
    var idapp = $('#idsebelumpopup', window.parent.document).val();
    $.getJSON(getbasepath() + "/sp2dpajaktransfer/json/updateInquiry", {idapp: idapp, npwprekanan: npwprekanan,
        namawp: namawp, alamatwp: alamatwp, kodebilling: kodebilling, map: map, kjs: kjs, masapajak: masapajak},
            function (data) {
                console.log("Update Inquiry Berhasil")
            });
}
