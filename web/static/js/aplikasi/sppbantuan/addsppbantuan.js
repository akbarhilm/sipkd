
$(document).ready(function () {
    var nospd = $('#noSpd').val();
    if (nospd == "") {
        $('#popupspd').show();
    } else {
        $('#popupspd').hide();
    }
    try {
        hitungnilaisppsisaall( );
    } catch (e) {

    }
});

// var global
var response76 = "00";

function hitungnilaisppsisaall( ) {
    var nilaispd = parseFloat(accounting.unformat($("#nilaiSpd").val(), ","));
    var nilaispp = parseFloat(accounting.unformat($("#nilaiSpp").val(), ","));
    var sisa = accounting.formatNumber((nilaispd - nilaispp), 2, '.', ",");
    console.log(" sisa " + sisa + " = " + (nilaispp <= nilaispd));
    if (nilaispp <= nilaispd) {
        if (nilaispp == 0)
            nilaispp = parseFloat(accounting.unformat($("#nilaiSpp").val(), ","));

    } else {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function () {
            $("#nilaiSpp").val(0);
            sisa = accounting.formatNumber((nilaispd - 0), 2, '.', ",");
        });
    }
    $("#nilaiSppSisaAkhir").text(sisa);
    $("#nilaiSppSisa").val(sisa);

}

function hitungnilaisppsisa(objval) {
    var nilaispd = parseFloat(accounting.unformat($("#nilaiSpd").val(), ","));
    var nilaispp = parseFloat(accounting.unformat(objval, ","));
    var sisa = accounting.formatNumber((nilaispd - nilaispp), 2, '.', ",");
    console.log(" sisa " + sisa + " = " + (nilaispp <= nilaispd));
    if (nilaispp <= nilaispd) {
        if (nilaispp == 0)
            nilaispp = parseFloat(accounting.unformat($("#nilaiSpp").val(), ","));

    } else {
        bootbox.alert("Nilai SPP tidak boleh lebih besar dari nilai SPD", function () {
            $("#nilaiSpp").val(0);
            sisa = accounting.formatNumber((nilaispd - 0), 2, '.', ",");
        });
    }
    $("#nilaiSppSisaAkhir").text(sisa);
    $("#nilaiSppSisa").val(sisa);

}

function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppbantuan/indexsppbantuan/");
    } else {
        window.location.replace(getbasepath() + "/sppbantuan/indexsppbantuan");
    }
}

function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#rekeningBank').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");

    $("#refsppbantuan").valid();
    //console.log("getDataBankDki()+ kode bank == "+kodebank);

    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkialamat').val("");
    $('#dkinama').val("");
    //$('#dkinpwp').val("");

    //if ((kodebank == "111") && (norek !== "")) {
    if (norek !== "") {
        //var varurl = getbasepath() + "/postdata/json/kirimpostdata";
        var varurl = getbasepath() + "/postdata/json/ceknorek";

        var dataac = [];

        var datajour = {
            kodebank: kodebank,
            norek: norek
        };
        dataac = datajour;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: varurl,
            data: JSON.stringify(dataac),
            success: function (data) {
                console.log(data);

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    $('#dkinpwp').val(data.npwp);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"].trim());
                    $('#dkialamat').val("");
                    $('#dkinpwp').val("");
                    response76 = data["response"];
                }

            },
            error: function (xhr) {
                console.error(xhr);
            }
        }).always(function (data) {
            $('#buttoninduk').attr("disabled", false);
            //$("#spdBTLMasterform").valid();
        });
    }

}

function cek() {
    //if ($("#kodeBankTransfer").val() == "111") {
        if ($("#rekeningBank").val() !== $("#dkinorek").val()) {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
            bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
            return false;

        } else if ($("#dkinama").val().trim() !== $("#namaPenerima").val()) {
            bootbox.alert("Nama Penerima Harus Sama dengan Nama dari Bank DKI");
            return false;

        } else if (response76 !== "00") {
            bootbox.alert("Data Nasabah Tidak Terdaftar");
            return false;

        } else {
            return true;
        }
    /*
    } else {
        return true;
    }*/

}
