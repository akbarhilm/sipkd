function getlistspdcetak(id) {
     
}

function cetakjurnal() {
    
    
    var aaa = $("#tahun").val();
    var ccc = $("#nojurpop").val();
   
    var eee = aaa+"-"+"JURNAL-UMUM-SKPD"+"-"+ccc+".pdf"
    
    //bootbox.alert("No jurnal = "+ ccc);
   if (ccc == ""){
       bootbox.alert("Pilih Nomor Jurnal Terlebih Dulu");
   } else {
       window.location.href= getbasepath() + "/cetakbynojur/json/prosescetak?tahun="+aaa+"&nojur="+ccc+"&namafile="+eee;
   }
  
}


