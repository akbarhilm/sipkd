$(document).ready(function() {
    if ($("#kodeAktif").val() == 1) {
        document.getElementById("kodeaktif").checked = true;
    } else {
        document.getElementById("kodeaktif").checked = false;
    }
});

function numbersonly(myfield, e, dec) {
    var key;
    var keychar;

    if (window.event)
        key = window.event.keyCode;
    else if (e)
        key = e.which;
    else
        return true;
    keychar = String.fromCharCode(key);

// control keys
    if ((key == null) || (key == 0) || (key == 8) ||
            (key == 9) || (key == 13) || (key == 27) || (key == 46) || (key == 45))
        return true;

// numbers
    else if ((("0123456789").indexOf(keychar) > -1))
        return true;

// decimal point jump
    else if (dec && (keychar == "."))
    {
        myfield.form.elements[dec].focus();
        return false;
    }
    else
        return false;
}

function cekLengkap() {
    var kode = $("#kodeBankTransfer").val();
    var nama = $("#namaBankTransfer").val();

    if (kode == "" || nama == "") {
        bootbox.alert("Kode / Nama Bank Transfer Tidak Boleh Kosong");
    } else {
        update();
    }
}

function update() {

    var varurl = getbasepath() + "/bankpfk/json/prosesupdate";
    var dataac = [];
    var kodeaktif;

    if (document.getElementById("kodeaktif").checked == true) {
        kodeaktif = "1";
    } else {
        kodeaktif = "0";
    }

    var datajour = {
        kodebanktf: $("#kodeBankTransfer").val(),
        namabanktf: $("#namaBankTransfer").val(),
        kodebank: $("#kodeBank").val(),
        namabank: $("#namaBank").val(),
        kodebankrtgs: $("#kodeBankRtgs").val(),
        namabankrtgs: $("#namaBankRtgs").val(),
        kodeaktif: kodeaktif,
        alamat: "",
        idinduk: "0",
        idbank: $("#idBank").val()
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
        bootbox.alert("Data Bank Utama Berhasil Diubah");
        
    });

}

