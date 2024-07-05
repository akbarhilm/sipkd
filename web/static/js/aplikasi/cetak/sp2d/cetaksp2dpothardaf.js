$(document).ready(function() {
    //gridsppup();
});

function gridsppup() {
    //var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
   }

function cetakpothardaf() {
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl").val();
    var ddd = $("#namwil").val();
    var qqq =  ccc.split("/").join("-");
    var www =  moment.utc(ccc, 'SSS').format('DDMM');
    
    var dd, mm, yy, tanggal;
    dd = ccc.substr(0, 2);
    mm = ccc.substr(3, 2);
    yy = ccc.substring(6);
    tanggal = yy + mm + dd; // d_posting (yyyymmdd)
    //
    //
    var eee = aaa+"-"+"SP2DDAFTARPOTHARIAN"+bbb+"-"+qqq+".pdf";
    
   window.location.href= getbasepath() + "/cetaksp2dpothardaf/json/prosescetak?tahun="+aaa+"&kproses="+bbb+"&tgl="+tanggal+"&namafile="+eee;
  
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




