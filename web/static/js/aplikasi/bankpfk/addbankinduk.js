$(document).ready(function() {
    getIdBankUtama();
});

var idbank;

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
    var kode = $("#kodebanktf").val();
    var nama = $("#namabanktf").val();

    if (kode == "" || nama == "") {
        bootbox.alert("Kode / Nama Bank Transfer Tidak Boleh Kosong");
    } else {
        simpan();
    }
}

function getIdBankUtama() {
    
    $.getJSON(getbasepath() + "/bankpfk/json/getIdBankUtama", {},
    function (result) {
        
        idbank = result;
        
    });
}

function simpan() {

    var varurl = getbasepath() + "/bankpfk/json/prosessimpan";
    var dataac = [];
    var kodeaktif;

    if (document.getElementById("kodeaktif").checked == true) {
        kodeaktif = "1";
    } else {
        kodeaktif = "0";
    }

    var datajour = {
        kodebanktf: $("#kodebanktf").val(),
        namabanktf: $("#namabanktf").val(),
        kodebank: $("#kodebank").val(),
        namabank: $("#namabank").val(),
        kodebankrtgs: $("#kodebankrtgs").val(),
        namabankrtgs: $("#namabankrtgs").val(),
        kodeaktif: kodeaktif,
        alamat: "",
        idinduk: "0",
        idbank: idbank
        
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
        bootbox.alert("Data Bank Utama Berhasil Disimpan");
        //window.location.href = getbasepath() + "/bankpfk/indexbankpfk"; 

    });

}

