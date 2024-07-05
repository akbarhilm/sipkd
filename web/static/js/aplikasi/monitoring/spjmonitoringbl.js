function getlistspdcetak(id) {
       gridspdbtlcetaklist( );
}


function gridspdbtlcetaklist() {
    //var urljson = getbasepath() + "/spd/monitoring/json/getmonitoring";
  }


function cetakmonbl() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#idskpd").val();
     var ddd = $("#tgl").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    //alert(aaa);
   // alert(ccc);
    var eee = aaa+"-"+"SPJ-MONITORING-BL"+"-"+ccc+".pdf"
    //alert(eee);
   window.location.href= getbasepath() + "/monitoringbl/json/prosescetakbl?tahun="+aaa+"&idskpd="+ccc+"&tgl="+ddd+"&namafile="+eee;
 // alert(window.location.href);
   
}


function cetaktes(){
    window.location.href= getbasepath() + "/monitoringbl/json/prosescetaktes";
    
}
