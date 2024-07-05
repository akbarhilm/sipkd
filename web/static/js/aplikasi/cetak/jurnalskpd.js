
function cetakjurnal() {
    
    
    var aaa = $("#tahun").val();
    var ccc = $("#nojurpop").val();
   
    var eee = aaa+"-"+"JURNAL-SKPD"+"-"+ccc+".pdf";
    
    if (ccc == ""){
       bootbox.alert("Pilih Nomor Jurnal Terlebih Dulu");
   } else {
       window.location.href= getbasepath() + "/cetakjurskpd/json/prosescetak?tahun="+aaa+"&nojur="+ccc+"&namafile="+eee;
   }
   
 
}


