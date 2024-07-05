$(document).ready(function() {
    bulan = "01";
    getkodestatus();
});


var bulan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var kodeskpd = $("#kodeskpd").val();
    var namaskpd = $("#namaskpd").val();
    
    console.log("kode skpd"+kodeskpd);
    console.log("nama skpd"+namaskpd);
    
    if (kodestatus == "1"){
        bootbox.alert("Dara LRA Sudah Pernah Diproses.");
    } else{
        var varurl = getbasepath() + "/neracaskpd/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun : tahun,
            idskpd : idskpd,
            kodeskpd : kodeskpd,
            namaskpd : namaskpd,
            bulan: bulan
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
            bootbox.alert("Proses Neraca SKPD Berhasil Disimpan");
        });
    }
}

function getkodestatus() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    
    var tglgabung = tahun+bulan;
    
        $.getJSON(getbasepath() + "/neracaskpd/json/getKodeStatus", {idskpd: idskpd, tanggal:tglgabung},
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
    var postawal = formattanggal($("#tglPostingAwal").val());
    var postakhir = formattanggal($("#tglPosting").val());
    var kodeskpd = $("#kodeskpd").val();
    var namaskpd = $("#namaskpd").val();
    
    bootbox.alert("Kode SKPD = " + kodeskpd + " || Nama SKPD = " + namaskpd);
    //bootbox.alert("tanggal post  = " + tglpost);
}
