$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakssppph22() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
   // var ccc = $("#tgl").val();
    var fff = $("#nsp2d").val();
    var ddd = $("#namwil").val();
    var ggg = $("#idskpd").val();
    //var qqq =  ccc.split("/").join("-");
   // var www =  moment.utc(ccc, 'SSS').format('DDMM');
    console.log("Parameter : "+aaa+" "+bbb+" "+fff+" "+ggg);
    
    var eee = aaa+"-"+"SSP-PPh22-"+bbb+"-"+fff+".pdf";
    
   window.location.href= getbasepath() + "/cetakssppph22/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&nsp2d="+fff+"&idskpd="+ggg+"&namafile="+eee;
  
  }



function tampilcek(id) {
    $("#cek" + id).show();
}




