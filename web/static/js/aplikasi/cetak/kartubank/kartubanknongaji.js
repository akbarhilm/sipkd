$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakkbnongaji() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var fff = $("#sa").val();
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    console.log("tgl : "+ccc);
    
    var eee = aaa+"-"+"KARTUBANKNONGAJI"+bbb+"-"+qqq+".pdf";
    
   window.location.href= getbasepath() + "/cetakkartubanknongaji/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+ccc+"&sa="+fff+"&namafile="+eee;
  
  }



function tampilcek(id) {
    $("#cek" + id).show();
}




