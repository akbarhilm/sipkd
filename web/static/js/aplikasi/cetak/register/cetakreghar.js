$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakreghar() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    // alert(qqq);
    var eee = aaa+"-"+"REGISTER_SP2D_HARIAN"+bbb+"-"+qqq+".pdf";
    
   window.location.href= getbasepath() + "/registerhar/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+ccc+"&namafile="+eee;
  
   
}



function tampilcek(id) {
    $("#cek" + id).show();
}




