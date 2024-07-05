$(document).ready(function() {
    setBulan();
});


var bulan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var kodeskpd = $("#kodeskpd").val();
    var namaskpd = $("#namaskpd").val();
    var bulan = $("#bulan").val();
    
    if (idskpd == "" || bulan == null){
        bootbox.alert("Maaf, Belum Ada Data yang Tersedia untuk Dicetak.");
    } else{
        //window.location.href= getbasepath() + "/neracaskpd/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd;
  
    }
}

function setBulan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    
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
                
                 opt += '<option value="'+ kode + '" selected>' + ket + '</option>';
            }
        }
       
        $("#bulan").html(opt);
        
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
