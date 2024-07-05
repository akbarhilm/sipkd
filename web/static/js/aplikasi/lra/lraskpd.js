$(document).ready(function() {
    bulan = "01";
    getkodestatus(); 
});

var kodestatus;
var bulan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var dpaperubahan;
    
    if (document.getElementById("dpaperubahan").checked == true){
        dpaperubahan="1";
    } else if (document.getElementById("dpaperubahan").checked == false){
        dpaperubahan="0";
    }
    
    if(kodestatus == "1"){
        bootbox.alert("Data LRA Sudah Pernah Diroses.");
    } else {
        var varurl = getbasepath() + "/lra/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun : tahun,
            idskpd : idskpd,
            bulan : bulan,
            perubahan : dpaperubahan
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
            //grid();
            bootbox.alert("Proses LRA SKPD Berhasil Disimpan");
        });
    }
    
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var eee = tahun+"-"+"JURNAL-NERACA-SKPD"+"-"+idskpd+".pdf";
  
    window.location.href= getbasepath() + "/lra/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee;
}

function getkodestatus() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    
    var tglgabung = tahun+bulan;
    
        $.getJSON(getbasepath() + "/lra/json/getKodeStatus", {idskpd: idskpd, tanggal:tglgabung},
        function(result) {
            if (result == null){
            kodestatus = "0";
        } else {
            kodestatus = result;
        }
           console.log("Kode Status = "+kodestatus);
        });
       
}

function cek(){
    var kodeskpd = $("#kodeskpd").val();
    var namaskpd = $("#namaskpd").val();
    
    bootbox.alert("Kode SKPD = " + kodeskpd + " || Nama SKPD = " + namaskpd);
}
