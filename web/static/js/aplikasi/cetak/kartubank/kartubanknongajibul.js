$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakkbnongajibul() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var fff = $("#sa").val();
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
    console.log("tgl 1: "+ccc);
    console.log("tgl 2: "+qqq);
    
    var eee = aaa+"-"+"KARTUBANKNONGAJI-BULANAN"+bbb+"-"+qqq+".pdf";
    
   window.location.href= getbasepath() + "/cetakkartubanknongajibul/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+ccc+"&sa="+fff+"&namafile="+eee;
  
  }



function tampilcek(id) {
    $("#cek" + id).show();
}




