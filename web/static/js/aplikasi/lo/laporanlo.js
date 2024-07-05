$(document).ready(function() {
   setComboBulan();
});

var bulan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    
    console.log("id skpd == "+idskpd);
    
   
    var eee = tahun+"-"+"LAPORAN-OPERASIONAL"+".pdf";
  /*
    window.location.href= getbasepath() + "/lra/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee;
    */
}

function setComboBulan() {
    var tahun = $("#tahun").val();
    var idskpd = $('#idskpd').val();
    
   
    $.getJSON(getbasepath() + "/lo/json/setBulan", {tahun: tahun, idskpd: idskpd},
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
