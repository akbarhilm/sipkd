

function cetak() {

    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var tanggal = $("#tgl").val();

    window.location.href = getbasepath() + "/lappnrm/json/prosescetakpnrmskpd?tahun=" + tahun + "&idskpd=" + idskpd + "&tgl=" + tanggal;

}


