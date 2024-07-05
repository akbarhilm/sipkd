$(document).ready(function() {
    

});

function cetak() {
    var idskpd = $('#idskpd').val();
    var tahun = $("#tahun").val();
    var idkegiatan = $("#idKegiatan").val();
    var bulan = $("#bulan").val();
    
    if (idskpd == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href = getbasepath() + "/kartukendali/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&idkegiatan=" + idkegiatan + "&bulan=" + bulan;
    }
}