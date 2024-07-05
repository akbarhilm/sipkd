function getlistspdcetak(id) {
       gridspdbtlcetaklist( );
}


function gridspdbtlcetaklist() {
    //var urljson = getbasepath() + "/spd/monitoring/json/getmonitoring";
  }


function cetakmonbiaya() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#idskpd").val();
   var ddd = $("#tgl").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    //alert(aaa);
   // alert(ccc);
    var eee = aaa+"-"+"SPD-MONITORING-BIAYA"+"-"+ccc+".pdf";
    //alert(eee);
   window.location.href= getbasepath() + "/spd/monitoringbiaya/json/prosescetakbiaya?tahun="+aaa+"&idskpd="+ccc+"&tgl="+ddd+"&namafile="+eee;
  //alert(window.location.href);
   
}


