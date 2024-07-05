/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
   ready();
   
  
       $("#btnBatal").click(function() {
          
              
              if(($('#noBa').val() != null) ||($('#keterangan').val() != null)||($('#noBa').val() != "") ||($('#keterangan').val() != "")){
                     
               window.parent.setNoBa($('#noBa').val());
               window.parent.setTglBa($('#tglBa').val());
                window.parent.setKeterangan($('#keterangan').val());
               window.parent.batal();
                parent.$.fancybox.close();
        }else{
            alert("Nomor berita acara dan keterangan harus diisi");
        }

       
         
       });
       
        $("#btnKembali").click(function() {
          
              
             
                parent.$.fancybox.close();

       
         
       });
    
});



function ready(){
     $('div#noValidasi').html($("#idValidasi",window.parent.document).val());
      $('div#nilaiPenerimaan').html($("#totalPenerimaan",window.parent.document).val());  
      $('div#namaNpwpd').html($('#namaNpwpd',window.parent.document).val());
      $('div#npwpd').html($('#npwpd',window.parent.document).val());
     $('#tglBa').val(window.parent.getJatuhTempoDefault());
    $('div#dateValidasi').html(window.parent.getDateValidasi());
      
    
}



