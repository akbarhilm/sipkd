$(document).ready(function() {
   // tampil();
   $("#tanggal").datepicker();
   });


function cetakxls() {
   
    var tglawal = $("#tanggal").val();
    var taw = tglawal.substr(0,2);
    var maw = tglawal.substr(3,2);
    var yaw = tglawal.substr(6);
    var awall = yaw+maw+taw;
    
    
   
   //var rek = $("#noRekening").val();
    //var eee = aaa + "-" + "ADM-PENGGUNA.pdf";

    window.location.href = getbasepath() + "/DraftPajakOnline/xls/cetakxls?tglsp2d=" + awall;


}