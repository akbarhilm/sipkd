$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakregbul() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var p =ccc.substr(0,2);
   // alert(ccc);
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    //
    var eee = aaa+"-"+"REGISTER_SP2D_BULANAN"+bbb+"-"+qqq+".pdf";
   // alert(p);
   window.location.href= getbasepath() + "/registerbul/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+ccc+"&namafile="+eee;
  
  
}



function tampilcek(id) {
    $("#cek" + id).show();
}




