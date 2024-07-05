$(document).ready(function() {
    //$("#tglPosting").datepicker("setDate", new Date());
    
});


function cetakjurnal() {
    var tgl = $("#tglPosting").val();
    var yy = tgl.substring(6);
    var mm = tgl.substr(3, 2);
    var dd = tgl.substr(0, 2);
    
    var tglpost = yy+mm+dd;
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();
    var idbas = $("#idbaspop").val();
   
    var eee = tahun+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+tglpost+".pdf";
    
    //bootbox.alert("Tanggal Posting = "+ tgl);
   
   if (tgl == ""){
       bootbox.alert("Tanggal Posting Harus Diisi");
   }else if (idskpd == ""){
       bootbox.alert("SKPD Harus Diisi");
   }else{
       //bootbox.alert("idskpd == "+ idskpd);
       window.location.href= getbasepath() + "/cetakbyidskpd/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee+"&tglpost="+tglpost+"&idbas="+idbas;
   }
   
  //alert(window.location.href);
   
}
