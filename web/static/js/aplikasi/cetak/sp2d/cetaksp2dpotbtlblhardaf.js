$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakpotbtlblhardaf() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
  //  var www =  moment.utc(ccc, 'SSS').format('DDMM');
   // alert(ccc); alert(qqq);
    var eee = aaa+"-"+"SP2DDAFTAR-POT-BTLBL-HARIAN"+bbb+"-"+ccc+".pdf";
    
   window.location.href= getbasepath() + "/cetaksp2dpotbtlblhardaf/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+ccc+"&namafile="+eee;
  
    /* var dataac = JSON.stringify({"tahun":aaa,"kproses":bbb,"tgl":qqq,"namafile":eee})
    
    console.log(dataac);
    return   $.ajax({
        type: "GET",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
       // data: dataac
    }).always(function(data) {
        gridsppup();
        bootbox.alert(data.responseText);
    });*/
}



function tampilcek(id) {
    $("#cek" + id).show();
}




