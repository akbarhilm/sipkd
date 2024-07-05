$(document).ready(function() {
    
});


function cetakpotbuldaf() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var bulan = $("#bulan").val();
    
    var eee = aaa+"-"+"SP2DDAFTARPOTBULANAN"+bbb+".pdf";
    
   window.location.href= getbasepath() + "/cetaksp2dpotbtlblbulanan/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+bulan+"&namafile="+eee;
  
}

function tampilcek(id) {
    $("#cek" + id).show();
}




