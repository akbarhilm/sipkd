$(document).ready(function() {
    setWilayah();     
});

function cetak() {

    var tahun = $("#tahun").val();
    var wilayah = $("#wilayah").val();
    var tanggal = $("#tgl").val();

    window.location.href = getbasepath() + "/lappnrm/json/prosescetakpnrmharian?tahun=" + tahun + "&wilayah=" + wilayah + "&tgl=" + tanggal;

}


function setWilayah() {
    
    $.getJSON(getbasepath() + "/lappnrm/json/setWilayah", {},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeWilayahProses'];
                 ket = result.aData[i]['namaWilayah'];
                
                 opt += '<option value="'+ kode + '">' + ket + '</option>';
            }
        }
       
        $("#wilayah").html(opt);
        
    });
    
}