$(document).ready(function() {
 is();
});

function setBulanEdit(tahun,idskpd) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    
    
    $.getJSON(getbasepath() + "/bapkas/json/setBulanEdit", {tahun: tahun,idskpd: idskpd},
    function(result) {
        //console.log(result);
      
        var banyak, bulan,ketbulan;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 bulan = result.aData[i]['BLNBKUBA'];
                 ketbulan = result.aData[i]['KETBUL'];
                 //alert(bulan+ketbulan);
                 opt += '<option value="'+ bulan + '">' + ketbulan + '</option>';
            
            }
        }
       
        $("#blnBkuBa1").html(opt);
       
        
    });
    
}

 function pindahhalamanadepan() {
        window.location.replace(getbasepath() + "/bapkas/indexbapkas");
    }
