$(document).ready(function() {
   bulan = "01";
   getkodestatus(); 
});

var bulan;
var kodestatus;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}
function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var kodeskpd = $("#kodeskpdpop").val();
    var namaskpd = $("#namaskpdpop").val();
            
    //console.log("bulan === "+bulan);
        
    if (idskpd == ""){
        bootbox.alert("Data SKPD Tidak Boleh Kosong.");
    }else if (kodestatus == "1"){
        bootbox.alert("Data Sudah Pernah Diproses");
    }else{

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

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
      
    var eee = tahun+"-"+"JURNAL-NERACA-SKPD"+"-"+idskpd+".pdf";
  
    //bootbox.alert("idskpd == "+ idskpd);
    window.location.href= getbasepath() + "/neracaskpd/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee;
  
}

function getkodestatus() {
    var tahun = $("#tahun").val();
    //var idskpd = $("#idskpdpop").val();
    
    var tglgabung = tahun+bulan;
    
    $.getJSON(getbasepath() + "/lra/json/getKodeStatusProvinsi", {tanggal:tglgabung},
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
   var kodeskpd = $("#kodeskpdpop").val();
    var namaskpd = $("#namaskpdpop").val();idskpdpop
    var idskpd = $("#idskpdpop").val();
    
    bootbox.alert("ID SKPD = " + idskpd + " || Kode SKPD = " + kodeskpd + " || Nama SKPD = " + namaskpd);
    //bootbox.alert("tanggal post  = " + tglpost);
}
