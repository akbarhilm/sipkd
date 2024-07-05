$(document).ready(function() {
    //$("#tglPosting").datepicker("setDate", new Date());
    jenislaporan = 1;
});

var jenislaporan;
var jenisbulan;

function setjenis(jenis){
    jenislaporan = jenis;
}

function setbulan(bulan){
    jenisbulan = bulan;
}

function cetakjurnal() {
    bootbox.alert("Belum ada file reportnya");
    
    /*
    var tahun = $("#tahun").val();
    var jenislap = jenislaporan; //$("#jenislaporan").val();
    var eee = tahun+"-"+"LRA-Permendagri-13"+".pdf";
    //bootbox.alert("jenis laporan == "+ jenislap);
    window.location.href= getbasepath() + "/laporanprovinsi/json/prosescetak?tahun="+tahun+"&jenislaporan="+jenislap+"&namafile="+eee;
   */
}

    
   