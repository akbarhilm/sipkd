/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
   ready();
   
  
       $("#btnSimpan").click(function() {
          
              
              window.parent.simpanTransaksi();
                parent.$.fancybox.close();

       
         
       });
       
        $("#btnBatal").click(function() {
          
              
             
                parent.$.fancybox.close();

       
         
       });
    
});



function ready(){
   $('div#lokasiLoket').html( $('#kodeLoket', window.parent.document).val() + ", " + $('#namaLoket', window.parent.document).val());
    $('div#noSts').html($('#idSts',window.parent.document).val());
      $('div#npwpd').html($('#npwpd',window.parent.document).val());
      $('div#namaNpwpd').html($('#namaNpwpd',window.parent.document).val());
      $('div#alamatNpwpd').html($('#alamatNpwpd',window.parent.document).val());
      $('div#caraBayar').html($("#caraBayar option:selected",window.parent.document).text());
      $('div#catatan').html($("#uraian",window.parent.document).val());
      $('div#tahun').html($("#tahunPajak",window.parent.document).val());
      $('div#objekPajak').html($("#idObjekPajak option:selected",window.parent.document).text());
      $('div#totalPenerimaan').html($("#totalPenerimaan",window.parent.document).val());
      $('div#noLoket').html($("#noLoket",window.parent.document).val());
}



