function getlistspdcetak(id) {
       gridspdbtlcetaklist( );
}


function gridspdbtlcetaklist() {
    //var urljson = getbasepath() + "/spd/monitoring/json/getmonitoring";
  }


function cetakmonbantuan() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#idskpd").val();
    var ddd = $("#tgl").val();
    var qqq =  ddd.split("/").join("");
   
    //var www = moment.utc(ddd).format('YYYYMMDD');
  
    //alert(www);
    var eee = aaa+"-"+"SP2D-MONITORING-BANTUAN"+"-"+ccc+".pdf";
    //alert(eee);
   window.location.href= getbasepath() + "/monitoringbantuan/json/prosescetakbantuan?tahun="+aaa+"&idskpd="+ccc+"&tgl="+ddd+"&namafile="+eee;
  //alert(window.location.href);
   
}


