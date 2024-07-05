$(document).ready(function() {
   bulan = "01";
    
});

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var kodeskpd = $("#kodeskpdpop").val();
    var namaskpd = $("#namaskpdpop").val();
    var bulan = $("#bulan").val();
      
    console.log("bulan -- "+bulan);
    if (idskpd == ""){
        bootbox.alert("Pilih SKPD Terlebih Dulu.");
    } else if (bulan == null){
        bootbox.alert("Maaf, Belum Ada Data yang Tersedia untuk Dicetak.");
    } else {
        //window.location.href= getbasepath() + "/neracaskpd/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd;
  
    }
    
}

function setBulan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    
    $.getJSON(getbasepath() + "/neracaskpd/json/setBulan", {tahun: tahun, idskpd: idskpd},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeBulan'];
                 ket = result.aData[i]['ketBulan'];
                
                 opt += '<option value="'+ kode + '" >' + ket + '</option>';
            }
        }
       
        $("#bulan").html(opt);
       
    });
    
}

function cek(){
   var kodeskpd = $("#kodeskpdpop").val();
    var namaskpd = $("#namaskpdpop").val();idskpdpop
    var idskpd = $("#idskpdpop").val();
    
    bootbox.alert("ID SKPD = " + idskpd + " || Kode SKPD = " + kodeskpd + " || Nama SKPD = " + namaskpd);
    //bootbox.alert("tanggal post  = " + tglpost);
}
