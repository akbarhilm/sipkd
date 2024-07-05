/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
   ready();
   
  
       $("#btnCetak").click(function() {
          
              
              window.parent.cetakTransaksi();
                parent.$.fancybox.close();

       
         
       });
       
        $("#btnBatal").click(function() {
          
              
             
                parent.$.fancybox.close();

       
         
       });
    
});



function ready(){
   $('div#lokasiLoket').html( $('#kodeLoket', window.parent.document).val() + ", " + $('#namaLoket', window.parent.document).val());
    $('div#noSts').html($('#noSkpd',window.parent.document).val());
      $('div#jenisSetoran').html("Pajak Daerah");
      $('div#namaNpwpd').html($('#namaNpwpd',window.parent.document).val());
      $('div#alamatNpwpd').html($('#alamatNpwpd',window.parent.document).val());
      $('div#caraBayar').html($("#caraBayar option:selected",window.parent.document).val() + " - " +$("#caraBayar option:selected",window.parent.document).text());
   
      $('div#tahun').html($("#tahunPajak",window.parent.document).val());
      $('div#objekPajak').html($("#idObjekPajak",window.parent.document).text());
      $('div#totalNilai').html($("#totalPenerimaan",window.parent.document).val());
       $('div#noValidasi').html($('#idValidasi',window.parent.document).val());
}



