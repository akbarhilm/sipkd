$(document).ready(function() {

});

function cekSimpan() {
    var str = $("#passwordBaru").val();
    var panjang = str.length;

    if ($("#passwordLama").val() !== "" && $("#passwordBaru").val() !== "" && $("#passwordBaruVerify").val() !== "") {
        if ($("#passwordLama").val() !== $("#passpengguna").val()) {
            bootbox.alert("Password Lama Tidak Sesuai");
        } else if ($("#passwordBaru").val() !== $("#passwordBaruVerify").val()) {
            bootbox.alert("Password Baru Tidak Sesuai");
        } else if (panjang < 5 || panjang > 16) {
            bootbox.alert("Panjang Karakter : 5-16 Karakter");
        } else {
            simpan();
        }
    } else {
        bootbox.alert("Pengisina Data Harus Lengkap");
    }


}
function simpan() {

    var varurl = getbasepath() + "/ubahpass/json/prosesubah";
    var dataac = [];

    var datajour = {
        user: $("#pengguna").val(),
        pass: $("#passwordBaru").val()
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
        bootbox.alert("Password Berhasil Diubah");
    });
}
